import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
* This tests the AudioFile class.
*
* Acknowledgments: I acknowledge that I have neither given nor
* received assistance for this assignment except as
* noted below:
*
* Dr. Norton 9/19, 9/21
*
* Modifications: PDM 9/14/2018 Completed first versions of tests.
* PDM 9/26/2018 Completed final versions of tests.
*
* @author Patrick Muradaz
* @version PA01 (September 26, 2018)
*/
public class AudioFileTest {
    
    // valid test audio file
    private AudioFile testFile1 = new AudioFile( "The Beatles", 
            "I Am the Walrus", "Magical Mystery Tour", 10 );
    
    // test files for canAdd()
    private AudioFile testFile11 = new AudioFile( "", 
            "I Am the Walrus", "Magical Mystery Tour", 10 );
    private AudioFile testFile12 = new AudioFile( "The Beatles", 
            "", "Magical Mystery Tour", 10 );
    private AudioFile testFile13 = new AudioFile( "The Beatles", 
            "I Am the Walrus", "", 10 );
    private AudioFile testFile14 = new AudioFile( "The Beatles", 
            "I Am the Walrus", "Magical Mystery Tour", 0 );
    private AudioFile testFile15 = new AudioFile( null, 
            "I Am the Walrus", "Magical Mystery Tour", 10 );
    private AudioFile testFile16 = new AudioFile( "The Beatles", 
            null, "Magical Mystery Tour", 10 );
    private AudioFile testFile17 = new AudioFile( "The Beatles", 
            "I Am the Walrus", null, 10 );
    private AudioFile testFile18 = new AudioFile( "The Beatles", 
            "I Am the Walrus", "Magical Mystery Tour", 0 );
    
    // empty test audio file with invalid track number
    private AudioFile testFile2 = new AudioFile( "", 
            "", "", 0 );
    
    // null test audio file with invalid track number
    private AudioFile testFile3 = new AudioFile( null, null, null, 100 );
    
    // setter test audio file
    private AudioFile testFile4 = new AudioFile( null, null, null, 1 );
    
    // long output test audio file
    private AudioFile testFile5 = new AudioFile( "The Ruttles -not The "
            + "Beatles-", "This is Going to be Very Very Long", "The Long Long "
            + "Long Album", 0 );
    
    // no album output test
    private AudioFile testFile6 = new AudioFile( "Artist", 
            "Title", "", 10 );
    
    /**
     * Tests the canAdd method in AudioFile.
     */
    @Test
    public void testCanAdd() {
        // AudioFiles that can be added
        assertTrue( testFile1.canAdd() );
        
        assertTrue( testFile13.canAdd() );
        
        assertTrue( testFile17.canAdd() );
        
        // AudioFiles that can not be added
        assertFalse( testFile11.canAdd() );
        
        assertFalse( testFile12.canAdd() );
        
        assertFalse( testFile14.canAdd() );
        
        assertFalse( testFile15.canAdd() );
        
        assertFalse( testFile16.canAdd() );
        
        assertFalse( testFile18.canAdd() );
        
        assertFalse( testFile2.canAdd() );
        
        assertFalse( testFile3.canAdd() );
        
    }
    
    /**
     * Tests the setAlbum method in AudioFile.
     */
    @Test
    public void testSetAlbum() {

        // perfect album test
        String expected = "Magical Mystery Tour";
        testFile4.setAlbum( "Magical Mystery Tour" );
        
        assertEquals( "testSetAlbum() 1 failed", expected, 
                testFile4.getAlbum() );
        
        // empty album test
        expected = "";
        testFile4.setAlbum( "" );
        
        assertEquals( "testSetAlbum() 2 failed", expected, 
                testFile4.getAlbum() );
        
        // null album test
        expected = "";
        testFile4.setAlbum( null );
        
        assertEquals( "testSetAlbum() 3 failed", expected, 
                testFile4.getAlbum() );
    }
    
    /**
     * Tests the setArtist method in AudioFile.
     */
    @Test
    public void testSetArtist() {

        // perfect artist test
        String expected = "The Beatles";
        testFile4.setArtist( "The Beatles" );
               
        assertEquals( "testSetArtist() 1 failed", expected, 
                testFile4.getArtist() );
        
        // empty artist test
        expected = "";
        testFile4.setArtist( "" );
                
        assertEquals( "testSetArtist() 2 failed", expected, 
                testFile4.getArtist() );
        
        // null artist test
        expected = "";
        testFile4.setArtist( null );

        assertEquals( "testSetArtist() 3 failed", expected, 
                testFile4.getArtist() );
        
        // return true
        boolean expect = true;
               
        assertEquals( "testSetArtist() 4 failed", expect, 
                testFile4.setArtist( "The Beatles" ) );
        
        // return false
        expect = false;
        
        assertEquals( "testSetArtist() 5 failed", expect, 
                testFile4.setArtist( "" ) );
        
        assertEquals( "testSetArtist() 6 failed", expect, 
                testFile4.setArtist( null ) );
    }
    
    /**
     * Tests the setTitle method in AudioFile.
     */
    @Test
    public void testSetTitle() {

        // perfect title test
        String expected = "The Beatles";
        testFile4.setTitle( "The Beatles" );
               
        assertEquals( "testSetTitle() 1 failed", expected, 
                testFile4.getTitle() );
        
        // empty title test
        expected = "";
        testFile4.setTitle( "" );
                
        assertEquals( "testSetTitle() 2 failed", expected, 
                testFile4.getTitle() );
        
        // null title test
        expected = "";
        testFile4.setTitle( null );
        
        assertEquals( "testSetTitle() 3 failed", expected, 
                testFile4.getTitle() );
        
        // return true
        boolean expect = true;
               
        assertEquals( "testSetTitle() 4 failed", expect, 
                testFile4.setTitle( "I Am the Walrus" ) );
        
        // return false
        expect = false;
        
        assertEquals( "testSetTitle() 5 failed", expect, 
                testFile4.setTitle( "" ) );
        
        assertEquals( "testSetTitle() 6 failed", expect, 
                testFile4.setTitle( null ) );
    }
    
    /**
     * Tests the setTrack method in AudioFile.
     */
    @Test
    public void testSetTrack() {

        // -1 track test
        int expected = -1;
        testFile4.setTrack( -1 );
               
        assertEquals( "testSetTrack() 1 failed", expected, 
                testFile4.getTrack() );
        
        // 1 track test
        testFile4.setTrack( -1 );
        
        assertEquals( "testSetTrack() 2 failed", expected, 
                testFile4.getTrack() );
        
        // 50 artist test
        expected = 50;
        testFile4.setTrack( 50 );
                
        assertEquals( "testSetTrack() 3 failed", expected, 
                testFile4.getTrack() );
        
        // out of bounds artist test
        expected = 100;
        testFile4.setTrack( 100 );
        
        assertEquals( "testSetTrack() 4 failed", expected, 
                testFile4.getTrack() );
        
        // return true
        boolean expect = true;
               
        assertEquals( "testSetTrack() 5 failed", expect, 
                testFile4.setTrack( 1 ) );
        
        assertEquals( "testSetTrack() 6 failed", expect, 
                testFile4.setTrack( -1 ) );
        
        // return false
        expect = false;
        
        assertEquals( "testSetTrack() 7 failed", expect, 
                testFile4.setTrack( 100 ) );
        
        assertEquals( "testSetTrack() 8 failed", expect, 
                testFile4.setTrack( 0 ) );
    }
    
    /**
     * Tests the toString method in AudioFile.
     */
    @Test
    public void testToString() {
        
        // regular output test
        String expected = "The Beatles, I Am the Walrus (Magical Mystery Tour)";
        
        assertEquals( "testToString() 1 failed", expected, 
                testFile1.toString() );
        
        // long output test
        expected = "The Ruttles -not The Beatles-, This is Going to be Very " 
                + "Very Long (Th...";
        
        assertEquals( "testToString() 2 failed", expected, 
                testFile5.toString() );
        
        // no album output test
        expected = "Artist, Title";
        
        assertEquals( "testToString() 3 failed", expected, 
                testFile6.toString() );
        
    }

}
