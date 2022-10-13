package com.example.thesis_demo;

import javafx.fxml.*;
import javafx.event.*;
import javafx.scene.control.*;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;


public class Controller implements Initializable {

    @FXML private TextField nameTextField;
    @FXML private TextField matScoreTextField;
    @FXML private TextField phyScoreTextField;
    @FXML private TextField cheScoreTextField;
    @FXML private TextField bioScoreTextField;
    @FXML private TextField litScoreTextField;
    @FXML private TextField hisScoreTextField;
    @FXML private TextField geoScoreTextField;
    @FXML private TextField engScoreTextField;
    @FXML private Button submitButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        System.out.println(Application.class.getResource("floor-points.csv"));
        InputStream inputStream = Application.class.getResourceAsStream("floor-points.csv");
        FloorPointParser.readAllData(inputStream);
    }

    @FXML protected void handleSubmitButtonAction(ActionEvent event) {
        System.out.println(nameTextField.getText());
        System.out.println(matScoreTextField.getText());
        System.out.println(phyScoreTextField.getText());
        System.out.println(cheScoreTextField.getText());
        System.out.println(bioScoreTextField.getText());
        System.out.println(litScoreTextField.getText());
        System.out.println(hisScoreTextField.getText());
        System.out.println(geoScoreTextField.getText());
        System.out.println(engScoreTextField.getText());
    }

    UnaryOperator<TextFormatter.Change> numberValidationFormatter = change -> {
        if (change.getText().matches("\\d+")) {
            return change;
        } else {
            change.setText("");
            change.setRange(
                    change.getRangeStart(),
                    change.getRangeStart()
            );
            return change;
        }
    };

}