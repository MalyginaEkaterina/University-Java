import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Comparator;

public class RunSuite {
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface BeforeSuite {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface AfterSuite {
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface Test {
        int priority() default 5;
    }

    public static void start(String className) throws ClassNotFoundException {
        Class c = Class.forName(className);
        start(c);
    }

    public static void start(Class c) {
        try {
            Object testObj = c.newInstance();
            Method[] methods = c.getDeclaredMethods();
            Method before = null;
            Method after = null;
            ArrayList<Method> tests = new ArrayList<>();
            for (Method o : methods) {
                if (o.getAnnotation(BeforeSuite.class) != null) {
                    if (before != null) {
                        throw new RuntimeException("there are more than 1 BeforeSuite methods");
                    }
                    before = o;
                } else if (o.getAnnotation(AfterSuite.class) != null) {
                    if (after != null) {
                        throw new RuntimeException("there are more than 1 AfterSuite methods");
                    }
                    after = o;
                } else if (o.getAnnotation(Test.class) != null) {
                    tests.add(o);
                }
            }
            tests.sort(new Comparator<Method>() {
                @Override
                public int compare(Method o1, Method o2) {
                    return o1.getAnnotation(Test.class).priority() - o2.getAnnotation(Test.class).priority();
                }
            });
            if (before != null) {
                before.invoke(testObj);
            }
            for (Method method : tests) {
                try {
                    method.invoke(testObj);
                } catch (InvocationTargetException e) {
                    System.out.println(method.getName() + " failed");
                    continue;
                }
                System.out.println(method.getName() + " passed");
            }
            if (after != null) {
                after.invoke(testObj);
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
