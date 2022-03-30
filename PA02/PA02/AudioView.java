import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Class to handle User I/O.
 * 
 * Acknowledgments: I acknowledge that I have neither given nor
 * received assistance for this assignment except as
 * noted below:
 *
 * This was originally Dr. Norton's solution to PA01
 *
 * Modifications: 
 * 
 * @author Patrick Muradaz
 * @version PA02 October 7, 2018
 */
public class AudioView {
    
    public static final int SCREEN_DEPTH = 25;
    public static final int SCREEN_WIDTH = 80;
    public static final String NL = "\n";
    public static final String SPACE = " ";
    
    private BufferedReader reader;
    
    /**
     * Default constructor.
     */
    public AudioView() {
        
        reader = new BufferedReader( new InputStreamReader( System.in ) );
    }
    
    /**
     * Center the string in the screen.
     * 
     * @param str the string to center
     * @return the number of spaces indented
     */
    public int center( String str ) {
        
        int spaces = ( SCREEN_WIDTH - str.length() ) / 2;
        
        for ( int i = 0; i < spaces; i++ ) {
            
            display( SPACE );
        }
        
        display( str );
        
        return spaces;
    }
    
    /**
     * Clear the screen by printing LIMIT number of newlines.
     */
    public void clearScreen() {
        
        for ( int i = 0; i < SCREEN_DEPTH; i++ ) {
            
            display( NL );
        }
    }
    
    /**
     * Display the incoming string.
     * 
     * @param str the incoming string
     */
    public void display( String str ) {
        
        System.out.print( str );
    }
     
    /**
     * Get input from the user.
     * 
     * @return whatever the user enters as a string
     * @throws IOException the IOException
     */
    public String getInput() throws IOException {
        
        return reader.readLine();
    }
    
    /**
     * Indent the number of specified spaces and then print the string.
     * 
     * @param spaces the number of spaces to indent
     * @param str the String to print
     */
    public void indent( int spaces, String str ) {
        
        for ( int i = 0; i < spaces; i++ ) {
            
            display( SPACE );
        }
        
        display( str );
    }
    
    /**
     * Print the message and pause the screen - wait for RETURN/ENTER.
     * 
     * @param message the message to print
     * @throws IOException the IOException
     */
    public void pause( String message ) throws IOException {
        
        display( message );
        getInput();
    }
}
