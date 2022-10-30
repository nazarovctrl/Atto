package org.example.util;

import java.util.Scanner;

public class ScannerUtil {

    public static int getAction() {

        while (true){
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter action: ");
                return scanner.nextInt();
            } catch (RuntimeException e) {
                System.out.println("Mazgi nima bu togri kirit");
                return getAction();
            }
        }


    }
}
