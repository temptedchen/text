package com.cy.web;

import com.cy.bean.Tblrole;
import com.cy.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/powerPage")
    public String login(){
        return "powerManager";//页面的文件名名称
    }

    @RequestMapping("/roles")
    @ResponseBody
    public List<Tblrole> showRoles(){
        return roleService.roles();
    }
    @RequestMapping("/myPower")
    @ResponseBody
    public Map<String,Object> myPower(String roleId){
        return roleService.myPowers(roleId);
    }


    @RequestMapping("/delPower")
    @ResponseBody
    public Map<String,Object> delPower(String roleId,String list){
        return roleService.myPowers(roleId);
    }


}
