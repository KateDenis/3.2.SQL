package ru.netology;

import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import ru.netology.mode.User;

import java.sql.DriverManager;

public class DbInteractionDbUtils {
//    @BeforeEach
//    @SneakyThrows
//    void setUp() {
//        var faker = new Faker();
//        var runner = new QueryRunner();
//        var dataSQL = "INSERT INTO users(login, password) VALUES (?, ?);";
//
//        try (
//                var conn = DriverManager.getConnection(
//                        "jdbc:mysql://localhost:3306/app", "app", "pass"
//                );
//
//        ) {
//            // обычная вставка
//            runner.update(conn, dataSQL, faker.name().username(), "pass");
//            runner.update(conn, dataSQL, faker.name().username(), "pass");
//        }
//    }

    @SneakyThrows
    public String getCodeByUserId(String id) {
        var codeSQL = "SELECT code FROM auth_codes WHERE user_id = ? ORDER BY created DESC LIMIT 1;";
        var runner = new QueryRunner();

        try (
                var conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/ap-dbp", "app", "YES"
                );
        ) {
            String code = runner.query(conn, codeSQL, new ScalarHandler<>()).toString();

            return code;
        }
    }

//    @Test
//    @SneakyThrows
//    void stubTest() {
//        var countSQL = "SELECT COUNT(*) FROM users;";
//        var usersSQL = "SELECT * FROM users;";
//        var runner = new QueryRunner();
//
//        try (
//                var conn = DriverManager.getConnection(
//                        "jdbc:mysql://localhost:3306/app", "app", "pass"
//                );
//        ) {
//            var count = runner.query(conn, countSQL, new ScalarHandler<>());
//            System.out.println(count);
//            var first = runner.query(conn, usersSQL, new BeanHandler<>(User.class));
//            System.out.println(first);
//            var all = runner.query(conn, usersSQL, new BeanListHandler<>(User.class));
//            System.out.println(all);
//        }
//    }
}
