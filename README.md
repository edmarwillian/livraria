# 📊 Livraria API — Spring Boot

API REST para gerenciamento de uma livraria.

---

## Diagramas do Projeto

### Diagrama do sistema
![Diagrama](docs/imagens/diagrama.jpg)

### Modelo de dados
![Modelo](docs/imagens/modelo.jpg)

---

# Autenticação

A API utiliza Basic Auth.

Credenciais padrão:

```
Username: admin
Password: 123
```

Importante:

* GET → público (não precisa login)
* POST / PUT / DELETE → requer autenticação

---

# Endpoints

## Clientes

GET

```
GET /clientes
GET /clientes/{id}
```

POST

```
POST /clientes
```

```json
{
  "nome": "João",
  "email": "joao@email.com"
}
```

PUT

```
PUT /clientes/{id}
```

DELETE

```
DELETE /clientes/{id}
```

---

## Livros

GET

```
GET /livros
GET /livros/{id}
```

POST

```
POST /livros
```

```json
{
  "titulo": "Java",
  "preco": 50.0
}
```

PUT

```
PUT /livros/{id}
```

DELETE

```
DELETE /livros/{id}
```

---

## Pedidos

GET

```
GET /pedidos
GET /pedidos/{id}
```

POST

```
POST /pedidos
```

```json
{
  "cliente": {
    "codCliente": 1
  }
}
```

PUT

```
PUT /pedidos/{id}
```

DELETE

```
DELETE /pedidos/{id}
```

---

## Contem

GET

```
GET /contem
GET /contem/{id}
```

POST

```
POST /contem
```

```json
{
  "pedido": {
    "numeroPedido": 1
  },
  "livro": {
    "codLivro": 1
  }
}
```

PUT

```
PUT /contem/{id}
```

DELETE

```
DELETE /contem/{id}
```

---

## Estoque

GET

```
GET /estoque
GET /estoque/{id}
```

POST

```
POST /estoque
```

```json
{
  "quantidadeLivro": 10,
  "livro": {
    "codLivro": 1
  }
}
```

PUT

```
PUT /estoque/{id}
```

DELETE

```
DELETE /estoque/{id}
```
