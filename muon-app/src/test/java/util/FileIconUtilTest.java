package util;

import muon.app.common.FileInfo;
import muon.app.common.FileType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import static org.junit.Assert.*;

@RunWith(value = Parameterized.class)
public class FileIconUtilTest {

    private String fileName;
    private String iconType;

    public FileIconUtilTest(String fName, String icType){
        this.fileName = fName;
        this.iconType = icType;
    }

    @Parameterized.Parameters(name= "{index}: isValid({0})={1}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"file.zip", FontAwesomeContants.FA_FILE_ARCHIVE_O},
                {"file.mp3", FontAwesomeContants.FA_FILE_AUDIO_O},
                {"file.c", FontAwesomeContants.FA_FILE_CODE_O},
                {"file.xls", FontAwesomeContants.FA_FILE_EXCEL_O},
                {"file.jpg", FontAwesomeContants.FA_FILE_IMAGE_O},
                {"file.mp4", FontAwesomeContants.FA_FILE_VIDEO_O},
                {"file.pdf", FontAwesomeContants.FA_FILE_PDF_O},
                {"file.ppt", FontAwesomeContants.FA_FILE_POWERPOINT_O},
                {"file.doc", FontAwesomeContants.FA_FILE_WORD_O},
                {"file.obscureExtension", FontAwesomeContants.FA_FILE},
                 } ); }

    @Test
    public void getIconForType() {
        FileInfo f1 = new FileInfo(fileName,"./"+fileName,10, FileType.File,
                1, 0, "","",0,"",false);
        String icon = FileIconUtil.getIconForType(f1);
        assertEquals(iconType,icon);
    }

}