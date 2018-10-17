package com.atlp.jzfp.repository.zzjg;

import org.atlp.base.BaseRepository;
import com.atlp.jzfp.entity.zzjg.JzfpBZzjgDwEntity;
import org.springframework.data.jpa.repository.Query;

/**
 * 组织架构--单位 Repository
 *
 * @author ctc
 * @date 2018年10月7日 11:17:56
 */
public interface ZzjgDwRepository extends BaseRepository<JzfpBZzjgDwEntity, Long> {
    JzfpBZzjgDwEntity findByDwid(String dwid);

    JzfpBZzjgDwEntity findAllByDwidOrDqzt(String dwid, String dqzt);
}
