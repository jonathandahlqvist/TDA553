package labs.graphics;

import javax.swing.*;
import java.awt.*;

public class View implements Observer{
    private static final int X = 800;
    private static final int Y = 800;
    private final JFrame frame = new JFrame();


    private Model model;

    DrawPanel drawPanel;

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

    public View(String title, Model model) {
        this.model = model;

        frame.setTitle(title);
        frame.setPreferredSize(new Dimension(X,Y));
        frame.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        drawPanel = new DrawPanel(X, Y-240, model);

        initComponents();

        frame.add(drawPanel);

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

    public void initComponents() {
        gasPanel.setLayout(new BorderLayout());
        gasPanel.add(gasLabel, BorderLayout.PAGE_START);
        gasPanel.add(gasSpinner, BorderLayout.PAGE_END);

        frame.add(gasPanel);

        controlPanel.setLayout(new GridLayout(2,4));

        controlPanel.add(gasButton, 0);
        controlPanel.add(turboOnButton, 1);
        controlPanel.add(liftBedButton, 2);
        controlPanel.add(brakeButton, 3);
        controlPanel.add(turboOffButton, 4);
        controlPanel.add(lowerBedButton, 5);
        controlPanel.setPreferredSize(new Dimension((X/2)+4, 200));
        frame.add(controlPanel);
        controlPanel.setBackground(Color.CYAN);

        startButton.setBackground(Color.blue);
        startButton.setForeground(Color.green);
        startButton.setPreferredSize(new Dimension(X/5-15,200));
        frame.add(startButton);

        stopButton.setBackground(Color.red);
        stopButton.setForeground(Color.black);
        stopButton.setPreferredSize(new Dimension(X/5-15,200));
        frame.add(stopButton);
    }

    @Override
    public void actOnChange() {
        drawPanel.repaint();
    }

    public JButton getStopButton() {
        return stopButton;
    }

    public JButton getStartButton() {
        return startButton;
    }

    public JButton getTurboOnButton() {
        return turboOnButton;
    }

    public JButton getTurboOffButton() {
        return turboOffButton;
    }

    public JButton getLiftBedButton() {
        return liftBedButton;
    }

    public JButton getLowerBedButton() {
        return lowerBedButton;
    }

    public JButton getGasButton() {
        return gasButton;
    }

    public JSpinner getGasSpinner() {
        return gasSpinner;
    }

    public void setGasAmount(double amount) {
        this.gasAmount = amount;
    }

    public double getGasAmount() {
        return gasAmount;
    }
}



