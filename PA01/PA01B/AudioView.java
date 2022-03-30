import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
* This class handles all interaction between the user and the program.
*
* Acknowledgments: I acknowledge that I have neither given nor
* received assistance for this assignment except as
* noted below:
*
* none
*
* Modifications: PDM 9/25/2018 Completed first version of class.
* PDM 9/25/2018 Completed final version of class.
*
* @author Patrick Muradaz
* @version PA01 (September 26, 2018)
*/
public class AudioView {

    private BufferedReader reader;
    
    /**
     * Constructor for the AudioView class.
     */
    public AudioView() {
        reader = new BufferedReader( new InputStreamReader( System.in ) );
    }
    
    /**
     * Prints input String str to the screen.
     * 
     * @param str is the input String
     */
    public void display( String str ) {
        System.out.print( str );
    }
    
    /**
     * Get input from the user.
     * 
     * @return what the user enters as a String
     * @throws IOException the IOException
     */
    public String getInput() throws IOException {
        String input;
        
        input = reader.readLine();
        
        return input;
    }
    
    /**
     * Prints a message and waits for the user to press the enter/return key.
     * 
     * @param message is the message to print out
     * @throws IOException the IOException
     */
    public void pause( String message ) throws IOException {
        String input;
        
        System.out.print( message + "\nPress <ENTER/RETURN> to continue..." );
        input = reader.readLine();
        
        if ( input.equals( "" ) ) {
            clearScreen();
        } else {
            pause( message );
        }
    }
    
    /**
     * Prints 25 blank lines to the screen to clear the original screen.
     */
    public void clearScreen() {
        System.out.print( "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
                + "\n\n\n\n" );
    }
}
