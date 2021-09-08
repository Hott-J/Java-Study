## 예외의 기본
### 예외의 세 가지 종류
* 검사 예외
    * 프로그램 작성 시에 예상할 수 있는 비정상 상태를 통지
    * 파일 입출력
* 실행 시 예외
    * 프로그램 작성 시에 예상할 수 없는 비정상 상태를 통지
* 오류
    * 시스템의 동작을 계속할 수 없는 치명적 오류

### 예외를 나타내는 세 가지 클래스
* java.lang.Exception
    * 파일이나 네트워크 등의 입출력 중에 발생한 에러를 나타내는 java.io.IOException 클래스
    * 데이터베이스 액세스 중에 발생한 에러를 나타내는 java.sql.SQLException 클래스
    ```java
    public List<String> readFile() throws IOException{
        // 파일 읽는 처리
    }
    ```
* java.lang.RuntimeExcption
    * 예외를 어디서도 포착하지 못하면 해당 스레드 종료
* java.lang.Error
    * OutOfMemoryError
    * 신속하게 종료할 것

### 예외 처리의 세 가지 구문 제대로 사용
* try~catch~finally
    ```java
    try {
        // 예외가 발생하는 코드를 포함하는 처리
    } catch(SomeException ex) {
        // someException 예외를 catch한 경우의 처리
    } finally {
        // try~catch 블록을 종료할 때에 반드시 실행해야 하는 처리
    }
    ```
    * finally 블록에서는 데이터베이스 접속처럼 사용 후에 반드시 해제해야하는 리소스의 객체를 사용할 경우 자주 이용
* try~with~resources
    ```java
    try (InputStream is = Files.newInputStream(path)){
        is.read(contents);
    } catch (IOException ex) {
    }
    ```
    * finally가 없어도 된다???
    * 자바7부터 InputStream등의 리소스를 취급하는 클래스는 AutoClosable 인터페이스 또는 Closable 인터페이스를 구현하도록 되어있다. 즉, try~catch 블록의 종료 시의 처리에서 실시할 close 메서드를 자동으로 호출하게 된다.
    * try 블록 시작 시의 선언부에서 변수의 초깃값 대입이나 로그 출력등 리소스 확보/해제에 관련 없는 처리를 작성하지 말자! 여러 리소스를 확보해야하는 처리가 있다면 근본적으로 설계를 재검토하자!
* 다중 캐치
    * try블록안에서 여러 예외가 발생하는 경우
    ```java
    try {
        Class<?> clazz = Class.forName(className);
        SomeClass objSomeClass = clazz.newInstance();
    } catch (ClassNotFoundException |
            InstantiationException |
            IllegalAccessException ex) {
                // 오류 처리
    }

    // 공통 처리
    try {
        Class<?> clazz = Class.forName(className);
        SomeClass objSomeClass = clazz.newInstance();
    } catch (ClassNotFoundException ex){
        // classNotFound 예외 처리
    } catch (InstantiationException |
            IllegalAccessException ex) {
                // 두가지 오류 처리
    }
    ```
## 예외 처리에서 혼란에 빠지지 않기 위한 포인트
### 오류 코드를 return하지 않기
* 오류가 발생하면, 예외를 발생시켜야함
    * 모두 예외를 throw? -> X

### 예외를 제거하지 않기
* 로그 출력을 잊지 않는다
    * catch 블록에서 작업할 게 없더라도 혹시 예외가 발생할 수 있다. 로그정보를 블록에 넣자!
    ```java
    try{
        int intValue = Integer.valueOf(strValue);
    } catch (NumberFormatException ex) {
        log.warn("숫자가 아님", ex);
    }
    // 어떤 코드에서 예외가 발생했는지, 몇번 째 줄에서 에러가 났는지에 대한 예외의 스택 트레이스가 출력됨
    ```
* 처리를 계속할지 판단
    * catch 블록까지 처리가 넘어간 시점에서 객체의 초기화가 완료되지 않거나, 값을 얻지 못한 경우 NPE 발생
    catch 블록안에서 초기화 필요

### 공포의 throws Exception 감염
* 호출하는 곳에서 Exception을 포착해야 한다
* 도중에 IOException 등 구체적인 예외가 발생한다 해도 Exception에 휩쓸려 버린다
* 도중에 RuntimeException이 발생해도 Exception에 휩슬려 버린다

### 어느 계층에서 예외를 포착해서 처리해야 하는가?
* 예외가 발생하는 (가능성이 있는) 장소
* 처리의 흐름을 판단하는 장소

### 독자적인 예외 작성
* 일반적으로 자바 표준 API 예외 클래스를 선택하는 것이 좋다
* 예외를 개별적으로 처리하는 것이 아니라 통일해서 처리할 때 사용
* 애플리케이션 예외(ApplicationException Class)
* 시스템 예외(SystemException Class)
