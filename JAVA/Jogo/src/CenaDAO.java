import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CenaDAO {
    private Connection connection;

    public CenaDAO() {
        this.connection = Mysql.getConnection(); // Conexão com o banco de dados
    }

    public Cena getCenaById(int id) {
        Cena cena = null;
        String sql = "SELECT * FROM Cena WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int cenaId = rs.getInt("id");
                String descricao = rs.getString("descricao");
                int idProximaCena = rs.getInt("id_proxima_cena");
                String comandoCorreto = rs.getString("comando_correto");
                cena = new Cena(cenaId, descricao, idProximaCena, comandoCorreto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cena;
    }

    public List<String> getComandosByCenaId(int cenaId) {
        List<String> comandos = new ArrayList<>();
        String sql = "SELECT comando_correto FROM Cena WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, cenaId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                comandos.add(rs.getString("comando_correto"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comandos;
    }
}
