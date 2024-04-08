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

    //Mutation TESTS
    @Test
    public void testIsSingleArchiveMutation() {
        ArchiveOperation archiveOperation = new ArchiveOperation();

        // Mutant: Change the condition to always return true
        assertTrue(archiveOperation.isSingleArchive("test.tar.gz"));

        // Mutant: Change the condition to always return false
        assertFalse(archiveOperation.isSingleArchive("example.zip"));
    }
    
    @Test
    public void testGetArchiveFileNameMutation() {
        ArchiveOperation archiveOperation = new ArchiveOperation();

        // Mutant: Change the substring length for .tar.gz extension
        assertEquals("test", archiveOperation.getArchiveFileName("test.tar.gz"));

        // Mutant: Change the substring length for .zip extension
        assertEquals("exampl", archiveOperation.getArchiveFileName("example.zip"));
    }

}