package util;

import org.junit.Test;

import static org.junit.Assert.*;

public class FormatUtilsTest {

    @Test
    public void humanReadableByteCount() {
        String bc = FormatUtils.humanReadableByteCount(65432,false);
        assertEquals("63.9 Ki",bc);
    }
}