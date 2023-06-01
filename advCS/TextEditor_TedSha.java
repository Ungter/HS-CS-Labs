/*****************************************************************************************************************
NAME: Ted Sha     
PERIOD: 6th
DUE DATE: Jan 5th 2023
ASSIGNMENT: Text Editor

PURPOSE: Emulate a text editor that can delete characters and delete entire lines

LEARNED:
- string builder and reverse
            
CREDITS: 

****************************************************************************************************************/
import java.util.*;
public class TextEditor_TedSha
{
   public static void main(String[] args)
   {
      Scanner sc = new Scanner(System.in);
      Scanner choice = new Scanner(System.in);
      String again;
      do
      {
         System.out.print("Enter a line of text: ");
         String input = sc.nextLine();
         editText(input);
         System.out.print("\nAgain (y/n)? ");
         again = choice.next();
      }while(!again.equals("n"));
   }//main
   
   //pre:  s is not null
   //post: edits a String according to certain characters it contains and prints the resulted string
   public static void editText(String s)
   {
      Stack<Character> text = new Stack<Character>();

      for (int i = 0; i < s.length(); i++) {
         char c = s.charAt(i);
         if (c == '-') {
            if(!text.isEmpty()){
               text.pop();
            }
         } else if (c == '$') {
            text.clear();
         } else {
            text.push(c);
         }
      }  
      printStack(text);
   }//editText
   
   //pre:  none
   //post: prints the Stack in a nicer format, ex. abc instead of [a, b, c]
   public static void printStack(Stack<Character> s)
   {     
      System.out.print("Here is the line you entered: ");
      StringBuilder sb = new StringBuilder();
      while (!s.isEmpty()) {
         sb.append(s.pop());
      }
      System.out.print(sb.reverse().toString()); 
   }//printStack
}

/*
----jGRASP exec: java TextEditor_shell
Enter a line of text: Ca-noe$Ra3-fx-t

Here is the line you entered: Raft
Again (y/n)? y
Enter a line of text: AP$$-Compp-utee-r Sic--cei--ience

Here is the line you entered: Computer Science
Again (y/n)? y
Enter a line of text: He$He was astg-- tall ae-s a$ 6 foot,- 3 inchre-- treeu-

Here is the line you entered:  6 foot 3 inch tree
Again (y/n)? y
Enter a line of text: bone matrix and pivot joint$

Here is the line you entered: 
Again (y/n)? y
Enter a line of text: dey$daybsah---reakk-s be-ell

Here is the line you entered: daybreaks bell
Again (y/n)? n

 ----jGRASP: operation complete.
*/

/* self-produced test cases

 * Enter a line of text: AP$$-Compp-utee-r Sic--cei--ience
Here is the line you entered: Computer Science
Again (y/n)? y
Enter a line of text: He$He was astg-- tall ae-s a$ 6 foot,- 3 inchre-- treeu-
Here is the line you entered:  6 foot 3 inch tree
Again (y/n)? Ca-noe$Ra3-fx-t
Enter a line of text: Ca-noe$Ra3-fx-t
Here is the line you entered: Raft
Again (y/n)? y
Enter a line of text: bone matrix and pivot joint$
Here is the line you entered: 
Again (y/n)? y
Enter a line of text: dey$daybsah---reakk-s be-ell
Here is the line you entered: daybreaks bell
Again (y/n)? y
Enter a line of text: tt
Here is the line you entered: tt
Again (y/n)? n
 */