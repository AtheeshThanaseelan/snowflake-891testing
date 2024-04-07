package muon.app;

import muon.app.ui.components.session.SessionFolder;
import muon.app.ui.components.session.SessionInfo;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class PasswordStoreTest {

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

}