package muon.app;

import muon.app.ui.components.session.SessionFolder;
import muon.app.ui.components.session.SessionInfo;
import muon.app.ui.components.session.files.transfer.FileTransferProgress;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import muon.app.App;

public class PasswordStoreTest {


    public void deletePasswordsPfx(){
        File filePasswordStore = new File(App.CONFIG_DIR, "passwords.pfx");
        filePasswordStore.delete();
    }

    @Test
    public void testChangeStorePassword1() throws Exception{
        deletePasswordsPfx();
        PasswordStore ps = PasswordStore.getSharedInstance();

        ps.unlockStore("samePassword".toCharArray());
        ps.savePassword("bill","abc".toCharArray());
        char gotPs1[] = ps.getSavedPassword("bill");
        // Same password for change store password
        boolean cspResult = ps.changeStorePassword("samePassword".toCharArray());
        char gotPs2[] = ps.getSavedPassword("bill");

        assertEquals(gotPs2,gotPs1);
        assertTrue(cspResult);

        deletePasswordsPfx();

    }


    @Test
    public void testChangeStorePassword2() throws Exception{
        deletePasswordsPfx();
        PasswordStore ps = PasswordStore.getSharedInstance();

        ps.unlockStore("changeit".toCharArray());
        ps.savePassword("bill","abc".toCharArray());
        char gotPs1[] = ps.getSavedPassword("bill");
        boolean cspResult = ps.changeStorePassword("changed".toCharArray());
        char gotPs2[] = ps.getSavedPassword("bill");

        assertEquals(gotPs2,gotPs1);
        assertTrue(cspResult);

        deletePasswordsPfx();

    }

    @Test
    public void savePassword1() throws Exception{

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
    @Test
    public void savePassword2() throws Exception{

        SessionFolder sf = new SessionFolder();
        SessionInfo si1 = new SessionInfo();
        si1.setPassword("hi");
        si1.setId("test1");

        SessionFolder sfInternal = new SessionFolder();
        SessionInfo siInternal = new SessionInfo();
        siInternal.setPassword("hiInternal");
        siInternal.setId("testInternal");
        sfInternal.setItems(Arrays.asList(siInternal));
        sf.setFolders(Arrays.asList(sfInternal));
        sf.setItems(Arrays.asList(si1));

        deletePasswordsPfx();
        PasswordStore ps = PasswordStore.getSharedInstance();
        ps.unlockStore("changeit".toCharArray());
        ps.savePassword(sf);

        String gotPass = new String(ps.getSavedPassword(si1.getId()));
        assertEquals("hi",gotPass);

        String gotPassInternal = new String(ps.getSavedPassword(siInternal.getId()));
        assertEquals("hiInternal",gotPassInternal);



        deletePasswordsPfx();
    }
}