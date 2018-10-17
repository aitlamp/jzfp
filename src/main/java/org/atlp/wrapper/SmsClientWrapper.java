package org.atlp.wrapper;

import com.atlp.jzfp.entity.common.ComBSmsLogEntity;
import com.atlp.jzfp.repository.common.SmsLogRepository;
import lombok.extern.slf4j.Slf4j;
import org.atlp.utils.AtlpUtil;
import org.atlp.utils.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 短信包装类
 *
 * @author 曹铁诚
 * @since 2018年10月16日 16:47:58
 */
@Slf4j
@Component
public class SmsClientWrapper {
    private static String smsUrl = "http://www.ztsms.cn/sendNSms.do";
    private static String username = "wangk";
    private static String password = "Wangk2016";
    private static String productid = "887361";

    @Autowired
    SmsLogRepository smsLogRepository;

    /**
     * 发送短信
     *
     * @param username  用户名
     * @param password  密码
     * @param tkey      时间戳
     * @param mobile    手机号
     * @param content   内容
     * @param sign      签名
     * @param productid 产品ID
     * @param xh        扩展的小号
     * @return
     */
    public Map sendSms(String username, String tkey, String password, String mobile, String content, String sign, String productid, String xh, HttpServletRequest request) {
        Map retMap = new HashMap();
        retMap.put("code", -2);
        retMap.put("msg", "失败");
        try {
            //new 日志实体对象
            ComBSmsLogEntity smsLog = new ComBSmsLogEntity();
            smsLog.setMobile(mobile);
            smsLog.setContent(content);

            //设置签名
            if (!AtlpUtil.isEmpty(sign)) {
                content = content + "【" + sign + "】";
            } else {
                sign = "网上辅助决策";
            }
            smsLog.setSign(sign);

            //组织参数
            Map pmap = new HashMap();
            pmap.put("username", username);
            pmap.put("tkey", tkey);
            pmap.put("password", password);
            pmap.put("mobile", mobile);
            pmap.put("content", content);
            pmap.put("productid", productid);
            pmap.put("xh", xh);
            //设置头信息
            Map headers = new HashMap();
            //调用接口
            Map responseMap = HttpClientUtil.postForm(smsUrl, pmap, headers);
            int statusCode = AtlpUtil.toInt(responseMap.get("statusCode"));
            if (statusCode == 200) {
                String responseContent = AtlpUtil.toString(responseMap.get("responseContent"));
                smsLog.setResultval(responseContent);
                String[] responseArr = responseContent.split(",");
                int code = AtlpUtil.toInt(responseArr[0]);
                retMap.put("code", code);
                if (responseArr.length > 1) {
                    String xxbh = responseArr[1];
                    retMap.put("xxbh", xxbh);
                }
                if (code == 1) {
                    smsLog.setDqzt("成功");
                    retMap.put("msg", "成功");
                } else {
                    smsLog.setDqzt("失败");
                    log.info("短信发送失败，错误代码：" + code);
                }
            }
            //保存日志
            smsLog.setFirsttime(new Timestamp(new Date().getTime()));
            smsLog.setLasttime(new Timestamp(new Date().getTime()));
            AtlpUtil.setUserInfo(smsLog, request);
            smsLogRepository.save(smsLog);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //返回数据
        return retMap;
    }

    /**
     * 发送短信
     *
     * @param mobile  手机号
     * @param content 内容
     * @param sign    签名
     * @param request 请求
     * @return
     */
    public boolean sendSms(String mobile, String content, String sign, HttpServletRequest request) {
        String date = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String password = md5Password(md5Password(this.password) + date);
        Map retMap = this.sendSms(username, date, password, mobile, content, sign, productid, "", request);
        int code = AtlpUtil.toInt(retMap.get("code"));
        if (code == 1) {
            return true;
        }
        return false;
    }

    /**
     * 发送短信
     *
     * @param mobile  手机号
     * @param content 内容
     * @param request 请求
     * @return
     */
    public boolean sendSms(String mobile, String content, HttpServletRequest request) {
        return this.sendSms(mobile, content, null, request);
    }

    /**
     * 发送短信
     *
     * @param mobile  手机号
     * @param content 内容
     * @param sign    签名
     * @return
     */
    public boolean sendSms(String mobile, String content, String sign) {
        return this.sendSms(mobile, content, sign, null);
    }

    /**
     * 发送短信
     *
     * @param mobile  手机号
     * @param content 内容
     * @return
     */
    public boolean sendSms(String mobile, String content) {
        return this.sendSms(mobile, content, null, null);
    }

    /**
     * md5加密方法
     */
    public String md5Password(String password) {
        try {
            // 得到一个信息摘要器
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(password.getBytes());
            StringBuffer buffer = new StringBuffer();
            // 把没一个byte 做一个与运算 0xff;
            for (byte b : result) {
                // 与运算
                int number = b & 0xff;// 加盐
                String str = Integer.toHexString(number);
                if (str.length() == 1) {
                    buffer.append("0");
                }
                buffer.append(str);
            }
            // 标准的md5加密后的结果
            return buffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void main(String[] args) {
        SmsClientWrapper smsClient = new SmsClientWrapper();
        boolean ret = smsClient.sendSms("17380400925", "");
        //log.debug(JSON.toJSONString(retMap));
        //log.info(ret);
    }
}
