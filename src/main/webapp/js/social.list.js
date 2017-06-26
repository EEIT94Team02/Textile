function getInviteId(obj) {
	answer = confirm("確定要加好友嗎？");
	if (answer)
		location.href = obj;
};
function refuse(obj) {
	answer = confirm("確定要拒絕嗎？");
	if (answer)
		location.href = obj;
};