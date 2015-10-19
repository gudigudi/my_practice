package head_first_design_pattern.Singleton;

/**
 * Created by gudi on 5/16/15.
 */
public class Singleton {

    private volatile static Singleton uniqueInstance = new Singleton();

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (uniqueInstance == null)
            uniqueInstance = new Singleton();
        return uniqueInstance;
    }


}

