import java.util.ArrayList;

/**
 * IntegerSet - Wrapper class for an ArrayList object.
 *
 * @author Patrick Muradaz
 * @version September 24, 2018
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
     * @param newInt the int to add
     */
    public void add( int newInt ) {

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
     * @return the int from the object at the index given
     */
    public int get( int index ) {

        int valToReturn = -1;

        if ( index >= 0 && index < size() ) {
            valToReturn = model.get( index );
        }

        return valToReturn;

    } // method get

    /**
     * size - return the size of the ArrayList.
     *
     * @return int
     */
    public int size() {

        return model.size();

    } // method size

    /******************************* private methods **********************/

    /**
     * append - add to the end of the list.
     *
     * @param newInt (the object to append)
     */
    private void append( int newInt ) {

        model.add( newInt );

    } // method append

    /**
     * insert - insert an Integer into the list at its proper position.
     *
     * @param newInt (the object to insert)
     */
    private void insert( int newInt ) {

        if ( newInt < model.get( model.size() - 1 ) ) {
            int index = 0;

            while ( newInt > ( model.get( index ) ) ) {
                index++;
            }

            model.add( index, newInt ); // insert Integer at index

        } else {
            append( newInt );
        }

    } // method insert

    /**
     * isLast - return true if the object is bigger than the last object in the
     * list.
     *
     * @param intToCompare an int to compare
     * @return boolean
     */
    private boolean isLast( int intToCompare ) {

        boolean isLast = true;

        if ( model.size() > 0 ) {
            isLast = intToCompare >= model.get( model.size() - 1 );
        }

        return isLast;

    } // method isLast
    
    /**
     * intersection - return an IntegerSet of all elements common between two 
     * IntegerSets.
     * 
     * @param incoming is the IntegerSet intersecting this IntegerSet
     * @return the new IntegerSet of all common elements
     */
    public IntegerSet intersection( IntegerSet incoming ) {
        IntegerSet newSet = new IntegerSet();
        
        for ( int i = 0; i < incoming.size(); i++ ) {
            if ( model.contains( incoming.get( i ) ) ) {
                newSet.add( incoming.get( i ) );
            }
        }
        
        return newSet;
    }
    
    /**
     * subset - returns true if the incoming IntegerSet is a subset of this
     * IntegerSet.
     * 
     * @param incoming is the IntegerSet to find within this IntegerSet
     * @return true if incoming is a subset of this IntegerSet
     */
    public boolean subset( IntegerSet incoming ) {
        boolean result = false;
        int count = 0;
        
        for ( int i = 0; i < incoming.size(); i++ ) {
            if ( model.contains( incoming.get( i ) ) ) {
                count++;
            }
        }
        
        if ( count == incoming.size() ) {
            result = true;
        }
        
        return result;
    }

} // class IntegerSet
