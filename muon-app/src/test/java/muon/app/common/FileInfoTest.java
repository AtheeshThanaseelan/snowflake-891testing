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

    // ISP test: Valid input with user in extra info
    @Test
    public void testGetUserName_ValidExtraInfoWithUser() {
        FileInfo fileInfo = new FileInfo("file.txt", "./file.txt", 100, FileType.File,
                1234567890, 755, "protocol", "rwxr-xr-x", 1234567890, "user123 user456", false);

        assertEquals("user123", fileInfo.getUserName());
    }

    // ISP test: Valid input without user in extra info
    @Test
    public void testGetUserName_ValidExtraInfoWithoutUser() {
        FileInfo fileInfo = new FileInfo("file.txt", "./file.txt", 100, FileType.File,
                1234567890, 755, "protocol", "rwxr-xr-x", 1234567890, "", false);

        assertEquals("", fileInfo.getUserName());
    }

    // ISP test: Invalid input in extra info
    @Test
    public void testGetUserName_InvalidExtraInfo() {
        FileInfo fileInfo = new FileInfo("file.txt", "./file.txt", 100, FileType.File,
                1234567890, 755, "protocol", "rwxr-xr-x", 1234567890, "invalid", false);

        assertEquals("", fileInfo.getUserName());
    }

    // ISP test: Empty extra info
    @Test
    public void testGetUserName_EmptyExtraInfo() {
        FileInfo fileInfo = new FileInfo("file.txt", "./file.txt", 100, FileType.File,
                1234567890, 755, "protocol", "rwxr-xr-x", 1234567890, "", false);

        assertEquals("", fileInfo.getUserName());
    }

    // BVA test: Max length of extra info
    @Test
    public void testGetUserName_MaxLengthExtraInfo() {
        String maxExtraInfo = String.format("%-300s", "user123").replace(' ', 'x');
        FileInfo fileInfo = new FileInfo("file.txt", "./file.txt", 100, FileType.File,
                1234567890, 755, "protocol", "rwxr-xr-x", 1234567890, maxExtraInfo, false);

        assertEquals("user123", fileInfo.getUserName());
    }

    // BVA test: Min length of extra info
    @Test
    public void testGetUserName_MinLengthExtraInfo() {
        FileInfo fileInfo = new FileInfo("file.txt", "./file.txt", 100, FileType.File,
                1234567890, 755, "protocol", "rwxr-xr-x", 1234567890, "u", false);

        assertEquals("", fileInfo.getUserName());
    }

    // BVA test: Max length of user name
    @Test
    public void testGetUserName_MaxLengthUserName() {
        FileInfo fileInfo = new FileInfo("file.txt", "./file.txt", 100, FileType.File,
                1234567890, 755, "protocol", "rwxr-xr-x", 1234567890, "user123456789012345678901234567890123456789012345678901234567890", false);

        assertEquals("user123456789012345678901234567890123456789012345678901234567890", fileInfo.getUserName());
    }

    // BVA test: Min length of user name
    @Test
    public void testGetUserName_MinLengthUserName() {
        FileInfo fileInfo = new FileInfo("file.txt", "./file.txt", 100, FileType.File,
                1234567890, 755, "protocol", "rwxr-xr-x", 1234567890, "u", false);

        assertEquals("", fileInfo.getUserName());
    }

    // ISP test: Special characters in user name
    @Test
    public void testGetUserName_SpecialCharacters() {
        FileInfo fileInfo = new FileInfo("file.txt", "./file.txt", 100, FileType.File,
                1234567890, 755, "protocol", "rwxr-xr-x", 1234567890, "user&*%#", false);

        assertEquals("user&*%#", fileInfo.getUserName());
    }


    // ISP test: Comparing two directories
    @Test
    public void testCompareTo_TwoDirectories() {
        FileInfo dir1 = new FileInfo("dir1", "./dir1", 0, FileType.Directory,
                0, 755, "protocol", "rwxr-xr-x", 0, "", false);
        FileInfo dir2 = new FileInfo("dir2", "./dir2", 0, FileType.Directory,
                0, 755, "protocol", "rwxr-xr-x", 0, "", false);

        assertTrue(dir1.compareTo(dir2) < 0);
    }

    // ISP test: Comparing two files
    @Test
    public void testCompareTo_TwoFiles() {
        FileInfo file1 = new FileInfo("file1.txt", "./file1.txt", 100, FileType.File,
                1234567890, 644, "protocol", "rw-r--r--", 1234567890, "", false);
        FileInfo file2 = new FileInfo("file2.txt", "./file2.txt", 100, FileType.File,
                1234567890, 644, "protocol", "rw-r--r--", 1234567890, "", false);

        assertTrue(file1.compareTo(file2) < 0);
    }

    // ISP test: Comparing a directory with a file
    @Test
    public void testCompareTo_DirectoryVsFile() {
        FileInfo dir = new FileInfo("dir", "./dir", 0, FileType.Directory,
                0, 755, "protocol", "rwxr-xr-x", 0, "", false);
        FileInfo file = new FileInfo("file.txt", "./file.txt", 100, FileType.File,
                1234567890, 644, "protocol", "rw-r--r--", 1234567890, "", false);

        assertTrue(dir.compareTo(file) < 0);
    }

    // ISP test: Comparing a file with a directory
    @Test
    public void testCompareTo_FileVsDirectory() {
        FileInfo file = new FileInfo("file.txt", "./file.txt", 100, FileType.File,
                1234567890, 644, "protocol", "rw-r--r--", 1234567890, "", false);
        FileInfo dir = new FileInfo("dir", "./dir", 0, FileType.Directory,
                0, 755, "protocol", "rwxr-xr-x", 0, "", false);

        assertTrue(file.compareTo(dir) > 0);
    }

    // BVA test: Null comparison
    @Test
    public void testCompareTo_NullComparison() {
        FileInfo fileInfo = new FileInfo("file.txt", "./file.txt", 100, FileType.File,
                1234567890, 644, "protocol", "rw-r--r--", 1234567890, "", false);

        assertEquals(1, fileInfo.compareTo(null));
    }

    // ISP test: Same file names
    @Test
    public void testCompareTo_SameNames() {
        FileInfo file1 = new FileInfo("file.txt", "./file.txt", 100, FileType.File,
                1234567890, 644, "protocol", "rw-r--r--", 1234567890, "", false);
        FileInfo file2 = new FileInfo("file.txt", "./file.txt", 100, FileType.File,
                1234567890, 644, "protocol", "rw-r--r--", 1234567890, "", false);

        assertEquals(0, file1.compareTo(file2));
    }

    // ISP test: Different file names
    @Test
    public void testCompareTo_DifferentNames() {
        FileInfo file1 = new FileInfo("file1.txt", "./file1.txt", 100, FileType.File,
                1234567890, 644, "protocol", "rw-r--r--", 1234567890, "", false);
        FileInfo file2 = new FileInfo("file2.txt", "./file2.txt", 100, FileType.File,
                1234567890, 644, "protocol", "rw-r--r--", 1234567890, "", false);

        assertTrue(file1.compareTo(file2) < 0);
    }

}