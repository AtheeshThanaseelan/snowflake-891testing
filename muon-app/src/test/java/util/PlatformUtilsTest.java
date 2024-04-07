package util;

import muon.app.ui.components.settings.EditorEntry;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class PlatformUtilsTest {

    @Test
    public void testOpenFolder2() {
        PlatformUtils.openFolder2("/bin");
    }

    @Test
    public void testGetKnownEditors() {
        List<EditorEntry> list= PlatformUtils.getKnownEditors();
        for(int i=0; i<list.size();i++){
            EditorEntry ee = list.get(i);
            System.out.println(ee.getName());
        }
    }
}