package by.javatr.finances.scanner;

import java.util.Scanner;

/**
 * @author Aleh Yemelyanchyk on 11/14/2019.
 */
public class DataScanner {

    private Scanner scanner = new Scanner(System.in);

    public int readInteger() {
        int number;
        while (!scanner.hasNextInt()) {
            scanner.next();
        }
        number = scanner.nextInt();
        scanner.nextLine();
        return number;
    }

    public int readIntegerInRange(int min, int max) {
        int number = 0;
        boolean quit = false;
        while (!quit) {
            quit = true;
            while (!scanner.hasNextInt()) {
                scanner.next();
            }
            number = scanner.nextInt();
            if (number < min || number > max) {
                quit = false;
            }
        }
        scanner.nextLine();
        return number;
    }

    public double readDouble() {
        double number;
        while (!scanner.hasNextDouble()) {
            scanner.next();
        }
        number = scanner.nextDouble();
        scanner.nextLine();
        return number;
    }

    public String readString() {
        String str = "";
        while (str.isEmpty()) {
            str = scanner.nextLine();
        }
        return str;
    }
}
