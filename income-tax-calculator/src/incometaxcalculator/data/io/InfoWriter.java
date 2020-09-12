package incometaxcalculator.data.io;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;

import incometaxcalculator.data.management.Receipt;
import incometaxcalculator.data.management.TaxpayerManager;

public abstract class InfoWriter implements MyFileWriter {
  
  public abstract String getWriterStatus();
  public abstract String createBeginTag(String name);
  public abstract String createEndTag(String name);
  
  public void generateFile(int taxRegistrationNumber) throws IOException {
    TaxpayerManager tManager = new TaxpayerManager();
    PrintWriter outputStream = new PrintWriter(
        new java.io.FileWriter(taxRegistrationNumber + "_INFO." + getWriterStatus()));
    outputStream.println(createBeginTag("Name") + tManager.getTaxpayerName(taxRegistrationNumber) + createEndTag("Name"));
    outputStream.println(createBeginTag("AFM") + taxRegistrationNumber + createEndTag("AFM"));
    outputStream.println(createBeginTag("Status") + tManager.getTaxpayerStatus(taxRegistrationNumber) + createEndTag("Status"));
    outputStream.println(createBeginTag("Income") + tManager.getTaxpayerIncome(taxRegistrationNumber) + createEndTag("Income"));
    outputStream.println();
    outputStream.println(createBeginTag("Receipts"));
    outputStream.println();
    generateTaxpayerReceipts(taxRegistrationNumber, outputStream);
    outputStream.close();
  }
  
  private void generateTaxpayerReceipts(int taxRegistrationNumber, PrintWriter outputStream) {

    TaxpayerManager tManager = new TaxpayerManager();
    HashMap<Integer, Receipt> receiptsHashMap = tManager.getReceiptHashMap(taxRegistrationNumber);
    Iterator<HashMap.Entry<Integer, Receipt>> iterator = receiptsHashMap.entrySet().iterator();
    while (iterator.hasNext()) {
      HashMap.Entry<Integer, Receipt> entry = iterator.next();
      Receipt receipt = entry.getValue();
      outputStream.println(createBeginTag("Receipt ID") + receipt.getId() + createEndTag("Receipt ID"));
      outputStream.println(createBeginTag("Date") + receipt.getIssueDate() + createEndTag("Date"));
      outputStream.println(createBeginTag("Kind") + receipt.getKind() + createEndTag("Kind"));
      outputStream.println(createBeginTag("Amount") + receipt.getAmount() + createEndTag("Amount"));
      outputStream.println(createBeginTag("Company") + receipt.getCompany() + createEndTag("Company"));
      outputStream.println(createBeginTag("Country") + receipt.getCompanyAddress().getCountry() + createEndTag("Country"));
      outputStream.println(createBeginTag("City") + receipt.getCompanyAddress().getCity() + createEndTag("City"));
      outputStream.println(createBeginTag("Street") + receipt.getCompanyAddress().getStreet() + createEndTag("Street"));
      outputStream.println(createBeginTag("Number") + receipt.getCompanyAddress().getNumber() + createEndTag("Number"));
      outputStream.println();
    }
  }
}
