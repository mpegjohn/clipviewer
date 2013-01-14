<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ page session="false"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-us">
<head>
<title>jCarousel Examples</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/jcarousel/style.css" />" ></link>
<!--
  jQuery library
-->
<script type="text/javascript"
	src="<c:url value="/resources/jcarousel/lib/jquery-1.4.2.min.js" />">
	
</script>
<!--
  jCarousel library
-->
<script type="text/javascript"
	src="<c:url value="/resources/jcarousel/lib/jquery.jcarousel.min.js" />"></script>
<!--
  jCarousel skin stylesheet
-->
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/jcarousel/skins/tango/skin.css" />">
</link>



<script type="text/javascript"
	src="<c:url value="/resources/javascript/image_list.js" />">
	
</script>



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
	//console.log('Testing console');
    // Check if the requested items already exist
    if (carousel.has(carousel.first, carousel.last)) {
        return;
    }

    jQuery.getJSON('http://localhost:8080/clipviewer/thumbnails',
    		{first: 1,
        last: 3},
        function(data) {
        	alert(data.total);
        	console.log('Testing console');
            mycarousel_itemAddCallback(carousel, carousel.first, carousel.last, data);
        }
      );
    
};

function mycarousel_itemAddCallback(carousel, first, last, json)
{
	console.log(json.imageList[0]);
    // Set the size of the carousel
    carousel.size(parseInt(json.total));

    for(var i = 0; i < json.imageList.length; i++)
    {
    	console.log(json.imageList[i].url);
    	var table = mycarousel_create_table(json.imageList[i].url, json.imageList[i].timestamp)
    	carousel.add(table);
    }
};

/**
 * Item html creation helper.
 */
function mycarousel_create_table(url, time)
{
	var img_url = '<img src="<s:url value="'+ url +'"/>" />';
	
	var table = "<table>";
	table += "<tr>";
	table += "<td>" + img_url + "</td>";
	table += "<td>" + time + "</td>";
	table += "</tr>";
	table += "</table>"
	
    return table;
};

jQuery(document).ready(function() {
    jQuery('#mycarousel').jcarousel({
        // Uncomment the following option if you want items
        // which are outside the visible range to be removed
        // from the DOM.
        // Useful for carousels with MANY items.

        // itemVisibleOutCallback: {onAfterAnimation: function(carousel, item, i, state, evt) { carousel.remove(i); }},
        itemLoadCallback: mycarousel_itemLoadCallback
    });
});

</script>

</head>
<body>

<div id="wrap">
  <h1>jCarousel</h1>
  <h2>Riding carousels with jQuery</h2>

  <h3>Carousel with dynamic content loading via Ajax</h3>
  <p>
    The data is loaded dynamically from a simple text file which contains the image urls.
  </p>

  <div id="mycarousel" class="jcarousel-skin-tango">
    <ul>
      <!-- The content will be dynamically loaded in here -->
    </ul>
  </div>

</div>

</body>
</html>
