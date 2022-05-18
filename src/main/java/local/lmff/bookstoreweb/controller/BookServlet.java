/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package local.lmff.bookstoreweb.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import local.lmff.bookstoreweb.model.bean.Book;
import local.lmff.bookstoreweb.model.dao.BookDAO;

/**
 *
 * @author devsys-b
 */
public class BookServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html/charset=UTF-8");
        
        String action = request.getPathInfo();
        Logger.getLogger(BookDAO.class.getName()).log(Level.INFO,
                "Patch solicitado: {0}");
        
        if (action == null) {
            action = "/list";
        }
        
        try {
            switch(action) {
                case "/new":
                    showNewBookForm(request, response);
                    break;
                    
                case "/insert":
                    insertBookAction(request, response);
                    break;
                    
                case "/edit":
                    showEditBookForm(request, response);
                    break;
                    
                case "/update":
                    updateBookAction(request, response);
                    break;
                    
                case "/delete":
                    deleteBookAction(request, response);
                    break;
                    
                case "/list":
                    
                default:
                    listBook(request, response);
                    break;
            }
        }
        catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    
    private void listBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        
        response.setContentType("text/html;charset=UTF-8");
        
        BookDAO BookDAO = new BookDAO();
        List<Book> listaBook = BookDAO.getResults();
        
        Logger.getLogger(BookDAO.class.getName()).log(Level.INFO,
                "Total de registros: {0}", listaBook.size());
        
        request.setAttribute("listaBook", listaBook);
        
        RequestDispatcher dispatcher =  request.getRequestDispatcher("/BookList.jsp");
        dispatcher.forward(request, response);
    }
    
    private void showNewBookForm(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
        
        RequestDispatcher dispatcher =  request.getRequestDispatcher("/BookForm.jsp");
        dispatcher.forward(request, response);
        
        }
    
    private void insertBookAction(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
        
        
        
        BookDAO bookDAO = new BookDAO();
        Book novoBook = new Book();
        novoBook.setTitulo(request.getParameter("formTitulo"));
        novoBook.setAutor(request.getParameter("formAutor"));
        novoBook.setPreco(Double.parseDouble(request.getParameter("formPreco")));
        
        bookDAO.create(novoBook);
        response.sendRedirect("list");
    }
    
    private void showEditBookForm(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        BookDAO bookDAO = new BookDAO();
        Book atualizaBook = bookDAO.getResultsById(id);
        RequestDispatcher dispatcher =  request.getRequestDispatcher("/BookForm.jsp");
        request.setAttribute("book", atualizaBook);
        dispatcher.forward(request, response);
    }
    
    private void updateBookAction(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
        
        
        BookDAO bookDAO = new BookDAO();
        Book bookAtualizado = new Book();
        
        bookAtualizado.setId(Integer.parseInt(request.getParameter("formId")));
        
        bookAtualizado.setTitulo(request.getParameter("formTitulo"));
        bookAtualizado.setAutor(request.getParameter("formAutor"));
        bookAtualizado.setPreco(Double.parseDouble(request.getParameter("formPreco")));
        
        bookDAO.update(bookAtualizado);
        response.sendRedirect("list");
    }
    
    private void deleteBookAction(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
        
        int id = Integer.parseInt(request.getParameter("id"));

        BookDAO bookDAO = new BookDAO();
        bookDAO.delete(id);
        response.sendRedirect("list");
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
