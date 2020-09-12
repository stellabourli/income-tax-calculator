package incometaxcalculator.data.management;

import incometaxcalculator.exceptions.WrongReceiptDateException;

import java.text.SimpleDateFormat;

public class Receipt {

  private final int id;
  private final SimpleDateFormat issueDate;
  private final float amount;
  private final String kind;
  private final String company;
  private final Address companyAddress;

  public Receipt(int id, String issueDate, float amount, String kind, String company, Address companyAddress)
      throws WrongReceiptDateException {
    this.id = id;
    this.issueDate = createDate(issueDate);
    this.amount = amount;
    this.kind = kind;
    this.company = company;
    this.companyAddress = companyAddress;
  }

  private SimpleDateFormat createDate(String issueDate) throws WrongReceiptDateException {
    String token[] = issueDate.split("/");
    if (token.length != 3) {
      throw new WrongReceiptDateException();
    }
    int day = Integer.parseInt(token[0]);
    int month = Integer.parseInt(token[1]);
    int year = Integer.parseInt(token[2]);
    return new SimpleDateFormat(day+"/"+month+"/"+year);
  }

  public int getId() {
    return id;
  }

  public String getIssueDate() {
    return issueDate.toPattern();
  }

  public float getAmount() {
    return amount;
  }

  public String getKind() {
    return kind;
  }

  public String getCompany() {
    return company;
  }
  
  public Address getCompanyAddress() {
    return companyAddress;
  }
}