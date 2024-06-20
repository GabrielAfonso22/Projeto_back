package br.com.malldelivery.lojista.controller;

import br.com.malldelivery.lojista.model.DadoBancario;
import br.com.malldelivery.lojista.model.Endereco;
import br.com.malldelivery.lojista.model.Loja;
import br.com.malldelivery.lojista.model.TipoConta;
import br.com.malldelivery.lojista.request.LojistaRequest;
import br.com.malldelivery.lojista.seguranca.authentication.JwtTokenService;
import br.com.malldelivery.lojista.seguranca.authentication.UserAuthenticationFilter;
import br.com.malldelivery.lojista.service.LojaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.Filter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.mockito.BDDMockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.testSecurityContext;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@AutoConfigureMockMvc
@WebMvcTest(controllers = LojistaController.class)
public class LojistaControllerTest {
    @MockBean
    private LojaService lojaService;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Loja loja;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private UserAuthenticationFilter filter;

    @BeforeEach
    public void setup() {
        loja = new Loja();
        loja.setCnpj("54144813000100");
        loja.setBanner("http://xpto.com.br");
        loja.setNome("Lojista do Teste");
        loja.setId(1);

        Endereco endereco = new Endereco();
        endereco.setLogradouro("Rua do teste");
        endereco.setComplemento("Apto do Teste");
        endereco.setBairro("Bairro do Teste");
        endereco.setCidade("Cidade do Teste");
        endereco.setCep("99999-999");
        loja.getEnderecos().add(endereco);

        DadoBancario dadoBancario = new DadoBancario();
        dadoBancario.setTipoConta(TipoConta.CONTA_CORRENTE);
        dadoBancario.setAgencia("9999");
        dadoBancario.setConta("99990-0");
        dadoBancario.setCodigoBanco("341");

        loja.getDadosBancarios().add(dadoBancario);

        this.mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    private LojistaRequest lojistaRequest;
    @BeforeEach
    public void Setup() {
        lojistaRequest = new LojistaRequest();
        lojistaRequest.setCnpj("12.345.679/9001-90");
        lojistaRequest.setCep("12345-678");
        lojistaRequest.setCidade("Cidade Teste");
        lojistaRequest.setBairro("Bairro Teste");
        lojistaRequest.setAgencia("1111");
        lojistaRequest.setNome("Testes Lojas");
        lojistaRequest.setCodigoBanco("3333");
        lojistaRequest.setBanner("http://xpto.com");
        lojistaRequest.setTipoConta("CC");
        lojistaRequest.setTelefone("(23)99999-9999");
        lojistaRequest.setLogradouro("sdfadfasfda");
        lojistaRequest.setEstado("rj");
        lojistaRequest.setComplemento("apt 000");
        lojistaRequest.setEmail("blabal@blabla.com");
        lojistaRequest.setNumMaxProduto(5);
        lojistaRequest.setUrlLoja("https://xptxot.com.br");
    }

    @Test
    @WithMockUser(username = "user1", password = "pwd", roles = "USER")
    public void deveConsultarLojistaPorIdSuccess() throws Exception {
        int id = 1;
        given(this.lojaService.obterLojistaPorId(id)).willReturn(Loja.toResponse(this.loja));

        mvc.perform(MockMvcRequestBuilders
                        .get("/lojista/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(id)))
                .andExpect(jsonPath("$.nome", is(this.loja.getNome())));
    }

    @Test
    @WithMockUser(username = "user1", password = "pwd", roles = "USER")
    public void deveConsultarLojistaPorIdRetornandoNotFound() throws Exception {
        int id = 1;
        given(this.lojaService.obterLojistaPorId(id)).willReturn(null);

        mvc.perform(MockMvcRequestBuilders.get("/lojista/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = "user1", password = "pwd", roles = "USER")
    public void deveConsultarLojistaPorCNPJSuccess() throws Exception {
        int id = 1;
        given(this.lojaService.obterLojistaPorId(id)).willReturn(null);

        mvc.perform(MockMvcRequestBuilders.get("/lojista/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}

