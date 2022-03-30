import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * ArrayListView - User Interface for the ArrayList lab.
 *
 * @author Patrick Muradaz
 * @version September 24, 2018
 */
public class ArrayListView {

    // ---------------------------------------------------------------------
    // Declarations
    // ---------------------------------------------------------------------
    private BufferedReader reader; // the reader object

    /**
     * constructor ArrayListView.
     *
     * @throws IOException the IOException
     */
    public ArrayListView() throws IOException {

        reader = new BufferedReader( new InputStreamReader( System.in ) );
    }

    /**************************** public Methods *************************/

    /**
     * displayToScreen - display the content of the parameter to the screen.
     *
     * @param s the String to display
     */
    public void display( String s ) {

        System.out.print( s );

    } // method display

    /**
     * getInput - get user input.
     *
     * @return the String entered
     * @throws IOException the IOException
     */
    public String getInput() throws IOException {

        return reader.readLine();

    } // method getInput

} // class ArrayListView
