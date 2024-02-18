import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Str {
    private JTextField inputField;
    private JTextArea outputArea;

    public Str() {
        JFrame frame = new JFrame("Ділення рядка");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        inputField = new JTextField();
        JButton divideButton = new JButton("Ділити");
        outputArea = new JTextArea();
        outputArea.setEditable(false);

        panel.add(inputField, BorderLayout.NORTH);
        panel.add(divideButton, BorderLayout.CENTER);
        panel.add(new JScrollPane(outputArea), BorderLayout.SOUTH);

        divideButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = inputField.getText();
                int middle = inputText.length() / 2;
                String part1 = inputText.substring(0, middle);
                String part2 = inputText.substring(middle);
                outputArea.setText("Частина 1: " + part1 + "\nЧастина 2: " + part2);
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run(){
                new Str();
            }
        });
    }
}