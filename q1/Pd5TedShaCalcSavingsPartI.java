/************************************************
* Qianhe Sha; Pd. 5
* Lab: Savings Calculator
* Purpose of the Program: Comparing the money saved in a savings account and in the stock market in terms of the amount of money the user has and the number of years the user planning to save the money for.
* 
* What I learned: 
*  #1: Inline scanner(I think that is what its called)
*  #2: You can type cast inside a print statment.
*  #3: The NumberFormat class and how you can use .getCurrencyInstance() to make the number into currency format.
*  
*  Credits: N/A  
**************************************************/
import java.util.Scanner;
import java.text.NumberFormat;

public class Pd5TedShaCalcSavingsPartI {
    public static void main(String[] args) {

        double P, t;
        // Asks and saves the amount of money the user has in the first place
        System.out.print("Enter the amount of money saved: $");
        P = new Scanner(System.in).nextInt();

        // Asks and saves the years the user plan to invest
        System.out.print("Enter the number of years the money will be invested: ");
        t = new Scanner(System.in).nextInt();

        // Output
        NumberFormat dollar = NumberFormat.getCurrencyInstance();
        System.out.println("In a savings account with 1% annual interest for " + (int) t + " years, you will save: " + dollar.format(P * Math.pow(1.0025, 4 * t)));
        System.out.println("If you invest this money in the stock market for " + (int) t + " years, you can expect to save: " + dollar.format(P * Math.pow(Math.E, 0.07 * t)));
        System.out.println("You can expect to save " + dollar.format((P * Math.pow(Math.E, 0.07 * t)) - (P * Math.pow(1.0025, 4 * t))) + " more by investing in the stock market.");
    }
}