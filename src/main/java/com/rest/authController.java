package com.rest;

import java.util.HashMap;
import java.util.Map;

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
import com.dto.UserLoginDto;
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
	public ResponseEntity<?> authenticateUser(@RequestBody UserLoginDto loginRequest) {
		if(userDetailsService.getByEmail(loginRequest.getEmail()).getStatus()==false) {
			return ResponseEntity.notFound().build();
		}else {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
			
			SecurityContextHolder.getContext().setAuthentication(authentication);

			String jwt = tokenProvider.createToken(loginRequest);
			if (jwt.isEmpty()) {
				return ResponseEntity.notFound().build();
			} else {
				return ResponseEntity.ok(jwt);
			}
		}

	}

	@PostMapping("/addAccount")
	public ResponseEntity<?> addAccount(@RequestBody Users user) {
		if(userDetailsService.getByEmail(user.getEmail())==null) {
		 {
				userDetailsService.addUser(user);

				UserLoginDto userdto = new UserLoginDto();
				userdto.setEmail(user.getEmail());
				userdto.setIsGoogle(user.getGoogle());
				userdto.setPassword(user.getPassword());
				String jwt = tokenProvider.createToken(userdto);
				if (jwt.isEmpty()) {
					return ResponseEntity.notFound().build();
				} else {
					return ResponseEntity.ok(jwt);
				}
			}
		}else {
			if(userDetailsService.getByEmail(user.getEmail()).getStatus()==false) {
				return ResponseEntity.notFound().build();
			}else {
//				userDetailsService.addUser(user);

				UserLoginDto userdto = new UserLoginDto();
				userdto.setEmail(user.getEmail());
				userdto.setIsGoogle(user.getGoogle());
				userdto.setPassword(user.getPassword());
				String jwt = tokenProvider.createToken(userdto);
				if (jwt.isEmpty()) {
					return ResponseEntity.notFound().build();
				} else {
					return ResponseEntity.ok(jwt);
				}
			}
		}



	}

	@GetMapping("/getOneUserByGoogleFalse")
	public ResponseEntity<?> getOneUserByGoogleFalse(@RequestParam String username) {
		if (userDetailsService.getOneUser(username) != null) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}

	}
	@GetMapping("/getOneUser")
	public ResponseEntity<?> getOneUser(@RequestParam String username) {
		if (userDetailsService.getByEmail(username) != null) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}

	}
	@GetMapping("/getAllUser")
	public ResponseEntity<?> getAllUser() {
		Map<String, Object> response = new HashMap<>();
		response.put("data", userDetailsService.getAllUser());
		return ResponseEntity.ok(response);

	}

	@PutMapping("/forgotpass")
	public ResponseEntity<?> forgotPassword( @RequestParam String email,
			@RequestParam String password) {
		System.err.println(password+"abc");
		Users user = userDetailsService.forgotPassword(email, password);
		if (user == null) {
			return ResponseEntity.notFound().build();
		} else {

			return ResponseEntity.ok().build();

	

		}

	}
	@PutMapping("/updateUser")
	public ResponseEntity<?> update(@RequestParam int id, @RequestParam String password,
			@RequestParam String oldPassword, @RequestParam String image) {
		Users user = userDetailsService.Update(id, password, oldPassword, image);
		if (user == null) {
			return ResponseEntity.notFound().build();
		} else {

			UserLoginDto userdto = new UserLoginDto();
			userdto.setEmail(user.getEmail());
			userdto.setIsGoogle(user.getGoogle());
			userdto.setPassword(user.getPassword());
			String jwt = tokenProvider.createToken(userdto);

			return ResponseEntity.ok(jwt);

		}

	}
	@PutMapping("/updateUserStatus")
	public ResponseEntity<?> updateStatus(@RequestParam int id, @RequestParam Boolean status) {
		Users tempUser=userDetailsService.UpdateStatus(id, status);
			return ResponseEntity.ok(tempUser);

		}
	@PutMapping("/updateUserRole")
	public ResponseEntity<?> updateRole(@RequestParam int id, @RequestParam Boolean role) {
		Users tempUser=userDetailsService.UpdateRole(id, role);
			return ResponseEntity.ok(tempUser);

		}

	
}
