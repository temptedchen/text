package com.cy.mapper;

import com.cy.bean.TblUserBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TblUserMapper {
    //查找用户
    TblUserBean findUser(@Param("acc") String acc, @Param("pwd") String pwd);
    //注册
    boolean insertInfo(TblUserBean tblUserBean);
    //删除
    boolean delUser(String acc);
    //修改
    boolean updUser(TblUserBean tblUserBean);
    //查询
    List<TblUserBean> select1(@Param("acc") String acc, @Param("name") String name);
}
