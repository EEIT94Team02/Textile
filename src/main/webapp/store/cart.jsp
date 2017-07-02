<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>購物車</title>
<link rel="shortcut icon" type="image/png" sizes="32x32" href="<c:url value = '/image/icon/favicon-32x32.png'/>">
<link rel="shortcut icon" type="image/png" sizes="16x16" href="<c:url value = '/image/icon/favicon-16x16.png'/>">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui-1.12.1.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui-1.12.1.js"></script>
</head>
<style>
#body {
	display: flex;
	flex-wrap: wrap;
	justify-content: center;
}

.cartForm {
	display: flex;
	flex-wrap: wrap;
	justify-content: center;
	width: 100%;
}

.container {
	padding: 7px;
	margin: 5px;
	width: 30%;
	border: .5px solid #778899;
	box-shadow: 5px 5px 5px #778899;
}

.sum, .errorSection {
	padding: 7px;
	margin: 5px;
	width: 80%;
	height: 20%;
	border: .5px solid #778899;
	text-align: center;
}

.headTo {
	display: inline;
}
#left li {
	list-style-type: none;
	line-height: 5em;
}
</style>
<body>
	<div id="header">
		<jsp:include page="/headerInclude.jsp" />
	</div>
	<div id="center">
		<div id="body">
			<c:choose>
				<c:when test="${not empty sessionScope.cartList && sessionScope.cartList.size != 0}">
					<c:if test="${not empty purchaseError || not empty errorDetail.actionError}">
						<div class="errorSection">
							<c:if test="${not empty purchaseError}">
								<h3>${purchaseError}</h3>
								<h3>尚不足&nbsp;${notEnough}&nbsp;點數</h3>
								<input type="button" class="headTo btn btn-info btn-lg" name="deposit" value="前往儲值" />
								<input type="button" class="headTo btn btn-primary btn-lg" name="store" value="返回商店" />
							</c:if>
							<c:if test="${not empty errorDetail.actionError}">
								<c:out value="<h3>${errorDetail.actionError}</h3><hr/>" escapeXml="false" />
							</c:if>
						</div>
					</c:if>
					<c:out value=" <form class='cartForm'> " escapeXml="false" />
					<c:forEach items="${cartList.content}" var="cList" varStatus="cStatus">
						<c:url value="/store/pShowImg.do" var="showImg" scope="page">
							<c:param name="productId" value="${cList.value.productBean.productId}" />
						</c:url>
						<div class="container">
							<img src="${showImg}" width="200" />
							<p>${cList.value.productBean.productName}</p>
							<p>每個&nbsp;${cList.value.productBean.unitPrice}&nbsp;點</p>
							<p>購物車中有&nbsp;${cList.value.amount}&nbsp;個</p>
							<p>
								<input type="hidden" name="id" value="${cList.value.productBean.productId}" /> <input type="text" name="amount"
									maxlength="4" value="${cList.value.amount}" /> <span>${errors[cList.value.productBean.productId].nAmount}</span>
								<input type="button" class="btn btn-success btn-md" name="action" value="修改" /> <input type="button"
									class="btn btn-danger btn-md" name="action" value="刪除" />
							</p>
						</div>
					</c:forEach>
					<div class="sum">
						<p>小計:&nbsp;${cartList.subtotal}&nbsp;點</p>
						<p>
							<input type="button" name="action" class="btn btn-success btn-md" value="結帳" /> <input type="button"
								name="action" class="btn btn-danger btn-md" value="清空購物車" /> <input type="button" name="store"
								class="headTo btn btn-primary btn-md" value="繼續購物" /> <input type="hidden" id="postId" name="productId"
								value="" /> <input type="hidden" id="postAmount" name="newAmount" value="" /> <input type="hidden"
								id="postSubtotal" name="totalCost" value="${sessionScope.cartList.subtotal}" /> <input type="hidden"
								id="postAction" name="adjustAction" value="" />
						</p>
					</div>
					<c:out value=" </form> " escapeXml="false" />
				</c:when>
				<c:otherwise>
					<div class="noCart">
						<h2>購物車中無商品。</h2>
						<input type="button" class="headTo btn btn-info btn-lg" name="store" value="前往商店" /> <input type="button"
							class="headTo btn btn-primary btn-lg" name="index" value="回首頁" />
					</div>
				</c:otherwise>
			</c:choose>
		</div>
		<div id="right">
			<jsp:include page="/rightInclude.jsp" />
		</div>
		<div id="left"></div>
	</div>
	<div id="footer">
		<jsp:include page="/footerInclude.jsp" />
	</div>
</body>
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script>
	$(function() {
		$(':button[name="action"]')
				.on(
						'click',
						function() {
							var action = $(this).val();
							$('#postAction').val(action);
							if (action == "修改" || action == "刪除") {
								var id = $(this).parents('p').find(
										'input[name="id"]').val();
								var amount = $(this).parents('p').find(
										'input[name="amount"]').val();
								$('#postId').val(id);
								$('#postAmount').val(amount);
								$('.cartForm')
										.attr('action',
												'${pageContext.request.contextPath}/store/adjust.do');
								$('.cartForm').attr('method', 'POST');
								$('.cartForm').submit();
							} else if (action == "結帳") {
								$('.cartForm')
										.attr('action',
												'${pageContext.request.contextPath}/deal/buy.do');
								$('.cartForm').attr('method', 'POST');
								$('.cartForm').submit();
							} else if (action == "清空購物車") {
								$('.cartForm')
										.attr('action',
												'${pageContext.request.contextPath}/store/clearCart.do');
								$('.cartForm').attr('method', 'POST');
								$('.cartForm').submit();
							}
						});
		$(':button[class*="headTo"]')
				.on(
						'click',
						function() {
							var headTo = $(this).attr('name');
							if (headTo == "deposit") {
								location.href = '${pageContext.request.contextPath}/deposit/';
							} else if (headTo == "store") {
								location.href = '${pageContext.request.contextPath}/store/pList.do';
							} else {
								location.href = '${pageContext.request.contextPath}/';
							}
						});
	});
</script>
</html>