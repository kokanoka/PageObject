package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;


import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
//    private SelenideElement firstCard = $$("[data-test-id=action-deposit]").first();
//    private SelenideElement secondCard = $$("[data-test-id=action-deposit]").last();
//    private static SelenideElement firstBalance = $("[data-test-id=\"92df3f1c-a033-48e6-8390-206f6b1f56c0\"]");
//    private static SelenideElement secondBalance = $("[data-test-id=\"0f3f5c2a-249e-4c3d-8287-09f7a039391d\"]");

    public DashboardPage() {
        $("[data-test-id='dashboard']").shouldBe(visible);
    }


    private ElementsCollection cards = $$(".list__item");
    private SelenideElement firstCard = $$("[data-test-id=action-deposit]").first();
    private SelenideElement secondCard = $$("[data-test-id=action-deposit]").last();
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public int getFirstCardBalance() {
        var text = cards.first().text();
        return extractBalance(text);
    }

    public int getSecondCardBalance() {
        var text = cards.last().text();
        return extractBalance(text);
    }

    public TransferPage firstCard() {
        firstCard.click();
        return new TransferPage();
    }

    public TransferPage secondCard() {
        secondCard.click();
        return new TransferPage();
    }

    private int extractBalance(String text) {
        var arr = text.split(" ");
        return Integer.parseInt(arr[5]);
    }

}
