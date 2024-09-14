package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnector {

    private static final String URL = "jdbc:mysql://192.168.0.5:3306/userdb?useUnicode=true&characterEncoding=UTF-8";
    private static final String USER = "root";  // 替换为你的数据库用户名
    private static final String PASSWORD = "tianbojituan";  // 替换为你的数据库密码

    // 获取数据库连接
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // 登录验证
    public static boolean validateLogin(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next(); // 如果存在记录则返回 true
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 新增明星信息
    public static boolean addStar(String name, String commonEndorsement, String portraitOneYear, String portraitTwoYears, String grassXHS, String grassDouyin, String grassEcommerce, String offlineActivity, String liveStream, String remark) {
        String sql = "INSERT INTO stars (name, common_endorsement_price, portrait_price_one_year, portrait_price_two_years, grass_xhs_price, grass_douyin_price, grass_ecommerce_price, offline_activity_price, live_stream_price, remark) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, commonEndorsement);
            statement.setString(3, portraitOneYear);
            statement.setString(4, portraitTwoYears);
            statement.setString(5, grassXHS);
            statement.setString(6, grassDouyin);
            statement.setString(7, grassEcommerce);
            statement.setString(8, offlineActivity);
            statement.setString(9, liveStream);
            statement.setString(10, remark);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 更新明星信息
    public static boolean updateStar(String name, String commonEndorsement, String portraitOneYear, String portraitTwoYears, String grassXHS, String grassDouyin, String grassEcommerce, String offlineActivity, String liveStream, String remark) {
        String sql = "UPDATE stars SET common_endorsement_price = ?, portrait_price_one_year = ?, portrait_price_two_years = ?, grass_xhs_price = ?, grass_douyin_price = ?, grass_ecommerce_price = ?, offline_activity_price = ?, live_stream_price = ?, remark = ? WHERE name = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, commonEndorsement);
            statement.setString(2, portraitOneYear);
            statement.setString(3, portraitTwoYears);
            statement.setString(4, grassXHS);
            statement.setString(5, grassDouyin);
            statement.setString(6, grassEcommerce);
            statement.setString(7, offlineActivity);
            statement.setString(8, liveStream);
            statement.setString(9, remark);
            statement.setString(10, name);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 查询明星是否存在
    public static boolean searchStar(String name) {
        String sql = "SELECT COUNT(*) FROM stars WHERE name = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;  // 返回 true 如果找到该明星
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;  // 没有找到明星
    }

    // 获取明星的更新时间
    public static String getStarUpdateDate(String name) {
        String sql = "SELECT update_date FROM stars WHERE name = ?";
        return getSingleFieldFromDatabase(name, sql);
    }

    // 通用方法，用于从数据库中获取单个字段
    private static String getSingleFieldFromDatabase(String name, String sql) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 获取明星的常规代言价格
    public static String getStarCommonEndorsementPrice(String name) {
        String sql = "SELECT common_endorsement_price FROM stars WHERE name = ?";
        return getSingleFieldFromDatabase(name, sql);
    }

    // 获取明星的肖像一年价格
    public static String getStarPortraitPriceOneYear(String name) {
        String sql = "SELECT portrait_price_one_year FROM stars WHERE name = ?";
        return getSingleFieldFromDatabase(name, sql);
    }

    // 获取明星的肖像两年价格
    public static String getStarPortraitPriceTwoYears(String name) {
        String sql = "SELECT portrait_price_two_years FROM stars WHERE name = ?";
        return getSingleFieldFromDatabase(name, sql);
    }

    // 获取小红书种草价格
    public static String getStarGrassXHSPrice(String name) {
        String sql = "SELECT grass_xhs_price FROM stars WHERE name = ?";
        return getSingleFieldFromDatabase(name, sql);
    }

    // 获取抖音种草价格
    public static String getStarGrassDouyinPrice(String name) {
        String sql = "SELECT grass_douyin_price FROM stars WHERE name = ?";
        return getSingleFieldFromDatabase(name, sql);
    }

    // 获取种草+电商价格
    public static String getStarGrassEcommercePrice(String name) {
        String sql = "SELECT grass_ecommerce_price FROM stars WHERE name = ?";
        return getSingleFieldFromDatabase(name, sql);
    }

    // 获取线下活动价格
    public static String getStarOfflineActivityPrice(String name) {
        String sql = "SELECT offline_activity_price FROM stars WHERE name = ?";
        return getSingleFieldFromDatabase(name, sql);
    }

    // 获取直播加倍价格
    public static String getStarLiveStreamPrice(String name) {
        String sql = "SELECT live_stream_price FROM stars WHERE name = ?";
        return getSingleFieldFromDatabase(name, sql);
    }

    // 获取备注
    public static String getStarRemark(String name) {
        String sql = "SELECT remark FROM stars WHERE name = ?";
        return getSingleFieldFromDatabase(name, sql);
    }
}
