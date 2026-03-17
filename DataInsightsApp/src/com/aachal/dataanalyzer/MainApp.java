package com.aachal.dataanalyzer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MainApp {

    public static void main(String[] args) {

        String filePath = "C:\\Users\\leena\\Desktop\\projects\\java\\DataInsightsApp\\restaurant_churn_dataset.csv";
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            br.readLine(); // skip header
            
            int totalCustomers = 0;
            int totalAge = 0;
            int churnCount = 0;
            int maleChurn = 0;
            int femaleChurn = 0;
            int highSpenders = 0;

            while ((line = br.readLine()) != null) {

                String[] values = line.split(",");

                int age = Integer.parseInt(values[1]);
                String gender = values[2];
                int churn = Integer.parseInt(values[values.length - 3]); // near end
                double spend = Double.parseDouble(values[values.length - 1]);

                System.out.println("Age: " + age +
                        ", Gender: " + gender +
                        ", Churn: " + churn +
                        ", Spend: " + spend);
                totalCustomers++;
                totalAge += age;
                if (churn == 1) {
                    churnCount++;
                }
                
                if (churn == 1) {
                    if (gender.equalsIgnoreCase("Male")) {
                        maleChurn++;
                    } else if (gender.equalsIgnoreCase("Female")) {
                        femaleChurn++;
                    }
                }
                
                if (spend > 20) {  // you can adjust threshold
                    highSpenders++;
                }
            }
            
            double churnRate = ((double) churnCount / totalCustomers) * 100;
            System.out.println("Churn Rate: " + churnRate + "%");
            
            System.out.println("Total Customers: " + totalCustomers);
            System.out.println("Average Age: " + (totalAge / totalCustomers));
            System.out.println("Churned Customers: " + churnCount);
            System.out.println("Male Churn: " + maleChurn);
            System.out.println("Female Churn: " + femaleChurn);
            System.out.println("High Spending Customers: " + highSpenders);
            
            if (maleChurn > femaleChurn) {
                System.out.println("Insight: Male customers are churning more.");
            } else {
                System.out.println("Insight: Female customers are churning more.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}