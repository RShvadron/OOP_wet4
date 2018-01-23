package homework4;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;


import javax.swing.*;

/**
 * Billboard is a graphical user interface (GUI) of Billboard with 25 panels. The Billboard panels can change
 * their color every 2 seconds following the ColorGenerator. The Billboard lets the user through his GUI to 
 * choose the order in witch the panels change their color.
 * Thus, a typical Billboard consists of the following properties: {menuBar, mainPanel, panels, colorGenerator }.
 */
@SuppressWarnings("serial")
public class Billboard extends JFrame implements ActionListener {

	//constants
	private static final int COLOR_CHANGE_DELAY = 2000;
	private static final int NUMBER_OF_PANELS = 25;
	
	// preferred frame width and height.
	private static final int WINDOW_WIDTH = 600;
	private static final int WINDOW_HEIGHT = 600;
	
	// graphical components
	private JMenuBar menuBar;
	private JMenu fileMenu, colorChangingMenu;
	private JRadioButtonMenuItem increasingItem, columnItem,randomItem, parityItem;
	private JMenuItem exitItem, aboutItem;
	private JPanel mainPanel;

	//private fields
	private ArrayList<Panel> panels;
	private ColorGenerator colorGenerator;

	
	
	// Abstraction Function:
    //    Represents a Billboard that is consisted of 25 Panels witch are viewed as a matrix of 5x5. panels are fixed size squares stored at this.
    //    In addition to a main panel at this.mainPanel. Various graphical elements are implemented in the Billboard,
    //    such are this.menuBar, this.colorChangingMenu and this.fileMenu.
    //    The ColorGenerator that determines which color the panels will display is referenced at this.colorGenerator.

	// Representation Invariant:
	//	 1) size width > 0 &&  size height > 0
	//	 2) color != NULL
	//	 3) id < 25 
	
	
	
	/**
     * Checks to see if the representation invariant is being violated.
     * @throws AssertionError if representation invariant is violated.
     */ 
    private void checkRep() {
    	assert(panels.size() == NUMBER_OF_PANELS) : "Only 25 panels are allowed";
    	assert(colorGenerator != null) : "ColorGnerator can't be null";
    	assert(panels !=null) : "Panels can't be null";
    	assert(menuBar != null && fileMenu != null && colorChangingMenu != null && increasingItem != null && columnItem != null && randomItem != null
    			&& parityItem != null && exitItem != null && aboutItem != null && mainPanel != null);
    	
    	
    }
	
	
	
	/**
	 * @modifies this
	 * @effects Initializes the GUI and enables a timer that steps animation of
	 *          all shapes in this 25 times per second while animation check box is selected.
	 */
	public Billboard() {
		super("Billboard");
		// create main panel and menu bar
		mainPanel = (JPanel) createMainPanel();
		getContentPane().add(mainPanel);
		menuBar = (JMenuBar) createMenuBar();
		setJMenuBar(menuBar);
		colorGenerator = ColorGenerator.getInstance();
		panels = new ArrayList<>();
		createBillboard();
		checkRep();
	}

	/**
	 * @return main GUI panel.
	 */
	private JComponent createMainPanel() {
		checkRep();
		JPanel mainPanel = new JPanel();
		mainPanel.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
		mainPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		mainPanel.setBackground(Color.WHITE);
		checkRep();
		return mainPanel;
	}

	/**
	 * @return main GUI menu bar.
	 */
	private JMenuBar createMenuBar() {
		checkRep();
		JMenuBar menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		aboutItem = new JMenuItem("About");
		aboutItem.addActionListener(this);
		fileMenu.add(aboutItem);
		menuBar.add(fileMenu);
		exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(this);
		fileMenu.add(exitItem);
		menuBar.add(fileMenu);

		colorChangingMenu = new JMenu("Painting Order");
		increasingItem = new JRadioButtonMenuItem("Increasing Order");
		increasingItem.addActionListener(this);
	    colorChangingMenu.add(increasingItem);
	    columnItem = new JRadioButtonMenuItem("Column Order");
	    columnItem.addActionListener(this);
	    colorChangingMenu.add(columnItem);
	    randomItem = new JRadioButtonMenuItem("Random Order");
	    randomItem.addActionListener(this);
	    colorChangingMenu.add(randomItem);
	    parityItem = new JRadioButtonMenuItem("Odd/Even Order");
	    parityItem.addActionListener(this);
	    colorChangingMenu.add(parityItem);
	    ButtonGroup group = new ButtonGroup();
	    group.add(increasingItem);
	    group.add(columnItem);
	    group.add(randomItem);
	    group.add(parityItem);
	    menuBar.add(colorChangingMenu);
	    checkRep();
		return menuBar;
	}

	/**
	 * @modifies g
	 * @effects Paint this including all its shapes to g. This method is invoked
	 *          by Swing to draw components. It should not be invoked directly,
	 *          but the repaint method should be used instead in order to
	 *          schedule the component for redrawing.
	 */
	public void paint(Graphics g) {
		checkRep();
		super.paint(g);
		colorGenerator.updateGraphics(getContentPane().getGraphics());
		for (Iterator<Panel> panelsIterator = this.panels.iterator(); panelsIterator.hasNext();) {
			Panel panel = panelsIterator.next();
			panel.draw(getContentPane().getGraphics());
		}

	}

	/**
	 * @modifies this
	 * @effects Invoked when the user selects an action from the menu bar and
	 *          performs the appropriate operation.
	 */
	public void actionPerformed(ActionEvent e) {
		JMenuItem source = (JMenuItem) (e.getSource());

		// File->Exit: close application
		if (source.equals(exitItem)) {
			dispose();
		}
		else if ((source.equals(increasingItem)) || (source.equals(columnItem)) || (source.equals(parityItem)) || (source.equals(randomItem))) {

			if (source.equals(increasingItem)) {
				colorGenerator.changeNotifyAlgorithm(new IncreasingOrder());
			} 
			else if (source.equals(columnItem)) {	
				colorGenerator.changeNotifyAlgorithm(new ColumnOrder());
			}
			else if (source.equals(parityItem)) {
				colorGenerator.changeNotifyAlgorithm(new JumpOrder());
			}
			else if (source.equals(randomItem)) {
				colorGenerator.changeNotifyAlgorithm(new RandomOrder());
			}
			repaint();
		}
		
		// Help->About : show about message dialog
		else if (source.equals(aboutItem)) {
			JOptionPane.showMessageDialog(this, "Billboard - 4th" + " homework assignment", "About",
					JOptionPane.INFORMATION_MESSAGE);
		}
		checkRep();
	}
	
	
	/**
	 * @modifies this
	 * @effects Creates 25 Billboard panels and inserts them into this panels list 
	 * 			and into colorGenerator observers list.
	 */
	public void createBillboard() {
		for(int i = 0 ; i < NUMBER_OF_PANELS; ++i) {
			Panel panel = new Panel();
			this.panels.add(panel);
			this.colorGenerator.addObserver(panel);
		}
	}
	
	
	/**
	 * @modifies this
	 * @effects paints the updated panel again on the Billboard
	 */
	public void updateBillboard(int index) {
		checkRep();
		panels.get(index).draw(getContentPane().getGraphics());
		checkRep();
	}
	
	

	/**
	 * @effects Billboard application.
	 */
	public static void main(String[] args) {
		Billboard application = new Billboard();
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		application.setResizable(false);
		application.pack();
		application.setVisible(true);
		
		Timer timer = new Timer(COLOR_CHANGE_DELAY, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                 ColorGenerator.getInstance().setState();
            }
        }) ;
        timer.start();
	}
}
