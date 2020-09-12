package incometaxcalculator.data.management;

import java.util.HashMap;

public class Taxpayer {

  protected final String fullname;
  protected final int taxRegistrationNumber;
  protected final double income;
  private float amountPerReceiptsKind[] = new float[5];
  private int totalReceiptsGathered = 0;
  private HashMap<Integer, Receipt> receiptHashMap = new HashMap<Integer, Receipt>(0);
  private final String[] kindOfReceipt = {"Entertainment", "Basic", "Travel", "Health", "Other"};
  private final double[] limitOfIncome = {0.2, 0.4, 0.6};
  private final double[] rateOfIncome = {0.08, 0.04, -0.15};
  private final double BIGGEST_RATE = 0.3;
  private int[] calculationIncomeLimits;
  private double[] calculationIncomeRates;
  private double[] calculationIncomeAdditions;
  private final int CALCULATION_INCOME_ARRAYS_SIZE = 5;
  private final String taxpayerStatus;
  
  public Taxpayer(String fullname, int taxRegistrationNumber, double income,
      String taxpayerStatus, int[] calculationIncomeLimits, double[] calculationIncomeRates, double[] calculationIncomeAdditions) {
    this.fullname = fullname;
    this.taxRegistrationNumber = taxRegistrationNumber;
    this.income = income;
    this.taxpayerStatus = taxpayerStatus;
    this.calculationIncomeLimits = calculationIncomeLimits;
    this.calculationIncomeRates = calculationIncomeRates;
    this.calculationIncomeAdditions = calculationIncomeAdditions;
  }

  public void addReceipt(Receipt receipt) {
    for(int i=0; i<kindOfReceipt.length; i++) {
      if(receipt.getKind().equals(kindOfReceipt[i])) {
        amountPerReceiptsKind[i] += receipt.getAmount();
      }
    }
    receiptHashMap.put(receipt.getId(), receipt);
    totalReceiptsGathered++;
  }

  public void removeReceipt(int receiptId) {
    Receipt receipt = receiptHashMap.get(receiptId);
    for(int i=0; i<kindOfReceipt.length; i++) {
      if(receipt.getKind().equals(kindOfReceipt[i])) {
        amountPerReceiptsKind[i] -= receipt.getAmount();
      }
    }
    totalReceiptsGathered--;
    receiptHashMap.remove(receiptId);
  }

  public String getFullname() {
    return fullname;
  }

  public int getTaxRegistrationNumber() {
    return taxRegistrationNumber;
  }

  public float getIncome() {
    return (float)income;
  }

  public HashMap<Integer, Receipt> getReceiptHashMap() {
    return receiptHashMap;
  }

  public double getVariationTaxOnReceipts() {
    float totalAmountOfReceipts = getTotalAmountOfReceipts();
    for(int i=0; i<limitOfIncome.length; i++) {
      if(totalAmountOfReceipts < limitOfIncome[i]*income) {
        return calculateBasicTax()*rateOfIncome[i];
      }
    }
    return calculateBasicTax()*BIGGEST_RATE;
  }

  private float getTotalAmountOfReceipts() {
    int sum = 0;
    for (int i = 0; i < 5; i++) {
      sum += amountPerReceiptsKind[i];
    }
    return sum;
  }

  public int getTotalReceiptsGathered() {
    return totalReceiptsGathered;
  }

  public float getAmountOfReceiptKind(short kind) {
    return amountPerReceiptsKind[kind];
  }

  public double getTotalTax() {
    return calculateBasicTax() + getVariationTaxOnReceipts();
  }

  public double getBasicTax() {
    return calculateBasicTax();
  }
  
  public String getTaxpayerStatus() {
    return taxpayerStatus;
  }
  
  public double calculateBasicTax() {
    double previousCategoryIncomeLimit = 0.0;
    
    for(int i=0; i<CALCULATION_INCOME_ARRAYS_SIZE-1; i++) {
      if(income < calculationIncomeLimits[i]) {
        return calculationIncomeAdditions[i] + calculationIncomeRates[i] * (income - previousCategoryIncomeLimit);
      }
      previousCategoryIncomeLimit = calculationIncomeLimits[i];
    }
    return calculationIncomeAdditions[4] + calculationIncomeRates[4] * (income - previousCategoryIncomeLimit);
  }

}