<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cart page</title>
</head>
<body>
	<c:choose>
		<c:when test="${not empty sessionScope.cartList && sessionScope.cartList.size != 0}">
			<c:if test="${not empty purchaseError}">
				<h3>${purchaseError}</h3>
				<h3>尚不足&nbsp;${notEnough}&nbsp;點數</h3>
				<h4><a href="${pageContext.request.contextPath}/deposit/">前往儲值</a></h4>
				<h4><a href="${pageContext.request.contextPath}/store/index.v">返回商店</a></h4><hr/>
			</c:if>
			<c:if test="${not empty errorDetail.actionError}">
				<c:out value="<h3>${errorDetail.actionError}</h3><hr/>" escapeXml="false" />
			</c:if>
			<table>
				<thead>
					<tr>
						<th>img</th>
						<th>productName</th>
						<th>unitPrice</th>
						<th>amount</th>
						<th>action</th>
					</tr>
				</thead>
				<tbody>
				<c:out value=" <form class='cartForm'> "  escapeXml="false" />
			<c:forEach items="${cartList.content}" var="cList" varStatus="cStatus">
					<c:url value="/store/pShowImg.do" var="showImg" scope="page">
							<c:param name="productId" value="${cList.value.productBean.productId}"/>
					</c:url>
					<tr>
						<td><img src="${showImg}" height="200" /></td>
						<td>${cList.value.productBean.productName}</td>
						<td>${cList.value.productBean.unitPrice}</td>
						<td>${cList.value.amount}</td>
						<td>
							<input type="hidden" name="id" value="${cList.value.productBean.productId}" />
							<input type="text" name="amount" maxlength="4" value="${cList.value.amount}" />
							<span>${errors[cList.value.productBean.productId].nAmount}</span>
							<input type="button" value="Update" />
							<input type="button" value="Delete" />
						</td>
					</tr>
			</c:forEach>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td>
						<span id="return"></span>
						<p>小計: ${cartList.subtotal}</p>
						<input type="button" value="Purchase" />
						<input type="button" value="ClearCart" />
						<input type="hidden" id="postId" name="productId" value="" />
						<input type="hidden" id="postAmount" name="newAmount" value="" />
						<input type="hidden" id="postSubtotal" name="totalCost" value="${sessionScope.cartList.subtotal}" />
						<input type="hidden" id="postAction" name="adjustAction" value="" />
					</td>
				</tr>
			<c:out value=" </form> " escapeXml="false" />
				</tbody>
			</table>
		</c:when>
		<c:otherwise>
			<h3>尚無商品加入購物車中。</h3>
			<h4><a href=" <c:url value='/store/index.v' /> ">前往商店</a></h4>
			<h4><a href=" <c:url value='/index.v' />" >回首頁</a></h4><hr/>
		</c:otherwise>
	</c:choose>
</body>
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script>
$(function() {
	$('input[type="button"]').on('click', function() {
		var action = $(this).val();
		$('#postAction').val(action);
		if (action == "Update" || action == "Delete") {
			var id = $(this).parents('tr').find('input[name="id"]').val();
			var amount = $(this).parents('tr').find('input[name="amount"]').val();
			$('#postId').val(id);
			$('#postAmount').val(amount);
			$('.cartForm').attr('action', '${pageContext.request.contextPath}/store/adjust.do');
			$('.cartForm').attr('method', 'POST');
			$('.cartForm').submit();
		} else if (action == "Purchase") {
			$('.cartForm').attr('action', '${pageContext.request.contextPath}/deal/buy.do');
			$('.cartForm').attr('method', 'POST');
			$('.cartForm').submit();
		} else if (action == "ClearCart") {
			$('.cartForm').attr('action', '${pageContext.request.contextPath}/store/clearCart.do');
			$('.cartForm').attr('method', 'POST');
			$('.cartForm').submit();
		}
	});
});
</script>
</html>