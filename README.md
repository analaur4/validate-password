# Validação de Senha
Projeto desenvolvido como resolução de case para o banco Itaú

## Dependências do projeto
* Spring Boot
* Bean Validation
* Lombok
* JUnit

## Requisitos
* Java 11
* Maven 3.8

## Para rodar o projeto local
Clone esse repositório e entre na pasta do projeto.
````shell
git clone https://github.com/analaur4/validate-password
cd validate-password
````

Execute o projeto através do Maven.
````shell
mvn spring-boot:run
````

Acesse a api em http://localhost:8080/validate-password

## Solução

Na classe de modelo foi utilizado o Bean Validation para validar:
* a quantidade mínima de caracteres
    * Através da anotação @Size
    
* se a string possui os caracteres necessários para a senha ser considerada válida
    * Através da anotação @Pattern com o regex ````(^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#$%^&*()-+]).*)````, onde:
        * ````^````: início da expressão
        * ````(?=.*[0-9])````: qualquer caractere, nenhuma ou uma vez, entre os dígitos de 0 a 9
        * ````(?=.*[a-z])````: qualquer caractere, nenhuma ou uma vez, entre letras minúsculas
        * ````(?=.*[A-Z])````: qualquer caractere, nenhuma ou uma vez, entre letras maiúsculas
        * ````(?=.*[!@#$%^&*()-+]).*````: qualquer caractere, nenhuma ou uma vez, entre os símbolos especiais envolvidos entre colchetes
    
Já na classe de serviço, onde o método ````isValid()```` foi implementado, utilizo o método ````.split()```` para tratar a string recebida como um array e acessar os seus itens através de um ````for i````. No laço de repetição verifico se há algum espaço vazio na string acessando o método ````.contains()```` do item correspondente e se há caracteres repetidos utilizando a classe ````StringUtils```` do Spring Framework.
 * O método utilizado foi o ````.countOccurrencesOf()```` que recebe como primeiro parâmetro a string na qual deverá ocorrer a busca e em segundo o item que esperamos encontrar. Esse método retorna a quantidade de ocorrências, então foi validado se essa quantidade é maior do que 1.
    
Caso as validações do Bean Validation não sejam preenchidas, será lançada 
uma exceção do tipo ````MethodArgumentNotValidException````. E se caso as condições 
do método forem atendidas a exceção lançada será uma personalizada, chamada ````PasswordInvalidException````.

Essas exceções são capturadas por uma classe de handler - ````ResourceExceptionHandler```` - que as aguarda e retorna o response adequado informando que a senha não é válida e seu motivo seguido do status 400.
Se não houverem erros, é retornado no response body que a senha é válida e o código de status é 200.

## Autora
- GitHub - [analaur4](https://github.com/analaur4)
- LinkedIn - [Ana Laura Silva](https://www.linkedin.com/in/analaura-silva/)
