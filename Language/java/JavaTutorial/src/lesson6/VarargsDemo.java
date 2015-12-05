package lesson6;

public class VarargsDemo {
    public static void main(String[] args) {
        printMax(34, 3, 3, 2, 56.5);
        printMax(new double[]{1,2,3});
    }

    public static void printMax(double... numbers){
        if (numbers.length == 0){
            System.out.println("No argument passed");
            return;
        }
        double result = numbers[0];
        for (double i : numbers) {
            if (i > result){
                result = i;
            }
        }
        System.out.println("The max value is " + result);
    }
}
