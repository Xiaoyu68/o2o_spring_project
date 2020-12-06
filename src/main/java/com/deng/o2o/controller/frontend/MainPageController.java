package com.deng.o2o.controller.frontend;

import com.deng.o2o.entity.Headline;
import com.deng.o2o.entity.ShopCategory;
import com.deng.o2o.service.HeadlineService;
import com.deng.o2o.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/frontend")
public class MainPageController {
    @Autowired
    private ShopCategoryService shopCategoryService;
    @Autowired
    private HeadlineService headlineService;

    @RequestMapping(value="/listmainpageinfo",method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> listMainPageInfo(){
        Map<String, Object> modelMap = new HashMap<>();
        List<ShopCategory> shopCategoryList = new ArrayList<>();
        try{
            shopCategoryList = shopCategoryService.getShopCategoryList(null);
            modelMap.put("shopCategoryList",shopCategoryList);
        } catch (Exception e) {
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
            return modelMap;
        }
        List<Headline> headlineList = new ArrayList<>();
        try{
            Headline headlineCondition = new Headline();
            headlineCondition.setEnableStatus(1);
            headlineList = headlineService.getHeadlineList(headlineCondition);
            modelMap.put("headlineList",headlineList);
        } catch (Exception e) {
            modelMap.put("success",false);
            modelMap.put("errMsg",e.getMessage());
            return modelMap;
        }
        modelMap.put("success",true);
        return modelMap;
    }

}
