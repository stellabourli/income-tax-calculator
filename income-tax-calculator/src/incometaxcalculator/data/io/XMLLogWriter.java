package incometaxcalculator.data.io;

public class XMLLogWriter extends LogWriter {

  public String getWriterStatus() {
    return "xml";
  }
  
  public String createBeginTag(String name) {
    return "<" + splitName(name) + "> ";
  }
  
  public String createEndTag(String name) {
    return " </" + splitName(name) + ">";
  }
  
  private String splitName(String name) {
    return name.replaceAll(" ", "");
  }
  
  public String getReceiptsBeginTag() {
    return "<Receipts> ";
  }

}
