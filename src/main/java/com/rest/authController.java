package com.rest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.dto.UserDto;
import com.entity.Users;
import com.service.JwtTokenProviderService;
import com.service.UserService;
@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class authController {

    @Autowired(required = true)
    private AuthenticationManager authenticationManager;

    @Autowired(required = true)
    private JwtTokenProviderService tokenProvider;

    @Autowired
    private UserService userDetailsService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticateUser(@RequestBody UserDto loginRequest) {

    	 Authentication authentication = authenticationManager.
    	            authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.createToken(loginRequest);
    if(jwt.isEmpty()) {
    	   return ResponseEntity.notFound().build();
    }else {
        return ResponseEntity.ok(jwt);
    }
    }
    @PostMapping("/addAccount")
    public ResponseEntity<?> addAccount (@RequestBody Users user){
    	
    	userDetailsService.addUser(user);

    	UserDto userdto=new UserDto();
    	userdto.setEmail(user.getEmail());
    	userdto.setIsGoogle(user.getGoogle());
    	userdto.setPassword(user.getPassword());
        String jwt = tokenProvider.createToken(userdto);
    if(jwt.isEmpty()) {
    	   return ResponseEntity.notFound().build();
    }else {
        return ResponseEntity.ok(jwt);
    }

    }
    @GetMapping("/checkUser")
    public ResponseEntity<?> checkUser (@RequestParam String username){
    	if(userDetailsService.getOneUser(username)!=null) {
    		return ResponseEntity.ok().build();
    	}else {
    		return ResponseEntity.notFound().build();
    	}

    }
    @PutMapping("/updateUser")
    public ResponseEntity<?> update (@RequestParam int id,@RequestParam String password,@RequestParam String oldPassword,@RequestParam String image ){
    	if(userDetailsService.Update(id,password,oldPassword,image)==null) {
	return ResponseEntity.notFound().build();
}else {
	return   ResponseEntity.ok().build();
}
    }
    
}
