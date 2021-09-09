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

