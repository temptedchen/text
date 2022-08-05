package com.cy.service;

import com.cy.bean.Tblrole;

import java.util.List;
import java.util.Map;

public interface RoleService {
    //查找角色
    public List<Tblrole> roles();
    //查找对应的权限
    public Map<String,Object> myPowers(String roleId);
    //删除权限
    public boolean delPower(String list, String powerIds);

}
