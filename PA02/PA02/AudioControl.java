import java.io.IOException;

/**
 * The control program for the JMUTunes application.
 * 
 * Acknowledgments: I acknowledge that I have neither given nor
 * received assistance for this assignment except as
 * noted below:
 *
 * This was originally Dr. Norton's solution to PA01, got help from Dr. Norton 
 * 10/12/2018
 *
 * Modifications: PDM 10/12/2018 added READER, WRITER, fileIO variables; 
 * removed deleteLast method; added edit, validEditDelete, editFunction, delete,
 * valudateFunction, populateList and populateFile methods
 * 
 * @author Patrick Muradaz
 * @version PA02 October 12, 2018
 */
public class AudioControl {

    public static final int LIMIT = 16;  
    public static final int READER = 0; 
    public static final int WRITER = 1;
    public static final String NL = "\n";
    
    private int page;   // which page are we on (0-based)

    private AudioView io;
    private AudioFileIO fileIO;
    private AudioList audioList;

    /**
     * Default constructor.
     */
    public AudioControl() {

        page = 0;

        io = new AudioView();
        fileIO = new AudioFileIO(
                );
        audioList = new AudioList();
        
    }

    /**
     * The entry point into the application.
     * 
     * @throws IOException the IOException
     */
    public void start() throws IOException {
    
        char choice = '~'; // default to something ridiculous
        
        if ( fileIO.open( READER ) ) {
            
            populateList();
            fileIO.close( READER );
        }
        
        do {
            displayMenu();
            choice = getChoice();
            actOnChoice( choice );
        
        } while ( choice != 'q' );
                
        fileIO.open( WRITER );
        populateFile();
        fileIO.close( WRITER );
    
    }

    /**
     * Act on the choice of the user.
     * 
     * @param choice the char representing the user choice
     * @throws IOException the IOException
     */
    private void actOnChoice( char choice ) throws IOException {

        switch ( choice ) {

            case 'a':
                add();
                break;
            case 'b':
                backPage();
                break;
            case 'd':
                delete();   
                break;
            case 'e':
                edit();
                break;
            case 'p':
                play();
                break;
            case 'n':
                nextPage();
                break;
            case 's':
                search();
                break;
            case 'q':
                quit();
                break;
            default:
                io.display( "Oops!!" + NL );
        }

    }

    /**************************** private methods ****************************/

    /**
     * Add a book.
     * @throws IOException the IOException
     */
    private void add() throws IOException {
        
        int track = 0;
        
        AudioFile newFile;
        
        String album = "";
        String artist = "";
        String title = "";
    
        io.clearScreen();
        io.center( "Add Audio File" + NL );
        io.display( NL + NL );
        
        io.display( String.format( "%-11s", "Artist:" ) );
        artist = validate( "Artist:", io.getInput() );
    
        io.display( String.format( "%-11s", "Title:" ) );
        title = validate( "Title:", io.getInput() );
        
        io.display( String.format( "%-11s", "Album:" ) );
        album = io.getInput().trim();
    
        io.display( String.format( "%-11s", "Track:" ) );
        track = validate( io.getInput() );
        
        if ( confirmYesNo( "Add (Y/N): " ) ) {
        
            newFile = new AudioFile( artist, title, album, track );
            audioList.add( newFile );
        }
        
        // if this takes us to a new page, then go there 
        // (but only if we're on the former last page).
        if ( audioList.size() > ( page * LIMIT + 16 )
                        && ( audioList.size() - ( page * LIMIT + 16 ) 
                             < 16 ) ) {
            
            nextPage();
        }
        
        // possibly make the "find page" functional
        // for ( int i = 0; i < audioList.size() - 1 && !found; i++ ) {
        // if ( audioList.get( i ) == newFile ) {
        //        found = true;
        //    }
        // }
    
        io.clearScreen();
    }

    /**
     * Move back a page if possible.
     */
    private void backPage() {
    
        if ( isPriorPage() ) {            
            
            page--;
        }
        
        io.clearScreen();
    }

    /**
     * Confirm a 'Y' or 'N' prompt.
     * 
     * @param prompt the prompt to print
     * @return true if 'y', false otherwise
     * @throws IOException the IOException
     */
    private boolean confirmYesNo( String prompt ) throws IOException {
        
        String confirm = "";
        
        io.display( NL );
        io.display(  prompt );
        confirm = io.getInput().toLowerCase();
        
        while ( !isValidYesNo( confirm ) ) {
            
            io.display( NL );
            io.display( "Must be 'Y' or 'N'." + NL );
            io.display( prompt );
            confirm = io.getInput().toLowerCase();
        }
        
        return confirm.charAt( 0 ) == 'y';
    }

    /**
     * Delete an AudioFile at a specified location.
     * 
     * @throws IOException the IOException
     */
    private void delete() throws IOException {
        
        int spaces = 7;
        int delete;
        
        if ( audioList.size() > 0 ) {
            
            io.indent( spaces, "Delete Audio File number -> " );
            delete = validEditDelete( "delete", io.getInput() );
            io.clearScreen();
            io.center( "Delete Audio File" + NL );
            io.display( NL + NL );
            
            AudioFile file = audioList.get( delete - 1 );
            
            if ( confirmYesNo( "Delete \"" + file.getShortTitle() 
                            + "\"? (Y/N) -> " ) ) {
    
                audioList.delete( delete - 1 );
            }
            
            io.clearScreen();
                                 
        } else {
            
            io.clearScreen();
            io.center( "Delete Audio File" + NL );
            io.display( NL + NL );
            io.display( "Nothing to delete." + NL );
            io.pause( "Press <ENTER/RETURN> to continue . . ." );
        }
        
        // if this was the only item on the current page, then go back a page
        if ( audioList.size() < page * LIMIT + 1 ) {
            
            backPage();
        }
        
        io.clearScreen();
    }

    /**
     * Display the list of choices to the users.
     */
    private void displayChoices() {
        
        int spaces = 9;
        
        io.display( NL );
        spaces = io.center( "(A)dd, (E)dit, (D)elete, (S)earch, (P)lay, "
                          + "(N)ext, (B)ack, (Q)uit" + NL );
        io.display( NL );
        io.indent( spaces, "Choose Operation -> " );
    }

    /**
     * Display the list of publications.
     */
    private void displayList() {
    
        String itemWidth = "";
        String highestWidth = "" + ( page * LIMIT + 16 );
    
        for ( int i = 1; i <= LIMIT; i++ ) {
    
            itemWidth = "" + ( page * LIMIT + i );
            io.indent( highestWidth.length() - itemWidth.length(),
                            ( page * LIMIT + i ) + ". " 
                            + getListEntry( page * LIMIT + i ) 
                            + NL );
        }
    }

    /**
     * Display the menu.
     */
    private void displayMenu() {
    
        // Let's start up cleanly
        io.clearScreen();
        
        displayTitle();
        displayList();
        displayChoices();
    }

    /**
     * Display the title of the screen.
     */
    private void displayTitle() {
    
        io.center( "JMUTunes Audio Player" + NL );
        io.center( "CS159 (Fall 2018)" + NL );
        io.display( NL + NL );
    }

    /**
     * Edit books.
     * 
     * @throws IOException the IOException
     */
    private void edit() throws IOException {
        
        int spaces = 7;
        int edit;
        
        if ( audioList.size() != 0 ) {
            
            io.indent( spaces, "Edit Audio File number -> " );
            edit = validEditDelete( "edit", io.getInput() );
            
            AudioFile file = audioList.get( edit - 1 );
            
            io.clearScreen();
            
            io.center( "Edit Audio File" );
            io.display( NL + NL );
            if ( confirmYesNo( "Edit \"" + file.getShortTitle() 
                    + "\"? (Y/N) -> " ) ) {
    
                editFunction( edit - 1 );
            }
            
        } else {
            
            io.clearScreen();
            io.center( "Edit Audio File" );
            io.display( NL + NL );
            io.display( "Nothing to edit." + NL );
            io.pause( "Press <ENTER/RETURN> to continue . . ." );
        }
    }

    /**
     * Edit an entry.
     * 
     * @param whichOne is the index of the file to edit
     * @throws IOException the IOException
     */
    private void editFunction( int whichOne ) throws IOException {
        
        int newTrack = 0;
        int spaces = 2;
        
        AudioFile file = audioList.get( whichOne );
        AudioFile newFile;
        
        String newAlbum = "";
        String newArtist = "";
        String newTitle = "";

        io.clearScreen();
        io.center( "Edit Audio File" + NL );
        io.display( NL + NL );
        
        io.display( String.format( "%-11s", "Artist (" + file.getArtist() + ")" 
                        + NL ) );
        io.indent( spaces, "New Value -> " );
        newArtist = validateFunction( "Artist", file.getArtist(), 
                        io.getInput() );
                
        if ( newArtist.equals( "" ) ) {
            
            newArtist = file.getArtist();
        }

        io.display( String.format( "%-11s", "Title (" + file.getTitle() + ")" 
                        + NL ) );
        io.indent( spaces, "New Value -> " );
        newTitle = validateFunction( "Title", file.getTitle(), io.getInput() );
        
        if ( newTitle.equals( "" ) ) {
            
            newTitle = file.getTitle();
        }
        
        io.display( String.format( "%-11s", "Album (" + file.getAlbum() + ")" 
                        + NL ) );
        io.indent( spaces, "New Value -> " );
        newAlbum = io.getInput();
        
        if ( newAlbum.equals( "" ) ) {
            
            newAlbum = file.getAlbum();
        }
      
        io.display( String.format( "%-11s", "Track (" + file.getTrack() + ")"
                        + NL ) );
        io.indent( spaces, "New Value -> " );
        newTrack = validateFunction( file.getTrack(), io.getInput() );
        
        if ( newTrack == -2 ) {
            
            newTrack = file.getTrack();
        }
        
        if ( confirmYesNo( "Edit (Y/N): " ) ) {
        
            newFile = new AudioFile( newArtist, newTitle, newAlbum, newTrack );
            audioList.delete( whichOne );
            audioList.add( newFile );
        }
        
        // if this takes us to a new page, then go there 
        // (but only if we're on the former last page).
        if ( audioList.size() > ( page * LIMIT + 16 )
                        && ( audioList.size() - ( page * LIMIT + 16 ) 
                             < 16 ) ) {
            
            nextPage();
        }
       
        io.clearScreen();
    }

    /**
     * Get a valid choice from the user. Must be in 'abdelnqs".
     * 
     * @return the user's choice
     * @throws IOException the IOException
     */
    private char getChoice() throws IOException {
    
        boolean valid = false;
        
        int spaces = 7;
        
        String choices = "abdenpqs";
        String entry;
    
        entry = io.getInput();
    
        do {
            
            entry = entry.toLowerCase().trim();
    
            if ( entry.length() == 1 && choices.indexOf( entry ) > -1 ) {
                
                valid = true;
            
            } else {
                
                io.display( NL );
                io.indent( spaces, "Incorrect entry (" + entry
                                + ")." + NL );
                io.indent( spaces, "Choose Operation -> " );
                entry = io.getInput();
            }
    
        } while ( !valid );
    
        return entry.charAt( 0 );
    }

    /**
     * Get the line item for the listing.
     * 
     * @param whichOne which entry to get
     * @return to String to print in the listing
     */
    private String getListEntry( int whichOne ) {
        
        String line = "";
        
        if ( whichOne <= audioList.size() ) {
            
            AudioFile audioFile = audioList.get( whichOne - 1 );
            line = audioFile.toString();
        }
        
        return line;
    }

    /**
     * Is there a page after this one?
     * 
     * @return true if there is another page
     */
    private boolean isNextPage() {
        
        return audioList.size() > page * LIMIT + LIMIT;
    }

    /**
     * Is there a page before this one?
     * 
     * @return true if there is a prior page
     */
    private boolean isPriorPage() {
        
        return page > 0;
    }

    /**
     * Is this a valid track?
     * 
     * @param str the track to test
     * @return true if valid, false otherwise
     */
    private boolean isValidTrack( String str ) {
        
        boolean valid = true;
        int track = -1;
                
        try {
             
            track = Integer.parseInt( str );
            valid = track == -1 || track == -2 || ( track > 0 && track < 100 );
            
        } catch ( NumberFormatException e ) {
           
            valid = false;
        }
    
        return valid;
    }

    /**
     * Is the string either 'y' or 'n'?
     * 
     * @param ok the string to check
     * @return true if valid
     */
    private boolean isValidYesNo( String ok ) {
        
        String yesNo = ok.toLowerCase();
        
        return yesNo.length() == 1 && ( yesNo.charAt( 0 ) == 'y' 
                        || yesNo.charAt( 0 ) == 'n' );
    }

    /**
     * Go to the next page if possible.
     */
    private void nextPage() {
        
        if ( isNextPage() ) {           
            
            page++;
        }
    
        io.clearScreen();
    }

    /**
     * Play the audio file.
     * 
     * @throws IOException The IOException
     */
    private void play() throws IOException {
        
        showNotAvailable( "Play Audio File" );
    }

    /**
     * Populates the AudioList.txt file from the AudioList.
     * @throws IOException the IOException
     */
    private void populateFile() throws IOException {
        
        String split = "|";        
        String artist = "";
        String title = "";
        String album = "";
        
        int track = 0;
        
        for ( int i = 0; i < audioList.size(); i++ ) {
            
            artist = audioList.get( i ).getArtist();
            title = audioList.get( i ).getTitle();
            album = audioList.get( i ).getAlbum();
            track = audioList.get( i ).getTrack();
            
            fileIO.write( artist + split + title + split + album + split 
                            + track + split );
        }
    }

    /**
     * Populates the AudioList from a file.
     * 
     * @throws IOException the IOException
     */
    private void populateList() throws IOException {
        
        AudioFile addFile;
        
        AudioFileIO writeOut = new AudioFileIO( "malformed.err" );
        
        String[] test = new String[4];
        
        String fileIn = "";
        
        while ( ( fileIn = fileIO.readLine() ) != null ) {
            
            test = fileIn.split( "\\|", -1 );
            
            if ( test.length > 4 && !test[ 0 ].equals( "" ) 
                    && !test[ 1 ].equals( "" ) ) {
                    
                String artist = test[ 0 ];
                String title = test[ 1 ];
                String album = test[ 2 ];
                
                int track;
                    
                try {
                    
                    track = Integer.parseInt( test[ 3 ] );
                
                } catch ( NumberFormatException nfe ) {
                    
                    track = -1;
                }
                    
                addFile = new AudioFile( artist, title, album, track );
                audioList.add( addFile );
                                
            } else {
                
                writeOut.open( WRITER );
                writeOut.write( fileIn );
                writeOut.close( WRITER );
            }
        }
    }

    /**
     * Quit the application.
     */
    private void quit() {
    
        io.clearScreen();
    }

    /**
     * Search for books.
     * 
     * @throws IOException the IOException
     */
    private void search() throws IOException {
    
        showNotAvailable( "Search Audio Files" );
    }

    /**
     * Show the not available message.
     * 
     * @param prompt the prompt to print
     * @throws IOException The IOException
     */
    private void showNotAvailable( String prompt ) throws IOException {
    
        io.clearScreen();
        io.center( prompt + NL );
        io.display( NL + NL + "This Function is not currently available."
                        + NL );
        io.pause( "Press <ENTER> to continue . . . " );
        io.clearScreen();
    
    }
    
    /**
     * Validate the track given the string incoming.
     * 
     * @param str the string to convert and test
     * 
     * @return the valid track
     * @throws IOException the IOException
     */
    private int validate( String str ) throws IOException {
        
        String input = str;
                
        if ( input.equals( "-1" ) ) {
            
            input = "0";
        }
        
        if ( input.equals( "-2" ) ) {
            
            input = "0";
        }
        
        if ( input.trim().length() == 0 ) {
            
            input = "-1";
        }
        
        while ( !isValidTrack( input ) ) {
            
            io.display( NL );
            io.display( "Must be a number between 1 and 99." + NL );
            io.display( String.format( "%-11s", "Track:" ) );
            input = io.getInput();
            io.display( NL );
            
            if ( input.equals( "-1" ) ) {
                
                input = "0";
            }
            
            if ( input.equals( "-2" ) ) {
                
                input = "0";
            }
            
            if ( input.trim().length() == 0 ) {
                
                input = "-1";
            }
        }
        
        return Integer.parseInt( input );
    }
    
    /**
     * Ensure string has length > 0.
     * 
     * @param prompt the prompt to print
     * @param str the string to test
     * @return the validated string
     * @throws IOException the IOException
     */
    private String validate( String prompt, String str ) throws IOException {
                
        String input = str;
        
        while ( input.trim().length() == 0 ) {
            
            io.display( NL );
            io.display( "Required entry." + NL );
            io.display( String.format( "%-11s", prompt ) );
            
            input = io.getInput();     
            // io.display( NL );
        }
        
        return input.trim();
    }

    /**
     * Check if the input gotten in the editFunction method is valid.
     * 
     * @param att is the attribute passed through
     * @param str is the input to check
     * @return true if input is valid
     * @throws IOException the IOException
     */
    private int validateFunction( int att, String str ) 
                    throws IOException {
        
        int spaces = 2;
        
        String newAtt = str;
        
        if ( newAtt.length() == 0 ) {
            
            newAtt = "-2";
        
        } else if ( newAtt.matches( "\\s+" ) ) {
            
            newAtt = "-1";
        }
        
        while ( !isValidTrack( newAtt ) ) {
            
            io.display( NL );
            io.display( "Must be a number between 1 and 99." + NL );
            io.display( String.format( "%-11s", "Track" + " (" + att + ")" 
                    + NL ) );
            io.indent( spaces, "New Value -> " );
            
            newAtt = io.getInput(); 

            if ( newAtt.length() == 0 ) {
                
                newAtt = "-2";
            
            } else if ( newAtt.matches( "\\s+" ) ) {
                newAtt = "-1";
            }
        }
        
        return Integer.parseInt( newAtt );
    }
    
    /**
     * Check if the input gotten in the editFunction method is valid.
     * 
     * @param prompt is the prompt to print out
     * @param att is the attribute passed through
     * @param str is the input to check
     * @return true if input is valid
     * @throws IOException the IOException
     */
    private String validateFunction( String prompt, String att, String str ) 
                    throws IOException {
        
        int spaces = 2;
        
        String newAtt = str;
        
        while ( newAtt.matches( "\\s+" ) ) {
            
            io.display( NL );
            io.display( "Required entry." + NL );
            io.display( String.format( "%-11s", prompt + " (" + att + ")" 
                    + NL ) );
            io.indent( spaces, "New Value -> " );
            
            newAtt = io.getInput();     
            // io.display( NL );
        }
        
        return newAtt;
    }

    /**
     * Validate the number of the AudioFile to edit.
     * 
     * @param str the string to convert and test
     * @param type is the type of operation being conducted (edit or delete) 
     * @return the valid input
     * @throws IOException the IOException
     */
    private int validEditDelete( String type, String str ) 
                    throws IOException {
        
        int spaces = 7;
        int first = ( page + 1 ) * LIMIT - 15;
        int last = first + 15;
        int input = -1;
        
        try {
            
            input = Integer.parseInt( str );
            
        } catch ( NumberFormatException nfe ) {
            
            input = -1;
        }
        
        while ( input < first || input > last ) {
            
            io.display( NL );
            io.indent( spaces, "Must be a number between " + first + " and " 
                        + last + "." + NL );
            io.indent( spaces, type + " Audio File Number -> " );
            
            try {
                
                input = Integer.parseInt( io.getInput() );
                
            } catch ( NumberFormatException nfe ) {
                
                input = -1;
            }
            
            io.display( NL );
        }
        
        return input;
    }
}
