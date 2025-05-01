package com.example.demo.Controller;

import com.example.demo.Dto.Request.LoginDto;
import com.example.demo.Dto.Request.OrgLoginDto;
import com.example.demo.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    //JWT token döndür
    @PostMapping("/user")
    public String login(@RequestBody LoginDto loginDto)
    {
        return loginService.login(loginDto);
    }

    @PostMapping("/organizator")
    public String login(@RequestBody OrgLoginDto orgLoginDto){
        return loginService.login(orgLoginDto);
    }
}
