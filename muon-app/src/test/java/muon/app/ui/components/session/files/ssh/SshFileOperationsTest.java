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

}