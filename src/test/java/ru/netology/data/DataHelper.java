package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import lombok.Value;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataHelper {
    private DataHelper() {
    }

    @SneakyThrows
    private static Connection getConnection() {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/app-db", "app", "YES"
        );
    }

    @SneakyThrows
    public static String getCodeByUser(User user) {
        var codeSQL = "SELECT code FROM auth_codes WHERE user_id = ? ORDER BY created DESC LIMIT 1;";
        var runner = new QueryRunner();
        try (
                var conn = getConnection();
        ) {
            var dataStmt = conn.prepareStatement(codeSQL);
            dataStmt.setString(1, user.getId());

            String code = "";
            try (var rs = dataStmt.executeQuery()) {
                if (rs.next()) {
                    code = rs.getString("code");

                }
            }
            return code;
        }
    }

    @SneakyThrows
    public static String getUserIdByLogin(String login) {
        var codeSQL = "SELECT id FROM users WHERE login = ? LIMIT 1;";
        var runner = new QueryRunner();
        try (
                var conn = getConnection();
        ) {
            var dataStmt = conn.prepareStatement(codeSQL);
            dataStmt.setString(1, login);

            String id = "";
            try (var rs = dataStmt.executeQuery()) {
                if (rs.next()) {
                    id = rs.getString("id");

                }
            }
            return id;
        }
    }

    @Value
    public static class User {
        private String id;
        private String login;
        private String password;
    }

    public static User getVasya() {
        String login = "vasya";

        return new User(
                getUserIdByLogin(login),
                login,
                "qwerty123"
        );
    }

    public static User getPetya() {
        String login = "petya";

        return new User(
                getUserIdByLogin(login),
                login,
                "123qwerty"
        );
    }

    public static User getPetyaInvalid() {
        String login = "petya";

        return new User(
                getUserIdByLogin(login),
                login,
                "123qwerty12"
        );
    }

    @SneakyThrows
    public static void clearDB() {
        try (
                var conn = getConnection();
        ) {
            var dataStmt = conn.prepareStatement("SET FOREIGN_KEY_CHECKS = 0;");
            dataStmt.executeUpdate();
            dataStmt = conn.prepareStatement("TRUNCATE card_transactions ;");
            dataStmt.executeUpdate();
            dataStmt = conn.prepareStatement("TRUNCATE users ;");
            dataStmt.executeUpdate();
            dataStmt = conn.prepareStatement("TRUNCATE cards ;");
            dataStmt.executeUpdate();
            dataStmt = conn.prepareStatement("TRUNCATE auth_codes ;");
            dataStmt.executeUpdate();
        }
    }
}
