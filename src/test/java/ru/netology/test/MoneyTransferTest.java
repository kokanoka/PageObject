package ru.netology.test;

import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {
    @Test
    void shouldTransferMoneyFromFirstCardToSecond() {
        int amount = 5000;
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var DashboardPage = verificationPage.validVerify(verificationCode);
        int balanceOfFirstCard = DashboardPage.getFirstCardBalance();
        int balanceOfSecondCard = DashboardPage.getSecondCardBalance();
        var TransferPage = DashboardPage.secondCard();
        var cardInfo = DataHelper.getFirstCardInfo();
        TransferPage.transferCard(cardInfo, amount);
        int balanceAfterTransferFirstCard = DataHelper.decreaseBalance(balanceOfFirstCard, amount);
        int balanceAfterTransferSecondCard = DataHelper.increaseBalance(balanceOfSecondCard, amount);
        int balanceOfFirstCardAfter = DashboardPage.getFirstCardBalance();
        int balanceOfSecondCardAfter = DashboardPage.getSecondCardBalance();
        assertEquals(balanceAfterTransferFirstCard, balanceOfFirstCardAfter);
        assertEquals(balanceAfterTransferSecondCard, balanceOfSecondCardAfter);
    }

    @Test
    void shouldTransferMoneyFromSecondCardToFirst() {
        int amount = 5000;
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var DashboardPage = verificationPage.validVerify(verificationCode);
        int balanceOfFirstCard = DashboardPage.getFirstCardBalance();
        int balanceOfSecondCard = DashboardPage.getSecondCardBalance();
        var TransferPage = DashboardPage.firstCard();
        var cardInfo = DataHelper.getSecondCardInfo();
        TransferPage.transferCard(cardInfo, amount);
        int balanceAfterTransferFirstCard = DataHelper.increaseBalance(balanceOfFirstCard, amount);
        int balanceAfterTransferSecondCard = DataHelper.decreaseBalance(balanceOfSecondCard, amount);
        int balanceOfFirstCardAfter = DashboardPage.getFirstCardBalance();
        int balanceOfSecondCardAfter = DashboardPage.getSecondCardBalance();
        assertEquals(balanceAfterTransferFirstCard, balanceOfFirstCardAfter);
        assertEquals(balanceAfterTransferSecondCard, balanceOfSecondCardAfter);
    }
}
