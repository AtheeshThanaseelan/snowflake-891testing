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
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Scanner;

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
        new File("srctestdirectory/filename2.txt").createNewFile();

        //Create destination directory
        new File("desttestdirectory").mkdirs();

        //Create Duplicate Test Directory and File
        new File("Dupetestdirectory").mkdirs();
        new File("Dupetestdirectory/filename.txt").createNewFile();
        FileWriter myWriter = new FileWriter("Dupetestdirectory/filename.txt");
        myWriter.write("This File is actually different.");
        myWriter.close();
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
    public void testTransfer1() throws  Exception {
        copyFileSetup();

        FileSystem sourceFs = new LocalFileSystem();
        FileSystem targetFs = new LocalFileSystem();
        FileInfo fileInfo = new FileInfo("filename.txt", "srctestdirectory/filename.txt", 0, FileType.File,
                2, 0, "","", 1, "", false);
        FileInfo[] files = new FileInfo[1];
        files[0] = fileInfo;
        String targetFolder = "Dupetestdirectory";

        FileTransfer.ConflictAction defaultConflictAction = FileTransfer.ConflictAction.Skip;
        //Override IllegalArgumentException when Cancel is set as defaultConflictAction
        fileInfo.setPermission(FileTransfer.ConflictAction.Cancel.ordinal());
        FileTransfer ft = new FileTransfer(sourceFs, targetFs, files, targetFolder,callback, defaultConflictAction);
        ft.transfer(targetFolder);

        assertTrue(checkFileExists("Dupetestdirectory/filename.txt"));
        assertFalse(checkFileExists("Dupetestdirectory/filename2.txt")); //File 2 not copied as operation is cancelled.
        cleanTestDirs();

    }

    @Test
    public void testTransfer2() throws  Exception {
        copyFileSetup();

        FileSystem sourceFs = new LocalFileSystem();
        FileSystem targetFs = new LocalFileSystem();
        FileInfo fileInfo = new FileInfo("filename.txt", "srctestdirectory/filename.txt", 0, FileType.File,
                2, 0, "","", 1, "", false);
        FileInfo[] files = new FileInfo[1];
        files[0] = fileInfo;
        String targetFolder = "desttestdirectory";

        FileTransfer.ConflictAction defaultConflictAction = FileTransfer.ConflictAction.Skip;
        FileTransfer ft = new FileTransfer(sourceFs, targetFs, files, targetFolder,callback, defaultConflictAction);
        ft.transfer(targetFolder);

        assertTrue(checkFileExists("desttestdirectory/filename.txt"));

        cleanTestDirs();
    }

    @Test
    public void testTransfer3() throws  Exception {
        copyFileSetup();

        FileSystem sourceFs = new LocalFileSystem();
        FileSystem targetFs = new LocalFileSystem();
        FileInfo fileInfo = new FileInfo("filename.txt", "srctestdirectory/filename.txt", 0, FileType.File,
                2, 0, "","", 1, "", false);
        FileInfo[] files = new FileInfo[1];
        files[0] = fileInfo;
        String targetFolder = "Dupetestdirectory";

        FileTransfer.ConflictAction defaultConflictAction = FileTransfer.ConflictAction.Skip;
        FileTransfer ft = new FileTransfer(sourceFs, targetFs, files, targetFolder,callback, defaultConflictAction);
        ft.transfer(targetFolder);


        assertTrue(checkFileExists("Dupetestdirectory/filename.txt"));
        Scanner scanner = new Scanner(Paths.get("Dupetestdirectory/filename.txt"), StandardCharsets.UTF_8.name());
        String content = scanner.useDelimiter("\\A").next();
        scanner.close();
        assertEquals("This File is actually different.",content);

        cleanTestDirs();

    }

    @Test
    public void testTransfer4() throws  Exception {
        copyFileSetup();

        FileSystem sourceFs = new LocalFileSystem();
        FileSystem targetFs = new LocalFileSystem();
        FileInfo fileInfo = new FileInfo("filename.txt", "srctestdirectory/filename.txt", 0, FileType.File,
                2, 0, "","", 1, "", false);
        FileInfo[] files = new FileInfo[1];
        files[0] = fileInfo;
        String targetFolder = "Dupetestdirectory";

        FileTransfer.ConflictAction defaultConflictAction = FileTransfer.ConflictAction.AutoRename;
        FileTransfer ft = new FileTransfer(sourceFs, targetFs, files, targetFolder,callback, defaultConflictAction);
        ft.transfer(targetFolder);


        assertTrue(checkFileExists("Dupetestdirectory/Copy-of-filename.txt"));

        cleanTestDirs();

    }

}