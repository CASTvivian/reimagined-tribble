package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class EditStarController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField commonEndorsementField;
    @FXML
    private TextField portraitOneYearField;
    @FXML
    private TextField portraitTwoYearsField;
    @FXML
    private TextField grassXHSField;
    @FXML
    private TextField grassDouyinField;
    @FXML
    private TextField grassEcommerceField;
    @FXML
    private TextField offlineActivityField;
    @FXML
    private TextField liveStreamField;
    @FXML
    private TextArea remarkField;
    @FXML
    private Label statusLabel;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;

    private String originalName;

    // 初始化编辑页面，接收明星数据
    public void setStarData(String name, String commonEndorsement, String portraitOneYear, String portraitTwoYears,
                            String grassXHS, String grassDouyin, String grassEcommerce, String offlineActivity,
                            String liveStream, String remark) {
        originalName = name;
        nameField.setText(name);
        commonEndorsementField.setText(commonEndorsement);
        portraitOneYearField.setText(portraitOneYear);
        portraitTwoYearsField.setText(portraitTwoYears);
        grassXHSField.setText(grassXHS);
        grassDouyinField.setText(grassDouyin);
        grassEcommerceField.setText(grassEcommerce);
        offlineActivityField.setText(offlineActivity);
        liveStreamField.setText(liveStream);
        remarkField.setText(remark);
    }

    // 处理保存操作
    @FXML
    private void handleSave() {
        String name = nameField.getText().trim();
        String commonEndorsement = commonEndorsementField.getText().trim();
        String portraitOneYear = portraitOneYearField.getText().trim();
        String portraitTwoYears = portraitTwoYearsField.getText().trim();
        String grassXHS = grassXHSField.getText().trim();
        String grassDouyin = grassDouyinField.getText().trim();
        String grassEcommerce = grassEcommerceField.getText().trim();
        String offlineActivity = offlineActivityField.getText().trim();
        String liveStream = liveStreamField.getText().trim();
        String remark = remarkField.getText().trim();

        // 调用更新方法
        boolean success = DatabaseConnector.updateStar(name, commonEndorsement, portraitOneYear, portraitTwoYears, grassXHS,
                grassDouyin, grassEcommerce, offlineActivity, liveStream, remark);

        if (success) {
            statusLabel.setText("保存成功！");
            closeWindow();
        } else {
            statusLabel.setText("保存失败，请重试！");
        }
    }

    @FXML
    private void handleCancel() {
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
