package servlets;

import clasesDAO.UsuarioDAO;
import clasesVO.UsuarioVO;
import clasesDAO.TokenDAO;
import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

@WebServlet("/MandarCorreoCambioContraseñaServlet")
public class MandarCorreoCambioContraseñaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String correo = request.getParameter("correo");

        // Verificar si el correo existe en la base de datos
        UsuarioVO usuario = UsuarioDAO.obtenerUsuarioPorCorreo(correo);

        if (usuario != null) {
            // Generar un token único para el restablecimiento de la contraseña
            String token = generarTokenUnico();
            // Guardar el token en la base de datos o en la sesión, dependiendo de tu implementación
           TokenDAO.guardarTokenRestablecimiento(usuario.getNombreUsuario(), token);

            // Enviar correo de restablecimiento
            enviarCorreoRestablecimiento(correo, token);
            response.setStatus(HttpServletResponse.SC_OK); // Respuesta exitosa
            response.getWriter().write("<script>alert('El correo ha sido mandado');</script>");
            response.sendRedirect(request.getContextPath() + "/views/jsp/login.jsp?event=El correo ha sido mandado");
            
        } else {
            // Si el correo no existe, redirigir a una página de error
        	 response.sendRedirect(request.getContextPath() + "/views/jsp/recuperar_password.jsp?event=El correo no esta registrado");
        }
    }

    private void enviarCorreoRestablecimiento(String correo, String token) {
        // Configuración de propiedades del correo
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        final String username = "baskettracker@gmail.com"; // Correo remitente
        final String password = "gvdv wsvh cyxx ijok"; // Contraseña de aplicación

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Crear el mensaje
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(correo));
            message.setSubject("Restablecimiento de contraseña - Basketracker");

            String urlRestablecimiento = "http://localhost:8081/BasketTracker/views/jsp/restablecer_contraseña.jsp?token=" + token;
            message.setText("Hola, \n\nPara restablecer tu contraseña, por favor haz clic en el siguiente enlace:\n" + urlRestablecimiento + ".\nEl token es: " + token + "\n\nSi no solicitaste este cambio, ignora este correo.");

            // Enviar el mensaje
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private String generarTokenUnico() {
        // Generar un token único (puedes personalizar este método)
        return java.util.UUID.randomUUID().toString();
    }
}
