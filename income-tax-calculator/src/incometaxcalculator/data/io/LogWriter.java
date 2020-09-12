package incometaxcalculator.data.io;

import incometaxcalculator.data.management.TaxpayerManager;

import java.io.IOException;
import java.io.PrintWriter;

public abstract class LogWriter implements MyFileWriter {

  protected static final short ENTERTAINMENT = 0;
  protected static final short BASIC = 1;
  protected static final short TRAVEL = 2;
  protected static final short HEALTH = 3;
  protected static final short OTHER = 4;
  
  protected abstract String getWriterStatus();
  protected abstract String createBeginTag(String name);
  protected abstract String createEndTag(String name);
  protected abstract String getReceiptsBeginTag();
  
public void generateFile(int taxRegistrationNumber) throws IOException {
    TaxpayerManager tManager = new TaxpayerManager();
    PrintWriter outputStream = new PrintWriter(
        new java.io.FileWriter(taxRegistrationNumber + "_LOG." + getWriterStatus()));
    outputStream.println(createBeginTag("Name") + tManager.getTaxpayerName(taxRegistrationNumber) + createEndTag("Name"));
    outputStream.println(createBeginTag("AFM") + taxRegistrationNumber + createEndTag("AFM"));
    outputStream.println(createBeginTag("Income") + tManager.getTaxpayerIncome(taxRegistrationNumber) + createEndTag("Income"));
    outputStream.println(createBeginTag("Basic Tax") + tManager.getTaxpayerBasicTax(taxRegistrationNumber) + createEndTag("BasicTax"));
    if (tManager.getTaxpayerVariationTaxOnReceipts(taxRegistrationNumber) > 0) {
      outputStream
          .println(createBeginTag("Tax Increase") + tManager.getTaxpayerVariationTaxOnReceipts(taxRegistrationNumber) + createEndTag("Tax Increase"));
    } else {
      outputStream
          .println(createBeginTag("Tax Decrease") + tManager.getTaxpayerVariationTaxOnReceipts(taxRegistrationNumber) + createEndTag("Tax Decrease"));
    }
    outputStream.println(createBeginTag("Total Tax") + tManager.getTaxpayerTotalTax(taxRegistrationNumber) + createEndTag("Total Tax"));
    outputStream.println(
        getReceiptsBeginTag() + tManager.getTaxpayerTotalReceiptsGathered(taxRegistrationNumber) + createEndTag("Receipts"));
    outputStream.println(
        createBeginTag("Entertainment") + tManager.getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, ENTERTAINMENT) + createEndTag("Entertainment"));
    outputStream.println(createBeginTag("Basic") + tManager.getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, BASIC) + createEndTag("Basic"));
    outputStream
        .println(createBeginTag("Travel") + tManager.getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, TRAVEL) + createEndTag("Travel"));
    outputStream
        .println(createBeginTag("Health") + tManager.getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, HEALTH) + createEndTag("Health"));
    outputStream.println(createBeginTag("Other") + tManager.getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, OTHER) + createEndTag("Other"));
    outputStream.close();
  }
}
