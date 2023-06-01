/* ----------------------------------
    Qianhe Sha; Pd. 5
    Lab: Nerd Wars

    Purpose of Program: A simple game of
    "Nerd Wars" in which is a battle between
    two players. The winner of the game is
    determined by certain rules.

    What I learned: 
        - the charAt() method
        - returns the character at the specified index in a string
        - ex: "Hello".charAt(0) returns 'H'

    Difficulties: Was wondering how to generate 
    the random stats when an player is created.
    I figured out how to do this by just putting
    the random number generator in the constructor.
    
  ---------------------------------- */

import java.util.Scanner;
public class Pd5TedShaWarrior {
    private String username;
    private int iq;
    private int strength;
    private char clique;

    // Constructor
    public Pd5TedShaWarrior(String u, char c) {
        username = u;
        clique = c;
        iq = 0;
        strength = 0; 
        generateStats();
    }

    // Start of getters and setters
    public String getUsername() {
        return username;
    }

    public int getIQ() {
        return iq;
    }

    public char getClique() {
        return clique;
    }

    public int getStrength() {
        return strength;
    }

    public void setIq(int i) {
        iq = i;
    }

    public void setStrength(int s) {
        strength = s;
    }
    // End of getters and setters

    // Sets the IQ and strength of the warrior based on its Clique
    public void generateStats() {
        if (Pd5TedShaWarrior.this.getClique() == 'N') {

            setIq((int) (Math.random() * 61) + 120);
            setStrength((int) (Math.random() * 41) + 20);
            
        } else if (Pd5TedShaWarrior.this.getClique() == 'J') {

            setIq((int) (Math.random() * 61) + 80);
            setStrength((int) (Math.random() * 51) + 50);

        } else if (Pd5TedShaWarrior.this.getClique() == 'P') {

            setIq((int) (Math.random() * 61) + 90);
            setStrength((int) (Math.random() * 51) + 40);

        } else if (Pd5TedShaWarrior.this.getClique() == 'T') {

            setIq((int) (Math.random() * 31) + 60);
            setStrength((int) (Math.random() * 21) + 80);

        } else if (Pd5TedShaWarrior.this.getClique() == 'F') {

            setIq((int) (Math.random() * 121) + 60);
            setStrength((int) (Math.random() * 100) + 1);

        }
    }

    // The warrior with the highest strength wins, 
    // unless the other warrior's iq is more than 20 higher than the warrior's iq
    public void fight(Pd5TedShaWarrior other) {
        int otherIq = other.getIQ();
        int otherStr = other.getStrength();
        int thisStr = this.getStrength();
        int thisIq = this.getIQ();

        // Note to self: When this wins, first if: by strength, second if: by iq
        //               When other wins, first if: by strength, second if: by iq
        if (thisStr > otherStr && thisIq - otherIq < 20) {
            System.out.println(this.getUsername() + " wins by strength!");
        } else if (thisIq - otherIq > 20) {
            System.out.println(this.getUsername() + " wins by IQ!");
        } else if (thisStr < otherStr && otherIq - thisIq < 20) {
            System.out.println(other.getUsername() + " wins by strength!");
        } else if (otherIq - thisIq > 20) {
            System.out.println(other.getUsername() + " wins by IQ!");
        } else {
            System.out.println("It's a tie!");
        }
    }

    // toString method
    public String toString() {
        return "Username: " + getUsername() + "\n--------------" +
               "\nIQ: " + getIQ() + "\n--------------" +
               "\nStrength: " + getStrength() + "\n--------------" +
               "\nClique: " + getClique() + "\n--------------\n";
    }
} // Base Warrior Class


class Pd5TedShaWarriorDriver {
    public static void main(String[] args) {
        Pd5TedShaWarrior x = new Pd5TedShaWarrior(getString(), getChar());
        System.out.println();
        Pd5TedShaWarrior y = new Pd5TedShaWarrior(getString(), getChar());
        System.out.println("\n" + x);
        System.out.println(y);

        /* -------------------------------------------------- 
            Method: fight

            Precondition: x and y are Pd5TedShaWarrior objects

            Postcondition: x and y fight each other and determines
            the winner based on their strength and IQ.
           -------------------------------------------------- */
        x.fight(y);
    }

    /* -------------------------------------------------
           Methods: getString, getChar

           Precondition: User must enter a String or char
            for their clique as stated below:
           'N'erd; 'J'ock; 'P'rep; 'T'hug; 'F'reak

           Postcondition: Returns the String or char
           the user enters
        ------------------------------------------------- */
    public static char getChar() {
        System.out.print("Enter your clique: ");
        return new Scanner(System.in).nextLine().charAt(0);
    }

    public static String getString() {
        System.out.print("Enter your name: ");
        return new Scanner(System.in).nextLine();
    }

    
} // Driver Class