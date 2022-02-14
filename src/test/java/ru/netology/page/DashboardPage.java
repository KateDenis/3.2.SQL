package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id='dashboard']");


    public String getHeading() {
        return heading.shouldBe(visible, Duration.ofMillis(15000)).text().trim();
    }

}







