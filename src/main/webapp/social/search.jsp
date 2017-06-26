<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Textile</h1>

	<fieldset>
		<c:out value="您的查詢結果" />
		<table>
			<thead>
				<tr>
					<th>名字</th>
					<th>群組</th>
					<th>結交時間</th>
					<th>類別</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="row" items="${search}">
					<tr>
						<td>${row.mbean.mName}</td>
						<td>${row.s_group}</td>
						<td>${row.log_in}</td>
						<td>${row.s_type}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</fieldset>

</body>
</html>