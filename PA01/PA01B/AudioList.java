/**
* This class models a list of audio files by creating an array of AudioFile 
* objects.
*
* Acknowledgments: I acknowledge that I have neither given nor
* received assistance for this assignment except as
* noted below:
*
* Dr. Norton 9/19, 9/21
*
* Modifications: PDM 9/14/2018 Completed first versions of methods.
* PDM 9/21/2018 Completed methods, minor errors.
* PDM 9/26/2018 Completed final version of class.
*
* @author Patrick Muradaz 
* @version PA01 (September 26, 2018)
*/
public class AudioList {

    private AudioFile[] list;
    private int last;
    
    /**
     * Constructor for the AudioList object.
     */
    public AudioList() {
        this.list = new AudioFile[16];
        this.last = -1;
    }
    
    /**
     * Adds an AudioFile to the end of the AudioList.
     * 
     * @param audio is the AudioFile object to add to the AudioList array
     * @return true if the method works correctly
     */
    public boolean add( AudioFile audio ) {
        boolean result = false;
        
        if ( audio != null && audio.canAdd() ) {
            if ( this.size() < this.capacity() ) {
                last++;
                list[last] = audio;
                result = true;
            } else {
                AudioFile[] newList = new AudioFile[list.length * 2];
                for ( int i = 0; i < list.length; i++ ) {
                    newList[i] = list[i];
                }
                list = newList;
                
                last++;
                list[last] = audio;
                result = true;
            }
        }
        return result;
    }
    
    /**
     * Returns the current capacity of the AudioList array.
     * 
     * @return the current capacity of the AudioList
     */
    public int capacity() {
        return list.length;
    }
    
    /**
     * Deletes the last AudioFile in the AudioList array.
     * 
     * @return true if the method works correctly
     */
    public boolean deleteLast() {
        boolean result = false;
        
        list[last] = null;
        last--;
        
        if ( list[last + 1] == null ) {
            result = true;
        }
        return result;
    }
    
    /**
     * Returns the AudioFile object at the index of AudioList given by the user.
     * 
     * @param whichOne is the index of AudioList given by the user
     * @return the AudioFile at the given index of AudioList
     */
    public AudioFile get( int whichOne ) {
        AudioFile result = null;
        
        if ( whichOne >= 0 && whichOne < this.size() ) {
            result = list[whichOne];
        }
        return result;
    }
    
    /**
     * Returns the current size of the AudioList array. 
     * 
     * @return the size of the AudioList array
     */
    public int size() {
        return last + 1;
    }
}
