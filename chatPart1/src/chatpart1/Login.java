package chatpart1;

import java.util.regex.Pattern;

/**
 * The Login class handles user registration and login validation.
 * Contains exactly the six methods required by the POE brief.
 */
public class Login {

    // Stores the registered user's details
    private String storedFirstName;
    private String storedLastName;
    private String storedUsername;
    private String storedPassword;
    private String storedCellPhone;

    // ---------- 1. checkUserName ----------
    public boolean checkUserName(String username) {
        if (username == null) return false;
        return username.contains("_") && username.length() <= 5;
    }

    // ---------- 2. checkPasswordComplexity ----------
    public boolean checkPasswordComplexity(String password) {
        if (password == null || password.length() < 8) return false;

        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasCapital = true;
            else if (Character.isDigit(c)) hasNumber = true;
            else if (!Character.isLetterOrDigit(c)) hasSpecial = true;
        }

        return hasCapital && hasNumber && hasSpecial;
    }

    // ---------- 3. checkCellPhoneNumber (regex, source credited) ----------
    // Regex source: standard SA mobile pattern – adapted from common examples
    public boolean checkCellPhoneNumber(String cellPhone) {
        if (cellPhone == null) return false;
        String pattern = "^\\+27[0-9]{9}$";
        return Pattern.matches(pattern, cellPhone);
    }

    // ---------- 4. registerUser ----------
    public String registerUser(String firstName, String lastName,
                               String username, String password, String cellPhone) {

        if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        if (!checkCellPhoneNumber(cellPhone)) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }

        // All valid – store details
        this.storedFirstName = firstName;
        this.storedLastName = lastName;
        this.storedUsername = username;
        this.storedPassword = password;
        this.storedCellPhone = cellPhone;

        return "User successfully registered";
    }

    // ---------- 5. loginUser ----------
    public boolean loginUser(String username, String password) {
        if (storedUsername == null) return false;
        return storedUsername.equals(username) && storedPassword.equals(password);
    }

    // ---------- 6. returnLoginStatus ----------
    public String returnLoginStatus(String username, String password) {
        if (loginUser(username, password)) {
            return "Welcome " + storedFirstName + " " + storedLastName + ", it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }

    // Optional getters (used by ChatPart1)
    public String getStoredFirstName() { return storedFirstName; }
    public String getStoredLastName() { return storedLastName; }
}