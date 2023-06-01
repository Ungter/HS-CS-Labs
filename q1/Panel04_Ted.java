/**********************************
 * Name: Ted Sha
 * 
 * Lab: GUI Lab - up to Lab 4
 * 
 * What I learned:
 *   - How to use classes for GUI
 *   - Using the ActionListener to assign
 *     functions to a button
 *   - How to know which button was pressed
 *     and respond accordingly 
 *   - Adding images to GUI programs (ImageIcon)
 *   - Calling the program from another file
 *   - Setting fonts for text
 ***********************************/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Panel04_Ted extends JPanel{
    private JButton nButton, sButton, wButton, eButton, cButton;
    private ImageIcon cIcon;
    private JTextField sText;

    public Panel04_Ted() {
        setLayout(new BorderLayout());

        nButton = new JButton("Top");
        nButton.setBackground(new Color(194, 249, 112));
        nButton.setBorderPainted(false);
        nButton.addActionListener(new ListenerBtn());

        sButton = new JButton("Bottom");
        sButton.setBackground(new Color(68, 52, 79));
        sButton.addActionListener(new ListenerBtn());
        sText = new JTextField("Enter Something", 240);
        sButton.add(sText);
        sText.addActionListener(new ListenerBtn());

        wButton = new JButton("Left");
        wButton.setFont(new Font("Arial", Font.BOLD, 12));
        wButton.setBackground(new Color(86, 77, 128));
        wButton.setBorder(BorderFactory.createLineBorder(Color.black));
        wButton.addActionListener(new ListenerBtn());

        eButton = new JButton("Right");
        eButton.setFont(new Font("Arial", Font.BOLD, 12));
        eButton.setBackground(new Color(152, 166, 212));
        eButton.addActionListener(new ListenerBtn());

        // Had to but the full path otherwise it wouldn't work
        cIcon = new ImageIcon("ghost.gif");
        cButton = new JButton(cIcon);
        cButton.setBackground(new Color(211, 252, 213));
        cButton.addActionListener(new ListenerBtn());

        add(nButton, BorderLayout.NORTH);
        add(sButton, BorderLayout.SOUTH);
        add(wButton, BorderLayout.WEST);
        add(eButton, BorderLayout.EAST);
        add(cButton, BorderLayout.CENTER);

        nButton.setHorizontalAlignment(SwingConstants.CENTER);
        sButton.setHorizontalAlignment(SwingConstants.CENTER);
        cButton.setHorizontalAlignment(SwingConstants.CENTER);
    }

    private class ListenerBtn implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            
            switch (e.getActionCommand()) {
                case "Top":
                    nButton.setText("Clicked!");
                    break;

                case "Bottom":
                    sButton.setText("Clicked!");
                    break;

                case "Left":
                    wButton.setText("Clicked!");
                    break;

                case "Right":
                    eButton.setText("Clicked!");
                    break;

                case "Center":
                    cButton.setText("Clicked!");
                    break;

                case "Enter Something":
                    sText.setText("Enter was pressed");

                    nButton.setBackground(Color.WHITE);
                    sButton.setBackground(Color.WHITE);
                    wButton.setBackground(Color.WHITE);
                    eButton.setBackground(Color.WHITE);
                    cButton.setBackground(Color.WHITE);

                    break;

                default:
                    break;
            }
        }
    }
}