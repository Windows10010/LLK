package com.company;

import sun.tools.jstat.Alignment;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class JUtilPane extends JPanel{
    JTimingLabel time = new JTimingLabel(320);
    String formattedTime = null;
    JToggleButton toggleButton = new JToggleButton();
    private class JTimingLabel extends JLabel{
        Thread timing;
        public JTimingLabel(){
            super();
            this.setVerticalAlignment(CENTER);
            this.setHorizontalAlignment(CENTER);
            this.setFont(new Font("hop",Font.BOLD,22));
            timing = new Thread(){
                @Override
                public void run() {
                    int second = 0,minute = 0;
                    while (true) {
                        try{Thread.sleep(1000);}catch (Exception e){e.printStackTrace();}
                        second++;
                        if(second == 60){
                            minute++;
                            second = 0;
                        }
                        formattedTime = ((minute<=9)? "0" + String.valueOf(minute): String.valueOf(minute)) + ":" + ((second<=9)? "0" + String.valueOf(second): String.valueOf(second));
                        setText(formattedTime);
                    }
                }
            };
            timing.start();
        }
        public JTimingLabel(final int secondToRewind){
            super();
            this.setFont(new Font("hop",Font.BOLD,22));
            timing = new Thread(){
                int second = secondToRewind%60;
                int minute = (int)secondToRewind/60;
                @Override
                public void run() {
                    while(true){
                        try{Thread.sleep(1000);}catch(Exception e){e.printStackTrace();}
                        second--;
                        if(second == 0){
                            second = 60;
                            minute--;
                        }
                        formattedTime = ((minute<=9)? "0" + String.valueOf(minute): String.valueOf(minute)) + ":" + ((second<=9)? "0" + String.valueOf(second): String.valueOf(second));
                        setText(formattedTime);
                    }
                }
            };
            timing.start();
        }
    }

    public JUtilPane(){
        super();
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        System.out.println();
        this.add(time);
        this.add(toggleButton);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(320,320);
        frame.setLayout(new BoxLayout(frame.getContentPane(),BoxLayout.PAGE_AXIS));

        frame.add(new JUtilPane());

        frame.setVisible(true);
        new JApplet().add(new Main(false));
    }
}
