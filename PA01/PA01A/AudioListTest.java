import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
* This tests the AudioList class.
*
* Acknowledgements: I acknowledge that I have neither given nor
* received assistance for this assignment except as
* noted below:
*
* Dr. Norton 9/19, 9/21
*
* Modifications: PDM 9/14/2018 Completed first versions of tests.
*
* @author Patrick Muradaz
* @version PA01 (September 17, 2018)
* 
*/
public class AudioListTest {

    // valid test audio file
    private AudioFile testFile1 = new AudioFile( "Author", 
            "Title", "Album", 10 );
        
    // empty test audio file invalid track
    private AudioFile testFile2 = new AudioFile( "", 
            "", "", 0 );

    // null test audio file invalid track
    private AudioFile testFile3 = new AudioFile( null, null, null, 100 );
    
    // AudioLists
    private AudioList list1 = new AudioList();
    private AudioList list2 = new AudioList();
    private AudioList list3 = new AudioList();
    private AudioList list4 = new AudioList();
    
    /**
     * populates a n AudioList.
     * 
     * @param list the list given to populate
     */
    public void populate( AudioList list ) {
        for ( int i = 0; i < 17; i++ ) {
            list.add( testFile1 );
        }
    } 
    
    /**
     * Official test emulator.
     */
    @Test
    public void officialTest() {
        AudioList listA = new AudioList();
        listA.add( testFile1 );
        listA.add( testFile1 );
        listA.add( testFile1 );
        listA.add( testFile1 );
        listA.add( testFile1 );
        listA.add( testFile2 );
        listA.add( testFile3 );
        
        assertEquals( 5, listA.size() );
    }
    
    /**
     * Tests the add() method.
     */
    @Test
    public void testAdd() {
        System.out.println( list1.size() );
        // test if add returns true correctly
        assertTrue( list1.add( testFile1 ) );
        System.out.println( list1.get( 0 ) );
        System.out.println( list1.size() );
        
        // test if add returns false correctly
        assertFalse( list1.add( testFile2 ) );
        assertFalse( list1.add( testFile3 ) );
        
        // test if add adds an element correctly
        int expected = 1;
        
        assertEquals( expected, list1.size() );
        
        AudioFile expect = testFile1;
        System.out.println( list1.get( 0 ) );
        System.out.println( list1.get( 1 ) );
        System.out.println( list1.get( 2 ) );
        
        assertEquals( expect, list1.get( 0 ) );
        
        assertNull( list1.get( -1 ) );
        assertNull( list1.get( 1 ) );
        
        // test doubling capacity capability
        expected = 32;
        populate( list2 );
        
        assertEquals( expected, list2.capacity() );
    }
    
    /**
     * Tests the capacity() method.
     */
    @Test
    public void testCapacity() {
        
        // test size of the AudioList
        int expected = 16;
        
        assertEquals( expected, list3.capacity() );
    }
    
    /**
     * Tests the deleteLast() method.
     */
    @Test
    public void testDeleteLast() {
        
        // test if deleteLast is returning true correctly
        list4.add( testFile1 );
        
        assertTrue( list4.deleteLast() );
        
        // test if deleteLast is working correctly
        int expect = 0;
        
        assertEquals( expect, list4.size() );
        assertNull( list4.get( list4.size() + 1 ) );
    }
}
