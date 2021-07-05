<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div>
	<c:if test="${!empty memberInfo }">
	<input type="button" value="로그아웃" onclick="location.href='logout.do';">
	<input type="button" value="마이페이지" onclick="location.href='mypage.do';">
	
	</c:if>
	<c:if test="${empty memberInfo }">
	<a href="form.do"><input type="button" value="로그인"></a>
	<input type="button" value="로그인1" onclick="location.href='form.do';">
	</c:if>
</div>
<c:forEach var="member" items="${list }">
	${member.mno} ${member.mname } ${member.email }<br>
</c:forEach>
<hr> <!-- 자바코드로  -->
<%@ page import="chap07.*" %>
<%@ page import="java.util.*" %>
<%
 List<MemberVo> list = (List<MemberVo>)request.getAttribute("list"); /*  list란 이름으로 get해오는 거야 model에 list란 이름으로 저장했으니까*/
%>
<% for(int i=0;i<list.size();i++) {%><br>
	<%=list.get(i).getMno() %> <%=list.get(i).getMname() %> <%=list.get(i).getEmail() %>
<%} %>
<hr>


</body>
</html>