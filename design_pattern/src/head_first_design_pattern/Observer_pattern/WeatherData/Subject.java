package head_first_design_pattern.Observer_pattern.WeatherData;

interface Subject {

    public void registerObserver(Observer o);
    public void removeObserver(Observer o);
    public void notifyObservers();

    public void measurementsChanged();
    public void setMeasurements(float temperature, float humidity, float pressure);
    // other methods
}
