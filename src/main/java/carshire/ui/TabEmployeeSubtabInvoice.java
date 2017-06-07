package carshire.ui;

import carshire.ClientService;
import carshire.HireService;
import carshire.SellerService;
import carshire.domain.Client;
import carshire.domain.Hire;
import carshire.domain.Hire.HireStatus;
import carshire.domain.Seller;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TabEmployeeSubtabInvoice {

    @Autowired
    HireService hireService;

    @Autowired
    SellerService sellerService;

    @Autowired
    ClientService clientService;

    private CarsPresenter main;

    public void init(CarsPresenter carsPresenter) {
        main = carsPresenter;
    }

    //Employee tab - Invoice subtab
    @FXML
    TableView<Hire> hires;
    @FXML
    TableColumn<Hire, Long> clientIdColumn;
    @FXML
    TableColumn<Hire, Long> carIdColumn;
    @FXML
    TableColumn<Hire, Long> sellerIdColumn;
    @FXML
    TableColumn<Hire, LocalDateTime> dateColumn;
    @FXML
    TableColumn<Hire, LocalDateTime> endDateColumn;
    @FXML
    TableColumn<Hire, Hire.HireStatus> statusColumn;
    @FXML
    TableColumn<Hire, BigDecimal> priceForHireColumn;
    @FXML
    TableColumn<Hire, BigDecimal> defaultInterestColumn;
    @FXML
    TextField clientId, carId, sellerId, date, endDate, status, priceForHire, defaultInterest;
    @FXML
    Button directoryChooser;
    @FXML
    Button generate;
    @FXML
    TextField idInvoice;
    @FXML
    Label directory;
    
    String statusPdf;

    void configureTable() {
        clientIdColumn.setCellValueFactory(new PropertyValueFactory<Hire, Long>("clientId"));
        carIdColumn.setCellValueFactory(new PropertyValueFactory<Hire, Long>("carId"));
        sellerIdColumn.setCellValueFactory(new PropertyValueFactory<Hire, Long>("sellerId"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Hire, LocalDateTime>("hireDate"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<Hire, LocalDateTime>("hireEndDate"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<Hire, Hire.HireStatus>("status"));
        priceForHireColumn.setCellValueFactory(new PropertyValueFactory<Hire, BigDecimal>("priceForHire"));
        defaultInterestColumn.setCellValueFactory(new PropertyValueFactory<Hire, BigDecimal>("defaultInterest"));
    }

    @FXML
    public void btnGeneratePdf() throws DocumentException, IOException {
        if (!idInvoice.getText().isEmpty()
                && !directory.getText().isEmpty()) {

            String path = directory.getText() + "faktura" + idInvoice.getText() + ".pdf";
            createInvoicePdf(path);
        }
    }

    void fillTable() {
        configureTable();
        for (Hire hire : hireService.findAllHires()) {
            hires.getItems().add(hire);
        }
        hires.getSelectionModel().selectFirst();
    }

    void deleteAllViewRecords() {
        for (Hire hire : hireService.findAllHires()) {
            hires.getItems().remove(hire);
        }
    }

    void addAllViewRecords() {
        for (Hire hire : hireService.findAllHires()) {
            hires.getItems().add(hire);
        }
    }

    public void fillTextFields() {
        Hire hire = hires.getSelectionModel().getSelectedItem();
        idInvoice.setText(hire.getId().toString());
        directory.setText("");
    }

    public void createInvoicePdf(String result) throws DocumentException, IOException {
        Hire hire = hires.getSelectionModel().getSelectedItem();
        Seller seller = sellerService.findById(hire.getSellerId());
        Client client = clientService.findById(hire.getClientId());
        
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(result));

        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(result));

        document.open();

        BaseFont helvetica = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1250, BaseFont.EMBEDDED);
        Font helvetica16 = new Font(helvetica, 16);
        Font helvetica8 = new Font(helvetica, 8);

        Paragraph companyInfo = new Paragraph("Wypożyczalnia samochodów", helvetica16);
        Paragraph documentInfo = new Paragraph("Faktura", helvetica16);
        companyInfo.setAlignment(Element.ALIGN_RIGHT);
        documentInfo.setAlignment(Element.ALIGN_RIGHT);
        document.add(companyInfo);
        document.add(documentInfo);

        Image companyLogo = Image.getInstance("logo.png");
        companyLogo.scalePercent(15);
        document.add(companyLogo);

        Paragraph clientName = new Paragraph("Klient: " + client.getFirstName() + " " + client.getLastName(), helvetica8);
        Paragraph sellerName = new Paragraph("Sprzedawca: " + seller.getFirstName() + " " + seller.getLastName(), helvetica8);
        Paragraph hireDateName = new Paragraph("Data faktury: " + LocalDateTime.now(), helvetica8);
        Paragraph emptySpace = new Paragraph(" ");
        clientName.setAlignment(Element.ALIGN_RIGHT);
        sellerName.setAlignment(Element.ALIGN_RIGHT);
        hireDateName.setAlignment(Element.ALIGN_RIGHT);
        emptySpace.setAlignment(Element.ALIGN_RIGHT);
        document.add(clientName);
        document.add(sellerName);
        document.add(hireDateName);
        document.add(emptySpace);

        PdfPTable table = new PdfPTable(9);
        table.setWidthPercentage(100);
        float[] columnWidths = {0.5f, 1f, 1f, 1f, 2f, 2f, 2f, 2f, 2f};
        table.setWidths(columnWidths);

        PdfPCell cell1 = new PdfPCell(new Paragraph("Id", helvetica8));
        PdfPCell cell2 = new PdfPCell(new Paragraph("Id klienta", helvetica8));
        PdfPCell cell3 = new PdfPCell(new Paragraph("Id samochodu", helvetica8));
        PdfPCell cell4 = new PdfPCell(new Paragraph("Id sprzedawcy", helvetica8));
        PdfPCell cell5 = new PdfPCell(new Paragraph("Data wypożyczenia", helvetica8));
        PdfPCell cell6 = new PdfPCell(new Paragraph("Koniec wypożyczenia", helvetica8));
        PdfPCell cell7 = new PdfPCell(new Paragraph("Status", helvetica8));
        PdfPCell cell8 = new PdfPCell(new Paragraph("Opłata", helvetica8));
        PdfPCell cell9 = new PdfPCell(new Paragraph("Odsetki", helvetica8));

        PdfPCell row1 = new PdfPCell(new Paragraph(hire.getId().toString(), helvetica8));
        PdfPCell row2 = new PdfPCell(new Paragraph(hire.getClientId().toString(), helvetica8));
        PdfPCell row3 = new PdfPCell(new Paragraph(hire.getCarId().toString(), helvetica8));
        PdfPCell row4 = new PdfPCell(new Paragraph(hire.getSellerId().toString(), helvetica8));
        PdfPCell row5 = new PdfPCell(new Paragraph(hire.getHireDate().toString(), helvetica8));
        PdfPCell row6 = new PdfPCell(new Paragraph(hire.getHireEndDate().toString(), helvetica8));
        
        if(hire.getStatus().equals(HireStatus.Paid)){
            statusPdf = "Opłacone";
        }else{
            statusPdf = "Nie opłacone";
        }
        
        PdfPCell row7 = new PdfPCell(new Paragraph(statusPdf, helvetica8));
        PdfPCell row8 = new PdfPCell(new Paragraph(hire.getPriceForHire().toString(), helvetica8));
        PdfPCell row9 = new PdfPCell(new Paragraph(hire.getDefaultInterest().toString(), helvetica8));

        table.addCell(cell1);
        table.addCell(cell2);
        table.addCell(cell3);
        table.addCell(cell4);
        table.addCell(cell5);
        table.addCell(cell6);
        table.addCell(cell7);
        table.addCell(cell8);
        table.addCell(cell9);
        table.addCell(row1);
        table.addCell(row2);
        table.addCell(row3);
        table.addCell(row4);
        table.addCell(row5);
        table.addCell(row6);
        table.addCell(row7);
        table.addCell(row8);
        table.addCell(row9);

        document.add(table);

        document.close();
        
        directory.setText("Zapisano: " + result);
    }

}
