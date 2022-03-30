import java.io.IOException;

/**
* This class controls the action of the program as a whole.
*
* Acknowledgments: I acknowledge that I have neither given nor
* received assistance for this assignment except as
* noted below:
*
* Dr Norton 9/26
*
* Modifications: PDM 9/25/2018 Completed first version of class.
* PDM 9/26/2018 Completed final version of class.
*
* @author Patrick Muradaz
* @version PA01 (September 26, 2018)
*/
public class AudioControl {

    private AudioView userIO;
    private AudioList audioList;
    private boolean run;
    private int page;
    
    /**
     * Constructor for the AudioControl class.
     */
    public AudioControl() {
        userIO = new AudioView();
        audioList = new AudioList();
        run = true;
        page = 0;
    }
    
    /**
     * Starts the program.
     * @throws IOException the IOException
     */
    public void start() throws IOException {
        displayHomePage( audioList, page );
                
        while ( run ) {
            String input = userIO.getInput();
            act( input );
        }
    }
    
    /**
     * Prints the JMUTunes home page.
     * 
     * @param list is the AudioList being used
     * @param start is the position to start the screen at
     */
    public void displayHomePage( AudioList list, int start ) {
        userIO.display( String.format( "%30s%s", "", 
                "JMUTunes Audio Player\n" ) );
        userIO.display( String.format( "%32s%s", "", "CS159 (Fall 2018)" ) );
        userIO.display( "\n\n" );
                
        for ( int i = start; i <= start + 15; i++ ) {
            if ( list.get( i ) != null ) {
                userIO.display( "\n" + String.format( "%2d", i + 1 ) + ". " 
                        + list.get( i ).toString() );
            } else {
                userIO.display( "\n" + String.format( "%2d", i + 1 ) 
                        + "." );
            }
        }
        
        userIO.display( "\n\n" );
        userIO.display( String.format( "%7s%s", "", "(A)dd, (E)dit, (D)elete, "
                + "(S)earch, (P)lay, (N)ext, (B)ack, (Q)uit." ) );
        userIO.display( "\n\n" );
        userIO.display( String.format( "%7s%s", "", "Choose Operation -> " ) );
    }
    
    /**
     * Decides what to do with user input.
     * 
     * @param input is the input from the user
     * @throws IOException the IOException
     */
    public void act( String input ) throws IOException {
        String testInput = input.toUpperCase();
                
        if ( testInput.equals( "A" ) ) {
            add();
        } else if ( testInput.equals( "E" ) ) {
            edit();
        } else if ( testInput.equals( "D" ) ) {
            delete();
        } else if ( testInput.equals( "S" ) ) {
            search();
        } else if ( testInput.equals( "P" ) ) {
            play();
        } else if ( testInput.equals( "N" ) ) {
            nextPage();
        } else if ( testInput.equals( "B" ) ) {
            pagePage();
        } else if ( testInput.equals( "Q" ) ) {
            quit();
        } else {
            userIO.display( "\n" );
            userIO.display( String.format( "%7s%s", "", "Incorrect entry "
                    + "(" + input + ")." ) );
            userIO.display( "\n" );
            userIO.display( String.format( "%7s%s", "", 
                    "Choose Operation -> " ) );
        }
    }
    
    /**
     * Adds an AudioFile to the AudioList.
     * 
     * @throws IOException the IOException
     */
    public void add() throws IOException {
        userIO.clearScreen();
        userIO.display( String.format( "%30s%s", "", "Add Audio File" ) );
        userIO.display( "\n\n\n" );
        
        userIO.display( "Artist: " );
        String artist = userIO.getInput();
        
        while ( artist.equals( "" ) ) {
            userIO.display( "\n" );
            userIO.display( "Required entry.\n" );
            userIO.display( "Artist: " );
            artist = userIO.getInput();
        }
        
        userIO.display( "Title: " );
        String title = userIO.getInput();
                
        while ( title.equals( "" ) ) {
            userIO.display( "\n" );
            userIO.display( "Required entry.\n" );
            userIO.display( "Title: " );
            title = userIO.getInput();
        }
        
        userIO.display( "Album: " );
        String album = userIO.getInput();
        
        userIO.display( "Track: " );
        int track = 0;
        String input = "null";
        
        while ( track != -1 && track < 1 || track > 99 ) {
            try {
                input = userIO.getInput();
                
                if ( input.equals( "" ) ) {
                    track = -1;
                } else {
                
                    track = Integer.parseInt( input );
                
                    if ( track < 1 || track > 99 ) {
                        userIO.display( "\n" );
                        userIO.display( "Must be a number between "
                                + "1 and 99.\n" );
                        userIO.display( "Track: " );
                    }
                }
            } catch ( NumberFormatException nfe ) {
                userIO.display( "\n" );
                userIO.display( "Must be a number between 1 and 99.\n" );
                userIO.display( "Track: " );
            }
        }
        
        userIO.display( "\nAdd (Y/N): " );
        String add = userIO.getInput().toUpperCase();
        
        while ( !add.equals( "Y" ) && !add.equals( "N" ) ) {
            userIO.display( "\n" );
            userIO.display( "Must be 'Y' or 'N'.\n" );
            userIO.display( "Add (Y/N): " );
            add = userIO.getInput().toUpperCase();
        }
        
        AudioFile audio = new AudioFile( artist, title, album, track );
        
        if ( add.equals( "Y" ) ) {
            audioList.add( audio );
        }
        
        userIO.clearScreen();
        
        
        displayHomePage( audioList, page );
    }
    
    /**
     * Currently unused.
     * 
     * @throws IOException the IOException
     */
    public void edit() throws IOException {
        userIO.clearScreen();
        userIO.display( String.format( "%30s%s", "", "Edit Audio File" ) );
        userIO.display( "\n\n\n" );
        userIO.pause( "This function is not currently available." );
        displayHomePage( audioList, page );
    }
    
    /**
     * Deletes the page AudioFile from the AudioList.
     * 
     * @throws IOException the IOException
     */
    public void delete() throws IOException {
        userIO.clearScreen();
        userIO.display( String.format( "%30s%s", "", "Delete Audio File" ) );
        userIO.display( "\n\n\n" );

        if ( audioList.get( audioList.size() - 1 ) != null ) {
            String template = "Delete \"" 
                    + audioList.get( audioList.size() - 1 ).toString() 
                    + "\"? (Y/N) -> ";
            String prompt;
        
            if ( template.length() > 80 ) {
                prompt = "Delete \"" 
                        + audioList.get( audioList.size() - 1 )
                        .toString().substring( 0, 56 ) 
                        + "...\"? (Y/N) -> ";
            } else {
                prompt = template;
            }
        
            userIO.display( prompt );
            String delete = userIO.getInput().toUpperCase();
        
            while ( !delete.equals( "Y" ) && !delete.equals( "N" ) ) {
                userIO.display( "\n" );
                userIO.display( "Must be 'Y' or 'N'.\n" );
                userIO.display( prompt );
                delete = userIO.getInput().toUpperCase();
            }
        
            if ( delete.equals( "Y" ) ) {
                audioList.deleteLast();
            }
        
            userIO.clearScreen();
            displayHomePage( audioList, page );
        } else {
            userIO.pause( "Nothing to delete." );
            displayHomePage( audioList, page );
        }
    }
    
    /**
     * Currently unused.
     * 
     * @throws IOException the IOException
     */
    public void search() throws IOException {
        userIO.clearScreen();
        userIO.display( String.format( "%30s%s", "", "Search Audio Files" ) );
        userIO.display( "\n\n\n" );
        userIO.pause( "This function is not currently available." );
        displayHomePage( audioList, page );
    }
    
    /**
     * Currently unused.
     * 
     * @throws IOException the IOException
     */
    public void play() throws IOException {
        userIO.clearScreen();
        userIO.display( String.format( "%30s%s", "", "Play Audio File" ) );
        userIO.display( "\n\n\n" );
        userIO.pause( "This function is not currently available." );
        displayHomePage( audioList, page );
    }
    
    /**
     * Advances to the next page of AudioFiles 
     * only if there are more AudioFiles than those 
     * displayed on the current page. 
     * 
     * @throws IOException the IOException
     */
    public void nextPage() throws IOException {
        userIO.clearScreen();
        
        if ( audioList.size() > page + 16 ) {
            page += 16;
            displayHomePage( audioList, page );
        } else {
            displayHomePage( audioList, page );
        }
    }
    
    /**
     * Moves to the previous page of AudioFiles 
     * only if the user is not on page one.
     * 
     * @throws IOException the IOException
     */
    public void pagePage() throws IOException {
        userIO.clearScreen();
        
        if ( page > 0 ) {
            page -= 16;
            displayHomePage( audioList, page );
        } else {
            displayHomePage( audioList, page );
        }
    }
    
    /**
     * Terminates the program.
     */
    public void quit() {
        userIO.clearScreen();
        run = false;
    }
}
