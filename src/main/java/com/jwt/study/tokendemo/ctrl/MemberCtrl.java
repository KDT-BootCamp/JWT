package com.jwt.study.tokendemo.ctrl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/members")
public class MemberCtrl {

    @PostMapping("/sign-in")
    public String postMethodName(@RequestBody String entity) {
        System.out.println("debug >>> ctrl endpoint /members/sign-in");
        
        return null;
    }

    @PostMapping("/test")
    public String test(@RequestBody String entity) {
       System.out.println("debug >>> ctrl endpoin /members/test");
        
        return "success";
    }
    


}
