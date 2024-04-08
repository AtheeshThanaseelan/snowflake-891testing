package util;

import muon.app.common.FileInfo;
import muon.app.common.FileType;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.Collections;

public class FileIconUtilTest {

    @Test
    public void getIconForType() {
        FileInfo f1 = new FileInfo("file1.docx", "./file1", 10, FileType.File,
                1, 0, "", "", 0, "", false);

        String icon = FileIconUtil.getIconForType(f1);

        assertEquals(FontAwesomeContants.FA_FILE_WORD_O, icon);
    }

    @Test
    public void testGetIconForType_Directory() {
        // ISP Test: Partition - Directory
        FileInfo fileInfo = new FileInfo("", "", 0, FileType.Directory, 0, 0, "", "", 0, "", false);
        assertEquals("Expected folder icon", FontAwesomeContants.FA_FOLDER, FileIconUtil.getIconForType(fileInfo));
    }

    @Test
    public void testGetIconForType_Archive() {
        // ISP Test: Partition - Archive
        FileInfo fileInfo = new FileInfo("test.zip", "", 0, FileType.File, 0, 0, "", "", 0, "", false);
        assertEquals("Expected archive icon", FontAwesomeContants.FA_FILE_ARCHIVE_O, FileIconUtil.getIconForType(fileInfo));
    }

    @Test
    public void testGetIconForType_Audio() {
        // ISP Test: Partition - Audio
        FileInfo fileInfo = new FileInfo("audio.mp3", "", 0, FileType.File, 0, 0, "", "", 0, "", false);
        assertEquals("Expected audio icon", FontAwesomeContants.FA_FILE_AUDIO_O, FileIconUtil.getIconForType(fileInfo));
    }

    @Test
    public void testGetIconForType_Code() {
        // ISP Test: Partition - Code
        FileInfo fileInfo = new FileInfo("code.java", "", 0, FileType.File, 0, 0, "", "", 0, "", false);
        assertEquals("Expected code icon", FontAwesomeContants.FA_FILE_CODE_O, FileIconUtil.getIconForType(fileInfo));
    }

    @Test
    public void testGetIconForType_EmptyFileName() {
        // BVA Test: Boundary - Empty file name
        FileInfo fileInfo = new FileInfo("", "", 0, FileType.File, 0, 0, "", "", 0, "", false);
        assertEquals("Expected default file icon", FontAwesomeContants.FA_FILE, FileIconUtil.getIconForType(fileInfo));
    }

    @Test
    public void testGetIconForType_MaxLengthArchive() {
        // BVA Test: Boundary - Maximum length file name for Archive
        String longName = String.join("", Collections.nCopies(252, "a")) + ".zip";
        FileInfo fileInfo = new FileInfo(longName, "", 0, FileType.File, 0, 0, "", "", 0, "", false);
        assertEquals("Expected archive icon", FontAwesomeContants.FA_FILE_ARCHIVE_O, FileIconUtil.getIconForType(fileInfo));
    }

    @Test
    public void testGetIconForType_MaxLengthAudio() {
        // BVA Test: Boundary - Maximum length file name for Audio
        String longName = String.join("", Collections.nCopies(252, "a")) + ".mp3";
        FileInfo fileInfo = new FileInfo(longName, "", 0, FileType.File, 0, 0, "", "", 0, "", false);
        assertEquals("Expected audio icon", FontAwesomeContants.FA_FILE_AUDIO_O, FileIconUtil.getIconForType(fileInfo));
    }

    @Test
    public void testGetIconForType_MaxLengthCode() {
        // BVA Test: Boundary - Maximum length file name for Code
        String longName = String.join("", Collections.nCopies(252, "a")) + ".java";
        FileInfo fileInfo = new FileInfo(longName, "", 0, FileType.File, 0, 0, "", "", 0, "", false);
        assertEquals("Expected code icon", FontAwesomeContants.FA_FILE_CODE_O, FileIconUtil.getIconForType(fileInfo));
    }

    @Test
    public void testGetIconForType_MaxLengthExcel() {
        // BVA Test: Boundary - Maximum length file name for Excel
        String longName = String.join("", Collections.nCopies(252, "a")) + ".xlsx";
        FileInfo fileInfo = new FileInfo(longName, "", 0, FileType.File, 0, 0, "", "", 0, "", false);
        assertEquals("Expected excel icon", FontAwesomeContants.FA_FILE_EXCEL_O, FileIconUtil.getIconForType(fileInfo));
    }

    @Test
    public void testGetIconForType_MaxLengthImage() {
        // BVA Test: Boundary - Maximum length file name for Image
        String longName = String.join("", Collections.nCopies(252, "a")) + ".jpg";
        FileInfo fileInfo = new FileInfo(longName, "", 0, FileType.File, 0, 0, "", "", 0, "", false);
        assertEquals("Expected image icon", FontAwesomeContants.FA_FILE_IMAGE_O, FileIconUtil.getIconForType(fileInfo));
    }

    @Test
    public void testGetIconForType_MaxLengthVideo() {
        // BVA Test: Boundary - Maximum length file name for Video
        String longName = String.join("", Collections.nCopies(252, "a")) + ".mp4";
        FileInfo fileInfo = new FileInfo(longName, "", 0, FileType.File, 0, 0, "", "", 0, "", false);
        assertEquals("Expected video icon", FontAwesomeContants.FA_FILE_VIDEO_O, FileIconUtil.getIconForType(fileInfo));
    }

    @Test
    public void testGetIconForType_MaxLengthPDF() {
        // BVA Test: Boundary - Maximum length file name for PDF
        String longName = String.join("", Collections.nCopies(252, "a")) + ".pdf";
        FileInfo fileInfo = new FileInfo(longName, "", 0, FileType.File, 0, 0, "", "", 0, "", false);
        assertEquals("Expected PDF icon", FontAwesomeContants.FA_FILE_PDF_O, FileIconUtil.getIconForType(fileInfo));
    }

    @Test
    public void testGetIconForType_MaxLengthPowerPoint() {
        // BVA Test: Boundary - Maximum length file name for PowerPoint
        String longName = String.join("", Collections.nCopies(252, "a")) + ".pptx";
        FileInfo fileInfo = new FileInfo(longName, "", 0, FileType.File, 0, 0, "", "", 0, "", false);
        assertEquals("Expected PowerPoint icon", FontAwesomeContants.FA_FILE_POWERPOINT_O, FileIconUtil.getIconForType(fileInfo));
    }

    @Test
    public void testGetIconForType_MaxLengthWord() {
        // BVA Test: Boundary - Maximum length file name for Word
        String longName = String.join("", Collections.nCopies(252, "a")) + ".docx";
        FileInfo fileInfo = new FileInfo(longName, "", 0, FileType.File, 0, 0, "", "", 0, "", false);
        assertEquals("Expected Word icon", FontAwesomeContants.FA_FILE_WORD_O, FileIconUtil.getIconForType(fileInfo));
    }

}
