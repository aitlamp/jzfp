package org.atlp.data;

import com.atlp.jzfp.entity.zzjg.JzfpBZzjgYhEntity;
import lombok.Data;
import org.atlp.utils.AtlpUtil;

import java.util.Map;

/**
 * 用户信息
 *
 * @author ctc
 * @date 2018年8月23日 21:14:06
 */
@Data
public class UserInfo {
    // 用户信息
    private String yhid; // 用户ID
    private String dlid; // 登录ID
    private String yhxm; // 用户姓名
    private int yhpwsx; // 用户排位顺序
    // 单位信息
    private String dwid; // 单位ID
    private String dwbm; // 单位编码
    private String dwmc; // 单位名称
    private int dwpwsx; // 单位排位顺序
    // 岗位信息

    //构造函数
    public UserInfo(Map userMap) {
        // 用户信息
        this.yhid = AtlpUtil.toString(userMap.get("yhid"));
        this.dlid = AtlpUtil.toString(userMap.get("dlid"));
        this.yhxm = AtlpUtil.toString(userMap.get("yhxm"));
        this.yhpwsx = AtlpUtil.toInt(userMap.get("yhpwsx"));
        // 单位信息
        this.dwid = AtlpUtil.toString(userMap.get("dwid"));
        this.dwmc = AtlpUtil.toString(userMap.get("dwmc"));
        //this.dwpwsx = yhEntity.getDwpwsx();
    }
}
