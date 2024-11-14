package servlets;

import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

@WebServlet("/MandarCorreoAyudaContactoServlet")
public class MandarCorreoAyudaContactoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String correo = request.getParameter("correo");
        String mensaje = request.getParameter("mensaje");

        // Verificar si los campos son válidos
        if (nombre == null || correo == null || mensaje == null || nombre.isEmpty() || correo.isEmpty() || mensaje.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/views/jsp/ayuda_contacto.jsp?event=Todos los campos son obligatorios");
            return;
        }

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
            Message emailMessage = new MimeMessage(session);
            emailMessage.setFrom(new InternetAddress(username));
            emailMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse("baskettracker@gmail.com"));
            emailMessage.setSubject("Contacto de ayuda - Basketracker");

            String contenido = "Nombre: " + nombre + "\n" +
                               "Correo: " + correo + "\n\n" +
                               "Mensaje: " + mensaje;

            emailMessage.setText(contenido);

            // Enviar el mensaje
            Transport.send(emailMessage);
            response.sendRedirect(request.getContextPath() + "/views/jsp/ayuda_contacto.jsp?event=El correo ha sido enviado con éxito");
        } catch (MessagingException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/views/jsp/ayuda_contacto.jsp?event=Error al enviar el correo");
        }
    }
}
