package connection;

import java.sql.*;

/**
 * Database connection
 *
 * @author Csabi
 */
public class DatabaseConnection {

    private static Connection connection = connect();

    /**
     * Created connection to the local SQLite databse
     *
     * @return
     */
    public static Connection connect() {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:schooldb.db");
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } /*finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }*/
        return conn;
    }

    public static Connection getConnection() {
        return connection;
    }
}