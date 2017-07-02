<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品維護</title>
<link rel="shortcut icon" type="image/png" sizes="32x32" href="<c:url value = '/image/icon/favicon-32x32.png'/>">
<link rel="shortcut icon" type="image/png" sizes="16x16" href="<c:url value = '/image/icon/favicon-16x16.png'/>">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<style>
input[type="button"], input[type="submit"] {
	display: inline;
}
label {
	display: block;
}
.imgUploadLabel {
	display: block;
	height: 3em;
	width: 5em;
	background-color: #E6E6FA;
	text-align: center;
	line-height: 3em;
}
.errorMessage {
	display: block;
	color: red;
}
#body {
	display: flex;
	flex-wrap: wrap;
	justify-content: center;
}
.products {
	padding: 7px;
	margin: 5px;
	width: 30%;
	border: .5px solid #778899;
	box-shadow: 5px 5px 5px #778899;
}
</style>
</head>
<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script>
$(function(){
	$('input[name="clearForm"]').on('click', function() {
		$(this).parents('div').find('input[type="text"]').val('');
		$(this).parents('div').find('input[type="hidden"]').val('');
	});
	
	$('.imgRemove').on('click', function() {
		$(this).parents('div').find('input[name="img"]').val('');
		$(this).parents('div').find('input[name*="img"]').val('');
		$(this).parents('div').find('img').attr('src', '');
	});
});
function setStatusIndex(pCount, index) {
	document.getElementsByClassName("statusSelect")[pCount-1].selectedIndex = index;
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
					<c:forEach items="${sessionScope.pMList}" var="pBean" varStatus="pStatus">
						<c:url value="pShowImg.do" var="showImg" scope="page">
							<c:param name="productId" value="${pBean.productId}"/>
						</c:url>
						<c:if test="${pStatus.last}">
							<c:set var="insertCount" value="${pStatus.count+1}" scope="page"/>
						</c:if>
						<div class="products">
							<c:out value="<form method='POST' action='${pageContext.request.contextPath}/store/pMaintain.do'>" escapeXml="false" />
							<input type="hidden" class="imgValue" name="img" value="${pBean.img}" alt="${pBean.productName}" />
							<input type="hidden" class="imgFileValue" name="imgFileContent" value="" />
							<input type="button" class="imgRemove" value="Remove" />
							<label class="imgUploadLabel" for="imgUploadButton${pStatus.count}">
								<span class="glyphicon glyphicon-folder-open" aria-hidden="true"></span>
								<input type="file" id="imgUploadButton${pStatus.count}" accept="image/*" class="imgUpload" onchange="setImg(${pStatus.count})" style="display:none" />
							</label>
							<img class="pBeanImg" src="${showImg}" width="200" />
							<div class="container">
								<p>
									<label>物品名稱</label>
									<input type="text" name="productName" value="${pBean.productName}" maxlength="20" />
									<span class="errorMessage">${errors[pBean.productId].pNa}</span>										
								</p>
								<p>
									<label>商品單價</label>
									<input type="text" name="unitPrice" value="${pBean.unitPrice}" maxlength="4" />
									<span class="errorMessage">${errors[pBean.productId].pUP}</span>
								</p>
								<p>
									<label>種類</label>
									<select class="categorySelect" name="category">
										<option value="送禮用">送禮用</option>
										<option value="自用">自用</option>
									</select>
								</p>
								<c:choose>
									<c:when test='${pBean.category == "送禮用"}'>
										<c:out value="<script>setCategoryIndex(${pStatus.count}, 0)</script>" escapeXml="false" />
									</c:when>
									<c:otherwise>
										<c:out value="<script>setCategoryIndex(${pStatus.count}, 1)</script>" escapeXml="false" />
									</c:otherwise>
								</c:choose>
								<p>
									<label>商品簡介</label>
									<input type="text" name="intro" value="${pBean.intro}" maxLength="20" />
									<span class="errorMessage">${errors[pBean.productId].pIn}</span>
								</p>
								<p>
									<label>商品狀態</label>
									<select class="statusSelect" name="status">
										<option value="false">下架</option>
										<option value="true">上架</option>
									</select>
								</p>
								<c:choose>
									<c:when test="${pBean.status}">
										<c:out value="<script>setStatusIndex(${pStatus.count}, 1)</script>" escapeXml="false" />
									</c:when>
									<c:otherwise>
										<c:out value="<script>setStatusIndex(${pStatus.count}, 0)</script>" escapeXml="false" />
									</c:otherwise>
								</c:choose>
								<p>
									<label>商品積分</label>
									<input type="text" name="rewardPoints" value="${pBean.rewardPoints}" maxlength="4" />
									<span class="errorMessage">${errors[pBean.productId].pRP}</span>
								</p>
								<p>
									<input type="hidden" name="productId" value="${pBean.productId}" />
									<input type="submit" class="btn btn-success" name="maintainAction" value="修改" />
									<input type="submit" class="btn btn-danger" name="maintainAction" value="刪除" />
								</p>
							</div>
							<c:out value="</form>" escapeXml="false" />
						</div>
					</c:forEach>
					<div class="products">
						<c:out value="<form method='POST' action='${pageContext.request.contextPath}/store/pMaintain.do'>" escapeXml="false" />
						<input type="hidden" class="imgValue" name="img" value="${param.img}" alt="${param.productName}" />
						<input type="hidden" class="imgFileValue" name="imgFileContent" value="" />
						<input type="button" class="imgRemove" value="Remove" />
						<label class="imgUploadLabel" for="imgUploadButton${insertCount}">
							<span class="glyphicon glyphicon-folder-open" aria-hidden="true"></span>
							<input type="file" id="imgUploadButton${insertCount}" accept="image/*" class="imgUpload" onchange="setImg(${insertCount})" style="display:none" />
						</label>
						<span class="errorMessage">${errors[icInteger].pImg}</span>
						
						<img class="pBeanImg" src="" width="200" />
						<div class="container">
							<p>
								<label>物品名稱</label>
								<input type="text" name="productName" value="${param.productName}" maxlength="20" placeholder="productName" />
								<span class="errorMessage">${errors[icInteger].pNa}</span>										
							</p>
							<p>
								<label>商品單價</label>
								<input type="text" name="unitPrice" value="${param.unitPrice}" maxlength="4" placeholder="unitPrice" />
								<span class="errorMessage">${errors[icInteger].pUP}</span>
							</p>
							<p>
								<label>種類</label>
								<select class="categorySelect" name="category">
									<option value="送禮用">送禮用</option>
									<option value="自用">自用</option>
								</select>
							</p>
							<p>
								<label>商品簡介</label>
								<input type="text" name="intro" value="${param.intro}"  maxlength="20" placeholder="intro" />
								<span class="errorMessage">${errors[icInteger].pIn}</span>
							</p>
							<p>
								<label>商品狀態</label>
								<select name="status">
									<option value="false">下架</option>
									<option value="true" selected="true">上架</option>
								</select>
							</p>
							<p>
								<label>商品積分</label>
								<input type="text" name="rewardPoints" value="${param.rewardPoints}"  maxlength="4" placeholder="rewardPoints" />
								<span class="errorMessage">${errors[icInteger].pRP}</span>
							</p>
							<p>
								<input type="submit" class="btn btn-success" name="maintainAction" value="新增" />
								<input type="button" class="btn btn-danger" name="clearForm" value="重填" />
							</p>
						</div>
						<c:out value="</form>" escapeXml="false" />
					</div>
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