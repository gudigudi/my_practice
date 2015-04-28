package head_first_design_pattern.Duck_class;


public abstract class Duck {

    public void quack() {
        System.out.println("Duck can quack");
    }
    public void swim() {
        System.out.println("Duck can swim");
    }
    public abstract void display();
}
