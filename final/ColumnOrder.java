package homework4;

import java.util.ArrayList;


/**
 * ColumnOrder represents an algorithm of invoking an update function for each panel in a matrix of panels.  
 * the order of invoking will be column after column.
 */
public class ColumnOrder implements NotifyObserversAlgorithm{

	
	/**
	 * @effects notifies panels column after column that the colorGenerator has been changed. 
	 * @throws InterruptedException
	 */
	@Override
	public void notify(ArrayList<Panel> list, ColorGenerator colorGenerator) throws InterruptedException {
		for(int i=0 ; i<5 ; ++i) {
			for(int j = 0 ; j< PANELS_NUMBER ; j += 5) {
				list.get(i+j).update(colorGenerator,colorGenerator.getGraphics());
				Thread.sleep(FORTY_MILI_SECOND);
			}
		}
	}
}
