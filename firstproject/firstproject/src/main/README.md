# 웹서비스의 동작 원리
1. 웹서비스란 클라이언트와 서버의 요청과 응답으로 동작한다
2. 클라이언트란? 서비스를 사용하는 프로그램 또는 컴퓨터
3. 서버란? 서비스를 제공하는 프로그램 또는 컴퓨터
4. 첫시간 실습했던 "헬로월드!"는 클라이언트와 서버의 요청과 응답의 결과
   1. 브라우져가 클라이언트로서 동작 
   2. 스프링부트는 서버의 역할 수행.
5. localhost:8080/hello.html 
   1. localhost : 주소
   2. 8080 : 포트번호
   3. hello.html : 파일명(main\resources\static\여기서 찾을 수 있음)

# 뷰 템플릿과 MVC패턴
1. 뷰 템플릿(View Templates(presentation)) : 웹 페이지에 변수를 활용한다.
   1. 화면을 담당하는 기술, 웹페이지를 하나의 틀로 만들고 여기에 변수를 삽입하게한다(틀이되는 페이지가 변수에 값에 따라서 수많은 페이지로 바뀔 수 있다는 뜻), mustache이 도구가 바로 뷰 템플릿을 만드는 도구이다.
   2. 이 뷰 템플릿에는 Controller와 Model이라는 두 동료가 있다
   3. Controller : 처리과정을 담당(logic)
   4. Model : 데이터를 관리(data)
2. MVC패턴 : 분야별 담당자를 나눈다(화면, 처리, 데이터 분야를 각 담당자별로 나누는 기법)
