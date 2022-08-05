package com.cy.mapper;

import com.cy.bean.TbPower;

import java.util.List;

public interface TblPowerMapper {
    //我的权限
    List<TbPower> myPower(String roleId);
    List<TbPower> notPower(String roleId);
    List<TbPower> notParentPower(String roleId);

}
