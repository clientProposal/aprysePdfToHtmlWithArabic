package com.poc;
import io.github.cdimascio.dotenv.Dotenv;
import com.pdftron.pdf.*;
import com.pdftron.common.PDFNetException;


public class App {
    public static void main( String[] args )
    {

        Dotenv dotenv = Dotenv.configure()
        .directory("./")
        .ignoreIfMissing()
        .load();
        String token = dotenv.get("PDFTRON_KEY");
        PDFNet.initialize(token);
        String inputPath = System.getProperty("user.dir") + "/src/main/java/resources/arabic_original.pdf";
        String outputPath = System.getProperty("user.dir") + "/src/main/java/resources/output/arabic_processed_from_pdf_to_html.html";
        boolean err = false;
        System.err.println(inputPath);
        System.err.println(outputPath);
        try {
            System.out.println("Converting PDF to HTML with fixed positioning option turned on (default)");
            Convert.toHtml(inputPath, outputPath);
        } catch (PDFNetException e) {
            System.out.println("Unable to convert PDF document to HTML, error: ");
            System.out.println(e);
            err = true;
        }  catch (Exception e) {
            System.out.println("Unknown Exception, error: ");
            System.out.println(e);
            err = true;
        }        
        try {
            PDFNet.initialize(token);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
