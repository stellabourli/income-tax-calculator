package incometaxcalculator.data.io;

public class TXTFileReader extends MyFileReader {
  
  protected boolean checkForReceipt(String[] values) {
    return values[0].equals("Receipt") && values[1].equals("ID:");
  }
  
  protected int getValueNumberForParsing() {
    return 2;
  }
  
  protected String processValues(String[] values) {
    values[1] = values[1].trim();
    return values[1];
  }
}