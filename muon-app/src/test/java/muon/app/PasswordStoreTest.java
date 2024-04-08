package muon.app;

import muon.app.ui.components.session.SessionFolder;
import muon.app.ui.components.session.SessionInfo;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;


public class PasswordStoreTest {


    private static PasswordStore passwordStore;

    public void deletePasswordsPfx(){
        File filePasswordStore = new File(App.CONFIG_DIR, "passwords.pfx");
        filePasswordStore.delete();
    }

    @Test
    public void changeStorePassword() throws Exception{
        deletePasswordsPfx();
        PasswordStore ps = PasswordStore.getSharedInstance();

        ps.unlockStore("changeit".toCharArray());
        ps.savePassword("bill","abc".toCharArray());
        char gotPs1[] = ps.getSavedPassword("bill");
        ps.changeStorePassword("changed".toCharArray());
        char gotPs2[] = ps.getSavedPassword("bill");

        assertEquals(gotPs2,gotPs1);


        deletePasswordsPfx();

    }

    @Test
    public void savePassword() throws Exception{

        SessionFolder sf = new SessionFolder();
        SessionInfo si1 = new SessionInfo();
        si1.setPassword("hi");
        si1.setId("test1");
        sf.setItems(Arrays.asList(si1));

        deletePasswordsPfx();
        PasswordStore ps = PasswordStore.getSharedInstance();
        ps.unlockStore("changeit".toCharArray());
        ps.savePassword(sf);

        String gotPass = new String(ps.getSavedPassword(si1.getId()));
        assertEquals("hi",gotPass);


        deletePasswordsPfx();
    }

    @BeforeClass
    public static void setUp() throws Exception {
        passwordStore = PasswordStore.getSharedInstance();
        passwordStore.unlockStore("test".toCharArray()); // Unlock the store before testing
    }

    // Boundary Value Analysis (BVA) Tests for changeStorePassword

    // Test for minimum length password
    @Test
    public void testChangeStorePassword_MinLength() {
        try {
            assertTrue("Password change successful", passwordStore.changeStorePassword("".toCharArray()));
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    // Test for maximum length password
    @Test
    public void testChangeStorePassword_MaxLength() {
        try {
            StringBuilder maxPassword = new StringBuilder();
            for (int i = 0; i < 256; i++) {
                maxPassword.append("a"); // Create a password of maximum length (256 characters)
            }
            assertTrue("Password change successful", passwordStore.changeStorePassword(maxPassword.toString().toCharArray()));
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    // Input Space Partitioning (ISP) Tests for changeStorePassword

    // Test changing to null password
    @Test(expected = NullPointerException.class)
    public void testChangeStorePassword_NullPassword() throws Exception {
        passwordStore.changeStorePassword(null);
    }

    // Test changing to empty password
    @Test
    public void testChangeStorePassword_EmptyPassword() {
        try {
            assertTrue("Password change successful", passwordStore.changeStorePassword("".toCharArray()));
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    // Test changing to a valid password
    @Test
    public void testChangeStorePassword_ValidPassword() {
        try {
            assertTrue("Password change successful", passwordStore.changeStorePassword("newPassword".toCharArray()));
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    // Boundary Value Analysis (BVA) Tests for savePassword

    // Test for minimum length password
    @Test
    public void testSavePassword_MinLength() {
        try {
            passwordStore.savePassword("testAlias", "".toCharArray());
            char[] savedPassword = passwordStore.getSavedPassword("testAlias");
            assertNotNull("Password should not be null", savedPassword);
            assertEquals("Password length should be 0", 0, savedPassword.length);
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    // Test for maximum length password
    @Test
    public void testSavePassword_MaxLength() {
        try {
            StringBuilder maxPassword = new StringBuilder();
            for (int i = 0; i < 256; i++) {
                maxPassword.append("a"); // Create a password of maximum length (256 characters)
            }
            passwordStore.savePassword("testAlias", maxPassword.toString().toCharArray());
            char[] savedPassword = passwordStore.getSavedPassword("testAlias");
            assertNotNull("Password should not be null", savedPassword);
            assertEquals("Password length should be 256", 256, savedPassword.length);
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    // Input Space Partitioning (ISP) Tests for savePassword

    // Test saving a null password
    @Test(expected = NullPointerException.class)
    public void testSavePassword_NullPassword() throws Exception {
        passwordStore.savePassword("testAlias", null);
    }

    // Test saving an empty password
    @Test
    public void testSavePassword_EmptyPassword() {
        try {
            passwordStore.savePassword("testAlias", "".toCharArray());
            char[] savedPassword = passwordStore.getSavedPassword("testAlias");
            assertNotNull("Password should not be null", savedPassword);
            assertEquals("Password length should be 0", 0, savedPassword.length);
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    // Test saving a valid password
    @Test
    public void testSavePassword_ValidPassword() {
        try {
            passwordStore.savePassword("testAlias", "myPassword".toCharArray());
            char[] savedPassword = passwordStore.getSavedPassword("testAlias");
            assertNotNull("Password should not be null", savedPassword);
            assertEquals("Saved password should match the original", "myPassword", new String(savedPassword));
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    //Mutation TESTS
    @Test
    public void testChangeStorePassword_SuccessfulChange() throws Exception {
        // Test changing the store password successfully
        PasswordStore passwordStore = PasswordStore.getSharedInstance();
        assertTrue(passwordStore.changeStorePassword("newPassword".toCharArray()));
    }

    @Test
    public void testChangeStorePassword_UnsuccessfulChange() throws Exception {
        // Test changing the store password unsuccessfully
        PasswordStore passwordStore = PasswordStore.getSharedInstance();
        assertFalse(passwordStore.changeStorePassword(null)); // Mutation: Passing null as newPassword
    }

    @Test
    public void testChangeStorePassword_NoStoredPasswords() throws Exception {
        // Test changing store password when there are no stored passwords
        PasswordStore passwordStore = PasswordStore.getSharedInstance();
        assertTrue(passwordStore.changeStorePassword("newPassword".toCharArray())); // No mutation
    }

    @Test
    public void testSavePassword_SuccessfulSave() throws Exception {
        // Test saving password successfully
        PasswordStore passwordStore = PasswordStore.getSharedInstance();
        SessionFolder folder = new SessionFolder();
        // SessionInfo session1 = new SessionInfo("alias1", "password1");
        // SessionInfo session2 = new SessionInfo("alias2", "password2");
        // folder.addItem(session1);
        // folder.addItem(session2);
        SessionInfo session1 = new SessionInfo();
        session1.setId("alias1");
        session1.setPassword("password1");
        SessionInfo session2 = new SessionInfo();
        session2.setId("alias2");
        session2.setPassword("password2");
        folder.getItems().add(session1);
        folder.getItems().add(session2);
        passwordStore.savePassword(folder);
        assertNotNull(passwordStore.getSavedPassword("alias1"));
        assertNotNull(passwordStore.getSavedPassword("alias2"));
    }

    @Test
    public void testSavePassword_EmptyFolder() throws Exception {
        // Test saving password when the folder is empty
        PasswordStore passwordStore = PasswordStore.getSharedInstance();
        SessionFolder folder = new SessionFolder();
        passwordStore.savePassword(folder); // No mutation
    }

    @Test
    public void testSavePassword_NullFolder() throws Exception {
        // Test saving password with null folder
        PasswordStore passwordStore = PasswordStore.getSharedInstance();
        passwordStore.savePassword(null); // Mutation: Passing null as folder
    }
}
