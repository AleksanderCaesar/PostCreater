package main.controller;

import main.api.response.ResultResponse;
import main.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiAuthController {

    @GetMapping("/auth/check")
    public ResponseEntity<?> checkAuth(){
        return ResponseEntity.ok(new ResultResponse());
    }
}
