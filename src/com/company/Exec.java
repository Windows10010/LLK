package com.company;

import javax.script.*;
import javax.swing.*;
import java.awt.*;

public class Exec {
	public static void main(String[] args) {
		JFrame frame = new JFrame("连连看 V0.1");
		frame.setLayout(new BoxLayout(frame.getContentPane(),BoxLayout.X_AXIS));
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setSize(1024,820);

		Main main = new Main(true);
		JScrollPane additionalPane = new JScrollPane(main);
		additionalPane.setMinimumSize(new Dimension(720,800));
		additionalPane.setMaximumSize(new Dimension(820,820));
//		additionalPane.setLayout(new FlowLayout(FlowLayout.CENTER));
		additionalPane.createVerticalScrollBar(); additionalPane.createHorizontalScrollBar();
//		additionalPane.add(main);
		additionalPane.setAutoscrolls(true);

		JSplitPane mainPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,additionalPane,new JScrollPane(new JLabel("Hello!")));
		mainPane.setOneTouchExpandable(true);

		frame.add(mainPane);
		frame.setVisible(true);
	}
}