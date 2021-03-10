package com.chris.manager.service;

import com.chris.common.result.FileResult;

import java.io.InputStream;

/**
 * ClassName:UploadService
 * Package:com.chris.manager.service
 * Description:
 *
 * @Date:2021/3/10 10:45
 * @Author:Chris
 * @E-mail:gzshs@vip.qq.com
 */
public interface UploadService {

    /**
     * 文件上传
     * @param inputStream
     * @param fileName
     * @return
     */
    FileResult upload(InputStream inputStream, String fileName);
}
