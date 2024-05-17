package br.com.malldelivery.lojista.model;

import br.com.malldelivery.lojista.exception.LojaException;
import br.com.malldelivery.lojista.request.LojistaRequest;
import br.com.malldelivery.lojista.response.LojistaResponse;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "loja")
public class Loja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String nomeLojista;

    @Column
    private String sobrenomeLojista;

    @Column
    private String nomeLoja;

    @Column
    private String cnpj;

    @Column
    private String email;

    @Column
    private LocalDateTime dtGeracao;

    @Column
    private LocalDateTime dtStatus;

    @Column
    private String telefone;

    @Column
    private String facebookUrl;

    @Column
    private String instagramUrl;

    @Column
    private String twitterUrl;

    @Column
    private String lojaUrl;

    @Column
    private int NumMaxProduto;

    @Column
    private Boolean exibeProdAdc;

    @Column
    private Boolean ativo = false;

    @Column
    private LocalDateTime dtAtivacao;

    @OneToMany
    @JoinColumn(name = "id_loja", referencedColumnName = "id")
    private List<DadoBancario> dadosBancarios = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "id_loja", referencedColumnName = "id")
    private List<Endereco> enderecos = new ArrayList<>();

    public static Loja fromRequest(LojistaRequest request) throws Exception {
        Loja loja = new Loja();

        loja.setNomeLoja(request.getNome());
        loja.setTelefone(request.getTelefone());
        loja.setLojaUrl(request.getUrlLoja());
        loja.setNumMaxProduto(request.getNumMaxProduto());
        loja.setCnpj(request.getCnpj());

        DadoBancario dadoBancario = getDadoBancario(request);
        loja.getDadosBancarios().add(dadoBancario);

        Endereco endereco = new Endereco();
        endereco.setCep(request.getCep());
        endereco.setBairro(request.getBairro());
        endereco.setCidade(request.getCidade());
        endereco.setEstado(request.getEstado());
        endereco.setComplemento(request.getComplemento());
        endereco.setLogradouro(request.getLogradouro());

        loja.getEnderecos().add(endereco);

        return loja;
    }

    private static DadoBancario getDadoBancario(LojistaRequest request) throws LojaException {
        DadoBancario dadoBancario = new DadoBancario();

        switch (request.getTipoConta()) {
            case "CC" -> dadoBancario.setTipoConta(TipoConta.CONTA_CORRENTE);
            case "CP" -> dadoBancario.setTipoConta(TipoConta.CONTA_POUPANCA);
            case "CI" -> dadoBancario.setTipoConta(TipoConta.CONTA_INVESTIMENTO);
            default -> throw new LojaException("tipoConta", "Tipo de conta invalido, valores validos: CC, CI, CP");
        }

        dadoBancario.setConta(request.getConta());
        dadoBancario.setAgencia(request.getAgencia());
        dadoBancario.setCodigoBanco(request.getCodigoBanco());
        return dadoBancario;
    }

    public static Loja fromRequest(Loja loja, LojistaRequest request) throws LojaException {

        loja.setNomeLoja(request.getNome());
        loja.setTelefone(request.getTelefone());
        loja.setNomeLojista(request.getBanner());
        loja.setLojaUrl(request.getUrlLoja());
        loja.setNumMaxProduto(request.getNumMaxProduto());
        loja.setCnpj(request.getCnpj());

        DadoBancario dadoBancario = loja.dadosBancarios.getFirst();

        switch (request.getTipoConta()) {
            case "CC" -> dadoBancario.setTipoConta(TipoConta.CONTA_CORRENTE);
            case "CP" -> dadoBancario.setTipoConta(TipoConta.CONTA_POUPANCA);
            case "CI" -> dadoBancario.setTipoConta(TipoConta.CONTA_INVESTIMENTO);
            default -> throw new LojaException("tipoConta", "Tipo de conta invalido, valores validos: CC, CI, CP");
        }

        dadoBancario.setConta(request.getConta());
        dadoBancario.setAgencia(request.getAgencia());
        dadoBancario.setCodigoBanco(request.getCodigoBanco());

        Endereco endereco = loja.getEnderecos().getFirst();

        endereco.setCep(request.getCep());
        endereco.setBairro(request.getBairro());
        endereco.setCidade(request.getCidade());
        endereco.setEstado(request.getEstado());
        endereco.setComplemento(request.getComplemento());
        endereco.setLogradouro(request.getLogradouro());

        return loja;

    }
    public static LojistaResponse toResponse(Loja loja) {
        LojistaResponse response = new LojistaResponse();

        response.setNome(loja.getNomeLoja());
        response.setTelefone(loja.getTelefone());
        response.setUrlLoja(loja.getLojaUrl());
        response.setNumMaxProduto(loja.getNumMaxProduto());
        response.setId(loja.getId());
        response.setDtCadastro(loja.getDtGeracao());
        response.setEnabled(loja.getAtivo());
        response.setDtAtivacao(loja.getDtAtivacao());

        if (!loja.getDadosBancarios().isEmpty()) {
            response.setConta(loja.getDadosBancarios().getFirst().getConta());
            response.setAgencia(loja.getDadosBancarios().getFirst().getConta());
            response.setCodigoBanco(loja.getDadosBancarios().getFirst().getConta());
            response.setTipoConta(loja.getDadosBancarios().getFirst().getTipoConta().toString());
        }

        if (!loja.getEnderecos().isEmpty()) {
            response.setCep(loja.getEnderecos().getFirst().getCep());
            response.setBairro(loja.getEnderecos().getFirst().getBairro());
            response.setCidade(loja.getEnderecos().getFirst().getCidade());
            response.setEstado(loja.getEnderecos().getFirst().getEstado());
            response.setComplemento(loja.getEnderecos().getFirst().getComplemento());
            response.setLogradouro(loja.getEnderecos().getFirst().getLogradouro());
        }

        return response;

    }
}
