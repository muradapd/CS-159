import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Test the AudioList Class.
 * 
 * Acknowledgments: I acknowledge that I have neither given nor
 * received assistance for this assignment except as
 * noted below:
 *
 * This was originally Dr. Norton's solution to PA01
 *
 * Modifications: PDM 10/9/2018 Added new AudioFiles; edited testAdd and 
 * testDelete methods; added testContains, testDifference, testIntersection,
 * testSubset and testUnion methods.
 *
 * @author Patrick Muradaz
 * @version PA02 October 10, 2018
 */
public class AudioListTest {


    private AudioFile allNulls = new AudioFile( null, null, null, -1 );
    private AudioFile badAuthorNull = new AudioFile( null, "title", 
                    null, 2 );
    private AudioFile goodFile1 = new AudioFile( "artist", "title1",
                    "album", 98 );
    private AudioFile goodFile2 = new AudioFile( "different", "title",
                    "album", 99 );
    private AudioFile goodFile3 = new AudioFile( "artist", "different",
                    "album", 99 );
    private AudioFile goodFile4 = new AudioFile( "artist", "title",
                    "different", 99 );
    private AudioFile goodFile5 = new AudioFile( "artist", "title",
                    "album", 1 );

    /**
     * Test the add function. Make sure that invalid items are not added.
     */
    @Test
    public void testAdd() {

        AudioList list = new AudioList();

        assertEquals( "AudioList should be empty before adding first item",
                        0, list.size() );

        // Add a good file
        list.add( goodFile1 );
        
        // Can the file be added again? (should be false)
        assertFalse( "Should not add duplicate AudioFiles", 
                        list.add( goodFile1 ) );
        
        // Add 4 different items
        list.add( goodFile2 );
        list.add( goodFile3 );
        list.add( goodFile4 );
        list.add( goodFile5 );

        // Add 2 bad items
        list.add( allNulls );
        list.add( badAuthorNull );

        // Check size (should be 5)
        assertEquals( "AudioList size should be 5 after adding 5 good, 1 "
                        + "duplicate and 2 bad files", 5, list.size() );
        
        // Check if files are in alphabetical order
        assertEquals( "AudioList should be in alphabetical order - 1", 
                        goodFile5, list.get( 0 ) );
        assertEquals( "AudioList should be in alphabetical order - 2", 
                        goodFile1, list.get( 1 ) );
        assertEquals( "AudioList should be in alphabetical order - 3", 
                        goodFile3, list.get( 2 ) );
        assertEquals( "AudioList should be in alphabetical order - 4", 
                        goodFile4, list.get( 3 ) );
        assertEquals( "AudioList should be in alphabetical order - 5", 
                        goodFile2, list.get( 4 ) );
    }
    
    /**
     * Test the contains function.
     */
    @Test
    public void testContains() {

        AudioList list = new AudioList();
        
        assertFalse( "AudioList shouldn't contain the specified AudioFile",
                        list.contains( goodFile1 ) );
        
        // Add the specified AudioFile
        list.add( goodFile1 );
        
        // Check if list contains specified AudioFile (should be true)
        assertTrue( "AudioList should contain the specified AudioFile",
                        list.contains( goodFile1 ) );
    }

    /**
     * Test the delete function.
     */
    @Test
    public void testDelete() {

        AudioList list = new AudioList();

        assertEquals( "AudioList should be empty before adding first file",
                        0, list.size() );

        // Add 5 good items
        list.add( goodFile1 );
        list.add( goodFile2 );
        list.add( goodFile3 );
        list.add( goodFile4 );
        list.add( goodFile5 );
        
        assertEquals( "AudioList size should be 5 after adding 5 good files", 
                        5, list.size() );
        
        assertFalse( "delete should return false if the value of whichOne is"
                        + " invalid", list.delete( 5 ) );

        // Remove AudioFile at index 1
        list.delete( 1 );
        
        assertEquals( "AudioList size should be 4 after deleting",
                        4, list.size() );
        
        // AudioFile at index 1 should be different (should be goodFile1)
        assertEquals( "if deleting at index 1, AudioFile at index 1 should be "
                        + "the next alphabetical AudioFile after deletion", 
                        goodFile3, list.get( 1 ) );

    }
    
    /**
     * Test the difference function.
     */
    @Test
    public void testDifference() {

        AudioList current = new AudioList();
        AudioList other = new AudioList();
           
        // Add files to the AudioLists
        current.add( goodFile1 );
        current.add( goodFile2 );
        current.add( goodFile3 );
        
        other.add( goodFile3 );
        other.add( goodFile4 );
        other.add( goodFile5 );
        
        AudioList difference = current.difference( other );
        
        assertEquals( "Size of difference should be 2 after finding "
                + "the difference between two lists, 3 files each, with "
                + "1 similarity", 2, difference.size() );
        
        // Check if correct files are in correct places
        assertEquals( "Difference not working - 1", 
                        goodFile1, difference.get( 0 ) );
        assertEquals( "Difference not working - 2", 
                        goodFile2, difference.get( 1 ) );
    }

    /**
     * Test get method to handle valid and invalid indices - Added after 
     * the fact.
     */
    @Test
    public void testGet() {
        
        AudioList list = new AudioList();

        // Add 5 good items
        list.add( goodFile1 );
        list.add( goodFile2 );
        list.add( goodFile3 );
        list.add( goodFile4 );
        list.add( goodFile5 );
        
        assertNotNull( "Get valid index should not be null", list.get( 0 ) );
        assertNotNull( "Get valid index should not be null", list.get( 4 ) );
        assertNull( "Get index -1 should return null", list.get( -1 ) );
        assertNull( "Get index size() should return null", 
                        list.get( list.size() ) );
    }
    
    /**
     * Test the intersection function.
     */
    @Test
    public void testIntersection() {

        AudioList current = new AudioList();
        AudioList other = new AudioList();
        
        // Add files to the AudioLists
        current.add( goodFile1 );
        current.add( goodFile2 );
        current.add( goodFile3 );
        current.add( goodFile4 );
        
        other.add( goodFile2 );
        other.add( goodFile3 );
        other.add( goodFile4 );
        other.add( goodFile5 );
        
        AudioList intersection = current.intersection( other );
        
        assertEquals( "Intersection size should be 3 after intersecting two "
                + "AudioLists with 3 similarities", 3, 
                        intersection.size() );
        
        // Check if correct files are in correct places
        assertEquals( "Intersection not working - 1", 
                        goodFile3, intersection.get( 0 ) );
        assertEquals( "Intersection not working - 2", 
                        goodFile4, intersection.get( 1 ) );
        assertEquals( "Intersection not working - 2", 
                        goodFile2, intersection.get( 2 ) );
    }
    
    /**
     * Test the subset function.
     */
    @Test
    public void testSubset() {

        AudioList current = new AudioList();
        AudioList other = new AudioList();
        
        // Add files to the AudioLists
        current.add( goodFile1 );
        current.add( goodFile2 );
        current.add( goodFile3 );
        current.add( goodFile4 );
        
        other.add( goodFile2 );
        other.add( goodFile3 );
        
        assertTrue( "Subset is true if all files in the current AudioList "
                        + "are also in the other AudioList", 
                        current.subset( other ) );
    }
    
    /**
     * Test the union function.
     */
    @Test
    public void testUnion() {

        AudioList current = new AudioList();
        AudioList other = new AudioList();
        
        // Add files to the AudioLists
        current.add( goodFile1 );
        current.add( goodFile2 );
        current.add( goodFile3 );
        
        other.add( goodFile3 );
        other.add( goodFile4 );
        other.add( goodFile5 );
        
        AudioList union = current.union( other );
        
        assertEquals( "Size of union should be 5 after merging 6 files with "
                + "1 duplicate", 5, union.size() );
        
        // Check if correct files are in correct places
        assertEquals( "Union not working - 1", 
                        goodFile5, union.get( 0 ) );
        assertEquals( "Union not working - 2", 
                        goodFile1, union.get( 1 ) );
        assertEquals( "Union not working - 3", 
                        goodFile3, union.get( 2 ) );
        assertEquals( "Union not working - 4", 
                        goodFile4, union.get( 3 ) );
        assertEquals( "Union not working - 5", 
                        goodFile2, union.get( 4 ) );
    }
}
