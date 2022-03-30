import java.io.IOException;

/**
* This class contains the main method for the program.
*
* Acknowledgments: I acknowledge that I have neither given nor
* received assistance for this assignment except as
* noted below:
*
* none
*
* Modifications: PDM 9/25/2018 Completed first version of class.
* PDM 9/26/2018 Completed final version of class.
*
* @author Patrick Muradaz
* @version PA01 (September 26, 2018)
*/
public class JMUTunes {
    
    /**
     * main method for the JMUTunes project.
     * 
     * @param args not used
     * @throws IOException the IOException
     */
    public static void main( String[] args ) throws IOException {
        AudioControl control = new AudioControl();
        control.start();
    }

}
