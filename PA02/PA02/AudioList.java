import java.util.ArrayList;

/**
 * Collection class to store the individual AudioFile objects.
 * 
 * Acknowledgments: I acknowledge that I have neither given nor
 * received assistance for this assignment except as
 * noted below:
 *
 * This was originally Dr. Norton's solution to PA01, 
 * got help from HW07 10/9/2018, got help from Dr. Norton 10/10/2018
 *
 * Modifications: PDM 10/8/2018 Edited add method; deleted capacity, deleteLast,
 * and expand methods; added contains, delete, difference, intersection, subset,
 * and union methods.
 * 
 * @author Patrick Muradaz
 * @version PA02 October 10, 2018
 */
public class AudioList {

    private int last; // pointer to the last slot used in the array
    private ArrayList< AudioFile > audioList;
    
    /**
     * Default constructor - instantiate array and initialize.
     */
    public AudioList() {
        
        last = -1;
        audioList = new ArrayList<>();
    }

    /**
     * Adds or inserts an AudioFile alphabetically to the collection.
     * An AudioFile can be added successfully if the AudioFile does
     * not already exist in the AudioList and if its canAdd() method
     * returns true.
     * If successful, then increment "last". 
     * 
     * @param audioFile the AudioFile object to add
     * @return true if the addition was successful (AudioFile.canAdd() returns
     *         true)
     */
    public boolean add( AudioFile audioFile ) {

        boolean success = false;
        
        if ( !audioList.contains( audioFile ) && audioFile.canAdd() ) {
            if ( audioList.size() == 0 || isLast( audioFile ) ) {
                
                append( audioFile );
                last++;
            
            } else {
                
                insert( audioFile );
                last++;
            }
            
            success = true;
        }
        
        return success;
    }
    
    /**
     * Looks through the AudioList for the parameter file. Return true if the
     * file is contained in the AudioList.
     * 
     * @param file is the file to be looked for in the AudioList
     * @return true if file is contained in the AudioList
     */
    public boolean contains( AudioFile file ) {
        
        boolean contains = false;
        
        for ( int i = 0; i < audioList.size(); i++ ) {
            if ( audioList.get( i ).equals( file ) ) {
                
                contains = true;
            }
        }
        
        return contains;
    }

    /**
     * Deletes the AudioFile found at the index indicated by whichOne.
     * Return true if successful, false otherwise (true if something to delete).
     * If successful, then decrement "last".
     * 
     * @param whichOne is the index to be deleted
     * @return true if deletion was successful, false otherwise
     */
    public boolean delete( int whichOne ) {

        boolean success = false;
        
        if ( whichOne >= 0 && whichOne < audioList.size() ) {
            
            audioList.remove( whichOne );
            last--;
            success = true;
        }
        
        return success;
    }
    
    /**
     * Returns a new AudioList containing all of the files in this AudioList
     * that are not in the other AudioList.
     * 
     * @param other is the AudioList to check this AudioList against
     * @return an AudioList containing the difference between this AudioList and
     * the other AudioList
     */
    public AudioList difference( AudioList other ) {
        
        AudioList difference = new AudioList();
        
        if ( other != null ) {
            for ( AudioFile file : audioList ) {
                if ( !other.contains( file ) ) {
                    
                    difference.add( file );
                }
            }
        
        } else {
            
            for ( AudioFile file : audioList ) {
                
                difference.add( file );
            }
        }
        
        return difference;
    }

    /**
     * Retrieves the AudioFile object at the specified position. If position is
     * out of bounds or if position is not populated, return null.
     * 
     * @param whichOne - the position from which to get the AudioFile
     * @return the AudioFile at the specified position
     */
    public AudioFile get( int whichOne ) {

        AudioFile pubToReturn = null;
        
        if ( whichOne >= 0 && whichOne < size() ) {
            
            pubToReturn = audioList.get( whichOne );
        }
        
        return pubToReturn;
    }
    
    
    
    /**************************** private methods ****************************/
    
    /**
     * Returns a new AudioList that contains all of the files that are in the 
     * current AudioList as well as the other AudioList.
     * 
     * @param other is the AudioList to check this AudioList against
     * @return an AudioList containing the intersection of the current AudioList
     * and the other AudioList
     */
    public AudioList intersection( AudioList other ) {
        
        AudioList intersection = new AudioList();
        
        if ( other != null ) {
            for ( AudioFile file : audioList ) {
                if ( other.contains( file ) ) {
                    
                    intersection.add( file );
                }
            }
        
        } else {
        
            intersection = null;
        }
        
        return intersection;
    }

    /**
     * Returns the size of the collection (how many items does it hold). This
     * will be equal to the value "last" + 1.
     * 
     * @return the value of "last" + 1
     */
    public int size() {

        return last + 1;
   
    }

    /**
     * Returns true if every item in the other AudioList is also in this
     * AudioList.
     * 
     * @param other is the AudioList to check this AudioList against
     * @return true if every item in the other AudioList is also in this
     * AudioList
     */
    public boolean subset( AudioList other ) {
        
        boolean isSubset = true;
        
        if ( other != null && other.size() > 0 ) {
            for ( int i = 0; i < other.size() && isSubset; i++ ) {
                if ( !audioList.contains( other.get( i ) ) ) {
                    
                    isSubset = false;
                }
            }
        }
        
        return isSubset;
    }

    /**
     * Returns a new AudioList that contains the union of the current AudioList
     * and the other AudioList.
     * 
     * @param other is the AudioList to check this AudioList against
     * @return a new AudioList that contains the union of the current AudioList
     * and the other AudioList
     */
    public AudioList union( AudioList other ) {
        
        AudioList union = new AudioList();
        
        for ( AudioFile file : audioList ) {
            
            union.add( file );
        }
        
        if ( other != null ) {
            for ( int i = 0; i < other.size(); i++ ) {
                
                union.add( other.get( i ) );
            }
        }
        
        return union;
    }

    /**
     * Adds an AudioFile at the specified position.
     * 
     * @param audioFile the AudioFile to be added
     */
    private void append( AudioFile audioFile ) {
        
        audioList.add( audioFile );
    
    }

    /**
     * Determines if the given AudioFile is before the AudioFile at the given 
     * index.
     * 
     * @param audioFile is the AudioFile to test with
     * @param index is the index of the AudioList file to test with
     * @return true if the given AudioFile is before the AudioFile at the given 
     * index
     */
    private boolean before( AudioFile audioFile, int index ) {
        
        boolean isBefore = true;
        
        int thisTrack = audioFile.getTrack();
        int otherTrack = audioList.get( index ).getTrack();
        
        String[] myArtist = audioFile.getArtist().split( " " );
        String[] otrArtist = audioList.get( index ).getArtist().split( " " );
        String[] myAlbum = audioFile.getAlbum().split( " " );
        String[] otrAlbum = audioList.get( index ).getAlbum().split( " " );
        String[] myTitle = audioFile.getTitle().split( " " );
        String[] otrTitle = audioList.get( index ).getTitle().split( " " );
        
        String thisArtist = myArtist[0].toLowerCase();
        String otherArtist = otrArtist[0].toLowerCase();
        String thisAlbum = myAlbum[0].toLowerCase();
        String otherAlbum = otrAlbum[0].toLowerCase();
        String thisTitle = myTitle[0].toLowerCase();
        String otherTitle = otrTitle[0].toLowerCase();
        
        if ( thisArtist.equals( otherArtist ) ) {
            if ( thisAlbum.equals( otherAlbum ) ) {
                if ( thisTrack == otherTrack ) {
                    
                    isBefore = figure( thisTitle, otherTitle );
                    
                } else {
                    
                    isBefore = thisTrack < otherTrack;
                    
                }
                
            } else if ( thisAlbum.length() == 0 ) { 
                
                isBefore = true;
                
            } else {
                    
                isBefore = figure( thisAlbum, otherAlbum );
                    
            }
            
        } else {
            
            isBefore = figure( thisArtist, otherArtist );
            
        }
        
        return isBefore;
    }

    /**
     * Figures whether this attribute is before the other attribute in 
     * alphabetical order.
     * 
     * @param thisAtt is this attribute to test
     * @param otherAtt is the other attribute to test
     * @return true if this attribute comes immediately before the other 
     * attribute in alphabetical order
     */
    private boolean figure( String thisAtt, String otherAtt ) {
        
        boolean isBefore = false;
        
        if ( thisAtt.compareTo( otherAtt ) < 0 ) {
            
            isBefore = true;
            
        }
        
        return isBefore;
    }

    /**
     * Inserts an AudioFile into its alphabetical position in the list.
     * 
     * @param audioFile is the AudioFile to be inserted
     */
    private void insert( AudioFile audioFile ) {
        
        if ( before( audioFile, audioList.size() - 1 ) ) {
            
            int index = 0;
            
            while ( !before( audioFile, index ) ) {
                
                index++;
            }
            
            audioList.add( index, audioFile );
            
        } else {
            
            append( audioFile );
        }
    }

    /**
     * Returns true if the AudioFile comes after the last AudioFile in the list.
     * 
     * @param audioFile is the AudioFile to compare
     * @return true if the AudioFile belongs last in the list
     */
    private boolean isLast( AudioFile audioFile ) {
        
        boolean isLast = true;
        
        if ( audioList.size() > 0 ) {
            
            isLast = !before( audioFile, audioList.size() - 1 );
        
        }
        
        return isLast;
    }
}
