package field;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import cell.Cell;

public class View extends JPanel{

	private static final long serialVersionUID = -5258995676212660595L;
	private static final int GRID_SIZE = 15;
	private Field theField;
	
	public View(Field field) {
		theField = field;
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		for(int x=0; x<theField.getHeight(); x++) {
			for(int y=0; y<theField.getWidth(); y++) {
				Cell cell = theField.get(x,y);
				if(cell!=null) {
					cell.draw(g, x*GRID_SIZE, y*GRID_SIZE, GRID_SIZE);
				}
			}
		}
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(theField.getWidth()*GRID_SIZE+1, theField.getHeight()*GRID_SIZE+1);
	}	
	
	public static void main(String[] args) {
		
		Field field = new Field(10,10);
		for(int x=0; x<field.getHeight(); x++) {
			for(int y=0; y<field.getWidth(); y++) {
				field.place(x, y, new Cell());
			}
		}
		
		View v = new View(field);
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Cells");
		frame.add(v);
		frame.pack();
		frame.setVisible(true);

	}

}
