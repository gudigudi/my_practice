public class Duck {
    FlyBehavior flyBehavior;
    QuackBehavior quackBehavior;

    public void performQuack() {

        quackBehavior.quack();
    }
    public void performFly() {
        flyBehavior.fly();
    }

    public void swim() {

    }

    public void display() {

    }


    // other methods
}
