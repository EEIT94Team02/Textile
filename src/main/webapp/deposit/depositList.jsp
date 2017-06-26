<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Deposit</title>
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
	<form id="depositCondition" action="dList.do">
		<fieldset>
			<legend>Select a date if need be:</legend>
			<label for="afterDate">此日期之後</label>
			<input type="text" id="afterDate" name="depositDateAfter" autocomplete="off" />
			<label for="beforeDate">此日期之前</label>
			<input type="text" id="beforeDate" name="depositDateBefore" autocomplete="off" />
		</fieldset>
		<input type="submit" value="開始查詢" />
		<input type="reset" value="清除條件" />		
	</form>
	<c:choose>
		<c:when test="${not empty dList}">
			<table>
			<thead>
				<tr>
					<th>depositId</th>
					<th>memberName</th>
					<th>depositDate</th>
					<th>depositAmount</th>
					<th>virtualPoints</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${dList}" var="dBean" varStatus="dStatus">
				<tr>
					<td>${dBean.depositId}</td>
					<td>${dBean.memberBean.mName}</td>
					<td>${dBean.depositDate}</td>
					<td>${dBean.depositAmount}</td>
					<td>${dBean.virtualPoints}</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		</c:when>
		<c:otherwise>
			<h2>查無紀錄</h2>
			<h3><a href="index.v">前往儲值</a></h3>
			<h3><a href="${pageContext.request.contextPath}/">返回首頁</a></h3>
		</c:otherwise>
	</c:choose>
</body>
<script>
$(function() {
	// default
	var depositCondition = $('#depositCondition');
	depositCondition.hide();
	
	// action
	$('#condition').on('click', function() {
		depositCondition.slideToggle();
	});
	
	// date related
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
		$(':text[name*="depositDate"]').datepicker('option', 'maxDate', null)
		.datepicker('option', 'minDate', null).val('');
	});
});
</script>
</html>