package com.gxy.hairorder.controller;

import com.gxy.hairorder.exception.BusinessException;
import com.gxy.hairorder.exception.BusinessExceptionCode;
import com.gxy.hairorder.resp.CommonResp;
import com.gxy.hairorder.resp.UploadResp;
import com.gxy.hairorder.resp.UserLoginResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author GUO
 * @Classname FileController
 * @Description TODO
 * @Date 2022/2/28 20:34
 */
@RestController
@Slf4j
public class FileController {
//    文件上传路径为：F:/usr/local/upload/
    @Value("${upload.path}")
    private String fileSavePath;
//    最终文件的访问路径：http://127.0.0.1:8089/53CE765D2770486AB51EDFF525AC53E8bj.png
    @Value("${upload.url}")
    private String fileComingPath;

    @PostMapping("/upload")
    public CommonResp<UploadResp> updloadImg(MultipartFile file, HttpServletRequest request){
        CommonResp<UploadResp> resp=new CommonResp<>();
        try {
            //获取文件上传路径
            String path = fileSavePath;
            log.info("文件上传路径为："+path);
            File files = new File(path);
            //判断不存在该目录就创建
            if (!files.exists()){
                files.mkdirs();
            }
            //获取文件名
            String filename = file.getOriginalFilename();
            log.info(filename);
            //起别名
            String s = UUID.randomUUID().toString().replace("-", "").toUpperCase();
            filename=s+filename;
            //开始上传
            file.transferTo(new File(files,filename));

            UploadResp uploadResp = new UploadResp();
            uploadResp.setFileName(fileComingPath+filename);
            log.info("最终文件的访问路径："+uploadResp.getFileName());
            resp.setContent(uploadResp);
            resp.setMessage("图片上传成功!!");
            return resp;
        } catch (IOException e) {
            log.error("图片上传失败"+file.getOriginalFilename());
            throw new BusinessException(BusinessExceptionCode.SERVICE_ERROR);
        }
    }
}
