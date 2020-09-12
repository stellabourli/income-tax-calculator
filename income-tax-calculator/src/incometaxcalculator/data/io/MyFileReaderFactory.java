package incometaxcalculator.data.io;

public class MyFileReaderFactory {
  
  public static MyFileReader createFileReader(String type) {
    if (type.equals("txt")) {
      return new TXTFileReader();
    }
    else if (type.equals("xml")) {
      return new XMLFileReader();
    }
    return null;
  }
}
 
