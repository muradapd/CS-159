import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Test the AudioFile Class.
 * 
 * Acknowledgments: I acknowledge that I have neither given nor
 * received assistance for this assignment except as
 * noted below:
 *
 * This was originally Dr. Norton's solution to PA01
 *
 * Modifications: PDM 10/9/2018 Added new audio files to be used by the new 
 * equals method tests.
 *  
 * @author Patrick Muradaz
 * @version PA02 October 9, 2018
 */
public class AudioFileTest {

    private AudioFile allNulls = new AudioFile( null, null, null, -1 );
    private AudioFile badArtistNull = new AudioFile( null, "title", null,
                    10 );
    private AudioFile badTitleNull = new AudioFile( "artist", null, "album",
                    19 );
    private AudioFile badTrackLow = new AudioFile( "artist", "title", "album",
                    0 );
    private AudioFile badTrackHigh = new AudioFile( "artist", "title", "album",
                    101 );
    private AudioFile goodLowTrack = new AudioFile( "artist", "title", "album",
                    1 );
    private AudioFile goodHighTrack = new AudioFile( "artist", "title", "album",
                    99 );
    private AudioFile goodPubNoTrack = new AudioFile( "artist", "title",
                    null, -1 );
    private AudioFile isEqualCommon = new AudioFile( "artist", "title",
                    "album", 1 );
    private AudioFile isEqual = new AudioFile( "artist", "title",
            "album", 1 );
    private AudioFile differentArtist = new AudioFile( "different", "title",
            "album", 1 );
    private AudioFile differentTitle = new AudioFile( "artist", "different",
            "album", 1 );
    private AudioFile differentAlbum = new AudioFile( "artist", "title",
            "different", 1 );
    private AudioFile differentTrack = new AudioFile( "artist", "title",
            "album", 10 );
    
    /**
     * Test what happens if all nulls & 0 sent to constructor.
     */
    @Test
    public void testAllNulls() {

        assertEquals( "Null artist not set correctly", "",
                        allNulls.getArtist() );
        assertEquals( "Null title not set correctly", "", allNulls.getTitle() );
        assertEquals( "Null album not set correctly", "", allNulls.getAlbum() );
        assertFalse( "canAdd() should be false when audio has all nulls",
                        allNulls.canAdd() );
    }

    /**
     * test what happens with valid entry except for bad author.
     */
    @Test
    public void testBadArtistNull() {

        assertFalse( "canAdd() should be false with author is null",
                        badArtistNull.canAdd() );
    }

    /**
     * test what happens with valid entry except for bad title.
     */
    @Test
    public void testBadTitleNull() {

        assertFalse( "canAdd() should be false when title is null",
                        badTitleNull.canAdd() );
    }

    /**
     * test what happens with valid entry except bad track > 99).
     */
    @Test
    public void testBadTrackHigh() {

        assertFalse( "canAdd() should be false when track greater than 99",
                        badTrackHigh.canAdd() );
    }

    /**
     * test what happens with valid entry except bad track (< 0).
     */
    @Test
    public void testBadTrackLow() {

        assertFalse( "canAdd() should be false when track is less than 1 "
                        + "(and not -1)",
                        badTrackLow.canAdd() );
    }

    /**
     * Test the equals method with two AudioFiles that have different album 
     * values.
     */
    @Test
    public void testDifferentAlbum() {
        
        assertFalse( "equals() should return false", 
                        isEqualCommon.equals( differentAlbum ) );
    }

    /**
     * Test the equals method with two AudioFiles that have different artist 
     * values.
     */
    @Test
    public void testDifferentArtist() {
        
        assertFalse( "equals() should return false", 
                        isEqualCommon.equals( differentArtist ) );
    }

    /**
     * Test the equals method with two AudioFiles that have different title 
     * values.
     */
    @Test
    public void testDifferentTitle() {
        
        assertFalse( "equals() should return false", 
                        isEqualCommon.equals( differentTitle ) );
    }

    /**
     * Test the equals method with two AudioFiles that have different track 
     * values.
     */
    @Test
    public void testDifferentTrack() {
        
        assertFalse( "equals() should return false", 
                        isEqualCommon.equals( differentTrack ) );
    }

    /**
     * Test what happens with all valid entries (lowest acceptable track).
     */
    @Test
    public void testGoodPubEarlyTrack() {

        assertTrue( "canAdd() should be true when track equals 1",
                        goodLowTrack.canAdd() );
    }

    /**
     * Test what happens with all valid entries (highest acceptable track).
     */
    @Test
    public void testGoodPubLateTrack() {

        assertTrue( "canAdd() should be true when track equals 99",
                        goodHighTrack.canAdd() );
    }
    
    /**
     * Test what happens with add valid entries but with null city, publisher, &
     * track = -1.
     */
    @Test
    public void testGoodPubNoTrack() {

        assertTrue( "canAdd() should be true with valid author/title and null "
                        + "album & track == -1",
                        goodPubNoTrack.canAdd() );
    }
    
    /**
     * Test the equals method with two different AudioFiles that have the same 
     * values.
     */
    @Test
    public void testIsEqual() {

        assertTrue( "equals() should return true", 
                        isEqualCommon.equals( isEqual ) );
    }
    
    /**
     * Test the equals method with a valid album and an invalid album.
     */
    @Test
    public void testNullAlbum() {
        
        assertFalse( "equals() should return false", 
                        isEqualCommon.equals( badArtistNull ) );
    }
    
    /**
     * Test the equals method with a valid artist and an invalid artist.
     */
    @Test
    public void testNullArtist() {
        
        assertFalse( "equals() should return false", 
                        isEqualCommon.equals( badArtistNull ) );
    }
    
    /**
     * Test the equals method with a valid title and an invalid title.
     */
    @Test
    public void testNullTitle() {
        
        assertFalse( "equals() should return false", 
                        isEqualCommon.equals( badTitleNull ) );
    }
    
    /**
     * Test to see if setArtist makes bad entry good.
     */
    @Test
    public void testSetToFixArtist() {

        // make sure this is correct to start
        assertFalse( "canAdd() should be false to start when artist is invalid",
                        badArtistNull.canAdd() );

        // set a valid author
        badArtistNull.setArtist( "artist" );

        // Should now be valid
        assertTrue( "canAdd() should be true after fixing invalid artist",
                        badArtistNull.canAdd() );

    }
    
    /**
     * Test to see if setTitle makes bad entry good.
     */
    @Test
    public void testSetToFixTitle() {

        // make sure this is correct to start
        assertFalse( "canAdd() should be false to start when title is invalid",
                        badTitleNull.canAdd() );

        // set a valid title
        badTitleNull.setTitle( "title" );

        // Should now be valid
        assertTrue( "canAdd() should be true after fixing invalid title",
                        badTitleNull.canAdd() );

    }
    
    /**
     * Test to see if setTitle makes bad entry good.
     */
    @Test
    public void testSetToFixTrack() {

        // make sure this is correct to start
        assertFalse( "canAdd() should be false to start when track is invlid",
                        badTrackLow.canAdd() );

        // set a valid track
        badTrackLow.setTrack( 10 );

        // Should now be valid
        assertTrue( "canAdd() should be true after fixing invalid track",
                        badTrackLow.canAdd() );

    }

    /**
     * Test toString with missing album.
     */
    @Test
    public void testToStringNoAlbum() {

        AudioFile file = new AudioFile( "Beatles", "Eleanor Rigby", null,
                        2 );

        assertEquals( "toString with missing album is incorrectly formatted",
                        "Beatles, Eleanor Rigby",
                        file.toString() );
    }

    /**
     * Test normal toString.
     */
    @Test
    public void testToStringNormal() {

        AudioFile pub = new AudioFile( "Beatles", "Eleanor Rigby", "Revolver",
                        2 );

        assertEquals( "Normal toString is incorrectly formatted",
                        "Beatles, Eleanor Rigby (Revolver)",
                        pub.toString() );
    }


    /**
     * Test toString too long.
     */
    @Test
    public void testToStringTooLong() {

        AudioFile pub = new AudioFile( "Wierd Al Yankovic", "A Truly Bizarre "
                        + "Song by Weird Al", "Weird Al's Greatest and "
                                        + "Bestest Hits", 3 );

        assertEquals( "Long toString is incorrectly formatted",
                        "Wierd Al Yankovic, A Truly Bizarre Song by Weird Al "
                        + "(Weird Al's Great...",
                        pub.toString() );

    }
}
