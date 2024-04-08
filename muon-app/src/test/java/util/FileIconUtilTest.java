package util;

import muon.app.common.FileInfo;
import muon.app.common.FileType;
import org.junit.Test;

import static org.junit.Assert.*;

public class FileIconUtilTest {

    @Test
    public void getIconForType() {

        FileInfo f1 = new FileInfo("file1.docx","./file1",10, FileType.File,
                1, 0, "","",0,"",false);

        String icon = FileIconUtil.getIconForType(f1);

        assertEquals(FontAwesomeContants.FA_FILE_WORD_O,icon);

    }

    //Mutation TESTS
    @Test
    public void testGetIconForType() {
        // Test cases to cover different fil
        FileInfo f1 = new FileInfo("folder","./file1",10, FileType.Directory,
                1, 0, "","",0,"",false);
        FileInfo f2 = new FileInfo("link","./file2",10, FileType.DirLink,
        1, 0, "","",0,"",false);
        FileInfo f3 = new FileInfo("folder","./file3",10, FileType.File,
                1, 0, "","",0,"",false);
            
        assertEquals(FontAwesomeContants.FA_FOLDER, FileIconUtil.getIconForType(f1));
        assertEquals(FontAwesomeContants.FA_FOLDER, FileIconUtil.getIconForType(f2));
        assertEquals(FontAwesomeContants.FA_FILE, FileIconUtil.getIconForType(f3));
    }
}