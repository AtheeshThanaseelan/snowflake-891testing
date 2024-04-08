package muon.app.common.local;

import muon.app.common.FileInfo;
import muon.app.common.FileType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;

import static org.junit.Assert.*;

public class LocalFileSystemTest {
    private LocalFileSystem lfs;

    @Before
    public void setUp() {
        lfs = new LocalFileSystem();
    }

    @After
    public void tearDown() {
        lfs.close(); // Close the LocalFileSystem instance after each test
    }

    public void testDirSetup() throws Exception {
        // Create Test Directory and File
        new File("srctestdirectory").mkdirs();
        new File("srctestdirectory/filename.txt").createNewFile();
    }

    public void cleanTestDirs() {
        String dirs[] = { "srctestdirectory" };
        for (int i = 0; i < dirs.length; i++) {
            File index = new File(dirs[i]);
            String[] entries = index.list();
            for (String s : entries) {
                File currentFile = new File(index.getPath(), s);
                currentFile.delete();
            }
            index.delete();
        }
    }

    public boolean checkFileExists(String path) {
        File f = new File(path);
        if (f.exists() && !f.isDirectory()) {
            return true;
        } else {
            return false;
        }
    }

    @Test
    public void list() throws Exception {
        testDirSetup();
        LocalFileSystem lfs = new LocalFileSystem();
        List<FileInfo> flist = lfs.list("srctestdirectory");
        assertEquals(flist.get(0).getName(), "filename.txt");
        cleanTestDirs();
    }

    @Test
    public void delete() throws Exception {
        testDirSetup();
        LocalFileSystem lfs = new LocalFileSystem();
        FileInfo fi = new FileInfo("filename.txt", "srctestdirectory/filename.txt", 0, FileType.File,
                2, 0, "", "", 1, "", false);

        lfs.delete(fi);
        assertFalse(checkFileExists("srctestdirectory/filename.txt"));
        cleanTestDirs();
    }

    @Test
    // Boundary Value Analysis (BVA) for List: Empty Path
    public void testListEmptyPath() throws Exception {
        testDirSetup();

        List<FileInfo> flist = lfs.list("");
        assertNotNull(flist);
        assertEquals(0, flist.size());

        cleanTestDirs();
    }

    @Test
    // Boundary Value Analysis (BVA) for List: Null Path
    public void testListNullPath() throws Exception {
        testDirSetup();

        assertThrows(NullPointerException.class, () -> {
            lfs.list(null);
        });

        cleanTestDirs();
    }

    @Test
    // Boundary Value Analysis (BVA) for List: Non-existent Path
    public void testListNonExistentPath() throws Exception {
        testDirSetup();

        List<FileInfo> flist = lfs.list("nonexistentdirectory");
        assertNotNull(flist);
        assertEquals(0, flist.size());

        cleanTestDirs();
    }

    @Test
    // Boundary Value Analysis (BVA) for List: Path to Specific File
    public void testListFilePath() throws Exception {
        testDirSetup();

        List<FileInfo> flist = lfs.list("srctestdirectory/filename.txt");
        assertNotNull(flist);
        assertEquals(0, flist.size());

        cleanTestDirs();
    }

    @Test
    // Boundary Value Analysis (BVA) for List: Path with Restricted Access
    public void testListRestrictedAccessPath() throws Exception {
        testDirSetup();

        // Note: You might need to adjust this path according to your system's directory
        // structure
        // Ensure the path points to a directory where the application doesn't have read
        // access
        List<FileInfo> flist = lfs.list("/restricted/directory");
        assertNotNull(flist);
        assertEquals(0, flist.size());

        cleanTestDirs();
    }

    @Test
    // Boundary Value Analysis (BVA) for Delete: Non-existent File
    public void testDeleteNonExistentFile() throws Exception {
        testDirSetup();
        FileInfo fi = new FileInfo("nonexistentfile.txt", "srctestdirectory/nonexistentfile.txt", 0, FileType.File,
                2, 0, "", "", 1, "", false);

        // Attempt to delete a non-existent file
        lfs.delete(fi);

        // Verify that the file still does not exist
        assertFalse(checkFileExists("srctestdirectory/nonexistentfile.txt"));

        cleanTestDirs();
    }

    @Test
    // Boundary Value Analysis (BVA) for Delete: Deleting a File
    public void testDeleteFile() throws Exception {
        testDirSetup();
        FileInfo fi = new FileInfo("filename.txt", "srctestdirectory/filename.txt", 0, FileType.File,
                2, 0, "", "", 1, "", false);

        // Delete an existing file
        lfs.delete(fi);

        // Verify that the file is deleted
        assertFalse(checkFileExists("srctestdirectory/filename.txt"));

        cleanTestDirs();
    }

    // Mutation TESTS
    @Test
    public void testListValidPath() {
        try {
            String path = "/path/to/valid/directory"; // Provide a valid path to a directory
            List<FileInfo> fileList = lfs.list(path);
            assertNotNull(fileList);
            assertFalse(fileList.isEmpty());
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    @Test
    public void testListEmptyPath2() {
        try {
            String path = "/path/to/empty/directory"; // Provide a valid path to an empty directory
            List<FileInfo> fileList = lfs.list(path);
            assertNotNull(fileList);
            assertTrue(fileList.isEmpty());
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    @Test
    public void testListNullPath2() {
        try {
            List<FileInfo> fileList = lfs.list(null);
            assertNotNull(fileList);
            assertFalse(fileList.isEmpty()); // Expecting default listing of user's home directory
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    @Test
    public void testListInvalidPath() {
        try {
            String path = "/path/to/nonexistent/directory"; // Provide an invalid path
            List<FileInfo> fileList = lfs.list(path);
            assertNotNull(fileList);
            assertTrue(fileList.isEmpty()); // Expecting empty list for non-existent directory
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    @Test
    public void testDelete_SkipDeletion() throws Exception {
        // Create a temporary file
        File tempFile = File.createTempFile("test", ".tmp");
        FileInfo fileInfo = new FileInfo(tempFile.getName(), tempFile.getAbsolutePath(), tempFile.length(),
                tempFile.isDirectory() ? FileType.Directory : FileType.File, tempFile.lastModified(), -1,
                LocalFileSystem.PROTO_LOCAL_FILE, "",
                Files.readAttributes(tempFile.toPath(), BasicFileAttributes.class).creationTime().toMillis(), "",
                tempFile.isHidden());

        // Call the method
        lfs.delete(fileInfo);

        // Assert that the file still exists
        assertTrue("File should still exist", tempFile.exists());
    }

    @Test
    public void testDelete_DirectoryWithContents() throws Exception {
        // Create a temporary directory with files inside
        File tempDir = Files.createTempDirectory("test").toFile();
        File tempFile1 = new File(tempDir, "file1.txt");
        File tempFile2 = new File(tempDir, "file2.txt");
        tempFile1.createNewFile();
        tempFile2.createNewFile();
        FileInfo dirInfo = new FileInfo(tempDir.getName(), tempDir.getAbsolutePath(), tempDir.length(),
                FileType.Directory, tempDir.lastModified(), -1, LocalFileSystem.PROTO_LOCAL_FILE, "",
                Files.readAttributes(tempDir.toPath(), BasicFileAttributes.class).creationTime().toMillis(), "",
                tempDir.isHidden());

        // Call the method
        lfs.delete(dirInfo);

        // Assert that the directory and its contents are deleted
        assertFalse("Directory should be deleted", tempDir.exists());
        assertFalse("File1 should be deleted", tempFile1.exists());
        assertFalse("File2 should be deleted", tempFile2.exists());
    }

    @Test
    public void testDelete_NonExistingFile() throws Exception {
        // Create a temporary file that doesn't exist
        File tempFile = new File("non_existing_file.txt");
        FileInfo fileInfo = new FileInfo(tempFile.getName(), tempFile.getAbsolutePath(), tempFile.length(),
                tempFile.isDirectory() ? FileType.Directory : FileType.File, tempFile.lastModified(), -1,
                LocalFileSystem.PROTO_LOCAL_FILE, "", 0, "", false);

        // Call the method
        lfs.delete(fileInfo);
    }
}