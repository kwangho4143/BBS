<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c"%>   
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="../menu/mainMenu.jsp" />
<hr>
	<div align="center"><h1>회원목록 정보</h1>
		<div>
			<table border="1">
				<tr>
					<th width="100">아이디</th>
					<th width="100">이름</th>
					<th width="200">주소</th>
					<th width="150">전화번호</th>
					<th width="150">가입일자</th>
					<th width="100">권한</th>
				</tr>
				<c:forEach var="member" items="${ members }">
					<tr>
						<td>${member.id } </td>
						<td>${member.name } </td>
						<td>${member.address } </td>
						<td>${member.tel } </td>
						<td>${member.enterdate } </td>
						<td>${member.author } </td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>	
</body>
</html>