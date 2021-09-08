## Stream API
### 컬렉션 조작의 변화
* 대량 데이터를 연속 처리 및 컬렉션, 배열의 조작을 효율적으로 실시

```java
List<Student> students = new ArrayList<>();
students.add(new Student(~));
''
''
students.stream() // stream 인스턴스 생성
    .filter(s->s.getScore() >= 70) // 중간 작업 -> 복수로 가능
    .forEach(s->Sout(s.getName())); // 종료 작업 -> 한번만, 요소마다 처리 혹은 요소를 집계
```
* 가독성 증가, but 지나치면 오히려 감소

* 람다식 작성법
    * 메서드의 인수 등에 처리 그 자체를 건네는 것이 가능한 강력한 기법
    * Java ver.7: Comparator로 컬렉션 정리
    ```java
    List<Student> studentList = new ArrayList<>(); // generic
    studentList.add(new Student("Jung",100));
    studentList.add(new Student("chung",200));
    studentList.add(new Student("Kung",300)); // generic -> 두 원소 중 어느것으로 정렬?
    // Student 타입의 객체를 원소로 가진 리스트를 정렬하고자 한다면 Comparable 인터페이스 구현! 혹은 아래와 같은 Comparator 인터페이스 사용
    // Tip: Comparator 인터페이스는 equals 메소드는 저절로 구현 (Object 클래스를 상속하므로)
    Collections.sort(studentList, new Comparator<Student>) {
        @Override
        public int compare(Student s1, Student s2) {
            return Integer.compare(s1.getScore(). s2.getScore()); // return: 1,0,-1
        }
    }
    ```
    * Java ver.8: Lambda
    ```java
    List<Student> studentList = new ArrayList<>(); // generic
    studentList.add(new Student("Jung",100));
    studentList.add(new Student("chung",200));
    studentList.add(new Student("Kung",300));
    Collections.sort(studentList, (s1,s2) 
        -> Integer.compare(s1.getScore(), s2.getScore()) // return: 1,0,-1
        );
    ```

### 함수형 인터페이스 대신 사용
* 구현해야할 메서드가 하나밖에 없는 인터페이스 -> 람다식 대입
    * Comparator의 경우 compare 메서드 하나
```java
Student[] s = {
    new Student("hottj",100),
    new Student("Ken",200);
}

Arrays.sort(s, (Student s1, Student s2) -> 
    Integer.compare(s2.getScore(), s1.getScore()));

Arrays.stream(s).forEach(s -> sout(s.getName()));
```

### 람다식의 기본 문법
* (인수) -> { 처리 }
    * (s1, s2) 를 가지고 {}의 처리를 할 거야!
```java
(Student s1, Student s2) -> {
    return Integer.compare(s2.getScore(), s1.getScore()));
}
```
### 인수 부분의 생략
* 인수 타입 생략 가능
```java
(s1, s2) -> {
    return Integer.compare(s2.getScore(), s1.getScore()));
}
```
* 인수가 1개면 소괄호 생략 가능
```java
Consumer<String> c =
    s -> {sout(s);}
```
* 인수가 0개면 소괄호만 씀
```java
Runnable r = 
    () -> {sout("test");}
```

### 처리 쪽의 생략
* 람다식의 처리가 하나인 경우, return과 처리부의 중괄호, 세미클론 생략 가능
```java
(s1,s2) -> Integer.compare(s1.getScore(), s2.getScore())
```

### 메서드 참조
* 자바8에서는 이미 준비되어 있는 메서드 그 자체도 대입 가능
```java
list.forEach(System.out::println);
list.forEach(str -> System.out::println(str)); // 람다식
```
* 인스턴스의 메서드를 참조
    * {인스턴스명}::{메서드명}
* 자기 자신의 인스턴스의 메서드를 참조
    * this::{메서드명}
* static 메서드를 참조
    * {클래스명}::{메서드명}

## Stream 작성
### List나 Set으로부터 Stream 작성하기
```java
List<String> l = Arrays.asList("a","b");
Stream<String> s = list.stream();
s.forEach(System.out::println); // a,b
```
### 배열로부터 Stream 작성
```java
List<String> ㅁ = {"a","b"};
Stream<String> s = Arrays.stream(a);
s.forEach(System.out::println); // a,b

Stream<String> s = Stream.of("a","b");
s.forEach(System.out::println); // a,b
```
### Map으로부터 Stream 작성
* Map의 entrySet 메서드에 의해 Set을 취득하고 Set 메서드의 stream 메서드를 호출
```java
Map<String, String> m = new HashMap<>();
map.put("1","hottj");
map.put("2","hottj2");

Stream<Entry<String,String>> s = map.entrySet().stream();
s.forEach(e->sout(e.getKey() + "" + e.getValue()));

Stream<String> s = map.values().stream();
s.forEach(System.out::println);
```
### 숫자 범위로부터 Stream 작성
```java
IntStream s = IntStream.range(1,5); // 1 ~ 4
s.forEach(System.out::println); //1234

IntStream s = IntStream.rangeClosed(1,5); // 1 ~ 5
s.forEach(System.out::println); //12345

Instream.range(0,count).forEach(i -> {
    //처리
}); // for 문이 가독성이 좋으니 for문 사용할 것!
```

## Stream에 대한 '중간 작업'
### 요소를 치환하는 중간 작업
* 메서드명에 'map'이 들어 있는 중간 작업은 요소를 치환하는 것을 목적으로한다
```java
List<Student> studentList = new ArrayList<>();
studentList.add(new Student("Jung",100));
studentList.add(new Student("chung",200));
studentList.add(new Student("Kung",300));

Stream<Integer> stream = studentList.stream()
    .map(s -> s.getScore());
stream.forEach(System.out::println);

studentList.stream()
    .map(Student::getScore)
    .forEach(System.out::println) // 위와 동일
```
* map과 flatMap 메서드의 차이
    * 확인 필요

### 요소를 걸러내는 중간 작업
* filter
    * 조건에 일치하는 요소만을 걸러냄
    * boolean으로 반환
    * 인수가 Stream 대상의 객체
* limit
    * 지정한 건수만을 추출
* distinct
    * 유니크한 요소만으로 추출

### 요소를 정렬하는 중간 작업
* sorted
    * 2개의 인수를 받아서 int를 건네는 함수 객체(Comparator)를 지정
    * 음수: 인수1의 객체가 앞
    * 양수: 인수2의 객체가 앞
    * 0: 나열 순서 그대로

    ```java
    students.stream()
        .sorted((s1,s2) -> s2.getScore() - s1.getScore())
        .forEach(s -> sout(s.getName()));
    ```

## Stream에 대한 '종료 작업'
### 반복 처리를 실시하는 종료 작업
* forEach 메서드
    * 인수: Consumer(인수가 Stream 대상인 객체, 반환값X)

### 결과를 정리해서 추출하는 종료 작업
* collect
    * 요소를 스캐닝하여 결과를 작성
* toArray
    * 모든 요소를 배열로 변환
* reduce
    * 값을 집약
* Collector 클래스의 각 메서드
    * toList
    * toSet
    * joining
    * groupingBy    
    ```java
    Map<Integer, List<Student>> map = students.stream()
        .collect(Collector.groupBy(Student::getScore));
    // 그룹을 나눠 키로 점수, 값에 대응하는 학생의 리스트가 들어있는 Map 생성
    ```

### 결과를 하나만 추출하는 종료 작업
* findFirst
    * 선두 요소 반환
* findAny
    * 어떤 한 요소 반환
* min
* max
* 반환값은 전부 Optional 객체

### 집계 처리를 실시하는 종료 작업
* count / min / max / sum / average

## Stream API를 사용하기 위한 포인트
### 왕도는 map, filter, collect
```java
List<String> l = list.stream()
    .filter(p -> p.length() > 5)
    .map(p -> "[" + p + "]")
    .collect(Collector.toList());
```

### n번 반복하는 IntStream
* IntStream은 숫자값의 배열로부터 Stream을 만드는 것인데, for문을 치환하는 용도로도 이용
```java
String[] a = new String[5];
Arrays.fill(a,"?");
String query = StringUtils.join(a,","); // ?,?,?,?,?
// 일부러 배열을 한 번 만들고 있다는 낭비

String query = IntStream.range(0,5)
    .mapToObj(i -> "?")
    .collect(Collector.joining(","));
// IntStream의 map 반환값은 int를 기대하므로 map 메서드가 아니라 mapToObj 메서드를 사용
```

### List나 Map에 대한 효율적인 처리 실시
* Stream 인스턴스 없이 비슷한 처리 실시
* List
    * removelf
        * 인수로 주어진 함수의 결과값이 true를 반환하는 모든 요소에 대해 List에서 삭제
    * replaceAll
        * 인수로 주어진 함수의 결과로 List의 모든 요소를 치환
* Map
    * compute
        * 인수로 주어진 함수의 결과를 Map에 재설정
    * computeIfPresent
        * 키가 있을 때만, 인수로 주어진 함수의 결과를 Map에 재설정
    * computeIfAbsent
        * 키가 없을 때만, 인수로 주어진 함수의 결과를 Map에 재설정

## Stream API를 사용하여 List 초기화
### Stream을 이용해 열거한 값으로 List 작성
```java
List<Integer> integerList = IntStream.of(1,62,31).boxed().collect(Collector.toList());
// boxed 메서드로 기본형인 int를 Integer로 변환

List<String> stringList = Stream.of("Hottj").collect(Collector.toList());
// 문자열 등의 다른 타입의 리스트를 작성할 경우 Stream 이용
```

### Stream을 이용해 값의 범위로부터 List 작성
```java
IntStream.range(1,100).boxed().collect(Collector.toList())
```

### Stream을 이용해 배열 작성
```java
Integer[] integerArray = IntStream.of(1,62,31).boxed()
    .toArray(n -> new Integer[n]);
```

