<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>하이하이 정보수정페이지</h1>
<form action="update.do" method="post">
<input type="hidden" name="mno" value="${vo.mno }">
	<table border="1">
		<tr>
			<td>이름</td>
			<td><input type="text" name="mname" value="${vo.mname }"></td>
		</tr>
			<tr>
			<td>이메일</td>
			<td>
			<input type="text" name="email" value=""><br>  <!--컨트롤러에 "vo" 키이름  -->
	 		</td>
		</tr>
		
	<%-- 	<tr>
			<td>번호</td>
			<td><input type="text" name="mno" value="${vo.mno }"></td>
		</tr> --%>
		<tr>
			<td>비밀번호</td>
			<td><input type="password" name="pwd"></td>
		</tr>
		
		<tr>
			<td colspan="2" align="center"><input type="submit" value="정보수정">
			</td>
		</tr>
	</table>
	</form>
</body>
</html>