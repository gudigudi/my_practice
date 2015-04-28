package head_first_design_pattern.Observer_pattern.WeatherData;


public class StatisticDisplay implements Observer, DisplayElement {

    private float temperature;
    private float humidity;
    private Subject weatherData;

    public StatisticDisplay(Subject weatherData) {
        this.weatherData = weatherData;
    }

    public void update(float temp, float humidity, float pressure) {
    }

    public void display() {
        // display min, avg and max weather data
    }
}