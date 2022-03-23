import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;

import java.util.stream.Stream;

public class GeoServiceTests {

    GeoService geoService;

    @BeforeAll
    public static void beforeAll() {
        System.out.println("Tests starts");
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("Test started");
        geoService = new GeoServiceImpl();
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
    public void geoTest(String ip, Country country) {
        Assertions.assertEquals(country, geoService.byIp(ip).getCountry());
    }

    public static Stream<Arguments> getArguments() {
        return Stream.of(Arguments.of("172.0.12.12", Country.RUSSIA), Arguments.of("96.44.121.157", Country.USA));
    }
}