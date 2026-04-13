package chatpart1;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LoginTest {

    private Login login;

    @Before
    public void setUp() {
        login = new Login();
    }

    // ----- checkUserName tests -----
    @Test
    public void testCheckUserName_Valid() {
        assertTrue(login.checkUserName("j_doe"));   // has underscore, length 5
        assertTrue(login.checkUserName("a_b"));     // length 3
    }

    @Test
    public void testCheckUserName_Invalid() {
        assertFalse(login.checkUserName("jdoe"));      // no underscore
        assertFalse(login.checkUserName("janed_"));    // too long (6 chars)
        assertFalse(login.checkUserName(null));
    }

    // ----- checkPasswordComplexity tests -----
    @Test
    public void testCheckPasswordComplexity_Valid() {
        assertTrue(login.checkPasswordComplexity("JaneDoe1!"));
        assertTrue(login.checkPasswordComplexity("Pass123$"));
    }

    @Test
    public void testCheckPasswordComplexity_Invalid() {
        assertFalse(login.checkPasswordComplexity("Jd1!"));      // too short
        assertFalse(login.checkPasswordComplexity("janedoe1!")); // no capital
        assertFalse(login.checkPasswordComplexity("JaneDoe!!")); // no number
        assertFalse(login.checkPasswordComplexity("JaneDoe12")); // no special
        assertFalse(login.checkPasswordComplexity(null));
    }

    // ----- checkCellPhoneNumber tests -----
    @Test
    public void testCheckCellPhoneNumber_Valid() {
        assertTrue(login.checkCellPhoneNumber("+27831234567"));
        assertTrue(login.checkCellPhoneNumber("+27123456789"));
    }

    @Test
    public void testCheckCellPhoneNumber_Invalid() {
        assertFalse(login.checkCellPhoneNumber("0831234567"));   // no +27
        assertFalse(login.checkCellPhoneNumber("+278312345678")); // too many digits
        assertFalse(login.checkCellPhoneNumber("+278312345"));    // too few
        assertFalse(login.checkCellPhoneNumber(null));
    }

    // ----- registerUser tests (full flow with Jane Doe) -----
    @Test
    public void testRegisterUser_Success() {
        String result = login.registerUser("Jane", "Doe", "j_doe", "JaneDoe1!", "+27831234567");
        assertEquals("User successfully registered", result);

        // Check that login works after registration
        assertTrue(login.loginUser("j_doe", "JaneDoe1!"));
        assertEquals("Welcome Jane Doe, it is great to see you again.",
                login.returnLoginStatus("j_doe", "JaneDoe1!"));
    }

    @Test
    public void testRegisterUser_InvalidUsername() {
        String result = login.registerUser("Jane", "Doe", "janeDoe", "JaneDoe1!", "+27831234567");
        assertEquals("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.",
                result);
    }

    @Test
    public void testRegisterUser_InvalidPassword() {
        String result = login.registerUser("Jane", "Doe", "j_doe", "weak", "+27831234567");
        assertEquals("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.",
                result);
    }

    @Test
    public void testRegisterUser_InvalidCellPhone() {
        String result = login.registerUser("Jane", "Doe", "j_doe", "JaneDoe1!", "0831234567");
        assertEquals("Cell phone number incorrectly formatted or does not contain international code.",
                result);
    }

    // ----- loginUser tests (after successful registration) -----
    @Test
    public void testLoginUser_CorrectCredentials() {
        login.registerUser("Jane", "Doe", "j_doe", "JaneDoe1!", "+27831234567");
        assertTrue(login.loginUser("j_doe", "JaneDoe1!"));
    }

    @Test
    public void testLoginUser_WrongCredentials() {
        login.registerUser("Jane", "Doe", "j_doe", "JaneDoe1!", "+27831234567");
        assertFalse(login.loginUser("j_doe", "wrongPass"));
        assertFalse(login.loginUser("wrongUser", "JaneDoe1!"));
    }

    // ----- returnLoginStatus tests -----
    @Test
    public void testReturnLoginStatus_Success() {
        login.registerUser("Jane", "Doe", "j_doe", "JaneDoe1!", "+27831234567");
        String msg = login.returnLoginStatus("j_doe", "JaneDoe1!");
        assertEquals("Welcome Jane Doe, it is great to see you again.", msg);
    }

    @Test
    public void testReturnLoginStatus_Failure() {
        login.registerUser("Jane", "Doe", "j_doe", "JaneDoe1!", "+27831234567");
        String msg = login.returnLoginStatus("j_doe", "wrong");
        assertEquals("Username or password incorrect, please try again.", msg);
    }
}