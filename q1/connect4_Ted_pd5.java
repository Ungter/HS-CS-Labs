/********************************* 
 * Name: Ted Sha
 * Program: Connect 4 Game
 * 
 * Description: This program is a game of Connect 4. The user can 
 *              play against another player.
 * 
 * Stuff I learned:
 *   - This is very similar to the Battleship game.
 *   - How to check in every direction.
 *   - Using the "new Color(xxx,xxx,xxx)" to create a color.
*********************************/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class connect4_Ted_pd5  extends JPanel {
   private JButton[][] board;
   private int[][] matrix;
   private boolean player;
   private JLabel label;
   private JButton reset;
   
   public connect4_Ted_pd5()
   {      
      setLayout(new BorderLayout());

      // true = player1; false = player2
      player = true; 

      JPanel north = new JPanel();
      north.setLayout(new FlowLayout());
      add(north, BorderLayout.NORTH);
      label = new JLabel("Player 1's turn");
      north.add(label);
      
      JPanel center = new JPanel();
      center.setLayout(new GridLayout(6,7));
      add(center, BorderLayout.CENTER);
      board = new JButton[6][7];
      matrix = new int[6][7];
      for(int r=0;r<6;r++)
      {
         for(int c=0;c<7;c++)
         {
            matrix[r][c]=0;
            board[r][c]=new JButton();
            board[r][c].setBackground(new Color(151, 207, 166));
            board[r][c].addActionListener(new play(r,c));
            center.add(board[r][c]);
            
         }
      }
      startGame();
      reset = new JButton("Reset");
      reset.addActionListener(new resetBtn());
      reset.setEnabled(true);
      add(reset, BorderLayout.SOUTH);
   }

   private class play implements ActionListener
   {
      private int myRow,myCol;
      public play(int r, int c)
      {
         myRow=r;
         myCol=c;
      }
      public void actionPerformed(ActionEvent e)
      {
            // Rotate player
            if (player) {
                    matrix[myRow][myCol] = 1;
                    board[myRow][myCol].setBackground(new Color(194, 249, 112));
                    // Start from bottom
                    if (myRow > 0) {
                        board[myRow - 1][myCol].setEnabled(true);
                    }
                    
                    // Check in every direction
                    if (checkWin(1, myRow, myCol, 1, 0) ||
                        checkWin(1, myRow, myCol, -1, 0) ||
                        checkWin(1, myRow, myCol, 0, 1) ||
                        checkWin(1, myRow, myCol, 0, -1) ||
                        checkWin(1, myRow, myCol, 1, 1) ||
                        checkWin(1, myRow, myCol, -1, 1) ||
                        checkWin(1, myRow, myCol, 1, -1) ||
                        checkWin(1, myRow, myCol, -1, -1)) {

                            label.setText("Player 1 wins! ");
                            setButtons(false);
                    } else {
                        label.setText("Player 2's turn");
                        board[myRow][myCol].setEnabled(false);
                        player = false;
                    }

            } else {
                    matrix[myRow][myCol] = 2;
                    board[myRow][myCol].setBackground(new Color(64, 50, 128));
                    // Start from bottom
                    if (myRow > 0) {
                        board[myRow - 1][myCol].setEnabled(true);
                    }

                    // Check in every direction
                    if (checkWin(2, myRow, myCol, 1, 0) ||
                        checkWin(2, myRow, myCol, -1, 0) ||
                        checkWin(2, myRow, myCol, 0, 1) ||
                        checkWin(2, myRow, myCol, 0, -1) ||
                        checkWin(2, myRow, myCol, 1, 1) ||
                        checkWin(2, myRow, myCol, -1, 1) ||
                        checkWin(2, myRow, myCol, 1, -1) ||
                        checkWin(2, myRow, myCol, -1, -1)) {

                            label.setText("Player 2 wins! ");
                            setButtons(false);

                    } else {
                        label.setText("Player 1's turn");
                        board[myRow][myCol].setEnabled(false);
                        player = true;
                    }
            }
      }
   }  // Handler1

   private class resetBtn implements ActionListener
   {
      public void actionPerformed(ActionEvent e) 
      {
          // reset the integer maxtrix to 0 and the button colors to blue
            for(int r=0;r<6;r++)
            {
                 for(int c=0;c<7;c++)
                 {
                    matrix[r][c]=0;
                    board[r][c].setBackground(new Color(151, 207, 166));
                 }
            }
            
            startGame();
            label.setText("Player 1's turn");
            
      }
   } 
   

    public static void main(String[] args)
   {
      JFrame frame = new JFrame("Connect 4");
      frame.setSize(400,400);
      frame.setLocation(200,100);
      frame.setContentPane(new connect4_Ted_pd5());
      frame.setVisible(true);
   }

   public void setButtons(boolean bool) {
    for(int r=0;r<6;r++)
    {
        for(int c=0;c<7;c++)
        {
            board[r][c].setEnabled(bool);
        }
    }
   }

   // check if there is a win
   // rowD and colD determines which way the check is going
   public boolean checkWin(int check, int row, int col, int rowD, int colD) {
    boolean match = false;
    int matches = 0;
    while (row < 6 && row >= 0 && col < 7 && col >= 0) {
        int test = matrix[row][col];
        if (test != check && match) {
            break;
        } else if (test == check) {
            match = true;
            matches++;
        }
        row += rowD;
        col += colD;
    }
    return matches == 4;
   }

   public void startGame() {
    setButtons(false);
    for (int i = 0; i < 7; i++) {
      board[5][i].setEnabled(true);
    }
   }
}
