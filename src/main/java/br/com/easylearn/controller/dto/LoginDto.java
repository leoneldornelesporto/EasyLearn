package br.com.easylearn.controller.dto;

import br.com.easylearn.domain.Usuario;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

public class LoginDto {

    private String authorizationCode;
    private String type;

    public LoginDto(String authorizationCode) {
        this.authorizationCode = authorizationCode;
        this.type = "Authorization";
    }

    public static LoginDto converterBase64(Optional<Usuario> user, String senha) {
        String authorization = user.get().getUsername() + ":" + senha;
        return new LoginDto(authorization);
    }

    public String getAuthorizationCode() {
        return authorizationCode;
    }

    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static void main(String[] args) {
        String token = new BCryptPasswordEncoder().encode("12345");

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.matches("12345", token));
    }
}
