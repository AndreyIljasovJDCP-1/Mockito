package service.advice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import service.preference.Preference;
import service.preference.PreferencesService;
import service.weather.Weather;
import service.weather.WeatherService;

import java.util.Set;

/**
 *  С помощью Аннотации @ExtendWith можно подключить дополнительные расширения функционала для тестового класса.
 */
@ExtendWith(MockitoExtension.class)
class AdviceServiceImplWithMockitoLibTest {

    private static final String USER_ID = "d930b754-e4fd-4675-a59e-93e5e0a193fc";

    /**
     * Аннотация @Mock эквивалетна записи Mockito.mock(PreferencesService.class).
     */
    @Spy
    private PreferencesService preferencesService;

    @Mock
    private WeatherService weatherService;

    private AdviceServiceImpl adviceService;

    @BeforeEach
    void setUp() {
        weatherService = Mockito.mock(WeatherService.class);
        adviceService = new AdviceServiceImpl(preferencesService, weatherService);

        /***
         * Если нам не важен аргумент на входе метода у мокируемого объекта - можем поставить Mockito.any().
         */
        Mockito.when(preferencesService.get(Mockito.any()))
                .thenReturn(Set.of(Preference.values()));

        /**
         *
         */

    }

    @Test
    void getAdvice() {
        Mockito.when(weatherService.currentWeather()).thenReturn(Weather.RAINY);

        Mockito.when(preferencesService.get(Mockito.eq(USER_ID))).thenReturn(Set.of(Preference.values()));

        Set<Preference> actualResult = adviceService.getAdvice(USER_ID);

        Set<Preference> expectedResult = Set.of(Preference.READING, Preference.WALKING, Preference.WATCHING_FILMS);

        Assertions.assertEquals(expectedResult, actualResult);
    }
}