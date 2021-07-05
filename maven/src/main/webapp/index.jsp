<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//response.sendRedirect("index.do");
%>
<script>
location.href='index.do';
</script>

<!-- 위에 2개 무슨 차이? =   sendRedirect 는 서버에서 실행 서버에서 보냄,location.href는 클라이언트에서 보냄
샌드리다이렉트 하면 응답 안 하고 index.do로 가고 클라이언트가 응답하는 거..?엥?

응답을 함 location때문에 다시 요청이 일어나서 index.do가 응답을 함
-->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
wepabb 밑에 index.jsp
</body>
</html>