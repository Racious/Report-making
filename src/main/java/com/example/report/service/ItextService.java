package com.example.report.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.report.dao.EmployeeDao;
import com.example.report.vo.Employee;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


@Service
public class ItextService {

	@Autowired
	EmployeeDao employeeDao;

	public void createRpt() throws IOException, DocumentException {
		Document document = new Document();
		OutputStream os = new FileOutputStream(new File("table.pdf"));
		BaseFont baseFont = BaseFont.createFont("D:/FiraCode-Regular.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
		Font font = new Font(baseFont, 10, Font.NORMAL, BaseColor.BLACK);
		PdfWriter.getInstance(document, os);

		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(60);
		table.setWidths(new int[] { 1, 3, 3 });
		Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		PdfPCell hcell;
		hcell = new PdfPCell(new Phrase("Id", headFont));
		hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(hcell);
		hcell = new PdfPCell(new Phrase("Name", headFont));
		hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(hcell);
		hcell = new PdfPCell(new Phrase("Phone", headFont));
		hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(hcell);

		List<Employee> emps = employeeDao.getAll();
		for (Employee emp : emps) {
			PdfPCell cell;
			cell = new PdfPCell(new Phrase(emp.getId().toString(), font));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(emp.getName(), font));
			cell.setPaddingLeft(5);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(cell);
			cell = new PdfPCell(new Phrase(emp.getPhone(), font));
			cell.setPaddingLeft(5);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(cell);
		}
		document.open();
		document.add(table);
		document.close();

	}

}


/*
 * 
 * Document類：等於是一個PDF文件，類似於File的存在。
 * PdfWriter類：看名字就知道啦，Writer，用於寫文件。
 * Paragraph類：類名翻譯過來是「段落」，一般是代表一段文字（小學學的「自然段」），其實也可以包含一些非文字的內容。
 * 
 * 	Paragraph.setIndentationLeft(float indentation)：整體縮進
 *	Paragraph.setFirstLineIndent(float firstLineIndent)：首行縮進
 *	Paragraph.setAlignment(int alignment)：設置對齊方式，Paragraph常用的對齊方式有3種
 *	Element.ALIGN_LEFT：左對齊
 *	Element.ALIGN_CENTER：居中
 *	Element.ALIGN_RIGHT：右對齊
 * 
 * */
