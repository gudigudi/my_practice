package example;

public class BatterImpl implements Batter {
    private int atBats;
    private int hits;

    public float getAverage() throws NotEnoughAtBatException {
        if (atBats < 10) {
            throw new NotEnoughAtBatException("Insufficient at bats",
                    10 - atBats);
            return ((float)hits / (float)atBats);
        }
    }
}