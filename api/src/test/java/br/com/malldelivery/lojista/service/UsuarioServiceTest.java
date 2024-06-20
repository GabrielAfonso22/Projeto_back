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

import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
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
    public void DeveCriarUmUsuarioFailPerfilNaoEncontrado() {
        given(this.perfilRepository.existsById(1))
            .willReturn(false);

        Assertions.assertThrows(Exception.class, () -> {
            this.usuarioService.criar(request.getUsername(), request.getPassword(), 1);
        });
    }

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
    public void DeveJogarUmaExcecaoPerfilNaoEncontrado() {
        given(this.perfilRepository.existsById(-1))
            .willReturn(false);

        try {
            this.usuarioService.criar(request.getUsername(), request.getPassword(), -1);
        } catch (LojaException e) {
            Assertions.assertEquals("Perfil não encontrado", e.getMessage());
        }
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
}














