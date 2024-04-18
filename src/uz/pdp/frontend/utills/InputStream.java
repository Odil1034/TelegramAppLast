package uz.pdp.frontend.utills;

import java.util.InputMismatchException;
import java.util.Scanner;

public interface InputStream {
    Scanner scanInt = new Scanner(System.in);
    Scanner scanStr = new Scanner(System.in);

    static int getInt(String hint) {
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

    static String getStr(String hint){
        System.out.print(hint);
        return scanStr.nextLine();
    }

    static double getDouble(String hint) {
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
