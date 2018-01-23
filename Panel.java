package homework4;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Observable;
import java.util.Observer;



/**
 * A Panel is one of 25 square panels of Billboard. A typical Panel consists of a set of properties: {color, size}.
 * Panel color is changeable and Panel size is unchangeable.
 * Panel observes at all time on ColorGenerator and update is color according to the generator.
 */
public class Panel implements Observer {
	
	private Color color;
	private Rectangle size;
	private final int id;
	
	private static int panelNumber = 1;
	private static final int width = 90;
	private static final int height = 90;
	private static final int space = 25;
	private static final int rawSize = 5;
	
	
	// Abstraction Function:
    // 	 represents a panel with location = (x,y) (point on Two-dimensional space) at this.size.x/y   
    //   and a color at this.color. panel width will be Panel.width and panel height will be Panel.heigth.
	//	 the default color is gray.

    // Representation Invariant:
	//	1) size width > 0 &&  size height > 0
	//	2) color != NULL
	//	3) id < 25 
	
	
	/**
     * Checks to see if the representation invariant is being violated.
     * @throws AssertionError if representation invariant is violated.
     */ 
    private void checkRep() {
    	assert(panelNumber <= 25) : "The maximum number of panels is 25";
    	assert(size.height > 0 && size.width > 0) : "Height or width of Panel can't be negative";
    	assert(color !=null) : "Panel color can't be null";
    }
	
	
    /**
     * @effects Initializes this with a specific location(according to this id) and default color.
     */
	public Panel() {
		id = panelNumber;
		color = Color.gray;
		if(1 <= panelNumber && panelNumber <= 5) {
			size = new Rectangle(space +(id-1)*(width+space),space,width,height);
		}
		else if(6 <= panelNumber && panelNumber <= 10) {
			size = new Rectangle(space + (id-1)%rawSize * (width+space),space + (height+ space),width,height);
		}
		else if(11 <= panelNumber && panelNumber <= 15) {
			size = new Rectangle(space + (id-1)%rawSize * (width+space),space + 2*(height+ space),width,height);
		}
		else if(16 <= panelNumber && panelNumber <= 20) {
			size = new Rectangle(space +(id-1)%rawSize * (width+space),space + 3*(height+ space),width,height);
		}
		else if(21 <= panelNumber && panelNumber <= 25) {
			size = new Rectangle(space + (id-1)%rawSize * (width+space),space + 4*(height+ space),width,height);
		}
		++panelNumber;
		checkRep();
	}
	
	
	
	/**
     * @modifies this
     * @effects Sets color of this.
     */
	public void setColor(Color color) {
		checkRep();
		this.color = color;
		checkRep();
	}
	
	
	
	/**
     * @return color of this.
     */
	public Color getColor() {
		checkRep();
		return color;
	}
	
	
	
	/**
     * @modifies g
     * @effects Draws this onto g.
     */
	public void draw(Graphics g) {
		checkRep();
		g.setColor(getColor());
		g.fillRect(size.x,size.y,size.width,size.height);
		checkRep();
	}
	
	
	
	/**
     * @modifies this
     * @effects Updates the color of this according to the observable and draws this again.
     */
	public void update(Observable obs, Object obj) {
		checkRep();
		Graphics g = (Graphics)obj;
		ColorGenerator generator = (ColorGenerator)obs;
		setColor(generator.getState());
		draw(g);
		checkRep();
	}

}
