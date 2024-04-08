package muon.app.common.local;

import muon.app.common.FileInfo;
import muon.app.common.FileType;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;

public class LocalFileSystemTest {

    public void testDirSetup() throws Exception{
        //Create Test Directory and File
        new File("srctestdirectory").mkdirs();
        new File("srctestdirectory/filename.txt").createNewFile();
        new File("emptyDir").mkdirs();

    }

    public void cleanTestDirs(){
        String dirs[] = {"srctestdirectory","emptyDir"};
        for(int i=0; i<dirs.length;i++){
            if(checkFileExists(dirs[i])) {
                File index = new File(dirs[i]);
                String[] entries = index.list();
                for (String s : entries) {
                    File currentFile = new File(index.getPath(), s);
                    currentFile.delete();
                }
                index.delete();
            }
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
    public void list1() throws Exception{
        testDirSetup();
        LocalFileSystem lfs = new LocalFileSystem();
        List<FileInfo> flist =  lfs.list("");
        assertTrue(flist.size()>0);
        cleanTestDirs();
    }

    @Test
    public void list2() throws Exception{
        testDirSetup();
        LocalFileSystem lfs = new LocalFileSystem();
        List<FileInfo> flist =  lfs.list("srctestdirectory/");
        assertEquals(flist.get(0).getName(),"filename.txt");
        cleanTestDirs();
    }

    @Test
    public void list3() throws Exception{
        testDirSetup();
        LocalFileSystem lfs = new LocalFileSystem();
        List<FileInfo> flist =  lfs.list("srctestdirectory");
        assertEquals(flist.get(0).getName(),"filename.txt");
        cleanTestDirs();
    }

    @Test
    public void testDelete1() throws Exception{
        testDirSetup();
        LocalFileSystem lfs = new LocalFileSystem();
        FileInfo fi = new FileInfo("filename.txt", "srctestdirectory/filename.txt", 0, FileType.File,
                2, 0, "","", 1, "", false);

        lfs.delete(fi);
        assertFalse(checkFileExists("srctestdirectory/filename.txt"));
        cleanTestDirs();
    }

    @Test
    public void testDelete2() throws Exception{
        testDirSetup();
        LocalFileSystem lfs = new LocalFileSystem();
        FileInfo fi = new FileInfo("emptyDir", "emptyDir", 0, FileType.Directory,
                2, 0, "","", 1, "", false);

        lfs.delete(fi);
        assertFalse(checkFileExists("emptyDir"));
        cleanTestDirs();
    }

    @Test
    public void testDelete3() throws Exception{
        testDirSetup();
        LocalFileSystem lfs = new LocalFileSystem();
        FileInfo fi = new FileInfo("srctestdirectory", "srctestdirectory", 0, FileType.Directory,
                2, 0, "","", 1, "", false);

        lfs.delete(fi);
        assertFalse(checkFileExists("srctestdirectory"));
        cleanTestDirs();
    }

}