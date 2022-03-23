import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.stream.Stream;

public class LocalizationServiceTests {

    LocalizationService localizationService;

    @BeforeAll
    public static void beforeAll() {
        System.out.println("Tests starts");
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("Test started");
        localizationService = new LocalizationServiceImpl();
    }

    @AfterEach
    public void afterEach() {
        System.out.println("Test finished");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("Tests finished");
    }

    @ParameterizedTest
    @MethodSource("getArguments")
    public void localeTest(String excepted, Country country) {
        Assertions.assertEquals(excepted, localizationService.locale(country));
    }

    public static Stream<Arguments> getArguments() {
        String englishMessage = "Welcome";
        return Stream.of(Arguments.of("Добро пожаловать", Country.RUSSIA), Arguments.of(englishMessage, Country.USA),
                Arguments.of(englishMessage, Country.BRAZIL), Arguments.of(englishMessage, Country.GERMANY));
    }
}