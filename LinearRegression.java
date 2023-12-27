import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class LinearRegression {

    public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);

    // allows user to input csv file when you run the program 
    System.out.print("Enter the CSV file path: ");
    String csvFileName = scanner.nextLine();

    // outputs the columns in the csv file, 
    // this also formats it where the columns are 
    // listed by number based on how many columns there are. 
    try {
        List<String[]> data = CSVData.readCSV(csvFileName);

        if (data.isEmpty()) {
            System.out.println("CSV file is empty.");  
        }

        System.out.println("Columns in the CSV file:");
        for (int i = 0; i < data.get(0).length; i++) {
            System.out.println(i + ": " + data.get(0)[i]);
        }

    // this allows the user to select what column they want to use for the x data and y data
        System.out.print("Select a column for X data (enter column index): ");
        int xColumn = scanner.nextInt();
        System.out.print("Select a column for Y data (enter column index): ");
        int yColumn = scanner.nextInt();

        double[] xData = new double[data.size() - 1];
        double[] yData = new double[data.size() - 1];

        for (int i = 1; i < data.size(); i++) {
            xData[i - 1] = Double.parseDouble(data.get(i)[xColumn]);
            yData[i - 1] = Double.parseDouble(data.get(i)[yColumn]);
        }

    // this calculates the linear regression using a method 
    // aditionally this asks the user to make a prediction for
    // for the x value then returns and output that predicts the y value 
    // using a predict method 
        LinearRegression regression = new LinearRegression();
        regression.calculateLinearRegression(xData, yData);

        System.out.print("Enter a value for prediction: ");
        double xValue = scanner.nextDouble();
        double predictedY = regression.predict(xValue);

        System.out.println("Linear Regression Equation: y = " + regression.predict(0) + "x + " + regression.predict(0));
        System.out.println("Predicted Y value = " + xValue + ": " + predictedY);

    // these are to catch exceptions and error when reading the csv file 
    } catch (IOException e) {

        System.err.println("Error reading CSV file: " + e.getMessage());

    } finally {
        scanner.close(); 
    }

}
    // declaring doubles for the method 
    private double slope;
    private double intercept;

    // method used to make the linear regression 
    public void calculateLinearRegression(double[] xData, double[] yData) {

        int n = xData.length;
        double sumX = 0, sumY = 0, sumXY = 0, sumX2 = 0;
    // code used to compute sumx, sumy, sumxy, and sumx2 in order to find slop and intercept
        for (int i = 0; i < n; i++) {
            sumX += xData[i];
            sumY += yData[i];
            sumXY += xData[i] * yData[i];
            sumX2 += xData[i] * xData[i];
        }
        // formulas used for the slope and intercept 
        slope = (n * sumXY - sumX * sumY) / (n * sumX2 - sumX * sumX);
        intercept = (sumY - slope * sumX) / n;
    }
    public double predict(double x) {
        return slope * x + intercept;
    }
}



