package service.advice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.preference.Preference;
import service.preference.PreferencesServiceMock;
import service.weather.Weather;
import service.weather.WeatherServiceMock;

import java.util.Set;

public class AdviceServiceImplWithoutMockitoLibTest {

    private static final String USER_ID = "d930b754-e4fd-4675-a59e-93e5e0a193fc";
    private PreferencesServiceMock preferencesService;
    private WeatherServiceMock weatherService;
    private AdviceServiceImpl adviceService;

    @BeforeEach
    void setUp() {
        preferencesService = new PreferencesServiceMock();
        weatherService = new WeatherServiceMock();

        weatherService.setValue(Weather.RAINY);
        preferencesService.setValue(Set.of(Preference.values()));

        adviceService = new AdviceServiceImpl(preferencesService, weatherService);
    }

    @Test
    void getAdvice() {
        Set<Preference> actualResult = adviceService.getAdvice(USER_ID);

        Set<Preference> expectedResult = Set.of(Preference.READING, Preference.WALKING, Preference.WATCHING_FILMS);

        Assertions.assertEquals(expectedResult, actualResult);
    }
}
