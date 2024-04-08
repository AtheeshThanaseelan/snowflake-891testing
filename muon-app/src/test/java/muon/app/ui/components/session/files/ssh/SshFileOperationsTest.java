package muon.app.ui.components.session.files.ssh;

import muon.app.common.FileInfo;
import muon.app.common.FileType;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SshFileOperationsTest {

    @Test
    public void testGetUniqueName() {
        FileInfo f1 = new FileInfo("file1","./file1",10, FileType.File,
                1, 0, "","",0,"",false);
        SshFileOperations sfo = new SshFileOperations();
        List<FileInfo> fList = new ArrayList<>();
        fList.add(f1);
        String uniqueName = sfo.getUniqueName(fList,"file1");

        assertEquals("Copy of file1",uniqueName);
    }

    //Mutation TESTS
    @Test
    public void testGetUniqueName_NoDuplicates() {
        // Arrange
        SshFileOperations sshFileOperations = new SshFileOperations();
        List<FileInfo> fileList = new ArrayList<>();
        FileInfo f1 = new FileInfo("file1.txt", null, 0, null, 0, 0, null, null, 0, null, false);
        FileInfo f2 = new FileInfo("file2.txt", null, 0, null, 0, 0, null, null, 0, null, false);
        fileList.add(f1);
        fileList.add(f2);
        
        // Act
        String uniqueName = sshFileOperations.getUniqueName(fileList, "file3.txt");

        // Assert
        assertEquals("file3.txt", uniqueName);
    }

    @Test
    public void testGetUniqueName_WithDuplicates() {
        // Arrange
        SshFileOperations sshFileOperations = new SshFileOperations();
        List<FileInfo> fileList = new ArrayList<>();
        FileInfo f1 = new FileInfo("file1.txt", null, 0, null, 0, 0, null, null, 0, null, false);
        FileInfo f2 = new FileInfo("file2.txt", null, 0, null, 0, 0, null, null, 0, null, false);
        FileInfo f3 = new FileInfo("Copy of file1.txt", null, 0, null, 0, 0, null, null, 0, null, false);
        fileList.add(f1);
        fileList.add(f2);
        fileList.add(f3);
        
        // Act
        String uniqueName = sshFileOperations.getUniqueName(fileList, "file1.txt");

        // Assert
        assertEquals("Copy of file1.txt", uniqueName);
    }

    @Test
    public void testGetUniqueName_Mutation_NameEquals() {
        // Arrange
        SshFileOperations sshFileOperations = new SshFileOperations();
        List<FileInfo> fileList = new ArrayList<>();
        FileInfo f1 = new FileInfo("file1.txt", null, 0, null, 0, 0, null, null, 0, null, false);
        FileInfo f2 = new FileInfo("file2.txt", null, 0, null, 0, 0, null, null, 0, null, false);
        FileInfo f3 = new FileInfo("Copy of file1.txt", null, 0, null, 0, 0, null, null, 0, null, false);
        fileList.add(f1);
        fileList.add(f2);
        fileList.add(f3);
        
        // Act
        String uniqueName = sshFileOperations.getUniqueName(fileList, "Copy of file1.txt");

        // Assert
        assertNotEquals("Copy of file1.txt", uniqueName); // Mutation: Changed 'name.equals(f.getName())' to 'true'
    }

    @Test
    public void testGetUniqueName_Mutation_BreakWhileLoop() {
        // Arrange
        SshFileOperations sshFileOperations = new SshFileOperations();
        List<FileInfo> fileList = new ArrayList<>();
        FileInfo f1 = new FileInfo("file1.txt", null, 0, null, 0, 0, null, null, 0, null, false);
        FileInfo f2 = new FileInfo("file2.txt", null, 0, null, 0, 0, null, null, 0, null, false);
        FileInfo f3 = new FileInfo("Copy of file1.txt", null, 0, null, 0, 0, null, null, 0, null, false);
        fileList.add(f1);
        fileList.add(f2);
        fileList.add(f3);
        
        // Act
        String uniqueName = sshFileOperations.getUniqueName(fileList, "file1.txt");

        // Assert
        assertEquals("Copy of file1.txt", uniqueName); // Mutation: Changed 'while (true)' to 'while (false)'
    }

}