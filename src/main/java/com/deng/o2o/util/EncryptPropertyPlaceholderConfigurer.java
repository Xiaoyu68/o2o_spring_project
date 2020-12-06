package com.deng.o2o.util;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.util.PropertyPlaceholderHelper;

public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
    private String[] encryptPropNames = {"jdbc.username","jdbc.password"};

    @Override
    protected String convertProperty(String propertyName, String propertyValue) {
        if(isEncryptProp(propertyName)) {
            String decryptValue = DESUtil.getDecryptString(propertyValue);
            return decryptValue;
        } else {
            return propertyValue;
        }
    }

    private boolean isEncryptProp(String properyName) {
        for (String encryptpropertyName:encryptPropNames) {
            if(encryptpropertyName.equals(properyName)) {
                return true;
            }
        }
        return false;
    }
}
