import java.lang.reflect.Field;

public class Main {
    public static void main(String[] args) throws Exception {
        //testclass
        System.out.println("Hello World");
        for(int i=0; i<10;i++){
            System.out.println(i);

        }
        System.out.println("Hi!");

        Integer i = 5;
        Integer j = 5;

        System.out.println( i == j);

        Integer k = 2000;
        Integer l = 2000;

        System.out.println( k == l);


        Class cache = Integer.class.getDeclaredClasses()[0];
        Field c = cache.getDeclaredField("cache");
        c.setAccessible(true);
        Integer[] array = (Integer[]) c.get(cache);
        array[132] = array[133];

        System.out.printf("2 + 2 = %d",2 + 2);
    }
}