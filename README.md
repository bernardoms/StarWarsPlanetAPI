# StarWarsPlanetAPI
## Tecnologias Utilizadas no Projeto
* Linguagem de programação: Java
* Framework: Spring boot
* Banco de dados: H2 embedded

## Testes unitários
Os testes unitários foram feitos utilzando o JUnit com MockMvc.
Foram feitos no total 10 cenários de testes que estão na pasta src/test/java/StarWarsPlanetApiApplicationTests/


## endpoints presentes no projeto e seus respectivos exemplos:

Exemplo de endpoint para criar um planeta

```sh
curl -H "Content-Type: application/json" -X POST -d '{"nome":"Alderaan","clima":"temperate","terreno":"grasslands, mountains"}' http://localhost:8080/planets
```

Exemplo de endpoint para ler as informações de todos os planetas

```sh
curl http://localhost:8080/planets
```

Exemplo endpoint ler as informações de um planeta pelo nome

```sh
curl http://localhost:8080/planets/search/Alderaan
```

Exemplo endpoint ler as informações de um planeta pelo id

```sh
curl http://localhost:8080/planets/1
```

Exemplo endpoint para remover um planeta pelo id.

```sh
curl -X "DELETE" http://localhost:8080/planets/1
```



