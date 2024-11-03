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
			  
			  // Comprobar si existe el usuario en la base de datos y tiene vinculada su contraseña
			  UsuarioVO usuarioEncontrado = UsuarioDAO.obtenerUsuarioPorNombre(usuario);
			                  
			  if(usuarioEncontrado == null) {
				  response.getWriter().write("<script>alert('Usuario no encontrado'); window.location.href = '" + request.getContextPath() + "/views/jsp/login.jsp';</script>");
			  }
			
			  
			  if (usuarioEncontrado != null && usuarioEncontrado.getNombreUsuario().equals("admin") && usuarioEncontrado.getPassword().equals("admin")) {
				  request.getSession().setAttribute("usuario", usuarioEncontrado);
					
			      // Redirigir al inicio
			      RequestDispatcher dispatcher = request.getRequestDispatcher("/views/jsp/admin/inicioAdmin.jsp");
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
			      response.getWriter().write("<script>alert('Usuario o contraseña incorrectos');window.location.href = '" + request.getContextPath() + "/views/jsp/login.jsp';</script>");
			  }
			} else {
			  response.getWriter().write("alert('Nombre de usuario o contraseña no pueden estar vacíos'); <script>window.location.href = '" + request.getContextPath() + "/views/jsp/login.jsp';</script>");
			}
		} catch (Exception e) { 
			e.printStackTrace();
			  response.getWriter().write("<script>alert('Error en el servidor'); window.location.href = '" + request.getContextPath() + "/views/jsp/login.jsp';</script>");
		}
    }

}