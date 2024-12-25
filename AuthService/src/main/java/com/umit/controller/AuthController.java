package com.umit.controller;

import com.umit.dto.request.LoginRequestDto;
import com.umit.dto.request.RegisterRequestDto;
import com.umit.entity.Auth;
import com.umit.exception.AuthServiceException;
import com.umit.service.AuthService;
import com.umit.utility.JwtTokenManager;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.umit.constants.RestApiUrls.*;
import static com.umit.exception.ErrorType.*;

@RestController
@RequestMapping(AUTH)
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final JwtTokenManager jwtTokenManager;
    @PostMapping(REGISTER)
    public ResponseEntity<Boolean> register(@RequestBody @Valid RegisterRequestDto dto){
        return ResponseEntity.ok(authService.register(dto));
    }
    @PostMapping(LOGIN)
    public ResponseEntity<String> doLogin(@RequestBody @Valid LoginRequestDto dto){
        Optional<Auth> auth= authService.doLogin(dto);
        if (auth.isEmpty())
            throw new AuthServiceException(ERROR_INVALID_LOGIN_PARAMETER);
        Optional<String> token= jwtTokenManager.createToken(auth.get().getId());
        if (token.isEmpty())
            throw new AuthServiceException(ERROR_CREATE_TOKEN);
        return ResponseEntity.ok(token.get());
    }
    @GetMapping("/get-service-name")
    public ResponseEntity<String> getServiceName(){
        return ResponseEntity.ok("AuthService is running...");
    }




}
