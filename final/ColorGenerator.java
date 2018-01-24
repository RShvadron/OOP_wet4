package homework4;


import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;


/**
 * ColorGenerator represents an object that changes its color every 2 seconds.
 * ColorGenerator determines the color for the Panel class therefore it is the subject class for Panel object.
 * Only one object of ColorGenerator can be created due to the implementation design (Singelton). In order to
 * notify the observers panels, ColorGenerator holds a list of observers.
 */
public class ColorGenerator extends Observable {
	
	private static ColorGenerator colorGenerator = null;
	
	private Color color;
	private Random rand;
	private Graphics graphics;
	private NotifyObserversAlgorithm notifyAlgorithm;
	private ArrayList<Panel> observers;
	
	private static final int VALUE = 256;
	
	
	
		// Abstraction Function:
		// 	 represents a single object only with a color. the color will be changed every 2 seconds due to random
		//   object at this.rand. every color related object that wants to imitate the color of this will be enter to 
		//   the observers list at this.observers. notification will be send to the observers in the moment of change according 
		//   to the notify algorithm at this.notifyAlgorithm.

		// Representation Invariant:
		//	1) random != null.
		//	2) color != null.
		//	3) notifyAlgorithm != null.
		//  4) observers != null.
	
	
	
	/**
     * Checks to see if the representation invariant is being violated.
     * @throws AssertionError if representation invariant is violated.
     */ 
    private void checkRep() {
    	assert(observers != null) : "Observers list can't be null";
    	assert(rand != null) : "Random object can't be null";
    	assert(color !=null) : "Panel color can't be null";
    	assert(notifyAlgorithm != null) : "Notify algorithm can't be null";
    }
	
	
    /**
     * @modifies this
     * @effects Initializes this with a random object, random color and empty observers list.
     */
	private ColorGenerator() {
		rand = new Random();
		color = new Color(rand.nextFloat(),rand.nextFloat(),rand.nextFloat());
		notifyAlgorithm = new IncreasingOrder();
		observers = new ArrayList<>();
		checkRep();
	}
	
	
	/**
     * @modifies this
     * @effects adds an observer(Panel) to the observers list.
     */
	@Override
	public void addObserver(Observer observer) {
		checkRep();
		Panel newPanel = (Panel)observer;
		observers.add(newPanel);
		checkRep();
	}
	
	
	
	/**
     * @modifies this
     * @effects deletes an observer from the observers list.
     */
	@Override
	public void deleteObserver(Observer observer) {
		checkRep();
		Panel newPanel = (Panel)observer;
		observers.remove(newPanel);
		checkRep();
	}
	
	
	/**
     * @effects notifies to all observers in the list that a change has been made.
     */
	@Override
	public void notifyObservers() {
		checkRep();
		try {
			notifyAlgorithm.notify(observers, this);
		}catch(InterruptedException e) {
			System.out.println("Program faild");
		}
		checkRep();
	}
	
	
	/**
	 * @modifies this 
     * @effects adds a graphics object to this in order to paint this on it.
     */
	public void updateGraphics(Graphics g) {
		checkRep();
		this.graphics = g;
	}
	
	
	
	/**
     * @returns graphics object of this.
     */
	public Graphics getGraphics() {
		checkRep();
		return graphics;
	}
	
	
	
	/**
     * @effects updates to notify algorithm of this.
     */
	public void changeNotifyAlgorithm(NotifyObserversAlgorithm algorithm) {
		checkRep();
		notifyAlgorithm = algorithm;
	}
	
	
	/**
     * @returns this. the method implements the singelton design pattern.
     */
	public static ColorGenerator getInstance() {
		if(colorGenerator == null) {
			colorGenerator = new ColorGenerator();
		}
		return colorGenerator;
	}
	
	
	/**
	 * @modifies this 
     * @effects sets a new random color at this and notifies the observers that a change has been made.
     */
	public void setState() {
		checkRep();
		int red = rand.nextInt(VALUE);
		int green = rand.nextInt(VALUE);
		int blue = rand.nextInt(VALUE); 
		color = new Color(red,green,blue);
		setChanged();
		notifyObservers();
		checkRep();
	}
	
	
	/**
     * @returns The color of this.
     */
	public Color getState() {
		checkRep();
		return color;
	}
}
