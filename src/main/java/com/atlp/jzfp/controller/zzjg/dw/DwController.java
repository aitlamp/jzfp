package com.atlp.jzfp.controller.zzjg.dw;

import com.alibaba.fastjson.JSONObject;
import com.atlp.jzfp.common.base.BaseController;
import com.atlp.jzfp.common.data.PageModel;
import com.atlp.jzfp.common.prop.CustomProps;
import com.atlp.jzfp.service.zzjg.dw.IDwService;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * 组织架构--单位 Controller
 *
 * @author ctc
 * @date 2018年10月7日 11:30:06
 */
@Controller
@RequestMapping(value = "zzjg/dw")
public class DwController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IDwService dwService;
    @Autowired
    CustomProps customProps;

    /**
     * 获取单位分页数据
     */
    @RequestMapping(value = "/getPage", method = RequestMethod.POST)
    @ResponseBody
    public Map getPage(@RequestBody PageModel pageModel) {
        //System.out.println(page.getPmap().get("name"));
        return dwService.getPage(pageModel);
    }

    //处理文件上传
    @ResponseBody
    @RequestMapping(value = "/doUpload", method = RequestMethod.POST)
    public String uploadImg(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();
        /*System.out.println("fileName-->" + fileName);
        System.out.println("getContentType-->" + contentType);*/
        String filePath = request.getSession().getServletContext().getRealPath("imgupload/");
        try {
            //PicUploadResult picUpload = new PicUploadResult();
            String url = customProps.getNginxPath() + "/" + fileName;
            System.out.println(url);
            file.transferTo(new File(url));
            //FileUtil.uploadFile(file.getBytes(), filePath, fileName);

            String content = "hellow 中国";
            InputStream ips = new ByteArrayInputStream(content.getBytes("UTF-8"));
            System.out.println(upload("http://aitlamp.com:82/files/jzfp/", "text.txt", ips));

        } catch (Exception e) {
            // TODO: handle exception
        }
        //返回json
        return "uploadimg success";
    }

    public static JSONObject upload(String httpurl, String fileName, InputStream inputStream) {
        String result = "";
        try {
            String BOUNDARY = "---------7d4a6d158c9"; // 定义数据分隔线
            URL url = new URL(httpurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            conn.setRequestProperty("Charsert", "UTF-8");
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
            OutputStream out = new DataOutputStream(conn.getOutputStream());
            byte[] end_data = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();// 定义最后数据分隔线
            StringBuilder sb = new StringBuilder();
            sb.append("--");
            sb.append(BOUNDARY);
            sb.append("\r\n");
            sb.append("Content-Disposition: form-data;name=\"file" + 1 + "\";filename=\"" + fileName + "\"\r\n");
            sb.append("Content-Type:application/octet-stream\r\n\r\n");
            byte[] data = sb.toString().getBytes();
            out.write(data);
            DataInputStream in = new DataInputStream(inputStream);
            int bytes = 0;
            byte[] bufferOut = new byte[1024];
            while ((bytes = in.read(bufferOut)) != -1) {
                out.write(bufferOut, 0, bytes);
            }
            out.write("\r\n".getBytes()); // 多个文件时，二个文件之间加入这个
            in.close();
            out.write(end_data);
            out.flush();
            out.close();            // 定义BufferedReader输入流来读取URL的响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送POST请求出现异常！" + e);
        }
        return JSONObject.parseObject(result);
    }

}
