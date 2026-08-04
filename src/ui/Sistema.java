package ui;

import model.Pedido;
import model.Produto;
import service.Estoque;

import java.util.Scanner;

public class Sistema {

    private Scanner leitor;
    private Estoque estoque;

    public Sistema() {
        leitor = new Scanner(System.in);
        estoque = new Estoque();
    }
    private int lerInteiro() {

        while (true) {

            if (leitor.hasNextInt()) {
                int numero = leitor.nextInt();
                leitor.nextLine();
                return numero;
            }

            System.out.print("Entrada inválida! Digite um número inteiro: ");
            leitor.nextLine();
        }
    }
    private double lerDouble() {

        while (true) {

            if (leitor.hasNextDouble()) {
                double numero = leitor.nextDouble();
                leitor.nextLine();
                return numero;
            }

            System.out.print("Entrada inválida! Digite um número: ");
            leitor.nextLine();
        }
    }


    public void iniciar() {

        int opcao;

        do {
            mostrarMenu();

            opcao = lerInteiro();

            executarOpcao(opcao);

        } while (opcao != 0);

        leitor.close();
    }

    private void cadastrarProduto() {
        System.out.print("Nome: ");
        String nome = leitor.nextLine();

        System.out.print("Preço: ");
        double preco = lerDouble();

        System.out.print("Quantidade: ");
        int quantidade = lerInteiro();

        if (estoque.cadastrarProduto(nome, preco, quantidade)) {
            System.out.println("✔ Produto cadastrado com sucesso!");
        } else {
            System.out.println("✖ Não foi possível cadastrar o produto.");
        }
    }
    private void listarProdutos(){
    estoque.listarProdutos();
}
    private void removerProduto() {
        System.out.print("Digite o nome do produto que deseja remover: ");
        String nomeRemover = leitor.nextLine();

        if (estoque.removerProduto(nomeRemover)) {
            System.out.println("✔ Produto removido com sucesso!");
        } else {
            System.out.println("✖ Produto não encontrado!");
        }
    }
    private void venderProduto() {

        System.out.println(
                "Digite o produto que quer vender:"
        );

        String nomeVenda = leitor.nextLine();

        Produto produto =
                estoque.buscarProduto(nomeVenda);

        if (produto == null) {
            System.out.println(
                    "✖ Produto não encontrado!"
            );
            return;
        }

        produto.exibirProduto();

        System.out.println(
                "Quantidade que venderá:"
        );

        int quantidadeVenda = lerInteiro();

        if (estoque.venderProduto(
                nomeVenda,
                quantidadeVenda
        )) {

            Produto produtoAtualizado =
                    estoque.buscarProduto(nomeVenda);

            System.out.println(
                    "✔ Venda realizada com sucesso!"
            );

            System.out.println(
                    "Quantidade restante em estoque: "
                            + produtoAtualizado.getQuantidade()
            );

        } else {

            System.out.println(
                    "✖ Venda inválida ou estoque insuficiente!"
            );
        }
    }
    private void listarPedidos() {
        System.out.println("== PEDIDOS REALIZADOS ==");
        estoque.listarPedidos();
    }
    private void mostrarFaturamento(){
        double faturamento = estoque.calcularFaturamento();

        if (faturamento > 0) {
            System.out.printf("Faturamento total: R$ %.2f%n", faturamento);
        }
    }
    private void buscarProduto() {

            System.out.println("Digite o nome ou parte do nome do produto:");

            String nomeBusca = leitor.nextLine();

            estoque.buscarProdutosPorNome(nomeBusca);
        }
    private void reporEstoque() {

        System.out.println("Digite o produto:");
        String nomeProduto = leitor.nextLine();

        Produto produto = estoque.buscarProduto(nomeProduto);

        if (produto == null) {
            System.out.println("✖ Produto não encontrado!");
            return;
        }

        produto.exibirProduto();

        System.out.println("Quantidade recebida:");
        int quantidadeRecebida = lerInteiro();

        if (estoque.reporEstoque(nomeProduto, quantidadeRecebida)) {

            Produto produtoAtualizado =
                    estoque.buscarProduto(nomeProduto);

            System.out.println("✔ Estoque atualizado!");

            System.out.println(
                    "Quantidade atual em estoque: "
                            + produtoAtualizado.getQuantidade()
            );

        } else {

            System.out.println("A quantidade deve ser maior que zero.");
        }
    }
    private void mostrarProdutoMaisVendido(){
        System.out.println("== PRODUTO MAIS VENDIDO ==");

        Produto maisVendido = estoque.produtoMaisVendido();

        if (maisVendido != null) {

            System.out.println(
                    "O produto mais vendido é: "
                            + maisVendido.getNome()
            );

            System.out.println(
                    "Quantidade vendida: "
                            + maisVendido.getTotalVendido()
            );

        } else {
            System.out.println(
                    "Nenhuma venda realizada até o momento."
            );
        }
    }
    private void exibirResumo() {
        estoque.exibirResumo();
    }
    private void mostrarMenu() {

        System.out.println("==============================================");
        System.out.println("      SISTEMA DE CONTROLE DE ESTOQUE");
        System.out.println("==============================================");
        System.out.println("      Desenvolvido por Leonardo Santos");
        System.out.println("==============================================");

        System.out.println("1 - Cadastrar Produto");
        System.out.println("2 - Listar Produtos");
        System.out.println("3 - Remover Produto");
        System.out.println("4 - Vender Produto");
        System.out.println("5 - Listar Pedidos");
        System.out.println("6 - Mostrar Faturamento");
        System.out.println("7 - Buscar Produto");
        System.out.println("8 - Repor Estoque");
        System.out.println("9 - Produto Mais Vendido");
        System.out.println("10 - Exibir Resumo");
        System.out.println("11 - Relatórios");
        System.out.println("0 - Sair");
        System.out.println("===========================");

        System.out.print("Escolha uma opção: ");
    }

    private void executarOpcao(int opcao) {

        switch (opcao) {

            case 1:
               cadastrarProduto();
                break;

            case 2:
                listarProdutos();
                break;
            case 3:
                removerProduto();
                break;

            case 4:
               venderProduto();
                break;

            case 5:
                listarPedidos();
                break;

            case 6:
                mostrarFaturamento();
                break;

            case 7:
                buscarProduto();
                break;

            case 8:
                reporEstoque();
                break;

            case 9:
                mostrarProdutoMaisVendido();
                break;

            case 10:
               exibirResumo();
                break;
            case 11:
                int opcaoRelatorio;

                do {

                    System.out.println("=========================");
                    System.out.println("      RELATÓRIOS");
                    System.out.println("=========================");

                System.out.println("1- Estoque baixo");
                System.out.println("2 - Produtos sem vendas");
                System.out.println("3 - Histórico de pedidos");
                System.out.println("0 - Voltar");
                System.out.println("===========================");

                System.out.println("Escolha uma opção");

                    opcaoRelatorio = lerInteiro();
                    switch (opcaoRelatorio) {

                        case 1:
                            estoque.listarEstoqueBaixo();
                            break;
                        case 2:
                            estoque.listarProdutosSemVenda();
                            break;
                        case 3:
                            estoque.listarPedidos();
                            break;

                        default:
                            System.out.println("Opção inválida!");
                    }

                } while (opcaoRelatorio != 0);

                    break;

            case 0:
                System.out.println("Encerrando...");
                System.out.println();
                System.out.println("===========================================================");
                System.out.println("  Obrigado por utilizar o Sistema de Controle de Estoque.");
                System.out.println("===========================================================");
                break;

            default:
                System.out.println("Opção inválida!");
        }
    }
}