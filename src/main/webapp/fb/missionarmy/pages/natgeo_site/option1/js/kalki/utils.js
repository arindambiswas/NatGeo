var kalki = function()
{

	// Public API
	return {
		mixinQueryParams : function(flashvars)
		{
	   		var queryVars = {};
	   		var queryParams = document.location.search || document.location.hash;
    		queryParams = queryParams.substr(1);
    		paramsArray = queryParams.split("&");
    		for(var i=0; i<paramsArray.length; i++)
    		{
    			var keyValuePair = paramsArray[i].split("=");
    			queryVars[keyValuePair[0]] = keyValuePair[1];
    		}
   			
   			// overriding flashvars from quueryParams
   			for (var i in queryVars)
   			{
   				flashvars[i] = queryVars[i];
   			}
			
			return flashvars;
		}
	}	
}();