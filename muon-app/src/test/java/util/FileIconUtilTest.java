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
}