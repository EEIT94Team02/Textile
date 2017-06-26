<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Gift</title>
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
	<input type="button" class="btn btn-info btn-lg" id="sending" value="送禮" />
	<form id="giftCondition" method="POST" action="gCondition.do" >
		<fieldset class="radioSet">
			<legend>Select a type:</legend>
			<label for="allRecord">所有紀錄</label>
			<input type="radio" id="allRecord" rname="giverId" name="recordType" />
			<label for="giveRecord">送禮紀錄</label>
			<input type="radio" id="giveRecord" rname="giverId" name="recordType" />
			<label for="receiveRecord">收禮紀錄</label>
			<input type="radio" id="receiveRecord" rname="recipientId" name="recordType" />
			<input type="hidden" id="postId" name="giverId" value="${sessionScope.user.mId}" />
		</fieldset>
		<fieldset class="nameSet">
			<legend>Input a name if need be:</legend>
			<label for="giverName">送禮人姓名</label>
			<input type="text" id="giverName" maxlength="20" name="giverName" />
			<label for="recipientName">收禮人姓名</label>
			<input type="text" id="recipientName" maxlength="20" name="recipientName" />
		</fieldset>
		<fieldset class="dateSet">
			<legend>Select date if need be:</legend>
			<label for="afterDate">此日期之後</label>
			<input type="text" id="afterDate" name="giveDateAfter" autocomplete="off" />
			<label for="beforeDate">此日期之前</label>
			<input type="text" id="beforeDate" name="giveDateBefore" autocomplete="off" />
		</fieldset>
		<input type="submit" value="條件查詢" />
		<input type="reset" value="清除條件" />
	</form>
	<hr/>
	<c:choose>
		<c:when test="${not empty gList}">
			<table>
			<thead>
				<tr>
					<td>giftId</td>
					<td>giverName</td>
					<td>recipientName</td>
					<td>giveDate</td>
					<td></td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${gList}" var="gBean" varStatus="gStatus">
					<tr>
						<td>${gBean.giftId}</td>
						<td>${gBean.giverBean.mName}</td>
						<td>${gBean.recipientBean.mName}</td>
						<td>${gBean.giveDate}</td>
						<td>
							<input type="button" name="submit" value="送禮明細" />
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<form id="giftDetailForm" action="giftDetail.do" method="POST">
			<input type="hidden" name="giftId" value="" />
		</form>
		</c:when>
		<c:otherwise>
			<h3>查無紀錄</h3>
		</c:otherwise>
	</c:choose>
</body>
<script>
$(function() {
	// default
	$('#giftCondition').hide();
	$(':submit').prop('disabled', true);
	
	// action button
	$('#condition').on('click', function() {
		$('#giftCondition').slideToggle();
	});
	$('#sending').on('click', function() {
		location.replace('headToGiftSending.do');
	});
	
	// name related
	$('.nameSet').hide();
	
	// id related
	$(':radio').checkboxradio({
		icon:false
	}).on('click', function() {
		$('#postId').attr('name' ,$(this).attr('rname'));
		if ($(':submit').prop('disabled')) {
			$(':submit').prop('disabled', false);
		}
	}).on('click.gr', function() {
		$('.nameSet').slideDown();		
	});
	$('#giveRecord').on('click.disableGN', function() {
		resetName();
		$('#giverName').prop('disabled', true);	
	});
	$('#receiveRecord').on('click.disableRN', function() {
		resetName();
		$('#recipientName').prop('disabled', true);
	});
	$('#allRecord').on('click.all', function() {
		$('.nameSet').slideUp();
		resetDate();
		$('input[id*="Name"]').prop('disabled', true);
	}).off('click.gr');
	function resetName() {
		$('input[id*="Name"]').prop('disabled', false).val('');
	};
	
	// date related
	$('input[id="afterDate"]').datepicker({
		dateFormat:"yy-mm-dd",
		contrainInput:false,
		showAnim:"slideDown",
		changeMonth:true,
		changeYear:true,
		onSelect: function() {
			$('input[id="beforeDate"]').datepicker('option', 'minDate', $(this).val())
		}
	});
	$('input[id="beforeDate"]').datepicker({
		dateFormat:"yy-mm-dd",
		contrainInput:false,
		showAnim:"slideDown",
		changeMonth:true,
		changeYear:true,
		onSelect: function() {
			$('input[id="afterDate"]').datepicker('option', 'maxDate', $(this).val())
		}
	});
	function resetDate() {
		$('input[id*="Date"]').datepicker('option', 'maxDate', null)
		.datepicker('option', "minDate", null)
		.val('');
	};
	
	// reset reinforce
	$('input[type="reset"]').on('click', function() {
		$('.nameSet').slideUp();
		$('#postId').attr('name', '');
		$(':submit').prop('disabled', true);
		resetDate();
		resetName();
	});
	
	// gift detail query
	$(':button[name="submit"]').on('click', function() {
		var giftId = $(this).parents('tr').find('td:eq(0)').text();
		$(':hidden[name="giftId"]').val(giftId);
		$('#giftDetailForm').submit();
	});
});
</script>
</html>