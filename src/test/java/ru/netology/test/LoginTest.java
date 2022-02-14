package ru.netology.test;

import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;


class LoginTest {

    @Test
    void shouldLoginSuccessfully() {
        open("http://localhost:9999");
        LoginPage loginPage = new LoginPage();
        var user = DataHelper.getVasya();
        var verificationPage = loginPage.validLogin(user);
        String verificationCode;
        verificationCode = DataHelper.getCodeByUser(user);
        DashboardPage dashboardPage = verificationPage.validVerify(verificationCode);
        assertEquals("Личный кабинет", dashboardPage.getHeading());
    }

    @Test
    void shouldLoginWithInvalidPassword() {
        open("http://localhost:9999");
        LoginPage loginPage = new LoginPage();
        var user = DataHelper.getPetyaInvalid();
        loginPage = loginPage.invalidLogin(user);
        assertEquals("Ошибка! Неверно указан логин или пароль", loginPage.getInvalidLoginNotification());
    }

    @Test
    void shouldLoginFail() {
        open("http://localhost:9999");
        LoginPage loginPage = new LoginPage();
        var user = DataHelper.getPetyaInvalid();
        loginPage = loginPage.multipleLogin(user);
        var newUser = DataHelper.getPetya();
        var verificationPage = loginPage.validLogin(newUser);
        assertEquals("Ошибка! Превышено количество попыток входа", loginPage.getInvalidLoginNotification());
    }
}

