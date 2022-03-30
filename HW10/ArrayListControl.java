import java.io.IOException;

/**
 * ArrayListControl - Control (driver) class for the ArrayList lab.
 *
 * @author Patrick Muradaz
 * @version September 24, 2018
 */
public class ArrayListControl {

    // ---------------------------------------------------------------------
    // Declarations
    // ---------------------------------------------------------------------
    private IntegerSet model1; // the first model1 object
    private IntegerSet model2; // the second model1 object
    private ArrayListView ui; // the UserInterface (view) object

    /**
     * constructor - init model1 & view objects and begin program.
     * 
     * @throws IOException the IOException
     */
    public ArrayListControl() throws IOException {

        model1 = new IntegerSet();
        model2 = new IntegerSet();
        ui = new ArrayListView();

    } // constructor

    /***************************** public methods **************************/

    /**
     * run - run the program.
     * 
     * @throws IOException the IOException
     */
    public void run() throws IOException {

        displayHeader();
        enterFirstIntegers();
        enterSecondIntegers();
        displayFirstList();
        displaySecondList();
        runIntersection();
        runSubset();

    } // run

    /*************************** private methods **************************/

    /**
     * displayHeader - show greeting.
     */
    private void displayHeader() {

        ui.display( "*****************************************************\n" );
        ui.display( "*                 ArrayList Lab                     *\n" );
        ui.display( "*****************************************************\n" );

    } // method displayHeader

    /**
     * displayFirstList - Display the list and print the number of used slots.
     */
    private void displayFirstList() {

        ui.display( "\nmodel1 input:\n" ); // print blank line

        if ( model1.size() > 0 ) {
            for ( int i = 0; i < model1.size(); i++ ) {
                ui.display( model1.get( i ) + "\n" );

            } // end for
        }

        ui.display( "\n" ); // add newline to end of list 

        // print stats at end
        ui.display( "Used slots   = " + model1.size() + "\n" );

    } // method displayFirstList
    
    /**
     * displaySecondList - Display the list and print the number of used slots.
     */
    private void displaySecondList() {

        ui.display( "\nmodel2 input:\n" ); // print blank line

        if ( model2.size() > 0 ) {
            for ( int i = 0; i < model2.size(); i++ ) {
                ui.display( model2.get( i ) + "\n" );

            } // end for
        }

        ui.display( "\n" ); // add newline to end of list 

        // print stats at end
        ui.display( "Used slots   = " + model2.size() + "\n" );

    } // method displaySecondList

    /**
     * enterFirstIntegers - enter integers and add to the IntegerSet.
     * 
     * @throws IOException the IOException
     */
    private void enterFirstIntegers() throws IOException {

        int newInt = Integer.MAX_VALUE; // the integer object to add
        String userString = "";         // the value entered by the user

        do {
            ui.display( "First IntegerSet.\nPlease enter a valid int value. "
                    + "Press 'Q' to quit: " );
            userString = ui.getInput();

           // if the user did not enter q
            if ( !isQuit( userString ) ) {

                if ( isValidInt( userString ) ) {
                    newInt = Integer.parseInt( userString );
                    model1.add( newInt );

                } // end if

            } // end if

        } while ( !isQuit( userString ) ); // continue as long as the
                                           // user doesn't quit

    } // method enterFirstIntegers
    
    /**
     * enterSecondIntegers - enter integers and add to the IntegerSet.
     * 
     * @throws IOException the IOException
     */
    private void enterSecondIntegers() throws IOException {

        int newInt = Integer.MAX_VALUE; // the integer object to add
        String userString = "";         // the value entered by the user

        do {
            ui.display( "Second IntegerSet.\nPlease enter a valid int value. "
                    + "Press 'Q' to quit: " );
            userString = ui.getInput();

           // if the user did not enter q
            if ( !isQuit( userString ) ) {

                if ( isValidInt( userString ) ) {
                    newInt = Integer.parseInt( userString );
                    model2.add( newInt );

                } // end if

            } // end if

        } while ( !isQuit( userString ) ); // continue as long as the
                                           // user doesn't quit

    } // method enterSecondIntegers

    /**
     * isQuit - return true if the user entered 'q'.
     *
     * @param s the String to test
     * @return true if the user entered 'q'
     */
    private boolean isQuit( String s ) {

        return s.length() == 1 && s.toLowerCase().charAt( 0 ) == 'q';

    } // method isQuit

    /**
     * isValidInt - return true if the user has entered valid int.
     *
     * @param s the String to test
     * @return true if the int is valid
     */
    private boolean isValidInt( String s ) {

        boolean isValid = false; // initialize return value to false

        try {
            Integer.parseInt( s ); // not assigning here, just testing
            isValid = true; // valid int, so set to true

        } catch ( NumberFormatException e ) {
            System.out.println( "Invalid Entry. Please try again." );

        } // end catch

        return isValid;

    } // method isValidInt
    
    /**
     * runIntersection - runs the IntegerSet method intersection.
     */
    private void runIntersection() {
        ui.display( "\nrunIntersection:\n" ); // print blank line

        if ( model1.intersection( model2 ).size() > 0 ) {
            for ( int i = 0; i < model1.intersection( model2 ).size(); i++ ) {
                ui.display( model1.intersection( model2 ).get( i ) + "\n" );

            } // end for
        }

        ui.display( "\n" ); // add newline to end of list 
    }
    
    /**
     * runSubset - runs the IntegerSet method subset.
     */
    private void runSubset() {
        String s = "false";
        
        if ( model1.subset( model2 ) ) {
            s = "true";
        }
        
        ui.display( s );
    }

} // class ArrayListControl
