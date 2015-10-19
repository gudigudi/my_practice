package head_first_design_pattern.Observer_pattern;


interface Subject {

    public void registerObserver();
    public void removeObserver();
    public void notifyObserver();

}
