## Stream API
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