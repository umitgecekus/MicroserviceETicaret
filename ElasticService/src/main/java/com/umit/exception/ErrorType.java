package com.umit.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ErrorType {

    INTERNAL_ERROR(1000, "Sunucuda beklenmeye hata oluştu, lütfen terar deneyiniz",HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_TOKEN_ERROR(1002, "Gecersiz token bilgisi",HttpStatus.BAD_REQUEST),
    ERROR_DUPLICATE_USERNAME(2000,"Bu Kullanıcı adı zaten kayıtlıdır. Lütfen değiştirerek tekrar deneyiniz.",HttpStatus.BAD_REQUEST),
    ERROR_INVALID_LOGIN_PARAMETER(2001,"Kullanici adi ya da sifre hatailidr, Lutfen tekrar deneyiniz",HttpStatus.BAD_REQUEST),
    ERROR_INVALID_USER_ID(2003,"Kullanici adi gecersiz, Boyle bir id li kullanici olmadigi icin guncelleme yapilamamistir",HttpStatus.BAD_REQUEST),
    BAD_REQUEST_ERROR(1001, "Girilen parametreler hatalıdır. Lütfen düzelterek tekrar deneyiniz", HttpStatus.BAD_REQUEST);

    int code;
    String message;
    HttpStatus httpStatus;
}
