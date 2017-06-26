<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Gift Sending</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui-1.12.1.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<script src="${pageContext.request.contextPath}/js/json2.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui-1.12.1.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</head>
<body>
<form id="sendingForm" action="giftSendingProceed.do" method="POST">
	<fieldset>
		<label for="typeFriend">好友</label>
		<input type="checkbox" id="typeFriend" name="type" value="好友" />
		<label for="typeFollow">追蹤</label>
		<input type="checkbox" id="typeFollow" name="type" value="追蹤" />
		<input type="button" class="btn btn-primary btn-sm" id="getSocialList" value="查詢名單" />
		<table id="socialListTable"></table>
	</fieldset>
	<fieldset id="itemSet">
		<c:choose>
			<c:when test="${not empty iList}">
				<table id="itemTable">
					<thead>
						<tr>
							<th></th>
							<th>productName</th>
							<th>remain amount</th>
							<th>using amount</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${iList}" var="iBean" varStatus="iStatus">
							<tr>
								<td>
									<input type="hidden" name="productId" value="${iBean.itemPK.productId}" />
								</td>
								<td>${iBean.productBean.productName}</td>
								<td>${iBean.amount}</td>
								<td>
									<select name="amount" id="${iBean.amount}">
									</select>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:when>
			<c:otherwise>
				<h2>查無資料</h2>
				<h3><a href="${pageContext.request.contextPath}/store/pList.do">前往購物</a></h3>
				<h3><a href="${pageContext.request.contextPath}/">返回首頁</a></h3>
			</c:otherwise>
		</c:choose>
	</fieldset>
	<input type="hidden" name="recipientId" />
	<input type="submit" value="送禮" />
</form>
</body>
<script>
$(function() {
	// default
	$('#itemSet').hide();
	
	// iList generate related
	$('select').each(function() {
		var remain = $(this).attr('id');
		if (remain > 20) {
			for (var i = 0; i <= 20; i++) {
				$(this).append($('<option value="' + i + '">' + i + '</option>'));
			}
		} else {
			for (var i = 0; i <= remain; i++) {
				$(this).append($('<option value="' + i + '">' + i + '</option>'));
			}	
		}
	});
	
	// type related
	$(':checkbox').checkboxradio({
		icon:false
	});
	
	// get social list
	$('#getSocialList').on('click', function() {
		var sTable = $('#socialListTable');
		sTable.empty();
		var types = [];
		$(':checkbox:checked').each(function() {
			types.push($(this).val());
		});
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
		});
	});
	
	// submit sending form
// 	$(':button').on('click', function() {
// 		$('#sendingForm').submit();
// 	});
});
</script>
</html>