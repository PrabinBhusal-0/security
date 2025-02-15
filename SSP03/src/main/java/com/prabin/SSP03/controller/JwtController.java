package com.prabin.SSP03.controller;

import com.prabin.SSP03.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class JwtController {

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/generate-token")
    public String generateToken(@RequestParam String username, @RequestParam String password){
        if ("admin".equals(username) && "admin".equals(password)){
            return jwtUtil.generateToken(username);
        }else {
            return "Invalid Credentials";
        }
    }

    @GetMapping("/contact")
    public String generateToken(){
        return "Contact Us at : 1234567890";
    }

    @GetMapping("/fund-transfer")
    public String secureApi(@RequestHeader(value = "Authorization", required = false) String authorizationHeader){
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            String token = authorizationHeader.substring(7);

            //validating the token
            if (jwtUtil.validateToken(token)){
                return "This is a Secured API , token valivated";// giving response or retuning something to th user
            }else {
                return "Invalid Token";
            }
        }else {
            return "Authorization header is Missing or malformed! ";
        }
    }
}
