package servlets;

import clasesVO.UsuarioVO;
import clasesDAO.UsuarioDAO;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		try { 
			if ((usuario != null) && (!usuario.trim().equals("")) && 
			 (password != null) && (!password.trim().equals("")) ) {
			  
			  UsuarioDAO usuarioDAO = new UsuarioDAO();
			  // Comprobar si existe el usuario en la base de datos y tiene vinculada su contraseña
			  UsuarioVO usuarioEncontrado = usuarioDAO.obtenerUsuarioPorNombre(usuario);
			                  
			  if(usuarioEncontrado == null) {
			      response.getWriter().write("usuario encontrado null");
			  }
			
			  
			  if (usuarioEncontrado != null && usuarioEncontrado.getNombreUsuario().equals("admin") && usuarioEncontrado.getPassword().equals("admin")) {
				  request.getSession().setAttribute("usuario", usuarioEncontrado);
					
			      // Redirigir al inicio
			      RequestDispatcher dispatcher = request.getRequestDispatcher("/views/jsp/inicioAdmin.jsp");
			      dispatcher.forward(request, response);
			  }
			  // Comprobar si el usuario existe y la contraseña coincide
			  else if (usuarioEncontrado != null && usuarioEncontrado.getPassword().equals(password)) {
			      // Usuario y contraseña son correctos
			      // Guardar el objeto UsuarioVO en la sesión
			      request.getSession().setAttribute("usuario", usuarioEncontrado);
			
			      // Redirigir al inicio
			      RequestDispatcher dispatcher = request.getRequestDispatcher("/views/jsp/inicio.jsp");
			      dispatcher.forward(request, response);
			  } else {
			      // Usuario no existe o la contraseña no coincide
			      response.getWriter().write("Usuario o contraseña incorrectos");
			  }
			} else {
			  response.getWriter().write("Nombre de usuario o contraseña no pueden estar vacíos");
			}
		} catch (Exception e) { 
			e.printStackTrace();
			response.getWriter().write("Error en el servidor");
		}
    }

}