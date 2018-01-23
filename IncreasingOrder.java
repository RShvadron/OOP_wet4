package homework4;

import java.awt.Graphics;
import java.util.ArrayList;


/**
 * IncresingOrder represents an algorithm of invoking an update function for each panel in a matrix of panels.  
 * the order of invoking will be in increasing order.
 */
public class IncreasingOrder implements NotifyObserversAlgorithm {
	
	/**
	 * @effects notifies panels in increasing order that the colorGenerator has been changed. 
	 * @throws InterruptedException
	 */
	@Override
	public void notify(ArrayList<Panel> list, ColorGenerator colorGenerator, Graphics g) throws InterruptedException {
		for(int i = 0 ; i < PANELS_NUMBER ; ++i) {
			list.get(i).update(colorGenerator, g);
			Thread.sleep(FORTY_MILI_SECOND);
		}
	}
}
