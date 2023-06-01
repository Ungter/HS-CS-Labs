/*
 * Ted Sha, Pd 6
 * 
 * Interface Polygon Lab
 * 
 * Note: For the square/rectangle, I just used the same
 *       method since they are basically the same.
 * 
 *       Wrote a very dumbed down GUI with spagetti code 
 *       since I felt like clicking buttons was easier 
 *       than navigating the console.
 * 
 *       Everything is in Integers.
 *        
 */
import java.lang.Math;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public interface TedShaPolygonPd6 {

    public int area();
    public int perimeter();

}

abstract class Triangle implements TedShaPolygonPd6 {

    public abstract int area();

    public abstract int perimeter();
}

abstract class Quad implements TedShaPolygonPd6 {
    private int side1, side2;

    public Quad(int side1, int side2) {
        this.side1 = side1;
        this.side2 = side2;
    }

    public int area() {
        return side1 * side2;
    }

    public int perimeter() {
        return side1 * 2 + side2 * 2;
    }
}

class Pentagon implements TedShaPolygonPd6 {
    private int side;

    public Pentagon(int side) {
        this.side = side;
    }

    public int area() {
        return (int) (0.25 * Math.sqrt(5 * (5 + 2 * Math.sqrt(5))) * (side * side));
    }

    public int perimeter() {
        return side * 5;
    }
}

class Hexagon implements TedShaPolygonPd6 {
    private int side;

    public Hexagon(int side) {
        this.side = side;
    }

    public int area() {
        return (int) (3 * Math.sqrt(3) / 2 * (side * side));
    }

    public int perimeter() {
        return side * 6;
    }
}

class Octagon implements TedShaPolygonPd6 {
    private int side;

    public Octagon(int side) {
        this.side = side;
    }

    public int area() {
        return (int) (2 * (1 + Math.sqrt(2)) * (side * side) );
    }

    public int perimeter() {
        return side * 8;
    }
}

// Triangles
class IsoTriangle extends Triangle {

    private int base, base2;
    public IsoTriangle(int base, int base2) {
        this.base = base;
        this.base2 = base2;
    }

    public int perimeter() {
        return base2 * 2 + base;
    }

    public int area() {
        return (int) (0.5 * base * (Math.sqrt(base2 * base2 - ((base * base) / 4))));
    }
}

class EquTriangle extends Triangle {
    private int base;

    public EquTriangle(int base) {
        this.base = base;
    }

    public int perimeter() {
        return base * 3;
    }

    public int area() {
        return (int) (((base * base) * Math.sqrt(3)) / (4)); 
    }
}

// Quads
class Square extends Quad {
    
    public Square(int side1, int side2) {
        super(side1, side2);
    }   
}

class Rectangle extends Quad {

    public Rectangle(int side1, int side2) {
        super(side1, side2);
    }
}

// Driver
class PolygonDriver {
    
    public static void main(String[] args) throws NumberFormatException{

        JFrame frame = new JFrame("Polygon Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocation(100, 100);
        frame.setLayout(new FlowLayout());

        JLabel label = new JLabel("Select a type of polygon: ");

        JButton square = new JButton("Square");
        JButton rectangle = new JButton("Rectangle");
        JButton EquTriangle = new JButton("Equilateral Triangle");
        JButton IsoTriangle = new JButton("Isosceles Triangle");
        JButton pentagon = new JButton("Pentagon");
        JButton hexagon = new JButton("Hexagon");
        JButton Octagon = new JButton("Octagon");

        frame.add(label);
        frame.add(square);
        frame.add(rectangle);
        frame.add(EquTriangle);
        frame.add(IsoTriangle);
        frame.add(pentagon);
        frame.add(hexagon);
        frame.add(Octagon);
        
        frame.setVisible(true);
        square.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                squarePane();
            }
        });

        rectangle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                squarePane();
            }
        });

        IsoTriangle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                isoPane();
            }
        });

        pentagon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pentaPane();
            }
        });

        hexagon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hexaPane();
            }
        });

        Octagon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                octaPane();
            }
        });

        EquTriangle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                equPane();
            }
        });
        
    }

    public static void squarePane() {
        JFrame square1 = new JFrame("Square/Rectangle");
        square1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        square1.setSize(500, 500);
        square1.setLocation(100, 100);
        square1.setLayout(new FlowLayout());

        JLabel label = new JLabel("Enter your dimensions:\n");

        JTextField side1 = new JTextField("Width", 20);
        JTextField side2 = new JTextField("Height", 20);

        JLabel area = new JLabel("Area:");
        JLabel perm = new JLabel("Perimeter:");
                
        JButton submit = new JButton("Submit");
        square1.setVisible (true);

        square1.add(label);
        square1.add(side1);
        square1.add(side2);
        square1.add(submit);
        square1.add(area);
        square1.add(perm);

        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int newSide = Integer.parseInt(side1.getText());
                int newSide1 = Integer.parseInt(side2.getText());

                Square r = new Square(newSide, newSide1);
                area.setText("Area: " + Integer.toString(r.area()));
                perm.setText("Perimeter: " + Integer.toString(r.perimeter()));
            }
        });
    }

    public static void isoPane() {
        JFrame triangle1 = new JFrame("Isosceles Triangle");
        triangle1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        triangle1.setSize(500, 500);
        triangle1.setLocation(100, 100);
        triangle1.setLayout(new FlowLayout());

        JLabel label = new JLabel("Enter your dimensions:\n");

        JTextField side1 = new JTextField("Base", 20);
        JTextField side2 = new JTextField("Side 2&3", 20);

        JLabel area = new JLabel("Area:");
        JLabel perm = new JLabel("Perimeter:");
                
        JButton submit = new JButton("Submit");
        triangle1.setVisible (true);

        triangle1.add(label);
        triangle1.add(side1);
        triangle1.add(side2);
        triangle1.add(submit);
        triangle1.add(area);
        triangle1.add(perm);

        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int newSide = Integer.parseInt(side1.getText());
                int newSide1 = Integer.parseInt(side2.getText());

                IsoTriangle r = new IsoTriangle(newSide, newSide1);
                area.setText("Area: " + Integer.toString(r.area()));
                perm.setText("Perimeter: " + Integer.toString(r.perimeter()));
            }
        });
    }

    public static void equPane() {
        JFrame triangle1 = new JFrame("Equilateral Triangle");
        triangle1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        triangle1.setSize(500, 500);
        triangle1.setLocation(100, 100);
        triangle1.setLayout(new FlowLayout());

        JLabel label = new JLabel("Enter your dimensions:\n");

        JTextField side1 = new JTextField("Side", 20);

        JLabel area = new JLabel("Area:");
        JLabel perm = new JLabel("Perimeter:");
                
        JButton submit = new JButton("Submit");
        triangle1.setVisible (true);

        triangle1.add(label);
        triangle1.add(side1);
        triangle1.add(submit);
        triangle1.add(area);
        triangle1.add(perm);

        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int newSide = Integer.parseInt(side1.getText());

                EquTriangle r = new EquTriangle(newSide);
                area.setText("Area: " + Integer.toString(r.area()));
                perm.setText("Perimeter: " + Integer.toString(r.perimeter()));
            }
        });
    }

    public static void pentaPane() {
        JFrame square1 = new JFrame("Pentagon");
        square1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        square1.setSize(500, 500);
        square1.setLocation(100, 100);
        square1.setLayout(new FlowLayout());

        JLabel label = new JLabel("Enter your dimensions:\n");

        JTextField side1 = new JTextField("Side", 20);

        JLabel area = new JLabel("Area:");
        JLabel perm = new JLabel("Perimeter:");
                
        JButton submit = new JButton("Submit");
        square1.setVisible (true);

        square1.add(label);
        square1.add(side1);
        square1.add(submit);
        square1.add(area);
        square1.add(perm);

        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int newSide = Integer.parseInt(side1.getText());

                Pentagon r = new Pentagon(newSide);
                area.setText("Area: " + Integer.toString(r.area()));
                perm.setText("Perimeter: " + Integer.toString(r.perimeter()));
            }
        });
    }

    public static void hexaPane() {
        JFrame square1 = new JFrame("Hexagon");
        square1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        square1.setSize(500, 500);
        square1.setLocation(100, 100);
        square1.setLayout(new FlowLayout());

        JLabel label = new JLabel("Enter your dimensions:\n");

        JTextField side1 = new JTextField("Side", 20);

        JLabel area = new JLabel("Area:");
        JLabel perm = new JLabel("Perimeter:");
                
        JButton submit = new JButton("Submit");
        square1.setVisible (true);

        square1.add(label);
        square1.add(side1);
        square1.add(submit);
        square1.add(area);
        square1.add(perm);

        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int newSide = Integer.parseInt(side1.getText());

                Hexagon r = new Hexagon(newSide);
                area.setText("Area: " + Integer.toString(r.area()));
                perm.setText("Perimeter: " + Integer.toString(r.perimeter()));
            }
        });
    }

    public static void octaPane() {
        JFrame square1 = new JFrame("Octagon");
        square1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        square1.setSize(500, 500);
        square1.setLocation(100, 100);
        square1.setLayout(new FlowLayout());

        JLabel label = new JLabel("Enter your dimensions:\n");

        JTextField side1 = new JTextField("Side", 20);

        JLabel area = new JLabel("Area:");
        JLabel perm = new JLabel("Perimeter:");
                
        JButton submit = new JButton("Submit");
        square1.setVisible (true);

        square1.add(label);
        square1.add(side1);
        square1.add(submit);
        square1.add(area);
        square1.add(perm);

        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int newSide = Integer.parseInt(side1.getText());

                Octagon r = new Octagon(newSide);
                area.setText("Area: " + Integer.toString(r.area()));
                perm.setText("Perimeter: " + Integer.toString(r.perimeter()));
            }
        });
    }
}