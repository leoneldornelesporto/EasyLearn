package br.com.easylearn.controller.dto;

import br.com.easylearn.domain.Usuario;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;

public class LoginDto {

    private Long idUser;
    private String authorizationCode;
    private String type;

    public LoginDto(String authorizationCode, Long id) {
        this.authorizationCode = authorizationCode;
        this.type = "Authorization";
        this.idUser = id;
    }

    public static LoginDto converterBase64(Optional<Usuario> user, String senha) {
        String base64 = Base64.getEncoder().encodeToString((user.get().getUsername() + ":" + senha).getBytes(StandardCharsets.UTF_8));
        String authorization = "Basic "+base64;
        return new LoginDto(authorization,user.get().getId());
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

    public Long getIdUser() {
        return idUser;
    }
}
