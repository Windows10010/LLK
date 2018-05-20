package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class Main extends JPanel implements ActionListener {
	protected LButton[][] BS = new LButton[10][10];
	protected LButton Temproray = null;
	private ArrayList<PointSet> procedures = new ArrayList<PointSet>();

	private class PointSet {
		Point p1, p2;

		public PointSet(Point arg0, Point arg1) {
			p1 = arg0;
			p2 = arg1;
		}
	}

	protected class LButton extends JButton {
		private int match = 0;
		private Point rpoint = null;

		public LButton(Icon arg0, int image, Point point) {
			super(arg0);
			LButton privacy = this;
			this.setSelectedIcon(new ImageIcon("icons/SelectedIcon" + image + ".jpeg"));
			this.match = image;
			this.rpoint = point;
		}

		public LButton() {
			super();
		}

		@Override
		public String toString() {
			return "/" + String.valueOf(match) + "/" + String.valueOf(rpoint.y) + "/" + String.valueOf(rpoint.x);
		}

		public Point getPoint() {
			return new Point();
		}
	}

	// protected String path = "/Users/Billy/Desktop/icons/Chess.jpge";
	Icon icon;
	Dimension solidDimension = new Dimension(720,270);

	public Main(boolean isBeSoluted) {
		super();
		System.out.println(1);
		setSize(solidDimension);
		setMinimumSize(solidDimension);
		setMaximumSize(solidDimension);
		setLayout(new GridLayout(10, 10));
		this.icon = new ImageIcon("icons/Icon1.jpeg");
		this.generate();
		this.addAll();
		setVisible(true);
		if(isBeSoluted)solution.start();
	}

	public static void main(String[] args) {
		// write your code here
		new Main(false);
	}

	protected void generate() {
		if (this.isVisible())
			this.setVisible(false);
		for (int i = 0; i < 10; i++) {
			for (int I = 0; I < 10; I++) {
				this.BS[i][I] = null;
			}
		} // clean up all the existing items
		Random random = new Random();
		int imageNumber = 1;
		while (true) {
			if (this.isAll()) {
				break;
			}
			// System.out.println(1);
			Point tempPoint1, tempPoint2;
			tempPoint1 = new Point(random.nextInt(10), random.nextInt(10));
			do {
				tempPoint2 = new Point(random.nextInt(10), random.nextInt(10));
			} while (tempPoint1.equals(tempPoint2));
			if (BS[tempPoint1.y][tempPoint1.x] == null && BS[tempPoint2.y][tempPoint2.x] == null) {// if meets the
																									// condition
				// try{Thread.sleep(300);}catch (Exception
				// e){System.err.println(e.getMessage());}
				this.procedures.add(new PointSet(tempPoint1, tempPoint2));
				BS[tempPoint1.y][tempPoint1.x] = new LButton(new ImageIcon("icons/Icon" + imageNumber + ".jpeg"),
						imageNumber, tempPoint1);
				BS[tempPoint2.y][tempPoint2.x] = new LButton(new ImageIcon("icons/Icon" + imageNumber + ".jpeg"),
						imageNumber, tempPoint2);
				if (imageNumber == 17) {
					imageNumber = 1;
				} else {
					imageNumber++;
				}
				// System.out.println("1");
			}
		}
		System.out.println("yo,man!");

	}

	private boolean isAll() {
		for (int i = 0; i < 10; i++) {
			for (int I = 0; I < 10; I++) {
				if (this.BS[i][I] == null) {
					return false;
				}
			}
		}
		return true;
	}

	private void addAll() {
		for (int i = 0; i < 10; i++) {
			for (int I = 0; I < 10; I++) {
				this.BS[i][I].addActionListener(this);
                this.BS[i][I].setFocusable(false);
				add(this.BS[i][I]);
				this.repaint();
			}

		}
        this.setVisible(true);
	}

	boolean isWon() {
		for (int i = 0; i < 10; i++) {
			for (int I = 0; I < 10; I++) {
				if (this.BS[i][I].isEnabled()){
                    return false;
                }
			}
		}
		return true;
	}

	@Override
    public void actionPerformed(ActionEvent ae) {
		LButton temp = this.BS[Integer.valueOf(ae.getSource().toString().split("/")[2])][Integer
				.valueOf(ae.getSource().toString().split("/")[3])];
		temp.setSelected(!temp.isSelected());
		if (this.Temproray == null) {
			Temproray = temp;
		} else if (this.Temproray.match == temp.match) {
			if (this.Temproray.equals(temp)) {
				temp.setSelected(false);
				Temproray.setSelected(false);
				Temproray = null;
			} else {
				Temproray.setEnabled(false);
				temp.setEnabled(false);
				Temproray = null;
				if (isWon()) {
//					if(solution.isAlive()) solution.interrupt();
					System.out.println("you won!");
					try{Thread.sleep(300);}catch (Exception e){e.printStackTrace();}
					this.removeAll();
					System.out.println("All the components have been removed.");
					this.repaint();
					this.generate();
					this.addAll();
				}
			}
		} else {
			temp.setSelected(false);
			Temproray.setSelected(false);
			Temproray = null;
		}
	}

	Thread solution = new Thread(){
	  @Override
      public void run(){
	      solute();
      }
    };

	private int solute() {
		int i = 0;
		do {
		    System.out.println("I am still doing something...");
			BS[this.procedures.get(i).p1.y][this.procedures.get(i).p1.x].doClick();
			try {
				Thread.sleep(150);
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
			BS[this.procedures.get(i).p2.y][this.procedures.get(i).p2.x].doClick();
			try {
				Thread.sleep(150);
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
			i++;

		} while (!this.isWon());
		System.out.println("hop");
		return 0;
	}
}