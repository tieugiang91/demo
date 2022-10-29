package com.example.thesis_demo;

import com.example.thesis_demo.model.Record;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.fxml.*;
import javafx.event.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import weka.core.Instances;

import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;


public class Controller implements Initializable {

    public static final String TESTDATA_PATH = "testdata.arff";
    public static final String MODEL_PATH = "model.bin";
    public static final String FLOOR_POINTS_CSV = "floor-points.csv";
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
    @FXML private Text textHolland;
    @FXML private Text textKhoiThi;
    @FXML private Text textNganh;

    @FXML private TableView tableView1;
    @FXML private TableColumn tableColumn11;
    @FXML private TableColumn tableColumn12;
    @FXML private TableColumn tableColumn13;
    @FXML private TableColumn tableColumn14;
    @FXML private TableView tableView2;
    @FXML private TableColumn tableColumn21;
    @FXML private TableColumn tableColumn22;
    @FXML private TableColumn tableColumn23;
    @FXML private TableColumn tableColumn24;

    private String name;
    private double matScore;
    private double phyScore;
    private double cheScore;
    private double bioScore;
    private double litScore;
    private double hisScore;
    private double geoScore;
    private double engScore;
    private List<Record> records;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(Application.class.getResource(FLOOR_POINTS_CSV));
        InputStream inputStream = Application.class.getResourceAsStream(FLOOR_POINTS_CSV);
        records = FloorPointParser.readAllData(inputStream);
        setupTableView();
    }

    @FXML protected void handleSubmitButtonAction(ActionEvent event) {
        textHolland.setText("Kết quả tư vấn:");
        textKhoiThi.setText("Khối thi:");
        textNganh.setText("Ngành nghề nên chọn:");
        name = nameTextField.getText();
        float[] scores = getScoreFromInput();
        String[] grade = getGradeFromScores(scores);

        outputToUI(scores, grade);
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

    private void setupTableView() {
        tableColumn11.setCellValueFactory(new PropertyValueFactory("nhomNganh"));
        tableView1.getColumns().set(0, tableColumn11);
        tableColumn12.setCellValueFactory(new PropertyValueFactory("maNganh"));
        tableView1.getColumns().set(1, tableColumn12);
        tableColumn13.setCellValueFactory(new PropertyValueFactory("maTruong"));
        tableView1.getColumns().set(2, tableColumn13);
        tableColumn14.setCellValueFactory(new PropertyValueFactory("tenTruong"));
        tableView1.getColumns().set(3, tableColumn14);
        tableColumn21.setCellValueFactory(new PropertyValueFactory("nhomNganh"));
        tableView2.getColumns().set(0, tableColumn21);
        tableColumn22.setCellValueFactory(new PropertyValueFactory("maNganh"));
        tableView2.getColumns().set(1, tableColumn22);
        tableColumn23.setCellValueFactory(new PropertyValueFactory("maTruong"));
        tableView2.getColumns().set(2, tableColumn23);
        tableColumn24.setCellValueFactory(new PropertyValueFactory("tenTruong"));
        tableView2.getColumns().set(3, tableColumn24);
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

    private void writeFile(String[] grade, String path) {
        File file = new File(path);
        FileWriter fw = null;
        String line = String.join(",",grade) + ",?";
        try {
            fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.append(line);
            bw.newLine();
            bw.flush();
            bw.close();
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getGrade(double score) {
        if (score >= 8) return "Gioi";
        if (score >= 6.5) return "Kha";
        if (score >= 5) return "TB";
        return "Yeu";
    }

    private float[] getScoreFromInput() {
        float[] scores = new float[8];
        scores[0] = Float.parseFloat(matScoreTextField.getText());
        scores[1] = Float.parseFloat(phyScoreTextField.getText());
        scores[2] = Float.parseFloat(cheScoreTextField.getText());
        scores[3] = Float.parseFloat(bioScoreTextField.getText());
        scores[4] = Float.parseFloat(litScoreTextField.getText());
        scores[5] = Float.parseFloat(hisScoreTextField.getText());
        scores[6] = Float.parseFloat(geoScoreTextField.getText());
        scores[7] = Float.parseFloat(engScoreTextField.getText());
        return scores;
    }

    private String[] getGradeFromScores(float[] scores) {
        String[] grade = new String[8];
        for (int i = 0; i < 8; i++) {
            grade[i] = getGrade(scores[i]);
        }
        return grade;
    }

    private void outputToUI(float[] scores, String[] grade) {
        writeFile(grade, TESTDATA_PATH);
        ModelGenerator mg = new ModelGenerator();
        Instances dataset = mg.loadDataset(TESTDATA_PATH);
        ModelClassifier cls = new ModelClassifier();
        String nganhHolland = cls.classify(dataset.lastInstance(), MODEL_PATH);
        textHolland.setText(textHolland.getText() + " " + nganhHolland);

        List<Record> filteredRecords = FloorPointParser.filter(nganhHolland, scores, records);
        tableView2.setItems(FXCollections.observableList(filteredRecords));
        tableView1.setItems(FXCollections.observableList(FloorPointParser.getTop3(filteredRecords)));
        Record top1 = filteredRecords.get(0);
        textKhoiThi.setText(textKhoiThi.getText() + " " + top1.getTohopMon());
        textNganh.setText(textNganh.getText() + " " + top1.getTenNganh());
    }

}