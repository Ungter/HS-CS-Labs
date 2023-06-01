/**************************
 * Name: Ted Sha
 * 
 * Lab: GUI Lab - Lab 5
 * 
 * What I learned:
 *  - "default" in switch statement works
 *    with Action Listener
 *  
 **************************/


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI_Lab06_Ted extends JPanel{
    
    private JButton n1Button, n2Button, n3Button, e1Button, e2Button, e3Button;
    private JLabel sLabel;

    public GUI_Lab06_Ted() {
        setLayout(new BorderLayout());

        n1Button = new JButton("11");
        n1Button.setBackground(new Color(194, 249, 112));
        n1Button.setBorderPainted(false);
        n1Button.addActionListener(new ListenerBtn());

        n2Button = new JButton("2");
        n2Button.setBackground(new Color(194, 249, 112));
        n2Button.setBorderPainted(false);
        n2Button.addActionListener(new ListenerBtn());

        n3Button = new JButton("10");
        n3Button.setBackground(new Color(194, 249, 112));
        n3Button.setBorderPainted(false);
        n3Button.addActionListener(new ListenerBtn());

        e1Button = new JButton("eleven");
        e1Button.setFont(new Font("Arial", Font.BOLD, 12));
        e1Button.setBackground(new Color(152, 166, 212));
        e1Button.addActionListener(new ListenerBtn());

        e2Button = new JButton("one one");
        e2Button.setFont(new Font("Arial", Font.BOLD, 12));
        e2Button.setBackground(new Color(152, 166, 212));
        e2Button.addActionListener(new ListenerBtn());

        e3Button = new JButton("I don't know");
        e3Button.setFont(new Font("Arial", Font.BOLD, 12));
        e3Button.setBackground(new Color(152, 166, 212));
        e3Button.addActionListener(new ListenerBtn());

        sLabel = new JLabel("1 + 1 = ? ", SwingConstants.CENTER);
        sLabel.setFont(new Font("Arial", Font.BOLD, 12));
        sLabel.setBackground(new Color(86, 77, 128));

        // create a north flowpanel with 3 buttons
        JPanel nFlowPanel = new JPanel();
        nFlowPanel.setLayout(new FlowLayout());
        nFlowPanel.add(n1Button);
        nFlowPanel.add(n2Button);
        nFlowPanel.add(n3Button);


        // create a east grid layout panel with 3 buttons
        JPanel eGridPanel = new JPanel();
        eGridPanel.setLayout(new GridLayout(3, 1));
        eGridPanel.add(e1Button);
        eGridPanel.add(e2Button);
        eGridPanel.add(e3Button);

        // create a south label 
        JPanel sPanel = new JPanel();
        sPanel.setLayout(new FlowLayout());
        sPanel.add(sLabel);

        // add the panels to the frame
        add(nFlowPanel, BorderLayout.NORTH);
        add(eGridPanel, BorderLayout.EAST);
        add(sPanel, BorderLayout.SOUTH);
        
    }


    // create a listener for the buttons
    // use switch statement to determine which button was pressed
    // and set the label text accordingly

    public class ListenerBtn implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            String s = e.getActionCommand();
            switch(s){
                case "2":
                    sLabel.setText("Correct!!!");
                    break;
                default:
                    sLabel.setText("Incorrect, when you add 1 to 1 you get 2");
                    break;
            }
        }
    }
    public static void main(String[] args) {

        JFrame frame = new JFrame("GUI 05");
        frame.setSize(400, 400);
        frame.setLocation(200, 200);   
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new GUI_Lab06_Ted());
        frame.setVisible(true);

    }
}
