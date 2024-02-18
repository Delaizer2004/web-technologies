import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FilterUserTest {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Filter User Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        List<User> dataList = new ArrayList<>();
        dataList.add(new User("Alice", 25));
        dataList.add(new User("Bob", 30));
        dataList.add(new User("Charlie", 28));
        dataList.add(new User("David", 22));
        dataList.add(new User("Emma", 27));

        FilterUserTextField filterTextField = new FilterUserTextField(dataList, 500);
        filterTextField.setPreferredSize(new Dimension(200, 40));
        panel.add(filterTextField);

        DefaultListModel<User> listModel = new DefaultListModel<>();
        JList<User> resultJList = new JList<>(listModel);
        panel.add(new JScrollPane(resultJList));

        frame.add(panel);

        filterTextField.setResultJList(listModel);

        
        filterTextField.filterData();

        frame.setVisible(true);
    }
}

class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return name + " - " + age;
    }
}

class FilterUserTextField extends JTextField {
    private List<User> data;
    private long interval;
    private Timer timer;
    private DefaultListModel<User> resultListModel;

    public FilterUserTextField(List<User> data, long interval) {
        this.data = data;
        this.interval = interval;

        timer = new Timer((int) interval, e -> filterData());
        timer.setRepeats(false);

        getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                timer.restart();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                timer.restart();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                timer.restart();
            }
        });
    }

    public void setResultJList(DefaultListModel<User> resultListModel) {
        this.resultListModel = resultListModel;
    }

    public void filterData() {
        String filterText = getText().toLowerCase();
        resultListModel.clear();

        for (User user : data) {
            if (user.toString().toLowerCase().contains(filterText)) {
                resultListModel.addElement(user);
            }
        }
    }
}