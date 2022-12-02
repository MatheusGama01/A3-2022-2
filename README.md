 
 # 📚 Notepad Magator 


# 🛠️ Projeto

O objetivo do projeto é que você tenha uma ferramenta que vá te auxiliar nas suas atividades do dia-a-dia, nela você poderá não só adicionar tarefas à fazer, como também marcar como feita as tarefas que já foram concluídas, além de visualizar todas as suas tarefas, editá-las e também poder excluí-las.


# Tecnologias Usadas

* Java v11.0.2
* Junit v5.3.0
* Mockito v2.23.4
* MySQL v8.0.31
* MySQL Workbench v8.0.31

# ⚠️ Pré-requisitos

* Instalar o jdk 11.
```bash
   https://www.oracle.com/br/java/technologies/javase/jdk11-archive-downloads.html
```

* Instalar o MySQL workbench 8.0 CE.
```bash
   https://dev.mysql.com/downloads/workbench/
```


# ⚙️ Instalação


###  Instale o  Mockito Core.

   No arquivo pom.xml adicione a dependência:

```bash
<dependency>
         <groupId>org.mockito</groupId>
         <artifactId>mockito-core</artifactId>
         <version>2.23.4</version>
         <scope>test</scope>
</dependency>
```
Para mais informações:
 https://mvnrepository.com/artifact/org.mockito/mockito-core 


### Instale o  Junit Jupiter API.

   No arquivo pom.xml adicione a dependência:

```bash
<dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-api</artifactId>
        <version>5.3.0</version>
        <scope>test</scope>
</dependency>
```
Para mais informações:
 https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api

 
### Instale o  Junit Jupiter Params.

   No arquivo pom.xml adicione a dependência:

```bash
<dependency>
         <groupId>org.junit.jupiter</groupId>
         <artifactId>junit-jupiter-params</artifactId>
         <version>5.3.0</version>
         <scope>test</scope>
</dependency>
```
Para mais informações:
https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-params


### Instale o  Junit Platform Laucher.
 
   No arquivo pom.xml adicione a dependência:

```bash
<dependency>
        <groupId>org.junit.platform</groupId>
        <artifactId>junit-platform-launcher</artifactId>
        <version>1.3.0</version>
        <scope>test</scope>
</dependency>
```
Para mais informações:
https://mvnrepository.com/artifact/org.junit.platform/junit-platform-launcher


### Instale o  Junit Jupiter Engine.
 
   No arquivo pom.xml adicione a dependência:

```bash
<dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-engine</artifactId>
        <version>5.3.0</version>
        <scope>test</scope>
</dependency>
```
Para mais informações:
https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-engine


### Instale o  Junit Vintage Engine.
 
   No arquivo pom.xml adicione a dependência:

```bash
<dependency>
        <groupId>org.junit.vintage</groupId>
        <artifactId>junit-vintage-engine</artifactId>
        <version>5.3.0</version>
        <scope>test</scope>
</dependency>
```
Para mais informações:
https://mvnrepository.com/artifact/org.junit.vintage/junit-vintage-engine


 ### Instale o  MySQL Connector.
 
   No arquivo pom.xml adicione a dependência:

```bash
<dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
        <version>8.0.31</version>
        <scope>compile</scope>
</dependency>
```
Para mais informações:
https://mvnrepository.com/artifact/com.mysql/mysql-connector-j







## Executando o Projeto

 Após ter feito a instalação dos pré-requisitos siga os passos a baixo:
    

 * Clonando o repositório 

 1. No repositório do [projeto](https://github.com/MatheusGama01/A3-2022-2) clique no botão verde escrito code.

 2. No menu aberto clique em "Download zip", o arquivo será baixado no seu computador.

 3. Ultize um descompactador de sua preferência para descompactar o arquivo A3-2022-2-main.zip.


* Abrindo o projeto na IDE 

 1. Para este projeto usaremos a IDE [Netbeans 12.0](https://netbeans.apache.org/download/nb120/nb120.html) como referência, mas você pode usar a IDE de sua preferência.

 2. Abra o Netbeans e clique no menu **`File`**, depois clique na opção **`Open Project...`**.

 3. Localize o arquivo descompactado A3-2022-2-main no seu computador, abra a pasta e selecione o arquivo dentro após isso clique em **`Open Project`**.

 4. Com o projeto aberto, localize a pasta **`Project File`** e abra o arquivo **`pom.xml`**.

 5. Verifique se o arquivo **`pom.xml`** está dessa maneira:

```bash
    <dependencies>
        <dependency>
            <groupId>org.netbeans.external</groupId>
            <artifactId>AbsoluteLayout</artifactId>
            <version>RELEASE120</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/com.mysql/mysql-connector-j -->
        <dependency>
            <groupId>com.mysql</groupId>
            <artifactId>mysql-connector-j</artifactId>
            <version>8.0.31</version>
            <scope>compile</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-api</artifactId>
            <version>5.3.0</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-params</artifactId>
            <version>5.3.0</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.platform</groupId>
            <artifactId>junit-platform-launcher</artifactId>
            <version>1.3.0</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-engine</artifactId>
            <version>5.3.0</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.vintage</groupId>
            <artifactId>junit-vintage-engine</artifactId>
            <version>5.3.0</version>
            <scope>test</scope>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.mockito/mockito-core -->
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-core</artifactId>
            <version>2.23.4</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
```

* Se estiver diferente modifique-o para ficar igual, caso esteja dessa maneira não modifique.


* Criando o banco de dados

1. Após realizar a instalção do MySQL Workbench crie um servidor.

2. Caso tenha dúvida, consulte o vídeo: https://www.youtube.com/watch?v=iYN9a3i2qkQ&list=PLbEOwbQR9lqxsTusvu8wfkUECrmcV81MU&index=3

3. No servidor criado insira o códido SQL:

```bash
-- Cria banco de dados
create database nomebanco;

-- Seleciona banco de dados a ser usado
use nomebanco;

-- Cria tabela de usuários
Create table usuarios(
id int primary key auto_increment,
nome varchar(50) not null,
email varchar(50) not null unique,
senha varchar(90) not null
);

-- Cria tabela de tarefas
Create table tarefas(
id int primary key auto_increment,
descricao varchar(255) not null,
status boolean not null,
idUsuario int not null,
foreign key(idUsuario) references usuarios(id)
);
```

4. Onde estiver **`nomebanco`** substitua para o nome que quer dar para o seu banco.


* Conectando o projeto com o banco de dados 

1. No projeto, abra a pasta **`Source Package`**, abra o pacote DAO e abra o arquivo **`ConexaoDAO.java`**.

```bash
String url = "jdbc:mysql://localhost:3306/nomebanco";
String usuario = "";
String senha = "";
```

2. Na String url substituia nomebanco pelo nome do seu banco de dados.

3. Na String usuario insira dentro das aspas o nome do usuario do seu banco de dados no MySQL Workbench.

4. Na String senha insira dentro das aspas a senha do seu bando de dados no MySQL Workbench.





    

# 🔥 Autores

- [Gabriele Cardoso](https://github.com/2433461)
- [Matheus Gama](https://github.com/MatheusGama01)
- [Vitor Magalhães](https://github.com/Vitor387)

 # 😊  Orientadores 
 
- [Raissa Arruda]()
- [Hissamu Shirado]()

