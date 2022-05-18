package local.lmff.bookstoreweb.model.dao;

import connection.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import local.lmff.bookstoreweb.model.bean.Book;

/**
 *
 * @author devsys-b
 */
public class BookDAO {
    
    Book b = new Book();
    
    private static final String SQL_INSERT = "INSERT INTO book(titulo, autor, "
            + "preco)" + "VALUES (?, ?, ?)";
    
    private static final String SQL_SELECT_ALL = "SELECT * FROM book";
    private static final String SQL_SELECT_ID = "SELECT * FROM book " 
            + "WHERE id = ?";
    
    private static final String SQL_UPDATE = "UPDATE book SET titulo = ?," +
        "autor = ?, preco = ? WHERE id = ?";
    
    private static final String SQL_DELETE = "DELETE FROM book WHERE id = ?";
    
    /**
     * insere um usuário na base de dados Book
     * @param b
     */
    
    public void create(Book b) {
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, b.getTitulo());
            stmt.setString(2, b.getAutor());
            stmt.setDouble(3, b.getPreco());
            
            //executa a querry
            int auxRetorno = stmt.executeUpdate();
            
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null,
                    "inclusão" + auxRetorno);
            
        }
        catch(SQLException sQLException) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null,
                    sQLException);
        }
        finally {
            //encerra a conexão com o BD
            MySQLConnection.CloseConnection(conn, stmt);
        }
    }
    
    /**
     * Retorna todos os registros da tabela book
     * @return
     */
    
    public List<Book> getResults() {
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Book b = null;
        List<Book> listaBooks = null;
        
        //Prepara a String de SELECT e executa a query.
        try {
            stmt = conn.prepareStatement(SQL_SELECT_ALL);
            rs = stmt.executeQuery();
            
            //Carrega os dados de ResultSet rs, converte em Book e adiciona
            //na lista de retorno
            
            listaBooks = new ArrayList<>();
            
            while (rs.next()) {
                b = new Book();
                b.setId(rs.getInt("id"));
                b.setTitulo(rs.getString("titulo"));
                b.setAutor(rs.getString("autor"));
                b.setPreco(rs.getDouble("preco"));
                listaBooks.add(b);
            }
            
        }
        catch(SQLException sQLException) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null,
                    sQLException);
        }
        return listaBooks;
    }
    
    /**
     * Retorna um book da tabela book
     * @param id Identificação do Book
     * @return
     */
    
    public Book getResultsById(int id) {
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Book b = null;
        
        try {
            stmt = conn.prepareStatement(SQL_SELECT_ID);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                b = new Book();
                b.setId(rs.getInt("id"));
                b.setTitulo(rs.getString("titulo"));
                b.setAutor(rs.getString("autor"));
                b.setPreco(rs.getDouble("preco"));
            }
        }
        catch (SQLException sQLException) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null,
                    sQLException);
        }
        finally {
            //Encerra a conexão com o banco e o statement.
            MySQLConnection.CloseConnection(conn, stmt, rs);
        }
        return b;
    }
    
    /**
     * Atualiza um registro na tabela book.
     * @param b Book a ser atualizado.
     */
    
    public void update(Book b) {
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, b.getTitulo());
            stmt.setString(2, b.getAutor());
            stmt.setDouble(3, b.getPreco());
            stmt.setInt(4, b.getId());
            
            //Executa a query
            int auxRetorno = stmt.executeUpdate();
            
            Logger.getLogger(BookDAO.class.getName()).log(Level.INFO, "Atualizados: {0}",
                    auxRetorno);
        }
        catch(SQLException sQLException) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, "Erro Update: {0}",
                    sQLException);
        }
        finally {
            //Encerra a conexão com o banco e o statement.
            MySQLConnection.CloseConnection(conn, stmt);
        }
    }
    
    /**
     * Exclui um book com base no ID fornecido.
     * @param id identicação do book.
     */
    
    public void delete(int id) {
        Connection conn = MySQLConnection.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, id);
            
            //Executa a query
            int auxRetorno = stmt.executeUpdate();
            
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null,
                    "Delete: " + auxRetorno);
        }
        catch(SQLException sQLException) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null,
                    sQLException);
        }
        finally {
            //Encerra a conexão com o banco e o statement.
            MySQLConnection.CloseConnection(conn, stmt);
        }
    }
}
