package com.wit.iot;

import java.io.*;
    import org.apache.poi.xwpf.usermodel.XWPFDocument;
    import org.apache.poi.xwpf.usermodel.XWPFParagraph;
    import org.apache.poi.xwpf.usermodel.XWPFRun;
    import com.itextpdf.text.pdf.PdfReader;
    import com.itextpdf.text.pdf.parser.PdfTextExtractor;

    public class PdfToWord {
        public static void main(String[] args) throws Exception {
            // load PDF document
            PdfReader pdfReader = new PdfReader("D:\\Users\\cepai\\Desktop\\123.pdf");
            // extract text from PDF document
            String text = PdfTextExtractor.getTextFromPage(pdfReader, 1);

            // create Word document
            XWPFDocument document = new XWPFDocument();
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();
            // set text to Word document
            run.setText(text);

            // save Word document
            FileOutputStream out = new FileOutputStream("D:\\Users\\cepai\\Desktop\\123_2.docx");
            document.write(out);
            out.close();
            document.close();
        }
    }