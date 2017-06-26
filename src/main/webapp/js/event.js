

function doMyAlbum() {
	request = new XMLHttpRequest();
	request.onreadystatechange = callBack;
	var data = "http:\\localhost:8080\Textile\photo\album\list.do"
	request.open("GET", data);
	request.send(null);
}
