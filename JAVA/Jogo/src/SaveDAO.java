import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SaveDAO {
    private Connection connection;

    public SaveDAO() {
        this.connection = Mysql.getConnection();
    }

    public void saveGame(int cenaAtualId) {
        String sql = "INSERT INTO Jogo_Salvo (id_cena_atual) VALUES (?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, cenaAtualId);
            stmt.executeUpdate();
            System.out.println("Jogo salvo com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
