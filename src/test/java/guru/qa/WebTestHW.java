package guru.qa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import guru.qa.domain.TestItem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class WebTestHW {

    @ValueSource(strings = {
            "Java",
            "REST"
    })
    @ParameterizedTest(name = "Checking Baeldung's search by word {0}")
    void baeldungSearchTest(String testData) {
        // Preconditions:
        Selenide.open("https://www.baeldung.com/");

        // Steps:
        $("#menu-item-40489").click();
        $("#search").setValue(testData).pressEnter();

        // Expected result:
        $$("#main")
                .find(text(testData))
                .shouldBe(Condition.visible);

    }

    @CsvSource({
            "Java, Learn how to configure the Java HttpClient for basic authentication and how it works.",
            "REST, Learn how to use Spring OAuth2RestTemplate to make OAuth2 REST calls"
    })
    @ParameterizedTest(name = "Checking Baeldung search by word {0}, expected result {1}")
    void baeldungSearchComplexTest(String testData, String expectedResult) {
        // Preconditions:
        Selenide.open("https://www.baeldung.com/");

        // Steps:
        $("#menu-item-40489").click();
        $("#search").setValue(testData).pressEnter();

        // Expected result:
        $$("#main")
                .find(text(testData))
                .shouldBe(Condition.visible);

    }

    @EnumSource(TestItem.class)
    @ParameterizedTest()
    void onlinerHeaderTest(TestItem testData) {
        // Preconditions:
        Selenide.open("https://onliner.by");
        //$(withTextCaseInsensitive(testData.headerName)).shouldBe(visible);
        $$(".b-top-menu")
                .find(Condition.text(testData.headerName)).shouldBe(visible);
    }

    static Stream<Arguments> methodSourceFilmTest() {
        return Stream.of(
                Arguments.of("Terminator", List.of(1, 5)),
                Arguments.of("The Lord of the ring", List.of(1, 3))
        );
    }

    @MethodSource("methodSourceFilmTest")
    @ParameterizedTest
    void methodSourceExampleTest(String filmName, List<Integer> second) {
        System.out.println(filmName + " and part:" + second);
    }

    @AfterEach
    void close() {
        Selenide.closeWebDriver();
    }
}


