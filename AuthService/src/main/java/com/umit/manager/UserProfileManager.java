package com.umit.manager;

import com.umit.dto.request.UserSaveRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.umit.constants.RestApiUrls.ADD;

/**
 * Microservisler arasinda iletisimi RestAPI uzerinden saglamak icin kullanilir
 * Iletisim kurulacak servisin controller katmanina istek atar
 * 2 adet parametresini ozellikle kullanmalıyız:
 * 1- url: istek atilacak controller sınıfına erisim adresini yaziyoruz
 * 2- name(optional): yazilan her bir manager icin bir isim veriyoruz.zorunlu degildir ancak
 * ayni ismi tasiyan birden fazla manager olursa sistem hata verir .Sorunu anlamamiz mumkun
 * olmaya bilir Kullanirken dikkatli olmaliyiz.
 */
@FeignClient(url = "http://localhost:9094/dev/v1/user",name = "userProfileManager")
public interface UserProfileManager {
    @PostMapping(ADD)
    ResponseEntity<Void> save(@RequestBody UserSaveRequestDto dto);
}
