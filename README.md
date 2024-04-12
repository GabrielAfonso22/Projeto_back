# Levantamento de Requisitos

# Documentação da API de Cadastro de Lojistas

Esta documentação descreve os requisitos e funcionalidades da API de Cadastro de Lojistas para uma plataforma de e-commerce, utilizando as tecnologias Lombok, Spring Boot, MySQL e Java 21.

## Requisitos Funcionais

### R1  -  Cadastro de Lojista

1. **Cadastro do Lojista:**
   - Permitir o cadastro de lojistas no e-commerce.
   - Campos obrigatórios: nome completo, e-mail, telefone e endereço.

2. **Validação do CNPJ:**
   - Validar o CNPJ fornecido pelo lojista.
   - CNPJ deve ser único para cada lojista cadastrado.

3. **Informações Pessoais:**
   - O lojista deve fornecer seu nome completo.
   - Validar o formato correto do e-mail.

4. **Redes Sociais:**
   - Permitir que o lojista indique sua presença em redes sociais e suas URLs.

5. **Endereço:**
   - O lojista deve informar seu endereço completo.

6. **Imagens Representativas:**
   - Permitir upload de imagens representativas para a loja (banner e imagem de perfil).

7. **Configurações da Loja:**
   - Permitir que o lojista defina o URL da loja, número máximo de produtos exibidos por página e se deseja mostrar uma aba de produtos adicionais.

8. **Informações Bancárias:**
   - O lojista deve fornecer informações bancárias para pagamentos.

9. **Informações de Cadastro:**
   - Registrar a data de cadastro do novo lojista.
   - O lojista deve ter uma aprovação administrativa.

### R2 - Aprovação Administrativa do Cadastro do Lojista

1. **Notificação Administrativa:**
   - Notificar os administradores sobre um novo cadastro de lojista pendente de aprovação.

2. **Interface Administrativa:**
   - Administradores devem ter acesso a uma interface para revisar os detalhes do cadastro.

3. **Revisão Administrativa:**
   - Verificar se os campos obrigatórios foram preenchidos corretamente e se o CNPJ é válido.

4. **Aprovação ou Rejeição:**
   - Administradores podem aprovar ou rejeitar o cadastro com base na conformidade com as políticas do e-commerce.

5. **Ativação da Loja:**
   - Se aprovado, ativar a loja virtual e notificar o lojista por e-mail.
   - Se rejeitado, informar o lojista sobre o motivo da rejeição e fornecer orientações para correção.

6. **Status de Aprovação:**
   - Registrar o status de aprovação (aprovado/rejeitado) para fins de auditoria.

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

## Como Usar

1. Clone este repositório.
2. Certifique-se de ter todas as dependências e tecnologias instaladas e configuradas corretamente.
3. Execute a aplicação.
4. Utilize as rotas da API conforme descrito na documentação para interagir com o sistema de cadastro de lojistas.

## Colaboradores



