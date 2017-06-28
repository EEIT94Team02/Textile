<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Gift Sending</title>
<link rel="shortcut icon" type="image/png" sizes="32x32" href="<c:url value = '/image/icon/favicon-32x32.png'/>">
<link rel="shortcut icon" type="image/png" sizes="16x16" href="<c:url value = '/image/icon/favicon-16x16.png'/>">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui-1.12.1.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<script src="${pageContext.request.contextPath}/js/json2.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui-1.12.1.js"></script>
</head>
<style>
#body {
	padding: 15px;
	display: flex;
	flex-wrap: wrap;
	justify-content: center;
}
#itemSet {
	padding: 15px;
	display: flex;
	flex-wrap: wrap;
	width: 100%;
	justify-content: center;
}
.container {
	position: relative;
	width: 30%;
	height: 30%;
}
</style>
<body>
	<div id="header">
		<jsp:include page="/headerInclude.jsp"/>
	</div>
		<div id="center">
			<div id="body">
				<form id="sendingForm" action="giftSendingProceed.do" method="POST">
					<fieldset>
						<label for="typeFriend">好友</label>
						<input type="checkbox" id="typeFriend" name="type" value="好友" />
						<label for="typeFollow">追蹤</label>
						<input type="checkbox" id="typeFollow" name="type" value="追蹤" />
					</fieldset>
					<fieldset id="socialListSet">
						<table id="socialListTable"></table>
					</fieldset>
					<fieldset id="itemSet">
						<c:choose>
							<c:when test="${not empty iList}">
								<c:forEach items="${iList}" var="iBean" varStatus="iStatus">
									<c:url value="/store/pShowImg.do" var="showImg" scope="page">
										<c:param name="productId" value="${iBean.itemPK.productId}"/>
									</c:url>
									<div class="container">
										<img src="${showImg}" width="200" />
										<p>
											<input type="hidden" name="productId" value="${iBean.itemPK.productId}" />
										</p>
										<p>${iBean.productBean.productName}</p>
										<p>擁有&nbsp;${iBean.amount}&nbsp;個</p>
										<p>
											<label>選擇數量：</label>
											<input type="text" class="spinner" id="${iBean.amount}" name="amount" value="1" />
										</p>
									</div>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<h3>物品欄空無一物。</h3>
								<input type="button" class="headTo btn btn-success btn-lg" name="store" value="前往購物" />
								<input type="button" class="headTo btn btn-primary btn-lg" name="index" value="返回首頁" />
							</c:otherwise>
						</c:choose>
					</fieldset>
					<input type="hidden" name="recipientId" />
					<input type="submit" class="btn btn-success btn-lg" value="送禮" />
				</form>
			</div>
			<div id="right">
				<jsp:include page="/rightInclude.jsp" />
			</div>
			<div id="left">
			</div>
		</div>
	<div id="footer">
		<jsp:include page="/footerInclude.jsp" />
	</div>


</body>
<script>
$(function() {
	// default
	$('#itemSet').hide();
	$('#socialListSet').hide();
	
	// iList generate related
	$('.spinner').spinner({
		min: 0
	});
	$('.spinner').each(function() {
		var remain = $(this).attr('id');
		$(this).spinner('option', 'max', remain);
	});
	
	// type related get socialList
	$(':checkbox').checkboxradio({
		icon:false
	}).on('click', function() {
		var sTable = $('#socialListTable');
		sTable.empty();
		var types = [];
		$(':checkbox:checked').each(function() {
			types.push($(this).val());
		});
		if (types.length != 0) {
			$.post('socialList.do', { 'sTypes[]':types }, function(datas) {
				if (datas.error != undefined) {
					sTable.append($('<tr></tr>').append($('<td></td>').text(datas.error)));
				} else {
					$.each(datas, function(name, value) {
						var choose = $('<td></td>').append($('<label for="choice'+ name +'">選擇</label>'))
													.append($('<input type="radio" id="choice'+ name +'" name="choice" />'));
						var fId = $('<td></td>').append($('<input type="hidden" />').val(value.acquaintanceId));
						var fName = $('<td></td>').text(value.acquaintanceName);
						var fType = $('<td></td>').text(value.type);
						var row = $('<tr></tr>').append(choose, fId, fName, fType);
						sTable.append(row);
					});
					$(':radio[name="choice"]').checkboxradio({
						icon:false
					}).on('click', function() {
						$('#itemSet').slideDown();
						var chooseId = $(this).parents('tr').find('td:eq(1)').find(':hidden').val();
						$(':hidden[name="recipientId"]').val(chooseId);
					});
				}
			}).done(function() {
				$('#socialListSet').slideDown();
			});
		} else {
			sTable.empty();
			$('#itemSet').slideUp();
			$('#socialListSet').slideUp();
		}
	});
	
	$('.headTo').on('click', function() {
		var headTo = $(this).attr('name');
		if (headTo == "store") {
			location.href='${pageContext.request.contextPath}/store/pList.do';
		} else {
			location.href='${pageContext.request.contextPath}/';
		}
	});
});
</script>
</html>