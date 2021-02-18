package com.step.utilities;

import java.util.Scanner;

public class Utilities {
    private Scanner sc = new Scanner(System.in);

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        //not working ^

        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    public void enterAnyValueToContinue() {
        System.out.print("Enter any value to continue... ");
        sc.nextLine();
    }

    public String firstLetterUpperCase(String str) {
        str = str.trim();
        str = str.toLowerCase();
        str = str.substring(0, 1).toUpperCase() + str.substring(1);

        return str;
    }

    //used for tabulation
    public String generateMultipleChars(int n, char c) {
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < n; i++) {
            str.append(c);
        }

        return str.toString();
    }

    // inserts word with n spaces needed
    public String insertWordWithNSpaces(int maxLength, String word) {
        StringBuilder str = new StringBuilder();
        str.append(word);

        int n = maxLength - word.length();
        for (int i = 0; i < n; i++) {
            str.append(' ');
        }

        return String.valueOf(str);
    }
}
