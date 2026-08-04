package dao;

import database.Conexao;
import model.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ProdutoDAO {

    public void cadastrar(Produto produto) {

        String sql = """
            
                INSERT INTO produtos
            (nome, preco, quantidade, total_vendido)
            VALUES (?, ?, ?, ?)
            """;

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setString(1, produto.getNome());
            comando.setDouble(2, produto.getPreco());
            comando.setInt(3, produto.getQuantidade());
            comando.setInt(4, produto.getTotalVendido());

            comando.executeUpdate();

            System.out.println("Produto salvo no banco!");

        } catch (SQLException e) {

            System.out.println("Erro ao cadastrar produto no banco.");
            e.printStackTrace();
        }
    }
    public ArrayList<Produto> listar() {

        ArrayList<Produto> produtos = new ArrayList<>();

        String sql = "SELECT * FROM produtos";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql);
             ResultSet resultado = comando.executeQuery()) {

            while (resultado.next()) {

                int id = resultado.getInt("id");
                String nome = resultado.getString("nome");
                double preco = resultado.getDouble("preco");
                int quantidade = resultado.getInt("quantidade");
                int totalVendido = resultado.getInt("total_vendido");

                Produto produto = new Produto(
                        nome,
                        preco,
                        quantidade
                );

                produto.setId(id);
                produto.setTotalVendido(totalVendido);

                produtos.add(produto);
            }

        } catch (SQLException e) {

            System.out.println("Erro ao listar produtos.");
            e.printStackTrace();
        }

        return produtos;
    }
    public boolean remover(int id) {

        String sql = "DELETE FROM produtos WHERE id = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setInt(1, id);

            int linhasAfetadas = comando.executeUpdate();

            return linhasAfetadas > 0;

        } catch (SQLException e) {

            System.out.println("Erro ao remover produto do banco.");
            e.printStackTrace();

            return false;
        }
    }
    public boolean atualizarQuantidade(int id, int quantidade) {

        String sql = "UPDATE produtos SET quantidade = ? WHERE id = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setInt(1, quantidade);
            comando.setInt(2, id);

            System.out.println("Banco conectado: "
                    + conexao.getCatalog());

            System.out.println("ID: " + id);
            System.out.println("Quantidade: " + quantidade);

            int linhasAfetadas = comando.executeUpdate();

            System.out.println("Linhas atualizadas: "
                    + linhasAfetadas);

            return linhasAfetadas > 0;

        } catch (SQLException e) {

            System.out.println("Erro ao atualizar quantidade.");
            e.printStackTrace();

            return false;
        }
    }
    public Produto produtoMaisVendido() {

        String sql = """
            SELECT *
            FROM produtos
            ORDER BY total_vendido DESC
            LIMIT 1
            """;

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando =
                     conexao.prepareStatement(sql);
             ResultSet resultado =
                     comando.executeQuery()) {

            if (resultado.next()) {

                int id =
                        resultado.getInt("id");

                String nome =
                        resultado.getString("nome");

                double preco =
                        resultado.getDouble("preco");

                int quantidade =
                        resultado.getInt("quantidade");

                int totalVendido =
                        resultado.getInt("total_vendido");

                Produto produto =
                        new Produto(
                                nome,
                                preco,
                                quantidade
                        );

                produto.setId(id);
                produto.setTotalVendido(totalVendido);

                return produto;
            }

        } catch (SQLException e) {

            System.out.println(
                    "Erro ao buscar produto mais vendido."
            );

            e.printStackTrace();
        }

        return null;
    }
    public int contarProdutos() {

        String sql = "SELECT COUNT(*) AS total FROM produtos";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando =
                     conexao.prepareStatement(sql);
             ResultSet resultado =
                     comando.executeQuery()) {

            if (resultado.next()) {
                return resultado.getInt("total");
            }

        } catch (SQLException e) {

            System.out.println(
                    "Erro ao contar produtos."
            );

            e.printStackTrace();
        }

        return 0;
    }
    public boolean atualizarVenda(int id, int quantidade, int totalVendido) {

        String sql = """
            UPDATE produtos
            SET quantidade = ?,
                total_vendido = ?
            WHERE id = ?
            """;

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando =
                     conexao.prepareStatement(sql)) {

            comando.setInt(1, quantidade);
            comando.setInt(2, totalVendido);
            comando.setInt(3, id);

            int linhasAfetadas = comando.executeUpdate();

            System.out.println(
                    "Linhas da venda atualizadas: "
                            + linhasAfetadas
            );

            return linhasAfetadas > 0;

        } catch (SQLException e) {

            System.out.println("Erro ao atualizar venda.");
            e.printStackTrace();

            return false;
        }
    }
    public ArrayList<Produto> estoqueBaixo(int limite) {

        ArrayList<Produto> produtos = new ArrayList<>();

        String sql = """
            SELECT *
            FROM produtos
            WHERE quantidade <= ?
            ORDER BY quantidade
            """;

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando =
                     conexao.prepareStatement(sql)) {

            comando.setInt(1, limite);

            ResultSet resultado =
                    comando.executeQuery();

            while (resultado.next()) {

                Produto produto = new Produto(
                        resultado.getString("nome"),
                        resultado.getDouble("preco"),
                        resultado.getInt("quantidade")
                );

                produto.setId(resultado.getInt("id"));
                produto.setTotalVendido(
                        resultado.getInt("total_vendido")
                );

                produtos.add(produto);
            }

        } catch (SQLException e) {

            System.out.println("Erro ao buscar produtos.");

            e.printStackTrace();
        }

        return produtos;
    }
    public ArrayList<Produto> produtosSemVenda() {

        ArrayList<Produto> produtos = new ArrayList<>();

        String sql = """
            SELECT *
            FROM produtos
            WHERE total_vendido = 0
            ORDER BY nome
            """;

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql);
             ResultSet resultado = comando.executeQuery()) {

            while (resultado.next()) {

                Produto produto = new Produto(
                        resultado.getString("nome"),
                        resultado.getDouble("preco"),
                        resultado.getInt("quantidade")
                );

                produto.setId(resultado.getInt("id"));
                produto.setTotalVendido(
                        resultado.getInt("total_vendido")
                );

                produtos.add(produto);
            }

        } catch (SQLException e) {

            System.out.println("Erro ao buscar produtos sem venda.");
            e.printStackTrace();
        }

        return produtos;
    }

    public ArrayList<Produto> buscarPorNome(String nome) {

        ArrayList<Produto> produtos = new ArrayList<>();

        String sql = """
            SELECT *
            FROM produtos
            WHERE nome LIKE ?
            ORDER BY nome
            """;

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando =
                     conexao.prepareStatement(sql)) {

            comando.setString(1, "%" + nome + "%");

            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {

                Produto produto = new Produto(
                        resultado.getString("nome"),
                        resultado.getDouble("preco"),
                        resultado.getInt("quantidade")
                );

                produto.setId(resultado.getInt("id"));
                produto.setTotalVendido(resultado.getInt("total_vendido"));

                produtos.add(produto);
            }

        } catch (SQLException e) {

            System.out.println("Erro ao buscar produtos.");
            e.printStackTrace();
        }

        return produtos;
    }
}