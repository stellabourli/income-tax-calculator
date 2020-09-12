package incometaxcalculator.data.io;


public class TXTInfoWriter extends InfoWriter {
  
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
}