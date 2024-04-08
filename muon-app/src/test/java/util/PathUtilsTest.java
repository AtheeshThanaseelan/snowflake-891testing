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

    // ISP Tests for getParent

    // Test getParent with empty path
    @Test
    public void getParentWithEmptyPath() {
        String result = PathUtils.getParent("");
        assertNull(result);
    }

    // Test getParent with null path
    @Test
    public void getParentWithNullPath() {
        String result = PathUtils.getParent(null);
        assertNull(result);
    }

    // Test getParent with path containing single segment
    @Test
    public void getParentWithSingleSegmentPath() {
        String result = PathUtils.getParent("C:\\file.txt");
        assertEquals("C:\\", result);
    }

    // Test getParent with path containing multiple segments
    @Test
    public void getParentWithMultipleSegmentPath() {
        String result = PathUtils.getParent("C:\\Windows\\explorer.exe");
        assertEquals("C:\\Windows\\", result);
    }

    // BVA Tests for getParent

    // Test getParent with minimum length path
    @Test
    public void getParentWithMinLengthPath() {
        String result = PathUtils.getParent("a");
        assertNull(result);
    }

    // Test getParent with maximum length path
    @Test
    public void getParentWithMaxLengthPath() {
        StringBuilder maxPath = new StringBuilder();
        for (int i = 0; i < 256; i++) {
            maxPath.append("a"); // Create a path of maximum length (256 characters)
        }
        String result = PathUtils.getParent(maxPath.toString());
        assertEquals(maxPath.substring(0, maxPath.length() - 1), result);
    }


        // ISP Tests

    // Test combining paths with empty segments
    @Test
    public void combineWithEmptySegments() {
        String result = PathUtils.combine("C:\\", "", "\\");
        assertEquals("C:\\", result);
    }

    // Test combining paths with null segments
    @Test
    public void combineWithNullSegments() {
        String result = PathUtils.combine(null, "Windows\\explorer.exe", "\\");
        assertNull(result);
    }

    // Test combining paths with minimum length segments
    @Test
    public void combineWithMinLengthSegments() {
        String result = PathUtils.combine("C:\\", "a", "\\");
        assertEquals("C:\\a", result);
    }

    // Test combining paths with maximum length segments
    @Test
    public void combineWithMaxLengthSegments() {
        StringBuilder maxSegment = new StringBuilder();
        for (int i = 0; i < 256; i++) {
            maxSegment.append("a"); // Create a segment of maximum length (256 characters)
        }
        String result = PathUtils.combine("C:\\", maxSegment.toString(), "\\");
        assertEquals("C:\\" + maxSegment.toString(), result);
    }

    // BVA Tests

    // Test combining paths with minimum length
    @Test
    public void combineWithMinLengthPath() {
        String result = PathUtils.combine("", "Windows\\explorer.exe", "\\");
        assertEquals("Windows\\explorer.exe", result);
    }

    // Test combining paths with maximum length
    @Test
    public void combineWithMaxLengthPath() {
        StringBuilder maxPath = new StringBuilder();
        for (int i = 0; i < 256; i++) {
            maxPath.append("a"); // Create a path of maximum length (256 characters)
        }
        String result = PathUtils.combine(maxPath.toString(), "explorer.exe", "\\");
        assertEquals(maxPath.toString() + "\\explorer.exe", result);
    }

    // Test combining paths with Unix separator
    @Test
    public void combineUnixWithUnixSeparator() {
        String result = PathUtils.combineUnix("/usr", "bin");
        assertEquals("/usr/bin", result);
    }

    // Test combining paths with Windows separator
    @Test
    public void combineWinWithWinSeparator() {
        String result = PathUtils.combineWin("C:\\", "Windows\\explorer.exe");
        assertEquals("C:\\Windows\\explorer.exe", result);
    }

    // Test combining paths with mixed separators
    @Test
    public void combineWithMixedSeparators() {
        String result = PathUtils.combineUnix("/usr", "bin\\test");
        assertEquals("/usr/bin\\test", result);
    }

    //Mutation Tests
    @Test
    public void testMutation() {
        
        // Mutation test cases:
        
        // Mutation: Change the condition to always return null
        assertEquals(null, PathUtils.getParent("/"));
        
        // Mutation: Change the substring indices to result in an incorrect parent path
        assertEquals("/path/", PathUtils.getParent("/path/to/parent/child"));
        
        // Mutation: Add 1 to the index variable to create an off-by-one error
        assertEquals("/path/to/par", PathUtils.getParent("/path/to/parent/child"));
        
        // Mutation: Swap the conditions in the if statement to always return an incorrect parent path
        assertEquals("/path/to/parent/chil", PathUtils.getParent("/path/to/parent/child"));
    }
}