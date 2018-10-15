package com.atlp.jzfp.repository.zzjg;

import org.atlp.base.BaseRepository;
import com.atlp.jzfp.entity.zzjg.JzfpBZzjgYhEntity;

import java.util.Map;

/**
 * 用户 Repository
 *
 * @author ctc
 * @date 2018年8月15日 20:34:43
 */
public interface ZzjgYhRepository extends BaseRepository<JzfpBZzjgYhEntity, Long> {
    JzfpBZzjgYhEntity findByYhid(String yhid);

    JzfpBZzjgYhEntity findByDlid(String dlid);

}
