public class WeatherData {

    int measure;
    public float getTemperature() {}
    public float getHumidity() {}
    public float getPressure() {}
    public void mentsChanged() {}
    public void measurementsChanged() {

        // get information
        float temp = getTemperature();
        float humidity = getHumidity();
        float pressure = getPressure();

        // Update ...
        currentConditionsDisplay.update(temp, humidity, pressure);
        statisticsDisplay.update(temp, humidity, pressure);
        forecastDisplay.update(temp, humidity, pressure);
    }

    // other methods
}
