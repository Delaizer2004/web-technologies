import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AirTicketApp extends JFrame {
    private JTextField flightNumberField;
    private JTextField passengerNameField;
    private JTextField departureCityField;
    private JTextField arrivalCityField;
    private JCheckBox isRoundTripCheckBox;
    private JTextArea displayArea;
    private ArrayList<AirTicket> airTickets;
    private int currentTicketIndex;

    public AirTicketApp() {
        setTitle("Авіаквиток");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));

        panel.add(new JLabel("Номер рейсу:"));
        flightNumberField = new JTextField();
        panel.add(flightNumberField);

        panel.add(new JLabel("Ім'я пасажира:"));
        passengerNameField = new JTextField();
        panel.add(passengerNameField);

        panel.add(new JLabel("Місто відправлення:"));
        departureCityField = new JTextField();
        panel.add(departureCityField);

        panel.add(new JLabel("Місто прибуття:"));
        arrivalCityField = new JTextField();
        panel.add(arrivalCityField);

        panel.add(new JLabel("Туди і назад:"));
        isRoundTripCheckBox = new JCheckBox();
        panel.add(isRoundTripCheckBox);

        JButton addButton = new JButton("Додати в об'єкт");
        panel.add(addButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                addValuesToAirTicket();
            }
        });

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        add(displayArea, BorderLayout.CENTER);

        JButton displayButton = new JButton("Показати об'єкт");
        add(displayButton, BorderLayout.SOUTH);

        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayCurrentAirTicket();
            }
        });

        JButton prevButton = new JButton("Попередній квиток");
        add(prevButton, BorderLayout.WEST);

        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPreviousTicket();
            }
        });

        JButton nextButton = new JButton("Наступний квиток");
        add(nextButton, BorderLayout.EAST);

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showNextTicket();
            }
        });

        airTickets = new ArrayList<>();
        currentTicketIndex = -1;
        add(panel, BorderLayout.NORTH);
    }

    private void addValuesToAirTicket() {
        AirTicket ticket = new AirTicket();
        ticket.setFlightNumber(flightNumberField.getText());
        ticket.setPassengerName(passengerNameField.getText());
        ticket.setDepartureCity(departureCityField.getText());
        ticket.setArrivalCity(arrivalCityField.getText());
        ticket.setRoundTrip(isRoundTripCheckBox.isSelected());
        airTickets.add(ticket);
        currentTicketIndex = airTickets.size() - 1;
        clearFields();
    }

    private void clearFields() {
        flightNumberField.setText("");
        passengerNameField.setText("");
        departureCityField.setText("");
        arrivalCityField.setText("");
        isRoundTripCheckBox.setSelected(false);
    }

    private void displayCurrentAirTicket() {
        if (currentTicketIndex >= 0 && currentTicketIndex < airTickets.size()) {
            AirTicket ticket = airTickets.get(currentTicketIndex);
            displayArea.setText("Номер рейсу: " + ticket.getFlightNumber() + "\n");
            displayArea.append("Ім'я пасажира: " + ticket.getPassengerName() + "\n");
            displayArea.append("Місто відправлення: " + ticket.getDepartureCity() + "\n");
            displayArea.append("Місто прибуття: " + ticket.getArrivalCity() + "\n");
            displayArea.append("Туди і назад: " + ticket.isRoundTrip() + "\n");
        } else {
            displayArea.setText("Немає квитків для відображення.");
        }
    }

    private void showPreviousTicket() {
        if (currentTicketIndex > 0) {
            currentTicketIndex--;
            displayCurrentAirTicket();
        }
    }

    private void showNextTicket() {
        if (currentTicketIndex < airTickets.size() - 1) {
            currentTicketIndex++;
            displayCurrentAirTicket();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AirTicketApp().setVisible(true);
            }
        });
    }
}

class AirTicket{
    private String flightNumber;
    private String passengerName;
    private String departureCity;
    private String arrivalCity;
    private boolean roundTrip;

    public String getFlightNumber(){
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber){
        this.flightNumber = flightNumber;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

     public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getArrivalCity(){
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity){
        this.arrivalCity = arrivalCity;
    }

    public boolean isRoundTrip(){
        return roundTrip;
    }

    public void setRoundTrip(boolean roundTrip){
        this.roundTrip = roundTrip;
    }
}