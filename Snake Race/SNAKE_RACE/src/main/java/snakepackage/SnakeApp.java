package snakepackage;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import enums.GridSize;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * @author jd-
 *
 */
public class SnakeApp {

    private static SnakeApp app;
    public static final int MAX_THREADS = 8;
    Snake[] snakes = new Snake[MAX_THREADS];
    private static final Cell[] spawn = {
        new Cell(1, (GridSize.GRID_HEIGHT / 2) / 2),
        new Cell(GridSize.GRID_WIDTH - 2,
        3 * (GridSize.GRID_HEIGHT / 2) / 2),
        new Cell(3 * (GridSize.GRID_WIDTH / 2) / 2, 1),
        new Cell((GridSize.GRID_WIDTH / 2) / 2, GridSize.GRID_HEIGHT - 2),
        new Cell(1, 3 * (GridSize.GRID_HEIGHT / 2) / 2),
        new Cell(GridSize.GRID_WIDTH - 2, (GridSize.GRID_HEIGHT / 2) / 2),
        new Cell((GridSize.GRID_WIDTH / 2) / 2, 1),
        new Cell(3 * (GridSize.GRID_WIDTH / 2) / 2,
        GridSize.GRID_HEIGHT - 2)};
    private JFrame frame;
    private static Board board;
    private JButton botonStart,botonPause,botonResume;
    private Snake worse;
    private int longest=-9999999;
    int nr_selected = 0;
    Thread[] thread = new Thread[MAX_THREADS];
    
    public SnakeApp() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        frame = new JFrame("The Snake Race");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setSize(618, 640);
        frame.setSize(GridSize.GRID_WIDTH * GridSize.WIDTH_BOX + 17,
                GridSize.GRID_HEIGHT * GridSize.HEIGH_BOX + 40);
        frame.setLocation(dimension.width / 2 - frame.getWidth() / 2,
                dimension.height / 2 - frame.getHeight() / 2);
        board = new Board();
        
        
        frame.add(board,BorderLayout.CENTER);
        
        JPanel actionsBPabel=new JPanel();
        actionsBPabel.setLayout(new FlowLayout());
        actionsBPabel.add(new JButton("Action "));
        botonStart= new JButton("Start");
        botonPause= new JButton("Pause");
        botonResume= new JButton("Resume");
        actionsBPabel.add(botonStart);
        actionsBPabel.add(botonPause);
        actionsBPabel.add(botonResume);
        
        frame.add(actionsBPabel,BorderLayout.SOUTH);
        prepareAcciones();
    }

    public static void main(String[] args) {
        app = new SnakeApp();
        app.init();
    }

    private void init() {
        
        
        
        for (int i = 0; i != MAX_THREADS; i++) {
            
            snakes[i] = new Snake(i + 1, spawn[i], i + 1);
            snakes[i].addObserver(board);
            thread[i] = new Thread(snakes[i]);
            //thread[i].start();
            
        }

        frame.setVisible(true);

        int y=0;  
        while (true) {
            int x = 0;
            for (int i = 0; i != MAX_THREADS; i++) {
                if (snakes[i].isSnakeEnd() == true) {
                	if (y==0) {
                		worse=snakes[i];
                	}
                	
                    x++;
                    y++;
                }
                longest=Math.max(snakes[i].getSnakebodyLength(),longest);

            }
            if (x == MAX_THREADS) {
                break;
            }
        }


        System.out.println("Thread (snake) status:");
        for (int i = 0; i != MAX_THREADS; i++) {

            System.out.println("["+i+"] :"+thread[i].getState());
        }
        

    }
    
    private void prepareAcciones() {
        botonStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (Thread t : thread) {
                    t.start();
                }
                botonStart.setEnabled(false);
                botonPause.setEnabled(true);
            }
        });
        
        botonPause.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (Snake s : snakes) {
                    s.pause();
                }
                botonPause.setEnabled(false);
                botonResume.setEnabled(true);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
                try {
                	JOptionPane.showMessageDialog(null, "La peor serpiente es la numero: "+worse.getIdt()+"\n Y la  serpiente mas larga mide: "+longest+" unidades.");
                } catch (NullPointerException e1) {
                	JOptionPane.showMessageDialog(null, "No hay peor serpiente ya que ninguna ha muerto \n Y la  serpiente mas larga mide: "+longest+" unidades.");
                }
            }
        });
        
        
        botonResume.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (Snake s : snakes) {
                    s.resume();
                }
                botonResume.setEnabled(false);
                botonPause.setEnabled(true);
            }
        });
    }
  
 



    public static SnakeApp getApp() {
        return app;
    }

}
