package com.fivekm_home.charge.controller;

import com.fivekm_home.charge.config.auth.dto.SessionUser;
import com.fivekm_home.charge.domain.USER.*;
import com.fivekm_home.charge.service.MailService;
import com.fivekm_home.charge.service.MemService;
import com.fivekm_home.charge.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@RestController
public class IndexRestController {
    @Autowired
    MemService memService;
    @Autowired
    MailService mailService;
    @Autowired
    StorageService storageService;

    @PostMapping("/rest/checkId")
    public int checkId(LoginCheck loginCheck){
        System.out.println("아이디 중복체크: "+loginCheck.toString());
        return memService.checkId(loginCheck);
    } 

    @PostMapping("/rest/login")
    public Object login(Login login, HttpSession httpSession){
        System.out.println(memService.login(login));
        SessionUser user = new SessionUser(memService.login(login));
        httpSession.setAttribute("user", user);
        if(httpSession.getAttribute("user") != null){
            return "로그인 성공";
        }else{
            return "로그인 실패";
        }
    }

//    @PostMapping("/rest/join")
//    public void join(Join join, @RequestPart("file") MultipartFile file) throws Exception {
//        if(!file.isEmpty()){
//            String fileName = file.getOriginalFilename();
//            String dir2 = "D:\\Backup\\F\\Java_Workspace\\charge\\src\\main\\resources\\static\\img\\upload\\";
//            System.out.println("dir2 : " + dir2);
//            String path = Paths.get(dir2, fileName).toString();
//            System.out.println("path  : " + path);
//
//            BufferedOutputStream steam = new BufferedOutputStream(new FileOutputStream(new File(path)));
//            steam.write(file.getBytes());
//            steam.close();
//
//            join.setPicture("/img/upload/"+fileName);
//        }
//
//        memService.join(join);
//    }

    @PostMapping("/rest/join")
    public void join(Join join, @RequestPart("file") MultipartFile file){
        storageService.store(file);
        join.setPicture("/img/upload/"+file.getOriginalFilename());
        memService.join(join);
    }

    @PostMapping("/service/mail/*")
    @ResponseBody
    public void emailConfirm(Email email)throws Exception{
        mailService.sendSimpleMessage(email.getUserId());
    }
    @PostMapping("/verifyCode")
    @ResponseBody
    public int verifyCode(Email email) {
        int result = 0;
        if(MailService.ePw.equals(email.getEmailAuthText())) {
            result = 1;
            ver(result);
            return result;
        }else{
            result = 0;
            ver(result);
            return result;
        }
    }
    public static int ver(int ver){
        int result = 0;
        if(ver==1){
            return 1;
        }else{
            return 0;
        }
    }
}
