# Cadastro de Clientes

Foi crido uma API REST que será usada para o controle do registro de Cliente.

Foi Criado um sistema web que comporta toda a funcionalidade pedida para o controle do registro da Cliente, Usúarios e autenticação.

## REST API

 * CadastroClientesRestController
 
  * Cadastro de Cliente    - url : http://localhost:8080/CadastroClientes/Cadastro
  * Alteração de Cliente   - url : http://localhost:8080/CadastroClientes/{id}
  * Remoção de Cliente     - url : http://localhost:8080/CadastroClientes/{id}
  * Consulta de Clientes   - url : http://localhost:8080/CadastroClientes/ConsultaTodos
  * Consulta de Clientes   - url : http://localhost:8080/CadastroClientes/ConsultaNome/{nome}
  * Consulta de Clientes   - url : http://localhost:8080/CadastroClientes/ConsultaCpf/{cpf}
  * Consulta de Clientes   - url : http://localhost:8080/CadastroClientes/ConsultaId/{id}
  
  cadastro, alteração, remoção e consulta de Clientes 

## Arquitetura do Sistema
 
 *** Para a configuração da aplicação foi utilizado o Apache Maven, para isso foram adicionadas dependências do mesmo:

  * Spring Boot 2.3.5.RELEASE;
  * Spring MVC - que adiciona as dependências do Spring MVC;
  * Spring Security - para o desenvolvimento da segurança e login;
  * thymeleaf - para o desenvolvimento da visão;
  * data-jpa - que adiciona as dependências do Spring Data;
  * log4j - que adiciona as dependências do log;
  * test - que adiciona as dependências do test;
  * com.h2database, que é o banco de dados que será utilizado nessa aplicação.
  
##Executar o Sistema Linha de comando

  ### Passo 1

  #### Abra CMD ou Power Shell
  
  #### Ir para o diretorio do projeto
  
  #### Execute : mvn spring-boot:run ( Sobe a aplicação na porta 8080 : http://localhost:8080/  )
 
  #### Abrir url http://localhost:8080/ e navegar e testar usando o arquivo de Evidencias.doc
  
  #### Abrir url http://localhost:8080/h2 se quiser ver o banco ( jdbc:h2:mem:testedb )
  
  ### Passo 2

  #### Abra um segundo CMD ou Power Shell
	
  #### Ir para o diretorio do projeto
  
  #### Execute : mvn test 
  
  ##### Vai executar a Classe de Test CadastroClientesRestControllerTest que testa a API CadastroClientesRestController
  
## Executar o Sistema IDE

  ### Abra idea da sua escolha - Foi utilizada a IDE Spring Tools 4
  
  ### Importa o projeto
  
  ### Executa Spring boot App
  
  #### Abrir url http://localhost:8080/ e navegar e testar usando o arquivo de Evidencias.doc
  
  #### Abrir url http://localhost:8080/h2 se quiser ver o banco ( jdbc:h2:mem:testedb )
  
  ### Executa a Classe de Test CadastroClientesRestControllerTest que testa a API CadastroClientesRestController
  
  
##Teste Unitário

  ####################### testCadastro #######################
  cliente Criado com sucesso!
  
  ####################### testConsultaId #######################
  Nome : cliente 1
  
  ####################### testConsultaNome #######################
  Nome : cliente 3

  ####################### testConsultaCpf #######################
  Nome : cliente 3 

  ####################### testAlteracao #######################
  Atualização cliente com sucesso!  
  
  ####################### testConsultaTodos #######################
  Nome : cliente 1
  Nome : cliente 1-2
  Nome : cliente 3
  
  ####################### testRemocao #######################
  cliente deletado com sucesso!  
  

