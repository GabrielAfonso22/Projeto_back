package br.com.malldelivery.lojista.service;

import br.com.malldelivery.lojista.exception.LojaException;
import br.com.malldelivery.lojista.model.Perfil;
import br.com.malldelivery.lojista.model.Usuario;
import br.com.malldelivery.lojista.repository.*;
import br.com.malldelivery.lojista.request.UsuarioRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UsuarioServiceTest {
    @MockBean
    private UsuarioRepository usuarioRepository;

    @MockBean
    private PerfilRepository perfilRepository;

    @Autowired
    private UsuarioService usuarioService;

    private UsuarioRequest request;

    @BeforeEach
    public void Setup() {
        request = new UsuarioRequest();
        request.setUsername("usuario");
        request.setPassword("senha");
    }

//    @Test
//    public void DeveCriarUmUsuarioSuccess() throws Exception {
//        Perfil perfil = new Perfil();
//        perfil.setId(1);
//        perfil.setNome("ADMIN");
//        perfilRepository.save(perfil);
//
//        request.setIdPerfil(perfil.getId());
//
//        given(this.perfilRepository.existsById(1))
//            .willReturn(true);
//
//        given(this.usuarioRepository.findByUsername(request.getUsername()))
//            .willReturn(Optional.empty());
//
//        Usuario novoUsuario = this.usuarioService.criar(request.getUsername(), request.getPassword(), request.getIdPerfil());
//
//        Assertions.assertNotNull(novoUsuario);
//        Assertions.assertEquals(request.getUsername(), novoUsuario.getUsername());
//        Assertions.assertEquals(request.getPassword(), novoUsuario.getPassword());
//    }

    @Test
    public void DeveJogarUmaExcecaoUsuarioJaExiste() {
        Perfil perfil = new Perfil();
        perfil.setId(1);
        perfil.setNome("ADMIN");
        perfilRepository.save(perfil);

        request.setIdPerfil(perfil.getId());

        given(this.perfilRepository.existsById(1))
            .willReturn(true);

        given(this.usuarioRepository.findByUsername(request.getUsername()))
            .willReturn(Optional.of(new Usuario()));

        Assertions.assertThrows(Exception.class, () -> {
            this.usuarioService.criar(request.getUsername(), request.getPassword(), request.getIdPerfil());
        });
    }

    @Test
    public void DeveListarTodosUsuarios() {
        Usuario usuario = new Usuario();

        given(this.usuarioRepository.findAll())
            .willReturn(List.of(usuario));

        Assertions.assertNotNull(this.usuarioService.obterTodos());
        Assertions.assertEquals(1, this.usuarioService.obterTodos().size());
    }

    @Test
    public void DeveRetornarUmUsuarioPorId() {
        Usuario usuario = new Usuario();
        usuario.setId(1);

        given(this.usuarioRepository.findById(1))
            .willReturn(Optional.of(usuario));

        Assertions.assertNotNull(this.usuarioService.obterUsuarioPorId(1));
        Assertions.assertEquals(1, this.usuarioService.obterUsuarioPorId(1).getId());
    }

    @Test
    public void DeveRetornarUmUsuarioQuandoEncontradoPorUsernameESenha() {
        String username = "usuario";
        String password = "senha";
        Usuario usuarioEsperado = new Usuario();
        usuarioEsperado.setUsername(username);
        usuarioEsperado.setPassword(password);

        when(usuarioRepository.findByUsernameAndPassword(username, password)).thenReturn(Optional.of(usuarioEsperado));

        Usuario actualUser = usuarioService.obterUsuarioPorUsernameAndPassword(username, password);

        Assertions.assertEquals(usuarioEsperado, actualUser);
    }

    @Test
    public void DeveRetornarNullQuandoUsuarioESenhaNaoCoincidem() {
        String username = "usuario";
        String password = "senha";

        when(usuarioRepository.findByUsernameAndPassword(username, password)).thenReturn(Optional.empty());

        Usuario usuario = usuarioService.obterUsuarioPorUsernameAndPassword(username, password);

        assertNull(usuario);
    }

    @Test
    public void DeveRetornarNullQuandoUsuarioNaoExiste() {
        String username = "usuario";
        String password = "usuario";

        when(usuarioRepository.findByUsernameAndPassword(username, password)).thenReturn(Optional.empty());

        Usuario usuario = usuarioService.obterUsuarioPorUsernameAndPassword(username, password);

        assertNull(usuario);
    }

    @Test
    public void DeveRetornarUsuarioQuandoIdExiste() {
        int id = 1;
        Usuario usuario = new Usuario();
        usuario.setId(id);

        given(this.usuarioRepository.findById(id)).willReturn(Optional.of(usuario));

        Usuario user = usuarioService.obterUsuarioPorId(id);

        Assertions.assertEquals(usuario, user);
    }

    @Test
    public void DeveRetornarNullQuandoIdNaoExiste() {
        int idNaoExistente = -1;

        given(this.usuarioRepository.findById(idNaoExistente)).willReturn(Optional.empty());

        Usuario usuario = usuarioService.obterUsuarioPorId(idNaoExistente);

        assertNull(usuario);
    }

    @Test
    public void DeveCriarUsuarioQuandoPerfilExiste() throws LojaException {
        String username = "usuario";
        String password = "senha";
        int idPerfil = 1;

        Perfil perfil = new Perfil();
        perfil.setId(idPerfil);

        given(this.perfilRepository.findById(idPerfil)).willReturn(Optional.of(perfil));

        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setPassword(password);
        usuario.getPerfis().add(perfil);

        given(this.usuarioRepository.save(any(Usuario.class))).willReturn(usuario);

        Usuario user = usuarioService.criar(username, password, idPerfil);

        Assertions.assertEquals(usuario, user);
    }

    @Test
    public void DeveJogarUmaExcecaoQuandoPerfilNaoExiste() {
        String username = "usuario";
        String password = "senha";
        int idPerfil = -1;

        given(this.perfilRepository.findById(idPerfil)).willReturn(Optional.empty());

        Assertions.assertThrows(LojaException.class, () -> {
            usuarioService.criar(username, password, idPerfil);
        });
    }

    @Test
    public void DeveRetornarTodosOsUsuarios() {
        Usuario user1 = new Usuario();
        Usuario user2 = new Usuario();
        List<Usuario> usuarios = Arrays.asList(user1, user2);

        given(this.usuarioRepository.findAll()).willReturn(usuarios);

        List<Usuario> actualUsers = usuarioService.obterTodos();

        Assertions.assertEquals(usuarios, actualUsers);
    }

    @Test
    public void DeveRetornarListaVazioQuandoNaoExistemUsuarios() {
        given(this.usuarioRepository.findAll()).willReturn(Collections.emptyList());

        List<Usuario> usuarios = usuarioService.obterTodos();

        Assertions.assertTrue(usuarios.isEmpty());
    }

    @Test
    public void DeveRetornarUsuarioQuandoUsernameExiste() {
        String username = "usuario";
        Usuario usuario = new Usuario();
        usuario.setUsername(username);

        given(this.usuarioRepository.findByUsername(username)).willReturn(Optional.of(usuario));

        Usuario user = usuarioService.obterUsuarioPorUsername(username);

        Assertions.assertEquals(usuario, user);
    }

    @Test
    public void DeveRetornarNullQuandoUsernameNaoExiste() {
        String nonExistentUsername = "usuario";

        given(this.usuarioRepository.findByUsername(nonExistentUsername)).willReturn(Optional.empty());

        Usuario usuario = usuarioService.obterUsuarioPorUsername(nonExistentUsername);

        assertNull(usuario);
    }
}




















