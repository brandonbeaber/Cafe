<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>
<%@page import="javax.activation.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@page import="javax.mail.*"%>
<%@page import="javax.mail.internet.*"%>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%

      // Recipient's email is constant.
      final String to = "cafetrexomaha@gmail.com";
	  final String pass = "Eagles337";

      // Sender's email needs to be mentioned
      String from = request.getParameter("email");
      
      // Sender's subject needs to be mentioned
      String subject = request.getParameter("subject");
      
      // Sender's message needs to be mentioned
      String theMessage = request.getParameter("message");

      // Assuming you are sending email from localhost
      String host = "smtp.gmail.com";/*localhost*/
      
      
      Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
      
		/*
		// Get system properties
      Properties properties = System.getProperties();

      // Setup mail server
      properties.setProperty("mail.smtp.host", host);
      */

      // Get the default Session object.
      Session sess = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(to, pass);
			}
		  });
      
            

      try{
         // Create a default MimeMessage object.
         MimeMessage message = new MimeMessage(sess);

         // Set From: 
         message.setFrom(new InternetAddress(from));

         // Set To: 
         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

         // Set Subject:
         message.setSubject(subject);
         
         message.setSentDate(new Date());

         // Now set the actual message
         message.setText(theMessage);

         // Send message
         Transport.send(message);
         System.out.println("Sent message successfully....");
         response.sendRedirect("contact.html");
      }catch (MessagingException mex) {
         mex.printStackTrace();
      }
%>




</body>
</html>