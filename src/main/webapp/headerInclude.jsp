<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.headbackground {
	background-image: url("<c:url value = '/image/background/4.jpg'/>");
}
</style>
</head>
<body>
	<div class="section">
		<c:url value="/photo/album/list.do" var="album">
			<c:param name="mId" value="${user.mId}"></c:param>
		</c:url>
		<c:url value="/activity/myAct.do" var="myAct">
		</c:url>
		<ul>
			<li><c:if test="${not empty user}">
					<c:if test='${sessionScope.user.mValidManager == "Y"}'>
						<a href="manager/">後臺</a>
					</c:if>
				</c:if></li>
			<li><a href="<c:url value ='/index.jsp' />">首頁</a></li>
			<li><a href="<c:url value ='/user/' />">會員</a></li>
			<li><a href="${album}">相簿</a></li>
			<li><a href="${myAct}">活動</a></li>
			<li><a href="<c:url value ='/store/pList.do' />">商店</a></li>
			<li><a href="<c:url value ='/deposit/' />">儲值</a></li>
			<li><a href="<c:url value ='/report/' />">回報</a></li>
			<li><a href="<c:url value ='/announcement/' />">公告</a></li>
			<li><c:if test="${empty user}">
					<a href="check/register.v">註冊</a>
					<c:out escapeXml="false" value="<a href='check/login.r'>(登入)</a>" />
				</c:if> <c:if test="${not empty user}">
					<c:url var="x" value="/check/logout.do" />
					<c:out escapeXml="false" value="<a href='${x}'>${user.mName}</a>" />
				</c:if></li>
		</ul>
	</div>
</body>
</html>