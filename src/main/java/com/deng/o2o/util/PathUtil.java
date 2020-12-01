package com.deng.o2o.util;
import java.io.FileInputStream;
import java.util.Properties;

public class PathUtil {
    private static String seperator = System.getProperty("file.separator");
    public static String getImgBasePath(){
        String os = System.getProperty("os.name");
        String basePath = "";
        if(os.toLowerCase().startsWith("win")){
            basePath = "D:/projectdev/image";
        } else {
            basePath = "/Users/dengxiaoyu/Desktop/image";
        }
        basePath = basePath.replace("/",seperator);
        return basePath;
    }
    public static String getShopImagePath(long shopId){
        String imagePath = "upload/item/shop" + shopId + "/";
        return imagePath.replace("/", seperator);
    }
}
