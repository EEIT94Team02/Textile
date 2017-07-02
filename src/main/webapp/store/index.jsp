<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商店</title>
<link rel="shortcut icon" type="image/png" sizes="32x32" href="<c:url value = '/image/icon/favicon-32x32.png'/>">
<link rel="shortcut icon" type="image/png" sizes="16x16" href="<c:url value = '/image/icon/favicon-16x16.png'/>">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui-1.12.1.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui-1.12.1.js"></script>
<style>
.father {
	padding: 15px;
	display: flex;
	flex-wrap: wrap;
	justify-content: center;
}
.container {
	position: relative;
	width: 30%;
	height: 30%;
}
.image {
	border: 1px solid pink;
	opacity: 1;
	transition: 0.5s ease;
	display: block;
	width: 100%;
	height: 100%;
	box-shadow: 3px 3px 3px pink;
}
.middle {
	opacity: 0;
	transition: 0.8s ease;
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	width: 100%;
	height: 100%;
}
.container:hover .image {
	opacity: 0.3;
}
.container:hover .middle {
	opacity: 1;
}
.info {
	background-color: #FFB6C1;
	color: #778899;
	font-size: 16px;
	padding: 16px 16px;
}
#left li {
	list-style-type: none;
	line-height: 5em;
} 
</style>
</head>
<body>
	<div id="header">
		<jsp:include page="/headerInclude.jsp"/>
	</div>
		<div id="center">
			<div id="body">
				<div class="father">
					<c:if test="${not empty pList}">
						<c:forEach items="${pList}" var="pBean" varStatus="pStatus">
							<c:url value="pShowImg.do" var="showImg" scope="page">
								<c:param name="productId" value="${pBean.productId}"/>
							</c:url>
							<c:if test="${pBean.status}">
								<div class="container">
									<c:out value="<form method='POST' action='${pageContext.request.contextPath}/store/cart.do'>" escapeXml="false" />
									<img class="image" src="${showImg}" width="200" />
									<div class="middle">
										<div class="info">
											<p>${pBean.productName}</p>
											<p>每個&nbsp;${pBean.unitPrice}&nbsp;點數&nbsp;&nbsp;&nbsp;可獲得&nbsp;${pBean.rewardPoints}&nbsp;點積分</p>
											<p>${pBean.category}</p>
											<p>${pBean.intro}</p>
											<p>
												<label>選擇數量：</label>
												<input type="text" class="spinner" name="amount" value="1" />
											</p>
											<p>
												<input type="button" class="btn btn-info btn-sm" id="add10" name="10" value="增加 10 個" />
												<input type="button" class="btn btn-info btn-sm" id="add50" name="50" value="增加 50 個" />
												<input type="button" class="btn btn-info btn-sm" id="add100" name="100" value="增加 100 個" />
												<input type="button" class="btn btn-info btn-sm" name="reset" value="重置" />
											</p>
											<p>
												<input type="hidden" name="productId" value="${pBean.productId}" />
												<input type="submit" class="btn btn-success btn-md" name="cartAction" value="加入購物車" />
											</p>
										</div>
									</div>
									<c:out value="</form>" escapeXml="false" />
								</div>
							</c:if>
						</c:forEach>
					</c:if>
				</div>
			</div>
			<div id="right">
				<jsp:include page="/rightInclude.jsp" />
			</div>
			<div id="left">
				<ul>
					<li>
						<input type="button" class="btn btn-success btn-lg" name="cart" value="購物車" />						
					</li>
					<li>
						<c:if test='${sessionScope.user.mValidManager == "Y"}'>
							<input type="button" class="btn btn-danger btn-lg" name="maintain" value="維護" />
						</c:if>
					</li>
				</ul>
			</div>
		</div>
	<div id="footer">
		<jsp:include page="/footerInclude.jsp" />
	</div>
</body>
<script>
$(function() {
	$(':button[name="maintain"]').on('click', function() {
		location.href='${pageContext.request.contextPath}/manager/pShowMaintain.do';
	});
	$(':button[name="cart"]').on('click', function() {
		location.href='cart.v';
	});
	$('.spinner').spinner({
		min: 1
	});
	$(':button, :submit').button();
	$(':button[id*="add"]').on('click', function() {
		var spinner = $('.spinner')
		var amount = Number($(this).attr('name'));
		var value = Number(spinner.spinner('value'));
		spinner.spinner('value', value + amount);
	});
	$(':button[name="reset"]').on('click', function() {
		$('.spinner').spinner('value', '0');
	});
});
</script>
</html>