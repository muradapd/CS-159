import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Class to handle all file reading and writing.
 * 
 * Acknowledgments: I acknowledge that I have neither given nor
 * received assistance for this assignment except as
 * noted below:
 *
 * Got help from HW05 10/10/2018
 *
 * Modifications: PDM 10/8/2018 Created class; added constructors; added close, 
 * open, readLine, and write methods.
 * 
 * @author Patrick Muradaz
 * @version PA02 October 10, 2018
 */
public class AudioFileIO {

    public static final int READER = 0;
    public static final int WRITER = 1;
    
    private BufferedReader fileReader;
    private PrintWriter fileWriter;
    private File file;
    
    /**
     * Default constructor.
     */
    public AudioFileIO() {
        
        file = new File( "AudioList.txt" );
    
    }
    
    /**
     * Explicit value constructor.
     * Creates a File object for the String sent via the parameter.
     * 
     * @param fileName is the name of the file to be created.
     */
    public AudioFileIO( String fileName ) {
        
        file = new File( fileName );
    
    }
    
    /**
     * Closes the the specified READER / WRITER.
     * 
     * @param whichOne is which operator to close.
     * @throws IOException the IOException
     */
    public void close( int whichOne ) throws IOException {
        
        if ( whichOne == READER ) {
            
            fileReader.close();
            
        } else if ( whichOne == WRITER ) {
            
            fileWriter.close();
        }
    }

    /**
     * Opens the specified READER / WRITER for the file represented by the File
     * object.
     * 
     * @param whichOne is which operator to open.
     * @return true if the file was able to be opened, false otherwise.
     * @throws IOException the IOException
     */
    public boolean open( int whichOne ) throws IOException {
        
        boolean isOpen = false;
        
        if ( whichOne == WRITER ) {
            
            fileWriter = new PrintWriter( new FileWriter( file ) );
            isOpen = true;
            
        } 

        if ( whichOne == READER ) {
            
            if ( file.exists() && file.canRead() ) {
            
                fileReader = new BufferedReader( new FileReader( file ) );
                isOpen = true;
                
            }
        }
        
        return isOpen;
    }
    
    /**
     * Reads a single line from the file represented by the File object.
     * 
     * @return the line read from the file.
     * @throws IOException the IOException
     */
    public String readLine() throws IOException {
        
        return fileReader.readLine();
        
    }
    
    /**
     * Writes a String to the file represented by the File object.
     * 
     * @param line is the String to be written.
     * @throws IOException the IOException.
     */
    public void write( String line ) throws IOException {
        
        fileWriter.println( line );
        
    }
}
