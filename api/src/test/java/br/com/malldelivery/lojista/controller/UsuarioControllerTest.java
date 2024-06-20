package br.com.malldelivery.lojista.controller;

import br.com.malldelivery.lojista.model.Usuario;
import br.com.malldelivery.lojista.request.LoginRequest;
import br.com.malldelivery.lojista.request.UsuarioRequest;
import br.com.malldelivery.lojista.response.TokenResponse;
import br.com.malldelivery.lojista.seguranca.authentication.JwtTokenService;
import br.com.malldelivery.lojista.seguranca.userdetails.UserDetailsImpl;
import br.com.malldelivery.lojista.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.*;
@SpringBootTest
public class UsuarioControllerTest {

    @Autowired
    private UsuarioController usuarioController;

    @MockBean
    private UsuarioService usuarioService;
    @Autowired
    private JwtTokenService jwtTokenService;

    @Test
    public void shouldCreateUserSuccessfully() throws Exception {
        UsuarioRequest request = new UsuarioRequest();
        request.setUsername("newUser");
        request.setPassword("password");
        request.setIdPerfil(1);

        given(this.usuarioService.obterUsuarioPorUsername(request.getUsername())).willReturn(null);

        Usuario expectedUser = new Usuario();
        expectedUser.setUsername(request.getUsername());

        given(this.usuarioService.criar(request.getUsername(), request.getPassword(), request.getIdPerfil())).willReturn(expectedUser);

        ResponseEntity<Usuario> response = usuarioController.criar(request);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertEquals(expectedUser, response.getBody());
    }

    @Test
    public void shouldReturnConflictWhenUserAlreadyExists() throws Exception {
        UsuarioRequest request = new UsuarioRequest();
        request.setUsername("existingUser");
        request.setPassword("password");
        request.setIdPerfil(1);

        Usuario existingUser = new Usuario();
        existingUser.setUsername(request.getUsername());

        given(this.usuarioService.obterUsuarioPorUsername(request.getUsername())).willReturn(existingUser);

        ResponseEntity<Usuario> response = usuarioController.criar(request);

        Assertions.assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        Assertions.assertEquals(existingUser, response.getBody());
    }

    @Test
    public void shouldReturnAllUsers() {
        List<Usuario> expectedUsers = Arrays.asList(new Usuario(), new Usuario());

        given(this.usuarioService.obterTodos()).willReturn(expectedUsers);

        ResponseEntity<List<Usuario>> response = usuarioController.obterTodos();

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(expectedUsers, response.getBody());
    }

    @Test
    public void shouldReturnUserWhenIdExists() {
        int id = 1;
        Usuario expectedUser = new Usuario();
        expectedUser.setId(id);

        given(this.usuarioService.obterUsuarioPorId(id)).willReturn(expectedUser);

        ResponseEntity<Usuario> response = usuarioController.obterPorId(id);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(expectedUser, response.getBody());
    }

    @Test
    public void shouldReturnNotFoundWhenIdDoesNotExist() {
        int nonExistentId = -1;

        given(this.usuarioService.obterUsuarioPorId(nonExistentId)).willReturn(null);

        ResponseEntity<Usuario> response = usuarioController.obterPorId(nonExistentId);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void shouldReturnTokenWhenCredentialsAreValid() throws Exception {
        LoginRequest request = new LoginRequest();
        request.setUsername("validUser");
        request.setPassword("validPassword");

        Usuario validUser = new Usuario();
        validUser.setUsername(request.getUsername());

        given(this.usuarioService.obterUsuarioPorUsernameAndPassword(request.getUsername(), request.getPassword())).willReturn(validUser);

        UserDetailsImpl userDetails = new UserDetailsImpl(validUser);
        String expectedToken = jwtTokenService.generateToken(userDetails);

        ResponseEntity<TokenResponse> response = usuarioController.login(request);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(expectedToken, response.getBody().getToken());
    }

    @Test
    public void shouldThrowExceptionWhenCredentialsAreInvalid() throws Exception {
        LoginRequest request = new LoginRequest();
        request.setUsername("invalidUser");
        request.setPassword("invalidPassword");

        given(this.usuarioService.obterUsuarioPorUsernameAndPassword(request.getUsername(), request.getPassword())).willReturn(null);

        Assertions.assertThrows(Exception.class, () -> usuarioController.login(request));
    }
}
