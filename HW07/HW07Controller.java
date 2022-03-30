import java.io.IOException;

/**
* This class calls the Viewer class in main.
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
public class HW07Controller {

    /**
     * Main method to control the program.
     * 
     * @param args not used for this project
     * @throws IOException the IOExcecption
     */
    public static void main( String[] args ) throws IOException {
        ArrayListViewer control = new ArrayListViewer();
        
        control.greeting();
        control.getInput();
    }
}
