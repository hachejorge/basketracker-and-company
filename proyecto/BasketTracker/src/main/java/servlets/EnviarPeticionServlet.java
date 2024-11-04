package servlets;

import clasesDAO.JugadorDAO;
import clasesVO.UsuarioVO;
import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Properties;

@WebServlet("/EnviarPeticionServlet")
public class EnviarPeticionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UsuarioVO usuario = (UsuarioVO) session.getAttribute("usuario");

        if (usuario != null && JugadorDAO.existeJugador(usuario.getNombreUsuario())) {
            String mensaje = request.getParameter("sugerenica-cambio"); // El mensaje de la solicitud 


            // Enviar el mensaje por correo
            enviarCorreoSugerencia(usuario, mensaje);
            response.setStatus(HttpServletResponse.SC_OK); // Respuesta exitosa
            response.sendRedirect(request.getContextPath() + "/views/jsp/editar_perfil.jsp");
        } else {
            response.sendRedirect(request.getContextPath() + "/views/jsp/login.jsp");
        }
    }

    private void enviarCorreoSugerencia(UsuarioVO usuario, String mensaje) {
        // Configuración de propiedades del correo
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        final String username = "baskettracker@gmail.com"; // Tu correo como remitente
        final String password = "gvdv wsvh cyxx ijok"; // Usa aquí la contraseña de aplicación si estás usando verificación en dos pasos

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Crear el mensaje
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username)); // Tu correo como remitente
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("baskettracker@gmail.com")); // Correo del destinatario
            message.setSubject("Sugerencia de " + usuario.getNombreUsuario());
            message.setText("Sugerencia enviada por: " + usuario.getCorreoElect() + "\n\n" + mensaje); // Incluir el correo del usuario en el cuerpo del mensaje

            // Enviar el mensaje
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace(); // Manejo de excepciones
        }
    }
}
