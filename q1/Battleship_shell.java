import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Battleship_shell extends JPanel
{
   private JButton[][] board;
   private int[][]matrix;
   private int hits;
   private int torpedoes;
   private JLabel label;
   private JButton reset;
   
   public Battleship_shell()
   {      
      setLayout(new BorderLayout());
      hits = 0;
      torpedoes = 20;
      
      JPanel north = new JPanel();
      north.setLayout(new FlowLayout());
      add(north, BorderLayout.NORTH);
      label = new JLabel("You have 20 torpedoes");
      north.add(label);
      
      JPanel center = new JPanel();
      center.setLayout(new GridLayout(10,10));
      add(center, BorderLayout.CENTER);
      board = new JButton[10][10];
      matrix = new int[10][10];
      for(int r=0;r<10;r++)
      {
         for(int c=0;c<10;c++)
         {
            matrix[r][c]=0;
            board[r][c]=new JButton();
            board[r][c].setBackground(Color.blue);
            board[r][c].addActionListener(new Handler1(r,c));
            center.add(board[r][c]);
         }
      }
      reset = new JButton("Reset");
      reset.addActionListener(new Handler2());
      reset.setEnabled(true);
      add(reset,BorderLayout.SOUTH);
      
      placeShip();
   }
   
   private void placeShip()
   {
        int r = (int)(Math.random()*9);
        int c = (int)(Math.random()*9);
        int dir = (int)(Math.random()*2);
        int length = 4;
        if(dir==0)
        {
            for(int i=0;i<length;i++)
            {
                matrix[r][c+i]=1;
            }
        }
        else
        {
            for(int i=0;i<length;i++)
            {
                matrix[r+i][c]=1;
            }
        }
   } // placeShip
   
   
   private class Handler1 implements ActionListener
   {
      private int myRow,myCol;
      public Handler1(int r, int c)
      {
         myRow=r;
         myCol=c;
      }
      public void actionPerformed(ActionEvent e)
      {
            if(matrix[myRow][myCol]==1)
            {
                board[myRow][myCol].setBackground(Color.red);
                torpedoes--;
                hits++;
                if(hits==4)
                {
                label.setText("You sunk the battleship!");
                setButtons(false);
                reset.setEnabled(true);
                }
            }
            else
            {
                board[myRow][myCol].setBackground(Color.yellow);
                torpedoes--;
                label.setText("You have "+torpedoes+" torpedoes");
                if(torpedoes==0)
                {
                label.setText("You have no more torpedoes");
                setButtons(false);
                reset.setEnabled(true);
                }
            }
      }
   }  // Handler1
   
   
   private class Handler2 implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
          // reset the integer maxtrix to 0 and the button colors to blue
            for(int r=0;r<10;r++)
            {
                 for(int c=0;c<10;c++)
                 {
                    matrix[r][c]=0;
                    board[r][c].setBackground(Color.blue);
                 }
            }
            hits = 0;
            torpedoes = 20;
            // call placeShip
            placeShip();
            setButtons(true);
      }
   }   // Handler2
   
   
   public static void main(String[] args)
   {
      JFrame frame = new JFrame("Battleship!");
      frame.setSize(400,400);
      frame.setLocation(200,100);
      frame.setContentPane(new Battleship_shell());
      frame.setVisible(true);
   }

   public void setButtons(boolean bool) {
    for(int r=0;r<10;r++)
    {
        for(int c=0;c<10;c++)
        {
            board[r][c].setEnabled(bool);
        }
    }
   }
}