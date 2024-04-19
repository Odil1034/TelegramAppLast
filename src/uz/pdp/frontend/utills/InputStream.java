package uz.pdp.frontend.utills;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputStream {
    private static final Scanner scanInt = new Scanner(System.in);
    private static final Scanner scanStr = new Scanner(System.in);

    private InputStream() {
    }

    public static int getInt(String hint) {
        int num;
        while (true) {
            try {
                System.out.print(hint);
                num = scanInt.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input! Please enter an integer.");
                scanInt.nextLine();
            }
        }
        return num;
    }

    public static String getStr(String hint) {
        System.out.print(hint);
        return scanStr.nextLine();
    }

    public static double getDouble(String hint) {
        double num;
        while (true) {
            try {
                System.out.print(hint);
                num = scanInt.nextDouble();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Incorrect input! Please enter a valid number.");
                scanInt.nextLine();
            }
        }
        return num;
    }

}
