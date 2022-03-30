/**
 * A class to store individual audio files.
 * 
 * Acknowledgments: I acknowledge that I have neither given nor
 * received assistance for this assignment except as
 * noted below:
 *
 * This was originally Dr. Norton's solution to PA01
 *
 * Modifications: PDM 10/8/2018 Added equals method.
 *
 * @author Patrick Muradaz
 * @version PA02 October 8, 2018
 */
public class AudioFile {

    private int track;

    private String album;
    private String artist;
    private String title;

    /**
     * Explicit Value Constructor.
     * 
     * @param artist - the artist for the publication
     * @param title - the title of the publication
     * @param album - the album of publication
     * @param track - the track of the publication
     */
    public AudioFile( String artist, String title, String album,
                    int track ) {

        setArtist( artist );
        setTitle( title );
        setAlbum( album );
        setTrack( track );
    }

    /**
     * Can this publication be added? True if artist & title have values and
     * track is valid (-1 or between 1 & 99).
     * 
     * @return true if this item can be added to the audio list
     */
    public boolean canAdd() {

        return isValidArtist() && isValidTitle() && isValidTrack();
    }
    
    /**
     * Are all of the public attributes of this object the same as all of the
     * public attributes of the other object (obj)? True if this.artist == 
     * obj.artist, this.title == obj.title, etc.
     * 
     * @param obj is the other object
     * @return true if the two objects have identical attributes
     */
    public boolean equals( Object obj ) {
        
        AudioFile other = (AudioFile) obj;
        
        return isEqualArtist( other ) && isEqualTitle( other )
                && isEqualAlbum( other ) && isEqualTrack( other );
    }

    /**
     * Return the album of the publication. This attribute is optional.
     * 
     * @return the album of the publication
     */
    public String getAlbum() {

        return album;
    }

    /**
     * Return the artist of the publication. This attribute must exist and have
     * a length > 0.
     * 
     * @return the artist of the publication
     */
    public String getArtist() {

        return artist;
    }

    /**
     * Get artist, title up to 60 characters.
     * 
     * @return the short title
     */
    public String getShortTitle() {
        
        return getDisplayLine( 60 );      
    }

    /**
     * Return the title of the publication. This attribute must exist and have a
     * length > 0.
     * 
     * @return the title of the publication
     */
    public String getTitle() {

        return title;
    }

    /**
     * Return the track of the publication. Must be 1 or a track between 1 and
     * 99.
     * 
     * @return the track of publication
     */
    public int getTrack() {

        return track;
    }

    /**
     * Set the value of the album. If the incoming parameter is null, set it to
     * an empty string.
     * 
     * @param album the album of publication
     */
    public void setAlbum( String album ) {
    
        this.album = album;
    
        if ( this.album == null ) {
            
            this.album = "";
        }
        
        this.album = this.album.trim();
    }

    /**
     * Set the value of the artist. If the incoming parameter is invalid (null
     * or length == 0) then return false, else return true.
     * 
     * @param artist the artist of the publication
     * @return true if valid, false otherwise
     */
    public boolean setArtist( String artist ) {
    
        boolean success = true;
    
        this.artist = artist;
    
        if ( !isValidArtist() ) {
            this.artist = "";
            
            success = false;
        }
        
        this.artist = this.artist.trim();
    
        return success;
    }

    /**
     * Set the value of the title. If the incoming parameter is invalid (null or
     * length == 0) then return false, else return true.
     * 
     * @param title the title of the publication
     * @return true if valid, false otherwise
     */
    public boolean setTitle( String title ) {
    
        boolean success = true;
    
        this.title = title;
    
        if ( !isValidTitle() ) {
            
            this.title = "";
            success = false;
        }
        
        this.title = this.title.trim();
    
        return success;
    }

    /**
     * Set the value of the track. If the incoming parameter is valid (-1 or
     * between 1 and 99, return true, else return false
     * 
     * @param track the track of the publication
     * @return true if valid, false otherwise
     */
    public boolean setTrack( int track ) {
    
        boolean success = true;
    
        this.track = track;
    
        if ( !isValidTrack() ) {
            
            success = false;
        }
    
        return success;
    }

    /**
     * Return the formatted artist title for the book (72 characters max).
     * 
     * @return the formatted string for artist, title
     */
    public String toString() {
    
        return getDisplayLine( 72 );
    }

    /**
     * Get the string to display (up to max characters).
     * 
     * @param max THe maximum length of the string to return
     * @return The formatted string
     */
    private String getDisplayLine( int max ) {
        
        String displayString = artist + ", " + title;
    
        String audioAlbum = album.length() > 0 ? " (" + album + ")" : "";
    
        displayString += audioAlbum;
    
        displayString = displayString.length() <= max ? displayString
                        : displayString.substring( 0, max - 3 ) + "...";
    
        return displayString;
    
    
    }

    /**
     * Is this album the same as the other album?
     * 
     * @param other is the other album to be tested
     * @return true if the albums are the same
     */
    private boolean isEqualAlbum( AudioFile other ) {
        
        String thisAlbum = this.getAlbum().toLowerCase();
        String otherAlbum = other.getAlbum().toLowerCase();
        
        return album != null && thisAlbum.equals( otherAlbum );
    }

    /**
     * Is this artist the same as the other artist?
     * 
     * @param other is the other artist to be tested
     * @return true if the artists are the same
     */
    private boolean isEqualArtist( AudioFile other ) {
        
        String thisArtist = this.getArtist().toLowerCase();
        String otherArtist = other.getArtist().toLowerCase();
        
        return artist != null && thisArtist.equals( otherArtist );
    }

    /**
     * Is this title the same as the other title?
     * 
     * @param other is the other title to be tested
     * @return true if the titles are the same
     */
    private boolean isEqualTitle( AudioFile other ) {
        
        String thisTitle = this.getTitle().toLowerCase();
        String otherTitle = other.getTitle().toLowerCase();
        
        return title != null && thisTitle.equals( otherTitle );
    }

    /**
     * Is this track the same as the other track?
     * 
     * @param other is the other track to be tested
     * @return true if the tracks are the same
     */
    private boolean isEqualTrack( AudioFile other ) {
        
        return this.getTrack() == other.getTrack();
    }

    /****************************** private methods ***************************/

    /**
     * Is the artist valid (length > 0)?
     * 
     * @return true if valid, false otherwise
     */
    private boolean isValidArtist() {

        return artist != null && artist.length() > 0;
    }
    
    /**
     * Is the title valid (length > 0)?
     * 
     * @return true if valid, false otherwise
     */
    private boolean isValidTitle() {

        return title != null && title.length() > 0;

    }

    /**
     * Is this a valid track?
     * 
     * @return true if valid, false otherwise
     */
    private boolean isValidTrack() {

        return track == -1 || ( track > 0 && track < 100 );

    }
}
