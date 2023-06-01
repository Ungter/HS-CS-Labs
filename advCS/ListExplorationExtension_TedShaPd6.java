/*********************************************************************************
NAME:Ted Sha
PERIOD: 6 
DATE SUBMITTED: 11/17/2022
DUE DATE: 11/18/2022
ASSIGNMENT: List Exploration Extension

PURPOSE OF THE LAB: 
To pratice traversing and editing Linked Lists

MISTAKES MADE:
-copyOfLast(): first attempt resulted in an infinite loop despite having a base case,
 error pointed to getValue(), took a while to figure out.

NEW CONCEPTS LEARNED:
- How to use the conditional operator in java

HOW I FEEL ABOUT THIS LAB:
- Pretty short

CREDITS: 

STUDENTS WHOM I HELPED: 
*/
import java.util.*;
public class ListExplorationExtension_TedShaPd6
{
   public static void main(String[] args)
   {
      ListNode <String> head = new ListNode <>("hello", null);
      
      head = new ListNode <>("foo", head);
      head = new ListNode <>("boo", head);
      head = new ListNode <>("nonsense", head);
      head = new ListNode <>("computer",
         	 new ListNode <>("science",
         	 new ListNode <>("java",
         	 new ListNode <>("coffee", head))));
   
      print(head);
         
      /**** uncomment the code below for ListExploration extension  ****/
      
   	System.out.println("First = " + first(head));
      System.out.println("Second = " + second(head));
      ListNode  <String> p = pointerToLast(head);
      System.out.println("Pointer to Last = " + p.getValue()+ " at " + p);
      
      ListNode  <String> c = copyOfLast(head);
      System.out.println("Copy of Last =    " + c.getValue()+ " at " + c);
   	// 	
      Scanner in = new Scanner(System.in);
      System.out.print("Insert what? ");
      String x = in.next();
      head = insertFirst(head, x);
      head = insertLast(head, x);
      print(head);
   }

   /*  precondition: String linked list
       postcondition: Prints out the linked list with some brackets
    */
   public static void print(ListNode  <String> head)
   {
      System.out.print("[");
      while(head != null)
      {
         System.out.print(head.getValue());
         head = head.getNext();
         if(head != null)
            System.out.print(", ");
      }
      System.out.println("]");
   } // print
      
      
   /* Enter your code here */

   /*  
      precondition: String linked list
      postcondition: returns the value of the first node, or null if the list is empty
   */
  public static ListNode<String> copyNode(ListNode<String> arg) {
    return new ListNode<String>(arg.getValue(), null);
  }

   /*  
      precondition: String linked list
      postcondition: returns the value of the first node, or null if the list is empty
   */
   public static String first(ListNode  <String> head)
   {
      return (head == null) ? null : head.getValue();
   } 
   
   /*  
      precondition: String linked list
      postcondition: returns the value of the second node, or null if the list is empty
                     or if there is only one node
                     or if there are less than two nodes
   */
   public static String second(ListNode  <String> head)
   {
      if(head == null)
         return null;
      else if(head.getNext() == null)
         return null;
      else
         return head.getNext().getValue();
   }

   /*  
      precondition: String linked list
      postcondition: returns a reference to the last node in the list, or null if list the empty
   */
   public static ListNode<String> pointerToLast(ListNode  <String> head) {
      if(head == null)
         return null;
      else {
         ListNode<String> p = head ;
         while(p.getNext() != null) {
            p = p.getNext();
         }
         return p;
      }
   }

  /*  
      precondition: String linked list
      postcondition: returns a copy of the last node or null if the list is empty
   */
   public static ListNode<String> copyOfLast(ListNode  <String> head) {
      if(head == null)
         return null;
      else {
         ListNode<String> p = pointerToLast(head);
         return copyNode(p);
      }
   }

  /*  
      precondition: String linked list
      postcondition: returns a reference to a list whose first node's value is specified by the argument, 
                     and the first node's next node is the original list
   */
   public static ListNode<String> insertFirst(ListNode  <String> head, String value) {
      ListNode<String> newNode = new ListNode<String>(value, head);
      return newNode;
   }

  /*  
      precondition: String linked list
      postcondition: returns a reference to a list whose last node's value is specified by the argument, 
                     such that the last node has been added to the original list
   */
   public static ListNode<String> insertLast(ListNode  <String> head, String value ) {
      ListNode<String> newNode = new ListNode<String>(value, null);
      ListNode<String> p = pointerToLast(head);
      p.setNext(newNode);
      return head;
   }

   /* Make sure you have pre- and postconditions for each method */
      
      
} // ListExplorationExtension_shell


class ListNode <E> 
{    
   private E value;    
   private ListNode <E>  next; 
   
   public ListNode  (E  initValue, ListNode <E> initNext)    
   { 
      value = initValue; 
      next = initNext; 
   }  
   public E getValue()  
   { 
      return value; 
   }    
  
   public ListNode <E> getNext() 
   { 
      return next;  
   } 
   
   public void setValue(E theNewValue)
   { 
      value = theNewValue;
   }
   
   public void setNext(ListNode  <E> theNewNext)
   { 
      next = theNewNext; 
   }
 
}  // end of ListNode
