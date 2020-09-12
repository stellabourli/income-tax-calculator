package incometaxcalculator.data.io;

import java.io.File;
import java.io.IOException;

public class InfoWriterFactory {
  
  public static void createInfoWriter(int taxRegistrationNumber) throws IOException {
    if (XMLfileExists(taxRegistrationNumber))
      new XMLInfoWriter().generateFile(taxRegistrationNumber);
    else
      new TXTInfoWriter().generateFile(taxRegistrationNumber);
  }
  
  private static boolean XMLfileExists(int taxRegistrationNumber) {
    return new File(taxRegistrationNumber + "_INFO.xml").exists();
  }
}
