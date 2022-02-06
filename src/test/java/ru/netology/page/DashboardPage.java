package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id='dashboard']");
//    private SelenideElement reloadButton = $("[data-test-id='action-reload']");

//    private final String balanceStart = "баланс: ";
//    private final String balanceFinish = " р.";

    public DashboardPage() {
        heading.shouldBe(visible);
    }
}






