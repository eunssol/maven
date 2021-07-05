<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>  
<body>
<form action ="send.do" method="post">
<!-- 이름 : <input type = "text" name="name" required value=><br> 필수입력부분!!!! -->
이름 : <input type = "text" name="name" required value="${memberVo.name }"><br> <!-- 폼컨트롤러에 이메일이 공백일 경우 form.jsp나오게 되어있어 그때 입력한 이름값을 그대로 남겨두고 싶을 때
required value="${memberVo.name }" 얠 넣으면 돼  -->
이메일 : <input type="text" name="email"><br>
취미 : <input type="checkbox" name="hobby" value="1">독서
	  <input type="checkbox" name="hobby" value="2">게임
	  <input type="checkbox" name="hobby" value="3">영화
	  <input type="checkbox" name="hobby" value="4">등산

<input type="hidden" name="no" value="10"><br> <!-- 얘 때문에 10 나옴!-->
<input type="submit" value="전송"/>
</form>

</body>
</html>