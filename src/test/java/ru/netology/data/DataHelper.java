package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import lombok.SneakyThrows;
import lombok.Value;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

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
                        "jdbc:mysql://localhost:3306/ap-dbp", "app", "YES"
                );
        ) {
            var dataStmt = conn.prepareStatement(codeSQL);
            dataStmt.setString(1, user.getId());

            String code;
            try (var rs = dataStmt.executeQuery()) {
                rs.first();
                code = rs.getString("code");
            }

//            try (var rs = cardsStmt.executeQuery()) {
//                while (rs.next()) {
//                    var id = rs.getInt("id");
//                    var number = rs.getString("number");
//                    var balanceInKopecks = rs.getInt("balance_in_kopecks");
//                    // TODO: сложить всё в список
//                    System.out.println(id + " " + number + " " + balanceInKopecks);
//                }
//            }

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
}
