import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InventarioDAO {
    private Connection connection;

    public InventarioDAO() {
        this.connection = Mysql.getConnection();
    }

    public void adicionarItemAoInventario(int idItem, int idJogo) {
        String sql = "INSERT INTO Inventario (id_item, id_jogo) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idItem);
            stmt.setInt(2, idJogo);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para listar itens do inventário
    public List<Item> getItensDoInventario(int idJogo) {
        List<Item> itens = new ArrayList<>();
        String sql = "SELECT i.id, i.nome, i.descricao FROM Inventario inv " +
                "JOIN Item i ON inv.id_item = i.id WHERE inv.id_jogo = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idJogo);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Item item = new Item();
                item.setId(rs.getInt("id"));
                item.setNome(rs.getString("nome"));
                item.setDescricao(rs.getString("descricao"));
                itens.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return itens;
    }

    // Método para limpar o inventário do jogador
    public void limparInventario(int idJogo) {
        String sql = "DELETE FROM Inventario WHERE id_jogo = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idJogo);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
