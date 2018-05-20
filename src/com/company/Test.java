package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Test extends JFrame {
	public Test() {
		super();
		this.setLayout(null);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setSize(320, 640);
		this.setLayout(new GridLayout(1, 1));
		JButton button = new JButton(new ImageIcon("chess.jpg"));
		this.add(button);
		this.setVisible(true);
	}

	public static void main(String[] args) throws IOException {
		new Test();
	}
}
