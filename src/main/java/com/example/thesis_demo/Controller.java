package com.example.thesis_demo;

import com.example.thesis_demo.model.Record;
import javafx.fxml.*;
import javafx.event.*;
import javafx.scene.control.*;
import javafx.scene.text.Text;

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
    @FXML private RadioButton maleRadioButton;
    @FXML private RadioButton femaleRadioButton;
    @FXML private Button submitButton;
    @FXML private Text text;

    private String name;
    private int matScore;
    private int phyScore;
    private int cheScore;
    private int bioScore;
    private int litScore;
    private int hisScore;
    private int geoScore;
    private int engScore;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        System.out.println(Application.class.getResource("floor-points.csv"));
        InputStream inputStream = Application.class.getResourceAsStream("floor-points.csv");
        FloorPointParser.readAllData(inputStream);
    }

    @FXML protected void handleSubmitButtonAction(ActionEvent event) {
        text.setText("Ngành nghề nên chọn:");
        name = nameTextField.getText();
        matScore = Integer.parseInt(matScoreTextField.getText());
        phyScore = Integer.parseInt(phyScoreTextField.getText());
        cheScore = Integer.parseInt(cheScoreTextField.getText());
        bioScore = Integer.parseInt(bioScoreTextField.getText());
        litScore = Integer.parseInt(litScoreTextField.getText());
        hisScore = Integer.parseInt(hisScoreTextField.getText());
        geoScore = Integer.parseInt(geoScoreTextField.getText());
        engScore = Integer.parseInt(engScoreTextField.getText());
        String str = "Tên: " + name + ", Toán: " + matScore + ", Lí: " + phyScore
                + ", Hóa: " + cheScore + ", Sinh: " + bioScore
                + ", Văn: " + litScore + ", Sử: " + hisScore
                + ", Địa: " + geoScore + ", NN: " + engScore;
        text.setText(text.getText() + " " + str);
//        reset();
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

    public void handleResetButtonAction(ActionEvent event) {
        reset();
    }

    private void reset() {
        nameTextField.setText("");
        matScoreTextField.setText("");
        phyScoreTextField.setText("");
        cheScoreTextField.setText("");
        bioScoreTextField.setText("");
        litScoreTextField.setText("");
        hisScoreTextField.setText("");
        geoScoreTextField.setText("");
        engScoreTextField.setText("");
        maleRadioButton.setSelected(false);
        femaleRadioButton.setSelected(false);
    }
}