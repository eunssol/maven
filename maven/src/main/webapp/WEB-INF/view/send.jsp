<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>1번째 : HttpServletRequest방식</h3>
이름 : ${name1 }<br>
이메일 : ${email1 }<br>
<h3>2번째 : @RequestParam 방식</h3>
이름2 : ${name2 }<br>
이메일2 : ${email2 }<br>
번호2 :${no}<br>
<h3>3번째 : 커멘드객체(request set) 방식</h3>
이름3 : ${vo.name }<br>
이메일3 : ${vo.email }<br>
번호3 : ${vo.no }<br>
<h3>커멘드객체(request set없이) 방식 (꼭 vo 아니더라도 MemberVo타입에서 앞글자만 소문자로 바꿔서도 쓸 수 있음)</h3>
이름3 : ${memberVo.name }<br>
이메일3: ${memberVo.email }<br>
번호3 : ${memberVo.no } <br>

<h3>request에서 get</h3>
아이디 : ${id }<br> <!--request에 set을 하던, model에 add를 하던, jsp입장에선 다 똑같아   --> 
<!-- 결론은 jsp에서 출력을 하려면 무조건 request에 저장이 되어있어야함 그래야 출력가능 model을 쓰든, modelview를 쓰든 request를 쓰든 request에 들어가기만 하면 돼 -->
</body>
</html>