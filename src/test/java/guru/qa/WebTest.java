package guru.qa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import  com.codeborne.selenide.WebDriverRunner;
import guru.qa.domain.MenuItem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class WebTest {

    @ValueSource(strings = {
            "Selenide",
            "JUnit"
    })
    @ParameterizedTest(name = "Checking Yandex search by word {0}")
    void yandexSearchTest(String testData) {
        // Preconditions:
        Selenide.open("https://ya.ru");

        // Steps:
        $("#text").setValue(testData);
        $("button[type='submit']").click();

        // Expected result:
        $$(".serp-item ")
                .find(Condition.text(testData))
                .shouldBe(Condition.visible);

    }

    @CsvSource({
            "Selenide, is an open source library for test",
            "JUnit, Support JUnit"
    })
    @ParameterizedTest (name = "Checking Yandex search by word {0}, ожидаем результат {1}")
    void yandexSearchComplexTest (String testData, String expectedResult) {
        // Preconditions:
        Selenide.open("https://ya.ru");

        // Steps:
        $("#text").setValue(testData);
        $("button[type='submit']").click();

        // Expected result:
        $$(".serp-item ")
                .find(Condition.text(expectedResult ))
                .shouldBe(Condition.visible);

    }

    static Stream<Arguments> methodSourceExampleTest() {
        return Stream.of (
                Arguments.of("first string", List.of(42, 13)),
                Arguments.of("second string", List.of(1, 2))
        );
    }

    @MethodSource("methodSourceExampleTest")
    @ParameterizedTest
    void methodSourceExampleTest (String first, List<Integer> second) {
        System.out.println(first + " and list:" + second );
    }

    @EnumSource(MenuItem.class)
    @ParameterizedTest( )
    void yandexSearchMenuTest(MenuItem testData) {
        // Preconditions:
        Selenide.open("https://ya.ru");

        // Steps:
        $("#text").setValue("Allure TestOps");
        $("button[type='submit']").click();

        // Expected result:
        $$(".navigation__item")
                .find(Condition.text(testData.rusName ))
                .click();

        Assertions.assertEquals(
                2,
                WebDriverRunner.getWebDriver().getWindowHandles().size()
        );
    }

    @AfterEach
    void close() {
        Selenide.closeWebDriver();
    }
}
