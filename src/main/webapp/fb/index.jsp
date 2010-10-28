<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%
 
 	//DefaultFacebookClient pubFBClient = new DefaultFacebookClient();
 
 	//User user = pubFBClient.fetchObject("me", User.class);
 %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
ß<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- 
	<script type="text/javascript" 
		src="https://getfirebug.com/firebug-lite.js#startOpened=false">
	</script>
-->	
	<script type="text/javascript" 
		src="https://getfirebug.com/firebug-lite.js#startOpened=true">
	</script>
	<script type="text/javascript" src="http://www.google.com/jsapi?key=ABQIAAAASqHliaerVOB6H9GcFfesDRSOzFTfi6iGE_ValiQ-0eOsq69wDxR7J0A5VMSH7jSC__y21vCS4qy5ZQ"></script>
	<script type="text/javascript" src="lib/js/cz_fbtestbed/fbtestbed.js"></script>
	
	 <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.5/themes/redmond/jquery-ui.css" rel="stylesheet" type="text/css"/>
  	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
  	<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
  
	
<title>Facebook Testbed App</title>
</head>
<body>
	<div id="fb-root"></div>
<br />

<div id="tabs">
    <ul>
        <li><a href="#fragment-1"><span>New User</span></a></li>
        <li><a href="#fragment-2"><span>Two</span></a></li>
        <li><a href="#fragment-3"><span>Three</span></a></li>
    </ul>
    <div id="fragment-1">
		<fb:login-button width="200" max-rows="1">Install Example App</fb:login-button>
    </div>
    <div id="fragment-2">
        <fb:live-stream event_app_id="255955255198" width="400" height="500" via_url="" always_post_to_friends="false"></fb:live-stream>
    </div>
    <div id="fragment-3">
        Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.
        Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.
        Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.
    </div>
</div>		

	<script>
	  	function onGLibLoad()
	  	{
			console.log("Google Libraries Loaded!!!!!!!!!");
			jQuery.noConflict();
			
			window.fbAsyncInit = function() {
		    	FB.init({appId: '155442984483491', status: true, cookie: true,
		             xfbml: true});
//				FB.Canvas.setAutoResize();
				console.log("FB.init() called!");
		  	};
		  	(function() {
		    	var e = document.createElement('script'); 
		    	e.async = true;
		    	e.src = document.location.protocol +
		      		'//connect.facebook.net/en_US/all.js';
		    	document.getElementById('fb-root').appendChild(e);
		  	}());
	  	}
			  	  		
	 // google.load("jquery", "1.4.2");
	  google.load("swfobject", "2.2");
	  google.setOnLoadCallback(onGLibLoad);

	  $(document).ready(function() {
		    $("#tabs").tabs();
		  });
	  
	  $('#tabs').bind('tabsshow', function(event, ui) {

			console.log("Tab Selected!", ui);
		    // Objects available in the function context:
		    //ui.tab     // anchor element of the selected (clicked) tab
		    //ui.panel   // element, that contains the selected/clicked tab contents
		    //ui.index   // zero-based index of the selected (clicked) tab

		});
			  
	</script>
</body>
</html>