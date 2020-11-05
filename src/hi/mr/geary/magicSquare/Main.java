package hi.mr.geary.magicSquare;

import hi.mr.geary.magicSquare.exceptions.InvalidInput;
import hi.mr.geary.magicSquare.exceptions.InvalidSize;

import java.time.temporal.ValueRange;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    public static final ValueRange squareRange = ValueRange.of(2, 8);

    public static void main(String[] args) throws InvalidInput {
        System.out.println("Welcome to the Magic Square Calculator! Keep in mind, it can only calculate squares from 2x2 to 8x8.");
        System.out.println("These are our options:");
        System.out.println("    1. Check if your square is a magic square ");
        System.out.println("    2. Make a magic square with one number given");
        System.out.print("What would you like to do? (1/2): ");
        int option = scanner.nextInt();
        System.out.println();

        if (option == 1) {
            System.out.println("Nice! You want to check if you have a valid magic square.");
            System.out.print("Lets start with the size of the square (2-8): ");
            int n = scanner.nextInt();
            if (Square.notInRange(n)) throw new InvalidSize(n);
            Integer[][] data = new Integer[n][n];
            System.out.println("Sick, we have " + (int) Math.pow(n, 2) + " spaces to fill. We will start with the top left and work sideways. If there are duplicates, the square is automatically not magic.");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print("Please input the " + (i * n + j + 1) + " number: ");
                    int input = scanner.nextInt();
                    if (input > 0) data[i][j] = input;
                    else throw new InvalidInput("The number you entered is less than 0.");
                }
            }
            Square square = new Square(data);
            if (square.magic)
                System.out.println("Your square is magic! The square constant is: " + square.magicConstant() + ".");
            else System.out.println("That is not a magic square! Thanks and bye.");
        } else if (option == 2) {
            System.out.println("Cool! You would like to make a magic square!");
            System.out.print("How big do you want your square in side length? (2-8): ");
            //hey mr. geary I spent a lot of time coding it so that it can generate ANY magic square for any number, so if you want to see that, comment out line 49
            int n = scanner.nextInt();
            if (Square.notInRange(n)) throw new InvalidSize(n);
            //if (n < 1) throw new InvalidSize(n);
            System.out.print("What number would you like to start with? (any number): ");
            int startingNumber = scanner.nextInt();
            Square generatedSquare = MagicSquareGenerator.squareGenerator(n, startingNumber);
            if (generatedSquare != null) {
                System.out.println("Fantastic, here is the generated square! \n");
                System.out.println(generatedSquare);
            } else System.out.println("Two does not make a valid magic square.");

        } else throw new InvalidInput(option + " is not a valid choice.");
    }
}
