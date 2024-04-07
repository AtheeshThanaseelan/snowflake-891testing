package util;

import org.junit.Test;

import static org.junit.Assert.*;

public class PathUtilsTest {

    @Test
    public void combine() {
        String test = "C:\\";
        String test2 = "Windows\\explorer.exe";
        String result = PathUtils.combine(test,test2,"\\");
        assertEquals("C:\\Windows\\explorer.exe",result);
    }

    @Test
    public void getParent() {
        String test = "C:\\Windows\\explorer.exe";
        String result = PathUtils.getParent(test);
        assertEquals("C:\\Windows\\",result);
    }
}