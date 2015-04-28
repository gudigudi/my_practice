package head_first_design_pattern.Observer_pattern.JavaObserver;

import head_first_design_pattern.Observer_pattern.WeatherData.CurrentConditionsDisplay;
import head_first_design_pattern.Observer_pattern.WeatherData.ForecastDisplay;
import head_first_design_pattern.Observer_pattern.WeatherData.StatisticDisplay;
import head_first_design_pattern.Observer_pattern.WeatherData.WeatherData;

/**
 * Created by gudi on 4/28/15.
 */
public class WeatherStation {

    public static void main(String[] args) {

        head_first_design_pattern.Observer_pattern.WeatherData.WeatherData weatherData = new WeatherData();

        CurrentConditionsDisplay currentConditionsDisplay =
                new CurrentConditionsDisplay(weatherData);
        StatisticDisplay statisticDisplay =
                new StatisticDisplay(weatherData);
        ForecastDisplay forecastDisplay =
                new ForecastDisplay(weatherData);

        weatherData.setMeasurements(80, 65, 30.4f);
        weatherData.setMeasurements(82, 70, 29.2f);
        weatherData.setMeasurements(78, 90, 29.2f);
    }
}
