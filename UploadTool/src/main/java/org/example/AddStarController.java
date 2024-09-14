package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class AddStarController {

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

    // 设置明星信息用于编辑
    public void setStarInfo(String name, String commonEndorsement, String portraitOneYear, String portraitTwoYears, String grassXHS, String grassDouyin, String grassEcommerce, String offlineActivity, String liveStream, String remark) {
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

    // 处理保存明星信息
    @FXML
    private void handleSave() {
        String name = nameField.getText().trim();
        String commonEndorsement = getValidDecimal(commonEndorsementField.getText());
        String portraitOneYear = getValidDecimal(portraitOneYearField.getText());
        String portraitTwoYears = getValidDecimal(portraitTwoYearsField.getText());
        String grassXHS = getValidDecimal(grassXHSField.getText());
        String grassDouyin = getValidDecimal(grassDouyinField.getText());
        String grassEcommerce = getValidDecimal(grassEcommerceField.getText());
        String offlineActivity = getValidDecimal(offlineActivityField.getText());
        String liveStream = getValidDecimal(liveStreamField.getText());
        String remark = remarkField.getText() != null ? remarkField.getText().trim() : "";

        boolean success;
        if (DatabaseConnector.searchStar(name)) {
            // 如果明星存在，执行更新操作
            success = DatabaseConnector.updateStar(name, commonEndorsement, portraitOneYear, portraitTwoYears, grassXHS, grassDouyin, grassEcommerce, offlineActivity, liveStream, remark);
        } else {
            // 如果明星不存在，执行新增操作
            success = DatabaseConnector.addStar(name, commonEndorsement, portraitOneYear, portraitTwoYears, grassXHS, grassDouyin, grassEcommerce, offlineActivity, liveStream, remark);
        }

        if (success) {
            statusLabel.setText("保存成功！");
            closeWindow();
        } else {
            statusLabel.setText("保存失败，请重试！");
        }
    }

    // 新增辅助方法，确保数值字段有效
    private String getValidDecimal(String value) {
        return (value == null || value.trim().isEmpty()) ? "0" : value.trim();
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
