package labs.graphics;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller extends JComponent {
    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        initComponents();
    }

    private void initComponents() {
        view.getGasButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.gas(view.getGasAmount());
            }
        });

        view.brakeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.brake(view.getGasAmount());
            }
        });

        view.getTurboOnButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.turboOn();
            }
        });

        view.getTurboOffButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.turboOff();
            }
        });

        view.getLiftBedButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.changeAngle(70);
            }
        });

        view.getLowerBedButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.changeAngle(-70);
            }
        });


        view.getStartButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.startCars();
            }
        });

        view.getStopButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.stopCars();
            }
        });

        view.getGasSpinner().addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                view.setGasAmount((int) ((JSpinner)e.getSource()).getValue());
            }
        });
    }


}
