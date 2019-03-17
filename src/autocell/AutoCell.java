package autocell;

import javax.swing.JFrame;

import cell.Cell;
import field.Field;
import field.View;

public class AutoCell {

	public static void main(String[] args) {
		
		Field field = new Field(30, 30);
		for(int x=0; x<field.getHeight(); x++) {
			for(int y=0; y<field.getWidth(); y++) {
				field.place(x, y, new Cell());
			}
		}
		
		for(int x=0; x<field.getHeight(); x++) {
			for(int y=0; y<field.getWidth(); y++) {
				Cell cell = field.get(x, y);
				if(Math.random()<=0.2) {
					cell.reborn();
				}
			}
		}		
		
		View v = new View(field);
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setTitle("Cells");
		frame.add(v);
		frame.pack();
		frame.setVisible(true);
		
		for(int i=0;i<1000;i++) {
			
			for(int x=0; x<field.getHeight(); x++) {
				for(int y=0; y< field.getWidth(); y++) {
					Cell cell = field.get(x, y);
					Cell[] neighbours = field.getNeighbours(x, y);
					int numOfCells = 0;
					for(Cell c:neighbours) {
						if(c.isAlive()) {
							numOfCells++;
						}
					}
					System.out.print(i+":"+"("+x+","+y+"):this cell was ");
					System.out.print(cell.isAlive()?"alive":"dead");
					System.out.print(" --> now is ");
					if(cell.isAlive()) {
						if(numOfCells<2 || numOfCells>3) {
							cell.die();
							System.out.println("dead");
						}else {
							System.out.println("still alive");
						}
					}else if(numOfCells==3) {
						cell.reborn();
						System.out.println("alive");
					}else {
						System.out.println("still dead");
					}
				}
			}

			System.out.println("UPDATE");
			frame.repaint();
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}		

	}

}
