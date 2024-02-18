import java.util.ArrayList;
import java.util.List;

abstract class MonthlyData {
    protected String monthName;
    protected int year;
    protected int previousReading;
    protected int currentReading;
    protected double electricityUsed;
    protected double electricityPrice;
    protected double electricityCost;

    public MonthlyData(String monthName, int year) {
        this.monthName = monthName;
        this.year = year;
    }

    public abstract void calculateElectricityCost();

    public double getElectricityPrice() {
        return electricityPrice;
    }

    // Геттери та сеттери
    public double getElectricityCost() {
        return electricityCost;
    }

    public double getElectricityUsed() {
        return electricityUsed;
    }

    public void setPreviousReading(int previousReading) {
        this.previousReading = previousReading;
        calculateElectricityUsed();
        calculateElectricityCost();
    }

    public void setCurrentReading(int currentReading) {
        this.currentReading = currentReading;
        calculateElectricityUsed();
        calculateElectricityCost();
    }

    public void setElectricityPrice(double electricityPrice) {
        this.electricityPrice = electricityPrice;
        calculateElectricityCost();
    }

    private void calculateElectricityUsed() {
        electricityUsed = currentReading - previousReading;
    }

    protected void setElectricityCost(double electricityCost) {
        this.electricityCost = electricityCost;
    }
}

// Нащадок
class DualRateMonthlyData extends MonthlyData {
    private static final double NIGHT_RATE_MULTIPLIER = 0.5;

    public DualRateMonthlyData(String monthName, int year) {
        super(monthName, year);
    }

    @Override
    public void calculateElectricityCost() {
        double dayUsage = getElectricityUsed() * NIGHT_RATE_MULTIPLIER;
        double nightUsage = getElectricityUsed() - dayUsage;
        setElectricityCost(dayUsage * getElectricityPrice() + nightUsage * getElectricityPrice() * NIGHT_RATE_MULTIPLIER);
    }
}

class ElectricityAccounting {
    private List<MonthlyData> monthlyDataList;

    public ElectricityAccounting() {
        monthlyDataList = new ArrayList<>();
    }

    public void addMonthlyData(MonthlyData monthlyData) {
        monthlyDataList.add(monthlyData);
    }

    public double getTotalElectricityUsed() {
        double total = 0;
        for (MonthlyData monthlyData : monthlyDataList) {
            total += monthlyData.getElectricityUsed();
        }
        return total;
    }

    public double getTotalElectricityCost() {
        double total = 0;
        for (MonthlyData monthlyData : monthlyDataList) {
            total += monthlyData.getElectricityCost();
        }
        return total;
    }

    public void printMonthlyData() {
        for (MonthlyData monthlyData : monthlyDataList) {
            System.out.println(monthlyData.monthName + " " + monthlyData.year + ":");
            System.out.println("Electricity used: " + monthlyData.getElectricityUsed() + " kWh");
            System.out.println("Electricity cost: $" + monthlyData.getElectricityCost());
            System.out.println();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        MonthlyData januaryData = new MonthlyData("January", 2024) {
            @Override
            public void calculateElectricityCost() {

            }
        };
        januaryData.setElectricityPrice(0.12);
        januaryData.setPreviousReading(1000);
        januaryData.setCurrentReading(1100);

        MonthlyData februaryData = new MonthlyData("February", 2024) {
            @Override
            public void calculateElectricityCost() {

            }
        };
        februaryData.setElectricityPrice(0.12);
        februaryData.setPreviousReading(1100);
        februaryData.setCurrentReading(1200);

        DualRateMonthlyData marchData = new DualRateMonthlyData("March", 2024);
        marchData.setElectricityPrice(0.12);
        marchData.setPreviousReading(1200);
        marchData.setCurrentReading(1300);

        ElectricityAccounting accounting = new ElectricityAccounting();
        accounting.addMonthlyData(januaryData);
        accounting.addMonthlyData(februaryData);
        accounting.addMonthlyData(marchData);

        System.out.println("Monthly Data:");
        accounting.printMonthlyData();

        System.out.println("Total electricity used: " + accounting.getTotalElectricityUsed() + " kWh");
        System.out.println("Total electricity cost: $" + accounting.getTotalElectricityCost());
    }
}
