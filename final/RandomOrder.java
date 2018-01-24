package homework4;

import java.util.ArrayList;
import java.util.Collections;


/**
 * RandomOrder represents an algorithm of invoking an update function for each panel in a matrix of panels.  
 * the order of invoking will be random.
 */
public class RandomOrder implements NotifyObserversAlgorithm {
	
	
	/**
	 * @effects notifies panels in random order that the colorGenerator has been changed. 
	 * @throws InterruptedException
	 */
	@Override
	public void notify(ArrayList<Panel> list, ColorGenerator colorGenerator) throws InterruptedException {
		ArrayList<Integer> sortingBucket = new ArrayList<>();
		for(Integer i = 0; i< PANELS_NUMBER; ++i) {
			sortingBucket.add(i);
		}
		Collections.shuffle(sortingBucket);
		for(Integer index : sortingBucket) {
			list.get(index).update(colorGenerator, colorGenerator.getGraphics());
			Thread.sleep(FORTY_MILI_SECOND);
		}	
	}

}
