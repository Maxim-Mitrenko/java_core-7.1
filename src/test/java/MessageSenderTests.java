import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSender;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.stream.Stream;

public class MessageSenderTests {

    Location location;
    GeoService geoService;
    LocalizationService localizationService;

    @BeforeAll
    public static void beforeAll() {
        System.out.println("Tests starts");
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("Test started");
        location = Mockito.mock(Location.class);
        geoService = Mockito.mock(GeoService.class);
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
    public void countyTest(String expected, Country country) {
        Mockito.when(location.getCountry()).thenReturn(country);
        Mockito.when(geoService.byIp(Mockito.<String>any())).thenReturn(location);
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        HashMap<String, String> map = new HashMap<String, String>();
        map.put(MessageSenderImpl.IP_ADDRESS_HEADER, " ");
        Assertions.assertEquals(expected, messageSender.send(map));
    }

    public static Stream<Arguments> getArguments() {
        return Stream.of(Arguments.of("Добро пожаловать", Country.RUSSIA), Arguments.of("Welcome", Country.USA));
    }
}