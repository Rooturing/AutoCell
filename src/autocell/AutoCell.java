package autocell;

import javax.swing.JFrame;

import cell.Cell;
import field.Field;
import field.View;

public class AutoCell {

    public static void main(String[] args) {

        Field field = new Field(30, 30);
        Field tempField = new Field(60, 60);//创建一个缓存区域，以保存一轮更新的过程中产生的临时数据

        for (int x = 0; x < field.getHeight(); x++) {
            for (int y = 0; y < field.getWidth(); y++) {
                field.place(x, y, new Cell());
                tempField.place(x, y, new Cell());//初始化field的同时，也对tempField进行初始化
            }
        }

        //随机唤醒区域内20%的细胞
        for (int x = 0; x < field.getHeight(); x++) {
            for (int y = 0; y < field.getWidth(); y++) {
                Cell cell = field.get(x, y);
                Cell copy = tempField.get(x, y);
                if (Math.random() <= 0.2) {
                    cell.reborn();
                    copy.reborn();//同时唤醒tempField区域内的对应细胞
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

        for (int i = 0; i < 1000; i++) {
            //在以下每一次循环中，都应该从field里读取数据进行判定，但只对tempField做更新，在最后再将tempField里的数据复制到field里
            for (int x = 0; x < field.getHeight(); x++) {
                for (int y = 0; y < field.getWidth(); y++) {
                    Cell cell = field.get(x, y);
                    Cell[] neighbours = field.getNeighbours(x, y);
                    int numOfCells = 0;
                    for (Cell c : neighbours) {
                        if (c.isAlive()) {
                            numOfCells++;
                        }
                    }
                    System.out.print(i + ":" + "(" + x + "," + y + "):this cell was ");
                    System.out.print(cell.isAlive() ? "alive" : "dead");
                    System.out.print(" --> now is ");
                    if (cell.isAlive()) {
                        if (numOfCells < 2 || numOfCells > 3) {
//                            cell.die();
                            tempField.get(x, y).die();
                            System.out.println("dead");
                        } else {
                            System.out.println("still alive");
                        }
                    } else if (numOfCells == 3) {
//                        cell.reborn();
                        tempField.get(x, y).reborn();
                        System.out.println("alive");
                    } else {
                        System.out.println("still dead");
                    }
                }
            }

            //在field中更新tempField中的改变
            for (int x = 0; x < field.getHeight(); x++) {
                for (int y = 0; y < field.getWidth(); y++) {
                    Cell cell = field.get(x, y);
                    Cell copy = tempField.get(x, y);
                    if (copy.isAlive()) {
                        cell.reborn();
                    } else {
                        cell.die();
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
