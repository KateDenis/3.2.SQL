package ru.netology.page;

import org.openqa.selenium.Keys;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    public VerificationPage validLogin(DataHelper.User user) {
        this.auth(user);
        return new VerificationPage();
    }

    public LoginPage invalidLogin(DataHelper.User user) {
        this.auth(user);
        return new LoginPage();
    }

    public void auth(DataHelper.User user) {
        $("[data-test-id=login] input")
                .doubleClick().shouldBe(visible, Duration.ofMillis(15000)).sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=password] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=login] input").setValue(user.getLogin());
        $("[data-test-id=password] input").setValue(user.getPassword());
        $("[data-test-id=action-login]").click();
    }

    public String getInvalidLoginNotification() {
        return $("[class=notification__content]").shouldBe(visible, Duration.ofMillis(15000)).text();
    }
}
