package muon.app.ui.components.session.files.ssh;

import muon.app.common.FileInfo;
import muon.app.common.FileType;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class SshFileOperationsTest {

    @Test
    public void testGetUniqueName() {
        FileInfo f1 = new FileInfo("file1",FileType.File);
        SshFileOperations sfo = new SshFileOperations();
        List<FileInfo> fList = new ArrayList<>();
        fList.add(f1);
        String uniqueName = sfo.getUniqueName(fList,"file1");

        assertEquals("Copy of file1",uniqueName);
    }

    @Test
    public void testGetUniqueName_NoDuplicates() {
        List<FileInfo> fileList = new ArrayList<>();
        fileList.add(new FileInfo("file1", FileType.File));
        fileList.add(new FileInfo("file2", FileType.File));
        
        String uniqueName = new SshFileOperations().getUniqueName(fileList, "file3");
        
        assertEquals("file3", uniqueName);
    }

    @Test
    public void testGetUniqueName_WithDuplicates() {
        List<FileInfo> fileList = new ArrayList<>();
        fileList.add(new FileInfo("file1", FileType.File));
        fileList.add(new FileInfo("file2", FileType.File));
        fileList.add(new FileInfo("Copy of file3", FileType.File)); // Duplicate
        
        String uniqueName = new SshFileOperations().getUniqueName(fileList, "file3");
        
        assertEquals("Copy of file3", uniqueName);
    }

    @Test
    public void testGetUniqueName_EmptyList() {
        List<FileInfo> fileList = new ArrayList<>();
        
        String uniqueName = new SshFileOperations().getUniqueName(fileList, "file1");
        
        assertEquals("file1", uniqueName);
    }

    @Test
    public void testGetUniqueName_MaxLengthName() {
        String longName = String.join("", Collections.nCopies(250, "a")) + ".txt";
        List<FileInfo> fileList = Arrays.asList(new FileInfo(longName, FileType.File));
        
        String uniqueName = new SshFileOperations().getUniqueName(fileList, longName);
        
        assertEquals("Copy of " + longName, uniqueName);
    }

    @Test
    public void testGetUniqueName_MinLengthName() {
        List<FileInfo> fileList = Arrays.asList(new FileInfo("a", FileType.File));
        
        String uniqueName = new SshFileOperations().getUniqueName(fileList, "a");
        
        assertEquals("Copy of a", uniqueName);
    }

    @Test
    public void testGetUniqueName_SpecialCharacters() {
        List<FileInfo> fileList = Arrays.asList(new FileInfo("file&*%#", FileType.File));
        
        String uniqueName = new SshFileOperations().getUniqueName(fileList, "file&*%#");
        
        assertEquals("Copy of file&*%#", uniqueName);
    }
}