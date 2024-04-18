# Levantamento de Requisitos

# Documentação da API de Cadastro de Lojistas

Esta documentação descreve os requisitos e funcionalidades da API de Cadastro de Lojistas para uma plataforma de e-commerce, utilizando as tecnologias Lombok, Spring Boot, MySQL e Java 21.

## Tecnologias Utilizadas

- **Lombok:** Biblioteca Java que ajuda a reduzir a quantidade de código boilerplate.
- **Spring Boot:** Framework Java para criar aplicativos baseados em Spring de forma rápida e fácil.
- **MySQL:** Sistema de gerenciamento de banco de dados relacional.
- **Java 21:** Versão mais recente do Java na época do desenvolvimento.

## Requisitos Funcionais:

Registro de Lojista:
Os lojistas devem ser capazes de se registrar na plataforma fornecendo informações básicas, como nome da loja, CNPJ, endereço físico e de e-mail, telefone de contato e uma senha segura.
O sistema deve validar o CNPJ fornecido para garantir sua autenticidade.
Após o registro bem-sucedido, os lojistas devem receber um e-mail de confirmação.

Autenticação:
Os lojistas registrados devem poder fazer login na plataforma usando seu CNPJ/e-mail e senha.
A autenticação deve ser protegida por meio de técnicas como hashing de senha e tokens de autenticação JWT (JSON Web Tokens).
O sistema deve fornecer um mecanismo para redefinição de senha caso os lojistas a esqueçam.

Gestão de Produtos:
Os lojistas devem poder adicionar novos produtos especificando detalhes como nome, descrição, preço, quantidade em estoque, categoria, imagens, etc.
Eles devem ser capazes de atualizar e excluir produtos existentes.
A API deve suportar operações de busca para que os lojistas possam encontrar facilmente produtos em seu catálogo.

Gestão de Pedidos:
Os lojistas devem ter acesso aos detalhes dos pedidos recebidos, incluindo informações do cliente, produtos comprados, status do pedido, etc.
Eles devem poder atualizar o status do pedido conforme o progresso (por exemplo, "recebido", "em processamento", "enviado", "entregue", etc.).
A API deve permitir a geração de faturas e recibos para os pedidos.

Integração de Pagamento:
Os lojistas devem ser capazes de integrar métodos de pagamento populares à sua loja, como cartões de crédito, PayPal, boleto bancário, etc.
A API deve fornecer endpoints seguros para processar transações de pagamento de forma transparente e segura.
O sistema deve registrar e notificar os lojistas sobre o status das transações (por exemplo, "pago com sucesso", "pendente", "falha na transação", etc.).

Relatórios de Vendas:
Os lojistas devem poder gerar relatórios detalhados sobre suas vendas, incluindo métricas como receita total, número de pedidos, produtos mais vendidos, etc.
A API deve suportar filtros e opções de personalização para que os lojistas possam analisar suas vendas de acordo com suas necessidades específicas.
Os relatórios devem ser exportáveis em formatos comuns, como CSV ou PDF, para facilitar a análise e o compartilhamento.

## Requisitos Não Funcionais:

Segurança: Garantir a segurança dos dados dos lojistas e dos clientes, implementando medidas como criptografia de dados, autenticação robusta e prevenção contra ataques de segurança.

Desempenho: Assegurar que a API seja eficiente e responsiva, mesmo com um grande volume de acessos e transações simultâneas.

Escalabilidade: Projetar a API para ser escalável, permitindo que ela cresça conforme a demanda, tanto em termos de número de lojistas quanto de volume de transações.

Disponibilidade: Garantir que a API esteja sempre disponível para os lojistas e clientes, minimizando o tempo de inatividade e implementando medidas de recuperação em caso de falhas.

Documentação: Fornecer uma documentação clara e abrangente da API, incluindo exemplos de uso, endpoints disponíveis, parâmetros necessários e possíveis códigos de erro.

Compatibilidade: Certificar-se de que a API seja compatível com as tecnologias especificadas (Lombok, Spring Boot, MySQL e Java 21) e que possa ser facilmente integrada com outras ferramentas e sistemas.

---

### Campos

#### Lojista

| Campo                   | Tipo           | Obrigatório |
| ----------------------- | -------------- | ----------- |
| **nome_loja**           | string         | SIM         |
| **nome_lojista**        | string         | NAO         |
| **sobrenome_lojista**   | string         | NAO         |
| **cnpj**                | string         | SIM         |                
| **email**               | string         | SIM         |
| **telefone**            | string         | SIM         |
| **endereco.logradouro** | string         | SIM         |
| **endereco.numero**     | string         | SIM         |
| **endereco.bairro**     | string         | SIM         |
| **endereco.cidade**     | string         | SIM         |
| **endereco.uf**         | string         | SIM         |
| **facebook_url**        | string         | NAO         |
| **instagram_url**       | string         | NAO         |
| **twitter_url**         | string         | NAO         |
| **loja_url**            | string         | NAO         |
| **num_prod_max**        | integer        | NAO         |
| **exibe_prod_ad**       | boolean        | NAO         |
| **nome_banco**          | string         | NAO         |
| **num_agencia**         | string         | NAO         |
| **num_conta**           | string         | NAO         |
| **cod_banco**           | string         | NAO         |


### Endpoints
| Função                           | Método  | Endpoint                                       |
| -------------------------------- | ------- | ---------------------------------------------- |
| **Requisição de Cadastro**       | POST    | `lojista/cadastro`                             |
| **Listar Requisições**           | GET     | `admin/requisicoes-cadastro`                   |
| **Aprovar Requisição**           | POST    | `admin/cadastro/{id}/aprovar`                  |
| **Aprovar Requisição**           | POST    | `admin/cadastro/{id}/negar`                    |
| **Listar Lojistas**              | GET     | `admin/listar-lojistas`                        |


## Modelagem BD

![MER](imagens/mer.png)
  

## Como Usar

1. Clone este repositório.
2. Certifique-se de ter todas as dependências e tecnologias instaladas e configuradas corretamente.
3. Execute a aplicação.
4. Utilize as rotas da API conforme descrito na documentação para interagir com o sistema de cadastro de lojistas.

## Colaboradores
Caio Monteiro

Gabriel Afonso

Marcus Vinicius 

João Gois

Felipe Seda

