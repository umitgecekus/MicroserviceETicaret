package com.umit.controller;

import com.umit.domain.User;
import com.umit.dto.request.UserRequestDto;
import com.umit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import static com.umit.constants.RestApiUrls.*;
@RequiredArgsConstructor
@RestController
@RequestMapping(USER)
public class UserController {
    private final UserService userService;
    @PostMapping(ADD)
    @CrossOrigin("*")
    public ResponseEntity<Void> save(@RequestBody UserRequestDto dto){
        userService.save(dto);
        return ResponseEntity.ok().build();
    }


    @PostMapping(UPDATE)
    @CrossOrigin("*")
    public ResponseEntity<Void> update(@RequestBody UserRequestDto dto){
        userService.update(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping(GET_ALL)
    public ResponseEntity<Iterable<User>> getAll(){
        return  ResponseEntity.ok(userService.getAll());
    }
}
