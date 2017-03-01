app.factory('routeUtil', function ($location, $timeout, $http) {
	var util = {};

	//重定向 需要参数解析 参数传递需注意顺序
	util.redirectTo=function(path, params, prefix){
		var urlParam = '';
		if(params){
			for (var key in params) {
				urlParam += '/' + params[key];
			}
		}
		util.redirect(path + urlParam, prefix);
	};
	//重定向 不需要参数解析
	util.redirect=function(path, prefix){
		if(prefix == 13){
			var bootP = bootPATH.substring(0, bootPATH.lastIndexOf("/"));
			return window.location = bootP.substring(0, bootP.lastIndexOf("/")) + path;
		}
		return window.location = bootPATH+path;
	};
	
	
	util.redirect2=function(path)
	{
		return window.location = window.location.protocol+"//"+  window.location.host + path;
	};
	
	util.fromJson=function(jsonStr){
		return angular.fromJson(jsonStr);
	};
	util.toJson=function(obj){
		return angular.toJson(obj);
	};
	util.getCookie =function (sName) {//获取cookie
	    var aCookie = document.cookie.split("; ");
	    var lastMatch = null;
	    for (var i = 0; i < aCookie.length; i++) {
	        var aCrumb = aCookie[i].split("=");
	        if (sName == aCrumb[0]) {
	            lastMatch = aCrumb;
	        }
	    }
	    if (lastMatch) {
	        var v = lastMatch[1];
	        if (v === undefined) return v;
	        return unescape(v);
	    }
	    return null;
	};
	
	util.postRequestReturnDetail = function(url,param,success,failed,prefix){//发送httppost请求
		//var loadIndex = layer.load(0, {shade: [0.1, '#7D7D7D']});
		var path = "";
		if(!prefix){
			path = bootPATH + url + '.do';
		}else if(prefix == 13){
			path = bootPATH + url;
		}else if(prefix == 17){
			var bootP = bootPATH.substring(0, bootPATH.lastIndexOf("/"));
			path = bootP.substring(0, bootP.lastIndexOf("/")) + url + '.do';
		}else if(prefix == 19){
			var bootP = bootPATH.substring(0, bootPATH.lastIndexOf("/"));
			path = bootP.substring(0, bootP.lastIndexOf("/")) + url;
		}else{
			path = bootPATH + url + prefix;
		}
		$http.post(path, angular.toJson(param)).success(function(response, status, headers, config,result) {
			if($.isFunction(success)){
				//layer.close(loadIndex);
				success(response, status, headers, config,result);
			}
		}).error(function(err) {
			//layer.close(loadIndex);
			if($.isFunction(failed)){
				failed(err);
			}
	    });
	};

	util.postRequest = function(url,param,success,failed,prefix){//发送httppost请求
		if(!prefix){
			prefix = '.do';
		}
		$http.post(bootPATH+url+prefix, angular.toJson(param)).success(function(result) {
			if($.isFunction(success)){
				success(result);
			}
		}).error(function(err) {
			if($.isFunction(failed)){
				failed(err);
			}
	    });
	};

	/*用于客服api请求*/
	util.getRequestApi = function(url,success,failed){//http请求
		$http.get(url).success(function(result){
			if($.isFunction(success)){
				success(result);
			}
		}).error(function(err) {
			if($.isFunction(failed)){
				failed(err);
			}
		})
	};

	util.postRequestApi = function(url,param,success,failed){
		$http.post(url,angular.toJson(param)).success(function(result){
			if($.isFunction(success)){
				success(result);
			}
		}).error(function(err){
			if($.isFunction(failed)){
				failed(err);
			}
		})
	};
	return util;
});


