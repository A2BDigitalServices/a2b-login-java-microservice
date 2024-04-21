package com.todo.backend.services;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import com.todo.backend.entities.ImageData;
import com.todo.backend.entities.MailRequest;
import com.todo.backend.entities.MailResponse;
import com.todo.backend.util.ImageUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender sender;
	
	@Autowired
	private Configuration config;
	
	@Autowired
	private StorageService storageService;
	
	public MailResponse sendEmail(MailRequest request, Map<String, Object> model, String caseId) {
		MailResponse response = new MailResponse();
		MimeMessage message = sender.createMimeMessage();
		try {
			// set mediaType
			MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
					StandardCharsets.UTF_8.name());
			// add attachment
			helper.addAttachment("A2BDigitalService_document1.pdf", new ClassPathResource("A2BDigitalService_document1.pdf"));
			helper.addAttachment("A2BDigitalServices_document2.jpg", new ClassPathResource("A2BDigitalServices_document2.jpg"));

			Template t = config.getTemplate("email-template-applied.ftl");
			String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

			helper.setTo(request.getTo());
			helper.setText(html, true);
			helper.setSubject("Your loan application submitted successfully.Here is reference number: " + caseId);
			helper.setFrom(request.getFrom());
			sender.send(message);

			response.setMessage("mail send to : " + request.getTo());
			response.setStatus(Boolean.TRUE);

		} catch (MessagingException | IOException | TemplateException e) {
			response.setMessage("Mail Sending failure : "+e.getMessage());
			response.setStatus(Boolean.FALSE);
		}

		return response;
	}
	
	public MailResponse sendEmailWhenProgress(MailRequest request, Map<String, Object> model, String caseId) {
		MailResponse response = new MailResponse();
		MimeMessage message = sender.createMimeMessage();
		try {
			// set mediaType
			MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
					StandardCharsets.UTF_8.name());
			// add attachment
			helper.addAttachment("A2BDigitalService_document1.pdf", new ClassPathResource("A2BDigitalService_document1.pdf"));
			helper.addAttachment("A2BDigitalServices_document2.jpg", new ClassPathResource("A2BDigitalServices_document2.jpg"));

			Template t = config.getTemplate("email-template-progress.ftl");
			String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

			helper.setTo(request.getTo());
			helper.setText(html, true);
			helper.setSubject("Great! Your loan application is in Progress. Here is reference number: " + caseId);
			helper.setFrom(request.getFrom());
			sender.send(message);

			response.setMessage("mail send to : " + request.getTo());
			response.setStatus(Boolean.TRUE);

		} catch (MessagingException | IOException | TemplateException e) {
			response.setMessage("Mail Sending failure : "+e.getMessage());
			response.setStatus(Boolean.FALSE);
		}

		return response;
	}
	
//	public MailResponse sendEmailToThirdPartyImpl(MailRequest request, Map<String, Object> model, String caseId,List<ImageData> files) {
//		MailResponse response = new MailResponse();
//		MimeMessage message = sender.createMimeMessage();
//		try {
//			// set mediaType
//			MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
//					StandardCharsets.UTF_8.name());
//			// add attachment
//			helper.addAttachment("A2BDigitalService_document1.pdf", new ClassPathResource("A2BDigitalService_document1.pdf"));
//			helper.addAttachment("A2BDigitalServices_document2.jpg", new ClassPathResource("A2BDigitalServices_document2.jpg"));
//
//			Template t = config.getTemplate("email-template-progress.ftl");
//			String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
//
//			helper.setTo(request.getTo());
//			helper.setText(html, true);
//			helper.setSubject("Great! Your loan application is in Progress. Here is reference number: " + caseId);
//			helper.setFrom(request.getFrom());
//			sender.send(message);
//
//			response.setMessage("mail send to : " + request.getTo());
//			response.setStatus(Boolean.TRUE);
//
//		} catch (MessagingException | IOException | TemplateException e) {
//			response.setMessage("Mail Sending failure : "+e.getMessage());
//			response.setStatus(Boolean.FALSE);
//		}
//
//		return response;
//	}
	
	public MailResponse sendEmailToThirdParty(MailRequest request, Map<String, Object> model, String caseId) {
		List<ImageData> files = storageService.getAllDocuments(caseId);
		return sendEmailToThirdPartyImpl(request,model,caseId,files);
	}
	
	public MailResponse sendEmailToThirdPartyImpl(MailRequest request, Map<String, Object> model, String caseId, List<ImageData> files) {
	    MailResponse response = new MailResponse();
	    MimeMessage message = sender.createMimeMessage();
	    try {
	        MimeMessageHelper helper = new MimeMessageHelper(message, true, StandardCharsets.UTF_8.name());
	        Template t = config.getTemplate("email-template-progress.ftl");
	        String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

	        helper.setTo(request.getTo());
	        helper.setText(html, true);
	        helper.setSubject("New Case " + caseId + " From A2B Digital Service");
	        helper.setFrom(request.getFrom());
	        
	        // Attach files
	        for (ImageData file : files) {
	            byte[] decryptedData = ImageUtils.decompressImage(file.getImageData());
	            ByteArrayResource resource = new ByteArrayResource(decryptedData);
	            helper.addAttachment(file.getName(), resource);
	        }
	        
	        sender.send(message);

	        response.setMessage("Mail sent to: " + request.getTo());
	        response.setStatus(Boolean.TRUE);
	    } catch (MessagingException | IOException | TemplateException e) {
	        response.setMessage("Mail Sending failure: " + e.getMessage());
	        response.setStatus(Boolean.FALSE);
	    }
	    return response;
	}
}
