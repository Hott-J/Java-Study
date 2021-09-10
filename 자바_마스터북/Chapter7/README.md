## 문자열 조작의 기본
### String 클래스의 특징
* String은 char의 배열을 유지
* String 객체는 한번 만들면 변경X (Immutable)
### 문자열을 결합하는 세 가지 방법
* StringBuilder 클래스를 사용
    * append 메서드
    * 가장 빠름
        * 여유 크기의 배열을 확보하고 있기 때문에 문자열 추가에서 매번 새로운 배열을 만들 필요가 없기 때문이다
    * 로컬 변수 등 여러 스레드로부터 액세스되지 않는 경우 사용
    * 여러 스레드로부터 사용되면 StringBuffer 클래스 사용
* + 연산자 사용
    * 가장 느림
    * 가독성 좋음
* String Class의 concat 메서드 사용

### 문자열 분할하기
* split 안의 인수는 정규 표현이다.
    * split("\\."): . 을 기준으로 쪼갬
        * . 은 정규표현에서 임의의 문자라는 특수한 의미이므로, 일반 문자로 인식시키기 위해 \\ 을 붙인다

### 여러 문자열 결합하기
* `String.join(".", "www", "jpub", "co", "kr") // www.jpub.co.kr`

### 문자열 치환하기
* `sentence.replace("is", "at") // is를 at으로 치환`

### 문자열 검색하기
*   ```java
    sentence.indexOf("is") // is가 처음으로 등작하는 인덱스를 반환, 없으면 -1 리턴`
    sentence.indexOf("is",3) // 4번째 문자 이후에서 is가 나타내는 인덱스 반환
    sentence.lastIndexOf("is") // 뒤에서부터 검색
    ```

## 정규 표현으로 문자열 유연하게 지정
### 문자열이 정규 표현 패턴에 적합한지 체크
* *: 임의의 0문자 이상의 문자열
* \\. : 마침표
```java
Pattern pattern = Pattern.compile("This is a .*\\/"); // 패턴 생성
String s = "This is a pen.";
Matcher matcher = pattern.match(s); // 정규 표현 처리를 실시하기 위한 클래스
if(matcher.matches()){sout("correct");}
```
### 정규 표현을 사용하여 문자열 분할
```java
Pattern p = Pattern.compile("\\s+"); // \\s는 공백, +는 하나 이상
String s = "This            is a pen";
String[] words = p.split(s); // This
                             // is
                             // a
                             // pen

```

### 정규 표현을 사용하여 문자열 치환
* `pattern.matcher.replaceAll(" ")` 사용
    * 정규 표현과 일치하는 문자열을 인수의 문자열로 치환

### String 클래스의 메서드로 정규 표현 사용
```java
String s = "This is           a pen"
s.matches("This.* is a .*\\.")); // 정규 표현과 일치하는지 체크
s.split("\\s+"); // 정규 표현 사용한 분할
s.replaceAll("\\s+"," "); // 정규 표현을 사용한 치환
```
* 한번만 문자열 처리를 할 경우(앱을 시작할 때의 인수 처리 등)
    * String 클래스를 사용하여 간결하게 작성
* 대량의 문자열을 반복하는 경우(로그 파일 처리 등)
    * 자신이 직접 Pattern 클래스의 객체를 생성하고 객체를 재사용

## 문자열의 포맷과 출력
### 문자열 출력하기
```java
int num = 3;
String pa = "apple"
String m = MessageFormat.format("I have {0} {1}.", num, pa);
```

## 문자 코드 변환하기
* 윈도우는 MS949, 리눅스에서 UTF-8, EUC-KR이라는 문자 코드 사용
* 문자 코드가 다르면 글자가 깨진다
### 자바는 어떻게 문자 코드를 이용하는가?
* 자바는 내부적으로 UTF-16으로 인코딩된 유니코드를 사용하여 문자열을 보관

### 자바 문자에서 임의의 문자 코드로 변환
* 자바로 문자에서 문자 코드를 만들려면 String 클래스의 getBytes 메서드를 사용

### 임의의 문자 코드로부터 자바 문자로 변환
* byte 배열로 지정된 문자 코드를 String 클래스의 생성자를 사용하여 문자열로 만듦

### 문자 깨짐의 원인과 대책
* 개발 중에는 문제가 없었는데 실제 운영 환경에서는 문자 깨짐이 발생
    * 디폴트 인코딩을 피하자
        * String생성자(인수없음)
        * String 클래스의 getBytes
        * FileReader
            * FileInputStream 사용하자
        * FileWriter
            * InputStreamReader 사용하자

### 서로게이트 페어?
* 2개의 char로 하나의 문자를 표현하는 경우가 있음
```java
String str = "코알라❤"
str.length() // 5
str.codePointCount(0,str.length()) //4
```

## String 클래스의 intern 메서드로 같은 문자열 찾기
* String 객체는 불변이라, 동일 문자열을 여러번 생성하면 그때마다 객체가 생성?
    * String 클래스의 객체는 Java VM이 관리하고 있어 'aaa'와 같이 문자열을 작성하는 경우 Java VM에서 동일 객체를 참조한다는 구조가 포함. 이를 명시하는 것이 intern 메서드
    intern 메서드를 호출하면 Java VM 에서 동일한 문자열이 있는 경우에 그 객체를 얻게 된다.
    ```java
    String aaa = "aaa"
    String aa = "aa"
    String a = "a"

    aaa.equals(aa+a) // true 문자열 비교
    aaa == (aa+a) // false 객체 자체는 다르므로 false
    aaa == (aa+a).intern() // true : Java VM에서 동일한 문자열을 나타내는 객체를 취득
    ```                           