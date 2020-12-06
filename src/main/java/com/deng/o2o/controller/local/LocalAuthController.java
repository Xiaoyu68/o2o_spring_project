package com.deng.o2o.controller.local;

import com.deng.o2o.dto.LocalAuthExecution;
import com.deng.o2o.entity.LocalAuth;
import com.deng.o2o.entity.PersonInfo;
import com.deng.o2o.enums.LocalAuthStateEnum;
import com.deng.o2o.service.LocalAuthService;
import com.deng.o2o.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "local",method = {RequestMethod.GET, RequestMethod.POST})
public class LocalAuthController {
    @Autowired
    private LocalAuthService localAuthService;

    @RequestMapping(value = "/bindlocalauth", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> bindLocalAuth(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();
        String username = HttpServletRequestUtil.getString(request,"username");
        String password = HttpServletRequestUtil.getString(request, "password");
        PersonInfo user = (PersonInfo) request.getSession().getAttribute("user");
        if(username != null && password != null && user != null && user.getUserId() != null) {
            LocalAuth localAuth = new LocalAuth();
            localAuth.setPassword(password);
            localAuth.setUsername(username);
            localAuth.setPersonInfo(user);

            LocalAuthExecution le = localAuthService.bindLocalAuth(localAuth);
            if(le.getState() == LocalAuthStateEnum.SUCCESS.getState()) {
                modelMap.put("success", true);
            } else {
                modelMap.put("success", false);
                modelMap.put("errMsg", le.getStateInfo());
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "can't be empty");
        }
        return modelMap;
    }

    @RequestMapping(value = "/changelocalpwd",method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> changeLocalPwd(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        String username = HttpServletRequestUtil.getString(request,"username");
        String password = HttpServletRequestUtil.getString(request, "password");
        PersonInfo user = (PersonInfo) request.getSession().getAttribute("user");
        String newPassword = HttpServletRequestUtil.getString(request, "newPassword");

        if(username != null && password != null && user != null && user.getUserId() != null && !password.equals(newPassword)){
            try {
                LocalAuth localAuth = localAuthService.getLocalAuthByUserId(user.getUserId());
                if(localAuth == null || !localAuth.getUsername().equals(username)) {
                    modelMap.put("success",false);
                    modelMap.put("errMsg","not the same");
                    return modelMap;
                }
                LocalAuthExecution le = localAuthService.modifyLocalAuth(user.getUserId(), username, password, newPassword);
                if(le.getState() == LocalAuthStateEnum.SUCCESS.getState()) {
                    modelMap.put("success", true);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", "please input the password");
                }
            } catch (Exception e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.getMessage());
            }
        } else{
            modelMap.put("success", false);
            modelMap.put("errMsg", "please input the password");
        }
        return modelMap;
    }

    @RequestMapping(value = "/logincheck",method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> logincheck(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        String username = HttpServletRequestUtil.getString(request,"username");
        String password = HttpServletRequestUtil.getString(request, "password");


        if(username != null && password != null){
                LocalAuth localAuth = localAuthService.getLocalAuthByUsernameAndPwd(username, password);

                if(localAuth != null) {
                    modelMap.put("success", true);
                    request.getSession().setAttribute("user",localAuth.getPersonInfo());
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", "username or password incorrect");
                }
        } else{
            modelMap.put("success", false);
            modelMap.put("errMsg", "please input the password");
        }
        return modelMap;
    }
    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    @ResponseBody
    Map<String, Object> logout(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        request.getSession().setAttribute("user", null);
        modelMap.put("success", true);
        return modelMap;
    }
}
