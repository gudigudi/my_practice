package examples;

public class NotEnoughAtBatException extends Exception {
    private static final long serialVersionUID = 1L;
    private int atBatsNeeded;

    public NotEnoughAtBatException(String message, int currentBats) {
        super(message);
        atBatsNeeded = currentBats;
    }

    int getNeeded() {
        return atBatsNeeded;
    }
}