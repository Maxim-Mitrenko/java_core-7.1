import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSender;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;

public class ServiceTests {

    @BeforeAll
    public static void beforeAll() {
        System.out.println("Tests starts");
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("Test started");
    }

    @AfterEach
    public void afterEach() {
        System.out.println("Test finished");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("Tests finished");
    }

    @Test
    public void rusText() {
        String expected = "Добро пожаловать";
        Location location = Mockito.mock(Location.class);
        Mockito.when(location.getCountry()).thenReturn(Country.RUSSIA);
        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(Mockito.<String>any())).thenReturn(location);
        LocalizationService localizationService = new LocalizationServiceImpl();
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        HashMap<String, String> map = new HashMap<String, String>();
        map.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.0.12.12");
        Assertions.assertEquals(expected, messageSender.send(map));
    }

    @Test
    public void usaTest() {
        String expected = "Welcome";
        Location location = Mockito.mock(Location.class);
        Mockito.when(location.getCountry()).thenReturn(Country.USA);
        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(Mockito.<String>any())).thenReturn(location);
        LocalizationService localizationService = new LocalizationServiceImpl();
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        HashMap<String, String> map = new HashMap<String, String>();
        map.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.44.183.149");
        Assertions.assertEquals(expected, messageSender.send(map));
    }

    @Test
    public void geoTest() {
        GeoService geoService = new GeoServiceImpl();
        Assertions.assertEquals(Country.RUSSIA, geoService.byIp("172.0.12.12").getCountry());
        Assertions.assertEquals(Country.USA, geoService.byIp("96.44.121.157").getCountry());
    }

    @Test
    public void localeTest() {
        LocalizationService localizationService = new LocalizationServiceImpl();
        Assertions.assertEquals("Добро пожаловать", localizationService.locale(Country.RUSSIA));
    }
}