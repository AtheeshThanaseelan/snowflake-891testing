package util;

import org.junit.Test;

import static org.junit.Assert.*;

public class PathUtilsTest {

    @Test
    public void combine1() {
        String test = "test1";
        String test2 = "/test2";
        String result = PathUtils.combine(test,test2,"/");
        assertEquals("test1/test2",result);
    }

    @Test
    public void combine2() {
        String test = "test1/";
        String test2 = "/test2";
        String result = PathUtils.combine(test,test2,"/");
        assertEquals("test1/test2",result);
    }

    @Test
    public void getParent1() {
        String test = "";
        String result = PathUtils.getParent(test);
        assertEquals(null,result);
    }

    @Test
    public void getParent2() {
        String test = "/home/abc/";
        String result = PathUtils.getParent(test);
        assertEquals("/home/",result);
    }

    @Test
    public void getParent3() {
        String test = "/usr/abc";
        String result = PathUtils.getParent(test);
        assertEquals("/usr/",result);
    }

}