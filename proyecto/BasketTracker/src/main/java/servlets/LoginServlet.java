package servlets;

import clasesVO.UsuarioVO;
import clasesDAO.UsuarioDAO;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//@WebServlet(description = "Servlet de autenticación del usuario", urlPatterns = { "/LoginServlet" })
//@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public LoginServlet() {
    	super();
    }
    
    protected void doPost(HttpServletRequest request, 
    		HttpServletResponse response) throws IOException, ServletException {
        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");
        System.out.println("Holaaaa");
        try { 
        	if ((usuario != null) && (!usuario.trim().equals("")) && 
        	   (password != null) && (!password.trim().equals("")) ) {
        		
        		UsuarioDAO usuarioDAO = new UsuarioDAO();
        		
        		// Comprobar si existe el usuario en la base de datos y tiene vinculada su contraseña
                UsuarioVO usuarioEncontrado = usuarioDAO.obtenerUsuarioPorNombre(usuario);
                
                // Comprobar si el usuario existe y la contraseña coincide
                if (usuarioEncontrado != null && usuarioEncontrado.getPassword().equals(password)) {
                    // El usuario y la contraseña son correctos
                	RequestDispatcher dispatcher = request.getRequestDispatcher("hello.jsp");
                    dispatcher.forward(request, response);
                } else {
                    // El usuario no existe o la contraseña no coincide
                    response.getWriter().write("Usuario o contraseña incorrectos");
                }
        	} 
        	else{
                response.getWriter().write("Nombre de usuario o contraseña no pueden estar vacíos");
        	}
        } catch (Exception e) { 
        	e.printStackTrace();
            response.getWriter().write("Error en el servidor");
        }
    }
}

