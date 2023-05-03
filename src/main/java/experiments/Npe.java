package experiments;

public class Npe {

    private record Test(Object a) {}

    public static void main(String[] args) {
        Test test = new Test(new Object());
        Test testNull = new Test(null);

        System.out.println(test.a().toString());
        System.out.println(testNull.a().toString());
    }
}
