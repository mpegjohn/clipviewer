	jQuery(document).ready(
			function() {

				var carousels = document.getElementsByClassName('jcarousel-skin-tango');

				for ( var i = 0; i < carousels.length; i++) {
					var id = carousels[i].id;
					jQuery("#"+id).jcarousel({
					});
				}

			});
	
	function imageItem(url,timestamp)
	{
		this.url = url;
		this.timestamp = timestamp;
	}
