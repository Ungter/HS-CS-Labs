/***********************************************************************
Name: Ted Sha     
Period: 6th
Date: 1/11/23   
What I Learned: .isDigit(x)            
Credit (person who helped me): 
Student(s) whom I helped (to what extent):
************************************************************************/    
import java.util.*;
   public class Postfix_TedSha
   {
      public static void main(String[] args)
      {
         System.out.println("Enter a valid postfix expression (single digits only),");
         System.out.println("such as 35*1+");
         Scanner keyboard = new Scanner(System.in);
         String s = keyboard.next();  
         while(!s.equals("-1"))
         { 
            System.out.println(s + " ---> " + eval(s) + "\n");
            System.out.println((s = "354*+7*") + " = " + eval(s) + "\n");
            System.out.println((s = "82-") + " = " + eval(s) + "\n");
            System.out.println((s = "82/") + " = " + eval(s) + "\n");
            s = keyboard.next();
         }
      }
      public static int eval(String x)
      {
         Stack<Integer> stack = new Stack<Integer>();
         Stack<String> infix = new Stack<>();
         for (int i = 0; i < x.length(); i++) {
             char c = x.charAt(i);
             if (Character.isDigit(c)) {
                 stack.push(c - '0');
                 infix.push(Character.toString(c));
             } else {
                 int a = stack.pop();
                 int b = stack.pop();
                 int result = eval(a, b, c);
                 stack.push(result);
                 infix.push(infix.pop() + c + infix.pop());
             }
         }
         System.out.print(infix.pop() + " ---> ");
         return stack.pop();
      }

      

      public static int eval(int a, int b, char ch)
      {
         switch (ch) {
            case '+':
                return b + a;
            case '-':
                return b - a;
            case '*':
                return b * a;
            case '/':
                return b / a;
            case '^':
                return (int) Math.pow(b, a);
        }
        return 0;
      }

      public static boolean isOperator(char ch)
      {
         return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^';
      }
   }

/*
Enter a valid postfix expression (single digits only),
such as 35*1+
345*+
5*4+3 ---> 345*+ ---> 23

34*5+
5+4*3 ---> 34*5+ ---> 17

45+53*-
3*5-5+4 ---> 45+53*- ---> -6

34+56+*
6+5*4+3 ---> 34+56+* ---> 77

345+*2-5/
5/2-5+4*3 ---> 345+*2-5/ ---> 5

812*+93/-
3/9-2*1+8 ---> 812*+93/- ---> 7

7*4*5+3 ---> 354*+7* = 161

2-8 ---> 82- = 6

2/8 ---> 82/ = 4
 */