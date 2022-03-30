import java.util.ArrayList;

/**
 * IntegerSet - Wrapper class for an ArrayList object.
 * 
 * Modifications: **MLN 9/18/2016 - Changed to IntegerSet - Modified add method
 * to enforce uniqueness - added intersection and subset methods
 *
 * @author Michael Norton
 * @version 18 September 2018, 16 September 2018
 */
public class IntegerSet {

    // ---------------------------------------------------------------------
    // Declarations
    // ---------------------------------------------------------------------
    private ArrayList< Integer > model;

    /**
     * constructor - init model object.
     */
    public IntegerSet() {

        model = new ArrayList<>();

    } // constructor model

    /**************************** public Methods *************************/

    /**
     * add - add an int to the collection (convert to Integer).
     * 
     * **MLN 9/19/2016 - added uniqueness condition (using contains() )
     *
     * @param newInt the int to add
     */
    public void add( int newInt ) {

        // 1. Test to see if list is empty or if the new item should be at
        // the end. Ensure it is not there first
        if ( !model.contains( newInt ) ) {
            if ( size() == 0 || isLast( newInt ) ) {
                append( newInt );
            } else {
                insert( newInt );
            }
        }

    } // method add

    /**
     * get - return a single ArrayList element.
     *
     * @param index - the index of the item to get
     * @return Integer
     */
    public int get( int index ) {

        int valToReturn = -1;

        if ( index >= 0 && index < size() ) {
            valToReturn = model.get( index );
        }

        return valToReturn;

    } // method get

    /**
     * intersection - return the intersection of 2 sets.
     * 
     * **MLN 9/19/2016 - new for HW08
     * 
     * @param other - the incoming set
     * @return the intersection of 2 sets
     */
    public IntegerSet intersection( IntegerSet other ) {

        IntegerSet result = new IntegerSet();

        if ( other != null ) {
            for ( int i = 0; i < other.size(); i++ ) {
                if ( model.contains( other.get( i ) ) ) {
                    result.add( other.get( i ) );
                }
            }
        }

        return result;

    } // method intersection

    /**
     * size - return the size of the ArrayList.
     *
     * @return int
     */
    public int size() {

        return model.size();

    } // method size

    /**
     * subset - returns true if incoming is a subset of the current set.
     * 
     * **MLN 9/19/2016 - new for HW08
     * 
     * @param other - the set to test
     * @return true if the incoming set is a subset of the current set
     */
    public boolean subset( IntegerSet other ) {

        boolean isSubset = true;

        if ( other != null && other.size() > 0 ) {
            for ( int i = 0; i < other.size() && isSubset; i++ ) {
                if ( !model.contains( other.get( i ) ) ) {
                    isSubset = false;
                }
            }
        }

        return isSubset;
    }
    
    /**
     * difference - returns an IntegerSet with all of the elements that are in
     * the incoming IntegerSet but not the source IntegerSet. 
     * 
     * @param incoming is the IntegerSet to be compared to source
     * @return an IntegerSet of all elements in incoming and not in source
     */
    public IntegerSet difference( IntegerSet incoming ) {
        IntegerSet newSet = new IntegerSet();
        
        if ( incoming != null ) {
            ArrayList< Integer > other = new ArrayList< Integer >();
            
            for ( int i = 0; i < incoming.size(); i++ ) {
                other.add( incoming.get( i ) );
            }
            
            for ( int i = 0; i < model.size(); i++ ) {
                if ( !other.contains( model.get( i ) ) ) {
                    newSet.add( model.get( i ) );
                }
            }
        }
        
        return newSet;
    }
    
    /**
     * union - returns an IntegerSet of all elements in incoming and source
     * without duplicates.
     * 
     * @param incoming the IntegerSet to unite with source
     * @return an IntegerSet of all elements in incoming and source
     */
    public IntegerSet union( IntegerSet incoming ) {
        IntegerSet newSet = new IntegerSet();
        
        for ( int i = 0; i < model.size(); i++ ) {
            newSet.add( model.get( i ) );
        }
        
        if ( incoming != null ) {
            for ( int i = 0; i < incoming.size(); i++ ) {
                if ( !model.contains( incoming.get( i ) ) ) {
                    newSet.add( incoming.get( i ) );
                }
            }
        }
        
        return newSet;
    }

    /******************************* private methods **********************/

    /**
     * append - add to the end of the list.
     *
     * @param newInt the int to append
     */
    private void append( int newInt ) {

        model.add( newInt );

    } // method append

    /**
     * insert - insert an Integer into the list at its proper position.
     *
     * @param newInt the int to insert
     */
    private void insert( int newInt ) {

        if ( newInt < model.get( model.size() - 1 ) ) {
            int index = 0;

            // locate the insert point
            while ( newInt > ( model.get( index ) ) ) {
                index++;
            }

            model.add( index, newInt ); // insert Integer at
                                        // index

        } else {
            append( newInt );
        }

    } // method insert

    /**
     * isLast - return true if the object is bigger than the last object in the
     * list.
     *
     * @param comparableInt - an int to compare
     * @return true if this is greater than the last value
     */
    private boolean isLast( int comparableInt ) {

        boolean isLast = true;

        if ( model.size() > 0 ) {
            isLast = comparableInt >= model.get( model.size() - 1 );
        }

        return isLast;

    } // method isLast

} // class IntegerSet
