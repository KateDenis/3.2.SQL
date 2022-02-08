package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import lombok.Value;

import java.sql.DriverManager;

public class DataHelper {
    private DataHelper() {
    }

    @SneakyThrows
    public static String getCodeByUser(User user) {
        var codeSQL = "SELECT code FROM auth_codes WHERE user_id = ? ORDER BY created DESC LIMIT 1;";
        var runner = new QueryRunner();
        try (
                var conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app-db", "app", "YES"
                );
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

    @Value
    public static class User {
        private String id;
        private String login;
        private String password;
    }

    public static User getVasya() {
        return new User(
                "4130ac72-c7ba-4a3f-9d91-30b08f28e1ec",
                "vasya",
                "qwerty123"
        );
    }

    public static User getPetya() {
        return new User(
                "bfb439cd-3b78-43f7-aeb2-5fdabddf1a85",
                "petya",
                "123qwerty"
        );
    }

    public static User getPetyaInvalid() {
        return new User(
                "bfb439cd-3b78-43f7-aeb2-5fdabddf1a85",
                "petya",
                "123qwerty12"
        );
    }
}
