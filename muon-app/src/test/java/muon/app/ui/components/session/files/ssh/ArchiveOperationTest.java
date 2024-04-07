package muon.app.ui.components.session.files.ssh;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArchiveOperationTest {

    @Test
    public void testIsSingleArchive(){
        ArchiveOperation ao = new ArchiveOperation();
        String filename = "test.tar.gz";
        boolean result = ao.isSingleArchive(filename);
        assertEquals(result,false);
    }

    @Test
    public void getArchiveFileName(){
        ArchiveOperation ao = new ArchiveOperation();
        String filename = "test.tar.gz";
        String result = ao.getArchiveFileName(filename);
        assertEquals("test.tar",result);
    }

}