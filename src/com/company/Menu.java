// Ramy ElGendi
// 900170269

package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.Hashtable;

public class Menu extends Container {
    private Settings settings;
    private final JButton b=new JButton("Begin Program");
    private final JFrame jFrame = new JFrame("Covid Tracker");
    private final JSlider slider_width = new JSlider(JSlider.HORIZONTAL, 0, 100, 30);
    private final JSlider slider_height = new JSlider(JSlider.HORIZONTAL, 0, 100, 30);
    private final JSlider slider_stars = new JSlider(JSlider.HORIZONTAL, 0, 100, 30);
    private final JSlider slider_covid = new JSlider(JSlider.HORIZONTAL, 0, 100, 10);
    private final JSlider slider_walk = new JSlider(JSlider.HORIZONTAL, 0, 180, 60);
    private final JSlider slider_waitMIN = new JSlider(JSlider.HORIZONTAL, 0, 5000, 500);
    private final JSlider slider_waitMAX = new JSlider(JSlider.HORIZONTAL, 0, 5000, 1000);
    private final JSlider slider_safe = new JSlider(JSlider.HORIZONTAL, 0, 25, 1);
    private final JSlider slider_step = new JSlider(JSlider.HORIZONTAL, 0, 30, 10);
    private final JSlider slider_timetoflag = new JSlider(JSlider.HORIZONTAL, 0, 30, 2);

    public JButton getB() { return b; }
    public Settings getSettings() { return settings; }
    public JFrame getjFrame() { return jFrame; }

    public Menu() {
        // Hashtable For Labels
        Hashtable<Integer, JLabel> position = new Hashtable<>();        // Add positions label in the slider
        position.put(0, new JLabel("0"));
        position.put(25, new JLabel("25"));
        position.put(50, new JLabel("50"));
        position.put(75, new JLabel("75"));
        position.put(100, new JLabel("100"));

        Hashtable<Integer, JLabel> time = new Hashtable<>();        // Add positions label in the slider
        time.put(0, new JLabel("0"));
        time.put(30, new JLabel("30"));
        time.put(60, new JLabel("60"));
        time.put(90, new JLabel("90"));
        time.put(120, new JLabel("120"));
        time.put(150, new JLabel("150"));
        time.put(180, new JLabel("180"));

        Hashtable<Integer, JLabel> small_time = new Hashtable<>();        // Add positions label in the slider
        small_time.put(0, new JLabel("0"));
        small_time.put(500, new JLabel("0.5"));
        small_time.put(1000, new JLabel("1"));
        small_time.put(1500, new JLabel("1.5"));
        small_time.put(2000, new JLabel("2"));
        small_time.put(2500, new JLabel("2.5"));
        small_time.put(3000, new JLabel("3"));
        small_time.put(3500, new JLabel("3.5"));
        small_time.put(4000, new JLabel("4"));
        small_time.put(4500, new JLabel("4.5"));
        small_time.put(5000, new JLabel("5"));

        Hashtable<Integer, JLabel> step_distance = new Hashtable<>();        // Add positions label in the slider
        step_distance.put(0, new JLabel("0"));
        step_distance.put(10, new JLabel("1"));
        step_distance.put(20, new JLabel("2"));
        step_distance.put(30, new JLabel("3"));

        // Labels
        JLabel width = new JLabel("Frame Width: 30 m", JLabel.CENTER);
        JLabel height = new JLabel("Frame Height: 30 m", JLabel.CENTER);
        JLabel stars = new JLabel("Stars Amount: 30", JLabel.CENTER);
        JLabel covid = new JLabel("Covid Percent: 10%", JLabel.CENTER);
        JLabel walk = new JLabel("Walking Time: 60 sec", JLabel.CENTER);
        JLabel waitMIN = new JLabel("Min Time To Wait: 0.5 sec", JLabel.CENTER);
        JLabel waitMAX = new JLabel("Max Time To Wait: 1.0 sec", JLabel.CENTER);
        JLabel safe = new JLabel("Safe Social Distance: 1 m", JLabel.CENTER);
        JLabel step = new JLabel("Walking Step: 1.0 m", JLabel.CENTER);

        JLabel timetoflag = new JLabel("Time To Flag: 2 sec", JLabel.CENTER);

        slider_width.setLabelTable(position);slider_width.setMajorTickSpacing(5);slider_width.setPaintTicks(true);
        slider_width.setPaintLabels(true);slider_width.setFocusable(false);

        slider_height.setLabelTable(position);slider_height.setMajorTickSpacing(5);slider_height.setPaintTicks(true);
        slider_height.setPaintLabels(true);slider_height.setFocusable(false);

        slider_stars.setLabelTable(position);slider_stars.setMajorTickSpacing(5);slider_stars.setPaintTicks(true);
        slider_stars.setPaintLabels(true);slider_stars.setFocusable(false);

        slider_covid.setLabelTable(position);slider_covid.setMajorTickSpacing(5);slider_covid.setPaintTicks(true);
        slider_covid.setPaintLabels(true);slider_covid.setFocusable(false);

        slider_walk.setLabelTable(time);slider_walk.setMajorTickSpacing(30);slider_walk.setPaintTicks(true);
        slider_walk.setPaintLabels(true);slider_walk.setFocusable(false);

        slider_waitMIN.setLabelTable(small_time);slider_waitMIN.setMajorTickSpacing(500);slider_waitMIN.setPaintTicks(true);
        slider_waitMIN.setPaintLabels(true);slider_waitMIN.setFocusable(false);slider_waitMIN.setSnapToTicks(true);

        slider_waitMAX.setLabelTable(small_time);slider_waitMAX.setMajorTickSpacing(500);slider_waitMAX.setPaintTicks(true);
        slider_waitMAX.setPaintLabels(true);slider_waitMAX.setFocusable(false);slider_waitMAX.setSnapToTicks(true);

        slider_safe.setLabelTable(position);slider_safe.setMajorTickSpacing(1);slider_safe.setPaintTicks(true);
        slider_safe.setPaintLabels(true);slider_safe.setFocusable(false);

        slider_step.setLabelTable(step_distance);slider_step.setMajorTickSpacing(5);slider_step.setPaintTicks(true);
        slider_step.setPaintLabels(true);slider_step.setFocusable(false);

        slider_timetoflag.setLabelTable(time);slider_timetoflag.setMajorTickSpacing(1);slider_timetoflag.setPaintTicks(true);
        slider_timetoflag.setPaintLabels(true);slider_timetoflag.setFocusable(false);

        slider_width.addChangeListener(e -> width.setText("Frame Width: " + ((JSlider)e.getSource()).getValue()+" m"));

        slider_height.addChangeListener(e -> height.setText("Frame Height: " + ((JSlider)e.getSource()).getValue()+" m"));

        slider_stars.addChangeListener(e -> stars.setText("Stars Amount: " + ((JSlider)e.getSource()).getValue()));

        slider_covid.addChangeListener(e -> covid.setText("Covid Percent: " + ((JSlider)e.getSource()).getValue()+"%"));

        slider_walk.addChangeListener(e -> walk.setText("Walking Time: " + ((JSlider)e.getSource()).getValue()+" sec"));

        slider_waitMIN.addChangeListener(e -> waitMIN.setText("Min Time To Wait: " + ((JSlider)e.getSource()).getValue()/1000.0+" sec"));

        slider_waitMAX.addChangeListener(e -> waitMAX.setText("Max Time To Wait: " + ((JSlider)e.getSource()).getValue()/1000.0+" sec"));

        slider_safe.addChangeListener(e -> safe.setText("Safe Social Distance: " + ((JSlider)e.getSource()).getValue()+" m"));

        slider_timetoflag.addChangeListener(e -> timetoflag.setText("Time To Flag: " + ((JSlider)e.getSource()).getValue()+" sec"));
        slider_step.addChangeListener(e -> step.setText("Walking Step: " + ((JSlider)e.getSource()).getValue()/10.0+" m"));

        JPanel panel=new JPanel(new GridLayout(0, 2));
        JPanel panel2=new JPanel();

        panel.add(width);
        panel.add(slider_width);
        panel.add(height);
        panel.add(slider_height);
        panel.add(stars);
        panel.add(slider_stars);
        panel.add(covid);
        panel.add(slider_covid);
        panel.add(walk);
        panel.add(slider_walk);
        panel.add(waitMIN);
        panel.add(slider_waitMIN);
        panel.add(waitMAX);
        panel.add(slider_waitMAX);
        panel.add(safe);
        panel.add(slider_safe);
        panel.add(step);
        panel.add(slider_step);
        panel.add(timetoflag);
        panel.add(slider_timetoflag);

        jFrame.setLayout(new BorderLayout());
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jFrame.setSize(500,800);
        jFrame.setResizable(false);

        b.setBounds(50,100,95,30);

        panel2.add(panel);
        panel2.add(b);
        jFrame.add(panel2);
        jFrame.repaint();
        jFrame.setVisible(true);
        jFrame.repaint();
    }

    void setSettings(){
        settings = new Settings(slider_width.getValue(),slider_height.getValue()+2,10,slider_stars.getValue(),slider_covid.getValue()*slider_stars.getValue()/100,slider_step.getValue(),slider_walk.getValue(),slider_waitMIN.getValue(),slider_waitMAX.getValue(),slider_timetoflag.getValue(),slider_safe.getValue());
    }
}
