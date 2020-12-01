package com.deng.o2o.controller.shopadmin;

import com.deng.o2o.dto.ShopExecution;
import com.deng.o2o.entity.Area;
import com.deng.o2o.entity.PersonInfo;
import com.deng.o2o.entity.Shop;
import com.deng.o2o.entity.ShopCategory;
import com.deng.o2o.enums.ShopStateEnum;
import com.deng.o2o.service.AreaService;
import com.deng.o2o.service.ShopCategoryService;
import com.deng.o2o.service.ShopService;
import com.deng.o2o.util.HttpServletRequestUtil;
import com.deng.o2o.util.ImageUtil;
import com.deng.o2o.util.PathUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController {
    @Autowired
    private ShopService shopService;
    @Autowired
    private ShopCategoryService shopCategoryService;
    @Autowired
    private AreaService areaService;
    @RequestMapping(value = "/getshopinitinfo", method = RequestMethod.GET)
    @ResponseBody
    private Map<String,Object> getShopInitInfo(){
        Map<String, Object> modelMap = new HashMap<>();
        List<ShopCategory> shopCategoryList = new ArrayList<>();
        List<Area> areaList = new ArrayList<>();
        try{
            shopCategoryList = shopCategoryService.getShopCategoryList(new ShopCategory());
            areaList = areaService.getAreaList();
            modelMap.put("shopCategoryList",shopCategoryList);
            modelMap.put("areaList",areaList);
            modelMap.put("success",true);
        } catch (Exception e){
            modelMap.put("success", false);
            modelMap.put("errMsq", e.getMessage());
        }
        return modelMap;
    }
    @RequestMapping(value = "/registershop", method = RequestMethod.POST)
    @ResponseBody
    private Map<String,Object> registerShop(HttpServletRequest request) throws IOException {

        //1.接收并转化相应的参数，包括店铺信息以及图片信息
        Map<String, Object> modelMap = new HashMap<>();
        String shopStr = HttpServletRequestUtil.getString(request,"shopStr");
        ObjectMapper mapper = new ObjectMapper();
        Shop shop = null;
        try{
            shop = mapper.readValue(shopStr, Shop.class);
        } catch (Exception e){
            modelMap.put("Success", false);
            modelMap.put("errMsq",e.getMessage());
            return modelMap;
        }
        CommonsMultipartFile shopImg = null;
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if(commonsMultipartResolver.isMultipart(request)){
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
        } else {
            modelMap.put("success",false);
            modelMap.put("errMsq","can't be empty");
            return modelMap;
        }
        //2.注册店铺
        if(shop != null && shopImg != null){
            PersonInfo owner = new PersonInfo();
            owner.setUserId(1L);
            shop.setOwner(owner);
            ShopExecution se = shopService.addShop(shop,shopImg.getInputStream(),shopImg.getOriginalFilename());
            if(se.getState() == ShopStateEnum.CHECK.getState()){
                modelMap.put("success", true);
            } else {
                modelMap.put("success",false);
                modelMap.put("errMsq",se.getStateInfo());
            }
            return modelMap;
        } else {
            modelMap.put("success",false);
            modelMap.put("errMsq","please input shop info");
            return modelMap;
        }
        //3.返回结果

    }
//    private static void InputStreamToFile(InputStream ins, File file) throws IOException {
//        FileOutputStream os = null;
//        try{
//            os= new FileOutputStream(file);
//            int bytesRead = 0;
//            byte[] buffer = new byte[1024];
//            while ((bytesRead = ins.read(buffer)) != -1){
//                os.write(buffer,0,bytesRead);
//            }
//        } catch (Exception e){
//            throw new RuntimeException("InputStreamtoFile error!");
//        } finally {
//            try{
//                if(os != null){
//                os.close();
//            }
//            if(ins != null){
//                ins.close();
//            }
//        } catch (IOException e){
//            throw new RuntimeException("close io error" + e.getMessage());
//        }
//    }
//}
}
