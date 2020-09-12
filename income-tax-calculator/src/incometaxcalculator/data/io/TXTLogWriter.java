package incometaxcalculator.data.io;

public class TXTLogWriter extends LogWriter {
  
  public String getWriterStatus() {
    return "txt";
  }
  
  public String createBeginTag(String name) {
    return name + ": ";
  }
  
  public String createEndTag(String name) {
    //dummy class, TXT writer has no end tags
    return "";
  }
  
  public String getReceiptsBeginTag() {
    return "TotalReceiptsGathered: ";
  }
}
