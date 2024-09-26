import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private int cenaAtualId;
    private List<Item> inventario;
    private Connection connection;

    public Game() {
        this.cenaAtualId = 4; // ID da cena inicial
        this.inventario = new ArrayList<>();
        this.connection = Mysql.getConnection();
    }

    public void saveGame() {
        String sql = "INSERT INTO Jogo_salvo (cena_atual) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, cenaAtualId);
            stmt.executeUpdate();
            System.out.println("Jogo salvo com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadGame() {
        String sql = "SELECT cena_atual FROM Jogo_salvo ORDER BY id DESC LIMIT 1";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                cenaAtualId = rs.getInt("cena_atual");
                System.out.println("Jogo carregado com sucesso! Cena atual: " + cenaAtualId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Comando Para restaurar cena
    public void restartGame() {
        cenaAtualId = 4;
        System.out.println("O jogo foi reiniciado!");
    }

    public int getCenaAtualId() {
        return cenaAtualId;
    }

    public void setCenaAtualId(int cenaAtualId) {
        this.cenaAtualId = cenaAtualId;
    }

    public List<Item> getInventario() {
        return inventario;
    }
}
