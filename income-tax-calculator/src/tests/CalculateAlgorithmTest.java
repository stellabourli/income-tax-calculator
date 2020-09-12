package tests;

import static org.junit.Assert.*;
import incometaxcalculator.data.management.Taxpayer;
import incometaxcalculator.data.management.TaxpayerFactory;

import org.junit.Test;

public class CalculateAlgorithmTest {
  
    @Test 
    public void testSingleTexpayer() {
      new TaxpayerFactory();
      Taxpayer testSingleTaxpayer1 = TaxpayerFactory.createTaxpayer("Apostolos Zarras", 130456093, 24000, "Single Taxpayer");
      Taxpayer testSingleTaxpayer2 = TaxpayerFactory.createTaxpayer("Apostolos Zarras", 130456093, 81000, "Single Taxpayer");
      Taxpayer testSingleTaxpayer3 = TaxpayerFactory.createTaxpayer("Apostolos Zarras", 130456093, 89000, "Single Taxpayer");
      Taxpayer testSingleTaxpayer4 = TaxpayerFactory.createTaxpayer("Apostolos Zarras", 130456093, 150000, "Single Taxpayer");
      Taxpayer testSingleTaxpayer5 = TaxpayerFactory.createTaxpayer("Apostolos Zarras", 130456093, 500000, "Single Taxpayer");
      double result1 = testSingleTaxpayer1.calculateBasicTax();
      double result2 = testSingleTaxpayer2.calculateBasicTax();
      double result3 = testSingleTaxpayer3.calculateBasicTax();
      double result4 = testSingleTaxpayer4.calculateBasicTax();
      double result5 = testSingleTaxpayer5.calculateBasicTax();
      assertEquals(result1, 0.0535 * 24000, 0);
      assertEquals(result2, 1320.38 + 0.0705 * (81000 - 24680), 0);
      assertEquals(result3, 5296.58 + 0.0785 * (89000 - 81080), 0);
      assertEquals(result4, 5996.80 + 0.0785 * (150000 - 90000), 0);
      assertEquals(result5, 10906.19 + 0.0985 * (500000 - 152540), 0);
    }
    
    @Test 
    public void testHeadOfHouseholdTaxpayer() {
      new TaxpayerFactory();
      Taxpayer testHeadOfHouseholdTaxpayer1 = TaxpayerFactory.createTaxpayer("Apostolos Zarras", 130456093, 30000, "Head Of Household Taxpayer");
      Taxpayer testHeadOfHouseholdTaxpayer2 = TaxpayerFactory.createTaxpayer("Apostolos Zarras", 130456093, 89000, "Head Of Household Taxpayer");
      Taxpayer testHeadOfHouseholdTaxpayer3 = TaxpayerFactory.createTaxpayer("Apostolos Zarras", 130456093, 122000, "Head Of Household Taxpayer");
      Taxpayer testHeadOfHouseholdTaxpayer4 = TaxpayerFactory.createTaxpayer("Apostolos Zarras", 130456093, 203000, "Head Of Household Taxpayer");
      Taxpayer testHeadOfHouseholdTaxpayer5 = TaxpayerFactory.createTaxpayer("Apostolos Zarras", 130456093, 500000, "Head Of Household Taxpayer");
      double result1 = testHeadOfHouseholdTaxpayer1.calculateBasicTax();
      double result2 = testHeadOfHouseholdTaxpayer2.calculateBasicTax();
      double result3 = testHeadOfHouseholdTaxpayer3.calculateBasicTax();
      double result4 = testHeadOfHouseholdTaxpayer4.calculateBasicTax();
      double result5 = testHeadOfHouseholdTaxpayer5.calculateBasicTax();
      assertEquals(result1, 0.0535 * 30000, 0);
      assertEquals(result2, 1625.87 + 0.0705 * (89000 - 30390), 0);
      assertEquals(result3, 5828.38 + 0.0705 * (122000 - 90000), 0);
      assertEquals(result4, 8092.13 + 0.0785 * (203000 - 122110), 0);
      assertEquals(result5, 14472.61 + 0.0985 * (500000 - 203390), 0);
    }
    
    @Test 
    public void testMarriedFilingJointlyTaxpayer() {
      new TaxpayerFactory();
      Taxpayer testMarriedFilingJointlyTaxpayer1 = TaxpayerFactory.createTaxpayer("Apostolos Zarras", 130456093, 35080, "Married Filing Jointly");
      Taxpayer testMarriedFilingJointlyTaxpayer2 = TaxpayerFactory.createTaxpayer("Apostolos Zarras", 130456093, 80000, "Married Filing Jointly");
      Taxpayer testMarriedFilingJointlyTaxpayer3 = TaxpayerFactory.createTaxpayer("Apostolos Zarras", 130456093, 133350, "Married Filing Jointly");
      Taxpayer testMarriedFilingJointlyTaxpayer4 = TaxpayerFactory.createTaxpayer("Apostolos Zarras", 130456093, 244240, "Married Filing Jointly");
      Taxpayer testMarriedFilingJointlyTaxpayer5 = TaxpayerFactory.createTaxpayer("Apostolos Zarras", 130456093, 500000, "Married Filing Jointly");
      double result1 = testMarriedFilingJointlyTaxpayer1.calculateBasicTax();
      double result2 = testMarriedFilingJointlyTaxpayer2.calculateBasicTax();
      double result3 = testMarriedFilingJointlyTaxpayer3.calculateBasicTax();
      double result4 = testMarriedFilingJointlyTaxpayer4.calculateBasicTax();
      double result5 = testMarriedFilingJointlyTaxpayer5.calculateBasicTax();
      assertEquals(result1, 0.0535 * 35080, 0);
      assertEquals(result2, 1930.28 + 0.0705 * (80000 - 36080), 0);
      assertEquals(result3, 5731.64 + 0.0705 * (133350 - 90000), 0);
      assertEquals(result4, 9492.82 + 0.0785 * (244240 - 143350), 0);
      assertEquals(result5, 18197.69 + 0.0985 * (500000 - 254240), 0);
    }
    
    @Test 
    public void testMarriedFilingSeparatelyTaxpayer() {
      new TaxpayerFactory();
      Taxpayer testMarriedFilingSeparatelyTaxpayer1 = TaxpayerFactory.createTaxpayer("Apostolos Zarras", 130456093, 17040, "Married Filing Separately");
      Taxpayer testMarriedFilingSeparatelyTaxpayer2 = TaxpayerFactory.createTaxpayer("Apostolos Zarras", 130456093, 70680, "Married Filing Separately");
      Taxpayer testMarriedFilingSeparatelyTaxpayer3 = TaxpayerFactory.createTaxpayer("Apostolos Zarras", 130456093, 80000, "Married Filing Separately");
      Taxpayer testMarriedFilingSeparatelyTaxpayer4 = TaxpayerFactory.createTaxpayer("Apostolos Zarras", 130456093, 117120, "Married Filing Separately");
      Taxpayer testMarriedFilingSeparatelyTaxpayer5 = TaxpayerFactory.createTaxpayer("Apostolos Zarras", 130456093, 500000, "Married Filing Separately");
      double result1 = testMarriedFilingSeparatelyTaxpayer1.calculateBasicTax();
      double result2 = testMarriedFilingSeparatelyTaxpayer2.calculateBasicTax();
      double result3 = testMarriedFilingSeparatelyTaxpayer3.calculateBasicTax();
      double result4 = testMarriedFilingSeparatelyTaxpayer4.calculateBasicTax();
      double result5 = testMarriedFilingSeparatelyTaxpayer5.calculateBasicTax();
      assertEquals(result1, 0.0535 * 17040, 0);
      assertEquals(result2, 965.14 + 0.0705 * (70680 - 18040), 0);
      assertEquals(result3, 4746.76 + 0.0785 * (80000 - 71680), 0);
      assertEquals(result4, 6184.88 + 0.0785 * (117120 - 90000), 0);
      assertEquals(result5, 9098.80 + 0.0985 * (500000 - 127120), 0);
    }
  
  
  
}





