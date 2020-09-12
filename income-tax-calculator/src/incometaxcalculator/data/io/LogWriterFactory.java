package incometaxcalculator.data.io;

public class LogWriterFactory {
  
  public static MyFileWriter createLogWriter(int taxRegistrationNumber, String fileFormat) {
    switch(fileFormat) {
    case "txt":
      return new TXTLogWriter();
    case "xml":
      return new XMLLogWriter();
    }
    return null;
  }
}
