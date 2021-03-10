package com.chris.manager.service.impl;

import com.chris.common.result.FileResult;
import com.chris.manager.service.UploadService;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * ClassName:UploadServiceImpl
 * Package:com.chris.manager.service.impl
 * Description:
 *
 * @Date:2021/3/10 10:46
 * @Author:Chris
 * @E-mail:gzshs@vip.qq.com
 */
@Service
public class UploadServiceImpl implements UploadService {
    @Override
    public FileResult upload(InputStream inputStream, String fileName) {

        FileResult fileResult = new FileResult();
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.region2());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        String accessKey = "IhDRMmJcE0QKczHDtxYsq3U7PBXqOlj71N3ipgC2";
        String secretKey = "NaoC41lH7vOHHpK_vDs0NsI-z1o2I1eql2kAHH07";
        String bucket = "chris996";
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = fileName;
        try {
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            try {
                Response response = uploadManager.put(inputStream,key,upToken,null, null);
                //解析上传成功的结果
                if (response.statusCode == 200) {
                    fileResult.setSuccess("success");
                    fileResult.setMessage("上传成功！");
                    fileResult.setFileUrl("http://qpqejxse7.hn-bkt.clouddn.com/" + fileName);
                    return fileResult;
                } else {
                    fileResult.setError("error");
                    fileResult.setMessage("上传失败！");
                    return fileResult;
                }
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                fileResult.setError("error");
                fileResult.setMessage("上传失败！");
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (Exception ex) {
            //ignore
        }

        return fileResult;
    }
}
