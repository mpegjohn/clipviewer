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

<!-- 
	jpaginator
 -->
<script type="text/javascript"
	src="<c:url value="/resources/jpaginator/jPaginator.js" />"></script>

<!--
  jpaginator stylesheet
-->
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/jpaginator/jPaginator.css" />" />

<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/jpaginator/test1.css" />" />

<!-- 
	jquery-ui slider
 -->
<script type="text/javascript"
	src="<c:url value="/resources/jquery-ui/jquery-ui-1.10.2.custom.js" />"></script>


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
	overflow: hidden;
	overflow-y: scroll;
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

    for(var i = 0; i < json.thumbnailList.length; i++)
    {
    	var image_file = json.thumbnailList[i].image;
    	var table = mycarousel_create_table(json.thumbnailList[i].path + "/" + image_file, json.thumbnailList[i].imageTime);
    	
    	carousel.add(i+first,table);
    }
};

/**
 * Item html creation helper.
 */
function mycarousel_create_table(url, time)
{
	var base_url = '<s:url value="/images/"/>';
	
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
		get_media(3, 1);
//		$("#imageList").append('<ul id="carouselList"></ul>');
//        $("#carouselList").append('<li> <div id="mycarousel_1" class="dynamiccarousel jcarousel-skin-tango"> <ul></ul></div></li>');

        //setup_carousels();
    });
    
    $("#test1").jPaginator({
    	  nbPages:1,
    	  selectedPage:1,
    	  nbVisible:6,
    	  overBtnLeft:'#test1_o_left',
    	  overBtnRight:'#test1_o_right',
    	  maxBtnLeft:'#test1_m_left',
    	  maxBtnRight:'#test1_m_right',
    	  onPageClicked: function(a,num) {
    	      $("#page1").html("demo1 - page : "+num);
    	      $("#carouselList").remove();
    	      $("#imageList").append('<ul id="carouselList"></ul>');
    	      get_media(3, num);
    	  }
    	});
    
});

// The total number of pages
var num_pages;

function get_media(id, page)
{
    jQuery.getJSON('<c:url value="/media/"/>' + id,
    		{page: page,
        size: 20
        },
        function(data) {
          //  $("#carouselList").append('<li> <div id="mycarousel_1" class="dynamiccarousel jcarousel-skin-tango"> <ul></ul></div></li>');
        	//console.log('Testing console');
            show_carousels(data);
            setup_carousels();
        }
      );	
}

function show_carousels(data)
{
	if(data.numPages != num_pages)
	{
		num_pages = data.numPages;
		$("#test1").trigger("reset",{  
	        //selectedPage:null, 
	        nbVisible:6, 
	        nbPages:num_pages//,
	        //marginPx:8, 
	        //speed:1 
	    }); 
	}
	for(var i =0; i< data.sceneIds.length; i++)
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
            	 itemFallbackDimension:100
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
 <p id="page1">demo1</p>
<div id="test1"> 

    <!-- optional left control buttons --> 
    <nav id="test1_m_left"></nav><nav id="test1_o_left"></nav> 

    <div class='paginator_p_wrap'> 
        <div class='paginator_p_bloc'> 
            <!--<a class='paginator_p'></a> // page number : dynamically added --> 
        </div> 
    </div> 

    <!-- optional right control buttons --> 
    <nav id="test1_o_right"></nav><nav id="test1_m_right"></nav> 


    <!-- slider --> 
    <div class='paginator_slider ui-slider ui-slider-horizontal ui-widget ui-widget-content ui-corner-all'> 
        <a class='ui-slider-handle ui-state-default ui-corner-all' href='#'></a> 
    </div> 

</div>

</body>
</html>
