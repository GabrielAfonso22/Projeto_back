# Levantamento de Requisitos

# Documentação da API de Cadastro de Lojistas

Esta documentação descreve os requisitos e funcionalidades da API de Cadastro de Lojistas para uma plataforma de e-commerce, utilizando as tecnologias Lombok, Spring Boot, MySQL e Java 21.

## Tecnologias Utilizadas

- **Lombok:** Biblioteca Java que ajuda a reduzir a quantidade de código boilerplate.
- **Spring Boot:** Framework Java para criar aplicativos baseados em Spring de forma rápida e fácil.
- **MySQL:** Sistema de gerenciamento de banco de dados relacional.
- **Java 21:** Versão mais recente do Java na época do desenvolvimento.

## Requisitos Funcionais:

Registro de Lojista: Permitir que novos lojistas se cadastrem na plataforma fornecendo informações como nome da loja, CNPJ, endereço, informações de contato, etc.

Autenticação: Implementar um sistema de autenticação seguro para que os lojistas registrados possam fazer login na plataforma.

Gestão de Produtos: Permitir que os lojistas adicionem, atualizem, visualizem e removam produtos de seu catálogo de vendas.

Gestão de Pedidos: Fornecer funcionalidades para os lojistas visualizarem e gerenciarem os pedidos recebidos, incluindo status do pedido, informações do cliente, etc.

Integração de Pagamento: Integrar um sistema de pagamento para processar transações de compra dos clientes.

Relatórios de Vendas: Oferecer aos lojistas a capacidade de gerar relatórios de vendas para acompanhar o desempenho de suas lojas.

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
| **id**                  | integer        | -           |
| **nome_completo**       | string         | SIM         |
| **nome**                | string         | NAO         |
| **sobrenome**           | string         | NAO         |
| **email**               | string         | SIM         |
| **telefone**            | string         | SIM         |
| **endereco.rua**        | string         | SIM         |
| **endereco.num**        | string         | SIM         |
| **endereco.bairro**     | string         | SIM         |
| **endereco.cidade**     | string         | SIM         |
| **endereco.rua**        | string         | SIM         |
| **endereco.estado**     | string         | SIM         |
| **endereco.pais**       | string         | SIM         |
| **facebook_url**        | string         | NAO         |
| **instagram_url**       | string         | NAO         |
| **twitter_url**         | string         | NAO         |
| **loja_url**            | string         | NAO         |
| **num_prod_max**        | integer        | NAO         |
| **exibe_prod_ad**       | boolean        | NAO         |
| **nome_banco**          | string         | NAO         |
| **num_agencia**         | string         | NAO         |
| **num_conta**           | string         | NAO         |


### Endpoints
| Função                           | Método  | Endpoint                                       |
| -------------------------------- | ------- | ---------------------------------------------- |
| **Requisição de Cadastro**       | POST    | `lojista/cadastro`                             |
| **Listar Requisições**           | GET     | `admin/requisicoes-cadastro`                   |
| **Aprovar Requisição**           | POST    | `admin/cadastro/{id}/aprovar`                  |
| **Aprovar Requisição**           | POST    | `admin/cadastro/{id}/negar`                    |
| **Listar Lojistas**              | GET     | `admin/listar-lojistas`                        |
  

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

