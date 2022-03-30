import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * IntegerSetTest.
 * 
 * @author Michael Norton
 * @version 9/18/2017
 */
public class IntegerSetTest {

    private IntegerSet source = new IntegerSet();
    private IntegerSet other = new IntegerSet();
    private IntegerSet result = new IntegerSet();

    /**
     * Setup method.
     */
    @Before
    public void setup() {

        source = new IntegerSet();
        other = new IntegerSet();
        result = new IntegerSet();
    }

    /**
     * Test add with no duplicates.
     */
    @Test
    public void testAddNoDuplicates() {

        source.add( 3 );
        source.add( 5 );
        source.add( 9 );
        source.add( 1 );
        source.add( 12 );
        source.add( 6 );

        assertTrue( source.size() == 6 );
    }

    /**
     * Test add with duplicates.
     */
    @Test
    public void testAddWithDuplicates() {

        source.add( 3 );
        source.add( 5 );
        source.add( 3 );
        source.add( 5 );
        source.add( 5 );
        source.add( 1 );

        assertTrue( source.size() == 3 );
    }

   /**
    * Test ordering.
    */
    @Test
    public void testAddingInOrder() {

        boolean inOrder = true;

        source.add( 3 );
        source.add( 5 );
        source.add( 9 );
        source.add( 1 );
        source.add( 12 );
        source.add( 6 );

        for ( int i = 0; i < source.size() - 1 && inOrder; i++ ) {
            if ( source.get( i ) > source.get( i + 1 ) ) {
                inOrder = false;
            }
        }

        assertTrue( inOrder );
    }

    /**
     * Test intersection with empty source.
     */
    @Test
    public void testIntersectionEmptySource() {

        other.add( 3 );
        other.add( 5 );
        result = source.intersection( other );

        assertTrue( result.size() == 0 );
    }

    /**
     * Test intersection with empty other.
     */
    @Test
    public void testIntersectionEmptyOther() {

        source.add( 3 );
        source.add( 5 );
        result = source.intersection( other );

        assertTrue( result.size() == 0 );
    }

    /**
     * Test intersection with empty source and other.
     */
    @Test
    public void testIntersectionEmptyBoth() {

        result = source.intersection( other );

        assertTrue( result.size() == 0 );
    }

    /**
     * Test valid intersection.
     */
    @Test
    public void testIntersectionValid() {

        source.add( 3 );
        source.add( 5 );
        other.add( 7 );
        other.add( 5 );
        result = source.intersection( other );

        assertTrue( result.size() == 1 );
    }

    /**
     * Test intersection with no match.
     */
    @Test
    public void testIntersectionNoMatch() {

        source.add( 3 );
        source.add( 5 );
        other.add( 7 );
        other.add( 9 );
        result = source.intersection( other );

        assertTrue( result.size() == 0 );
    }

    /**
     * Test valid subset.
     */
    @Test
    public void testSubsetGood() {

        source.add( 3 );
        source.add( 5 );
        source.add( 7 );
        other.add( 7 );
        other.add( 3 );

        assertTrue( source.subset( other ) );

    }

    /**
     * Test invalid subset.
     */
    @Test
    public void testSubsetNotGood() {

        other.add( 3 );
        other.add( 5 );
        other.add( 7 );
        source.add( 7 );
        source.add( 3 );

        assertFalse( source.subset( other ) );

    }

    /**
     * Test subset with empty source.
     */
    @Test
    public void testSubsetSourceEmpty() {

        other.add( 3 );
        other.add( 5 );
        other.add( 7 );

        assertFalse( source.subset( other ) );
    }

    /**
     * Test subset with empty other.
     */
    @Test
    public void testSubsetOtherEmpty() {

        source.add( 3 );
        source.add( 5 );
        source.add( 7 );

        assertTrue( source.subset( other ) );
    }

    /**
     * Test subset with empty source and other.
     */
    @Test
    public void testSubsetBothEmpty() {

        assertTrue( source.subset( other ) );
    }
    
    /**
     * Test difference with empty source.
     */
    @Test
    public void testDifferenceEmptySource() {

        other.add( 3 );
        other.add( 5 );
        result = source.difference( other );

        assertTrue( result.size() == 0 );
    }

    /**
     * Test difference with empty other.
     */
    @Test
    public void testDifferenceEmptyOther() {

        source.add( 3 );
        source.add( 5 );
        result = source.difference( other );

        assertTrue( result.size() == 2 );
    }

    /**
     * Test difference with empty source and other.
     */
    @Test
    public void testDifferenceEmptyBoth() {

        result = source.difference( other );

        assertTrue( result.size() == 0 );
    }

    /**
     * Test valid difference.
     */
    @Test
    public void testDifferenceValid() {

        source.add( 3 );
        source.add( 5 );
        source.add( 4 );
        other.add( 7 );
        other.add( 5 );
        other.add( 1 );
        result = source.difference( other );

        assertTrue( result.size() == 2 );
    }

    /**
     * Test difference with no match.
     */
    @Test
    public void testDifferenceNoMatch() {

        source.add( 3 );
        source.add( 5 );
        other.add( 7 );
        other.add( 9 );
        result = source.difference( other );

        assertTrue( result.size() == 2 );
    }
    
    /**
     * Test union with empty source.
     */
    @Test
    public void testUnionEmptySource() {

        other.add( 3 );
        other.add( 5 );
        result = source.union( other );

        assertTrue( result.size() == 2 );
    }

    /**
     * Test union with empty other.
     */
    @Test
    public void testUnionEmptyOther() {

        source.add( 3 );
        source.add( 5 );
        result = source.union( other );

        assertTrue( result.size() == 2 );
    }

    /**
     * Test union with empty source and other.
     */
    @Test
    public void testUnionEmptyBoth() {

        result = source.union( other );

        assertTrue( result.size() == 0 );
    }

    /**
     * Test valid union.
     */
    @Test
    public void testUnionValid() {

        source.add( 3 );
        source.add( 5 );
        other.add( 7 );
        other.add( 5 );
        result = source.union( other );

        assertTrue( result.size() == 3 );
    }

    /**
     * Test union with no match.
     */
    @Test
    public void testUnionNoMatch() {

        source.add( 3 );
        source.add( 5 );
        other.add( 7 );
        other.add( 9 );
        result = source.union( other );

        assertTrue( result.size() == 4 );
    }
    
}
