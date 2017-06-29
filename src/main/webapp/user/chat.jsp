<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/style.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/jacky.css'/>">
<title>Chat, Textile</title>
<style type="text/css">
.rightDiv {
	background: #FFFF77;
	border: 0.5px solid black;
	border-radius: 10px;
	margin-top: 1vh;
	right: 0px;
	margin-bottom: 1vh;
	margin-right: 0; width : 30%;
	text-align: right;
	padding: 10px;
	width: 30%;
}

.centerDiv {
	display: block;
	height: 70vh;
	background-image: url("../image/background/9.jpg");
	background-size: cover;
	position: absolute;
	top: 90px;
	left: 21%;
	bottom: 40px;
	height: 790px;
	background: #FFD1A4;
	width: 63%;
	opacity: 0.8;
	font-weight: bold;
}

.leftDiv {
	left: 0px;
	background: #C9C9C9;
	border: 0.5px solid black;
	border-radius: 10px;
	margin-top: 1vh;
	margin-bottom: 1vh;
	width: 30%;
	text-align: left;
	padding: 10px;
}

#input {
	width: 90%;
}

#button {
	
}

#beneathTop {
	height: 10vh;
	display: block;
	padding-left: 10px;
}

#response {
	height: 60vh;
	display: block;
	overflow-x: hidden;
	overflow-y: scroll;
	padding: 10px;
}

#onButtom {
	height: 15vh;
	display: block;
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

	<div id="left">
		<div class="actions">
			<ul>
				<li class="list"><a href="modifySecure.v">修改密碼</a></li>
				<li class="list"><a href="modifyProfile.v">修改基本資料</a></li>
				<li class="list"><a href="modifySituation.v">修改個人狀況</a></li>
				<li class="list"><a href="modifyInterest.v">修改興趣喜好</a></li>
				<li class="list"><a href="queryName.v">會員姓名查詢</a></li>
				<li class="list"><a href="queryCondition.v">會員條件查詢</a></li>
				<li class="list"><a href="queryRandom.do">會員隨機查詢</a></li>
			</ul>
		</div>
	</div>
	<div id="right">
		<jsp:include page="/rightInclude.jsp" />
	</div>
	<div class="centerDiv">
		<div id="beneathTop">
			<h3>${chat.acquaintenceName}</h3>
			<hr />
		</div>
		<div id="response">
			<c:if test="${not empty chat.messageLogs}">
				<c:forEach var="x" items="${chat.messageLogs}">
					<c:choose>
						<c:when test="${x.chatroom_LogPK.mId == chat.mId}">
							<div class="rightDiv">${x.cContent}</div>
						</c:when>
						<c:otherwise>
							<div class="leftDiv">${x.cContent}</div>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</c:if>
		</div>
		<div id="onButtom">
			<p>
				<textarea id="input" rows="5" placeholder="請在此輸入訊息..."></textarea>
				<input id="button" class="btn" type="button" name="button" value="送出" />
			</p>
		</div>
	</div>
	<div id="footer">
		<jsp:include page="/footerInclude.jsp" />
	</div>
	<script type="text/javascript">
    var stompClient;
    var q = '${chat.encryptedMId}';

    function chatViewScrollToButtom() {
      $('#response').scrollTop($('#response').prop('scrollHeight'));
    }

    function doRightAppend(message) {
      var divJOb = $('<div></div>').text(message);
      divJOb.attr('class', 'rightDiv');
      $('#response').append(divJOb);
      chatViewScrollToButtom();
    }

    $(document).ready(function() {
      var websocket = new WebSocket('${chat.websocketURI}');
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
        var unrestoredQ = message.headers['q'];
        var returnQ = unrestoredQ.substring(1, unrestoredQ.length - 1);
        if (q == returnQ) {
          doRightAppend(message.body);
        } else {
          doLeftAppend(message.body);
        }
      };

      stompClient.subscribe('${chat.subscribeURI}', onmessage);

      // var ondisconnect = function() {
      // console.log('關閉連線。');
      // };
      // stompClient.disconnect(ondisconnect);
    }

    // 偵測Enter
    function doEnterSend(event) {
      event.preventDefault();
      if (event.keyCode == 13) {
        doSend();
      }
    }

    $('#input').on('keydown', doEnterSend);

    $(document).ready(function() {
      chatViewScrollToButtom();
    });
  </script>
</body>
</html>