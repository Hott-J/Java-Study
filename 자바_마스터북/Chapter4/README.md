## 배열로 여러 데이터 처리하기
### 배열
    * 배열보단 컬렉션을 사용하자
```java
int[] array = new int[10];
array[0] = 1;
```
* 복수의 값을 보관하거나 동일 처리를 반복해서 계산할 때 유용

### 배열 초기화
```java
int[] a1 = new int[10]; // 크기만 지정 (1)
int[] a2 = {1,2,3}; // 초깃값 지정 (2)
int[] a3 = new int[] {10,9,8}; // 초깃값과 타입 지정 (3)
```
* 선언 시에 내용이 정해져 있지 않다 -> new 로 요소 수만을 지정 (1) -> 컬렉션 이용하는게 좋다
* 선언 시에 내용이 정해져 있다 -> 값의 리스트를 열거 (2)
* 선언 후에 내용이 정해지거나 인수로 이용 -> new 선언을 붙여서 값의 리스트를 열거 (3)

### 배열의 대입과 취득
* ArrayIndexOutOfBoundException에 주의
    * index에 -1 불가능

### 배열의 사이즈 변경하기
* 배열은 한번 작성하면 요소 수를 변경 X
    * 새로운 배열을 작성한 후에 예전 배열에서 새로운 배열로 필요한 정보를 복사
    * 자바6 이후에 Arrays 클래스의 copyOf 메서드 사용
```java
int[] a = {1,2,3};
int[] newA = Arrays.copyOf(a,a.length + 3); // 두번째 인수에 길이 지정
a[3] = 4;
a[4] = 5;
a[5] = 6;
a[6] = 7; // IndexOutOfBoundException
```
* ArrayList 클래스 등 컬렉션 클래스를 이용하는 편이 적절함

### Arrays 클래스를 이용하여 배열 조작
* 배열의 문자열 변환
    * Arrays.toString()
    * 배열 요소의 리스트를 문자열로 변환
* 배열의 정렬
    * Arrays.sort()
    * 오름차순
    * 객체: Comparable 인터페이스의 compareTo 메서드를 사용
    * 객체의 경우 정렬의 대상이 되는 클래스가 Comparable 인터페이스의 구현 클래스가 아니면 ClassCastException이 발생
    ```java
    Integer[] a = {3,1,13,2};
    Comparator<Integer> c = new Comparator<Integer> () {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2.compareTo(01);
        }
    };

    Arrays.sort(array, c);
    ```
    * compare 메서드의 반환값에 따라 정렬의 동작이 변함
        * 반환값 1 이상: 인수1 -> 인수2의 순서로 정렬
        * 반환값 0미만: 인수2 -> 인수1의 순서로 정렬
    * Comparable은 compareTo 메소드를 구현하는데, 이는 한 종류의 정렬밖에 못하므로, Comparator을 사용하자
* 배열의 검색
    * 정렬이 되어 있는 경우, 이진탐색으로 빠른 검색
    ```java
    int[] a = {1,1,4,5,7,8};
    int f = Arrays.binarySearch(a,5); // 3
    int nF = Arrays.binaraySearch(a,6); // -5: 만일 그 요소가 들어있다고 치면 몇번째가 될까? 에서 마이너스 붙이고 거기서 1을 뺀 수가 반환됨. 6은 4번째 일 것이므로 -5 반환
    * 정렬이 되어있지 않은 경우
        * Arrays 클래스의 sort 메서드 사용한 뒤, 이진탐색
        * 선형 탐색
    * 동일 데이터에 대해서 몇 번이고 검색을 할 경우
        * sort와 같이 사전에 정렬한 후 검색
    * 한 번만 검색
        * 선형 탐색

### 가변 길이 인수로 메서드 정의하기
```java
void log (String msg, String[] args) {
    for (String a: args) {
        sout(arg);
    }
}

log("~", new String[]{"user","Ken"});
log("~", {"user","Ken"}); // 에러 : 인자가 배열일 경우, new로 생성해줘야한다.
```
* 계속 new로 객체 생성하는 것은 낭비이고 중복
```java
void log (String msg, String... args) {
    for (String a: args) {
        sout(arg);
    }
}

log("~", "user","Ken"); // OK. 컴파일 시에 가변 길이 인수가 배열로 변환됨
```

## 컬렉션 프레임워크로 여러 데이터 처리하기
### 배열의 한계와 컬렉션의 특징
* 컬렉션은 배열과 달리 처음부터 크기의 제한을 결정할 필요가 없이 원하는 만큼 데이터를 저장
* 많은 인터페이스 구현이 준비됨
    * 배열: 리스트
    * 키:값: 맵

## 배열과 비슷한 방법으로 여러 요소 처리하기 - List
* 요소 추가: add
* 요소 덮어씀: set
* 인덱스로 요소 취득: get

### List 작성하기
```java
// 비어있는 리스트 초기화
List<Integer> l = new ArrayList<>(); 
l.add(1);
l.add(62);
l.add(31);

// 간결하게 작성
List<Integer> l = Arrays.asList(1,62,31);
// 위 방법은 작성한 리스트에 대해 요소 추가, 변경, 삭제 불가능

//해결하기 위해
List<Integer> l = new ArrayList<>(Arrays.asList(1,62,31));
```

