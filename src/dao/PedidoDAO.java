package dao;

import database.Conexao;
import model.Pedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.sql.Timestamp;

public class PedidoDAO {

    public boolean cadastrar(Pedido pedido) {

        String sql = """
        INSERT INTO pedidos
        (produto_nome, quantidade, valor_total, data_venda)
        VALUES (?, ?, ?, ?)
        """;

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando =
                     conexao.prepareStatement(sql)) {

            comando.setString(
                    1,
                    pedido.getNomeProduto()
            );

            comando.setInt(
                    2,
                    pedido.getQuantidade()
            );

            comando.setDouble(
                    3,
                    pedido.getValorTotal()
            );
            comando.setTimestamp(
                    4,
                    Timestamp.valueOf(
                            pedido.getDataVenda()
                    )
            );

            comando.executeUpdate();

            System.out.println(
                    "Pedido salvo no banco!"
            );

            return true;

        } catch (SQLException e) {

            System.out.println(
                    "Erro ao salvar pedido."
            );

            e.printStackTrace();

            return false;
        }
    }
    public ArrayList<Pedido> listar() {

        ArrayList<Pedido> pedidos = new ArrayList<>();

        String sql = "SELECT * FROM pedidos";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando =
                     conexao.prepareStatement(sql);
             ResultSet resultado =
                     comando.executeQuery()) {

            while (resultado.next()) {

                String nomeProduto =
                        resultado.getString("produto_nome");

                int quantidade =
                        resultado.getInt("quantidade");

                double valorTotal =
                        resultado.getDouble("valor_total");
                LocalDateTime dataVenda =
                        resultado.getTimestamp("data_venda").toLocalDateTime();

                Pedido pedido = new Pedido(
                        nomeProduto,
                        quantidade,
                        valorTotal,
                        dataVenda
                );

                pedidos.add(pedido);
            }

        } catch (SQLException e) {

            System.out.println("Erro ao listar pedidos.");
            e.printStackTrace();
        }

        return pedidos;
    }
    public double calcularFaturamento() {

        String sql =
                "SELECT SUM(valor_total) AS faturamento "
                        + "FROM pedidos";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando =
                     conexao.prepareStatement(sql);
             ResultSet resultado =
                     comando.executeQuery()) {

            if (resultado.next()) {

                return resultado.getDouble(
                        "faturamento"
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "Erro ao calcular faturamento."
            );

            e.printStackTrace();
        }

        return 0;
    }


}
