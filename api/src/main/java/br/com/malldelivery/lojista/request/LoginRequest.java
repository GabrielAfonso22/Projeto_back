package br.com.malldelivery.lojista.request;


import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginRequest {
    @NotEmpty(message = "O campo username não pode ser vazio")
    private String username;

    @NotEmpty(message = "O campo password não pode ser vazio")
    private String password;
}