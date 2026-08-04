package service;

import model.Pedido;
import model.Produto;
import dao.ProdutoDAO;
import dao.PedidoDAO;

import java.util.ArrayList;
import java.text.NumberFormat;
import java.util.Locale;
import java.time.LocalDateTime;

public class Estoque {


    private ProdutoDAO produtoDAO = new ProdutoDAO();
    private PedidoDAO pedidoDAO = new PedidoDAO();



    public boolean produtoExiste(String nome) {
        return buscarProduto(nome) != null;

    }

    public boolean cadastrarProduto(String nome, double preco, int quantidade) {

        if (produtoExiste(nome)) {
            System.out.println("Este produto ja existe!");
            return false;
        }
        if (preco <= 0) {
            System.out.println("Preço invalido!");
            return false;
        } else if (quantidade < 0) {
            System.out.println("Quantidade Invalida!");
            return false;
        }

        Produto produto = new Produto(nome, preco, quantidade);

        produtoDAO.cadastrar(produto);

        return true;
    }

    public void listarProdutos() {

        ArrayList<Produto> produtos =
                produtoDAO.listar();

        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto Cadastrado.");
            return;
        }
        for (Produto produto : produtos) {
            System.out.println();
            produto.exibirProduto();
        }
    }

    public Produto buscarProduto(String nome) {

        ArrayList<Produto> produtosBanco = produtoDAO.listar();

        System.out.println("Produtos encontrados no banco: "
                + produtosBanco.size());

        for (Produto produto : produtosBanco) {


            if (produto.getNome().equalsIgnoreCase(nome)) {
                return produto;
            }
        }

        return null;
    }

    public void listarPedidos() {

        ArrayList<Pedido> pedidos =
                pedidoDAO.listar();

        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido realizado.");
            return;
        }

        for (Pedido pedido : pedidos) {

            pedido.exibirPedido();

            System.out.println("--------------------");
        }
    }

    public double calcularFaturamento() {

        return pedidoDAO.calcularFaturamento();
    }

    public Produto produtoMaisVendido() {

        Produto maisVendido =
                produtoDAO.produtoMaisVendido();

        if (maisVendido == null) {
            return null;
        }

        if (maisVendido.getTotalVendido() == 0) {
            return null;
        }

        return maisVendido;
    }

    public boolean removerProduto(String nome) {

        Produto produto = buscarProduto(nome);

        if (produto == null) {
            return false;
        }

        return produtoDAO.remover(produto.getId());
    }

    public void exibirResumo() {

        NumberFormat formatoMoeda =
                NumberFormat.getCurrencyInstance(
                        new Locale("pt", "BR")
                );

        int totalProdutos =
                produtoDAO.contarProdutos();

        Produto maisVendido =
                produtoMaisVendido();

        System.out.println(
                " RESUMO DO ESTOQUE "
        );

        System.out.println(
                "Total de Produtos: "
                        + totalProdutos
        );

        System.out.println(
                "Total de pedidos: "
                        + pedidoDAO.listar().size()
        );

        System.out.println(
                "Faturamento total: "
                        + formatoMoeda.format(
                        calcularFaturamento()
                )
        );

        if (maisVendido != null) {

            System.out.println(
                    "Produto mais vendido: "
                            + maisVendido.getNome()
            );

        } else {

            System.out.println(
                    "Produto mais vendido: "
                            + "Nenhuma venda realizada"
            );
        }
    }

    public boolean reporEstoque(String nome, int quantidadeRecebida) {

        Produto produto = buscarProduto(nome);

        if (produto == null) {
            return false;
        }

        if (!produto.reporEstoque(quantidadeRecebida)) {
            return false;
        }

        System.out.println("ID enviado: " + produto.getId());
        System.out.println("Quantidade enviada: " + produto.getQuantidade());

        return produtoDAO.atualizarQuantidade(
                produto.getId(),
                produto.getQuantidade()
        );
    }

    public boolean venderProduto(String nome, int quantidadeVenda) {

        Produto produto = buscarProduto(nome);

        if (produto == null) {
            return false;
        }

        if (!produto.vender(quantidadeVenda)) {
            return false;
        }

        boolean atualizado = produtoDAO.atualizarVenda(
                produto.getId(),
                produto.getQuantidade(),
                produto.getTotalVendido()
        );

        if (!atualizado) {
            return false;
        }

        double valorTotal =
                produto.getPreco() * quantidadeVenda;

        Pedido pedido = new Pedido(
                produto.getNome(),
                quantidadeVenda,
                valorTotal,
                LocalDateTime.now()
        );

        pedidoDAO.cadastrar(pedido);

        return true;
    }

    public void listarEstoqueBaixo() {

        ArrayList<Produto> produtos =
                produtoDAO.estoqueBaixo(5);

        if (produtos.isEmpty()) {

            System.out.println(
                    "Nenhum produto com estoque baixo."
            );

            return;
        }

        System.out.println(
                "PRODUTOS COM ESTOQUE BAIXO"
        );

        for (Produto produto : produtos) {

            produto.exibirProduto();

            System.out.println("----------------");
        }
    }

    public void listarProdutosSemVenda() {

        ArrayList<Produto> produtos =
                produtoDAO.produtosSemVenda();

        if (produtos.isEmpty()) {

            System.out.println(
                    "Todos os produtos já possuem vendas."
            );

            return;
        }

        System.out.println(
                "PRODUTOS SEM VENDAS"
        );

        for (Produto produto : produtos) {

            produto.exibirProduto();

            System.out.println("-------------------");
        }
    }

    public void buscarProdutosPorNome(String nome) {

        ArrayList<Produto> produtos =
                produtoDAO.buscarPorNome(nome);

        if (produtos.isEmpty()) {

            System.out.println("Nenhum produto encontrado.");
            return;
        }

        System.out.println("\nProdutos encontrados:\n");

        for (Produto produto : produtos) {

            produto.exibirProduto();
            System.out.println("----------------------");
        }
    }
}
