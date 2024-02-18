import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class FilterTextFieldTest {
    public static void main(String[] args) {
        JFrame frame = new JFrame("FilterTextField Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        List<String> dataList = Arrays.asList("Apple", "Banana", "Orange", "Mango", "Pineapple");

        FilterTextField filterTextField = new FilterTextField(dataList, 500);
        filterTextField.setPreferredSize(new Dimension(200, 40));
        panel.add(filterTextField);

        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> resultJList = new JList<>(listModel);
        panel.add(new JScrollPane(resultJList));

        frame.add(panel);

        filterTextField.setResultJList(listModel);

        filterTextField.filterData();

        frame.setVisible(true);
    }
}

class FilterTextField extends JTextField {
    private List<String> data;
    private long interval;
    private Timer timer;
    private DefaultListModel<String> resultListModel;

    public FilterTextField(List<String> data, long interval) {
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

    public void setResultJList(DefaultListModel<String> resultListModel) {
        this.resultListModel = resultListModel;
    }

    public void filterData() {
        String filterText = getText().toLowerCase();
        resultListModel.clear();

        for (String item : data) {
            if (item.toLowerCase().contains(filterText)) {
                resultListModel.addElement(item);
            }
        }
    }
}
