package muon.app.common;

import org.junit.Test;

import static org.junit.Assert.*;

public class FileInfoTest {

    @Test
    public void testCompareTo1() {
        FileInfo f1 = new FileInfo("file1","./file1",10,FileType.Directory,
                1, 0, "","",0,"",false);

        FileInfo f2 = new FileInfo("file1","./file1",10,FileType.File,
                1, 0, "","",0,"",false);

        int res = f1.compareTo(f2);

        assertEquals(1,res);
    }

    @Test
    public void testCompareTo2() {
        FileInfo f1 = new FileInfo("file1","./file1",10,FileType.File,
                1, 0, "","",0,"",false);

        FileInfo f2 = new FileInfo("file1","./file1",10,FileType.File,
                1, 0, "","",0,"",false);

        int res = f1.compareTo(f2);

        assertEquals(0,res);

    }

    @Test
    public void testCompareTo3() {
        FileInfo f1 = new FileInfo("file1","./file1",10,FileType.File,
                1, 0, "","",0,"",false);

        FileInfo f2 = new FileInfo("file1","./file1",10,FileType.Directory,
                1, 0, "","",0,"",false);

        int res = f1.compareTo(f2);

        assertEquals(-1,res);
    }

    @Test
    public void testCompareTo4() {
        FileInfo f1 = new FileInfo("file1","./file1",10,FileType.Directory,
                1, 0, "","",0,"",false);

        FileInfo f2 = new FileInfo("file1","./file1",10,FileType.Directory,
                1, 0, "","",0,"",false);

        int res = f1.compareTo(f2);

        assertEquals(0,res);
    }

    @Test
    public void testGetUsername1() {
        FileInfo f1 = new FileInfo("file1","./file1",10,FileType.File,
                1, 0, "","",0,"group:wheel",false);

        String res = f1.getUserName();

        assertEquals("",res);

    }

    @Test
    public void testGetUsername2() {
        FileInfo f1 = new FileInfo("file1","./file1",10,FileType.File,
                1, 0, "","",0,"home abcd username 153 223",false);

        String res = f1.getUserName();

        assertEquals("username",res);

    }


    @Test
    public void testGetUsername3() {
        FileInfo f1 = new FileInfo("file1","./file1",10,FileType.File,
                1, 0, "","",0,"",false);

        String res = f1.getUserName();

        assertEquals("",res);

    }


}