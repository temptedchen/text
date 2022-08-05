package com.cy.service;

import com.cy.bean.TblUserBean;
import com.cy.mapper.TblUserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private TblUserMapper tblUserMapper;
    @Override
    public TblUserBean login(String acc, String pwd) {
        return tblUserMapper.findUser(acc, pwd);
    }

    @Override
    public boolean delUser(String acc) {
        return tblUserMapper.delUser(acc);
    }

    @Override
    public boolean updUser(TblUserBean tblUserBean) {
        return tblUserMapper.updUser(tblUserBean);
    }

    @Override
    public boolean registerUser(TblUserBean tblUserBean) {

        return tblUserMapper.insertInfo(tblUserBean);
    }

    @Override
    public List<TblUserBean> select(String acc, String name) {
        List<TblUserBean> tblUserBeans = tblUserMapper.select1(acc, name);
        return tblUserBeans;
    }
}
