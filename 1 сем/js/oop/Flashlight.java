import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Flashlight extends JPanel {
    private Color lightColor;
    private Color selectedColor;
    private boolean isFlashing;
    private Timer timer;

    public Flashlight() {
        lightColor = null; 
        selectedColor = Color.black;
        isFlashing = false;
        timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lightColor = lightColor == Color.BLACK ? selectedColor : Color.black;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(lightColor != null){
        g.setColor(lightColor);
        g.fillOval(10, 10, 80, 80);
        }
       
    }

    public void setColor(Color color) {
        selectedColor = color;
        if (!isFlashing) {
            lightColor = color;
            repaint();
        }
    }

    public void startFlashing() {
        isFlashing = true;
        timer.start();
    }

    public void stopFlashing() {
        isFlashing = false;
        timer.stop();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Flashlight App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 200);

        Flashlight flashlight = new Flashlight();
        frame.add(flashlight);

        JButton createButton = new JButton("Створити");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color selectedColor = JColorChooser.showDialog(frame, "Виберіть колір", Color.YELLOW);
                flashlight.setColor(selectedColor);
            }
        });

        JButton startButton = new JButton("Стартувати");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flashlight.startFlashing();
            }
        });

        JButton stopButton = new JButton("Зупинити");
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flashlight.stopFlashing();
            }
        });

        JPanel controlPanel = new JPanel();
        controlPanel.add(createButton);
        controlPanel.add(startButton);
        controlPanel.add(stopButton);

        frame.add(controlPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}
