<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ page session="false"%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en-us">
<head>

<title>jCarousel Examples</title>

<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/jcarousel/style.css" />" />

<!--
  jQuery library
-->
<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.0.min.js"></script>

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

/**
 * Overwrite for having a carousel with dynamic width.
 */
.jcarousel-skin-tango .jcarousel-container-horizontal {
    width: 85%;
}

.jcarousel-skin-tango .jcarousel-clip-horizontal {
    width: 100%;
}

#display {
    clear: both;
    width: auto;
    height: 250px;
    overflow: scroll;
    border: 1px solid #666;
    background-color: #fcfcfc;
    padding: 10px;
}
</style>

<script type="text/javascript">

/**
 * This is the callback function which receives notification
 * about the state of the next button.
 */
function mycarousel_buttonNextCallback(carousel, button, enabled) {
    display('Next button is now ' + (enabled ? 'enabled' : 'disabled'));
};

/**
 * This is the callback function which receives notification
 * about the state of the prev button.
 */
function mycarousel_buttonPrevCallback(carousel, button, enabled) {
    display('Prev button is now ' + (enabled ? 'enabled' : 'disabled'));
};

/**
 * This is the callback function which receives notification
 * right after initialisation of the carousel
 */
function mycarousel_initCallback(carousel, state) {
    if (state == 'init')
        display('Carousel initialised');
    else if (state == 'reset')
        display('Carousel reseted');
};

/**
 * This is the callback function which receives notification
 * right after reloading of the carousel
 */
function mycarousel_reloadCallback(carousel) {
    display('Carousel reloaded');
};

/**
 * This is the callback function which receives notification
 * when an item becomes the first one in the visible range.
 */
function mycarousel_itemFirstInCallback(carousel, item, idx, state) {
    display('Item #' + idx + ' is now the first item');
};

/**
 * This is the callback function which receives notification
 * when an item is no longer the first one in the visible range.
 */
function mycarousel_itemFirstOutCallback(carousel, item, idx, state) {
    display('Item #' + idx + ' is no longer the first item');
};

/**
 * This is the callback function which receives notification
 * when an item becomes the first one in the visible range.
 */
function mycarousel_itemLastInCallback(carousel, item, idx, state) {
    display('Item #' + idx + ' is now the last item');
};

/**
 * This is the callback function which receives notification
 * when an item is no longer the first one in the visible range.
 */
function mycarousel_itemLastOutCallback(carousel, item, idx, state) {
    display('Item #' + idx + ' is no longer the last item');
};

/**
 * This is the callback function which receives notification
 * when an item becomes the first one in the visible range.
 * Triggered before animation.
 */
function mycarousel_itemVisibleInCallbackBeforeAnimation(carousel, item, idx, state) {
    // No animation on first load of the carousel
    if (state == 'init')
        return;

    jQuery('img', item).fadeIn('slow');
};

/**
 * This is the callback function which receives notification
 * when an item becomes the first one in the visible range.
 * Triggered after animation.
 */
function mycarousel_itemVisibleInCallbackAfterAnimation(carousel, item, idx, state) {
    display('Item #' + idx + ' is now visible');
};

/**
 * This is the callback function which receives notification
 * when an item is no longer the first one in the visible range.
 * Triggered before animation.
 */
function mycarousel_itemVisibleOutCallbackBeforeAnimation(carousel, item, idx, state) {
    jQuery('img', item).fadeOut('slow');
};

/**
 * This is the callback function which receives notification
 * when an item is no longer the first one in the visible range.
 * Triggered after animation.
 */
function mycarousel_itemVisibleOutCallbackAfterAnimation(carousel, item, idx, state) {
    display('Item #' + idx + ' is no longer visible');
};

/**
 * Helper function for printing out debug messages.
 * Not needed for jCarousel.
 */
var row = 1;
function display(s) {
    // Log to Firebug (getfirebug.com) if available
    //if (window.console != undefined && typeof window.console.log == 'function')
      //  console.log(s);

    if (row >= 1000)
        var r = row;
    else if (row >= 100)
        var r = '&nbsp;' + row;
    else if (row >= 10)
        var r = '&nbsp;&nbsp;' + row;
    else
        var r = '&nbsp;&nbsp;&nbsp;' + row;

    jQuery('#display').html(jQuery('#display').html() + r + ': ' + s + '<br />').get(0).scrollTop += 10000;

    row++;
};

jQuery(document).ready(function() {
    jQuery('#mycarousel').jcarousel({
        scroll: 1,

        initCallback:   mycarousel_initCallback,
        reloadCallback: mycarousel_reloadCallback,

        buttonNextCallback:   mycarousel_buttonNextCallback,
        buttonPrevCallback:   mycarousel_buttonPrevCallback,

        itemFirstInCallback:  mycarousel_itemFirstInCallback,
        itemFirstOutCallback: mycarousel_itemFirstOutCallback,
        itemLastInCallback:   mycarousel_itemLastInCallback,
        itemLastOutCallback:  mycarousel_itemLastOutCallback,
        itemVisibleInCallback: {
            onBeforeAnimation: mycarousel_itemVisibleInCallbackBeforeAnimation,
            onAfterAnimation:  mycarousel_itemVisibleInCallbackAfterAnimation
        },
        itemVisibleOutCallback: {
            onBeforeAnimation: mycarousel_itemVisibleOutCallbackBeforeAnimation,
            onAfterAnimation:  mycarousel_itemVisibleOutCallbackAfterAnimation
        }
    });
});

</script>

</head>
<body>
<div id="wrap">
  <h1>jCarousel</h1>
  <h2>Riding carousels with jQuery</h2>

  <h3>Carousel illustrating the callback functions</h3>
  <p>
    This carousel has registered all available callback functions and displays
    information about the state of the items and buttons. Additionally the width
    of the carousel is set to auto. Resize the browser window and see what happens.
  </p>


  <ul id="mycarousel" class="jcarousel-skin-tango">
    <li><img src="http://static.flickr.com/66/199481236_dc98b5abb3_s.jpg" width="75" height="75" alt="" /></li>
    <li><img src="http://static.flickr.com/75/199481072_b4a0d09597_s.jpg" width="75" height="75" alt="" /></li>
    <li><img src="http://static.flickr.com/57/199481087_33ae73a8de_s.jpg" width="75" height="75" alt="" /></li>
    <li><img src="http://static.flickr.com/77/199481108_4359e6b971_s.jpg" width="75" height="75" alt="" /></li>
    <li><img src="http://static.flickr.com/58/199481143_3c148d9dd3_s.jpg" width="75" height="75" alt="" /></li>
    <li><img src="http://static.flickr.com/72/199481203_ad4cdcf109_s.jpg" width="75" height="75" alt="" /></li>
    <li><img src="http://static.flickr.com/58/199481218_264ce20da0_s.jpg" width="75" height="75" alt="" /></li>
    <li><img src="http://static.flickr.com/69/199481255_fdfe885f87_s.jpg" width="75" height="75" alt="" /></li>
    <li><img src="http://static.flickr.com/60/199480111_87d4cb3e38_s.jpg" width="75" height="75" alt="" /></li>
    <li><img src="http://static.flickr.com/70/229228324_08223b70fa_s.jpg" width="75" height="75" alt="" /></li>
  </ul>

  <p id="display"></p>

</div>
</body>
</html>