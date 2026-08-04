package model;


public class Produto {
    private String nome;
    private double preco;
    private int quantidade;
    private int totalVendido;


    public Produto(String nome, double preco, int quantidade) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public void exibirProduto(){

        System.out.println(
               "Nome: " + nome + "\n" +
               "Preço: " + preco +  "\n" +
               "Quantidade: " + quantidade + "\n" +
               "Total vendido: " + totalVendido);
    }

    public boolean vender(int quantidadeVendida) {

        if (quantidadeVendida <= 0 || quantidadeVendida > quantidade) {
            return false;
        }
        quantidade -= quantidadeVendida;
        totalVendido += quantidadeVendida;

        return true;
    }

        public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;

    }

    public void setPreco(double preco) {
       if (preco > 0) {
           this.preco = preco;
       }
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
       if (quantidade >= 0){
        this.quantidade = quantidade;
       }
    }

    public int getTotalVendido(){
        return totalVendido;
    }

    public void setTotalVendido(int totalVendido) {
        this.totalVendido = totalVendido;
    }

    public boolean reporEstoque (int quantidadeRecebida) {

        if (quantidadeRecebida <= 0){
            return false;
        }

        quantidade += quantidadeRecebida;
        return true;

    }

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
