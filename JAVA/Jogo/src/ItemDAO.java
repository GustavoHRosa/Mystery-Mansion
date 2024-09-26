import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemDAO {
    private Connection connection;

    public ItemDAO() {
        this.connection = Mysql.getConnection();
    }

    public Item getItemByNome(String nome) {
        String sql = "SELECT * FROM Item WHERE nome = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Item item = new Item();
                item.setId(rs.getInt("id"));
                item.setNome(rs.getString("nome"));
                item.setDescricao(rs.getString("descricao"));
                item.setPodeSerColetado(rs.getBoolean("pode_ser_coletado"));
                item.setIdCena(rs.getInt("id_cena"));
                return item;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
