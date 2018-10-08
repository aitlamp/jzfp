package com.atlp.jzfp.common.base;

import com.atlp.jzfp.common.data.PageModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * repository 基类，封装自定义查询方法
 *
 * @author 曹铁诚
 * @date 2018年9月22日 20:50:43
 */
@NoRepositoryBean //该注解表示 spring 容器不会创建该对象
public interface BaseRepository<T, ID extends Serializable> extends PagingAndSortingRepository<T, ID>, JpaRepository<T, ID> {

    List<Map> findAllByParams(String sql, Object... args);

    Page<Map> findPageByParams(String sql, Pageable pageable, Object... args);

    Page<Map> findPageBySql(String sql, Pageable pageable);

    PageModel findPageBySql(String sql, PageModel pageModel);

    PageModel findPageBySql(String sql, PageModel pageModel, String[][] columns);
}

