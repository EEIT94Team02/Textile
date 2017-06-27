<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Product Maintenance</title>
<link rel="shortcut icon" type="image/png" sizes="32x32" href="<c:url value = '/image/icon/favicon-32x32.png'/>">
<link rel="shortcut icon" type="image/png" sizes="16x16" href="<c:url value = '/image/icon/favicon-16x16.png'/>">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<style>
.mainList, .mainList th, td {
	border: 3px solid lightblue;
	border-collapse: collapse;
}
input[type="button"], input[type="submit"] {
	display: block;
}
.imgUploadLabel {
	display: block;
	height: 3em;
	width: 5em;
	background-color: #E6E6FA;
}
.errorMessage {
	display: block;
	color: red;
}
</style>
</head>
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script>
$(function(){
	$('input[name="clearForm"]').on('click', function() {
		$(this).parents('tr').find('input[type="text"]').val('');
		$(this).parents('tr').find('input[type="hidden"]').val('');
	});
	
	$('.imgRemove').on('click', function() {
		$(this).parents('tr').find('input[name="img"]').remove();
		$(this).parents('tr').find('input[name*="img"]').val('');
		$(this).parents('tr').find('img').attr('src', '');
		$(this).parents('tr').find('img').css("display", "none");
	});
});
function setStatusIndex(pCount, index) {
	var status = document.getElementsByClassName("statusSelect")[pCount-1].selectedIndex = index;
}
function setCategoryIndex(pCount, index) {
	document.getElementsByClassName("categorySelect")[pCount-1].selectedIndex = index;
}
</script>
<body>
	<div id="header">
		<jsp:include page="/headerInclude.jsp"/>
	</div>
	<div id="center">
		<div id="body" >
			<c:if test="${not empty pMList}">
				<h3>${dataError.actionError}</h3>
				<table class="mainList">
					<thead>
						<tr>
							<th>productId</th>
							<th>productName</th>
							<th>unitPrice</th>
							<th>category</th>
							<th>intro</th>
							<th>status</th>
							<th>img</th>
							<th>rewardPoints</th>
							<th>maintain</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${sessionScope.pMList}" var="pBean" varStatus="pStatus">
							<c:url value="pShowImg.do" var="showImg" scope="page">
								<c:param name="productId" value="${pBean.productId}"/>
							</c:url>
							<c:if test="${pStatus.last}">
								<c:set var="insertCount" value="${pStatus.count+1}" scope="page"/>
							</c:if>
							<c:out value="<form method='POST' action='${pageContext.request.contextPath}/store/pMaintain.do'>" escapeXml="false" />
							<tr>
								<td><input type="hidden" name="productId" value="${pBean.productId}" /></td>
								<td>
									<input type="text" name="productName" value="${pBean.productName}" maxlength="20" />
									<span class="errorMessage">${errors[pBean.productId].pNa}</span>
								</td>
								<td>
									<input type="text" name="unitPrice" value="${pBean.unitPrice}" maxlength="4" />
									<span class="errorMessage">${errors[pBean.productId].pUP}</span>
								</td>
								<td>
									<select class="categorySelect" name="category">
										<option value="送禮用">送禮用</option>
										<option value="自用">自用</option>
									</select>
								</td>
								<c:choose>
									<c:when test='${pBean.category == "送禮用"}'>
										<c:out value="<script>setCategoryIndex(${pStatus.count}, 0)</script>" escapeXml="false" />
									</c:when>
									<c:otherwise>
										<c:out value="<script>setCategoryIndex(${pStatus.count}, 1)</script>" escapeXml="false" />
									</c:otherwise>
								</c:choose>
								<td>
									<input type="text" name="intro" value="${pBean.intro}" maxLength="20" />
									<span class="errorMessage">${errors[pBean.productId].pIn}</span>
								</td>
								<td>
									<select class="statusSelect" name="status">
										<option value="false">下架</option>
										<option value="true">上架</option>
									</select>
								</td>
								<c:choose>
									<c:when test="${pBean.status}">
										<c:out value="<script>setStatusIndex(${pStatus.count}, 1)</script>" escapeXml="false" />
									</c:when>
									<c:otherwise>
										<c:out value="<script>setStatusIndex(${pStatus.count}, 0)</script>" escapeXml="false" />
									</c:otherwise>
								</c:choose>
								<td>
									<input type="hidden" class="imgValue" name="img" value="${pBean.img}" alt="${pBean.productName}" />
									<input type="hidden" class="imgFileValue" name="imgFileContent" value="" />
									<input type="button" class="imgRemove" value="Remove" />
									<label class="imgUploadLabel" for="imgUploadButton${pStatus.count}">
										<span class="glyphicon glyphicon-folder-open" aria-hidden="true"></span>
										<input type="file" id="imgUploadButton${pStatus.count}" accept="image/*" class="imgUpload" onchange="setImg(${pStatus.count})" style="display:none" />
									</label>
									<img class="pBeanImg" src="${showImg}" height="200" />
								</td>
								<td>
									<input type="text" name="rewardPoints" value="${pBean.rewardPoints}" maxlength="4" />
									<span class="errorMessage">${errors[pBean.productId].pRP}</span>
								</td>
								<td>
									<input type="submit" class="btn btn-success" name="maintainAction" value="Update" />
									<input type="submit" class="btn btn-danger" name="maintainAction" value="Delete" />
								</td>
							</tr>
							<c:out value="</form>" escapeXml="false" />
						</c:forEach>
						<c:out value="<form method='POST' action='${pageContext.request.contextPath}/store/pMaintain.do'>" escapeXml="false" />
							<tr>
								<td><input type="hidden" name="insertCount" value="${insertCount}" /></td>
								<td>
									<input type="text" name="productName" value="${param.productName}" maxlength="20" placeholder="productName" />
									<span class="errorMessage">${errors[icInteger].pNa}</span>
								</td>
								<td>
									<input type="text" name="unitPrice" value="${param.unitPrice}" maxlength="4" placeholder="unitPrice" />
									<span class="errorMessage">${errors[icInteger].pUP}</span>
								</td>
								<td>
									<select class="categorySelect" name="category">
										<option value="送禮用">送禮用</option>
										<option value="自用">自用</option>
									</select>
								</td>
								<td>
									<input type="text" name="intro" value="${param.intro}"  maxlength="20" placeholder="intro" />
									<span class="errorMessage">${errors[icInteger].pIn}</span>
								</td>
								<td>
									<select name="status">
										<option value="false">下架</option>
										<option value="true" selected="true">上架</option>
									</select>
								</td>
								<td>
									<input type="hidden" class="imgValue" name="img" value="${param.img}" alt="${param.productName}" />
									<input type="hidden" class="imgFileValue" name="imgFileContent" value="" />
									<input type="button" class="imgRemove" value="Remove" />
									<label class="imgUploadLabel" for="imgUploadButton${insertCount}">
										<span class="glyphicon glyphicon-folder-open" aria-hidde="true"></span>
										<input type="file" id="imgUploadButton${insertCount}" accept="image/*" class="imgUpload" onchange="setImg(${insertCount})" style="display:none" />
									</label>
									<span class="errorMessage">${errors[icInteger].pImg}</span>
									
									<img class="pBeanImg" src="" height="200" />
								</td>
								<td>
									<input type="text" name="rewardPoints" value="${param.rewardPoints}"  maxlength="4" placeholder="rewardPoints" />
									<span class="errorMessage">${errors[icInteger].pRP}</span>
								</td>
								<td>
									<input type="submit" class="btn btn-success" name="maintainAction" value="Insert" />
									<input type="button" class="btn btn-danger" name="clearForm" value="Clear" />
								</td>
							</tr>
						<c:out value="</form>" escapeXml="false" />
					</tbody>
				</table>
			</c:if>
		</div>
		<div id="right">
			<jsp:include page="/rightInclude.jsp" />
		</div>
	</div>
	<div id="footer">
		<jsp:include page="/footerInclude.jsp" />
	</div>
</body>
<script>
function setImg(pCount) {
	var imgFile = document.getElementsByClassName("imgUpload")[pCount-1].files[0];
	var reader = new FileReader();
	reader.readAsDataURL(imgFile);
	reader.onload = function (e) {
		var fileContent = e.target.result;
		var theImg = document.getElementsByClassName("pBeanImg")[pCount-1];
		var theImgValue = document.getElementsByClassName("imgFileValue")[pCount-1];
		theImg.setAttribute("src", fileContent);
		theImgValue.setAttribute("value", fileContent);
	}
}
</script>
</html>