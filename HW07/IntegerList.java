import java.util.ArrayList;

/**
* This class models a list of Integers.
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
public class IntegerList {
    
    private ArrayList<Integer> list;
    
    /**
     * Default constructor.
     */
    public IntegerList() {
        list = new ArrayList<Integer>();
    }
    
    /**
     * Adds the input number to the end of the IntegerList.
     * 
     * @param input is the Integer entered by the user
     */
    public void add( Integer input ) {
        list.add( input );
    }
     
    /**
     * Returns the size of the IntegerList.
     * 
     * @return the size of the IntegerList
     */
    public int getSize() {
        return list.size();
    }
    
    /**
     * Provides a way to print the IntegerList to screen.
     * 
     * @return the String to print
     */
    public String toString() {
        return list.toString();
    }
}
