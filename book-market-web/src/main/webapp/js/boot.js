var head = document.getElementsByTagName("head")[0];
var bootPATH = "";
(function() {
	var scripts = head.getElementsByTagName("script");
	//alert('test'+script.toString);
	for (var i = 0; i < scripts.length; i++) {
		var script = scripts[i];
		var regex = /boot\.js(\?v=.*)?/ig;
		result = regex.exec(script.src);
		if (result) {
			bootPATH = script.src.substring(0,script.src.lastIndexOf("js/boot.js"));
			if (result[1]) {
				version = result[1];
			}
			break;
		}
	}
})();