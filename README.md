# Projeto JavaFX com integração do banco de dados MySQL

| :placard: Vitrine.Dev |  @Beforg   |
| -------------  | --- |
| :sparkles: Nome        | **Bank**
| :label: Tecnologias | java, css, javaFX, MySql

![](https://github.com/Beforg/assets/blob/main/bank_images/main.png#vitrinedev)

Este é um projeto de aplicação bancária desenvolvido em Java utilizando a API JDBC juntamente com o framework JavaFX para a interface do usuário, com o banco de dados MySQL.
Projeto simples com objetivo de colocar em prática os comandos de SQL que foram passados na formação de [Banco de Dados da Alura](https://cursos.alura.com.br/formacao-banco-dados-java)

## Funcionalidades

O projeto possui as seguintes funcionalidades:

- Cadastro de novos usuários
- Atualização de informações da conta
- Visualização do histórico de transferências
- Realização de transferências entre contas
- Realização de depósito na conta

## Estrutura do projeto

O projeto segue a estrutura padrão do Maven para projetos Java:

- `src/main/java`: Contém o código fonte do projeto.
- `src/main/resources`: Contém recursos como arquivos css, imagens e FXML para a interface do usuário.
- `pom.xml`: O arquivo de configuração do Maven, que contém informações sobre o projeto e as dependências.

## Classes principais

- `Classes DAO`: Responsável pelas operações/interações com o banco de dados
- `ApplicationController`: Controlador principal da aplicação, responsável por gerenciar a interface do usuário.
- `Classes do package Modelo`: Classes de modelos, formatação e inicialização de alguns campos do projeto.
- `Classe ViewControl`: Classe responsável pela troca de telas da aplicação, com lógica para evitar a abertura de várias janelas ao mesmo tempo.

## Detalhes do projeto:

### Login

O projeto conta com duas tabelas no banco de dados MySQL, a primeira `cadastros`, que contém os dados do usuário e a segunda `historico_transferencia`, que guarda os dados das transferências entre os usuários.
A aplicação começa com uma tela de Login controlada pela classe `LoginController` e acessa as informações do banco de dados através da classe `LoginDAO`:

![](https://github.com/Beforg/assets/blob/main/login)

Os campos de texto possuem formatação para inserir CPF, Valor monetário, data de nascimento e formatação para o número da conta, contidos na classe `TextFieldFormatter`:

![](https://github.com/Beforg/assets/blob/main/bank_images/login_formatacao.png)

### Cadastro

Na parte de cadastro de novos usuários, controlada pela classe `CadastroController`, grava os dados inseridos através da classe `CadastroDAO`, a classe `Formatter` cuida da geração do número da conta do usuário no momento em que o cadastro é efetuado. :

![](https://github.com/Beforg/assets/blob/main/bank_images/cadastro.png)

### Tela principal: Início

A tela principal da aplicação, controlada pela classe `ApplicationController` apresenta os botões para navegar entre o início e a conta do usuário, além de abrir as janelas para o Depósito e Transferência entre as contas.
Apresenta também, no início, o <strong>nome, saldo e o histórico de transferências</strong> do usuário, com a integração de um botão de atualizar quando o usuário depositar ou realizar alguma transferência, métodos contidos na classe `UpdateDAO`. a classe `InfoDAO` recupera e inicializa
os dados do usuário assim que o Login é efetuado, mostrando os dados de Saldo e Informações da conta do usuário. :

![](https://github.com/Beforg/assets/blob/main/bank_images/main.png)

### Tela principal: Conta

A aba <strong>Conta</strong> apresenta os dados da conta do usuário, além das opções para editar as informações ou apagar a conta, métodos contidos também na classe `UpdateDAO`

![](https://github.com/Beforg/assets/blob/main/bank_images/conta_tela.png)

![](https://github.com/Beforg/assets/blob/main/bank_images/altera_infos.png)

###  Depósito e Transferência

O botão <strong>Depósito e Transferência</strong> na tela principal abrem um nova janela controladas pelas classes `DepositarController` e `TransferirController` respectivamente, a operação de ambas é de responsabilidade da classe `OperacaoDAO`:

![](https://github.com/Beforg/assets/blob/main/bank_images/deposit.png)

![](https://github.com/Beforg/assets/blob/main/bank_images/transf.png)

### Histórico de transferências:

Na tela de início da aplicação, há a tabela de histórico de transferências, que mostra as informações das transferências, como o ID, data e hora, origem, destino e valor da transferência. A tabela é configurada pela classe `HistoricoTransferencia`:

![](https://github.com/Beforg/assets/blob/main/bank_images/tabela_transf.png)

## Como executar

Para executar este projeto, você precisa ter o Java e o Maven instalados em seu sistema. Siga estas etapas:

1. Clone o repositório para o seu sistema local.
2. Navegue até o diretório do projeto.
3. Execute o comando `mvn clean install` para compilar o projeto e instalar as dependências.
4. Execute o comando `java -jar target/nome-do-seu-projeto.jar` para iniciar a aplicação.

## Considerações finais:

Projeto simples feito com a lógica que achei adequada no momento, há muitos aspectos a melhorar mas a intenção do projeto é fazer uma aplicação integrada com um banco de dados para colocar a teoria aprendida em prática, qualquer sugestão,recomendação ou feedback
serão bem vindos.

bruno.capsqn@gmail.com

## Licença

Este projeto está licenciado sob a licença MIT.
