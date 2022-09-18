package service.weather;

public class WeatherServiceMock implements WeatherService {

    private Weather value;

    @Override
    public Weather currentWeather() {
        return value;
    }

    public void setValue(Weather value) {
        this.value = value;
    }
}


