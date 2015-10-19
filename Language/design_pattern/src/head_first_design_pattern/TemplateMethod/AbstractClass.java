package head_first_design_pattern.TemplateMethod;

/**
 * Created by gudi on 5/17/15.
 */
abstract class AbstractClass {

    final void templateMethod() {
        primitiveOperation1();
        primitiveOperation2();
        concreteOperation();
        hook();
    }

    abstract void primitiveOperation1();

    abstract void primitiveOperation2();

    void concreteOperation() {
        // implemention
    }

    void hook();
}
