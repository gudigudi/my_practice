package lesson6;

public class FinalizationDemo {
    public static void main(String[] args) {
        Cake cake1 = new Cake(1);
        Cake cake2 = new Cake(2);
        Cake cake3 = new Cake(3);

        cake2 = cake3 = null;
        System.gc();
    }
}

class Cake extends Object {
    private int id;

    public Cake(int id) {
        this.id = id;
        System.out.println("Cake Object " + id + " is created");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("Cake Object " + id + " is disposed");
    }
}
