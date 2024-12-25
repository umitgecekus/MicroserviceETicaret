package com.umit.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document //entity table yerine bunu ekledik
public class User implements Serializable {
    @Id
    String id;

    /**
     * Kullanici kayit olurken Auth bilgilerini eslestirmek uzere
     * auth_id bilgisini burada tutuyoruz
     */
    Long authId;
    String userName;
    String email;
    String photo;
    String name;
    String phone;
    String about;

}
