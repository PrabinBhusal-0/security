package com.prabin.SSP03.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.stereotype.Component;


import java.security.Key;
import java.util.Date;


@Component
public class JwtUtil {

    //Ensure this is a secure , long key (256 bites)
    private static final String SECRET_KEY_STRING = "prabin@!#$%^123456nextLeVeLSeCURIty";
    private final Key SECRET_KEY = Keys.hmacShaKeyFor(SECRET_KEY_STRING.getBytes());

    public String generateToken(String username){
        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() +1000 *60 * 30))//30 minutes
                .signWith(SECRET_KEY)
                .compact();
        System.out.println("Generated Token: "+ token);//log the generated token

        return token;
    }

    public boolean validateToken(String token){
        try{
            Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJwt(token);
            return true;//token is valid
        }catch (ExpiredJwtException e){
            System.out.println("Token Expired :: "+ e.getMessage());
            return false;
        }
        catch (SignatureException e){
            System.out.println("Invalid jwt signature:  "+e.getMessage());
            return false;
        }
        catch (MalformedJwtException e){
            System.out.println("Invalid jwt token  : "+e.getMessage());
            return false;
        }
        catch (UnsupportedJwtException e){
            System.out.println("jwt token is un_supported:  "+e.getMessage());
            return false;
        }
        catch (JwtException e){
            System.out.println("JWT  Exception :: "+ e.getMessage());
            return false;
        }

    }
}
