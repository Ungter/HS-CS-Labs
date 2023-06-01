import javax.swing.*;

public class GUI_Lab_Ted {
    public static void main(String[] args) {

        JFrame frame = new JFrame("First GUI");
        frame.setSize(400, 400);
        frame.setLocation(200, 200);   
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new Panel04_Ted());
        frame.setVisible(true);

    }
}
