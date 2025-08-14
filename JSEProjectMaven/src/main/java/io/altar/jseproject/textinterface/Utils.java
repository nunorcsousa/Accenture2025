package io.altar.jseproject.textinterface;

import java.util.Scanner;
import io.altar.jseproject.exceptions.*;

public class Utils {

	private static Scanner scanner = new Scanner(System.in);
	
    public int readInt(String msg, int min, int max) {
        int value;
        while (true) {
            System.out.print(msg + " ");
            try {
                value = Integer.parseInt(scanner.nextLine());
                if (value >= min && value <= max) return value;
            } catch (NumberFormatExceptionSM ignored) {}
            System.out.println("Valor inválido. Tente novamente.");
        }
    }

    public double readDouble(String msg) {
        double value;
        while (true) {
            System.out.print(msg + " ");
            try {
                value = Double.parseDouble(scanner.nextLine());
                return value;
            } catch (NumberFormatExceptionSM ignored) {
                System.out.println("Valor inválido.");
            }
        }
    }

    public long readLong(String msg) {
        long value;
        while (true) {
            System.out.print(msg + " ");
            try {
                value = Long.parseLong(scanner.nextLine());
                return value;
            } catch (NumberFormatExceptionSM ignored) {
                System.out.println("Valor inválido.");
            }
        }
    }

    public String readLine(String msg) {
        System.out.print(msg + " ");
        return scanner.nextLine();
    }
}
