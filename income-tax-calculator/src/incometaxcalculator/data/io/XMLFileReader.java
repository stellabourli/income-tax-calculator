package incometaxcalculator.data.io;

public class XMLFileReader extends MyFileReader {
  
  protected boolean checkForReceipt(String[] values) {
    return values[0].equals("<ReceiptID>");
  }
  
  protected int getValueNumberForParsing() {
    return 1;
  }
  
  protected String processValues(String[] values) {
    String valueReversed[] = new StringBuilder(values[1]).reverse().toString().trim()
        .split(" ", 2);
    return new StringBuilder(valueReversed[1]).reverse().toString();
  }
}
