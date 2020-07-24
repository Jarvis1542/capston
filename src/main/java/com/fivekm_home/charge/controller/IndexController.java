package com.fivekm_home.charge.controller;

import com.fivekm_home.charge.config.auth.dto.SessionUser;
import com.fivekm_home.charge.domain.USER.SearchId;
import com.fivekm_home.charge.domain.USER.SearchPassword;
import org.codehaus.jackson.JsonNode;
import com.fivekm_home.charge.domain.USER.Kakao;
import com.fivekm_home.charge.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

import static com.fivekm_home.charge.domain.USER.user.Role.일반;

@Controller
public class
IndexController {
    @Autowired
    MemService memService;
    @Autowired
    HPService hpService;
    @Autowired
    MailService mailService;
    @Autowired
    KakaoLoginService kakaoLoginService;

    @GetMapping("/")
    public String index() {
        return "/index/index";
    }

    @GetMapping("/index/searchId")
    public String searchId(Model model, HttpSession httpSession) {
        model.addAttribute("selId", httpSession.getAttribute("selectId"));
        return "/index/searchId";
    }

    @GetMapping("/index/searchPassword")
    public String searchPassword(Model model, HttpSession httpSession) {
        model.addAttribute("selPw", httpSession.getAttribute("selectPw"));
        return "/index/searchPassword";
    }

    @GetMapping("/index/join")
    public String join() {
        return "/index/join";
    }

    @GetMapping("/index/login")
    public String login(){
        return "/index/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession){
        httpSession.invalidate();
        return "redirect:/";
    }

    @PostMapping("/rest/searchId")
    public String searchId(SearchId searchId, HttpSession httpSession, Model model) {
        httpSession.setAttribute("selectId",memService.searchId(searchId));
//        model.addAttribute("seId", memService.searchId(searchId));
        model.addAttribute("seId", httpSession.getAttribute("selectId"));
        System.out.println("세션정보 : " + httpSession.getAttribute("selectId"));

        return "redirect:/index/searchId";
    }

    @PostMapping("/rest/searchPassword")
    public String searchPassword(SearchPassword searchPassword, HttpSession httpSession, Model model) {
        httpSession.setAttribute("selectPw",memService.searchPassword(searchPassword));
//        model.addAttribute("seId", memService.searchId(searchId));
        model.addAttribute("sePw", httpSession.getAttribute("selectPw"));
        System.out.println("세션정보 : " + httpSession.getAttribute("selectPw"));

        return "redirect:/index/searchPassword";
    }


    @GetMapping("/kakao")
    public String kakao(@RequestParam("code") String code, HttpSession httpSession, Kakao kakao){
        System.out.println("code : " + code);
        String access_Token = kakaoLoginService.getAccessToken(code);
        System.out.println("controller access_token : " + access_Token);
        HashMap<String, Object> userInfo = kakaoLoginService.getUserInfo(access_Token);
        System.out.println("login Controller : " + userInfo);
        /*userInfo.get("email");
        userInfo.get("nickname");*/
        //    클라이언트의 이메일이 존재할 때 세션에 해당 이메일과 토큰 등록
        if (userInfo.get("email") != null) {
            System.out.println("유저 이메일 : " + userInfo.get("email"));
            System.out.println("유저 이름 : " + userInfo.get("nickname"));
            System.out.println("유저 사진 : " + userInfo.get("thumbnail_image"));
            httpSession.setAttribute("userId", userInfo.get("email"));
            httpSession.setAttribute("access_Token", access_Token);
        }
        return "/";
    }

    @GetMapping("/MS/kakaologin")
    public String kakaoLogin(@RequestParam("code") String code, RedirectAttributes ra,
                    Kakao kakao, HttpSession httpSession, HttpServletResponse response){
        System.out.println("kakao code : " + code);
        JsonNode accessToken;
        org.codehaus.jackson.JsonNode jsonToken = KakaoAccessToken.getKakaoAccessToken(code);
        accessToken = jsonToken.get("access_token");
        System.out.println("access token : " + accessToken);

        // access_token을 통해 사용자 정보 요청
        JsonNode userInfo = KakaoUserInfo.getKakaoUserInfo(accessToken);

        // get id
        String id = userInfo.path("id").asText();
        String name = null;
        String email = null;
        String picture = null;

        // 유저정보 카카오에서 가져오기 Get properties
        JsonNode properties = userInfo.path("properties");
        JsonNode kakao_account = userInfo.path("kakao_account");

        name = properties.path("nickname").asText();
        email = kakao_account.path("email").asText();
        picture = properties.path("thumbnail_image").asText();

        System.out.println("id : " + id);
        System.out.println("name : " + name);
        System.out.println("email : " + email);
        System.out.println("picture : " + picture);
        kakao.setName(name);
        kakao.setEmail(email);
        kakao.setPicture(picture);
        kakao.setRole(일반);
        SessionUser user = new SessionUser(kakao);
        httpSession.setAttribute("user", user);
        return"/index/index";
    }
    // 부트스트랩
    @GetMapping("/about")
    public String about(){
        return "/templetSample/about";
    }
    @GetMapping("/apply")
    public String apply(){
        return "/templetSample/apply";
    }
    @GetMapping("/blog")
    public String blog(){
        return "/templetSample/blog";
    }
    @GetMapping("/contact")
    public String contact(){
        return "/templetSample/contact";
    }
    @GetMapping("/elements")
    public String elements(){
        return "/templetSample/elements";
    }
    @GetMapping("/faq")
    public String faq(){
        return "/templetSample/faq";
    }
    @GetMapping("/loan")
    public String loan(){
        return "/templetSample/loan";
    }
    @GetMapping("/single-blog")
    public String single_blog(){
        return "/templetSample/single-blog";
    }
    @GetMapping("/asdfasdf")
    public String asdfasdf(){
        return "/index/asdfasdf";
    }
    @GetMapping("/main")
    public String mainn(){
        return "/templetSample/main";
    }
}
