import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

/**
 * Test the AudioView Class.
 * 
 * Acknowledgments: I acknowledge that I have neither given nor
 * received assistance for this assignment except as
 * noted below:
 *
 * none
 *
 * @author Patrick Muradaz
 * @version PA02 October 10, 2018
 */
public class AudioFileIOTest {
    
    /**
     * Test the open method.
     * @throws IOException the IOException
     */
    @Test
    public void testOpen() throws IOException {
                
        AudioFileIO goodFile = new AudioFileIO( "TestFile.txt" );
        AudioFileIO badFile = new AudioFileIO( "FileDoesntExist.txt" );
                
        assertTrue( "File should be able to be opened - write", 
                        goodFile.open( goodFile.WRITER ) );
        
        goodFile.write( "TestWritten" );
        goodFile.close( goodFile.WRITER );
        
        assertTrue( "File should be able to be opened - read", 
                        goodFile.open( goodFile.READER ) );
        goodFile.close( goodFile.READER );
            
        assertFalse( "Open should not be able to open a file that does not "
                        + "exist - read", badFile.open( badFile.READER ) );
                        
    }
    
    /**
     * Test the readLine and write methods.
     * @throws IOException the IOException
     */
    @Test
    public void testReadAndWrite() throws IOException {
                
        AudioFileIO goodFile = new AudioFileIO( "HelloWorld.txt" );
        
        goodFile.open( goodFile.WRITER );
        goodFile.write( "Hello world." );
        goodFile.close( goodFile.WRITER );
        
        goodFile.open( goodFile.READER );
        
        assertEquals( "Method readLine should read a single line from the file",
                        "Hello world.", goodFile.readLine() );
        
        goodFile.close( goodFile.READER );
        
    }
} 
