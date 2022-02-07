package ru.netology.test;

import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LoginTest {

    @Test
    void shouldLoginSuccessfully() {
        open("http://localhost:9999");
        LoginPage loginPage = new LoginPage();

        var user = DataHelper.getVasya();

        var verificationPage = loginPage.validLogin(user);
        String verificationCode = DataHelper.getCodeByUser(user);

        DashboardPage dashboardPage = verificationPage.validVerify(verificationCode);

//        assertEquals(balanceResult2, balance2 + balance1);
//        assertTrue(balanceResult1 >= 0);
    }
}

