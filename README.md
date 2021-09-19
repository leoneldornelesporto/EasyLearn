<div align="center">
<h1>EasyLearn - Uma plataforma web de ensino livre</h1>
</div>


---
## 1. Resumo

Esta proposta apresenta o EasyLearn: uma plataforma web de ensino livre, com suporte a videoaulas e tutoria. Pretende-se desenvolver uma plataforma web que permita integrar produtores de conteúdo com quem necessita desses conteúdos, com o diferencial de oferecer o serviço de tutoria livre, onde pessoas que dominem o conteúdo possam oferecer seu tempo para atendimento e retirada de dúvidas. A metodologia a ser utilizada prevê cinco etapas: Levantamento do estado da arte; Análise de requisitos; Modelagem do sistema e concepção do design; Escolha das tecnologias e implementação; Teste e validação. Espera-se desenvolver uma plataforma web flexível, que permita integrar todos os atores de forma fácil, sem complicações.

## 2. Modelagem do Sistema

A partir das funcionalidades levantadas na tabela comparativa será modelado  o sistema através dos diagramas da UML, diagrama de casos de uso e o diagrama de classes. A modelagem do design também fará parte dessa etapa, artefatos como, wireframes e direção de arte serão trabalhados. Em relação a modelagem do banco de dados, como pretende-se utilizar ORM (Object-relational Mapping), uma engenharia reversa será realizada para efeitos de produção do diagrama lógico do banco de dados.

### DIAGRAMA DE CASOS DE USO

O diagrama de casos de uso procura, por meio de uma linguagem
simples, possibilitar a compreensão do comportamento externo do sistema por
qualquer pessoa, tentando apresentar o sistema da perspectiva do usuário
(GUEDES, 2011). Ele é conhecido como o diagrama mais geral e informal, mas se
mostra muito útil, visto que, ele aborda de maneira simples como o sistema irá se
portar e o que oferecerá aos seus usuários. Nele podemos obter uma visão geral
das suas reais necessidades. Além disso, ele reconhece e caracteriza os tipos de
usuários do sistema. De maneira geral pode-se dizer que ele dispõe de dois
principais elementos: o primeiro chamado de ator que representa os usuários do
sistema; e o segundo chamado de caso de uso que figura uma funcionalidade que o
sistema apresentará.

<img style="-webkit-user-select: none;margin: auto;cursor: zoom-in;background-color: hsl(0, 0%, 90%);transition: background-color 300ms;" src="https://i.ibb.co/7YZmFZp/Capturars.jpg" width="625" height="577">

### DIAGRAMA DE CLASSES

O diagrama de classes é conhecido como um dos diagramas mais
importantes da UML, pois possibilita a visualização de uma maneira geral das
classes que serão utilizadas no sistema, assim como os atributos e métodos
primordiais, e consequentemente demonstrando o relacionamento de todas as
classes e informações envolvidas.
De acordo com Guedes (2011, p. 101) "apresenta uma visão estática de
como as classes estão organizadas, preocupando-se em como definir a estrutura
lógica das mesmas".

<img style="-webkit-user-select: none;margin: auto;cursor: zoom-in;background-color: hsl(0, 0%, 90%);transition: background-color 300ms;" src="https://i.ibb.co/gS5FKdb/Capturarss.jpg" width="625" height="577">

### 2.1 Arquitetura

O sistema será desenvolvido utilizando a arquitetura REST para comunicação entre o front-end e o back-end. A figura 1 mostra a arquitetura geral do sistema aqui proposto.

<img style="-webkit-user-select: none;margin: auto;cursor: zoom-in;background-color: hsl(0, 0%, 90%);transition: background-color 300ms;" src="https://i.ibb.co/cXyfzHC/unnamed.png" width="625" height="577">


### 2.2 Proposta de implementação

Como foi apresentado na seção anterior, o sistema tem uma arquitetura cliente-servidor, com duas fronteiras de desenvolvimento bem distintas, back-end e front-end. Um detalhe importante na ordem dos acontecimentos da  implementação desse trabalho, é que ela se dará do back-end para o front-end, pois, os end-points REST deverão estar prontos e testados para facilitar o desenvolvimento do front-end.

A seguir outros detalhes da etapa de implementação:

Back-end
Spring Boot Controller: Recebe as requisições do front-end, e as encaminha para os managers. Esta camada não possui regras de negócio.
Spring Boot Manager: Aqui é realizado todo o processamento dos dados. Validações, filtros, tratamento de exceções e todas as regras de negócio são feitas nesta camada da aplicação.
Spring Boot Service: Responsável pela persistência e recuperação dos        dados dos usuários. Nessa camada será utilizado um framework de mapeamento objeto-relacional.
Banco de Dados: banco de dados relacional onde as informações e configurações dos usuários são persistidas e manipuladas.
Front-end

Interface do usuário: As páginas do sistema. É responsável por apresentar a interface ao usuário. Utiliza tecnologias como JSX e CSS.
React Component: É responsável pelo comportamento da interface. Ações de clique, carregamento de dados e manipulação das telas são realizadas nesta camada.
React Service: Pode ser considerado o DAO do front-end. Tem como responsabilidade gerenciar o estado dos dados da aplicação, em outras palavras, envia, recebe, e atualiza os dados através dos end-points do back-end.

## Ferramentas e dependências utilizadas
[![Java Badge](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)](https://www.java.com/en/)
[![Spring Badge](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)](https://start.spring.io/)
[![Postman Badge](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=Postman&logoColor=white)](https://www.postman.com/)
[![Git Badge](https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white)](https://git-scm.com/)
<!-- [<img src="https://static1.smartbear.co/swagger/media/assets/images/swagger_logo.svg" width="90px;"></img>](https://swagger.io/) !-->
<!-- [![Intellij Badge](https://img.shields.io/badge/Eclipse-2C2255?style=for-the-badge&logo=eclipse&logoColor=white)](https://www.eclipse.org/downloads/packages/release/kepler/sr2/eclipse-ide-java-ee-developers) !-->

- **[Spring Initializr](https://start.spring.io/):** gera um projeto de Spring Boot com dependências iniciais de forma rápida. Todas as dependências se encontram no arquivo **[pom.xml](/pom.xml)**.
    * **Projeto Maven com Spring Boot versão 2.5.2 e Java versão 11.**
    * **Spring Data JPA**: persiste dados em SQL com Java Persistence API usando Spring Data e Hibernate.
    * **Validation**: Bean Validation com validador do Hibernate.
    * **Spring Web**: cria aplicações web, incluindo RESTful, usando Spring MVC, utilizando o Apache Tomcat como contêiner integrado padrão.
    * **Spring cache abstraction**: fornece operações relacionadas ao cache, como a capacidade de atualizar o conteúdo do cache, mas não fornece o armazenamento de dados reais.
    * **Spring Security**: autenticação altamente personalizável e estrutura de controle de acesso para aplicações Spring.
    * **Spring Boot Actuator**: suporta endpoints integrados (ou personalizados) que permitem monitorar e gerenciar a aplicação - como a integridade, métricas, sessões, etc.
    * **SpringFox**: documentação de API JSON automatizada para APIs construídas com Spring
    * **Spring Boot DevTools**: fornece reinicializações rápidas de aplicativos, LiveReload e configurações para uma experiência de desenvolvimento aprimorada.
    * **PostgreSQL**: fornece um banco de dados que suporta acesso JDBC API e R2DBC, com um aplicativo de console baseado em navegador.
    * As configurações do DataSource, JPA e PostgreSQL se encontram no arquivo **[application.properties](/src/main/resources/application.properties)**.
    * Os registros do banco de dados utilizados como teste se encontram no arquivo **[data.sql](/src/main/resources/data.sql)**.
- **[Intellij ](https://www.jetbrains.com/pt-br/idea/)**: ferramentas para desenvolvedores Java criando aplicativos Java EE e Web, incluindo Java IDE, ferramentas para Java EE, JPA, JSF, Mylyn, EGit e outros.
- **[Postman](https://www.postman.com/)**: plataforma de colaboração para desenvolvimento de API, utilizado para requisições do tipo GET/POST/PUT/DELETE.
- **[Git](https://git-scm.com/)**: sistema de controle de versão distribuído gratuito e de código aberto.
<!-- - **[Swagger](https://swagger.io/)**: simplifica o desenvolvimento de API, ajudando a projetar e documentar APIs. A documentação criada para esse projeto se encontra em **[swagger-openapi.yaml](/swagger-openapi.yaml)**.-->