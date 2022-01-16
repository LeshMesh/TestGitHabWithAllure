package qa.guru;

import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Allure;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class GithubTestWithAllure {

    private static final String REPOSITORY = "eroshenkoam/allure-example";

    @Test
    public void issuesSearchTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com");
        $("[name=q]").setValue(REPOSITORY).pressEnter();
        $("[href='/eroshenkoam/allure-example']").click();
        $("#issues-tab").shouldHave(text("Issues"));
    }

    @Test
    public void lambdaStepTest() {

        step("Открываем главную страницу", () -> {
            open("https://github.com");
        });
        step("Находим репозиторий", () -> {
            $("[name=q]").setValue(REPOSITORY).pressEnter();
        });
        step("Открываем репозиторий", () -> {
            $("[href='/eroshenkoam/allure-example']").click();
        });
        step("Проверяем наличие вкладки Issues" , () -> {
            Allure.addAttachment("Page Source", "text/html", WebDriverRunner.source(), "html");
            $("#issues-tab").shouldHave(text("Issues"));
        });
    }

    @Test
    public void annotatedStepsTest() {
        WebSteps steps = new WebSteps();
        steps.openMainPage();
        steps.searchRepository(REPOSITORY);
        steps.openRepositoryPage(REPOSITORY);
        steps.shouldSeeIssuesTab();
    }
}
