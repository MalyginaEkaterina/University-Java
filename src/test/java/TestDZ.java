import org.junit.jupiter.api.Assertions;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class TestDZ {
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface TestLeap {
    }

    public void start() {
        File[] files = (new File("C:/fortest")).listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith("class");
            }
        });
        for (File file : files) {
            try {
                String className = file.getName();
                URL url = file.getParentFile().toURI().toURL();
                Class c = URLClassLoader.newInstance(new URL[]{url}).loadClass(className.substring(0, className.length() - 6));
                Method method = c.getDeclaredMethod("isLeapYear", int.class);
                Method[] testsLeap = this.getClass().getDeclaredMethods();
                for (Method t : testsLeap) {
                    if (t.getAnnotation(TestLeap.class) != null) {
                        try {
                            Object o = c.newInstance();
                            t.invoke(this, o, method);
                        } catch (InvocationTargetException e) {
                            System.out.println(c.getName() + " " + t.getName() + " failed");
                            continue;
                        }
                        System.out.println(c.getName() + " " + t.getName() + " passed");
                    }
                }
            } catch (MalformedURLException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                continue;
            }
        }
    }

    @TestLeap
    public void testLeapYear1(Object obj, Method method) {
        try {
            Assertions.assertTrue((Boolean) method.invoke(obj, 2000));
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @TestLeap
    public void testLeapYear2(Object obj, Method method) {
        try {
            Assertions.assertFalse((Boolean) method.invoke(obj, 2001));
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
