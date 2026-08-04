# 📦 Sistema de Controle de Estoque

## 📖 Sobre o Projeto

O Sistema de Controle de Estoque é uma aplicação desenvolvida em **Java** com integração ao **MySQL** utilizando **JDBC**.

O objetivo do projeto foi aplicar os conceitos de Programação Orientada a Objetos (POO), manipulação de banco de dados e arquitetura em camadas, simulando um sistema real de gerenciamento de estoque.

---

## 🚀 Funcionalidades

- ✅ Cadastro de produtos
- ✅ Listagem de produtos
- ✅ Busca de produtos por nome (LIKE)
- ✅ Remoção de produtos
- ✅ Reposição de estoque
- ✅ Registro de vendas
- ✅ Controle de quantidade em estoque
- ✅ Controle do total vendido
- ✅ Registro de pedidos
- ✅ Histórico de pedidos
- ✅ Cálculo do faturamento
- ✅ Produto mais vendido
- ✅ Relatórios gerenciais

---

## 🛠️ Tecnologias Utilizadas

- Java
- JDBC
- MySQL
- SQL
- Git
- GitHub
- IntelliJ IDEA

---

## 📂 Estrutura do Projeto

```text
src
│
├── dao
│   ├── ProdutoDAO.java
│   └── PedidoDAO.java
│
├── database
│   └── Conexao.java
│
├── model
│   ├── Produto.java
│   └── Pedido.java
│
├── service
│   └── Estoque.java
│
├── ui
│   └── Sistema.java
│
└── Main.java
```

---

## 🗄️ Banco de Dados

O projeto utiliza o **MySQL** para armazenar as informações.

### Tabelas

### Produtos

- ID
- Nome
- Preço
- Quantidade
- Total vendido

### Pedidos

- ID
- Produto
- Quantidade
- Valor Total
- Data da Venda

---

## ▶️ Como Executar

1. Clone este repositório.

```bash
git clone https://github.com/leoxss1/SistemaDeEstoque.git
```

2. Abra o projeto no IntelliJ IDEA.

3. Crie o banco de dados MySQL.

4. Execute o script SQL para criar as tabelas.

5. Configure o usuário e senha do banco na classe `Conexao.java`.

6. Execute a classe `Main.java`.

---

## 🎯 Aprendizados

Durante o desenvolvimento deste projeto foram praticados:

- Programação Orientada a Objetos
- Encapsulamento
- Classes e Objetos
- JDBC
- CRUD
- SQL
- DAO (Data Access Object)
- Arquitetura em Camadas
- Integração Java + MySQL

---

## 🔄 Próximas Melhorias

- API REST com Spring Boot
- Interface gráfica
- Dashboard
- Testes automatizados
- Autenticação de usuários

---

## 👨‍💻 Autor

**Leonardo Santos**

Estudante de Análise e Desenvolvimento de Sistemas.

- GitHub: https://github.com/leoxss1
- LinkedIn: *(linkedin.com/in/leonardo-santos-8a916726b/?skipRedirect=true)*