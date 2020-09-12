package incometaxcalculator.data.management;

import java.util.HashMap;

public class TaxpayerFactory {

  private static HashMap<String, int[]> calculationIncomeLimitsMap;
  private static HashMap<String, double[]> calculationIncomeRatesMap;
  private static HashMap<String, double[]> calculationIncomeAdditionsMap;
  
  public TaxpayerFactory() {
    calculationIncomeLimitsMap = new HashMap<String, int[]>();
    calculationIncomeRatesMap = new HashMap<String, double[]>();
    calculationIncomeAdditionsMap = new HashMap<String, double[]>();
    
    int[] MarriedFilingJointlyTaxpayerLimits = {36080, 90000, 143350, 254240, Integer.MAX_VALUE};
    double[] MarriedFilingJointlyTaxpayerRates = {0.0535, 0.0705, 0.0705, 0.0785, 0.0985};
    double[] MarriedFilingJointlyTaxpayerAdditions = {0, 1930.28, 5731.64, 9492.82, 18197.69};
    calculationIncomeLimitsMap.put("Married Filing Jointly", MarriedFilingJointlyTaxpayerLimits);
    calculationIncomeRatesMap.put("Married Filing Jointly", MarriedFilingJointlyTaxpayerRates);
    calculationIncomeAdditionsMap.put("Married Filing Jointly", MarriedFilingJointlyTaxpayerAdditions);
    
    int[] MarriedFilingSeperatelyTaxpayerLimits = {18040, 71680, 90000, 127120, Integer.MAX_VALUE};
    double[] MarriedFilingSeperatelyTaxpayerRates = {0.0535, 0.0705, 0.0785, 0.0785, 0.0985};
    double[] MarriedFilingSeperatelyTaxpayerAdditions = {0, 965.14, 4746.76, 6184.88, 9098.80};
    calculationIncomeLimitsMap.put("Married Filing Separately", MarriedFilingSeperatelyTaxpayerLimits);
    calculationIncomeRatesMap.put("Married Filing Separately", MarriedFilingSeperatelyTaxpayerRates);
    calculationIncomeAdditionsMap.put("Married Filing Separately", MarriedFilingSeperatelyTaxpayerAdditions);
    
    int[] SingleTaxpayerLimits = {24680, 81080, 90000, 152540, Integer.MAX_VALUE};
    double[] SingleTaxpayerRates = {0.0535, 0.0705, 0.0785, 0.0785, 0.0985};
    double[] SingleTaxpayerAdditions = {0, 1320.38, 5296.58, 5996.80, 10906.19};
    calculationIncomeLimitsMap.put("Single Taxpayer", SingleTaxpayerLimits);
    calculationIncomeRatesMap.put("Single Taxpayer", SingleTaxpayerRates);
    calculationIncomeAdditionsMap.put("Single Taxpayer", SingleTaxpayerAdditions);
    
    int[] HeadOfHouseholdTaxpayerLimits = {30390, 90000, 122110, 203390, Integer.MAX_VALUE};
    double[] HeadOfHouseholdTaxpayerRates = {0.0535, 0.0705, 0.0705, 0.0785, 0.0985};
    double[] HeadOfHouseholdTaxpayerAdditions = {0, 1625.87, 5828.38, 8092.13, 14472.61};
    calculationIncomeLimitsMap.put("Head Of Household Taxpayer", HeadOfHouseholdTaxpayerLimits);
    calculationIncomeRatesMap.put("Head Of Household Taxpayer", HeadOfHouseholdTaxpayerRates);
    calculationIncomeAdditionsMap.put("Head Of Household Taxpayer", HeadOfHouseholdTaxpayerAdditions);
    
  }
  
  public static Taxpayer createTaxpayer(String fullname, int taxRegistrationNumber, double income, String taxpayerStatus) {
    return new Taxpayer(fullname, taxRegistrationNumber, income, taxpayerStatus, calculationIncomeLimitsMap.get(taxpayerStatus), 
        calculationIncomeRatesMap.get(taxpayerStatus), calculationIncomeAdditionsMap.get(taxpayerStatus));
  }
  
}
