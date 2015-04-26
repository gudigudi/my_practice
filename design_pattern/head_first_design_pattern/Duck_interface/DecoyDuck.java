public class DecoyDuck extends Duck {
    public void display() {
        System.out.println("DecoyDuck display!");
    }

    public static void main(String[] args) {
        DecoyDuck a = new DecoyDuck();
        a.display();
    }
}
