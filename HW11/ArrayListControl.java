import java.io.IOException;

/**
 * ArrayListControl - Control (driver) class for the ArrayList lab.
 *
 * Acknowledgements: none
 *
 * Modifications: **MLN 9/18/2018 - Modified to handle IntegerSets & set
 * operations
 * 
 * @author Michael Norton
 * @version 18 September 2018, 16 September 2018
 */
public class ArrayListControl {

    // ---------------------------------------------------------------------
    // Declarations
    // ---------------------------------------------------------------------
    private ArrayListView ui; // the UserInterface (view) object
    private IntegerSet set1; // the first set
    private IntegerSet set2; // the second set

    /**
     * constructor - init model & view objects and begin program.
     * 
     * @throws IOException the IOException
     */
    public ArrayListControl() throws IOException {

        set1 = new IntegerSet();
        set2 = new IntegerSet();
        ui = new ArrayListView();

    } // constructor

    /***************************** public methods *************************/

    /**
     * run - run the program.
     * 
     * @throws IOException the IOException
     */
    public void run() throws IOException {

        displayHeader();
        enterIntegers();
        displayList();

    } // run

    /*************************** private methods **************************/

    /**
     * displayHeader - show greeting.
     */
    private void displayHeader() {

        ui.display( "*****************************************************\n" );
        ui.display( "*                 IntegerSet Lab                    *\n" );
        ui.display( "*****************************************************\n" );

    } // method displayHeader

    /**
     * displayList - Note that I am just printing the Integer object here (what
     * is returned from the returnArrayList() method) - What is actually
     * occuring is that the toString() method of the Integer class is invoked -
     * this method returns the value of the int as a String.
     */
    private void displayList() {

        IntegerSet set;
        String isNot;

        ui.display( "\n" ); // print blank line

        set = set1.intersection( set2 );

        ui.display( "Intersection of Sets: " );

        for ( int i = 0; i < set.size(); i++ ) {
            ui.display( set.get( i ) + " " );

        } // end for

        ui.display( "\n\n" ); // add <CR> to end of prior with extra line

        isNot = set1.subset( set2 ) ? "is" : "is not";
        ui.display( "Set2 " + isNot + " a subset of Set1.\n" );

    } // method displayList

    /**
     * enterIntegers - enter integers and store them in Integer objects & add to
     * ArrayList.
     * 
     * @throws IOException the IOException
     */
    private void enterIntegers() throws IOException {

        String userString; // the value entered by the user

        for ( int i = 0; i < 2; i++ ) {
            ui.display( "\n" );
            String which = i == 0 ? "first" : "second";
            IntegerSet set = i == 0 ? set1 : set2;

            do {
                ui.display( "Enter an int for the " + which
                                + " set. 'Q' to quit: " );

                userString = ui.getInput();

                // if the user did not choose to quit
                if ( !isQuit( userString ) ) {
                    // check to see if the string is valid (will convert to
                    // int)
                    if ( isValidInt( userString ) ) {
                        // it's valid, so add this to the model
                        set.add( Integer.parseInt( userString ) );

                    } // end if

                } // end if

            }
            while ( !isQuit( userString ) ); // continue as long as the
                                             // user doesn't quit
        } // end for

    } // method enterIntegers

    /**
     * isQuit - return true if the user entered 'q'.
     * 
     * **MLN 9/19/2016 - fixed to handle null possibility
     *
     * @param s the String to test
     * @return true if 'q'
     */
    private boolean isQuit( String s ) {

        boolean isQuit = false;

        if ( s != null ) {
            isQuit = s.length() == 1 && s.toLowerCase().charAt( 0 ) == 'q';
        }

        return isQuit;

    } // method isQuit

    /**
     * isValidInt - return true if the user has entered valid int.
     * 
     * **MLN 9/19/2016 - fixed to handle null possibility
     *
     * @param s the String to test
     * @return boolean
     */
    private boolean isValidInt( String s ) {

        boolean retVal = false; // initialize return value to false

        if ( s != null ) {
            try {
                Integer.parseInt( s ); // not assigning here, just testing
                retVal = true; // valid int, so set to true

            } catch ( NumberFormatException e ) {
                System.out.println( "Invalid Entry. Please try again." );

            } // end catch
        }

        return retVal;

    } // method isValidInt

} // class ArrayListControl
