import java.io.IOException;

/**
 * Starter class for JMUTunes application.
 * 
 * Acknowledgments: I acknowledge that I have neither given nor
 * received assistance for this assignment except as
 * noted below:
 *
 * This was originally Dr. Norton's solution to PA01
 *
 * Modifications: For the record, this method is identical to my PA01 JMUTunes 
 * method
 * 
 * @author Patrick Muradaz
 * @version PA02 October 7, 2018
 */
public class JMUTunes {
    
    /**
     * Main method.
     * 
     * @param args the command line arguments
     * @throws IOException the IOException
     */
    public static void main( String[] args ) throws IOException {
        
        AudioControl control = new AudioControl();
        control.start();
    }
}
