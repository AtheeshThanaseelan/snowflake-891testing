package muon.app.common;

import org.junit.Test;

import static org.junit.Assert.*;

public class FileInfoTest {

    @Test
    public void testCompareTo() {
        FileInfo f1 = new FileInfo("file1","./file1",10,FileType.File,
                1, 0, "","",0,"",false);

        FileInfo f2 = new FileInfo("file1","./file1",10,FileType.File,
                1, 0, "","",0,"",false);

        int res = f1.compareTo(f2);

        assertEquals(0,res);

    }

    @Test
    public void testGetUsername() {
        FileInfo f1 = new FileInfo("file1","./file1",10,FileType.File,
                1, 0, "","",0,"",false);

        String res = f1.getUserName();

        assertEquals("",res);

    }


}