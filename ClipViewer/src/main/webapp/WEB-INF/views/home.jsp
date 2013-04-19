<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ page session="false"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-us">
<head>
<title>jCarousel Examples</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/jcarousel/style.css" />" />
<!--
  jQuery library
-->
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.8.0.min.js">
	
</script>
<!--
  jCarousel library
-->
<script type="text/javascript"
	src="<c:url value="/resources/jcarousel/lib/jquery.jcarousel.js" />"></script>
<!--
  jCarousel skin stylesheet
-->
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/jcarousel/skins/tango/skin.css" />" />


<style type="text/css">
td,tr,img {
	padding: 0px;
	margin: 0px;
	border: none;
}

img {
	width: 96px;
	height: 72px;
}

table {
	border: 1px solid #000;
	border-spacing: 0px;
	border-collapse: collapse;
}

*.timestamp {
	text-align: center;
	font-size: 11px;
	background-color: #FFFFF2;
	color: #A3A3A3;
	vertical-align: middle;
	height: 12px;
}

*.image_cell {
	height: 60px;
}

#imageList {
	width: 800px;
	height: 600px;
	background-color: #E1E1E1;
}

#carouselList {
	list-style-type: none;
	padding: 0;
	margin: 0;
}
</style>

<script type="text/javascript">

function mycarousel_itemLoadCallback(carousel, state)
{
	console.log(carousel);
    // Check if the requested items already exist

    if (carousel.has(carousel.first, carousel.last)) {
        return;
    }

    var first = carousel.first;
    
    for(var i = carousel.first; i <= carousel.last; i++)
    {
    	if(!carousel.has(i))
    	{
    		first = i;
    		break;	
    	}
    }
    
    var last = carousel.last;
    jQuery.getJSON('<c:url value="/thumbnails"/>',
    		{first: first,
        last: last,
        id: carousel.carouselid.substring(11) // stripping out 'carousel' to get integer
        },
        function(data) {
        	//console.log('Testing console');
            mycarousel_itemAddCallback(carousel, first, data);
        }
      );    
};

function mycarousel_itemAddCallback(carousel, first, json)
{
    // Set the size of the carousel

    	carousel.size(parseInt(json.total));

    for(var i = 0; i < json.imageList.length; i++)
    {
    	//console.log(json.imageList[i].url);
    	var table = mycarousel_create_table(json.imageList[i].url, json.imageList[i].timeStamp);
    	
    	carousel.add(i+first,table);
    }
};

/**
 * Item html creation helper.
 */
function mycarousel_create_table(url, time)
{
	var base_url = '<s:url value="/images"/>';
	
	var img_url = '<img src="' + base_url + url + '" />';
	
	//console.log(img_url);
	
	var table = "<table>";
	table += "<tbody>";
	table += "<tr>";
	table += '<td id="image_cell">' + img_url + '</td>';
	table += "</tr>";
	table += "<tr>";
	table += '<td id="timestamp">' + time + '</td>';
	table += "</tr>";
	table += "</tbody>";
	table += "</table>";

    return table;
};

// From http://jquery.10927.n7.nabble.com/multiple-dynamic-jcarousel-instances-td114488.html


$(document).ready(function(){ // MAKE CAROUSELS 
    
	setup_carousels();
	
    $("#clear_button").click(function(){
        $("#carouselList").remove();
    });
    
    $("#add_button").click(function(){
        $("#imageList").append('<ul id="carouselList"></ul>');
		get_media(1);
        setup_carousels();
    });
});

function get_media(id)
{
    jQuery.getJSON('<c:url value="/media/"/>' + id,
    		{page: 1,
        size: 20
        },
        function(data) {
        	//console.log('Testing console');
            show_carousels(data);
        }
      );	
}

function show_carousels(data)
{
	for(var i =0; i< data.sceneIds.length(); i++)
	{
		var sceneId = data.sceneIds[i];
    	$("#carouselList").append('<li> <div id="mycarousel_' + sceneId + '" class="dynamiccarousel jcarousel-skin-tango"> <ul></ul></div></li>');
	}
}

function setup_carousels()
{
	$('.dynamiccarousel').each(function(){ 
    	$(this).jcarousel({ 
        	    itemLoadCallback: mycarousel_itemLoadCallback, 
            	initCallback: initiate_carousel, 
            	carouselid: this.id, //important!
    	}); 
	});
};

function initiate_carousel(carousel,state){ // ON EACH INITIATION, ASSIGN AN ID TO THE CAROUSEL INSTANCE 

	if(state == 'init')
	{
		carousel.carouselid = this.carouselid;
	}
};



</script>

</head>
<body>

<div id="wrap">
	<div id="imageList">
		<ul id="carouselList">
			<c:forEach var="carousel_item" items="${scene_id}">
				<li>
					<div id="mycarousel_${carousel_item}" class="dynamiccarousel jcarousel-skin-tango">
    						<ul>
      						<!-- The content will be dynamically loaded in here -->
						</ul>
					</div>
				</li>
			</c:forEach>
		</ul>
	</div>
</div>
<p>
<input type="button" id="clear_button" value="Clear"/><input type="button" id="add_button" value="Next"/>
</p>
</body>
</html>
