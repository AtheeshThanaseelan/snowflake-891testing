package muon.app.common;

import org.junit.Test;

import static org.junit.Assert.*;

public class FileInfoTest {

    @Test
    public void testCompareTo() {
        FileInfo f1 = new FileInfo("file1","./file1",10,FileType.File,
                1, 0, "","",0,"",false);

        FileInfo f2 = new FileInfo("file1","./file1",10,FileType.File,
                1, 0, "","",0,"",false);

        int res = f1.compareTo(f2);

        assertEquals(0,res);

    }

    @Test
    public void testGetUsername() {
        FileInfo f1 = new FileInfo("file1","./file1",10,FileType.File,
                1, 0, "","",0,"",false);

        String res = f1.getUserName();

        assertEquals("",res);

    }

    //Mutation TESTS
    @Test
    public void testGetUserName() {
        // Create FileInfo instance with non-empty extra field
        FileInfo fileInfo = new FileInfo("test.txt", "/path/to/file", 100,
                FileType.File, System.currentTimeMillis(), 755, "http",
                "rwxr-xr-x", System.currentTimeMillis(), "user group",
                false);

        // Test case where matcher finds a user
        assertEquals("user", fileInfo.getUserName());

        // Test case where matcher does not find a user
        fileInfo.setExtra("invalid extra string");
        assertEquals("", fileInfo.getUserName());

        // Additional test cases for mutation testing

        // Mutation: Change return value to null
        fileInfo.setExtra(null);
        assertEquals("", fileInfo.getUserName());

        // Mutation: Change return value to a constant string
        fileInfo.setExtra("constant string");
        assertEquals("", fileInfo.getUserName());

        // Mutation: Change return value to a different user
        fileInfo.setExtra("different user group");
        assertNotEquals("user", fileInfo.getUserName());
    }

    @Test
    public void testCompareTo2() {
        // Create FileInfo instances for comparison
        FileInfo fileInfo1 = new FileInfo("test1.txt", "/path/to/file1", 100,
                FileType.File, System.currentTimeMillis(), 755, "http",
                "rwxr-xr-x", System.currentTimeMillis(), "user group",
                false);

        FileInfo fileInfo2 = new FileInfo("test2.txt", "/path/to/file2", 200,
                FileType.File, System.currentTimeMillis(), 755, "http",
                "rwxr-xr-x", System.currentTimeMillis(), "user group",
                false);

        // Test cases for comparison
        assertEquals(0, fileInfo1.compareTo(fileInfo1)); // Same file info should return 0
        assertEquals(-1, fileInfo1.compareTo(fileInfo2)); // fileInfo1 should come before fileInfo2
        assertEquals(1, fileInfo2.compareTo(fileInfo1)); // fileInfo2 should come after fileInfo1

        // Additional test cases for mutation testing

        // Mutation: Swap comparison order
        assertEquals(0, fileInfo2.compareTo(fileInfo2)); // Same file info should return 0
        assertEquals(1, fileInfo1.compareTo(fileInfo2)); // fileInfo1 should come after fileInfo2
        assertEquals(-1, fileInfo2.compareTo(fileInfo1)); // fileInfo2 should come before fileInfo1

        // Mutation: Change comparison condition
        fileInfo1.setType(FileType.Directory);
        fileInfo2.setType(FileType.Directory);
        assertEquals(0, fileInfo1.compareTo(fileInfo2)); // Same file info should return 0 since both are directories
        assertEquals(0, fileInfo2.compareTo(fileInfo1)); // Same file info should return 0 since both are directories
    }

}