package br.com.malldelivery.lojista.service;

import br.com.malldelivery.lojista.exception.LojaException;
import br.com.malldelivery.lojista.model.Loja;
import br.com.malldelivery.lojista.repository.DadoBancarioRepository;
import br.com.malldelivery.lojista.repository.EnderecoRepository;
import br.com.malldelivery.lojista.repository.LojaRepository;
import br.com.malldelivery.lojista.request.LojistaAtivacaoRequest;
import br.com.malldelivery.lojista.request.LojistaRequest;
import br.com.malldelivery.lojista.response.LojistaResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static java.util.Optional.empty;
import static org.mockito.BDDMockito.*;
@SpringBootTest
public class LojaServiceTest {

    @MockBean
    private LojaRepository lojaRepository;

    @MockBean
    private DadoBancarioRepository dadoBancarioRepository;

    @MockBean
    private EnderecoRepository enderecoRepository;

    @Autowired
    private LojaService service;

    private LojistaRequest request;

    @BeforeEach
    public void Setup() {
        request = new LojistaRequest();
        request.setCnpj("82427325000110");
        request.setCep("20411-111");
        request.setCidade("Rio de janeiro");
        request.setBanner("http://xpto.com");
        request.setBairro("Bairro do Teste");
        request.setAgencia("9999");
        request.setCodigoBanco("341");
        request.setConta("999999");
        request.setTipoConta("CC");
        request.setNome("Lojista do Teste");
        request.setLogradouro("Rua do teste, 999");
        request.setComplemento("Apto do Teste");
    }

    @Test
    public void DeveCriarUmaLojaComSucesso() throws Exception {
        //AAA (ARRANGE, ACT, ASSERT)
        given(this.lojaRepository.findByCnpj(request.getCnpj()))
                .willReturn(Optional.empty());

        LojistaResponse response = this.service.criarLoja(request);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(response.getNome(), request.getNome());
        Assertions.assertFalse(response.getEnabled());
        Assertions.assertNotNull(response.getDtCadastro());

    }

    @Test
    public void NaoDeveCriarUmaLojaQuandoExistirCNPJCadastrado() throws Exception {
        //AAA (ARRANGE, ACT, ASSERT)
        given(this.lojaRepository.findByCnpj(request.getCnpj()))
                .willReturn(Optional.of(new Loja()));

        Assertions.assertThrowsExactly(LojaException.class, () -> {
            LojistaResponse response = this.service.criarLoja(this.request);
        });
    }

    @Test
    public void DeveObterLojaPorIdComSucesso() {
        int id = 1;
        given(this.lojaRepository.findById(id))
             .willReturn(Optional.of(new Loja()));
        LojistaResponse response = this.service.obterLojistaPorId(id);
        Assertions.assertNotNull(response);
    }

    @Test
    public void NaoDeveObterLojaPorIdQuandoNaoEncontrarNaBase() {
        int id = 1;
        given(this.lojaRepository.findById(id))
                .willReturn(Optional.empty());
        LojistaResponse response = this.service.obterLojistaPorId(id);
        Assertions.assertNull(response);
    }

    @Test
    public void DeveAtivarLojistaComSucesso() throws Exception {
        int id = 1;
        given(this.lojaRepository.findById(id))
                .willReturn(Optional.of(new Loja()));

        LojistaAtivacaoRequest request = new LojistaAtivacaoRequest();
        request.setEnabled(true);
        request.setUserNameAtivacao("Dummy User");

        LojistaResponse response = this.service.ativarLojista(id, request);

        Assertions.assertNotNull(response);
        Assertions.assertTrue(response.getEnabled());
        Assertions.assertSame(response.getUserNameAtivacao(), "Dummy User");
        Assertions.assertNotNull(response.getDtAtivacao());

    }

    @Test
    public void NaoDeveAtivarLojistaCasoNaoEncontreNaBase() throws Exception{
        int id = 1;
        given(this.lojaRepository.findById(id))
                .willReturn(Optional.empty());

        Assertions.assertThrowsExactly(LojaException.class, () -> {
            LojistaAtivacaoRequest request = new LojistaAtivacaoRequest();
            request.setEnabled(true);
            request.setUserNameAtivacao("Dummy User");

            LojistaResponse response = this.service.ativarLojista(id, request);
        });

    }

    @Test
    public void DeveRetornarLojistaQuandoCNPJExiste() {
        String cnpj = "82427325000110";
        Loja loja = new Loja();
        loja.setCnpj(cnpj);

        given(this.lojaRepository.findByCnpj(cnpj)).willReturn(Optional.of(loja));

        LojistaResponse actualLojista = service.obterLojistaPorCnpj(cnpj);

        Assertions.assertEquals(Loja.toResponse(loja), actualLojista);
    }

    @Test
    public void DeveRetornarNullQuandoCNPJNaoExiste() {
        String nonExistentCnpj = "99999999999999";

        given(this.lojaRepository.findByCnpj(nonExistentCnpj)).willReturn(Optional.empty());

        LojistaResponse actualLojista = service.obterLojistaPorCnpj(nonExistentCnpj);

        Assertions.assertNull(actualLojista);
    }

//    @Test
//    public void DeveAlterarDadosComSucesso() throws Exception {
//        int id = 1;
//        LojistaRequest request = new LojistaRequest();
//        request.setCnpj("82427325000110");
//        request.setCep("20411-111");
//        request.setCidade("Rio de janeiro");
//        request.setBanner("http://xpto.com");
//        request.setBairro("Bairro do Teste");
//        request.setAgencia("9999");
//        request.setCodigoBanco("341");
//        request.setConta("999999");
//        request.setTipoConta("CC");
//        request.setNome("Lojista do Teste");
//        request.setLogradouro("Rua do teste, 999");
//        request.setComplemento("Apto do Teste");
//
//        Loja existingLoja = new Loja();
//        existingLoja.setId(id);
//
//        given(this.lojaRepository.findById(id)).willReturn(Optional.of(existingLoja));
//
//        LojistaResponse expectedResponse = new LojistaResponse();
//        expectedResponse.setNome(request.getNome());
//
//        LojistaResponse actualResponse = service.atualizarDadosLojista(id, request);
//
//        Assertions.assertEquals(expectedResponse, actualResponse);
//    }

    @Test
    public void DeveJogarUmaExcecaoQuandoLojaNaoExiste() throws Exception {
        int nonExistentId = -1;
        LojistaRequest request = new LojistaRequest();

        given(this.lojaRepository.findById(nonExistentId)).willReturn(Optional.empty());

        Assertions.assertThrows(LojaException.class, () -> {
            service.atualizarDadosLojista(nonExistentId, request);
        });
    }
}
