package head_first_design_pattern.Observer_pattern.WeatherData;


interface Observer {

    public void update(float temp, float humidity, float pressure);
}
