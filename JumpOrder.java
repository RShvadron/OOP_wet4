package homework4;

import java.awt.Graphics;
import java.util.ArrayList;


/**
 * JumpOrder represents an algorithm of invoking an update function for each panel in a matrix of panels.  
 * the order of invoking will start with the even panels and than with the odd panels.
 */
public class JumpOrder implements NotifyObserversAlgorithm{

	
	/**
	 * @effects notifies the even panels first that the colorGenerator has been changed and later move to the odd panels.
	 * @throws InterruptedException
	 */
	@Override
	public void notify(ArrayList<Panel> list, ColorGenerator colorGenerator, Graphics g) throws InterruptedException {
		for(int i = 0 ; i < PANELS_NUMBER ; i += 2) {
			list.get(i).update(colorGenerator, g);
			Thread.sleep(FORTY_MILI_SECOND);
		}
		for(int j = 1 ; j < PANELS_NUMBER ; j += 2) {
			list.get(j).update(colorGenerator, g);
			Thread.sleep(FORTY_MILI_SECOND);
		}
	}

}
