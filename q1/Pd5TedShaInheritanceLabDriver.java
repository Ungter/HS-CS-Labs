/************************************************
* Qianhe Sha; Pd. 5
* Lab: Person, Vehicle, Truck
* Purpose of the Program: To create a class called Person, Vehicle, Truck,
                          and to use inheritance to create a class called Truck.
* 
* What I learned: 
*  #1: You can use the Person class all the way down to the Truck class.
**************************************************/
public class Pd5TedShaInheritanceLabDriver {
    public static void main(String[] args) {
        Person s1 = new Person("Ted", 17);
        Person s2 = new Person("Ted", 17);
        System.out.println(s1);
        System.out.println(s1.equals(s2)); // True

        s1.setName("James");

        System.out.println(s1.equals(s2)); // False
        System.out.println("------------");

        // String manuName, int cylinders, Person owner, 
        // double loadCapacity, int towingCapacity
        Truck d1 = new Truck("Little", 12, s1, 50, 50); 
        Truck d2 = new Truck();
        d2.setTowingCapacity(100);
        d1.setLoadCapacity(62.2);
        System.out.println(d1 + "\n" );
        System.out.println(d2.equals(d1)); // False
        System.out.println("--------------");

        Vehicle v1 = new Vehicle();
        Vehicle v2 = new Vehicle();
        System.out.println(v1.equals(v2) + "\n"); // True

        v1.setOwner(s1);
        v1.setCylinders(20);
        v2.setManuName("Ford");
        
        System.out.println(v2 + "\n");
        System.out.println(v1 + "\n");
        System.out.println(v1.getOwner() ); // James
        System.out.println(v2.getCylinders()); // 0
        System.out.println(v1.equals(v2)); // False
        System.out.println("--------------");

        Vehicle v3 = new Truck();
        System.out.println(v3 + "\n");
        Vehicle v4 = new Truck("BMW", 12, s2, 10, 60);
        v3.setManuName("Volvo");
        v3.setCylinders(60);
        
        System.out.println(v4 + "\n");
        System.out.println(v3.equals(v4)); // False
        System.out.println("--------------");        
    }

}

class Person {
    private String name;
    private int age;
    
    public Person() {
        name = "No name yet.";
        age = 0;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
        
    public String toString() {
        return "Name: " + name + "\nAge: " + age;
    }

    public boolean equals (Person other) {
        return (this.name.equalsIgnoreCase(other.name) && this.age == other.age);
    }
} // end of Person 

// Precondition: 
// String, int, Person
class Vehicle {
    private String manuName;
    private int cylinders;
    private Person owner;

    // Uses the Person class to create an owner
    public Vehicle(String manuName, int cylinders, Person owner) {
        this.manuName = manuName;
        this.cylinders = cylinders;
        this.owner = owner;
    }

    public Vehicle() {
        manuName = "No manufacturer yet.";
        cylinders = 0;
        owner = new Person();
    }

    public void setManuName(String manuName) {
        this.manuName = manuName;
    }

    public String getManuName() {
        return manuName;
    }

    public void setCylinders(int cylinders) {
        this.cylinders = cylinders;
    }

    public int getCylinders() {
        return cylinders;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public Person getOwner() {
        return owner;
    }

    public String toString() {
        return "Manufacturer: " + manuName + "\nCylinders: " + 
                cylinders + "\nOwner: " + owner;
    }

    public boolean equals (Vehicle other) {
        return (this.manuName.equalsIgnoreCase(other.manuName) && 
                this.cylinders == other.cylinders && 
                this.owner.equals(other.owner));
    }
} // end of Vehicle

// Percondition for construstor:
// String, int, Person, double, int
class Truck extends Vehicle {
    private double loadCapacity;
    private int towingCapacity;

    public Truck(String manuName, int cylinders, Person owner, 
            double loadCapacity, int towingCapacity) {
        super(manuName, cylinders, owner);
        this.loadCapacity = loadCapacity;
        this.towingCapacity = towingCapacity;
    }

    public Truck() {
        super();
        loadCapacity = 0;
        towingCapacity = 0;
    }

    public void setLoadCapacity(double loadCapacity) {
        this.loadCapacity = loadCapacity;
    }

    public double getLoadCapacity() {
        return loadCapacity;
    }

    public void setTowingCapacity(int towingCapacity) {
        this.towingCapacity = towingCapacity;
    }

    public int getTowingCapacity() {
        return towingCapacity;
    }

    public String toString() {
        return super.toString() + "\nLoad Capacity: " + loadCapacity + 
                "\nTowing Capacity: " + towingCapacity;
    }

    public boolean equals (Truck other) {
        return (super.equals(other) && this.loadCapacity == other.loadCapacity && 
                this.towingCapacity == other.towingCapacity);
    }
} // end of Truck

/* Program Output:
Name: Ted
Age: 17
true
false
------------
Manufacturer: Little
Cylinders: 12
Owner: Name: James
Age: 17
Load Capacity: 62.2
Towing Capacity: 50

false
--------------
true

Manufacturer: Ford
Cylinders: 0
Owner: Name: No name yet.
Age: 0

Manufacturer: No manufacturer yet.
Cylinders: 20
Owner: Name: James
Age: 17

Name: James
Age: 17
0
false
--------------
Manufacturer: No manufacturer yet.
Cylinders: 0
Owner: Name: No name yet.
Age: 0
Load Capacity: 0.0
Towing Capacity: 0

Manufacturer: BMW
Cylinders: 12
Owner: Name: Ted
Age: 17
Load Capacity: 10.0
Towing Capacity: 60

false
--------------
*/
