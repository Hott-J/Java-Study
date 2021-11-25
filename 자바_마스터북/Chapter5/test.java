import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.List;

public class test{
    public static void main(String[] args) {
        // Integer[] integerArray = IntStream.of(62,8,31).boxed()
        // .toArray(n -> new Integer[n]); 
        // //.collect(Collectors.toList());
        // System.out.println(integerArray.length);

        // List<Integer> integerList = IntStream.of(1,62,31).boxed()
        // .collect(Collectors.toList());
        // System.out.println(integerList);
        // System.out.print(Long.SIZE);
        String a ="123";
        char b = 'a';
        String c = "123";
        String d = "543";
        c = d;

        char e = '\u0000';
        System.out.print(e);
    }
}