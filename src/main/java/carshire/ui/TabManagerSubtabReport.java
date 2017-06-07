package carshire.ui;

import carshire.HireService;
import carshire.SellerService;
import carshire.domain.Seller;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TabManagerSubtabReport {

    @Autowired
    SellerService sellerService;

    @Autowired
    HireService hireService;

    private CarsPresenter main;

    @FXML
    Label directory;
    @FXML
    Button directoryChooser;
    @FXML
    Button generate;

    public void init(CarsPresenter carsPresenter) {
        main = carsPresenter;
    }

    @FXML
    public void btnGeneratePdf() throws DocumentException, IOException {
        if (!directory.getText().isEmpty()) {

            String path = directory.getText() + "raport.pdf";
            createReportPdf(path);
        }
    }

    public void createReportPdf(String result) throws DocumentException, IOException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(result));

        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(result));

        document.open();

        BaseFont helvetica = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1250, BaseFont.EMBEDDED);
        Font helvetica16 = new Font(helvetica, 16);
        Font helvetica8 = new Font(helvetica, 8);

        Paragraph companyInfo = new Paragraph("Wypożyczalnia samochodów", helvetica16);
        Paragraph documentInfo = new Paragraph("Raport sprzedaży", helvetica16);
        companyInfo.setAlignment(Element.ALIGN_RIGHT);
        documentInfo.setAlignment(Element.ALIGN_RIGHT);
        document.add(companyInfo);
        document.add(documentInfo);

        Image companyLogo = Image.getInstance("logo.png");
        companyLogo.scalePercent(15);
        document.add(companyLogo);

        Paragraph hireDateName = new Paragraph("Data wygenerowania raportu: " + LocalDateTime.now(), helvetica8);
        Paragraph emptySpace = new Paragraph(" ");
        hireDateName.setAlignment(Element.ALIGN_RIGHT);
        emptySpace.setAlignment(Element.ALIGN_RIGHT);
        document.add(hireDateName);
        document.add(emptySpace);

        Paragraph bestSellers = new Paragraph("Sprzedawcy i liczba ich wypożyczeń: ", helvetica16);
        document.add(bestSellers);

        List<Seller> sellers = sellerService.findAllSellers();
        List<Long> nubmerOfHires = new ArrayList();

        sellers.stream().forEach((Seller seller) -> nubmerOfHires.add(hireService.countBySellerId(seller.getId())));

        for (int i = 0; i < sellers.size(); i++) {
            document.add(new Paragraph(sellers.get(i).getFirstName()
                    + " " + sellers.get(i).getLastName()
                    + " " + nubmerOfHires.get(i), helvetica16));
        }

        document.add(emptySpace);
        Paragraph bestSellersMoney = new Paragraph("Sprzedawcy i ich utarg: ", helvetica16);
        document.add(bestSellersMoney);

        List<BigDecimal> sumOfEmployeeEarnings = new ArrayList();
        sellers.stream().forEach((Seller seller) -> sumOfEmployeeEarnings.add(hireService.findSumOfEmployeeEarnings((seller.getId()))));
        
        for (int i = 0; i < sellers.size(); i++) {
            document.add(new Paragraph(sellers.get(i).getFirstName()
                    + " " + sellers.get(i).getLastName()
                    + " " + sumOfEmployeeEarnings.get(i) + " złotych", helvetica16));
        }

        document.close();

        directory.setText("Zapisano: " + result);
    }
}
