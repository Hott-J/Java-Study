package 자바_마스터북.Chapter3;

public class test{
    public static void main(String[] args) {
        Integer num01 = new Integer(10); // 새로운 객체를 생성하기에 비효율적
        Integer num02 = Integer.valueOf(10); // 캐시된 객체를 반환
    }
}