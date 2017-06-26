<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<div id="header">
	<div class="section">
		<ul>
			<li><c:if test="${not empty user}">
					<c:if test='${sessionScope.user.mValidManager == "Y"}'>
						<a href="manager/">後臺</a>
					</c:if>
				</c:if></li>
			<li><a href="<c:url value ='/index.jsp' />">首頁</a></li>
			<li><a href="<c:url value ='/user/' />">會員</a></li>
			<li><a href="<c:url value ='/photo/'/>">相簿</a></li>
			<li><a href="<c:url value ='/activity/' />">活動</a></li>
			<li><a href="<c:url value ='/store/' />">商店</a></li>
			<li><a href="<c:url value ='/report/' />">回報</a></li>
			<li><a href="<c:url value ='/announcement/' />">公告</a></li>
			<li><c:if test="${not empty user}">
					<c:out escapeXml="false" value="<a href='../check/logout.do'>${user.mName}</a>" />
				</c:if></li>
		</ul>
	</div>
</div>
