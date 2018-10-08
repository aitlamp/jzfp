package com.atlp.jzfp.common.base;

import com.atlp.jzfp.common.data.PageModel;
import com.atlp.jzfp.common.utils.AtlpUtil;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 曹铁诚
 * @date 2018年10月6日 15:09:02
 */
public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

    //实体管理类，对持久化实体做增删改查，自动义sq操作模板所需要的核心类
    public final EntityManager entityManager;

    public BaseRepositoryImpl(JpaEntityInformation<T, ID> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Map> findAllByParams(String sql, Object... args) {
        Query query = entityManager.createQuery(sql);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

        int i = 0;
        for (Object arg : args) {
            query.setParameter(i++, arg);
        }

        List<Map> list = query.getResultList();
        List<Map> retList = new ArrayList<>();
        for (Map map : list) {
            Map tMap = AtlpUtil.mapKeyCaseConvert(map);
            retList.add(tMap);
        }
        return retList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Page<Map> findPageByParams(String sql, Pageable pageable, Object... args) {
        Query query = entityManager.createQuery(sql);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

        int i = 0;
        for (Object arg : args) {
            query.setParameter(i++, arg);
        }

        List<Map> content = new ArrayList<>();
        List<Map> list = query.getResultList();
        for (Map map : list) {
            Map tMap = AtlpUtil.mapKeyCaseConvert(map);
            content.add(tMap);
        }

        query.setFirstResult(pageable.getPageSize() * (pageable.getPageNumber() - 1));
        query.setMaxResults(pageable.getPageSize());
        Page<Map> pages = new PageImpl<>(content, pageable, list.size());
        return pages;
    }

    /**
     * 根据sql查询分页数据 -- spring page
     */
    @Transactional(rollbackFor = Exception.class)
    public Page<Map> findPageBySql(String sql, Pageable pageable) {
        Query query = entityManager.createNativeQuery(sql);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

        List<Map> content = new ArrayList<>();
        List<Map> list = query.getResultList();
        for (Map map : list) {
            Map tMap = AtlpUtil.mapKeyCaseConvert(map);
            content.add(tMap);
        }

        query.setFirstResult(pageable.getPageSize() * (pageable.getPageNumber() - 1));
        query.setMaxResults(pageable.getPageSize());
        Page<Map> pages = new PageImpl<>(content, pageable, list.size());
        return pages;
    }

    /**
     * 根据sql查询分页数据 -- spring page
     */
    @Transactional(rollbackFor = Exception.class)
    public PageModel findPageBySql(String sql, PageModel pageModel) {
        //根据sql查询数据，并返回Map
        Query query = entityManager.createNativeQuery(sql);
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

        //总记录数
        int totalRows = query.getResultList().size();

        //设置查询的起始和结束记录
        query.setFirstResult(pageModel.getPageSize() * (pageModel.getCurrentPage() - 1));
        query.setMaxResults(pageModel.getPageSize());

        //将map的key值转换成小写
        List<Map> content = new ArrayList<>();
        List<Map> list = query.getResultList();
        for (Map map : list) {
            Map tMap = AtlpUtil.mapKeyCaseConvert(map);
            content.add(tMap);
        }

        //设置返回值
        pageModel.setTotalPages((int) Math.ceil((double) totalRows / pageModel.getPageSize()));
        pageModel.setTotalRows(totalRows);
        pageModel.setRows(content);

        return pageModel;
    }

}
