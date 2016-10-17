import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 * @author Huyen
 *
 */
public class BouncingBall extends JFrame {
	private static final int WIDTH = 400;
	private static final int HEIGHT = 400;
	private static final int UPDATETIME = 5;
	private DrawingCanvas canvas;
	int x = 50, y = 50;
	int size = 50;
	int xSpeed = 1, ySpeed = 2;
	Color[] ballColorArray = { Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.GRAY, Color.PINK, Color.BLACK };
	Color ballColor = Color.BLACK;
	
	int mouseX = 0, mouseY =0;
	public BouncingBall() {
		canvas = new DrawingCanvas();
		canvas.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setContentPane(canvas);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Bouncing Ball");
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		//Mouse Motion listener
		this.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				
				
			}
		});
		//Repeated task
		ActionListener updateTask = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				x += xSpeed;
				y += ySpeed;
				if (x < 0 || x > WIDTH - size) {
					xSpeed = -xSpeed;
					changeBallColor();
				}
				if (y < 0 || y > HEIGHT - size) {
					ySpeed = -ySpeed;
					changeBallColor();
				}
				repaint();
			}
		};
		//Timer
		Timer timer = new Timer(UPDATETIME, updateTask);
		//Start the timer
		timer.start();
	}

	/**
	 * Change the color of the ball to random color
	 */
	public void changeBallColor() {
		Random rnd = new Random();
		ballColor = ballColorArray[rnd.nextInt(ballColorArray.length)];
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new BouncingBall();
			}
		});

	}

	private class DrawingCanvas extends JPanel {

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			setBackground(Color.WHITE);
			//Ball
			g.setColor(ballColor);
			g.fillOval(x, y, size, size);
			//Mouse trail
			g.setColor(Color.RED);
			g.fill3DRect(mouseX-10, mouseY-10, 20, 20,true);
		}
	}
}
