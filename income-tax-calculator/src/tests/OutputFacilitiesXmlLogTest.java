package tests;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;

import incometaxcalculator.data.io.XMLLogWriter;
import incometaxcalculator.data.management.TaxpayerManager;
import incometaxcalculator.exceptions.WrongFileFormatException;
import incometaxcalculator.exceptions.WrongReceiptDateException;
import incometaxcalculator.exceptions.WrongReceiptKindException;
import incometaxcalculator.exceptions.WrongTaxpayerStatusException;

import org.junit.Test;

public class OutputFacilitiesXmlLogTest {

  @Test
  public void testXmlLogWriter() throws WrongTaxpayerStatusException, WrongReceiptKindException, WrongReceiptDateException, 
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
    
    XMLLogWriter xmlLogWriter = new XMLLogWriter();
    xmlLogWriter.generateFile(123456789);
    
    /////////////////////////////////////////////////
    
    BufferedReader inputStream = new BufferedReader(new java.io.FileReader("123456789_LOG.xml"));
    assertEquals(inputStream.readLine(), "<Name> Apostolos Zarras </Name>");
    assertEquals(inputStream.readLine(), "<AFM> 123456789 </AFM>");
    assertEquals(inputStream.readLine(), "<Income> 22570.0 </Income>");
    assertEquals(inputStream.readLine(), "<BasicTax> 1207.495 </BasicTax>");
    assertEquals(inputStream.readLine(), "<TaxIncrease> 96.5996 </TaxIncrease>");
    assertEquals(inputStream.readLine(), "<TotalTax> 1304.0946 </TotalTax>");
    assertEquals(inputStream.readLine(), "<Receipts> 4 </Receipts>");
    assertEquals(inputStream.readLine(), "<Entertainment> 0.0 </Entertainment>");
    assertEquals(inputStream.readLine(), "<Basic> 266.0 </Basic>");
    assertEquals(inputStream.readLine(), "<Travel> 100.0 </Travel>");
    assertEquals(inputStream.readLine(), "<Health> 0.0 </Health>");
    assertEquals(inputStream.readLine(), "<Other> 1000.0 </Other>");
    inputStream.close();
  }

}
