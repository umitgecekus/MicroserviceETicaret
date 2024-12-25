package com.umit.controller;

import com.umit.domain.User;
import com.umit.dto.request.DefaultRequestDto;
import com.umit.dto.request.UserSaveRequestDto;
import com.umit.dto.request.UserUpdateRequestDto;
import com.umit.exception.ErrorType;
import com.umit.exception.UserServiceException;
import com.umit.service.UserService;
import com.umit.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.umit.constants.RestApiUrls.*;

/**
 * http://localhost:9094/dev/v1/user
 */
@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
//loglama islemleri icin kullaniriz
@Slf4j
public class UserController {

    private final UserService userService;
    private final JwtTokenManager jwtTokenManager;
    @Value("${userservice.deger2}")
    private String deger2;
    @Value("${userservice.listem.iki}")
    private String iki;

    @GetMapping("/get-application-properties")
    public String getApplicationProperties(){
        log.trace("Properties Bilgisi...: "+ iki);
        log.debug("Properties Bilgisi...: "+ iki);
        log.info("Properties Bilgisi...: "+ iki);
        log.warn("Properties Bilgisi...: "+ iki);
        log.error("Properties Bilgisi...: "+ iki);


        System.out.println("Console çıktı...: "+ iki);
        return deger2;
    }

    @PostMapping(ADD)
    public ResponseEntity<Void> save(@RequestBody UserSaveRequestDto dto){
        userService.save(dto);
        return ResponseEntity.ok().build();
    }
    @PutMapping(UPDATE)
    public ResponseEntity<Void> update(UserUpdateRequestDto dto){
        userService.update(dto);
        return ResponseEntity.ok().build();
    }
    @GetMapping(GET_ALL)
    public ResponseEntity<List<User>>getAll(DefaultRequestDto dto){
        Optional<Long> authId = jwtTokenManager.validateToken(dto.getToken());
        if (authId.isEmpty())
            throw new UserServiceException(ErrorType.INVALID_TOKEN_ERROR);
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/get-string")
    public ResponseEntity<String> getString(String ad){
        return ResponseEntity.ok(userService.getString(ad));
    }

    @GetMapping("/get-all-by-name")
    public ResponseEntity<Page<User>> getAllByName(String userName, int page, int size, String sortParameter, String sortDirection){
        return ResponseEntity.ok(userService.findAllByUserName(userName, page, size, sortParameter, sortDirection));
    }



}
