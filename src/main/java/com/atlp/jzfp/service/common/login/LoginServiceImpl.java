package com.atlp.jzfp.service.common.login;

import com.atlp.jzfp.entity.common.ComBLoginLogEntity;
import com.atlp.jzfp.repository.common.LoginLogRepository;
import com.atlp.jzfp.service.zzjg.yh.IYhService;
import lombok.extern.slf4j.Slf4j;
import org.atlp.utils.AtlpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;

/**
 * 登录 Service
 *
 * @author ctc
 * @date 2018年8月14日 23:11:18
 */
@Slf4j
@Service
@Transactional
public class LoginServiceImpl implements ILoginService {
    @Autowired
    private IYhService yhService;
    @Autowired
    LoginLogRepository loginLogRepository;

    //登录方法
    public Map<String, Object> doLogin(String userName, String userPwd, String clientIp) throws Exception {
        //new 登录日志实体对象
        ComBLoginLogEntity dlLog = new ComBLoginLogEntity();
        dlLog.setDlid(userName);
        dlLog.setDlsj(new Timestamp(new Date().getTime()));
        dlLog.setIp(clientIp);

        // 判断用户名是否存在
        Map userMap = yhService.findMapByDlid(userName);
        if (AtlpUtil.isEmpty(userMap)) {
            dlLog.setDljg("失败：用户名不存在");
            loginLogRepository.save(dlLog);
            throw new Exception("用户名不存在");
        }
        dlLog.setYhxm(AtlpUtil.toString(userMap.get("yhxm")));
        dlLog.setYhdwid(AtlpUtil.toString(userMap.get("yhdwid")));
        dlLog.setYhdwmc(AtlpUtil.toString(userMap.get("yhdwmc")));

        // 判断密码是否正确
        String dlmm = AtlpUtil.toString(userMap.get("dlmm"));
        if (!dlmm.equals(userPwd)) {
            dlLog.setDljg("失败：密码不正确");
            loginLogRepository.save(dlLog);
            throw new Exception("密码不正确");
        }

        //登录成功
        dlLog.setDljg("成功");
        dlLog.setHhid(AtlpUtil.getUUID());
        // 会话有效时间
        Calendar yxsjCa = Calendar.getInstance();
        yxsjCa.setTime(new Date());
        yxsjCa.add(Calendar.HOUR_OF_DAY, 1); // 加一个小时
        dlLog.setYxsj(new Timestamp(yxsjCa.getTime().getTime()));
        loginLogRepository.save(dlLog);

        //返回
        userMap.put("hhid", dlLog.getHhid());
        return userMap;
    }

    //判断hhid是否有效
    public boolean checkLogin(String hhid, String clientIp) {
        String sql = "select t.* from COM_B_LOGIN_LOG t " +
                " where t.hhid = '" + hhid + "' and t.ip = '" + clientIp + "' " +
                " and t.yxsj > sysdate ";
        List<ComBLoginLogEntity> retList = loginLogRepository.findAllBySql(sql);
        if (!AtlpUtil.isEmpty(retList) && retList.size() > 0) {
            ComBLoginLogEntity dlLog = retList.get(0);
            // 会话有效时间
            Calendar yxsjCa = Calendar.getInstance();
            yxsjCa.setTime(new Date());
            yxsjCa.add(Calendar.HOUR_OF_DAY, 1); // 加一个小时
            dlLog.setYxsj(new Timestamp(yxsjCa.getTime().getTime()));
            loginLogRepository.save(dlLog);
            return true;
        }
        return false;
    }
}
