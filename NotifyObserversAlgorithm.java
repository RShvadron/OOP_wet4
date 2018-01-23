package homework4;

import java.awt.Graphics;
import java.util.ArrayList;


/**
 * This Interface should be implemented by algorithms that where designed to notify a list of observers that the observable has been changed. 
 */
public interface NotifyObserversAlgorithm {
	
	public static final int FORTY_MILI_SECOND = 40;
	public static final int PANELS_NUMBER = 25;
	
	/**
     * Each implementation of this interface must provide a method for obtaining the indices in which we'll pass.
     */
	public void notify(ArrayList<Panel> list, ColorGenerator colorGenerator, Graphics g) throws InterruptedException;
}
