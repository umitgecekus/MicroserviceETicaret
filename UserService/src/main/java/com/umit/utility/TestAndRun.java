package com.umit.utility;

import com.umit.dto.request.UserRequestDto;
import com.umit.manager.ElasticUserManager;
import com.umit.service.UserService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component //bu siniftan nesne yaratilcagini biliyor
@RequiredArgsConstructor
public class TestAndRun {

    private final UserService userService;
    private final ElasticUserManager userManager;
    //@PostConstruct //bu anatasyon bu methodun bir constructor gibi davramasını saglar
    public void start(){    //sanki const cagiriyor gibi(nesne olustururken) calistiktan sonra yorum haline getirebiliriz
        userService.findAll().forEach(user->{
            UserRequestDto dto= new UserRequestDto(
                    user.getId(),
                    user.getAuthId(),
                    user.getUserName(),
                    user.getEmail(),
                    user.getPhoto(),
                    user.getName(),
                    user.getPhone(),
                    user.getAbout()
            );
            userManager.save(dto);
        });
    }

}
