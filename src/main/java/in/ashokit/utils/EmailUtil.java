package in.ashokit.utils;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtil {
	private JavaMailSender mailSender;
	public EmailUtil(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	public boolean senEmail(String subject, String body, String email) {
		boolean isSent = false;
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
			helper.setTo(email);
			helper.setSubject(subject);
			helper.setText(body, true);
			mailSender.send(mimeMessage);
			isSent = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return isSent;
	}
}
