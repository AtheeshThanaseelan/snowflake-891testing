package util;

import org.junit.Test;

import static org.junit.Assert.*;

public class FormatUtilsTest {

    @Test
    public void humanReadableByteCount1() {
        String bc = FormatUtils.humanReadableByteCount(5000,true);
        assertEquals("5.0 k",bc);
    }

    @Test
    public void humanReadableByteCount2() {
        String bc = FormatUtils.humanReadableByteCount(100,true);
        assertEquals("100 B",bc);
    }

    @Test
    public void humanReadableByteCount3() {
        String bc = FormatUtils.humanReadableByteCount(2148,false);
        assertEquals("2.1 Ki",bc);
    }

    @Test
    public void humanReadableByteCount4() {
        String bc = FormatUtils.humanReadableByteCount(50,false);
        assertEquals("50 B",bc);
    }

}