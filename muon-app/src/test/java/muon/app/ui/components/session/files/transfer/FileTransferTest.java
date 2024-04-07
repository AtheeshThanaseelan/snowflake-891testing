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


import java.io.File;

public class FileTransferTest {

    @Mock
    private FileTransferProgress callback;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    public void copyFileSetup() throws Exception{
        //Create Test Directory and File
        new File("srctestdirectory").mkdirs();
        new File("srctestdirectory/filename.txt").createNewFile();

        //Create destination directory
        new File("desttestdirectory").mkdirs();

    }

    public void cleanTestDirs(){
        String dirs[] = {"srctestdirectory","desttestdirectory"};
        for(int i=0; i<dirs.length;i++){
            File index = new File(dirs[i]);
            String[]entries = index.list();
            for(String s: entries){
                File currentFile = new File(index.getPath(),s);
                currentFile.delete();
            }
            index.delete();
        }
    }

    public boolean checkFileExists(String path){
        File f = new File(path);
        if(f.exists() && !f.isDirectory()) {
            return true;
        }else {
            return false;
        }
    }

    @Test
    public void testTransfer() throws  Exception {
        copyFileSetup();

        FileSystem sourceFs = new LocalFileSystem();
        FileSystem targetFs = new LocalFileSystem();
        FileInfo fileInfo = new FileInfo("filename.txt", "srctestdirectory/filename.txt", 0, FileType.File,
                2, 0, "","", 1, "", false);
        FileInfo[] files = new FileInfo[1];
        files[0] = fileInfo;
        String targetFolder = "desttestdirectory";

        FileTransfer.ConflictAction defaultConflictAction = FileTransfer.ConflictAction.Skip;;
        FileTransfer ft = new FileTransfer(sourceFs, targetFs, files, targetFolder,callback, defaultConflictAction);
        ft.transfer(targetFolder);

        assertTrue(checkFileExists("desttestdirectory/filename.txt"));

        cleanTestDirs();

    }
}