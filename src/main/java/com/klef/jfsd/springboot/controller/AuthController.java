package com.klef.jfsd.springboot.controller;

import java.nio.charset.StandardCharsets;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klef.jfsd.springboot.model.Password;
import com.klef.jfsd.springboot.model.User;
import com.klef.jfsd.springboot.service.UserService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }

    
    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody Map<String, String> request) {
        String name = request.get("name");
        String displayName = request.get("displayName");

        // Create the user
        User user = userService.createUser(name, displayName);

        // Generate challenge and associate it with the user
        String challenge = Base64.encodeBase64URLSafeString(UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8));
        user.setChallenge(challenge);

        // Save the user with updated details
        userService.updateUser(user);

        // Build the response
        Map<String, Object> response = new HashMap<>();
        response.put("id", user.getId());
        response.put("challenge", challenge);
        response.put("rp", Map.of("name", "Example Corp"));
        response.put("user", Map.of(
                "id", user.getId(),
                "name", user.getName(),
                "displayName", user.getDisplayName()
        ));
        response.put("pubKeyCredParams", new Object[]{
                Map.of("type", "public-key", "alg", -7)
        });

        return response;
    }

    @PostMapping("/register/verify")
    public String verifyRegistration(@RequestBody Map<String, Object> request) {
        String userId = (String) request.get("userId");

        // Fetch user from the database
        User user = userService.getUser(userId);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        // Process registration details
        String credentialId = (String) ((Map) request.get("id")).get("id");
        String publicKey = (String) ((Map) request.get("response")).get("attestationObject");

        Password password = new Password();
        password.setId(credentialId);
        password.setPublicKey(publicKey);

        // Add the credential to the user and update the database
        user.getCredentials().add(password);
        userService.updateUser(user);

        return "Registration successful";
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> request) {
        String userId = request.get("userId");

        
        if(userId==null)
        {
        	System.out.println("User id cannot be null");
        	return null;
        }
        else
        {
        // Fetch user from the database
        User user = userService.getUser(userId);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        // Generate a new challenge
        String challenge = Base64.encodeBase64URLSafeString(UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8));
        user.setChallenge(challenge);

        // Update the user with the new challenge
        userService.updateUser(user);

        // Build the response
        Map<String, Object> response = new HashMap<>();
        response.put("challenge", challenge);
        response.put("allowCredentials", user.getCredentials().stream().map(password -> Map.of(
                "type", "public-key",
                "id", password.getId()
        )).toArray());

        return response;
        }
    }

    @PostMapping("/login/verify")
    public String verifyLogin(@RequestBody Map<String, Object> request) {
        String userId = (String) request.get("userId");

        // Fetch user from the database
        User user = userService.getUser(userId);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        // Simplified: Here, you would normally validate the signature and credentials
        return "User authenticated";
    }
}
