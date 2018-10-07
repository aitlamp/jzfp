package com.atlp.jzfp.repository.zzjg;

import com.atlp.jzfp.common.base.BaseRepository;
import com.atlp.jzfp.entity.zzjg.JzfpBZzjgCdEntity;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * 菜单 Repository
 *
 * @author ctc
 * @date 2018年8月9日 15:39:46
 */
public interface ZzjgCdRepository extends BaseRepository<JzfpBZzjgCdEntity, Long> {
    JzfpBZzjgCdEntity findByCdid(String cdid);

    List<JzfpBZzjgCdEntity> findByPcdid(String pcdid, Sort sort);
}
