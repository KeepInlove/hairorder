package com.gxy.hairorder.utils;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/**
 * @author Guo
 * @creed: 少壮不努力, 以后卡卡西
 * @Date 2022/3/12 16:14
 * @Classname sendSMS
 * @Description 短信验证工具类
 */
@Component
public class SMSUtil {

    String reStr;
    // 短信应用AppID
    @Value("${SSM.appid}")
    int appid ;
    // 短信应用AppKey
    @Value("${SSM.appkey}")
    String appkey ;
    // 短信模板ID
    @Value("${SSM.templateId}")
    int templateId;
    // 签名内容
    @Value("${SSM.smsSign}")
    String smsSign;
    public   String sendSMS(HttpServletRequest request, String phone, String code) {
        try {
            //参数
            String[] params = {code};
            //创建ssender对象
            SmsSingleSender singleSender = new SmsSingleSender(appid, appkey);
            SmsSingleSenderResult result = singleSender.sendWithParam("86", phone,
                    templateId, params, smsSign, "", "");
            System.out.println(result);
            HttpSession session = request.getSession();
            //JSONObject存入数据
            JSONObject json = new JSONObject();
            json.put("Code", code);//存入验证码
            json.put("createTime", System.currentTimeMillis());//存入发送短信验证码的时间
            // 将验证码和短信发送时间码存入SESSION
            request.getSession().setAttribute("MsCode", json);
            reStr = "success";
        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
        }catch (Exception e) {
            // 网络IO错误
            e.printStackTrace();
        }
        return reStr;
    }

}
