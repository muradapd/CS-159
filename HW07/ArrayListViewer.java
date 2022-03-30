import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
* This class allows the user to interact with the IntegerList.
*
* Acknowledgements: I acknowledge that I have neither given nor
* received assistance for this assignment except as
* noted below:
*
* None
*
* Modifications: PDM 9/17/2018 Completed class.
*
* @author Patrick Muradaz
* @version HW07 (September 17, 2018)
*/
public class ArrayListViewer {

    private BufferedReader reader;
    private Integer integer;
    private IntegerList integerList;
    
    /**
     * Default constructor.
     */
    public ArrayListViewer() {
        reader = new BufferedReader( new InputStreamReader( 
                System.in ) );
        integer = 0;
        integerList = new IntegerList();
    }
    
    /**
     * Prints out a standard greeting to the screen.
     */
    public void greeting() {
        System.out.println( "Welcome!\nTo quit input: 'Q'\nOtherwise:\n" );
    }
    
    /**
     * Prompts the user for input and reads it in.
     * 
     * @throws IOException the IOException
     */
    public void getInput() throws IOException {
        String input = "A";
                
        while ( !input.equals( "Q" ) ) {
            System.out.print( "Please input a whole number -> " );
            input = reader.readLine().toUpperCase();
            
            try {
                integer = Integer.parseInt( input );
                addToList();
            } catch ( NumberFormatException nfe ) {
                if ( !input.equals( "Q" ) ) {
                    System.out.println( "Try again.\n" );
                } else {
                    System.out.println( "\nYou quit." );
                }
                    
            }
            
            
        }
        System.out.println( "Slots used: " + integerList.getSize() );
        System.out.println( "You entered: " + integerList );
    }
    
    /**
     * Adds user input to the IntegerList.
     */
    public void addToList() {
        integerList.add( integer );
    }
}
