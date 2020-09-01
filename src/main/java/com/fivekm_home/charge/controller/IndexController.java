package com.fivekm_home.charge.controller;

import com.fivekm_home.charge.config.auth.dto.SessionUser;
import com.fivekm_home.charge.domain.USER.*;
import org.codehaus.jackson.JsonNode;
import com.fivekm_home.charge.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

import static com.fivekm_home.charge.domain.USER.user.Role.일반;

@Controller
public class IndexController {
    @Autowired
    MemService memService;
    @Autowired
    HPService hpService;
    @Autowired
    MailService mailService;
    @Autowired
    StorageService storageService;

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
        model.addAttribute("seId", httpSession.getAttribute("selectId"));
        System.out.println("세션정보 : " + httpSession.getAttribute("selectId"));

        return "redirect:/index/searchId";
    }

    @PostMapping("/rest/searchPassword")
    public String searchPassword(SearchPassword searchPassword, HttpSession httpSession, Model model) {
        httpSession.setAttribute("selectPw",memService.searchPassword(searchPassword));
        model.addAttribute("sePw", httpSession.getAttribute("selectPw"));
        return "redirect:/index/searchPassword";
    }

    @GetMapping("/MS/kakaologin")
    public String kakaoLogina(@RequestParam("code") String code, Kakao kakao, HttpSession httpSession){
        JsonNode accessToken;
        org.codehaus.jackson.JsonNode jsonToken = KakaoAccessToken.getKakaoAccessToken(code);
        accessToken = jsonToken.get("access_token");

        // access_token을 통해 사용자 정보 요청
        JsonNode userInfo = KakaoUserInfo.getKakaoUserInfo(accessToken);

        // get id
        String id = userInfo.path("id").asText();

        // 유저정보 카카오에서 가져오기 Get properties
        JsonNode properties = userInfo.path("properties");
        JsonNode kakao_account = userInfo.path("kakao_account");

        String name = properties.path("nickname").asText();
        String email = kakao_account.path("email").asText();
        String picture = properties.path("thumbnail_image").asText();
        String asdf = memService.kakaoLoginChk(email).toString();
        email = email+"_kakao";
        // 이미 회원가입 했을 경우
        if( asdf.indexOf("null")>0 ){
            System.out.println("email로 검색 : " +memService.kakaoLogin(email));
            kakao.setRole(memService.kakaoLogin(email).getRole());
            kakao.setPicture(memService.kakaoLogin(email).getPicture());
            kakao.setName(memService.kakaoLogin(email).getName());
            kakao.setEmail(memService.kakaoLogin(email).getEmail());
            kakao.setPhone(memService.kakaoLogin(email).getPhone());
            SessionUser user = new SessionUser(kakao);
            httpSession.setAttribute("user", user);
            httpSession.setAttribute("role", user.getRole());
            System.out.println("카카오 로그인");
            return "/index/index";

         // 회원가입 해야하는 경우
        }else{
            kakao.setEmail(email);
            kakao.setName(name);
            kakao.setPicture(picture);
            memService.kakaoJoin(kakao); // 카카오 계정으로 회원가입

            System.out.println("카카오 계정 회원가입 후 : " + memService.kakaoLogin(email).toString());
            kakao.setRole(memService.kakaoLogin(email).getRole());
            kakao.setPicture(memService.kakaoLogin(email).getPicture());
            kakao.setName(memService.kakaoLogin(email).getName());
            kakao.setEmail(memService.kakaoLogin(email).getEmail());
            kakao.setPhone(memService.kakaoLogin(email).getPhone());
            SessionUser user = new SessionUser(kakao);
            httpSession.setAttribute("user", user);
            httpSession.setAttribute("role", user.getRole());
            System.out.println("카카오로 회원가입 후 로그인");
            return "/index/index";
        }
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
        return "/main/resources/templates/layout/asdfasdf.html";
    }
    @GetMapping("/main")
    public String mainn(){
        return "/templetSample/main";
    }
}
