package com.techreturners.music4WeatherAPI.view;

import com.techreturners.music4WeatherAPI.controller.Music4WeatherController;
import com.techreturners.music4WeatherAPI.model.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class Music4WeatherView extends JFrame {

    @Autowired
    private Music4WeatherController music4WeatherController;

    public Music4WeatherView() throws Exception {
//
//        String userCityInput;
//
//        JFrame frame = initUI();
//        frame.setVisible(true);
//
//        // TODO: Happens on button press
//        Track track = music4WeatherController.getTrack("Oxford").getBody();


        initUI();

    }

//    private UI initUI() {
//
//        UI ui = new UI();
//        ui.setTitle("Generate Plateau");
//        ui.setLocationRelativeTo(null);
//        ui.setVisible(true);
//        ui.setBounds(600, 250, 600, 470);
//        ui.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
//        ui.setResizable(false);
//
//        ui.addWindowListener(new java.awt.event.WindowAdapter() {
//            @Override
//            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
//                if (JOptionPane.showConfirmDialog(
//                        ui, "Proceed with terminal input?", "Terminal Input",
//                        JOptionPane.YES_NO_OPTION,
//                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
//
//                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//                    int[] coords;
//                    int[] roverCoords;
//                    while (true) {
//                        try {
//                            coords = inputCoords(reader);
//                            break;
//                        } catch (NumberFormatException | IOException | ArrayIndexOutOfBoundsException e) {
//                            System.out.println("\nERROR: Invalid format, please try again.\n");
//                        }
//                    }
//                    while (true) {
//                        try {
//                            roverCoords = inputRoverCoords(reader);
//                            break;
//                        } catch (NumberFormatException | IOException | ArrayIndexOutOfBoundsException e) {
//                            System.out.println("\nERROR: Invalid format, please try again.\n");
//                        }
//                    }
//                    ui.dispose();
//                    int[] finalCoords = coords;
//                    int[] finalRoverCoords = roverCoords;
//                }
//                else {
//                    System.exit(0);
//                }
//            }
//
//            private int[] inputCoords(BufferedReader reader) throws IOException {
//                System.out.println("Please specify your plateau size in coordinates separated by a space.");
//                System.out.println("(e.g. for an 8x5 grid: \"7 4\"):");
//                String[] args = reader.readLine().split(" ");
//                return new int[]{Integer.parseInt(args[0]), Integer.parseInt(args[1])};
//            }
//            private int[] inputRoverCoords(BufferedReader reader) throws IOException {
//                System.out.println("\nPlease specify your initial rover's starting coordinates and facing");
//                System.out.println("in the following format:  \"x y <N/E/S/W>\"");
//                System.out.println("(e.g. \"3 1 S\"):");
//                String[] args = reader.readLine().split(" ");
//                return new int[]{Integer.parseInt(args[0]), Integer.parseInt(args[1]), args[2].charAt(0)};
//            }
//
//        });
//
//        return ui;
//
//    }

    private void initUI() throws Exception {

//        var quitButton = new JButton("Quit");
//
//        quitButton.addActionListener((ActionEvent event) -> {
//
//            System.exit(0);
//        });
//
//        createLayout(quitButton);
//
//        setTitle("Quit button");
//        setSize(300, 200);
//        setLocationRelativeTo(null);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);

        UI ui = new UI();

        ui.setVisible(true);

    }

    private void createLayout(JComponent... arg) {

        var pane = getContentPane();
        var gl = new GroupLayout(pane);
        pane.setLayout(gl);

        gl.setAutoCreateContainerGaps(true);

        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addComponent(arg[0])
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addComponent(arg[0])
        );
    }

}
