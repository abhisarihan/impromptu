//package com.impromptu.email;
//
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.FileSystemXmlApplicationContext;
//
//public class MailServiceTest {
//	public static void main(String[] args) {
//		String fileName = "C://Users//Abhishek//Documents//workspace-sts-2.8.1.RELEASE//Impromptu//src//main//webapp//WEB-INF//mail.xml";
//        ApplicationContext context = new FileSystemXmlApplicationContext(fileName);
//        MailService mailService = (MailService) context.getBean("mailService");
//        mailService.sendMail("abhisarihan@gmail.com", "abhisarihan@gmail.com", "Testing123", "Testing only \n\n Hello Spring Email Sender");
//        mailService.sendAlertMail("Exception occurred");
//    }
//
//}
