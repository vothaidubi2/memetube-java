package com.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.dao.UserDAO;
import com.dto.UserDto;
import com.entity.Users;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;
@Service
public class JwtTokenProviderService {
@Autowired
UserDAO dao;
	    @Value("${security.jwt.token.secret-key:secret-key}")
	    private String secretKey;
	    
	    private final UserService userService = new UserService();

	    @PostConstruct
	    protected void init() {
	        // this is to avoid having the raw secret key available in the JVM
	        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	    }

	    public String createToken(UserDto userdto) {
	        Date now = new Date();
	        Date validity = new Date(now.getTime() + 3600000); // 1 hour
	        Users user =new Users();
	        System.err.println(userdto);
	        if(userdto.getIsGoogle()==true) {
	        	user=  dao.findByEmailAndGoogleTrue(userdto.getEmail());
	        }else {
	        	user=  dao.findByEmailAndGoogleFalse(userdto.getEmail());
	        }
	        Algorithm algorithm = Algorithm.HMAC256(secretKey);
	        return JWT.create()
	                .withIssuedAt(now)
	                .withExpiresAt(validity)
	                .withClaim("Email", user.getEmail())
	                .withClaim("Password", user.getPassword())
	                .withClaim("Avatar", user.getAvatar())
	                .withClaim("Iduser", user.getIduser())
	                .withClaim("Role", user.getRole())
	                .withClaim("Status", user.getStatus())
	                .withClaim("Datecreate", user.getDatecreated().toString())
	                .sign(algorithm);
	    }




}
