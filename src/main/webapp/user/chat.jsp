<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Chat, Textile</title>
<style type="text/css">
#input {
	width: 300px;
}
</style>
<link rel="shortcut icon" type="image/png" sizes="32x32" href="<c:url value = '/image/icon/favicon-32x32.png'/>">
<link rel="shortcut icon" type="image/png" sizes="16x16" href="<c:url value = '/image/icon/favicon-16x16.png'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/style.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/jacky.css'/>">
<script type="text/javascript" src="<c:url value = '../js/jquery-3.2.1.js'/>"></script>
<script type="text/javascript" src="<c:url value = '../js/stomp.js'/>"></script>
</head>
<body>
	<div id="header">
		<jsp:include page="/headerInclude.jsp" />
	</div>
	<div id=body>
		<h3>${chat.acquaintenceName}</h3>
		<hr />
		<div id="response"></div>
		<div>
			<p>
				<textarea id="input" rows="5" placeholder="請在此輸入訊息..."></textarea>
			</p>
			<input id="button" type="button" name="button" value="送出" />
		</div>
	</div>
	${chat.websocketURI}
	<script type="text/javascript">
		var stompClient;

		function writeToScreen(message) {
			var divJOb = $('<div></div>').text(message);
			$('#response').append(divJOb);
		}

		$(document).ready(
				function() {
					var websocket = new WebSocket(
							'ws://localhost:8080/Textile/endpoint.do');
					stompClient = Stomp.over(websocket);

					var onconnect = function(frame) {
						console.log('開啟連線：' + frame);
						init();
					};

					var onerror = function(error) {
						console.log('發生錯誤：' + error);
					};

					stompClient.connect({}, onconnect, onerror);
				});

		function init() {
			var onmessage = function(message) {
				writeToScreen(message.body);
			};

			stompClient.subscribe('/passage/out', onmessage);

			// var ondisconnect = function() {
			// console.log('關閉連線。');
			// };
			// stompClient.disconnect(ondisconnect);
		}

		function doSend() {
			var header = {
				'cId' : '${chat.cId}'
			};
			var body = $('#input').val();
			stompClient.send('/message/in', header, body);
			$('#input').text('');
		}

		$('#button').on('click', doSend);
	</script>
</body>
</html>