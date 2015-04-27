interface WeatherDataSubject {

    // get datas
    public float getTemperature();
    public float getHumidity();
    public float getPressure();

    // for Observer
    public void registerObserver();
    public void removeObserver();
    public void notifyObserver();

    // other methods
}
