import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Mysql {
    private static final String URL = "jdbc:mysql://localhost:3306/jogo_textadventure";
    private static final String USER = "root"; // Altere para seu usuário do MySQL
    private static final String PASSWORD = ""; // Altere para sua senha do MySQL

    public static Connection getConnection() {
        Connection connection = null;
        try {
            // Carregar o driver JDBC do MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Estabelecer a conexão
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexão com o banco de dados estabelecida com sucesso!");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver JDBC não encontrado!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Erro ao estabelecer a conexão com o banco de dados!");
            e.printStackTrace();
        }
        return connection;
    }
}
