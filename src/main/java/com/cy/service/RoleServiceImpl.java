package com.cy.service;

import com.cy.bean.TbPower;
import com.cy.bean.Tblrole;
import com.cy.mapper.TblPowerMapper;
import com.cy.mapper.TblRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private TblRoleMapper tblRoleMapper;        //角色表
    @Autowired
    private TblPowerMapper tblPowerMapper;      //权利表

    //查找所有的角色
    @Override
    public List<Tblrole> roles() {
        return tblRoleMapper.roles();
    }
    //查找对于的权限
    @Override
    public Map<String, Object> myPowers(String roleId) {
        Map<String,Object> map = new HashMap<>();
        List<TbPower> tbPowers = tblPowerMapper.myPower(roleId);
        map.put("list",tbPowers);
        //        全部的子权限
        List<TbPower> notList = tblPowerMapper.notPower(roleId);
        List<TbPower> notList1 = tblPowerMapper.notParentPower(roleId);
        notList.addAll(notList1);
        map.put("list1",notList1);
        return map;
    }

    @Override
    public boolean delPower(String list, String powerIds) {
        int[] nums = nums(list);

        return false;
    }


    private int[] nums(String list){
        list = list.replace("[","");
        list = list.replace("]","");
        String[] list1 = list.split(",");
        int[] nums = new int[list1.length];
        for (int i=0;i<list1.length;i++){
            nums[i] =Integer.parseInt(list1[i]);
        }
        return nums;
    }
}
