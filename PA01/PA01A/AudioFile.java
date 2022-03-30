/**
* This class models an audio file by creating an AudioFile object.
*
* Acknowledgements: I acknowledge that I have neither given nor
* received assistance for this assignment except as
* noted below:
*
* Dr. Norton 9/19, 9/21
*
* Modifications: PDM 9/13/2018 Completed first versions of methods.
*
* @author Patrick Muradaz
* @version PA01 (September 13, 2018)
*/
public class AudioFile {

    private String artist;
    private String title;
    private String album;
    private int track;
    
    /**
     * Constructor for an AudioFIle object.
     * 
     * @param artist is the artist associated with a particular audio file
     * @param title is the title of a particular audio file
     * @param album is the album that a particular audio file belongs to
     * @param track is the t of a particular audio file
     */
    public AudioFile( String artist, String title, String album, 
            int track ) {
        setArtist( artist );
        setTitle( title );
        setAlbum( album );
        setTrack( track );
    }
    
    /**
     * Decides if an AudioFile object is configured correctly, i.e. the object
     * contains values for author and title and the track is between 1 and 99.
     * 
     * @return true if the AudioFile object contains good values for artist,
     * title, and track
     */
    public boolean canAdd() {
        boolean result = false;
        
        if ( artist != null && artist.length() > 0 && title != null 
                && title.length() > 0 && track >= 1 && track <= 99 
                || track == -1 ) {
            result = true;
        }        
        return result;
    }

    /**
     * Return the album attribute as a String.
     * 
     * @return the album that a particular audio file belongs to
     */
    public String getAlbum() {
        return album.toString();
    }

    /**
     * Return the artist attribute as a String.
     * 
     * @return the artist associated with a particular audio file
     */
    public String getArtist() {
        return artist.toString();
    }

    /**
     * Return the title attribute as a String.
     * 
     * @return the title of a particular audio file
     */
    public String getTitle() {
        return title.toString();
    }

    /**
     * Return the track attribute as an int.
     * 
     * @return the track number of a particular audio file
     */
    public int getTrack() {
        return track;
    }
    
    /**
     * Set the value of the album attribute.
     * 
     * @param album is the album that a particular audio file belongs to
     */
    public void setAlbum( String album ) {
        if ( album != null ) {
            this.album = album;
        } else {
            this.album = "";
        }
    }

    /**
     * Set the value of the artist attribute.
     * 
     * @param artist is the artist associated with a particular audio file
     * @return true if method works correctly
     */
    public boolean setArtist( String artist ) {
        boolean result = false;
        
        if ( artist != null ) {
            String artistSet = artist.trim();
            if ( artistSet.length() > 0 ) { 
                this.artist = artistSet;
                result = true;
            } else {
                this.artist = "";
            }
        } else {
            this.artist = "";
        }
        return result;
    }

    /**
     * Set the value of the title attribute.
     * 
     * @param title is the title of a particular audio file
     * @return true if method works correctly
     */
    public boolean setTitle( String title ) {
        boolean result = false;
        
        if ( title != null ) {
            String titleSet = title.trim();
            if ( titleSet.length() > 0 ) {
                this.title = titleSet;
                result = true;
            } else {
                this.title = "";
            }
        } else {
            this.title = "";
        }
        return result;
    }
    
    /**
     * Set the value of the track attribute.
     * 
     * @param track is the track number of a particular audio file
     * @return true if method works correctly
     */
    public boolean setTrack( int track ) {
        boolean result = false;
        this.track = track;
        
        if ( track >= 1 && track <= 99 || track == -1 ) {
            result = true;
        } 
        return result;
    }
    
    /**
     * Returns the AudioFile object attributes as a String.
     * 
     * @return the AudioFile object attributes as a String 
     */
    public String toString() {
        String displayList;
        String displayEdit;
        
        if ( album.equals( "" ) ) {
            displayList = artist + ", " + title;
            displayEdit = artist + ", " + title;
        } else {
            displayList = artist + ", " + title + " (" + album + ")";
            displayEdit = artist + ", " + title + " (" + album + ")";
        }
        
        if ( displayList.length() > 72 ) {
            displayList = displayEdit.substring( 0, 69 ) + "...";
        }
        
        return displayList;
    }
}
