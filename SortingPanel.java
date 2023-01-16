import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class SortingPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private Random random;
    private int[] array;
    private int delay=10;
    private BinaryInsertionSort binarySort;

    private JButton start;
    private JButton reset;

    private boolean isRunning;

    public SortingPanel() {

        random = new Random();
        array = new int[80];
        this.setArray();

        binarySort =new BinaryInsertionSort(array);

        start = new JButton("start");
        reset = new JButton("reset");

        start.setModel(new ButtonModel());
        start.setBackground(Color.WHITE);
        start.setFocusPainted(false);
        start.setBorderPainted(false);
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    start.setBackground(Color.lightGray);
                    if (isRunning == false)
                        isRunning = true;
                    animate();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        reset.setModel(new ButtonModel());
        reset.setBackground(Color.WHITE);
        reset.setFocusPainted(false);
        reset.setBorderPainted(false);
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                reset.setBackground(Color.lightGray);
                start.setBackground(Color.WHITE);

                setArray();

                //reset binarySort object
                binarySort.setCompareIndex(Integer.MAX_VALUE);
                binarySort.setArrayIndex(Integer.MAX_VALUE);
                binarySort.setArray(array);
                binarySort.setStartOfIteration(false);

                isRunning = false;

                Timer timer  = new Timer(100, new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        reset.setBackground(Color.WHITE);
                        ((Timer)e.getSource()).stop();
                    }
                });

                timer.start();
                repaint();
            }
        });

        this.add(start);
        this.add(reset);
    }

    public int[] getArray() {
        return this.array;
    }

    public void setArray() {
        for (int i = 0; i < this.array.length; i++) {
            this.array[i] = random.nextInt(510) + 40;
        }
    }

    public boolean isSorted() {

        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }

        return true;
    }

    public void animate() throws Exception{
        if (isRunning) {
            binarySort.setArrayIndex(1);
            Timer binaryTimer  = new Timer(delay, new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {

                    if (isSorted() || isRunning == false) {
                        binarySort.setCompareIndex(Integer.MAX_VALUE);
                        binarySort.setArrayIndex(Integer.MAX_VALUE);
                        binarySort.setStartOfIteration(false);
                        isRunning = false;
                        start.setBackground(Color.WHITE);
                        ((Timer)e.getSource()).stop();
                    }
                    else {
                        if (isRunning == true)
                            array = binarySort.sortOnlyOneItem();
                    }
                    repaint();
                }
            });
            binaryTimer.start();
        }
    }
    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        this.setBackground(Color.BLACK);

        for (int i = 0; i < array.length; i++) {

            g.setColor(Color.BLACK);
            g.drawRect(i*15, 600 - array[i], 14, array[i]);

            if (isRunning) {
                if(i==binarySort.getMid()){
                    g.setColor(Color.blue);
                }

                if (i == binarySort.getCompareIndex() || i == (binarySort.getCompareIndex() + 1)) {
                    g.setColor(Color.MAGENTA);
                }

                if (i == binarySort.getArrayIndex()) {
                    g.setColor(Color.GREEN);
                }
            }

            if (g.getColor() != Color.MAGENTA && g.getColor() != Color.GREEN && g.getColor() != Color.BLUE && g.getColor() != Color.RED)
                g.setColor(Color.CYAN);

            g.fillRect(i*15, 600 - array[i], 14, array[i]);
        }
    }
}