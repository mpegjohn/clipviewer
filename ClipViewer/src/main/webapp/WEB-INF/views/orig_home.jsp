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

<script type='text/javascript'>
	<%int x=0;%>
<c:forEach var="carousel_item" items="${thumbs}">
	var mycarousel_array_<%=x%>= new Array();
	
	function init_mycarousel_array_<%=x%>() {
		<c:forEach var="item" items="${carousel_item}">
			<s:url value="${item['imagePath']}" var="thumb_uri" htmlEscape="true" />
			mycarousel_array_<%=x%>.push({url: "${thumb_uri}", timestamp: '${item["imageTime"]}'});
		</c:forEach>
		};
	<% x++; %>
</c:forEach>

</script>

</head>
<body>

	<div id="wrap">
		<div id="imageList">

			<ul id="carouselList">
				<%
					int i = 0;
				%>
				<c:forEach var="carousel_item" items="${thumbs}">
					<li>
						<ul id="mycarousel_<%=i++%>" class="jcarousel-skin-tango">

							<c:forEach var="item" items="${carousel_item}">
								<li>
									<table>
										<tbody>
											<tr>
												<td class="image_cell"><s:url
														value="${item['imagePath']}" var="thumb_uri"
														htmlEscape="true" /> <img src="${thumb_uri}" /></td>
											</tr>
											<tr>
												<td class="timestamp">${item["imageTime"]}</td>
											</tr>
										</tbody>
									</table>

								</li>
							</c:forEach>

						</ul>
					</li>
				</c:forEach>
			</ul>
		</div>

	</div>
</body>
</html>
