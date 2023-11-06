package com.woof.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailService{

	// 設定傳送郵件:至收信人的Email信箱,Email主旨,Email內容
	public void sendMail(String to, String subject, String messageText) {

		try {
			// 設定使用SSL連線至 Gmail smtp Server
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");

			final String myGmail = "cha103woof@gmail.com";
			final String myGmail_password = "ookbpywobsweyrmw";
			Session session = Session.getInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(myGmail, myGmail_password);
				}
			});

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myGmail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

			// 設定信中的主旨
			message.setSubject(subject);
			// 設定信中的內容
			message.setContent(html(), "text/html; charset=utf-8");

			Transport.send(message);
			System.out.println("傳送成功!");
		} catch (MessagingException e) {
			System.out.println("傳送失敗!");
			e.printStackTrace();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String args[]) {

		String to = "trick95710@gmail.com";

		String subject = "密碼通知";

		String ch_name = "peter1";
		String passRandom = "111";
		String messageText = "Hello! " + ch_name + " 請謹記此密碼: " + passRandom + "\n" + " (已經啟用)";

		MailService mailService = new MailService();

		mailService.sendMail(to, subject, messageText);
	}

	private String html() throws IOException {

		String html = "<!DOCTYPE >\n" +
				"<html>\n" +
				"  <head>\n" +
				"    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
				"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
				"    <title></title>\n" +
				"    <style>\n" +
				"      * {\n" +
				"        font-family: Microsoft JhengHei, PMingLiU, sans-serif;\n" +
				"      }\n" +
				"    </style>\n" +
				"  </head>\n" +
				"\n" +
				"  <body >\n" +
				"    <table\n" +
				"      align=\"center\"\n" +
				"      cellpadding=\"0\"\n" +
				"      cellspacing=\"0\"\n" +
				"      width=\"100%\"" +
				"      style=\"table-layout: fixed;background-color: white;\"\n" +
				"    >\n" +
				"      <tr>\n" +
				"        <td style=\"padding: 20px\" align=\"center\">\n" +
				"          <img src=\"data:image/png;base64,"+imgBase64()+"\" width=\"50%\" />\n" +
				"        </td>\n" +
				"      </tr>\n" +
				"      <tr>\n" +
				"        <td height=\"200\" width=\"450\">\n" +
				"          <table\n" +
				"            cellpadding=\"0\"\n" +
				"            cellspacing=\"0\"\n" +
				"            width=\"100%\"\n" +
				"            height=\"50\"\n" +
				"            style=\"padding: 20px\"\n" +
				"          >\n" +
				"            <tr\n" +
				"              height=\"60\"\n" +
				"              style=\"color: rgb(240, 98, 46); font-weight: 800; font-size: 30px\"\n" +
				"            >\n" +
				"              <td align=\"center\">團體課程報名</td>\n" +
				"            </tr>\n" +
				"            <tr height=\"60\">\n" +
				"              <td\n" +
				"                align=\"center\"\n" +
				"                style=\"\n" +
				"                  color: rgb(240, 98, 46);\n" +
				"                  font-weight: 900;\n" +
				"                  font-size: 30px;\n" +
				"                \"\n" +
				"              >\n" +
				"                報名成功 !!!\n" +
				"              </td>\n" +
				"            </tr>\n" +
				"            <tr height=\"60\">\n" +
				"              <td\n" +
				"                align=\"center\"\n" +
				"                style=\"\n" +
				"                  color: rgb(240, 98, 46);\n" +
				"                  font-weight: 900;\n" +
				"                  font-size: 30px;\n" +
				"                \"\n" +
				"              >\n" +
				"                報名人姓名：小華\n" +
				"              </td>\n" +
				"            </tr>\n" +
				"            <tr height=\"60\">\n" +
				"              <td\n" +
				"                align=\"center\"\n" +
				"                style=\"\n" +
				"                  color: rgb(240, 98, 46);\n" +
				"                  font-weight: 800;\n" +
				"                  font-size: 30px;\n" +
				"                \"\n" +
				"              >\n" +
				"                課程名稱：成犬班\n" +
				"              </td>\n" +
				"            </tr>\n" +
				"            <tr height=\"200\">\n" +
				"              <td\n" +
				"                style=\"color: rgb(240, 98, 46); font-weight: 800\"\n" +
				"                align=\"center\"\n" +
				"              >\n" +
				"                上課時間\n" +
				"                <div>2022-01-11</div>\n" +
				"                <div>2022-01-11</div>\n" +
				"                <div>2022-01-11</div>\n" +
				"                <div>2022-01-11</div>\n" +
				"              </td>\n" +
				"            </tr>\n" +
				"            <tr>\n" +
				"              <td>\n" +
				"                <p style=\"color: rgb(240, 98, 46); font-weight: 900\">\n" +
				"                  課程內容：\n" +
				"                </p>\n" +
				"                <p style=\"color: rgb(240, 98, 46); font-weight: 900\">\n" +
				"                  幼犬初級訓練課程：這個課程專為幼犬設計，旨在建立基本的服從和行為。課程包括基本指令的教學，如坐下、待命、走路，以及社交化訓練，讓您的幼犬學會與其他狗狗和人相處。\n" +
				"                </p>\n" +
				"              </td>\n" +
				"            </tr>\n" +
				"          </table>\n" +
				"        </td>\n" +
				"      </tr>\n" +
				"      <tr>\n" +
				"        <td\n" +
				"          style=\"\n" +
				"            background-color: rgb(240, 98, 46);\n" +
				"            color: white;\n" +
				"            font-weight: 900;\n" +
				"          \"\n" +
				"          height=\"150\"\n" +
				"          align=\"center\"\n" +
				"        >\n" +
				"          <div>寵毛導師 Woof</div>\n" +
				"          <div>連絡電話：03 425 1108</div>\n" +
				"          <div>地址： 320桃園市中壢區復興路46號8樓804室 CHA103</div>\n" +
				"        </td>\n" +
				"      </tr>\n" +
				"    </table>\n" +
				"  </body>\n" +
				"</html>\n";
		return html;
	}

	private String imgBase64() throws IOException {
		File imageFile = new File("src/main/resources/happy_1.png");
		byte[] imageBytes = Files.readAllBytes(imageFile.toPath());
		String base64Image = javax.xml.bind.DatatypeConverter.printBase64Binary(imageBytes);

		return base64Image;
	}

}