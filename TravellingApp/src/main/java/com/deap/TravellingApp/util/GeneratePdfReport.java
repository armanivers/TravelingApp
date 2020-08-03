package com.deap.TravellingApp.util;

import com.deap.TravellingApp.model.Booking;
import com.deap.TravellingApp.model.BookingActivity;
import com.deap.TravellingApp.model.User;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.thymeleaf.util.StringUtils;

public class GeneratePdfReport {

	public static ByteArrayInputStream bookingSummary(Booking b) {

		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.BOLD);
		Paragraph aB = new Paragraph("\n");

		try {

			PdfWriter.getInstance(document, out);
			document.open();

			Chunk start1 = new Chunk("Dear ");
			Chunk start2 = new Chunk(StringUtils.capitalize(b.getUser().getUsername())+",", boldFont);
			Paragraph start = new Paragraph(start1);
			start.add(start2);

			Paragraph info = new Paragraph(
					"\n On this site we would like to list your choosen parameters for this vacation.");
			Paragraph target = new Paragraph("Your choosen destination is: \n");

			Chunk city1 = new Chunk("          City: ");
			Chunk city2 = new Chunk(b.getDestination().getCity() + "\n", boldFont);
			Paragraph city = new Paragraph(city1);
			city.add(city2);

			Chunk country1 = new Chunk("          Country: ");
			Chunk country2 = new Chunk(b.getDestination().getCountry() + "\n", boldFont);
			Paragraph country = new Paragraph(country1);
			country.add(country2);

			Chunk hotel1 = new Chunk("          Hotel: ");
			Chunk hotel2 = new Chunk(b.getHotel().getName(), boldFont);
			Paragraph hotel = new Paragraph(hotel1);
			hotel.add(hotel2);
			
			Chunk price1 = new Chunk("          Price: ");
			Chunk price2 = new Chunk(b.getPrice()+" \u20ac", boldFont);
			Paragraph price= new Paragraph(price1);
			price.add(price2);

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.LLLL.yyyy");
			Chunk date1 = new Chunk("Your vacation will start on the ");
			Chunk date2 = new Chunk(b.getDateFrom().format(formatter), boldFont);
			Chunk date3 = new Chunk(" and will end at the ");
			Chunk date4 = new Chunk(b.getDateFrom().format(formatter), boldFont);
			Paragraph date = new Paragraph(date1);
			date.add(date2);
			date.add(date3);
			date.add(date4);

			Chunk person1 = new Chunk("You will take this vacation with ");
			Chunk person2 = new Chunk(Integer.toString(b.getPersonCount() - 1), boldFont);
			Chunk person3 = new Chunk(" other person(s)");
			Paragraph person = new Paragraph(person1);
			person.add(person2);
			person.add(person3);

			Image logo = Image.getInstance("src/main/resources/static/img/logo.png");
			logo.scaleAbsolute(250f, 100f);
			logo.setAlignment(Element.ALIGN_RIGHT);
			document.add(logo);
			document.add(aB);
			document.add(aB);
			document.add(start);
			document.add(info);
			document.add(aB);
			document.add(target);
			document.add(aB);
			document.add(city);
			document.add(country);
			document.add(hotel);
			document.add(price);
			document.add(aB);
			document.add(date);
			document.add(aB);
			document.add(person);

			for (int i = 0; i < 2; i++) {
				document.add(aB);
			}

			int counter = 1;

			for (BookingActivity a : b.getSortedActivitiesAsc()) {

				Chunk activity1 = new Chunk("Your " + counter + ". booked activity is: ");
				Chunk activity2 = new Chunk(a.getActivityAvailable().getActivity().getName(), boldFont);
				Chunk activity3 = new Chunk(" for ");
				Chunk activity4 = new Chunk(Integer.toString(a.getBookedSlots()), boldFont);
				Chunk activity5 = new Chunk(" person(s).\n It will take place in ");
				Chunk activity6 = new Chunk(a.getActivityAvailable().getActivity().getDestination().getCity(),
						boldFont);
				Chunk activity7 = new Chunk(" on ");
				Chunk activity8 = new Chunk(a.getActivityAvailable().getDate().format(formatter), boldFont);
				Chunk activity9 = new Chunk(" between ");
				Chunk activity10 = new Chunk(a.getActivityAvailable().getStartTimeString(), boldFont);
				Chunk activity11 = new Chunk(" and ");
				Chunk activity12 = new Chunk(a.getActivityAvailable().getEndTimeString(), boldFont);
				Chunk activity13 = new Chunk(".\n It's provided by: ");
				Chunk activity14 = new Chunk(a.getActivityAvailable().getActivity().getProvider(), boldFont);

				Paragraph activity = new Paragraph(activity1);
				activity.add(activity2);
				activity.add(activity3);
				activity.add(activity4);
				activity.add(activity5);
				activity.add(activity6);
				activity.add(activity7);
				activity.add(activity8);
				activity.add(activity9);
				activity.add(activity10);
				activity.add(activity11);
				activity.add(activity12);
				activity.add(activity13);
				activity.add(activity14);

				document.add(activity);
				document.add(aB);

				counter++;
			}

			document.add(aB);

			Image msg = Image.getInstance("src/main/resources/static/img/message.png");
			msg.scaleAbsolute(500f, 100f);
			msg.setAlignment(Element.ALIGN_LEFT);

			Image sign = Image.getInstance("src/main/resources/static/img/signature.png");
			sign.scaleAbsolute(100f, 66f);
			sign.setAlignment(Element.ALIGN_RIGHT);

			document.add(msg);
			document.add(sign);
			document.close();

		} catch (DocumentException ex) {
			Logger.getLogger(GeneratePdfReport.class.getName()).log(Level.SEVERE, null, ex);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new ByteArrayInputStream(out.toByteArray());
	}

	public static ByteArrayInputStream usersReport(List<User> users) {

		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {

			PdfPTable table = new PdfPTable(3);
			table.setWidthPercentage(60);
			table.setWidths(new int[] { 1, 3, 3 });

			Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

			PdfPCell hcell;
			hcell = new PdfPCell(new Phrase("Id", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.BLUE);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Username", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			hcell.setBackgroundColor(BaseColor.RED);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Password", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			for (User user : users) {

				PdfPCell cell;

				cell = new PdfPCell(new Phrase(user.getId().toString()));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(user.getUsername()));
				cell.setPaddingLeft(5);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(user.getPassword()));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(5);
				table.addCell(cell);
			}

			PdfWriter.getInstance(document, out);
			document.open();
			document.add(table);

			// Image options Example
			// Image URL
			Image img = Image.getInstance("src/main/resources/static/img/logo.png");
			// optional image properties like scale, rotation, position and alignment
			img.scaleAbsolute(500f, 100f);
			// img.setRotation(270f);
			// img.setAbsolutePosition(200f, 10f);
			img.setAlignment(Element.ALIGN_CENTER);
			// simple title above image
			Paragraph pg = new Paragraph("Team DEAP wishes you a nice vacation!");
			pg.setAlignment(Element.ALIGN_CENTER);
			// add header + image (in this order) to document
			document.add(pg);
			document.add(img);

			document.close();

		} catch (DocumentException ex) {
			Logger.getLogger(GeneratePdfReport.class.getName()).log(Level.SEVERE, null, ex);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new ByteArrayInputStream(out.toByteArray());
	}
}