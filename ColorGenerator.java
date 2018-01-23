package homework4;


import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;



public class ColorGenerator extends Observable {
	
	private static ColorGenerator colorGenerator = null;
	
	private Color color;
	private Random rand;
	private Graphics graphics;
	private NotifyObserversAlgorithm notifyAlgorithm;
	private ArrayList<Panel> observers;
	
	private static final int VALUE = 256;
	
	
	
	private ColorGenerator() {
		rand = new Random();
		color = new Color(rand.nextFloat(),rand.nextFloat(),rand.nextFloat());
		notifyAlgorithm = new IncreasingOrder();
		observers = new ArrayList<>();
	}
	
	
	
	@Override
	public void addObserver(Observer observer) {
		Panel newPanel = (Panel)observer;
		observers.add(newPanel);
	}
	
	
	
	
	@Override
	public void deleteObserver(Observer observer) {
		Panel newPanel = (Panel)observer;
		observers.remove(newPanel);
	}
	
	
	
	@Override
	public void notifyObservers() {
		try {
			notifyAlgorithm.notify(observers, this,this.graphics);
		}catch(InterruptedException e) {
			System.out.println("Program faild");
		}
	}
	
	
	public void updateGraphics(Graphics g) {
		this.graphics = g;
	}
	
	
	public void changeNotifyAlgorithm(NotifyObserversAlgorithm algorithm) {
		notifyAlgorithm = algorithm;
	}
	
	
	
	public static ColorGenerator getInstance() {
		if(colorGenerator == null) {
			colorGenerator = new ColorGenerator();
		}
		return colorGenerator;
	}
	
	
	
	public void setState() {
		int red = rand.nextInt(VALUE);
		int green = rand.nextInt(VALUE);
		int blue = rand.nextInt(VALUE); 
		color = new Color(red,green,blue);
		setChanged();
		notifyObservers();
	}
	
	
	
	public Color getState() {
		return color;
	}
}
