package com.demo.common.util.mail;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.demo.common.util.Config;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 文本邮件服务类.
 * <p/>
 * 多线程群发文本邮件.
 */
public class MailService {

	protected static Log log = LogFactory.getLog(MailService.class);

	private int threadPoolCount = 5;

	private String ENCODING;

	private JavaMailSender mailSender;

	private Template template;

	private Configuration configuration;

	private Executor executor = Executors.newFixedThreadPool(threadPoolCount);

	public void setENCODING(String encoding) {
		ENCODING = encoding;
	}

	public void setConfiguration(Configuration configuration) throws IOException {
		this.configuration = configuration;
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	/**
	 * 发送纯文本的邮件.
	 * <p/>
	 * 用Executor多线程群发邮件.
	 */
	public void sendSimpleMail(String subject, String body, String[] receiver) {
		String from = Config.getProperty("mail.sender.from");
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom(from);
		msg.setTo(receiver);
		msg.setSubject(subject);
		msg.setText(body);

		executor.execute(new MailTask(mailSender, msg));
	}

	public void sendSimpleMail2(String internetAddress, String subject, String body, String[] receiver) {
		MimeMessage msg = mailSender.createMimeMessage();
		String from = Config.getProperty("mail.sender.from");

		try {
			InternetAddress ia = new InternetAddress(from);
			ia.setPersonal(internetAddress, ENCODING);
			MimeMessageHelper helper = new MimeMessageHelper(msg, true, ENCODING);
			helper.setTo(receiver);
			helper.setFrom(ia);
			helper.setSubject(subject);
			helper.setText(body, true);
		} catch (MessagingException e) {
			log.error("构造邮件失败", e);
		} catch (UnsupportedEncodingException e) {
			log.error("编码错误", e);
		}
		executor.execute(new MailTask(mailSender, msg));
	}

	public void sendTextMail(String internetAddress, String subject, String[] receiver, String body) {
		MimeMessage msg = mailSender.createMimeMessage();
		String from = Config.getProperty("mail.sender.from");
		try {
			InternetAddress ia = new InternetAddress(from);
			ia.setPersonal(internetAddress, ENCODING);
			MimeMessageHelper helper = new MimeMessageHelper(msg, true, ENCODING);
			helper.setTo(receiver);
			helper.setFrom(ia);
			helper.setSubject(subject);
			helper.setText(body, false);
		} catch (MessagingException e) {
			log.error("构造邮件失败", e);
		} catch (UnsupportedEncodingException e) {
			log.error("编码错误", e);
		}
		executor.execute(new MailTask(mailSender, msg));
	}

	public void sendMIMEMail(String internetAddress, String subject, String body, String[] receiver) {

		try {
			String from = Config.getProperty("mail.sender.from");
			MimeMessage msg = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(msg, true, ENCODING);
			InternetAddress ia = new InternetAddress(from);
			ia.setPersonal(internetAddress, ENCODING);
			helper.setFrom(ia);
			helper.setTo(receiver);
			helper.setSubject(subject);
			helper.setText(body, true);
			executor.execute(new MailTask(mailSender, msg));
		} catch (Exception ex) {

		}
	}

	public void sendMIMEMail(String subject, String body, String[] receiver) {

		try {
			//String from = Config.getProperty("mail.sender.from");
			MimeMessage msg = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(msg, true, ENCODING);
			//InternetAddress ia = new InternetAddress(from);
			//helper.setFrom(ia);
			helper.setTo(receiver);
			helper.setSubject(subject);
			helper.setText(body, true);
			executor.execute(new MailTask(mailSender, msg));
		} catch (Exception ex) {

		}
	}

	/**
	 * 发送MIME格式的邮件.
	 * <p/>
	 * 用Executor多线程群发邮件.
	 */
	public void sendMIMEMail(String internetAddress, String subject, String[] receiver, HashMap<String, String> replaceKV, String templatePath) {

		MimeMessage msg = mailSender.createMimeMessage();
		String from = Config.getProperty("mail.sender.from");
		try {
			InternetAddress ia = new InternetAddress(from);
			ia.setPersonal(internetAddress, ENCODING);
			MimeMessageHelper helper = new MimeMessageHelper(msg, true, ENCODING);
			helper.setTo(receiver);
			helper.setFrom(ia);
			helper.setSubject(subject);
			buildContent(helper, replaceKV, templatePath);
		} catch (MessagingException e) {
			log.error("构造邮件失败", e);
		} catch (UnsupportedEncodingException e) {
			log.error("编码错误", e);
		}
		executor.execute(new MailTask(mailSender, msg));
	}

	/**
	 * 使用Freemarker生成html格式内容.
	 */
	public void buildContent(MimeMessageHelper helper, HashMap<String, String> replaceKV, String templatePath) throws MessagingException {

		try {
			HashMap<String, String> context = replaceKV;
			template = configuration.getTemplate(templatePath, ENCODING);
			String content = FreeMarkerTemplateUtils.processTemplateIntoString(template, context);
			helper.setText(content, true);
		} catch (IOException e) {
			log.error("构造邮件失败,FreeMarker模板不存在", e);
		} catch (TemplateException e) {
			log.error("构造邮件失败,FreeMarker处理失败", e);
		}
	}

	/**
	 * 添加附件.
	 */
//	public void buildAttachment(MimeMessageHelper helper) throws MessagingException {
//		try {
//			ClassPathResource attachment = new ClassPathResource("/email/mailAttachment.txt");
//			helper.addAttachment("mailAttachment.txt", attachment.getFile());
//		} catch (IOException e) {
//			log.error("构造邮件失败,附件文件不存在", e);
//		}
//	}

	/**
	 * 发送MIME格式的邮件.
	 * <p/>
	 * 用Executor多线程群发邮件.
	 */
	public void sendMIMEMail(String internetAddress, String subject, String[] receiver, String content) {

		MimeMessage msg = mailSender.createMimeMessage();
		String from = Config.getProperty("mail.sender.from");
		try {
			InternetAddress ia = new InternetAddress(from);
			ia.setPersonal(internetAddress, ENCODING);
			MimeMessageHelper helper = new MimeMessageHelper(msg, true, ENCODING);
			helper.setTo(receiver);
			helper.setFrom(ia);
			helper.setSubject(subject);
			helper.setText(content, true);

		} catch (MessagingException e) {
			log.error("构造邮件失败", e);
		} catch (UnsupportedEncodingException e) {
			log.error("编码错误", e);
		}
		executor.execute(new MailTask(mailSender, msg));
	}

	/**
	 * 群发邮件任务类.
	 */
	private static class MailTask implements Runnable {

		private JavaMailSender mailSender;
		private Object msg;

		public MailTask(JavaMailSender mailSender, Object msg) {
			this.mailSender = mailSender;
			this.msg = msg;
		}

		public void run() {

			if (msg instanceof SimpleMailMessage) {
				try {
					mailSender.send((SimpleMailMessage) msg);
					log.info("纯文本邮件已发送至" + ((SimpleMailMessage) msg).getTo());
				} catch (MailException e) {
					log.error("发送邮件失败", e);
				}
			} else if (msg instanceof MimeMessage) {
				try {
					mailSender.send((MimeMessage) msg);
					log.info("MIME邮件已发送成功");
				} catch (MailException e) {
					log.error("发送邮件失败", e);
				}
			}
		}
	}
}
