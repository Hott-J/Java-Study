package Chapter7;

public class test {
    public static void main(String[] args) {
        String userNo2 = "rcmd:profile:userzsb3xTOPsvq1cOwYITKnEw==";
        String[] arr = userNo2.split("user", 2);
        String userNo1 = arr[0];
        String userNo3 = arr[1];
        System.out.println("arr:" + userNo3);
    }
}
