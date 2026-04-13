package chatpart1;

import java.util.Scanner;

public class ChatPart1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Login login = new Login();  // Create the login/registration object

        System.out.println("Welcome to my program");

        // Decorative banner (your existing design)
        System.out.println("  *****    *     *   *****   ***** ");
        System.out.println(" *     *   *     *  *     *    *   ");
        System.out.println(" *         *     *  *     *    *   ");
        System.out.println(" *         *******  *******    *   ");
        System.out.println(" *         *     *  *     *    *   ");
        System.out.println(" *     *   *     *  *     *    *   ");
        System.out.println("  *****    *     *  *     *    *   ");
        System.out.println();
        System.out.println("   *     ****    **** ");
        System.out.println("  * *    *   *   *   *");
        System.out.println(" *****   ****    **** ");
        System.out.println("*     *  *       *    ");
        System.out.println("*     *  *       *    ");

        System.out.println("\nPlease follow the instructions to register");
        System.out.println("Username must contain an underscore[_] and be at most five characters");
        System.out.println("Password must be at least 8 characters, 1 capital letter, 1 number and 1 special character");
        System.out.println("Cellphone Number must start with +27 and be followed by nine digits");

        // Get user details
        System.out.print("\nEnter your first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter your surname: ");
        String lastName = scanner.nextLine();

        String username;
        String password;
        String cellPhone;

        // Username validation loop
        while (true) {
            System.out.print("Enter Username: ");
            username = scanner.nextLine();
            if (login.checkUserName(username)) {
                System.out.println("Username Successfully Captured");
                break;
            } else {
                System.out.println("Username not entered correctly; username must be under five characters and must have an underscore [ _ ]");
            }
        }

        // Password validation loop
        while (true) {
            System.out.print("Enter Password: ");
            password = scanner.nextLine();
            if (login.checkPasswordComplexity(password)) {
                System.out.println("Password Successfully Captured");
                break;
            } else {
                System.out.println("Password not entered correctly; password must be at least 8 characters, contain 1 capital letter, 1 number, and 1 special character");
            }
        }

        // Cellphone validation loop
        while (true) {
            System.out.print("Enter Cellphone Number [+27] : ");
            cellPhone = scanner.nextLine();
            if (login.checkCellPhoneNumber(cellPhone)) {
                System.out.println("Cellphone Number Successfully Captured");
                break;
            } else {
                System.out.println("Cellphone Number not entered correctly; cellphone number must start with +27 and be followed by 9 digits");
            }
        }

        // Register the user
        String regResult = login.registerUser(firstName, lastName, username, password, cellPhone);
        System.out.println("\n" + regResult);

        if (!regResult.equals("User successfully registered")) {
            System.out.println("Registration failed. Exiting program.");
            scanner.close();
            return;
        }

        System.out.println("\nRegistration completed successfully!\n");

        // ---------- LOGIN PHASE ----------
        System.out.println("++++++++++++++Login++++++++++++++");

        int attempts = 0;
        boolean loggedIn = false;

        while (attempts < 3 && !loggedIn) {
            System.out.print("Enter Username: ");
            String loginUsername = scanner.nextLine();

            System.out.print("Enter Password: ");
            String loginPassword = scanner.nextLine();

            String status = login.returnLoginStatus(loginUsername, loginPassword);
            System.out.println(status);

            if (status.contains("Welcome")) {
                loggedIn = true;
            } else {
                attempts++;
                if (attempts < 3) {
                    System.out.println("Please try again. (" + (3 - attempts) + " attempts remaining)\n");
                }
            }
        }

        if (loggedIn) {
            System.out.println("\nYou have successfully logged in!");
        } else {
            System.out.println("\nToo many failed attempts. Please contact support");
        }

        scanner.close();
    }
}