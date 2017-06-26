<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Deal</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui-1.12.1.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui-1.12.1.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<style>
fieldset {
	width: 300px;
	border: 3px solid blue;
}
</style>
</head>
<body>
	<input type="button" class="btn btn-info btn-lg" id="condition" value="條件查詢" />
	<form id="dealCondition" action="dealList.do" method="POST">
		<fieldset>
			<legend>Select a date if need be:</legend>
			<label for="afterDate">此日期之後</label>
			<input type="text" id="afterDate" name="dealDateAfter" autocomplete="off" />
			<label for="beforeDate">此日期之前</label>
			<input type="text" id="beforeDate" name="dealDateBefore" autocomplete="off" />
		</fieldset>
		<input type="submit" value="開始查詢" />
		<input type="reset" value="清除條件" />		
	</form>
	<c:choose>
		<c:when test="${not empty dealList}">
			<table>
			<thead>
				<tr>
					<td>dealId</td>
					<td>memberName</td>
					<td>dealDate</td>
					<td>totalCost</td>
					<td></td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${dealList}" var="dealBean" varStatus="dealStatus">
					<tr>
						<td>${dealBean.dealId}</td>
						<td>${dealBean.memberBean.mName}</td>
						<td>${dealBean.dealDate}</td>
						<td>${dealBean.totalCost}</td>
						<td>
							<input type="button" name="submit" value="交易明細" />
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<form id="dealDetailForm" action="dealDetail.do" method="POST">
				<input type="hidden" name="dealId" value="${dealBean.dealId}" />
				<input type="hidden" name="memberId" value="${sessionScope.user.mId}" />
		</form>
		</c:when>
		<c:otherwise>
			<h2>查無紀錄</h2>
			<h3><a href="${pageContext.request.contextPath}/store/pList.do">前往購物</a></h3>
			<h3><a href="${pageContext.request.contextPath}/">返回首頁</a></h3>
		</c:otherwise>
	</c:choose>
</body>
<script>
$(function() {
	// default
	$('#dealCondition').hide();
	
	// action
	$('#condition').on('click', function() {
		$('#dealCondition').slideToggle();
	});
	
	// datepicker related config
	$(':text[name*="After"]').datepicker({
		dateFormat:'yy-mm-dd',
		contrainInput:false,
		showAnim:'slideDown',
		changeMonth:true,
		changeYear:true,
		onSelect: function() {
			$(':text[name*="Before"]').datepicker('option', 'minDate', $(this).val());
		}
	});
	$(':text[name*="Before"]').datepicker({
		dateFormat:'yy-mm-dd',
		contrainInput:false,
		showAnim:'slideDown',
		changeMonth:true,
		changeYear:true,
		onSelect: function() {
			$(':text[name*="After"]').datepicker('option', 'maxDate', $(this).val());
		}
	});
	
	// reinforce reset
	$(':reset').on('click', function() {
		$(':text[name*="dealDate"]').datepicker('option', 'maxDate', null)
		.datepicker('option', 'minDate', null).val('');
	});
	
	// deal detail query
	$(':button[name="submit"]').on('click', function() {
		var dealId = $(this).parents('tr').find('td:eq(0)').text();
		$(':hidden[name="dealId"]').val(dealId);
		$('#dealDetailForm').submit();
	});
});
</script>
</html>