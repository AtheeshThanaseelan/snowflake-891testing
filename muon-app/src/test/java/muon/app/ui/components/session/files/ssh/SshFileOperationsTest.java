package muon.app.ui.components.session.files.ssh;

import muon.app.common.FileInfo;
import muon.app.common.FileType;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SshFileOperationsTest {

    @Test
    public void testGetUniqueNameTestPath1() {
        FileInfo f1 = new FileInfo("file1","./file1",10, FileType.File,
                1, 0, "","",0,"",false);
        SshFileOperations sfo = new SshFileOperations();
        List<FileInfo> fList = new ArrayList<>();
        fList.add(f1);
        String uniqueName = sfo.getUniqueName(fList,"file1");

        assertEquals("Copy of file1",uniqueName);
    }

    @Test
    public void testGetUniqueNameTestPath2() {
        FileInfo f1 = new FileInfo("file1","./file1",10, FileType.File,
                1, 0, "","",0,"",false);
        SshFileOperations sfo = new SshFileOperations();
        List<FileInfo> fList = new ArrayList<>();
        fList.add(f1);
        String uniqueName = sfo.getUniqueName(fList,"file2");

        assertEquals("file2",uniqueName);
    }

    @Test
    public void testGetUniqueNameTestPath3() {
        FileInfo f1 = new FileInfo("file3.bin","./file3.bin",10, FileType.File,
                1, 0, "","",0,"",false);
        SshFileOperations sfo = new SshFileOperations();
        List<FileInfo> fList = new ArrayList<>();
        fList.add(f1);
        String uniqueName = sfo.getUniqueName(fList,"file3.txt");

        assertEquals("file3.txt",uniqueName);
    }


}