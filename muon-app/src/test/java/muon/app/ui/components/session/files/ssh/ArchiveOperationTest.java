package muon.app.ui.components.session.files.ssh;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArchiveOperationTest {

    @Test
    public void testIsSingleArchive1(){
        ArchiveOperation ao = new ArchiveOperation();
        String filename = "test.xz";
        boolean result = ao.isSingleArchive(filename);
        assertEquals(result,true);
    }
    @Test
    public void testIsSingleArchive2(){
        ArchiveOperation ao = new ArchiveOperation();
        String filename = "test.bz2";
        boolean result = ao.isSingleArchive(filename);
        assertEquals(result,true);
    }
    @Test
    public void testIsSingleArchive3(){
        ArchiveOperation ao = new ArchiveOperation();
        String filename = "test.gz";
        boolean result = ao.isSingleArchive(filename);
        assertEquals(result,true);
    }
    @Test
    public void testIsSingleArchive4(){
        ArchiveOperation ao = new ArchiveOperation();
        String filename = "test.invalidArchive";
        boolean result = ao.isSingleArchive(filename);
        assertEquals(result,true);
    }




    @Test
    public void testGetArchiveFileName1(){
        ArchiveOperation ao = new ArchiveOperation();
        String filename = "test.tar.gz";
        String result = ao.getArchiveFileName(filename);
        assertEquals("test.tar",result);
    }
    @Test
    public void testGetArchiveFileName2(){
        ArchiveOperation ao = new ArchiveOperation();
        String filename = ".zip";
        String result = ao.getArchiveFileName(filename);
        assertEquals("",result);
    }
    @Test
    public void testGetArchiveFileName3(){
        ArchiveOperation ao = new ArchiveOperation();
        String filename = "test.gz";
        String result = ao.getArchiveFileName(filename);
        assertEquals("test",result);
    }
    @Test
    public void testGetArchiveFileName4(){
        ArchiveOperation ao = new ArchiveOperation();
        String filename = "test.zip";
        String result = ao.getArchiveFileName(filename);
        assertEquals("test",result);
    }

}