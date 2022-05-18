package local.lmff.bookstoreweb.model.dao;

import connection.MySQLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import local.lmff.bookstoreweb.model.bean.User;

/**
 *
 * @author devsys-b
 */
public class UserDAO {
    
    User u = new User();
    
    private static final String SQL_INSERT = "INSERT INTO user(email, password, "
            + "fullname)" + "VALUES (?, ?, ?)";
    
    private static final String SQL_SELECT_ALL = "SELECT * FROM user";
    private static final String SQL_SELECT_ID = "SELECT * FROM user " 
            + "WHERE id = ?";
    
    private static final String SQL_UPDATE = "UPDATE user SET email = ?," +
        "password = ?, fullname = ? WHERE id = ?";
    
    private static final String SQL_DELETE = "DELETE FROM user WHERE id = ?";
    
    /**
     * insere um usuário na base de dados User
     * @param u
     */
    
    public void create(User u) {
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, u.getEmail());
            stmt.setString(2, u.getPassword());
            stmt.setString(3, u.getFullname());
            
            //executa a querry
            int auxRetorno = stmt.executeUpdate();
            
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null,
                    "inclusão" + auxRetorno);
            
        }
        catch(SQLException sQLException) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null,
                    sQLException);
        }
        finally {
            //encerra a conexão com o BD
            MySQLConnection.CloseConnection(conn, stmt);
        }
    }
    
    /**
     * Retorna todos os registros da tabela user
     * @return
     */
    
    public List<User> getResults() {
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        User u = null;
        List<User> listaUsers = null;
        
        //Prepara a String de SELECT e executa a query.
        try {
            stmt = conn.prepareStatement(SQL_SELECT_ALL);
            rs = stmt.executeQuery();
            
            //Carrega os dados de ResultSet rs, converte em User e adiciona
            //na lista de retorno
            
            listaUsers = new ArrayList<>();
            
            while (rs.next()) {
                u = new User();
                u.setId(rs.getInt("id"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setFullname(rs.getString("fullname"));
                listaUsers.add(u);
            }
            
        }
        catch(SQLException sQLException) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null,
                    sQLException);
        }
        return listaUsers;
    }
    
    /**
     * Retorna um user da tabela user
     * @param id Identificação do User
     * @return
     */
    
    public User getResultsById(int id) {
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        User u = null;
        
        try {
            stmt = conn.prepareStatement(SQL_SELECT_ID);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                u = new User();
                u.setId(rs.getInt("id"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setFullname(rs.getString("fullname"));
            }
        }
        catch (SQLException sQLException) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null,
                    sQLException);
        }
        finally {
            //Encerra a conexão com o banco e o statement.
            MySQLConnection.CloseConnection(conn, stmt, rs);
        }
        return u;
    }
    
    /**
     * Atualiza um registro na tabela user.
     * @param u User a ser atualizado.
     */
    
    public void update(User u) {
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, u.getEmail());
            stmt.setString(2, u.getPassword());
            stmt.setString(3, u.getFullname());
            stmt.setInt(4, u.getId());
            
            //Executa a query
            int auxRetorno = stmt.executeUpdate();
            
            Logger.getLogger(UserDAO.class.getName()).log(Level.INFO, "Atualizados: {0}",
                    auxRetorno);
        }
        catch(SQLException sQLException) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, "Erro Update: {0}",
                    sQLException);
        }
        finally {
            //Encerra a conexão com o banco e o statement.
            MySQLConnection.CloseConnection(conn, stmt);
        }
    }
    
    /**
     * Exclui um user com base no ID fornecido.
     * @param id identicação do user.
     */
    
    public void delete(int id) {
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, id);
            
            //Executa a query
            int auxRetorno = stmt.executeUpdate();
            
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null,
                    "Delete: " + auxRetorno);
        }
        catch(SQLException sQLException) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null,
                    sQLException);
        }
        finally {
            //Encerra a conexão com o banco e o statement.
            MySQLConnection.CloseConnection(conn, stmt);
        }
    }
     
    
    public User checkLogin(String email, String password) {
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        User u = null;

        try {
            stmt = conn.prepareStatement("SELECT * FROM user WHERE email = ? AND password = ?");
            stmt.setString(1, email);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

            
                while (rs.next()) {
                    u = new User();
                    u.setId(rs.getInt("id"));
                    u.setEmail(rs.getString("email"));
                    u.setPassword(rs.getString("password"));
                    u.setFullname(rs.getString("fullname"));
                    
                    Logger.getLogger(UserDAO.class.getName()).log(Level.INFO, "Usuário encontrado");
                }
            
        }
            catch (SQLException sQLException) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null,
                    sQLException);
        }
            finally {
            //Encerra a conexão com o banco e o statement.
            MySQLConnection.CloseConnection(conn, stmt, rs);
        }
        
        return u;
    }
} 
       
