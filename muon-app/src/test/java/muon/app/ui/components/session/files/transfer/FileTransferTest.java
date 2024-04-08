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

import java.util.UUID;
import java.util.Collections;

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
    @Test
    public void testTransfer_ValidInput() throws Exception {
        // Valid target folder path
        String targetFolder = "valid/path/to/target";

        // Non-empty array of files
        FileInfo fileInfo = new FileInfo("filename.txt", "src/path/filename.txt", 0, FileType.File,
                2, 0, "", "", 1, "", false);
        FileInfo[] files = new FileInfo[]{fileInfo};

        // ConflictAction is Overwrite
        FileTransfer.ConflictAction conflictAction = FileTransfer.ConflictAction.OverWrite;

        // Instantiate FileTransfer object
        FileTransfer ft = new FileTransfer(new LocalFileSystem(), new LocalFileSystem(), files, targetFolder, callback, conflictAction);

        // Execute transfer method
        ft.transfer(targetFolder);

        // Add assertions
        // Example assertion: Verify that the file is copied to the destination folder
        assertTrue(checkFileExists(targetFolder + "/filename.txt"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTransfer_InvalidConflictAction() {
        // Invalid ConflictAction is Cancel
        FileTransfer.ConflictAction conflictAction = FileTransfer.ConflictAction.Cancel;

        // Attempt to create FileTransfer object with invalid ConflictAction
        new FileTransfer(new LocalFileSystem(), new LocalFileSystem(), new FileInfo[]{}, "targetFolder", callback, conflictAction);
    }

    @Test(expected = Exception.class)
    public void testTransfer_InvalidTargetFolder() throws Exception {
        // Invalid target folder path (null)
        String targetFolder = null;

        // Non-empty array of files
        FileInfo fileInfo = new FileInfo("filename.txt", "src/path/filename.txt", 0, FileType.File,
                2, 0, "", "", 1, "", false);
        FileInfo[] files = new FileInfo[]{fileInfo};

        // ConflictAction is Overwrite
        FileTransfer.ConflictAction conflictAction = FileTransfer.ConflictAction.OverWrite;

        // Instantiate FileTransfer object
        FileTransfer ft = new FileTransfer(new LocalFileSystem(), new LocalFileSystem(), files, targetFolder, callback, conflictAction);

        // Execute transfer method
        ft.transfer(targetFolder);
    }

    @Test
    public void testTransfer_BoundaryValues() throws Exception {
        // BVA1: Minimum valid length (1 character) for target folder path
        String targetFolder1 = "a";
    
        // BVA2: Maximum valid length (256 characters) for target folder path
        String targetFolder2 = String.join("", Collections.nCopies(256, "a"));
    
        // BVA3: Below minimum (0 characters) for target folder path
        String targetFolder3 = "";
    
        // BVA4: Above maximum (257 characters) for target folder path
        String targetFolder4 = String.join("", Collections.nCopies(257, "a"));
    
        // Instantiate FileTransfer objects for boundary value test cases
        FileTransfer ft1 = new FileTransfer(new LocalFileSystem(), new LocalFileSystem(), new FileInfo[]{}, targetFolder1, callback, FileTransfer.ConflictAction.OverWrite);
        FileTransfer ft2 = new FileTransfer(new LocalFileSystem(), new LocalFileSystem(), new FileInfo[]{}, targetFolder2, callback, FileTransfer.ConflictAction.OverWrite);
        FileTransfer ft3 = new FileTransfer(new LocalFileSystem(), new LocalFileSystem(), new FileInfo[]{}, targetFolder3, callback, FileTransfer.ConflictAction.OverWrite);
        FileTransfer ft4 = new FileTransfer(new LocalFileSystem(), new LocalFileSystem(), new FileInfo[]{}, targetFolder4, callback, FileTransfer.ConflictAction.OverWrite);
    
        try {
            // Execute transfer method for each boundary value test case
            ft1.transfer(targetFolder1);
            ft2.transfer(targetFolder2);
            ft3.transfer(targetFolder3);
            ft4.transfer(targetFolder4);
    
            // Add assertions
            assertTrue(checkFileExists(targetFolder1)); // For BVA1
            assertTrue(checkFileExists(targetFolder2)); // For BVA2
            assertFalse(checkFileExists(targetFolder3)); // For BVA3
            assertFalse(checkFileExists(targetFolder4)); // For BVA4
        } finally {
            // Close the FileTransfer objects
            if (ft1 != null) {
                ft1.close();
            }
            if (ft2 != null) {
                ft2.close();
            }
            if (ft3 != null) {
                ft3.close();
            }
            if (ft4 != null) {
                ft4.close();
            }
        }
    }
    

}


