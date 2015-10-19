package head_first_design_pattern.Observer_pattern.WeatherData;

public class ThirdPartyDisplay implements Observer, DisplayElement {

    public void update(float temp, float humidity, float pressure) {
    }

    public void display() {
        // display other contents
    }
}