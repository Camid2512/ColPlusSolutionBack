package co.edu.unbosque.ColPlusSolution.service;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	public void sendWelcomeEmail(String to) {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
		try {
			helper.setTo(to);
			helper.setSubject("Welcome Trainer");

			// Utiliza HTML para dar estilo al correo electrónico
			String htmlMsg = "<!DOCTYPE html>" + ""
					+ "<html lang=\"en\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" xmlns:v=\"urn:schemas-microsoft-com:vml\">"
					+ "<head>" + "<title></title>"
					+ "<meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\"/>"
					+ "<meta content=\"width=device-width, initial-scale=1.0\" name=\"viewport\"/><!--[if mso]><xml><o:OfficeDocumentSettings><o:PixelsPerInch>96</o:PixelsPerInch><o:AllowPNG/></o:OfficeDocumentSettings></xml><![endif]--><!--[if !mso]><!-->"
					+ "<link href=\"https://fonts.googleapis.com/css2?family=Fira+Sans:wght@100;200;300;400;500;600;700;800;900\" rel=\"stylesheet\" type=\"text/css\"/><!--<![endif]-->"
					+ "<style>" + "* {" + "box-sizing: border-box;" + "}" + "" + "body {" + "margin: 0;" + "padding: 0;"
					+ "}" + "" + "a[x-apple-data-detectors] {" + "color: inherit !important;"
					+ "text-decoration: inherit !important;" + "}" + "" + "#MessageViewBody a {" + "color: inherit;"
					+ "text-decoration: none;" + "}" + "" + "p {" + "line-height: inherit" + "}" + "" + ".desktop_hide,"
					+ ".desktop_hide table {" + "mso-hide: all;" + "display: none;" + "max-height: 0px;"
					+ "overflow: hidden;" + "}" + "" + ".image_block img+div {" + "display: none;" + "}" + ""
					+ "@media (max-width:705px) {" + ".desktop_hide table.icons-inner {"
					+ "display: inline-block !important;" + "}" + "" + ".icons-inner {" + "text-align: center;" + "}"
					+ "" + ".icons-inner td {" + "margin: 0 auto;" + "}" + "" + ".mobile_hide {" + "display: none;"
					+ "}" + "" + ".row-content {" + "width: 100% !important;" + "}" + "" + ".stack .column {"
					+ "width: 100%;" + "display: block;" + "}" + "" + ".mobile_hide {" + "min-height: 0;"
					+ "max-height: 0;" + "max-width: 0;" + "overflow: hidden;" + "font-size: 0px;" + "}" + ""
					+ ".desktop_hide," + ".desktop_hide table {" + "display: table !important;"
					+ "max-height: none !important;" + "}" + "" + ".row-2 .column-1 .block-2.heading_block h1 {"
					+ "font-size: 31px !important;" + "}" + "" + ".row-15 .column-1 .block-2.paragraph_block td.pad,"
					+ ".row-15 .column-3 .block-2.paragraph_block td.pad,"
					+ ".row-4 .column-2 .block-1.image_block td.pad {" + "padding: 10px !important;" + "}" + ""
					+ ".row-15 .column-1 .block-1.heading_block h1,"
					+ ".row-15 .column-1 .block-2.paragraph_block td.pad>div,"
					+ ".row-15 .column-3 .block-1.heading_block h1,"
					+ ".row-15 .column-3 .block-2.paragraph_block td.pad>div {" + "text-align: center !important;" + "}"
					+ "" + ".row-15 .column-1 .block-1.heading_block td.pad,"
					+ ".row-15 .column-3 .block-1.heading_block td.pad {" + "padding: 0 10px !important;" + "}" + ""
					+ ".row-1 .column-1," + ".row-1 .column-2 {" + "padding: 5px 10px !important;" + "}" + "}"
					+ "</style>" + "</head>"
					+ "<body style=\"background-color: #011638; margin: 0; padding: 0; -webkit-text-size-adjust: none; text-size-adjust: none;\">"
					+ "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"nl-container\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #011638;\" width=\"100%\">"
					+ "<tbody>" + "<tr>" + "<td>"
					+ "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-1\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #ffffff;\" width=\"100%\">"
					+ "<tbody>" + "<tr>" + "<td>"
					+ "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-radius: 0; color: #000000; width: 685px; margin: 0 auto;\" width=\"685\">"
					+ "<tbody>" + "<tr>"
					+ "<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: middle; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"25%\">"
					+ "<div class=\"spacer_block block-1\" style=\"height:60px;line-height:60px;font-size:1px;\"> </div>"
					+ "</td>"
					+ "<td class=\"column column-2\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: middle; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"75%\">"
					+ "<div class=\"spacer_block block-1\" style=\"height:60px;line-height:60px;font-size:1px;\"> </div>"
					+ "</td>" + "</tr>" + "</tbody>" + "</table>" + "</td>" + "</tr>" + "</tbody>" + "</table>"
					+ "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-2\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #ffffff;\" width=\"100%\">"
					+ "<tbody>" + "<tr>" + "<td>"
					+ "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 685px; margin: 0 auto;\" width=\"685\">"
					+ "<tbody>" + "<tr>"
					+ "<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"100%\">"
					+ "<div class=\"spacer_block block-1\" style=\"height:25px;line-height:25px;font-size:1px;\"> </div>"
					+ "<table border=\"0\" cellpadding=\"10\" cellspacing=\"0\" class=\"heading_block block-2\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">"
					+ "<tr>" + "<td class=\"pad\">"
					+ "<h1 style=\"margin: 0; color: #011638; direction: ltr; font-family: 'Fira Sans', 'Lucida Sans Unicode', 'Lucida Grande', sans-serif; font-size: 41px; font-weight: 500; letter-spacing: normal; line-height: 120%; text-align: center; margin-top: 0; margin-bottom: 0; mso-line-height-alt: 49.199999999999996px;\"><span class=\"tinyMce-placeholder\">Bienvenido a El Bosque Pokémon</span></h1>"
					+ "</td>" + "</tr>" + "</table>"
					+ "<div class=\"spacer_block block-3\" style=\"height:15px;line-height:15px;font-size:1px;\"> </div>"
					+ "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"image_block block-4\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">"
					+ "<tr>" + "<td class=\"pad\" style=\"width:100%;\">"
					+ "<div align=\"center\" class=\"alignment\" style=\"line-height:10px\">"
					+ "<div style=\"max-width: 685px;\"><img alt=\"Ramadana\" height=\"auto\" src=\"https://sm.ign.com/ign_es/feature/t/the-10-bes/the-10-best-pokemon-video-games_6gvp.jpg\" style=\"display: block; height: auto; border: 0; width: 100%;\" title=\"Ramadana\" width=\"685\"/></div>"
					+ "</div>" + "</td>" + "</tr>" + "</table>" + "</td>" + "</tr>" + "</tbody>" + "</table>" + "</td>"
					+ "</tr>" + "</tbody>" + "</table>"
					+ "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-3\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #011638;\" width=\"100%\">"
					+ "<tbody>" + "<tr>" + "<td>"
					+ "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-radius: 0; color: #000000; width: 685px; margin: 0 auto;\" width=\"685\">"
					+ "<tbody>" + "<tr>"
					+ "<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"100%\">"
					+ "<div class=\"spacer_block block-1\" style=\"height:65px;line-height:65px;font-size:1px;\"> </div>"
					+ "</td>" + "</tr>" + "</tbody>" + "</table>" + "</td>" + "</tr>" + "</tbody>" + "</table>"
					+ "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-4\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">"
					+ "<tbody>" + "<tr>" + "<td>"
					+ "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-radius: 0; color: #000000; width: 685px; margin: 0 auto;\" width=\"685\">"
					+ "<tbody>" + "<tr>"
					+ "<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: middle; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"50%\">"
					+ "<table border=\"0\" cellpadding=\"10\" cellspacing=\"0\" class=\"heading_block block-1\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">"
					+ "<tr>" + "<td class=\"pad\">"
					+ "<h3 style=\"margin: 0; color: #e37432; direction: ltr; font-family: Arial, Helvetica, sans-serif; font-size: 17px; font-weight: 700; letter-spacing: normal; line-height: 120%; text-align: left; margin-top: 0; margin-bottom: 0; mso-line-height-alt: 20.4px;\"><span class=\"tinyMce-placeholder\"></span></h3>"
					+ "</td>" + "</tr>" + "</table>"
					+ "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"heading_block block-2\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">"
					+ "<tr>"
					+ "<td class=\"pad\" style=\"padding-left:10px;padding-right:10px;padding-top:10px;text-align:center;width:100%;\">"
					+ "<h2 style=\"margin: 0; color: #ffffff; direction: ltr; font-family: 'Fira Sans', 'Lucida Sans Unicode', 'Lucida Grande', sans-serif; font-size: 27px; font-weight: 700; letter-spacing: normal; line-height: 150%; text-align: left; margin-top: 0; margin-bottom: 0; mso-line-height-alt: 40.5px;\"><span class=\"tinyMce-placeholder\">Información sobre tus Pokémon favoritos</span></h2>"
					+ "</td>" + "</tr>" + "</table>"
					+ "<table border=\"0\" cellpadding=\"10\" cellspacing=\"0\" class=\"paragraph_block block-3\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\" width=\"100%\">"
					+ "<tr>" + "<td class=\"pad\">"
					+ "<div style=\"color:#d8d8f6;direction:ltr;font-family:Arial, Helvetica, sans-serif;font-size:16px;font-weight:400;letter-spacing:0px;line-height:150%;text-align:left;mso-line-height-alt:24px;\">"
					+ "<p style=\"margin: 0;\">Tipos, tamaños, número de Pokédex</p>" + "</div>" + "</td>" + "</tr>"
					+ "</table>" + "</td>"
					+ "<td class=\"column column-2\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: middle; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"50%\">"
					+ "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"image_block block-1\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">"
					+ "<tr>" + "<td class=\"pad\" style=\"width:100%;\">"
					+ "<div align=\"center\" class=\"alignment\" style=\"line-height:10px\">"
					+ "<div style=\"max-width: 342.5px;\"><img alt=\"family praying\" height=\"auto\" src=\"https://archives.bulbagarden.net/media/upload/thumb/d/d1/2016_Mythical_Pok%C3%A9mon_Distributions_artwork.png/350px-2016_Mythical_Pok%C3%A9mon_Distributions_artwork.png\" style=\"display: block; height: auto; border: 0; width: 100%;\" title=\"family praying\" width=\"342.5\"/></div>"
					+ "</div>" + "</td>" + "</tr>" + "</table>" + "</td>" + "</tr>" + "</tbody>" + "</table>" + "</td>"
					+ "</tr>" + "</tbody>" + "</table>"
					+ "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-5\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #011638;\" width=\"100%\">"
					+ "<tbody>" + "<tr>" + "<td>"
					+ "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-radius: 0; color: #000000; width: 685px; margin: 0 auto;\" width=\"685\">"
					+ "<tbody>" + "<tr>"
					+ "<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"100%\">"
					+ "<div class=\"spacer_block block-1\" style=\"height:45px;line-height:45px;font-size:1px;\"> </div>"
					+ "</td>" + "</tr>" + "</tbody>" + "</table>" + "</td>" + "</tr>" + "</tbody>" + "</table>"
					+ "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-6\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">"
					+ "<tbody>" + "<tr>" + "<td>"
					+ "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-radius: 0; color: #000000; width: 685px; margin: 0 auto;\" width=\"685\">"
					+ "<tbody>" + "<tr>"
					+ "<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: middle; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"50%\">"
					+ "<table border=\"0\" cellpadding=\"10\" cellspacing=\"0\" class=\"image_block block-1\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">"
					+ "<tr>" + "<td class=\"pad\">"
					+ "<div align=\"center\" class=\"alignment\" style=\"line-height:10px\">"
					+ "<div style=\"max-width: 308.25px;\"><img alt=\"Iftar dinner\" height=\"auto\" src=\"https://www.mundodeportivo.com/alfabeta/hero/2021/05/Batalla-3V3-en-Pok%C3%A9mon-Masters-EX.jpg?width=768&aspect_ratio=16:9&format=nowebp\" style=\"display: block; height: auto; border: 0; width: 100%;\" title=\"Iftar dinner\" width=\"308.25\"/></div>"
					+ "</div>" + "</td>" + "</tr>" + "</table>" + "</td>"
					+ "<td class=\"column column-2\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: middle; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"50%\">"
					+ "<table border=\"0\" cellpadding=\"10\" cellspacing=\"0\" class=\"heading_block block-1\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">"
					+ "<tr>" + "<td class=\"pad\">"
					+ "<h3 style=\"margin: 0; color: #e37432; direction: ltr; font-family: Arial, Helvetica, sans-serif; font-size: 17px; font-weight: 700; letter-spacing: normal; line-height: 120%; text-align: left; margin-top: 0; margin-bottom: 0; mso-line-height-alt: 20.4px;\"><span class=\"tinyMce-placeholder\"> </span></h3>"
					+ "</td>" + "</tr>" + "</table>"
					+ "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"heading_block block-2\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">"
					+ "<tr>"
					+ "<td class=\"pad\" style=\"padding-left:10px;padding-right:10px;padding-top:10px;text-align:center;width:100%;\">"
					+ "<h2 style=\"margin: 0; color: #ffffff; direction: ltr; font-family: 'Fira Sans', 'Lucida Sans Unicode', 'Lucida Grande', sans-serif; font-size: 27px; font-weight: 700; letter-spacing: normal; line-height: 150%; text-align: left; margin-top: 0; margin-bottom: 0; mso-line-height-alt: 40.5px;\"><span class=\"tinyMce-placeholder\">Simula batallas</span></h2>"
					+ "</td>" + "</tr>" + "</table>"
					+ "<table border=\"0\" cellpadding=\"10\" cellspacing=\"0\" class=\"paragraph_block block-3\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\" width=\"100%\">"
					+ "<tr>" + "<td class=\"pad\">"
					+ "<div style=\"color:#d8d8f6;direction:ltr;font-family:Arial, Helvetica, sans-serif;font-size:16px;font-weight:400;letter-spacing:0px;line-height:150%;text-align:left;mso-line-height-alt:24px;\">"
					+ "<p style=\"margin: 0;\">¡Las más divertidas de la historia de la universidad El Bosque!</p>"
					+ "</div>" + "</td>" + "</tr>" + "</table>" + "</td>" + "</tr>" + "</tbody>" + "</table>" + "</td>"
					+ "</tr>" + "</tbody>" + "</table>"
					+ "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-7\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">"
					+ "<tbody>" + "<tr>" + "<td>"
					+ "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-radius: 0; color: #000000; width: 685px; margin: 0 auto;\" width=\"685\">"
					+ "<tbody>" + "<tr>"
					+ "<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"100%\">"
					+ "<div class=\"spacer_block block-1\" style=\"height:40px;line-height:40px;font-size:1px;\"> </div>"
					+ "</td>" + "</tr>" + "</tbody>" + "</table>" + "</td>" + "</tr>" + "</tbody>" + "</table>"
					+ "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-8\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">"
					+ "<tbody>" + "<tr>" + "<td>"
					+ "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-radius: 0; color: #000000; width: 685px; margin: 0 auto;\" width=\"685\">"
					+ "<tbody>" + "<tr>"
					+ "<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"100%\">"
					+ "<div class=\"spacer_block block-1\" style=\"height:15px;line-height:15px;font-size:1px;\"> </div>"
					+ "</td>" + "</tr>" + "</tbody>" + "</table>" + "</td>" + "</tr>" + "</tbody>" + "</table>"
					+ "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-9\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">"
					+ "<tbody>" + "<tr>" + "<td>"
					+ "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-radius: 0; color: #000000; width: 685px; margin: 0 auto;\" width=\"685\">"
					+ "<tbody>" + "<tr>"
					+ "<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"100%\">"
					+ "<div class=\"spacer_block block-1\" style=\"height:15px;line-height:15px;font-size:1px;\"> </div>"
					+ "</td>" + "</tr>" + "</tbody>" + "</table>" + "</td>" + "</tr>" + "</tbody>" + "</table>"
					+ "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-10\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">"
					+ "<tbody>" + "<tr>" + "<td>"
					+ "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-radius: 0; color: #000000; width: 685px; margin: 0 auto;\" width=\"685\">"
					+ "<tbody>" + "<tr>"
					+ "<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"100%\">"
					+ "<div class=\"spacer_block block-1\" style=\"height:20px;line-height:20px;font-size:1px;\"> </div>"
					+ "</td>" + "</tr>" + "</tbody>" + "</table>" + "</td>" + "</tr>" + "</tbody>" + "</table>"
					+ "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-11\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">"
					+ "<tbody>" + "<tr>" + "<td>"
					+ "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-radius: 0; color: #000000; width: 685px; margin: 0 auto;\" width=\"685\">"
					+ "<tbody>" + "<tr>"
					+ "<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"100%\">"
					+ "<div class=\"spacer_block block-1\" style=\"height:35px;line-height:35px;font-size:1px;\"> </div>"
					+ "</td>" + "</tr>" + "</tbody>" + "</table>" + "</td>" + "</tr>" + "</tbody>" + "</table>"
					+ "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-12\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">"
					+ "<tbody>" + "<tr>" + "<td>"
					+ "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #12294f; background-image: url('images/Group_1000006081.png'); background-repeat: no-repeat; background-size: cover; border-radius: 12px; color: #000000; width: 685px; margin: 0 auto;\" width=\"685\">"
					+ "<tbody>" + "<tr>"
					+ "<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"100%\">"
					+ "<div class=\"spacer_block block-1\" style=\"height:55px;line-height:55px;font-size:1px;\"> </div>"
					+ "</td>" + "</tr>" + "</tbody>" + "</table>" + "</td>" + "</tr>" + "</tbody>" + "</table>"
					+ "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-13\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">"
					+ "<tbody>" + "<tr>" + "<td>"
					+ "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-radius: 0; color: #000000; width: 685px; margin: 0 auto;\" width=\"685\">"
					+ "<tbody>" + "<tr>"
					+ "<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"100%\">"
					+ "<div class=\"spacer_block block-1\" style=\"height:60px;line-height:60px;font-size:1px;\"> </div>"
					+ "</td>" + "</tr>" + "</tbody>" + "</table>" + "</td>" + "</tr>" + "</tbody>" + "</table>"
					+ "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-14\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #746ff0;\" width=\"100%\">"
					+ "<tbody>" + "<tr>" + "<td>"
					+ "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-radius: 0; color: #000000; width: 685px; margin: 0 auto;\" width=\"685\">"
					+ "<tbody>" + "<tr>"
					+ "<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"100%\">"
					+ "<div class=\"spacer_block block-1\" style=\"height:35px;line-height:35px;font-size:1px;\"> </div>"
					+ "</td>" + "</tr>" + "</tbody>" + "</table>" + "</td>" + "</tr>" + "</tbody>" + "</table>"
					+ "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-15\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #746ff0;\" width=\"100%\">"
					+ "<tbody>" + "<tr>" + "<td>"
					+ "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 685px; margin: 0 auto;\" width=\"685\">"
					+ "<tbody>" + "<tr>"
					+ "<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"33.333333333333336%\">"
					+ "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"heading_block block-1\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">"
					+ "<tr>" + "<td class=\"pad\" style=\"padding-left:20px;text-align:center;width:100%;\">"
					+ "<h1 style=\"margin: 0; color: #ffffff; direction: ltr; font-family: Arial, Helvetica, sans-serif; font-size: 18px; font-weight: 700; line-height: 200%; text-align: left; margin-top: 0; margin-bottom: 0; mso-line-height-alt: 36px;\"><strong>About Us</strong></h1>"
					+ "</td>" + "</tr>" + "</table>"
					+ "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"paragraph_block block-2\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\" width=\"100%\">"
					+ "<tr>"
					+ "<td class=\"pad\" style=\"padding-bottom:10px;padding-left:20px;padding-right:20px;padding-top:10px;\">"
					+ "<div style=\"color:#ffffff;direction:ltr;font-family:Arial, Helvetica, sans-serif;font-size:14px;font-weight:400;letter-spacing:0px;line-height:150%;text-align:left;mso-line-height-alt:21px;\">"
					+ "<p style=\"margin: 0;\">Softpylsa</p>" + "</div>" + "</td>" + "</tr>" + "</table>" + "</td>"
					+ "<td class=\"column column-2\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"33.333333333333336%\">"
					+ "<div class=\"spacer_block block-1\" style=\"height:60px;line-height:60px;font-size:1px;\"> </div>"
					+ "</td>"
					+ "<td class=\"column column-3\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"33.333333333333336%\">"
					+ "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"heading_block block-1\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">"
					+ "<tr>" + "<td class=\"pad\" style=\"padding-left:20px;text-align:center;width:100%;\">"
					+ "<h1 style=\"margin: 0; color: #ffffff; direction: ltr; font-family: Arial, Helvetica, sans-serif; font-size: 18px; font-weight: normal; line-height: 200%; text-align: left; margin-top: 0; margin-bottom: 0; mso-line-height-alt: 36px;\"><span class=\"tinyMce-placeholder\">Copyrigth</span></h1>"
					+ "</td>" + "</tr>" + "</table>"
					+ "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"paragraph_block block-2\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\" width=\"100%\">"
					+ "<tr>"
					+ "<td class=\"pad\" style=\"padding-bottom:10px;padding-left:20px;padding-right:20px;padding-top:10px;\">"
					+ "<div style=\"color:#ffffff;direction:ltr;font-family:Arial, Helvetica, sans-serif;font-size:14px;font-weight:400;letter-spacing:0px;line-height:150%;text-align:left;mso-line-height-alt:21px;\">"
					+ "<p style=\"margin: 0;\">Pokémon company</p>" + "</div>" + "</td>" + "</tr>" + "</table>"
					+ "</td>" + "</tr>" + "</tbody>" + "</table>" + "</td>" + "</tr>" + "</tbody>" + "</table>"
					+ "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-16\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #746ff0;\" width=\"100%\">"
					+ "<tbody>" + "<tr>" + "<td>"
					+ "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; border-radius: 0; color: #000000; width: 685px; margin: 0 auto;\" width=\"685\">"
					+ "<tbody>" + "<tr>"
					+ "<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"100%\">"
					+ "<div class=\"spacer_block block-1\" style=\"height:35px;line-height:35px;font-size:1px;\"> </div>"
					+ "<div class=\"spacer_block block-2\" style=\"height:35px;line-height:35px;font-size:1px;\"> </div>"
					+ "</td>" + "</tr>" + "</tbody>" + "</table>" + "</td>" + "</tr>" + "</tbody>" + "</table>"
					+ "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-17\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #ffffff;\" width=\"100%\">"
					+ "<tbody>" + "<tr>" + "<td>"
					+ "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #ffffff; color: #000000; width: 685px; margin: 0 auto;\" width=\"685\">"
					+ "<tbody>" + "<tr>"
					+ "<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"100%\">"
					+ "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"icons_block block-1\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; text-align: center;\" width=\"100%\">"
					+ "<tr>"
					+ "<td class=\"pad\" style=\"vertical-align: middle; color: #1e0e4b; font-family: 'Inter', sans-serif; font-size: 15px; padding-bottom: 5px; padding-top: 5px; text-align: center;\">"
					+ "<table cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">"
					+ "<tr>"
					+ "<td class=\"alignment\" style=\"vertical-align: middle; text-align: center;\"><!--[if vml]><table align=\"center\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"display:inline-block;padding-left:0px;padding-right:0px;mso-table-lspace: 0pt;mso-table-rspace: 0pt;\"><![endif]-->"
					+ "<!--[if !vml]><!-->"
					+ "<table cellpadding=\"0\" cellspacing=\"0\" class=\"icons-inner\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; display: inline-block; margin-right: -4px; padding-left: 0px; padding-right: 0px;\"><!--<![endif]-->"
					+ "<tr>"
					+ "<td style=\"vertical-align: middle; text-align: center; padding-top: 5px; padding-bottom: 5px; padding-left: 5px; padding-right: 6px;\"><a href=\"http://designedwithbeefree.com/\" style=\"text-decoration: none;\" target=\"_blank\"><img align=\"center\" alt=\"Beefree Logo\" class=\"icon\" height=\"auto\" src=\"images/Beefree-logo.png\" style=\"display: block; height: auto; margin: 0 auto; border: 0;\" width=\"34\"/></a></td>"
					+ "<td style=\"font-family: 'Inter', sans-serif; font-size: 15px; font-weight: undefined; color: #1e0e4b; vertical-align: middle; letter-spacing: undefined; text-align: center;\"><a href=\"http://designedwithbeefree.com/\" style=\"color: #1e0e4b; text-decoration: none;\" target=\"_blank\">Designed with Beefree</a></td>"
					+ "</tr>" + "</table>" + "</td>" + "</tr>" + "</table>" + "</td>" + "</tr>" + "</table>" + "</td>"
					+ "</tr>" + "</tbody>" + "</table>" + "</td>" + "</tr>" + "</tbody>" + "</table>" + "</td>"
					+ "</tr>" + "</tbody>" + "</table><!-- End -->" + "</body>" + "</html>";
			helper.setText(htmlMsg, true); // Indica que el texto es HTML

			javaMailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public void sendUpdateEmail(String to) {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		try {
			helper.setTo(to);
			helper.setSubject("Information updated");

			String htmlMsg = "<!DOCTYPE html>"
					+ "<html xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" lang=\"en\">"
					+ "" + "<head>" + "<title></title>"
					+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">"
					+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"><!--[if mso]><xml><o:OfficeDocumentSettings><o:PixelsPerInch>96</o:PixelsPerInch><o:AllowPNG/></o:OfficeDocumentSettings></xml><![endif]--><!--[if !mso]><!-->"
					+ "<link href=\"https://fonts.googleapis.com/css?family=Open+Sans\" rel=\"stylesheet\" type=\"text/css\"><!--<![endif]-->"
					+ "<style>" + "* {" + "box-sizing: border-box;" + "}" + "" + "body {" + "margin: 0;" + "padding: 0;"
					+ "}" + "" + "a[x-apple-data-detectors] {" + "color: inherit !important;"
					+ "text-decoration: inherit !important;" + "}" + "" + "#MessageViewBody a {" + "color: inherit;"
					+ "text-decoration: none;" + "}" + "" + "p {" + "line-height: inherit" + "}" + "" + ".desktop_hide,"
					+ ".desktop_hide table {" + "mso-hide: all;" + "display: none;" + "max-height: 0px;"
					+ "overflow: hidden;" + "}" + "" + ".image_block img+div {" + "display: none;" + "}" + ""
					+ "@media (max-width:660px) {" + ".desktop_hide table.icons-inner {"
					+ "display: inline-block !important;" + "}" + "" + ".icons-inner {" + "text-align: center;" + "}"
					+ "" + ".icons-inner td {" + "margin: 0 auto;" + "}" + "" + ".mobile_hide {" + "display: none;"
					+ "}" + "" + ".row-content {" + "width: 100% !important;" + "}" + "" + ".stack .column {"
					+ "width: 100%;" + "display: block;" + "}" + "" + ".mobile_hide {" + "min-height: 0;"
					+ "max-height: 0;" + "max-width: 0;" + "overflow: hidden;" + "font-size: 0px;" + "}" + ""
					+ ".desktop_hide," + ".desktop_hide table {" + "display: table !important;"
					+ "max-height: none !important;" + "}" + "}" + "</style>" + "</head>" + ""
					+ "<body style=\"background-color: #0b0340; margin: 0; padding: 0; -webkit-text-size-adjust: none; text-size-adjust: none;\">"
					+ "<table class=\"nl-container\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #0b0340;\">"
					+ "<tbody>" + "<tr>" + "<td>"
					+ "<table class=\"row row-1\" align=\"center\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-position: center top;\">"
					+ "<tbody>" + "<tr>" + "<td>"
					+ "<table class=\"row-content stack\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 640px; margin: 0 auto;\" width=\"640\">"
					+ "<tbody>" + "<tr>"
					+ "<td class=\"column column-1\" width=\"100%\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 40px; padding-top: 40px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\">"
					+ "<div class=\"spacer_block block-1\" style=\"height:25px;line-height:25px;font-size:1px;\">&#8202;</div>"
					+ "<table class=\"heading_block block-2\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">"
					+ "<tr>"
					+ "<td class=\"pad\" style=\"padding-left:10px;padding-right:10px;text-align:center;width:100%;\">"
					+ "<h1 style=\"margin: 0; color: #ffffff; direction: ltr; font-family: Georgia, Times, 'Times New Roman', serif; font-size: 50px; font-weight: normal; line-height: 120%; text-align: center; margin-top: 0; margin-bottom: 0; mso-line-height-alt: 60px;\"><span class=\"tinyMce-placeholder\">Bosque Pokémon</span></h1>"
					+ "</td>" + "</tr>" + "</table>" + "</td>" + "</tr>" + "</tbody>" + "</table>" + "</td>" + "</tr>"
					+ "</tbody>" + "</table>"
					+ "<table class=\"row row-2\" align=\"center\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-position: center top;\">"
					+ "<tbody>" + "<tr>" + "<td>"
					+ "<table class=\"row-content stack\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 640px; margin: 0 auto;\" width=\"640\">"
					+ "<tbody>" + "<tr>"
					+ "<td class=\"column column-1\" width=\"100%\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\">"
					+ "<table class=\"image_block block-1\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">"
					+ "<tr>" + "<td class=\"pad\" style=\"width:100%;\">"
					+ "<div class=\"alignment\" align=\"center\" style=\"line-height:10px\">"
					+ "<div style=\"max-width: 640px;\"><img src=\"https://archives.bulbagarden.net/media/upload/thumb/a/ae/ORAS_Contest_art.png/1200px-ORAS_Contest_art.png\" style=\"display: block; height: auto; border: 0; width: 100%;\" width=\"640\" alt=\"Is This A Joke?\" title=\"Is This A Joke?\" height=\"auto\"></div>"
					+ "</div>" + "</td>" + "</tr>" + "</table>"
					+ "<div class=\"spacer_block block-2\" style=\"height:60px;line-height:60px;font-size:1px;\">&#8202;</div>"
					+ "<table class=\"heading_block block-3\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">"
					+ "<tr>"
					+ "<td class=\"pad\" style=\"padding-left:10px;padding-right:10px;text-align:center;width:100%;\">"
					+ "<h1 style=\"margin: 0; color: #ffffff; direction: ltr; font-family: Open Sans, Helvetica Neue, Helvetica, Arial, sans-serif; font-size: 50px; font-weight: normal; line-height: 120%; text-align: center; margin-top: 0; margin-bottom: 0; mso-line-height-alt: 60px;\"><strong>Información actualizada</strong></h1>"
					+ "</td>" + "</tr>" + "</table>"
					+ "<table class=\"heading_block block-4\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">"
					+ "<tr>"
					+ "<td class=\"pad\" style=\"padding-left:10px;padding-right:10px;text-align:center;width:100%;\">"
					+ "<h1 style=\"margin: 0; color: #ffffff; direction: ltr; font-family: Open Sans, Helvetica Neue, Helvetica, Arial, sans-serif; font-size: 50px; font-weight: normal; line-height: 120%; text-align: center; margin-top: 0; margin-bottom: 0; mso-line-height-alt: 60px;\"><strong>correctamente</strong></h1>"
					+ "</td>" + "</tr>" + "</table>"
					+ "<div class=\"spacer_block block-5\" style=\"height:30px;line-height:30px;font-size:1px;\">&#8202;</div>"
					+ "<div class=\"spacer_block block-6\" style=\"height:30px;line-height:30px;font-size:1px;\">&#8202;</div>"
					+ "<table class=\"paragraph_block block-7\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\">"
					+ "<tr>"
					+ "<td class=\"pad\" style=\"padding-bottom:10px;padding-left:20px;padding-right:20px;padding-top:10px;\">"
					+ "<div style=\"color:#393d47;font-family:'Open Sans','Helvetica Neue',Helvetica,Arial,sans-serif;font-size:16px;line-height:180%;text-align:center;mso-line-height-alt:28.8px;\">"
					+ "<p style=\"margin: 0; word-break: break-word;\"><span style=\"color: #ffb966;\">Si no ves los cambios contáctate con un administrador.</span></p>"
					+ "</div>" + "</td>" + "</tr>" + "</table>"
					+ "<div class=\"spacer_block block-8\" style=\"height:30px;line-height:30px;font-size:1px;\">&#8202;</div>"
					+ "</td>" + "</tr>" + "</tbody>" + "</table>" + "</td>" + "</tr>" + "</tbody>" + "</table>"
					+ "<table class=\"row row-3\" align=\"center\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-position: top center;\">"
					+ "<tbody>" + "<tr>" + "<td>"
					+ "<table class=\"row-content stack\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 640px; margin: 0 auto;\" width=\"640\">"
					+ "<tbody>" + "<tr>"
					+ "<td class=\"column column-1\" width=\"100%\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 10px; padding-top: 10px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\">"
					+ "<div class=\"spacer_block block-1\" style=\"height:40px;line-height:40px;font-size:1px;\">&#8202;</div>"
					+ "<div class=\"spacer_block block-2\" style=\"height:25px;line-height:25px;font-size:1px;\">&#8202;</div>"
					+ "<div class=\"spacer_block block-3\" style=\"height:40px;line-height:40px;font-size:1px;\">&#8202;</div>"
					+ "</td>" + "</tr>" + "</tbody>" + "</table>" + "</td>" + "</tr>" + "</tbody>" + "</table>"
					+ "<table class=\"row row-4\" align=\"center\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #ffffff;\">"
					+ "<tbody>" + "<tr>" + "<td>"
					+ "<table class=\"row-content stack\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; background-color: #ffffff; width: 640px; margin: 0 auto;\" width=\"640\">"
					+ "<tbody>" + "<tr>"
					+ "<td class=\"column column-1\" width=\"100%\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\">"
					+ "<table class=\"icons_block block-1\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; text-align: center;\">"
					+ "<tr>"
					+ "<td class=\"pad\" style=\"vertical-align: middle; color: #1e0e4b; font-family: 'Inter', sans-serif; font-size: 15px; padding-bottom: 5px; padding-top: 5px; text-align: center;\">"
					+ "<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">"
					+ "<tr>"
					+ "<td class=\"alignment\" style=\"vertical-align: middle; text-align: center;\"><!--[if vml]><table align=\"center\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"display:inline-block;padding-left:0px;padding-right:0px;mso-table-lspace: 0pt;mso-table-rspace: 0pt;\"><![endif]-->"
					+ "<!--[if !vml]><!-->"
					+ "<table class=\"icons-inner\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; display: inline-block; margin-right: -4px; padding-left: 0px; padding-right: 0px;\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\"><!--<![endif]-->"
					+ "<tr>"
					+ "<td style=\"vertical-align: middle; text-align: center; padding-top: 5px; padding-bottom: 5px; padding-left: 5px; padding-right: 6px;\"><a href=\"http://designedwithbeefree.com/\" target=\"_blank\" style=\"text-decoration: none;\"><img class=\"icon\" alt=\"Beefree Logo\" src=\"https://d1oco4z2z1fhwp.cloudfront.net/assets/Beefree-logo.png\" height=\"auto\" width=\"34\" align=\"center\" style=\"display: block; height: auto; margin: 0 auto; border: 0;\"></a></td>"
					+ "<td style=\"font-family: 'Inter', sans-serif; font-size: 15px; font-weight: undefined; color: #1e0e4b; vertical-align: middle; letter-spacing: undefined; text-align: center;\"><a href=\"http://designedwithbeefree.com/\" target=\"_blank\" style=\"color: #1e0e4b; text-decoration: none;\">Designed with Beefree</a></td>"
					+ "</tr>" + "</table>" + "</td>" + "</tr>" + "</table>" + "</td>" + "</tr>" + "</table>" + "</td>"
					+ "</tr>" + "</tbody>" + "</table>" + "</td>" + "</tr>" + "</tbody>" + "</table>" + "</td>"
					+ "</tr>" + "</tbody>" + "</table><!-- End -->" + "</body>" + "" + "</html>";
			helper.setText(htmlMsg, true);

			javaMailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
			// Maneja la excepción apropiadamente
		}
	}

	public String recoverPasswordEmail(String to) {

		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		try {
			helper.setTo(to);
			helper.setSubject("Recovering Password");
			String code = randomString();
			String msg = "<!DOCTYPE html>"
					+ "<html xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" lang=\"en\">"
					+ "" + "<head>" + "<title></title>"
					+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">"
					+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"><!--[if mso]><xml><o:OfficeDocumentSettings><o:PixelsPerInch>96</o:PixelsPerInch><o:AllowPNG/></o:OfficeDocumentSettings></xml><![endif]--><!--[if !mso]><!-->"
					+ "<link href=\"https://fonts.googleapis.com/css?family=Open+Sans\" rel=\"stylesheet\" type=\"text/css\"><!--<![endif]-->"
					+ "<style>" + "* {" + "box-sizing: border-box;" + "}" + "" + "body {" + "margin: 0;" + "padding: 0;"
					+ "}" + "" + "a[x-apple-data-detectors] {" + "color: inherit !important;"
					+ "text-decoration: inherit !important;" + "}" + "" + "#MessageViewBody a {" + "color: inherit;"
					+ "text-decoration: none;" + "}" + "" + "p {" + "line-height: inherit" + "}" + "" + ".desktop_hide,"
					+ ".desktop_hide table {" + "mso-hide: all;" + "display: none;" + "max-height: 0px;"
					+ "overflow: hidden;" + "}" + "" + ".image_block img+div {" + "display: none;" + "}" + ""
					+ "@media (max-width:660px) {" + ".desktop_hide table.icons-inner {"
					+ "display: inline-block !important;" + "}" + "" + ".icons-inner {" + "text-align: center;" + "}"
					+ "" + ".icons-inner td {" + "margin: 0 auto;" + "}" + "" + ".mobile_hide {" + "display: none;"
					+ "}" + "" + ".row-content {" + "width: 100% !important;" + "}" + "" + ".stack .column {"
					+ "width: 100%;" + "display: block;" + "}" + "" + ".mobile_hide {" + "min-height: 0;"
					+ "max-height: 0;" + "max-width: 0;" + "overflow: hidden;" + "font-size: 0px;" + "}" + ""
					+ ".desktop_hide," + ".desktop_hide table {" + "display: table !important;"
					+ "max-height: none !important;" + "}" + "}" + "</style>" + "</head>" + ""
					+ "<body style=\"background-color: #0b0340; margin: 0; padding: 0; -webkit-text-size-adjust: none; text-size-adjust: none;\">"
					+ "<table class=\"nl-container\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #0b0340;\">"
					+ "<tbody>" + "<tr>" + "<td>"
					+ "<table class=\"row row-1\" align=\"center\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-position: center top;\">"
					+ "<tbody>" + "<tr>" + "<td>"
					+ "<table class=\"row-content stack\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 640px; margin: 0 auto;\" width=\"640\">"
					+ "<tbody>" + "<tr>"
					+ "<td class=\"column column-1\" width=\"100%\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 40px; padding-top: 40px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\">"
					+ "<div class=\"spacer_block block-1\" style=\"height:25px;line-height:25px;font-size:1px;\">&#8202;</div>"
					+ "<table class=\"heading_block block-2\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">"
					+ "<tr>"
					+ "<td class=\"pad\" style=\"padding-left:10px;padding-right:10px;text-align:center;width:100%;\">"
					+ "<h1 style=\"margin: 0; color: #ffffff; direction: ltr; font-family: Georgia, Times, 'Times New Roman', serif; font-size: 50px; font-weight: normal; line-height: 120%; text-align: center; margin-top: 0; margin-bottom: 0; mso-line-height-alt: 60px;\"><span class=\"tinyMce-placeholder\">Bosque Pokémon</span></h1>"
					+ "</td>" + "</tr>" + "</table>" + "</td>" + "</tr>" + "</tbody>" + "</table>" + "</td>" + "</tr>"
					+ "</tbody>" + "</table>"
					+ "<table class=\"row row-2\" align=\"center\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-position: center top;\">"
					+ "<tbody>" + "<tr>" + "<td>"
					+ "<table class=\"row-content stack\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 640px; margin: 0 auto;\" width=\"640\">"
					+ "<tbody>" + "<tr>"
					+ "<td class=\"column column-1\" width=\"100%\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\">"
					+ "<table class=\"image_block block-1\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">"
					+ "<tr>" + "<td class=\"pad\" style=\"width:100%;\">"
					+ "<div class=\"alignment\" align=\"center\" style=\"line-height:10px\">"
					+ "<div style=\"max-width: 640px;\"><img src=\"https://img.asmedia.epimg.net/resizer/v2/IIBECBP77FHVVIVA44UJMXWCNA.jpg?auth=a80efcfd83807d76749f71f77208cc32e504b8c6368886a043138efadd9f01f0&width=1472&height=828&smart=true\" style=\"display: block; height: auto; border: 0; width: 100%;\" width=\"640\" alt=\"Is This A Joke?\" title=\"Is This A Joke?\" height=\"auto\"></div>"
					+ "</div>" + "</td>" + "</tr>" + "</table>"
					+ "<div class=\"spacer_block block-2\" style=\"height:60px;line-height:60px;font-size:1px;\">&#8202;</div>"
					+ "<table class=\"heading_block block-3\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">"
					+ "<tr>"
					+ "<td class=\"pad\" style=\"padding-left:10px;padding-right:10px;text-align:center;width:100%;\">"
					+ "<h1 style=\"margin: 0; color: #ffffff; direction: ltr; font-family: Open Sans, Helvetica Neue, Helvetica, Arial, sans-serif; font-size: 50px; font-weight: normal; line-height: 120%; text-align: center; margin-top: 0; margin-bottom: 0; mso-line-height-alt: 60px;\"><strong>El codigo de verificación para cambio de contraseña es:</strong></h1>"
					+ "</td>" + "</tr>" + "</table>"
					+ "<table class=\"heading_block block-4\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">"
					+ "<tr>"
					+ "<td class=\"pad\" style=\"padding-left:10px;padding-right:10px;text-align:center;width:100%;\">"
					+ "<h1 style=\"margin: 0; color: #ffffff; direction: ltr; font-family: Open Sans, Helvetica Neue, Helvetica, Arial, sans-serif; font-size: 50px; font-weight: normal; line-height: 120%; text-align: center; margin-top: 0; margin-bottom: 0; mso-line-height-alt: 60px;\"><strong>"
					+ code + "</strong></h1>" + "</td>" + "</tr>" + "</table>"
					+ "<div class=\"spacer_block block-5\" style=\"height:30px;line-height:30px;font-size:1px;\">&#8202;</div>"
					+ "<div class=\"spacer_block block-6\" style=\"height:30px;line-height:30px;font-size:1px;\">&#8202;</div>"
					+ "<table class=\"paragraph_block block-7\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\">"
					+ "<tr>"
					+ "<td class=\"pad\" style=\"padding-bottom:10px;padding-left:20px;padding-right:20px;padding-top:10px;\">"
					+ "<div style=\"color:#393d47;font-family:'Open Sans','Helvetica Neue',Helvetica,Arial,sans-serif;font-size:16px;line-height:180%;text-align:center;mso-line-height-alt:28.8px;\">"
					+ "<p style=\"margin: 0; word-break: break-word;\"><span style=\"color: #ffb966;\">¡No compartas este codigo con nadie!.</span></p>"
					+ "</div>" + "</td>" + "</tr>" + "</table>"
					+ "<div class=\"spacer_block block-8\" style=\"height:30px;line-height:30px;font-size:1px;\">&#8202;</div>"
					+ "</td>" + "</tr>" + "</tbody>" + "</table>" + "</td>" + "</tr>" + "</tbody>" + "</table>"
					+ "<table class=\"row row-3\" align=\"center\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-position: top center;\">"
					+ "<tbody>" + "<tr>" + "<td>"
					+ "<table class=\"row-content stack\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; width: 640px; margin: 0 auto;\" width=\"640\">"
					+ "<tbody>" + "<tr>"
					+ "<td class=\"column column-1\" width=\"100%\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 10px; padding-top: 10px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\">"
					+ "<div class=\"spacer_block block-1\" style=\"height:40px;line-height:40px;font-size:1px;\">&#8202;</div>"
					+ "<div class=\"spacer_block block-2\" style=\"height:25px;line-height:25px;font-size:1px;\">&#8202;</div>"
					+ "<div class=\"spacer_block block-3\" style=\"height:40px;line-height:40px;font-size:1px;\">&#8202;</div>"
					+ "</td>" + "</tr>" + "</tbody>" + "</table>" + "</td>" + "</tr>" + "</tbody>" + "</table>"
					+ "<table class=\"row row-4\" align=\"center\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #ffffff;\">"
					+ "<tbody>" + "<tr>" + "<td>"
					+ "<table class=\"row-content stack\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000000; background-color: #ffffff; width: 640px; margin: 0 auto;\" width=\"640\">"
					+ "<tbody>" + "<tr>"
					+ "<td class=\"column column-1\" width=\"100%\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\">"
					+ "<table class=\"icons_block block-1\" width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; text-align: center;\">"
					+ "<tr>"
					+ "<td class=\"pad\" style=\"vertical-align: middle; color: #1e0e4b; font-family: 'Inter', sans-serif; font-size: 15px; padding-bottom: 5px; padding-top: 5px; text-align: center;\">"
					+ "<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\">"
					+ "<tr>"
					+ "<td class=\"alignment\" style=\"vertical-align: middle; text-align: center;\"><!--[if vml]><table align=\"center\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"display:inline-block;padding-left:0px;padding-right:0px;mso-table-lspace: 0pt;mso-table-rspace: 0pt;\"><![endif]-->"
					+ "<!--[if !vml]><!-->"
					+ "<table class=\"icons-inner\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; display: inline-block; margin-right: -4px; padding-left: 0px; padding-right: 0px;\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\"><!--<![endif]-->"
					+ "<tr>"
					+ "<td style=\"vertical-align: middle; text-align: center; padding-top: 5px; padding-bottom: 5px; padding-left: 5px; padding-right: 6px;\"><a href=\"http://designedwithbeefree.com/\" target=\"_blank\" style=\"text-decoration: none;\"><img class=\"icon\" alt=\"Beefree Logo\" src=\"https://d1oco4z2z1fhwp.cloudfront.net/assets/Beefree-logo.png\" height=\"auto\" width=\"34\" align=\"center\" style=\"display: block; height: auto; margin: 0 auto; border: 0;\"></a></td>"
					+ "<td style=\"font-family: 'Inter', sans-serif; font-size: 15px; font-weight: undefined; color: #1e0e4b; vertical-align: middle; letter-spacing: undefined; text-align: center;\"><a href=\"http://designedwithbeefree.com/\" target=\"_blank\" style=\"color: #1e0e4b; text-decoration: none;\">Designed with Beefree</a></td>"
					+ "</tr>" + "</table>" + "</td>" + "</tr>" + "</table>" + "</td>" + "</tr>" + "</table>" + "</td>"
					+ "</tr>" + "</tbody>" + "</table>" + "</td>" + "</tr>" + "</tbody>" + "</table>" + "</td>"
					+ "</tr>" + "</tbody>" + "</table><!-- End -->" + "</body>" + "" + "</html>";
			;
			helper.setText(msg, true);
			javaMailSender.send(message);
			return code;
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return "Email not found";

	}

	public void changedPassword(String to, String newPassword) {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		try {
			helper.setTo(to);
			helper.setSubject("New password changed");
			helper.setText("You have changed your password to: " + newPassword);
			javaMailSender.send(message);
		} catch (MessagingException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public int randomNumberInRange(int minimo, int maximo) {

		return ThreadLocalRandom.current().nextInt(minimo, maximo + 1);
	}

	public String randomString() {
		String banco = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		String cadena = "";
		int longitud = 20;
		for (int x = 0; x < longitud; x++) {
			int indiceAleatorio = randomNumberInRange(0, banco.length() - 1);
			char caracterAleatorio = banco.charAt(indiceAleatorio);
			cadena += caracterAleatorio;
		}
		return cadena;
	}

}