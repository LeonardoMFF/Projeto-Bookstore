/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package local.lmff.bookstoreweb.controller;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import local.lmff.bookstoreweb.model.bean.User;

/**
 *
 * @author devsys-b
 */

@WebFilter(filterName = "AutorizaUserFilter", urlPatterns = {"/bstore/*", "/bsuser/*"})
public class AutorizaUserFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Logger.getLogger(AutorizaUserFilter.class.getName()).log(Level.INFO,
        "AltorizaFilter iniciado!");
    }
    
    @Override
    public void destroy() {
        Logger.getLogger(AutorizaUserFilter.class.getName()).log(Level.INFO,
        "AltorizaUserFilter destruido!");
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
    FilterChain chain)
    throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        
        //carrega a session caso exista
        HttpSession session = httpRequest.getSession(false);
        boolean isUsuarioLogado = (session != null && session.getAttribute("user") != null);
        
        if (isUsuarioLogado) {
            //tudo ok! Usuário com session autorizado e segue requesição.
            User userLogado = (User) session.getAttribute("user");
            Logger.getLogger(UserLogoutServlet.class.getName()).log(Level.INFO,
                "Usuário autentificado: {0}", userLogado.getEmail());
            
            Logger.getLogger(UserLogoutServlet.class.getName()).log(Level.INFO,
                "Carrega próximo Filtro ou Servlet - chain.doFilter");
            
            chain.doFilter(request, response);
        }
        else {
            //Ops... usuario não autentificado: Redirecionar para página de login
            Logger.getLogger(UserLogoutServlet.class.getName()).log(Level.INFO,
                "Usuario NAO autentificado: ");
            
            RequestDispatcher dispatcher =  request.getRequestDispatcher("/loginPage.jsp");
            dispatcher.forward(request, response);
        }
        Logger.getLogger(UserLogoutServlet.class.getName()).log(Level.INFO,
                " *** Pos Filtro *** ");
    }
}

