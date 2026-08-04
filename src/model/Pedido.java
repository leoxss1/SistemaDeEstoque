package model;

import java.time.LocalDateTime;
import java.text.NumberFormat;
import java.util.Locale;

public class Pedido {

    private String nomeProduto;
    private int quantidade;
    private double valorTotal;
    private LocalDateTime dataVenda;

    public Pedido(String nomeProduto,
                  int quantidade,
                  double valorTotal,
                  LocalDateTime dataVenda) {

        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
        this.dataVenda = dataVenda;
    }
    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void exibirPedido() {

        NumberFormat formatoMoeda =
        NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

        System.out.println("Produto: " + nomeProduto);
        System.out.println("Quantidade: " + quantidade);
        System.out.println("Valor total:  " + valorTotal);
    }


    public LocalDateTime getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDateTime dataVenda) {
        this.dataVenda = dataVenda;
    }
}