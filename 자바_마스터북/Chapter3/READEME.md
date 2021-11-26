## 자바는 정적 타입 언어
- 변수의 선언 시에 타입을 명기
- 선언 시에는 타입을 명확하게 하지 않고 실행 시에 타입의 정합성을 체크: 동적 타입 언어
    - JavaScript, Ruby

## 기본형
- 논리형, 숫자형, 문자열형
- 소스 코드에 직접 기술된 값을 리터럴이라고 부름
- 긴 숫자형의 경우 언더스코어로 구분 표기 가능(자바8 이상)
    - `long amount = 123_456_789L;`
- 확장 변환 가능
    - short -> int
- 축소 변환 불가능
    - int -> short
    - 강제로 캐스팅해야한다
    - 큰 그릇의 물을 작은 그릇의 물에 담으면 물이 흘러넘칠 수 있는 것처럼 데이터가 유실될 수 있다

## 참조형
- 인스턴스를 특정하는 정보
- String 클래스


## 래퍼 클래스
- Byte, Short, Integer, Long, Character, Float, Double, Boolean, SIZE, BYTES, MAX_VALUE, MIN_VALUE
- valueOf(기본형의 값)
    - 기본형으로부터 래퍼 클래스의 객체로 변환
- valueOf(String s)
    - 문자열로부터 래퍼 클래스의 객체로 변환
- valueOf(String s, int radix)
    - 진수를 지정해서 문자열로부터 래퍼 클래스의 객체로 변환
- parseXxx(String s)
    - 문자열로부터 기본형의 값으로 변환
- toString(기본형의 값)
    - 기본형에서 문자열로 변환
- 래퍼 클래스는 초깃값이 null
- new Integer(10)와 같이 사용하는 것보다는, Integer.valueOf(10)을 사용하자!
    - -127~128 범위라면 사전에 생성된 객체를 이용할 수 있으므로 메모리 효율적
- 래퍼와 기본형이 크게 다른점은 초깃값
    - Integer: null
    - int: 0
    - 0과 데이터가 없는 상태를 구별하고 싶은 경우는 래퍼형을 준비 (HTTP 통신 등)
    - 수치 계산에 이용하는 변수는 기본형. 래퍼 -> 기본형 변환에 걸리는 시간도 무시할 수 없으므로

## 오토박싱과 언박싱
```java
int num = 10;
Integer numInt = 10; // 컴파일시 Integer.valueOf(10)으로 자동 변환, 오토박싱
Integer sum =- num + numInt; // numInt가 numInt.intValue()의 int로 자동 변환되어 언박싱, 연산결과를 다시 오토박싱한다
```

```java
Integer num1 = new Integer(3);
Integer num2 = new Integer(3);

num1 == num2; // false (객체가 다름)
num1.equals(num2); // true

Integer int1 = 128;
Integer int2 = 128;
int1 == int2; // false

Integer int1 = 127;
Integer int2 = 127;
int1 == int2; // true, -128 ~ 127의 범위 값에는 사전에 생성된 객체가 이용됨, 해당 값을 오토박싱한 객체는 항상 동일 객체가 됨
```

## 요약
* 원칙적으로 오토박싱, 언박싱은 이용하지 않고 명시적인 변환을 실시
* 파일이나 데이터베이스, HTTP 요청 등을 유지하는 값은 래퍼 클래스를 사용한다
* 수치 연산에 사용하는 변수는 기본형으로 한다
* 코딩량의 감소에 효과적인 경우에 한하여 오토박싱, 언박싱을 이용한다