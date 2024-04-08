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
    
    // ISP Test: Test for non-archive file
    @Test
    public void testIsSingleArchive_NonArchiveFile() {
        ArchiveOperation archiveOperation = new ArchiveOperation();
        assertFalse(archiveOperation.isSingleArchive("example.txt"));
    }

    // ISP Test: Test for single archive file
    @Test
    public void testIsSingleArchive_SingleArchiveFile() {
        ArchiveOperation archiveOperation = new ArchiveOperation();
        assertTrue(archiveOperation.isSingleArchive("example.tar.gz"));
    }

    // ISP Test: Test for single archive file with tar extension
    @Test
    public void testIsSingleArchive_SingleArchiveFileWithTarExtension() {
        ArchiveOperation archiveOperation = new ArchiveOperation();
        assertFalse(archiveOperation.isSingleArchive("example.tar.gz"));
    }

    // BVA Test: Test for empty path
    @Test
    public void testIsSingleArchive_EmptyPath_BVA() {
        ArchiveOperation archiveOperation = new ArchiveOperation();
        assertFalse(archiveOperation.isSingleArchive(""));
    }

    // BVA Test: Test for maximum length path
    @Test
    public void testIsSingleArchive_MaxLengthPath_BVA() {
        ArchiveOperation archiveOperation = new ArchiveOperation();
        String maxLengthPath = "a".repeat(255) + ".tar.gz"; // Create a path of maximum length
        assertTrue(archiveOperation.isSingleArchive(maxLengthPath));
    }

    // BVA Test: Test for boundary archive extensions
    @Test
    public void testIsSingleArchive_BoundaryArchiveExtensions_BVA() {
        ArchiveOperation archiveOperation = new ArchiveOperation();
        // Test for boundary archive extensions
        assertTrue(archiveOperation.isSingleArchive("example.gz"));
        assertTrue(archiveOperation.isSingleArchive("example.xz"));
        assertTrue(archiveOperation.isSingleArchive("example.bz2"));
    }

        // ISP Test: Test for .gz extension
        @Test
        public void testGetArchiveFileName_GzExtension() {
            ArchiveOperation archiveOperation = new ArchiveOperation();
            String fileName = archiveOperation.getArchiveFileName("example.tar.gz");
            assertEquals("example.tar", fileName);
        }
    
        // ISP Test: Test for .xz extension
        @Test
        public void testGetArchiveFileName_XzExtension() {
            ArchiveOperation archiveOperation = new ArchiveOperation();
            String fileName = archiveOperation.getArchiveFileName("example.tar.xz");
            assertEquals("example.tar", fileName);
        }
    
        // ISP Test: Test for .bz2 extension
        @Test
        public void testGetArchiveFileName_Bz2Extension() {
            ArchiveOperation archiveOperation = new ArchiveOperation();
            String fileName = archiveOperation.getArchiveFileName("example.tar.bz2");
            assertEquals("example.tar", fileName);
        }
    
        // BVA Test: Test for empty path
        @Test
        public void testGetArchiveFileName_EmptyPath_BVA() {
            ArchiveOperation archiveOperation = new ArchiveOperation();
            String fileName = archiveOperation.getArchiveFileName("");
            assertEquals("", fileName);
        }
    
        // BVA Test: Test for maximum length path
        @Test
        public void testGetArchiveFileName_MaxLengthPath_BVA() {
            ArchiveOperation archiveOperation = new ArchiveOperation();
            String maxLengthPath = "a".repeat(255) + ".tar.gz"; // Create a path of maximum length
            String fileName = archiveOperation.getArchiveFileName(maxLengthPath);
            assertEquals("a".repeat(254) + ".tar", fileName);
        }
    
        // BVA Test: Test for boundary archive extensions
        @Test
        public void testGetArchiveFileName_BoundaryArchiveExtensions_BVA() {
            ArchiveOperation archiveOperation = new ArchiveOperation();
            // Test for boundary archive extensions
            String fileName1 = archiveOperation.getArchiveFileName("example.gz");
            assertEquals("example", fileName1);
            String fileName2 = archiveOperation.getArchiveFileName("example.xz");
            assertEquals("example", fileName2);
            String fileName3 = archiveOperation.getArchiveFileName("example.bz2");
            assertEquals("example", fileName3);
        }
}
