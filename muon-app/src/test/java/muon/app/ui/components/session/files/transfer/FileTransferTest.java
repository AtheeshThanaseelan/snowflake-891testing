package muon.app.ui.components.session.files.transfer;

import muon.app.common.FileInfo;
import muon.app.common.FileSystem;
import muon.app.common.FileType;
import muon.app.common.local.LocalFileSystem;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class FileTransferTest {

    @Mock
    private FileTransferProgress callback;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    // Testing: copy a temporary file into a temporary directory, make sure it copied correctly

    @Test
    public  void testFileOps() throws  Exception{

    }

    @Test
    public void testTransfer() throws  Exception {
        FileSystem sourceFs = new LocalFileSystem();
        FileSystem targetFs = new LocalFileSystem();
        FileInfo fileInfo = new FileInfo("test.txt", "/home/person/test.txt", 3, FileType.File, 2, 0, "","", 1, "", false);
        FileInfo[] files = new FileInfo[1];
        files[0] = fileInfo;
        String targetFolder = "/home/person/Documents";
        FileTransfer.ConflictAction defaultConflictAction = FileTransfer.ConflictAction.Skip;//null;
        FileTransfer ft = new FileTransfer(sourceFs, targetFs, files, targetFolder,callback, defaultConflictAction);
//        try{
        ft.transfer(targetFolder);
//        }catch (Exception e){
//            System.out.println(e);
//        }

    }
}