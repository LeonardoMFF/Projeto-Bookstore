//connection da bookstore
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Gerencia a conexão com o banco de dados. possui as instruções
 * para conectar ao banco.
 * @author devsys-b
 */
public class MySQLConnection {
    
    //define as strings de conexão com o banco
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://172.16.0.30:3306/lmff_bookstore";
    private static final String USER = "leonardo";
    private static final String PASS = "21262811";
    
    /**
    * Cria conexão com o banco de dados MySQL.
    * @author devsys-b
     * @return 
    */
    
    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
        } catch(SQLException ex) {
            throw new RuntimeException("Erro na coneão com o BD. Verifique!", ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQLConnection.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro na coneão com o BD. Verifique!", ex);
        }
    }
    
    /**
    * Fecha a conexão com o banco de dados MySQL.
    * @param conn
    * @author devsys-b
     * 
    */
    
    public static void CloseConnection(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        }
        catch(SQLException ex) {
            Logger.getLogger(MySQLConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /**
    * Fecha a conexão com o banco de dados MySQL.
    * @param conn
    * @param stmt
    * @author devsys-b
     * 
     * 
    */
    
    public static void CloseConnection(Connection conn, PreparedStatement stmt) {
        CloseConnection(conn);
        
        try {
            if (stmt != null) {
                stmt.close();
            }
        }
        catch(SQLException ex) {
            Logger.getLogger(MySQLConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
    * Fecha a conexão com o banco de dados MySQL.
    * @param conn
    * @param stmt
    * @param rs
    * @author devsys-b
     * 
     * 
    */
    public static void CloseConnection(Connection conn, PreparedStatement stmt, ResultSet rs) {
        CloseConnection(conn, stmt);
        
        try {
            if (rs != null) {
                rs.close();
            }
        }
        catch(SQLException ex) {
            Logger.getLogger(MySQLConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
