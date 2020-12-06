package com.deng.o2o.util;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Position;
import net.coobird.thumbnailator.geometry.Positions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

//把水印加到图片
//返回相对路径，可以通过basePath来找到
public class ImageUtil {
    //处理缩略图
    private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final Random r = new Random();
    private static Logger logger = LoggerFactory.getLogger(ImageUtil.class);
    public static File transferCommonsMultipleToFile(CommonsMultipartFile cFile) throws IOException {
        File newFile = new File(cFile.getOriginalFilename());
        cFile.transferTo(newFile);
        return newFile;
    }
    public static String generateThumbnail(InputStream thumbnail, String fileName, String targetAddr){
        String realFileName = getRandomFileName();
        String extension = getFileExtension(fileName);
        makeDirPath(targetAddr);
        String relativeAddr = targetAddr + realFileName + extension;
        logger.debug("current relativeAddr is:" + relativeAddr);
        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
        logger.debug("current complete address is" + PathUtil.getImgBasePath() + relativeAddr);
        try{
            Thumbnails.of(thumbnail).size(200, 200)
                    .watermark(Positions.BOTTOM_RIGHT,ImageIO.read(new File(basePath + "/wartermark.jpg")),0.25f)
                    .outputQuality(0.8f).toFile(dest);
        } catch (IOException e){
            logger.error(e.toString());
            e.printStackTrace();
        }
        return relativeAddr;
    }

    public static String generateNormalImg(InputStream thumbnail, String fileName, String targetAddr){
        String realFileName = getRandomFileName();
        String extension = getFileExtension(fileName);
        makeDirPath(targetAddr);
        String relativeAddr = targetAddr + realFileName + extension;
        logger.debug("current relativeAddr is:" + relativeAddr);
        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
        logger.debug("current complete address is" + PathUtil.getImgBasePath() + relativeAddr);
        try{
            Thumbnails.of(thumbnail).size(337, 640)
                    .watermark(Positions.BOTTOM_RIGHT,ImageIO.read(new File(basePath + "/wartermark.jpg")),0.25f)
                    .outputQuality(0.9f).toFile(dest);
        } catch (IOException e){
            logger.error(e.toString());
            e.printStackTrace();
        }
        return relativeAddr;
    }
    /**
     * 生成随机文件名，当前年月日小时分钟秒钟+五位随机数
     * @param
     * @throws IOException
     */
    public static String getRandomFileName(){
        int rannum = r.nextInt(89999) + 10000;
        String nowTimeStr = sDateFormat.format(new Date());
        return nowTimeStr + rannum;
    }

    /**
     * 获取输入文件流的扩展名
     * @param
     * @throws IOException
     */
    private static String getFileExtension(String fileName){
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * 创建目标路径所涉及的目录，即/home/work/xiangzai
     * @param
     * @throws IOException
     */
    private static void makeDirPath(String targetAddr){
        String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
        File dirPath = new File(realFileParentPath);
        if(!dirPath.exists()){
            dirPath.mkdirs();
        }
    }
    public static void main(String[] args) throws IOException {
        String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        Thumbnails.of(new File("/Users/dengxiaoyu/Desktop/1.jpg"))
                .size(200,200).watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark.jpg")),0.25f).outputQuality(0.8f)
                .toFile("/Users/dengxiaoyu/Desktop/image/2.jpg");
    }

    /**
     * 判断storePath是文件的路径还是目录的路径，
     *
     * @param storePath
     */
    public static void deleteFileOrPath(String storePath){
        File fileOrPath = new File(PathUtil.getImgBasePath() + storePath);
        if(fileOrPath.exists()) {
            if(fileOrPath.isDirectory()) {
                File files[] = fileOrPath.listFiles();
                for (int i = 0;i < files.length; i++) {
                    files[i].delete();
                }
            }
            fileOrPath.delete();
        }
    }
}
