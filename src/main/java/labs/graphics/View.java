package labs.graphics;

import javax.swing.*;
import java.awt.*;

public class View implements Observer{
    private static final int X = 800;
    private static final int Y = 800;
    private final JFrame frame = new JFrame();
    private final DrawPanel drawPanel;

    JPanel controlPanel = new JPanel();
    JPanel gasPanel = new JPanel();
    SpinnerModel spinnerModel =
            new SpinnerNumberModel(0, //initial value
                    0, //min
                    100, //max
                    1);//step
    JSpinner gasSpinner = new JSpinner(spinnerModel);
    double gasAmount = 0;
    JLabel gasLabel = new JLabel("Amount of gas");

    JButton gasButton = new JButton("Gas");
    JButton brakeButton = new JButton("Brake");

    JButton turboOnButton = new JButton("Saab Turbo on");
    JButton turboOffButton = new JButton("Saab Turbo off");
    JButton liftBedButton = new JButton("Scania Lift Bed");
    JButton lowerBedButton = new JButton("Lower Lift Bed");

    JButton startButton = new JButton("Start all cars");
    JButton stopButton = new JButton("Stop all cars");
    JButton addCarButton = new JButton("Add new car");
    JButton removeCarButton = new JButton("Remove car");

    public View(String title, Model model) {
        frame.setTitle(title);
        frame.setPreferredSize(new Dimension(X,Y));
        frame.setLayout(new BorderLayout());
        drawPanel = new DrawPanel(X, Y-240, model);
        initComponents();
        frame.add(drawPanel, BorderLayout.CENTER);

        frame.pack();

        // Get the computer screen resolution
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // Center the frame
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        // Make the frame visible
        frame.setVisible(true);
        // Make sure the frame exits when "x" is pressed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initComponents() {

        controlPanel.setLayout(new GridLayout(2,6,10,10));

        controlPanel.add(gasButton, 0);
        controlPanel.add(brakeButton, 1);

        controlPanel.add(turboOnButton, 2);
        controlPanel.add(turboOffButton, 3);
        controlPanel.add(liftBedButton, 4);
        controlPanel.add(lowerBedButton, 5);

        gasPanel.setLayout(new BorderLayout());
        gasPanel.setBackground(Color.white);
        gasPanel.add(gasLabel, BorderLayout.PAGE_START);
        gasPanel.add(gasSpinner, BorderLayout.PAGE_END);
        controlPanel.add(gasPanel, 6);

        startButton.setForeground(Color.green);
        controlPanel.add(startButton, 7);

        stopButton.setForeground(Color.red);
        controlPanel.add(stopButton, 8);

        addCarButton.setForeground(Color.green);
        controlPanel.add(addCarButton, 9);

        removeCarButton.setForeground(Color.red);
        controlPanel.add(removeCarButton, 10);

        controlPanel.setPreferredSize(new Dimension(X, 200));
        controlPanel.setBackground(Color.CYAN);
        frame.add(controlPanel, BorderLayout.SOUTH);    }

    @Override
    public void actOnChange() {
        drawPanel.repaint();
    }

    JButton getStopButton() {
        return stopButton;
    }

    JButton getStartButton() {
        return startButton;
    }

    JButton getTurboOnButton() {
        return turboOnButton;
    }

    JButton getTurboOffButton() {
        return turboOffButton;
    }

    JButton getLiftBedButton() {
        return liftBedButton;
    }

    JButton getLowerBedButton() {
        return lowerBedButton;
    }

    JButton getGasButton() {
        return gasButton;
    }

    JButton getBrakeButton() {
        return brakeButton;
    }

    JSpinner getGasSpinner() {
        return gasSpinner;
    }

    void setGasAmount(double amount) {
        this.gasAmount = amount;
    }

    double getGasAmount() {
        return gasAmount;
    }

    JButton getAddCarButton() { return addCarButton; }

    JButton getRemoveCarButton() { return removeCarButton; }
}



