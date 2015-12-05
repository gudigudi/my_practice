package lesson2;

public class Puppy {

    int puppyAge;

    public Puppy(String name) {
        System.out.println("Passed Name is : " + name);
    }

    public int getPuppyAge() {
        System.out.println("Puppy's age is : " + puppyAge);
        return puppyAge;
    }

    public void setPuppyAge(int puppyAge) {
        this.puppyAge = puppyAge;
    }

    public static void main(String[] args) {
        Puppy puppy = new Puppy("tommy");
        puppy.setPuppyAge(2);
        puppy.getPuppyAge();
        System.out.println("Variable Value : " + puppy.puppyAge);
    }
}
