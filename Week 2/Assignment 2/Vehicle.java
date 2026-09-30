import java.util.Scanner;

public class Vehicle {

    String brand;
    int speed;

    void displayInfo() {
        System.out.println("Brand: " + brand);
        System.out.println("Speed: " + speed + " km/h");
    }

    void start() {
        System.out.println("Vehicle is starting.");
    }

    // Subclass: Car
    static class Car extends Vehicle {

        @Override
        void start() {
            System.out.println("Car is starting with a key.");
        }
    }

    // Subclass: Bike
    static class Bike extends Vehicle {

        @Override
        void start() {
            System.out.println("Bike is starting with a self-start button.");
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("================================");
        System.out.println("       VEHICLE INFORMATION");
        System.out.println("================================");

        System.out.print("Enter car brand: ");
        String carBrand = sc.nextLine();

        System.out.print("Enter car speed: ");
        int carSpeed = sc.nextInt();

        sc.nextLine();

        System.out.print("\nEnter bike brand: ");
        String bikeBrand = sc.nextLine();

        System.out.print("Enter bike speed: ");
        int bikeSpeed = sc.nextInt();

        // Creating Car object
        Car car = new Car();
        car.brand = carBrand;
        car.speed = carSpeed;

        // Creating Bike object
        Bike bike = new Bike();
        bike.brand = bikeBrand;
        bike.speed = bikeSpeed;

        System.out.println("\n---------- CAR ----------");
        car.displayInfo();
        car.start();

        System.out.println("\n---------- BIKE ----------");
        bike.displayInfo();
        bike.start();

        sc.close();
    }
}