package tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;

import incometaxcalculator.data.io.TXTFileReader;
import incometaxcalculator.data.io.TXTInfoWriter;
import incometaxcalculator.data.management.Receipt;
import incometaxcalculator.data.management.Taxpayer;
import incometaxcalculator.data.management.TaxpayerManager;
import incometaxcalculator.exceptions.WrongFileFormatException;
import incometaxcalculator.exceptions.WrongReceiptDateException;
import incometaxcalculator.exceptions.WrongReceiptKindException;
import incometaxcalculator.exceptions.WrongTaxpayerStatusException;

import org.junit.Test;

public class OutputFacilitiesTxtInfoTest {

  @Test
  public void testTxtInfoWriter() throws WrongTaxpayerStatusException, WrongReceiptKindException, WrongReceiptDateException, 
                                                        IOException, NumberFormatException, WrongFileFormatException {
    TaxpayerManager taxpayerMan = new TaxpayerManager();
    taxpayerMan.createTaxpayer("Apostolos Zarras", 123456789, "Married Filing Jointly" , (float)22570.0);
    
    taxpayerMan.createReceipt(1, "10/5/1996", (float)251.0, "Basic",
        "Parta", "Jakusa", "Drama", "Fukosima", 5, 123456789);
    
    taxpayerMan.createReceipt(2, "12/12/2015", (float)15.0, "Basic",
        "LOL", "Greece", "Ioannina", "Napolewn Zerva", 12, 123456789);
      
    taxpayerMan.createReceipt(4, "11/11/2111", (float)1000.0, "Other",
        "ewre", "werw", "ewr", "were", 4, 123456789);
    
    taxpayerMan.createReceipt(5, "2/2/2019", (float)100.0, "Travel",
        "sfsdf", "sdfssdf", "sdfsd", "sdfs", 1, 123456789);
    
    TXTInfoWriter txtInfoWriter = new TXTInfoWriter();
    txtInfoWriter.generateFile(123456789);
    
    /////////////////////////////////////////////////
    
    TXTFileReader txtFileReader = new TXTFileReader();
    txtFileReader.readFile("123456789_INFO.txt");
    
    TaxpayerManager taxpayerManager = new TaxpayerManager();
    Taxpayer taxpayer = taxpayerManager.getTaxpayer(123456789);
    
    assertEquals(taxpayer.getFullname(), "Apostolos Zarras");
    assertEquals(taxpayer.getTaxRegistrationNumber(), 123456789);
    assertEquals(taxpayer.getIncome(), 22570.0, 0);
    assertEquals(taxpayer.getClass().getName(), "incometaxcalculator.data.management.Taxpayer");
    
    HashMap<Integer, Receipt> receipts = taxpayer.getReceiptHashMap();
    
    Receipt receipt1 = receipts.get(1);
    assertEquals(receipt1.getId(), 1);
    assertEquals(receipt1.getAmount(), 251.0, 0);
    assertEquals(receipt1.getKind(), "Basic");
    assertEquals(receipt1.getIssueDate(), "10/5/1996");
    assertEquals(receipt1.getCompany(), "Parta");
    assertEquals(receipt1.getCompanyAddress().getCountry(), "Jakusa");
    assertEquals(receipt1.getCompanyAddress().getCity(), "Drama");
    assertEquals(receipt1.getCompanyAddress().getStreet(), "Fukosima");
    assertEquals(receipt1.getCompanyAddress().getNumber(), 5);
    
    Receipt receipt2 = receipts.get(2);
    assertEquals(receipt2.getId(), 2);
    assertEquals(receipt2.getAmount(), 15.0, 0);
    assertEquals(receipt2.getKind(), "Basic");
    assertEquals(receipt2.getIssueDate(), "12/12/2015");
    assertEquals(receipt2.getCompany(), "LOL");
    assertEquals(receipt2.getCompanyAddress().getCountry(), "Greece");
    assertEquals(receipt2.getCompanyAddress().getCity(), "Ioannina");
    assertEquals(receipt2.getCompanyAddress().getStreet(), "Napolewn Zerva");
    assertEquals(receipt2.getCompanyAddress().getNumber(), 12);
    
    Receipt receipt3 = receipts.get(4);
    assertEquals(receipt3.getId(), 4);
    assertEquals(receipt3.getAmount(), 1000.0, 0);
    assertEquals(receipt3.getKind(), "Other");
    assertEquals(receipt3.getIssueDate(), "11/11/2111");
    assertEquals(receipt3.getCompany(), "ewre");
    assertEquals(receipt3.getCompanyAddress().getCountry(), "werw");
    assertEquals(receipt3.getCompanyAddress().getCity(), "ewr");
    assertEquals(receipt3.getCompanyAddress().getStreet(), "were");
    assertEquals(receipt3.getCompanyAddress().getNumber(), 4);
    
    Receipt receipt4 = receipts.get(5);
    assertEquals(receipt4.getId(), 5);
    assertEquals(receipt4.getAmount(), 100.0, 0);
    assertEquals(receipt4.getKind(), "Travel");
    assertEquals(receipt4.getIssueDate(), "2/2/2019");
    assertEquals(receipt4.getCompany(), "sfsdf");
    assertEquals(receipt4.getCompanyAddress().getCountry(), "sdfssdf");
    assertEquals(receipt4.getCompanyAddress().getCity(), "sdfsd");
    assertEquals(receipt4.getCompanyAddress().getStreet(), "sdfs");
    assertEquals(receipt4.getCompanyAddress().getNumber(), 1);
  }

}
