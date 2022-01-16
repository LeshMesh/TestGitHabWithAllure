package qa.guru;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;

import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class WebSteps {

    @Step("Открываем главную страницу")
    public void openMainPage() {
        open("https://github.com");
    }

    @Step("Находим репозиторий")
    public void searchRepository(String repository) {
        $("[name=q]").setValue("eroshenkoam/allure-example").pressEnter();
    }

    @Step("Открываем репозиторий")
    public void openRepositoryPage(String repository) {
        $("[href='/eroshenkoam/allure-example']").click();
    }

    @Step("Проверяем наличие вкладки Issues")
    public void shouldSeeIssuesTab() {
        attachPageSource();
        $("#issues-tab").shouldHave(text("Issues"));
    }

    @Attachment(value = "Screenshot", type = "text/html", fileExtension = "html")
    public byte[] attachPageSource() {
        return WebDriverRunner.source().getBytes(StandardCharsets.UTF_8);
    }
}
