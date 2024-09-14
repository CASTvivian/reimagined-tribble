package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

public class LoginController {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private Label statusLabel;

    // 处理登录操作
    @FXML
    private void handleLogin() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            statusLabel.setText("请输入用户名和密码！");
            return;
        }

        // 调用 DatabaseConnector 类中的 validateLogin 方法
        boolean loginSuccess = DatabaseConnector.validateLogin(username, password);
        if (loginSuccess) {
            statusLabel.setText("登录成功！");
            loadMainPage();  // 登录成功后跳转到主界面
        } else {
            statusLabel.setText("用户名或密码错误！");
        }
    }

    // 加载主页面
    private void loadMainPage() {
        try {
            // 获取当前窗口的 Stage
            Stage stage = (Stage) loginButton.getScene().getWindow();
            // 加载主界面 FXML 文件
            Parent root = FXMLLoader.load(getClass().getResource("/org/example/mainPage.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("主界面");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
