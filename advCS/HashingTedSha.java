/*****************************************************************************************************************
NAME: Ted Sha     
PERIOD: 6th
DUE DATE: March 20th

PURPOSE: Practice with hashing and managing collisions.   

WHAT I LEARNED:  Chaining storing, linear probing, and rehashing using relatively prime numbers in code form.   
         
CREDITS (BE SPECIFIC: FRIENDS, PEERS, ONLINE WEBSITE): 

****************************************************************************************************************/
/***********************************************************************************
   Assignment:  This hashing program results in collisions.
                You are to implement three different collision schemes: 
                linear probing, relative prime probing (use the first relatively prime 
                number of the length of the hash table as the step increase), and 
                chaining.  Then implement a search algorithm that is appropriate
                for each collision scheme.
 ***********************************************************************************/
import java.util.*;
import javax.swing.*;

public class HashingTedSha
{
   public static void main(String[] args)
   {
      int arrayLength = Integer.parseInt(JOptionPane.showInputDialog(
                         "Hashing!\n"+
                         "Enter the size of the array:  ")); // enter 20
       
      int numItems = Integer.parseInt(JOptionPane.showInputDialog(
                         "Add n items:  "));                 // enter 15
     
      int scheme = Integer.parseInt(JOptionPane.showInputDialog(
           "The Load Factor is " + (double)numItems/arrayLength +
           "\nWhich collision scheme?\n"+
           "1. Linear Probing\n" +
           "2. Relatively Prime Probing\n"+
           "3. Chaining"));
      Hashtable table = null;
      switch( scheme )
      {
         case 1:   
            table = new HashtableLinearProbe(arrayLength);
            break;
         case 2: // rehash using the first relatively prime of arrayLength
            table = new HashtableRehash(arrayLength); 
            break;
         case 3:  
            table = new HashtableChaining(arrayLength);
            break;
         default:  System.exit(0);    
      }
      for(int i = 0; i < numItems; i++)
         table.add("Item" + i);
      String action = JOptionPane.showInputDialog(
                       "Search for:  Item0" + " to "+ "Item"+(numItems-1));
      int itemNumber = 0;
      if (action != null)
      {
         itemNumber = Integer.parseInt(action);
         while( itemNumber != -1 )
         {
            String key = "Item" + itemNumber;
            int index = table.indexOf(key); 
            if( index >= 0)    //found it
               System.out.println(key + " found  at index " + index);
            else
               System.out.println(key + " not found!");
            action = JOptionPane.showInputDialog(
                       "Search for:  Item0" + " to "+ "Item"+(numItems-1));
            if (action != null)
               itemNumber = Integer.parseInt(action); 
            else
               itemNumber = -1;                         
         } 
      }
      System.out.println ("Goodbye!");
      System.exit(0);
   }
}

interface Hashtable
{
   void add(Object obj);
   int indexOf(Object obj);
}


class HashtableLinearProbe implements Hashtable
{
   private Object[] array;
   public HashtableLinearProbe(int size)
   {
      // your code goes here   
      array = new Object[size];
                
   }
   public void add(Object obj)
   {
      int code = obj.hashCode();
      int index = Math.abs(code % array.length);
      if ( array[index] == null )  //empty
      {
         //insert it
         array[index] = obj;
         System.out.println(obj + "\t" + code + "\t" + index);
      }
      else    //collision
      {
         System.out.println(obj + "\t" + code + "\tCollision at "+ index);
         index = linearProbe(index);
         array[index] = obj;
         System.out.println(obj + "\t" + code + "\t" + index);
      }
   }  
   
   
   public int linearProbe(int index)
   {
      //be sure to wrap around the array.
      while(array[index] != null)
      {
         index = (index + 1) % array.length;
      }
      return index;
   }
   
   
   public int indexOf(Object obj)     
   {
      int index = Math.abs(obj.hashCode() % array.length);
      while(array[index] != null)
      {
         if( obj.equals(array[index]) )  //found it
         {
            return index;
         }
         else    //search for it in a linear probe manner
         {
            index = (index + 1) % array.length;
            System.out.println("Looking at index " + index);
         }
      } // while
      //not found
      return -1;
   } // indexOf
} // HashtableLinearProbe


class HashtableRehash implements Hashtable
{
   private Object[] array;
   private int constant = 2;
   
   public HashtableRehash(int size)
   {
                             //constructor
                             //find a constant that is relatively prime to the size of the array
      array = new Object[size];
      while (gcd(size, constant) != 1) {
         constant++;
      }
   }
   
   
   public void add(Object obj)
   {
      int code = obj.hashCode();
      int index = Math.abs(code % array.length);
      if( array[index] == null )  //empty
      {
         //insert it
         array[index] = obj;
         System.out.println(obj + "\t" + code + "\t" + index);
      }
      else //collision
      {
         System.out.println(obj + "\t" + code + "\tCollision at "+ index);
         index = rehash(index);
         array[index] = obj;
         System.out.println(obj + "\t" + code + "\t" + index);
      }
   }  
   
   public int rehash(int index)
   {  
      int newIndex = (index + constant) % array.length;
      while (array[newIndex] != null) {
         newIndex = (newIndex + constant) % array.length;
      }
      return newIndex;
   }
   
   public  int indexOf(Object obj)
   {
      int index = Math.abs(obj.hashCode() % array.length);
      int initialIndex = index;
      boolean firstIteration = true;
      while(array[index] != null)
      {
         if( obj.equals(array[index]) )  //found it
         {
            return index;
         }
         else //search for it in a rehashing manner
         {
            index = (index + constant) % array.length;
            System.out.println("Looking at index " + index);
            if (index == initialIndex && !firstIteration) // already searched the entire array
            {
               break;
            }
            firstIteration = false;
         }
      }
      //not found
      return -1;
   }

   private int gcd(int a, int b) {
      if (b == 0) {
         return a;
      }
      return gcd(b, a % b);
   }
} // HashTableRehash


class HashtableChaining implements Hashtable
{
   private LinkedList[] array;
   public HashtableChaining(int size)
   {
                            //instantiate the array
                            //instantiate the LinkedLists
      array = new LinkedList[size];
      for (int i = 0; i < size; i++) {
         array[i] = new LinkedList<>();
      }
                            
   }
   public void add(Object obj)
   {
      int code = obj.hashCode();
      int index = Math.abs(code % array.length);
      array[index].addFirst(obj);
      System.out.println(obj + "\t" + code + " " + " at " +index + ": "+ array[index]);
   }  
   public int indexOf(Object obj)
   {
      int index = Math.abs(obj.hashCode() % array.length);
      if( !array[index].isEmpty() )
      {
         int position = array[index].indexOf(obj);
         if(position != -1)  //found it
         {
            return index;
         }
         else //search for it in a chaining manner
         {
            return -1;
         }
      }
      return -1; //not found
   } // indexOf
} // HashtableChaining