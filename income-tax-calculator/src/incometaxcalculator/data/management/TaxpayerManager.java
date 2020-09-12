package incometaxcalculator.data.management;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import incometaxcalculator.data.io.MyFileReader;
import incometaxcalculator.data.io.MyFileReaderFactory;
import incometaxcalculator.data.io.InfoWriterFactory;
import incometaxcalculator.data.io.LogWriterFactory;
import incometaxcalculator.data.io.MyFileWriter;
import incometaxcalculator.exceptions.ReceiptAlreadyExistsException;
import incometaxcalculator.exceptions.WrongFileEndingException;
import incometaxcalculator.exceptions.WrongFileFormatException;
import incometaxcalculator.exceptions.WrongReceiptDateException;
import incometaxcalculator.exceptions.WrongReceiptKindException;
import incometaxcalculator.exceptions.WrongTaxpayerStatusException;

public class TaxpayerManager {

  private static HashMap<Integer, Taxpayer> taxpayerHashMap = new HashMap<Integer, Taxpayer>(0);
  private static HashMap<Integer, Integer> receiptOwnerTRN = new HashMap<Integer, Integer>(0);

  public void createTaxpayer(String fullname, int taxRegistrationNumber, String status, double income) {
      new TaxpayerFactory();
      taxpayerHashMap.put(taxRegistrationNumber,
          TaxpayerFactory.createTaxpayer(fullname, taxRegistrationNumber, income, status));
  }

  public void createReceipt(int receiptId, String issueDate, float amount, String type,
      String companyName, String country, String city, String street, int number,
      int taxRegistrationNumber) throws WrongReceiptKindException, WrongReceiptDateException {

    Receipt receipt = new Receipt(receiptId, issueDate, amount, type, companyName, new Address(country, city, street, number));
    taxpayerHashMap.get(taxRegistrationNumber).addReceipt(receipt);
    receiptOwnerTRN.put(receiptId, taxRegistrationNumber);
  }

  public void removeTaxpayer(int taxRegistrationNumber) {
    Taxpayer taxpayer = taxpayerHashMap.get(taxRegistrationNumber);
    taxpayerHashMap.remove(taxRegistrationNumber);
    HashMap<Integer, Receipt> receiptsHashMap = taxpayer.getReceiptHashMap();
    Iterator<HashMap.Entry<Integer, Receipt>> iterator = receiptsHashMap.entrySet().iterator();
    while (iterator.hasNext()) {
      HashMap.Entry<Integer, Receipt> entry = iterator.next();
      Receipt receipt = entry.getValue();
      receiptOwnerTRN.remove(receipt.getId());
    }
  }

  public void addReceipt(int receiptId, String issueDate, float amount, String kind,
      String companyName, String country, String city, String street, int number,
      int taxRegistrationNumber) throws IOException, WrongReceiptKindException,
      WrongReceiptDateException, ReceiptAlreadyExistsException {

    if (containsReceipt(receiptId)) {
      throw new ReceiptAlreadyExistsException();
    }
    createReceipt(receiptId, issueDate, amount, kind, companyName, country, city, street, number,
        taxRegistrationNumber);
    updateFiles(taxRegistrationNumber);
  }

  public void removeReceipt(int receiptId) throws IOException, WrongReceiptKindException {
    taxpayerHashMap.get(receiptOwnerTRN.get(receiptId)).removeReceipt(receiptId);
    updateFiles(receiptOwnerTRN.get(receiptId));
    receiptOwnerTRN.remove(receiptId);
  }

  private void updateFiles(int taxRegistrationNumber) throws IOException {
    InfoWriterFactory.createInfoWriter(taxRegistrationNumber);
  }

  public void saveLogFile(int taxRegistrationNumber, String fileFormat)
      throws IOException, WrongFileFormatException {
    if(isValidFormat(fileFormat)) {
      MyFileWriter writer = LogWriterFactory.createLogWriter(taxRegistrationNumber, fileFormat);
      writer.generateFile(taxRegistrationNumber);
    }
    else
      throw new WrongFileFormatException();
    
  }
  
  private boolean isValidFormat(String fileFormat) {
    return fileFormat.equals("txt") || fileFormat.equals("xml");
  }

  public boolean containsTaxpayer(int taxRegistrationNumber) {
    if (taxpayerHashMap.containsKey(taxRegistrationNumber)) {
      return true;
    }
    return false;
  }

  public boolean containsTaxpayer() {
    if (taxpayerHashMap.isEmpty()) {
      return false;
    }
    return true;
  }

  public boolean containsReceipt(int id) {
    if (receiptOwnerTRN.containsKey(id)) {
      return true;
    }
    return false;

  }

  public Taxpayer getTaxpayer(int taxRegistrationNumber) {
    return taxpayerHashMap.get(taxRegistrationNumber);
  }

  public void loadTaxpayer(String fileName)
      throws NumberFormatException, IOException, WrongFileFormatException, WrongFileEndingException,
      WrongTaxpayerStatusException, WrongReceiptKindException, WrongReceiptDateException {

    if(checkIfTypeIsValid(getTypeOfFile(fileName))) {
      MyFileReader reader =  MyFileReaderFactory.createFileReader(getTypeOfFile(fileName));
      reader.readFile(getTypeOfFile(fileName));
    } 
    else
      throw new WrongFileEndingException();
  }
  
  private String getTypeOfFile(String fileName) {
    String ending[] = fileName.split("\\.");
    return ending[1];
  }
  
  private boolean checkIfTypeIsValid(String type) {
    return type.equals("txt") || type.equals("xml");
  }

  public String getTaxpayerName(int taxRegistrationNumber) {
    return taxpayerHashMap.get(taxRegistrationNumber).getFullname();
  }

  public String getTaxpayerStatus(int taxRegistrationNumber) {
    return taxpayerHashMap.get(taxRegistrationNumber).getTaxpayerStatus();
  }

  public String getTaxpayerIncome(int taxRegistrationNumber) {
    return "" + taxpayerHashMap.get(taxRegistrationNumber).getIncome();
  }

  public double getTaxpayerVariationTaxOnReceipts(int taxRegistrationNumber) {
    return taxpayerHashMap.get(taxRegistrationNumber).getVariationTaxOnReceipts();
  }

  public int getTaxpayerTotalReceiptsGathered(int taxRegistrationNumber) {
    return taxpayerHashMap.get(taxRegistrationNumber).getTotalReceiptsGathered();
  }

  public float getTaxpayerAmountOfReceiptKind(int taxRegistrationNumber, short kind) {
    return taxpayerHashMap.get(taxRegistrationNumber).getAmountOfReceiptKind(kind);
  }

  public double getTaxpayerTotalTax(int taxRegistrationNumber) {
    return taxpayerHashMap.get(taxRegistrationNumber).getTotalTax();
  }

  public double getTaxpayerBasicTax(int taxRegistrationNumber) {
    return taxpayerHashMap.get(taxRegistrationNumber).getBasicTax();
  }

  public HashMap<Integer, Receipt> getReceiptHashMap(int taxRegistrationNumber) {
    return taxpayerHashMap.get(taxRegistrationNumber).getReceiptHashMap();
  }

}