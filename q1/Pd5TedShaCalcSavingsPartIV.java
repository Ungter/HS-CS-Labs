/**************************************
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 **************************************/


import java.text.NumberFormat;
import java.util.Scanner;

public class Pd5TedShaCalcSavingsPartIV {

    
    // Bank account exponent
    public static double forthPwr(double yearsInv) {
        return Math.pow(1.0025, 4 * yearsInv);
    }

    // Stock market exponent
    public static double sevenPwr(double yearsInv) {
        return Math.pow(Math.E, 0.07 * yearsInv);
    }

    public static void main(String[] args) {
        
        
        double preAmt, yearsInv, stockAcc, diff, savingsAcc;

        // Ask and saves the amount before investing/saving
        System.out.print("Enter the amount of money saved: $"); 
        preAmt = new Scanner(System.in).nextInt();

        // Ask and saves the number of years planned on investing/saving
        System.out.print("Enter the number of years the money will be invested: ");
        yearsInv = new Scanner(System.in).nextInt(); 
        
        
        // Calculations
        savingsAcc = preAmt * forthPwr(yearsInv);

        stockAcc = preAmt * sevenPwr(yearsInv);

        diff = stockAcc - savingsAcc;

        // Output
        NumberFormat dollar = NumberFormat.getCurrencyInstance();
        
        System.out.println("\nIn a savings account with 1% annual interest for " + (int) yearsInv + " years, you will save: " + dollar.format(savingsAcc));
        System.out.println("If you invest this money in the stock market for " + (int) yearsInv + " years, you can expect to save: " + dollar.format(stockAcc));
        System.out.println("You can expect to save " + dollar.format(diff) + " more by investing in the stock market.");


    } // Main
} // Class