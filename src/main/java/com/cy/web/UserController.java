package com.cy.web;

import com.cy.bean.TblUserBean;
import com.cy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }


    @RequestMapping("/loginView")
    public String login(){
        return "login";//页面的文件名名称
    }

    @RequestMapping("/registerView")
    public String register(){
        return "register";
    }

    @RequestMapping("/powerManager")
    public String powerView(){
        return "powerManager";
    }

    @RequestMapping("/login")
    @ResponseBody
    public TblUserBean loginSuccess(String acc, String pwd){
        return userService.login(acc, pwd);
    }

    @RequestMapping(value = "/select1",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<TblUserBean> select1( String name, String acc){
        List<TblUserBean> select = userService.select(acc, name);
        return select;
    }

    @RequestMapping("/register")
    public String register(TblUserBean userBean, MultipartFile fileName, HttpServletRequest request){
        String fn = fileName.getOriginalFilename();//获取名字
        //重新设置新的文件名，防止重名覆盖
        String newFileName = UUID.randomUUID().toString() + fn.substring(fn.lastIndexOf(".",fn.length()));
        try {
            String path = request.getServletContext().getRealPath("/upload");
            System.out.println(path);
            File file = new File(path);
            if(!file.exists()){
                file.mkdirs();
            }
            fileName.transferTo(new File(path+"/"+newFileName));
            userBean.setHeadImg(newFileName);
            //写入数据库
            boolean b = userService.registerUser(userBean);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "register";
    }

}
