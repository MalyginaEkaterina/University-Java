public class Main {

    public static void main(String[] args) {
        try {
            RunSuite.start("TestMyArrays");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
