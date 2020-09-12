package incometaxcalculator.data.io;

import java.io.IOException;

public interface MyFileWriter {

  public void generateFile(int taxRegistrationNumber) throws IOException;
  
  

}