package org.example;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainPageController {

    @FXML
    private TextField nameSearchField;  // 搜索框
    @FXML
    private Button searchButton;  // 搜索按钮
    @FXML
    private Label searchResultLabel;  // 搜索结果标签
    @FXML
    private Button editButton;  // 编辑按钮

    // 明星详细信息标签
    @FXML
    private Label starNameLabel;
    @FXML
    private Label updateDateLabel;
    @FXML
    private Label commonEndorsementPriceLabel;
    @FXML
    private Label portraitPriceOneYearLabel;
    @FXML
    private Label portraitPriceTwoYearsLabel;
    @FXML
    private Label grassXHSPriceLabel;
    @FXML
    private Label grassDouyinPriceLabel;
    @FXML
    private Label grassEcommercePriceLabel;
    @FXML
    private Label offlineActivityPriceLabel;
    @FXML
    private Label liveStreamPriceLabel;
    @FXML
    private Label remarkLabel;  // 备注标签

    // 处理人名搜索功能
    @FXML
    private void handleSearch() {
        String name = nameSearchField.getText().trim();  // 获取输入的人名

        if (name.isEmpty()) {
            searchResultLabel.setText("请输入人名进行搜索");
            clearStarInfo();
            return;
        }

        // 调用 DatabaseConnector 中的 searchStar 方法进行查询
        boolean starFound = DatabaseConnector.searchStar(name);

        if (starFound) {
            // 获取明星详细信息并显示在界面上
            String updateDate = DatabaseConnector.getStarUpdateDate(name);
            String commonEndorsement = DatabaseConnector.getStarCommonEndorsementPrice(name);
            String portraitPriceOneYear = DatabaseConnector.getStarPortraitPriceOneYear(name);
            String portraitPriceTwoYears = DatabaseConnector.getStarPortraitPriceTwoYears(name);
            String grassXHSPrice = DatabaseConnector.getStarGrassXHSPrice(name);
            String grassDouyinPrice = DatabaseConnector.getStarGrassDouyinPrice(name);
            String grassEcommercePrice = DatabaseConnector.getStarGrassEcommercePrice(name);
            String offlineActivityPrice = DatabaseConnector.getStarOfflineActivityPrice(name);
            String liveStreamPrice = DatabaseConnector.getStarLiveStreamPrice(name);
            String remark = DatabaseConnector.getStarRemark(name);  // 获取备注

            // 格式化显示的数据为整数
            showStarInfo(name, updateDate,
                    formatPrice(commonEndorsement),
                    formatPrice(portraitPriceOneYear),
                    formatPrice(portraitPriceTwoYears),
                    formatPrice(grassXHSPrice),
                    formatPrice(grassDouyinPrice),
                    formatPrice(grassEcommercePrice),
                    formatPrice(offlineActivityPrice),
                    formatPrice(liveStreamPrice),
                    remark);
        } else {
            searchResultLabel.setText("未找到明星: " + name);
            clearStarInfo();  // 清空明星信息
        }
    }

    // 显示明星详细信息
    private void showStarInfo(String name, String updateDate, String commonEndorsement, String portraitPriceOneYear, String portraitPriceTwoYears, String grassXHSPrice, String grassDouyinPrice, String grassEcommercePrice, String offlineActivityPrice, String liveStreamPrice, String remark) {
        starNameLabel.setText(name);
        updateDateLabel.setText(updateDate);
        commonEndorsementPriceLabel.setText(commonEndorsement + "万");
        portraitPriceOneYearLabel.setText(portraitPriceOneYear + "万");
        portraitPriceTwoYearsLabel.setText(portraitPriceTwoYears + "万");
        grassXHSPriceLabel.setText(grassXHSPrice + "万");
        grassDouyinPriceLabel.setText(grassDouyinPrice + "万");
        grassEcommercePriceLabel.setText(grassEcommercePrice + "万");
        offlineActivityPriceLabel.setText(offlineActivityPrice + "万");
        liveStreamPriceLabel.setText(liveStreamPrice + "万");
        remarkLabel.setText(remark);  // 显示备注
        editButton.setVisible(true);  // 显示编辑按钮
    }

    // 清空明星详细信息
    private void clearStarInfo() {
        starNameLabel.setText("");
        updateDateLabel.setText("");
        commonEndorsementPriceLabel.setText("");
        portraitPriceOneYearLabel.setText("");
        portraitPriceTwoYearsLabel.setText("");
        grassXHSPriceLabel.setText("");
        grassDouyinPriceLabel.setText("");
        grassEcommercePriceLabel.setText("");
        offlineActivityPriceLabel.setText("");
        liveStreamPriceLabel.setText("");
        remarkLabel.setText("");  // 清空备注
        editButton.setVisible(false);  // 隐藏编辑按钮
    }

    // 格式化价格，移除小数点
    private String formatPrice(String price) {
        try {
            double value = Double.parseDouble(price);
            return String.format("%.0f", value);  // 将小数格式化为整数显示
        } catch (NumberFormatException e) {
            return price;  // 如果转换失败，返回原始字符串
        }
    }

    // 新增明星数据功能
    @FXML
    private void handleAddStar() {
        try {
            // 加载新增明星页面
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/addStar.fxml"));
            Parent root = loader.load();

            // 显示新增明星窗口
            Stage stage = new Stage();
            stage.setTitle("新增明星");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 编辑明星信息功能
    @FXML
    private void handleEdit() {
        try {
            // 加载编辑明星页面
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/addStar.fxml"));
            Parent root = loader.load();

            // 获取控制器
            AddStarController controller = loader.getController();

            // 将现有的明星信息传递给编辑页面
            controller.setStarInfo(
                    starNameLabel.getText(),
                    commonEndorsementPriceLabel.getText().replace("万", ""),
                    portraitPriceOneYearLabel.getText().replace("万", ""),
                    portraitPriceTwoYearsLabel.getText().replace("万", ""),
                    grassXHSPriceLabel.getText().replace("万", ""),
                    grassDouyinPriceLabel.getText().replace("万", ""),
                    grassEcommercePriceLabel.getText().replace("万", ""),
                    offlineActivityPriceLabel.getText().replace("万", ""),
                    liveStreamPriceLabel.getText().replace("万", ""),
                    remarkLabel.getText()
            );

            // 显示编辑窗口
            Stage stage = new Stage();
            stage.setTitle("编辑明星信息");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}