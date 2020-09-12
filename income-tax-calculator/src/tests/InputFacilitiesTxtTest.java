package tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;

import incometaxcalculator.data.io.TXTFileReader;
import incometaxcalculator.data.management.Receipt;
import incometaxcalculator.data.management.Taxpayer;
import incometaxcalculator.data.management.TaxpayerManager;
import incometaxcalculator.exceptions.WrongFileEndingException;
import incometaxcalculator.exceptions.WrongFileFormatException;
import incometaxcalculator.exceptions.WrongReceiptDateException;
import incometaxcalculator.exceptions.WrongReceiptKindException;
import incometaxcalculator.exceptions.WrongTaxpayerStatusException;

import org.junit.Test;

public class InputFacilitiesTxtTest {

  @Test
  public void testTXTFileReader() throws IOException, WrongReceiptDateException, WrongReceiptKindException,
                                               WrongFileFormatException, WrongTaxpayerStatusException, WrongFileEndingException {
      TXTFileReader txtFileReader = new TXTFileReader();
      txtFileReader.readFile("123456789_INFO.txt");
      
      TaxpayerManager taxpayerManager = new TaxpayerManager();
      Taxpayer taxpayer = taxpayerManager.getTaxpayer(123456789);
      
      assertEquals(taxpayer.getFullname(), "Apostolos Zarras");
      assertEquals(taxpayer.getTaxRegistrationNumber(), 123456789, 0);
      assertEquals(taxpayer.getIncome(), 22570.0, 0);
      assertEquals(taxpayer.getClass().getName(), "incometaxcalculator.data.management.Taxpayer");
      
      HashMap<Integer, Receipt> receipts = taxpayer.getReceiptHashMap();
      
      Receipt receipt1 = receipts.get(1);
      assertEquals(receipt1.getId(), 1, 0);
      assertEquals(receipt1.getAmount(), 251.0, 0);
      assertEquals(receipt1.getKind(), "Basic");
      assertEquals(receipt1.getIssueDate(), "10/5/1996");
      assertEquals(receipt1.getCompany(), "Parta");
      assertEquals(receipt1.getCompanyAddress().getCountry(), "Jakusa");
      assertEquals(receipt1.getCompanyAddress().getCity(), "Drama");
      assertEquals(receipt1.getCompanyAddress().getStreet(), "Fukosima");
      assertEquals(receipt1.getCompanyAddress().getNumber(), 5);
      
      Receipt receipt2 = receipts.get(2);
      assertEquals(receipt2.getId(), 2, 0);
      assertEquals(receipt2.getAmount(), 15.0, 0);
      assertEquals(receipt2.getKind(), "Basic");
      assertEquals(receipt2.getIssueDate(), "12/12/2015");
      assertEquals(receipt2.getCompany(), "LOL");
      assertEquals(receipt2.getCompanyAddress().getCountry(), "Greece");
      assertEquals(receipt2.getCompanyAddress().getCity(), "Ioannina");
      assertEquals(receipt2.getCompanyAddress().getStreet(), "Napolewn Zerva");
      assertEquals(receipt2.getCompanyAddress().getNumber(), 12, 0);
      
      Receipt receipt3 = receipts.get(4);
      assertEquals(receipt3.getId(), 4, 0);
      assertEquals(receipt3.getAmount(), 1000.0, 0);
      assertEquals(receipt3.getKind(), "Other");
      assertEquals(receipt3.getIssueDate(), "11/11/2111");
      assertEquals(receipt3.getCompany(), "ewre");
      assertEquals(receipt3.getCompanyAddress().getCountry(), "werw");
      assertEquals(receipt3.getCompanyAddress().getCity(), "ewr");
      assertEquals(receipt3.getCompanyAddress().getStreet(), "were");
      assertEquals(receipt3.getCompanyAddress().getNumber(), 4, 0);
      
      Receipt receipt4 = receipts.get(5);
      assertEquals(receipt4.getId(), 5, 0);
      assertEquals(receipt4.getAmount(), 100.0, 0);
      assertEquals(receipt4.getKind(), "Travel");
      assertEquals(receipt4.getIssueDate(), "2/2/2019");
      assertEquals(receipt4.getCompany(), "sfsdf");
      assertEquals(receipt4.getCompanyAddress().getCountry(), "sdfssdf");
      assertEquals(receipt4.getCompanyAddress().getCity(), "sdfsd");
      assertEquals(receipt4.getCompanyAddress().getStreet(), "sdfs");
      assertEquals(receipt4.getCompanyAddress().getNumber(), 1, 0);
  }

}
