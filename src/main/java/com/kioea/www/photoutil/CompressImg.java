package com.kioea.www.photoutil;

import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.io.*;

/**
 * 压缩图片
 */
public class CompressImg {

    private static Logger logger = Logger.getLogger(CompressImg.class);

    private static final String IMG_URL = "C:\\Users\\yinzhang.lu\\Pictures\\";

    public static void main(String[] args) throws Exception {
        String oriPath = "e5.png";
        saveThumb(oriPath, "/",  "e51.png");
    }

    /**
     * 保存缩略图
     * @param originalImgPath 原图的存放位置
     * @param thumbPath 缩略图保存的目录
     * @param thumbName 缩略图名称
     * @return
     * @throws Exception
     */
    public static String saveThumb(String originalImgPath, String thumbPath, String thumbName) throws Exception {
        byte[] originalImgBytes = CompressImg.getBytes(IMG_URL + originalImgPath);
        if(originalImgBytes == null || originalImgBytes.length == 0){
            throw new Exception("原始图片为空！");
        }
        if(StringUtils.isEmpty(thumbPath)){
            throw new Exception("缩略图保存路径为空！");
        }
        if(StringUtils.isEmpty(thumbName)){
            throw new Exception("缩略图名称为空！");
        }

        byte[] thumbBytes = CompressImg.compressPicForScale(originalImgBytes);
        if(thumbBytes == null || thumbBytes.length == 0){
            throw new Exception("图片压缩失败！");
        }

        CompressImg.getFile(thumbBytes, IMG_URL + thumbPath, thumbName);
        return thumbPath + "/" + thumbName;
    }


    public static byte[] getBytes(String filePath){
        byte[] buffer = null;
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    public static void getFile(byte[] bfile, String filePath,String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if(!dir.exists()){//判断文件目录是否存在
                dir.mkdirs();
            }
            file = new File(filePath+"/"+fileName);
            logger.debug(filePath+"/"+fileName);
            boolean delete = file.delete();
            logger.debug("删除图片"+delete);
            file.createNewFile();
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bfile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    public static byte[] compressPicForScale(byte[] imageBytes) {
        if (imageBytes == null || imageBytes.length <= 0) {
            return imageBytes;
        }
        long srcSize = imageBytes.length;
        double accuracy = getAccuracy(srcSize / 1024);
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(imageBytes);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream(imageBytes.length);
            Thumbnails.of(inputStream).scale(accuracy).outputQuality(accuracy).toOutputStream(outputStream);
            imageBytes = outputStream.toByteArray();
            logger.info("图片原大小={"+srcSize / 1024+"}kb | 压缩后大小={"+ imageBytes.length / 1024+"}kb");
        } catch (Exception e) {
            logger.error("【图片压缩】msg=图片压缩失败!", e);
        }
        return imageBytes;
    }

    private static double getAccuracy(long size) {
        if (size > 3072) {
            return 0.03;
        } else if (size > 1024) {
            return  0.06;
        } else if (size > 800) {
            return  0.08;
        } else if (size > 100) {
            return  0.1;
        } else {
            return 1;
        }
    }


}
