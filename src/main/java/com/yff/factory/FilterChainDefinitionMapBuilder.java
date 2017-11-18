package com.yff.factory;

import java.util.LinkedHashMap;

public class FilterChainDefinitionMapBuilder {

    public LinkedHashMap<String,String> filterChainDefinitionMapBuild() {
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        /* map.put("/login","anon");
         map.put("/logout","logout");
         map.put("/user","perms[user:add]");
         map.put("/user/add","roles[admin]");
         map.put("/rememberMe","user");
         map.put("/**","authc");*/
        map.put("/**","anon");
        return map;
    }
}
