import javax.swing.*;
import java.awt.*;


class Plate {
    private String material;
    private Color color;
    private int diameter;
    private boolean clean = false;
    private boolean broken = false;

    public Plate(String material, Color color, int diameter) {
        this.material = material;
        this.color = color;
        this.diameter = diameter;
    }

    public Plate(String material, Color color, int diameter, boolean clean, boolean broken) {
        this.material = material;
        this.color = color;
        this.diameter = diameter;
        this.clean = clean;
        this.broken = broken;
    }

    public Plate(int diameter){
        this("Кераміка", Color.WHITE, diameter);
    }

    public Plate() {
        this(20); 
    }
   

    public void wash() {
        if (!broken) {
            clean = true;
            System.out.println("Тарілка чиста");
        } else {
            System.out.println("Тарілка брудна");
        }
    }

    public void breakPlate() {
        broken = true;
        clean = false;
        System.out.println("Тарілка розбита");
    }

    public void displayInfo() {
        System.out.println("Матеріал: " + material);
        System.out.println("Колір: " + color);
        System.out.println("Діаметр: " + diameter + " см");
        System.out.println("Чиста: " + (clean ? "Так" : "Ні"));
        System.out.println("Розбита: " + (broken ? "Так" : "Ні"));
    }

    public Color getColor() {

        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}

public class PlateProgram02 {
    static Plate plate;
    public static void main(String[] args) {
        JFrame frame = new JFrame("Візуалізація об'екта тарілка");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new FlowLayout());

        

        JButton createButton = new JButton("Створити тарілку");
        JButton washButton = new JButton("Вимити тарілку");
        JButton breakButton = new JButton("Розбити тарілку");
        JButton infoButton = new JButton("Переглянути інформацію");
        JButton changeColorButton = new JButton("Змінити колір");

        createButton.addActionListener(e -> {
            plate = new Plate("Кераміка", Color.WHITE, 20);
            System.out.println("Нова тарілка створена");
        });

        washButton.addActionListener(e -> {
            if (plate != null) {
                plate.wash();
            } else {
                System.out.println("Будьласка творіть тарілку");
            }
        });

        breakButton.addActionListener(e -> {
            if (plate != null) {
                plate.breakPlate();
            } else {
                System.out.println("Будьласка творіть тарілку");
            }
        });

        infoButton.addActionListener(e -> {
            if (plate != null) {
                plate.displayInfo();
            } else {
                System.out.println("Будьласка творіть тарілку");
            }
        });

        changeColorButton.addActionListener(e -> {
            if (plate != null) {
                Color newColor = JColorChooser.showDialog(null, "Виберіть колір", plate.getColor());
                if (newColor != null) {
                    plate.setColor(newColor);
                    System.out.println("Колір тарілки змінено");
                }
            } else {
                System.out.println("Будьласка творіть тарілку");
            }
        });
        

        frame.add(createButton);
        frame.add(washButton);
        frame.add(breakButton);
        frame.add(infoButton);
        frame.add(changeColorButton);

        frame.setVisible(true);
    }
}
