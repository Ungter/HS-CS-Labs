/************************************************
* Qianhe Sha; Pd. 5
* Lab: Savings Calculator
* Purpose of the Program: Comparing the money saved in a savings account and in the stock market in terms of the amount of money the user has and the number of years the user planning to save the money for.
* 
* What I learned: 
*  #1: You can type cast inside a print statment.
*  #2: The NumberFormat class and how you can use .getCurrencyInstance() to make the number into currency format.
*  
*  Credits: N/A  
**************************************************/
import java.util.Scanner;
import java.text.NumberFormat;

public class Pd5TedShaCalcSavingsPartII {

   public static NumberFormat dollar = NumberFormat.getCurrencyInstance();

   // Returns time
   public static double getTime() {
      System.out.print("Enter the number of years the money will be invested: ");
      double t = new Scanner(System.in).nextInt();
      return t;
   }
   
   // Returns amount of money the user has
   public static double getPrincipal() {
      System.out.print("Enter the amount of money saved: $");
      double P = new Scanner(System.in).nextInt();
      return P;
   }

   // Money if in savings account
   public static double calcSavingsAccount(double t, double P) {
      double s = P * Math.pow(1.0025, 4 * t);
      System.out.println("In a savings account with 1% annual interest for " + (int) t + " years, you will save: " + dollar.format(s));
      return s;
   }

   // Money if in stock market
   public static double calcMarketReturn(double t, double P) {
      double m = P * Math.pow(Math.E, 0.07 * t);
      System.out.println("If you invest this money in the stock market for " + (int) t + " years, you can expect to save: " + dollar.format(m));
      return m;
   }

   // Differences in market return and savings return
   public static void calcDifference(double s, double m) {
      System.out.print("You can expect to save " + dollar.format(m - s) + " more by investing in the stock market.");
   }

   //Execute 
   public static void main(String args[]) {
      double principal = getPrincipal();
      double time = getTime();
      double savingsReturn = calcSavingsAccount(time, principal);
      double marketReturn = calcMarketReturn(time, principal);
      calcDifference(savingsReturn, marketReturn);
   }
} 