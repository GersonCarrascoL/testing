package pe.edu.unmsm.quipucamayoc.util;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

public class DJCorreoTexto {
	private static Logger logger = Logger.getLogger(DJCorreoTexto.class);

	
	public void enviarCompromisoPagoCorreo(String para, String asunto, String mensaje, byte[] pdf) {

		// La configuración para enviar correo
		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.user", Constantes.correoEnvia);
		properties.put("mail.password", Constantes.claveCorreo);

		Session session = Session.getInstance(properties, null);

		try {
			MimeMessage mimeMessage = new MimeMessage(session);

			mimeMessage.setFrom(new InternetAddress(Constantes.correoEnvia, "Quipucamayoc"));

			InternetAddress[] internetAddresses = { new InternetAddress(para) };

			mimeMessage.setRecipients(Message.RecipientType.TO, internetAddresses);

			mimeMessage.setSubject(asunto);

			MimeBodyPart mimeBodyPart = new MimeBodyPart();
	 		mimeBodyPart.setText(mensaje);
			File file = new File("ComprobanteDePago" + ".pdf");
			FileUtils.writeByteArrayToFile(file, pdf);
			MimeBodyPart mimeBodyPartAdjunto = new MimeBodyPart();
			mimeBodyPartAdjunto.attachFile(file);
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(mimeBodyPart);
			multipart.addBodyPart(mimeBodyPartAdjunto);

			mimeMessage.setContent(multipart);

			Transport transport = session.getTransport("smtp");
			transport.connect(Constantes.correoEnvia, Constantes.claveCorreo);
			transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
			transport.close();

		} catch (IOException ex) {
			logger.error("Error en lectura y escritura de archivos", ex);
		} catch (MessagingException ex) {
			logger.error("Error en el envio", ex);
		}

	}
}
