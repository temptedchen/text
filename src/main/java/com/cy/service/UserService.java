package com.cy.service;

import com.cy.bean.TblUserBean;

import java.util.List;


public interface UserService {
    //登录
    public TblUserBean login(String acc, String pwd);
    //删除
    public boolean delUser(String acc);
    //修改
    public boolean updUser(TblUserBean tblUserBean);
    //注册
    public boolean registerUser(TblUserBean tblUserBean);
    //查询
    public List<TblUserBean> select(String acc, String name);
}
