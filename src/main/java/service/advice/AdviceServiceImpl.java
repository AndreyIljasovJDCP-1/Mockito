package service.advice;

import service.preference.Preference;
import service.preference.PreferencesService;
import service.weather.Weather;
import service.weather.WeatherService;

import java.util.Set;
import java.util.stream.Collectors;

public class AdviceServiceImpl implements AdviceService {

    private final PreferencesService preferencesService;
    private final WeatherService weatherService;

    public AdviceServiceImpl(PreferencesService preferencesService, WeatherService weatherService) {
        this.preferencesService = preferencesService;
        this.weatherService = weatherService;
    }

    public Set<Preference> getAdvice(String userId) {
        Weather weather = weatherService.currentWeather();
        Set<Preference> preferences = preferencesService.get(userId);
        if (Weather.RAINY == weather || Weather.STORMY == weather) {
            return preferences.stream()
                    .filter(p -> p != Preference.FOOTBALL)
                    .collect(Collectors.toSet());
        } else if (Weather.SUNNY == weather) {
            return preferences.stream()
                    .filter(p -> p != Preference.READING)
                    .collect(Collectors.toSet());
        }
        return preferences;
    }
}

