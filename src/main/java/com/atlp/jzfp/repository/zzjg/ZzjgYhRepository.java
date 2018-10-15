package com.atlp.jzfp.repository.zzjg;

import org.atlp.base.BaseRepository;
import com.atlp.jzfp.entity.zzjg.JzfpBZzjgYhEntity;

/**
 * 用户 Repository
 *
 * @author ctc
 * @date 2018年8月15日 20:34:43
 */
public interface ZzjgYhRepository extends BaseRepository<JzfpBZzjgYhEntity, Long> {
    JzfpBZzjgYhEntity findByDlid(String dlid);
}
