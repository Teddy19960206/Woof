package com.woof.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Date;
import java.util.Properties;
import java.util.Set;
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
			props.put("mail.smtp.starttls.enable","true");
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
			message.setContent(messageText, "text/html; charset=utf-8");

			Transport.send(message);
			System.out.println("傳送成功!");
		} catch (MessagingException e) {
			System.out.println("傳送失敗!");
			e.printStackTrace();
		}
	}

	public static void main(String args[]) throws IOException {

		String to = "trick95710@gmail.com";

		String subject = "密碼通知";


		MailService mailService = new MailService();
//		mailService.sendMail(to, subject, "1111");

//		mailService.sendMail(to, subject, MailService.groupOrderhtml());
//		mailService.sendMail(to, subject, MailService.valid(url));
	}

	public static String groupOrderhtml(String name , String className , Set<Date> dates , String  content) {
		StringBuilder html = new StringBuilder();
		html.append("<!DOCTYPE ><html><head>")
				.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />")
				.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />")
				.append("<title></title><style>* { font-family: Microsoft JhengHei, PMingLiU, sans-serif; }</style></head>")
				.append("<body><table align=\"center\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"table-layout: fixed;background-color: white;\">")
				.append("<tr><td style=\"padding: 20px\" align=\"center\"><img src=\"https://cha103-17.s3.ap-northeast-1.amazonaws.com/happy_1.png\" width=\"50%\" /></td></tr>")
				.append("<tr><td height=\"200\" width=\"450\"><table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" height=\"50\" style=\"padding: 20px\">")
				.append("<tr height=\"60\" style=\"color: rgb(240, 98, 46); font-weight: 800; font-size: 30px\"><td align=\"center\">團體課程報名</td></tr>")
				.append("<tr height=\"60\"><td align=\"center\" style=\"color: rgb(240, 98, 46); font-weight: 900; font-size: 30px\">報名成功 !!!</td></tr>")
				.append("<tr height=\"60\"><td align=\"center\" style=\"color: rgb(240, 98, 46); font-weight: 900; font-size: 30px\">報名人姓名：").append(name).append("</td></tr>")
				.append("<tr height=\"60\"><td align=\"center\" style=\"color: rgb(240, 98, 46); font-weight: 800; font-size: 30px\">課程名稱：").append(className).append("</td></tr>")
				.append("<tr height=\"200\"><td style=\"color: rgb(240, 98, 46); font-weight: 800; font-size : 30px\" align=\"center\">上課時間");

		for (Date date : dates) {
			html.append("<div><h3>").append(date).append("</h3></div>");
		}

		html.append("</td></tr><tr><td><p style=\"color: rgb(240, 98, 46); font-weight: 900\">課程內容：</p><p style=\"color: rgb(240, 98, 46); font-weight: 900\">")
				.append(content)
				.append("</p></td></tr></table></td></tr>")
				.append("<tr><td style=\"background-color: rgb(240, 98, 46); color: white; font-weight: 900;\" height=\"150\" align=\"center\">")
				.append("<div>寵毛導師 Woof</div><div>連絡電話：03 425 1108</div><div>地址： 320桃園市中壢區復興路46號8樓804室 CHA103</div></td></tr>")
				.append("</table></body></html>");

		return html.toString();
	}

	public static String valid(String url) throws IOException {

		String html = "<!DOCTYPE >\n" +
				"<html>\n" +
				"  <head>\n" +
				"    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
				"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
				"    <title>會員驗證</title>\n" +
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
				"          <img src=\"https://cha103-17.s3.ap-northeast-1.amazonaws.com/happy_1.png\" width=\"50%\" />\n" +
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
				"              <td align=\"center\">會員驗證</td>\n" +
				"            </tr>\n" +
				"            <tr align=\"center\">\n"+
				"				<a href=\""+ url +"\"><button>會員驗證</button></a>"+
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
	
	public static String passwordResetEmail(String resetLink) {
	    String html = "<!DOCTYPE >\n" +
				"<html>\n" +
				"  <head>\n" +
				"    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
				"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
	            "    <title>密碼重置</title>\n" +
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
				"          <img src=\"https://cha103-17.s3.ap-northeast-1.amazonaws.com/happy_1.png\" width=\"50%\" />\n" +
				"        </td>\n" +
				"      </tr>\n" +
				"      <tr>\n" +
				"        <td height=\"100\" width=\"250\">\n" +
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
	            "    <h2 style=\"font-size: 24px;\">密碼重置請求</h2>\n" +
	            "    <p style=\"font-size: 24px;\">請於30分鐘內點擊下面的連結以重置您的密碼：</p>\n" +
	            "    <a href='" + resetLink + "' style='font-size: 18px;'>重置密碼</a>\n" +
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
	            "</body>\n" +
	            "</html>";
	    return html;
	}


	public static String classOrderhtml(Integer coBc,String memName) {
		String html ="<!DOCTYPE >\n" +
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
				"          <img src=\"https://cha103-17.s3.ap-northeast-1.amazonaws.com/happy_1.png\" width=\"50%\" />\n" +
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
				"              <td align=\"center\">課程購買</td>\n" +
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
				"                購買成功 !!!\n" +
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
				"                報名人姓名："+ memName +"\n" +
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
				"                購買堂數：" + coBc +"\n" +
				"              </td>\n" +
				"            </tr>\n" +
				"            <tr height=\"200\">\n" +          
				"            </tr>\n" +
				"            <tr>\n" +           
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

}
