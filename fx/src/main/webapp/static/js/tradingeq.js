HeaderMenu = (function(){
	var menu = function(){
		_init_menu();
		currentMenu = $(".header-menu.first.selected");
	};
	var closeTimer = null;
	var currentDropdown = null;
	var currentMenu = null;
	function _init_menu(){
		$(".header-container .bottom-container").delegate(".header-menu", "mouseenter", function(){
			var menuItem = $(this);
			_cancelClose();
			_close();
			menuItem.toggleClass("selected");
			currentMenu = menuItem; 
			var dropdown = $("." + menuItem.data("dropdown"));
			dropdown.show();
			var top = menuItem.offset().top + menuItem.height();
			var left = menuItem.offset().left;
			dropdown.offset({top: top, left: left});
			currentDropdown = dropdown;
		});
		$(".header-container .bottom-container").delegate(".header-menu", "mouseleave", function(){
			_delayedClose();
		});
		$(".header").delegate(".header-menu-dropdown", "mouseenter", function(){
			_cancelClose();
		});
		$(".header").delegate(".header-menu-dropdown", "mouseleave", function(){
			_delayedClose();
		});
		
	};
	function _close(){
		if(currentMenu){
			currentMenu.toggleClass("selected");
		}
		if(currentDropdown){
			currentDropdown.hide();
		}
		currentMenu = null;
		currentDropdown = null;
	}
	function _delayedClose(){
		closeTimer = window.setTimeout(_close, 500);
	}
	function _cancelClose(){
		if(closeTimer){
			window.clearTimeout(closeTimer);
			closeTimer = null;
		}
	}
	return menu;
})();
Gallery = (function(){
	var gallery = function(){
		_init_gallery();
		currentMenu = $(".menu-title.first.selected");
		currentSubmenu = $("gallery-news .submenu.first.selected");
	};
	var currentMenu = null;
	var currentSubmenu = null;
	function _init_gallery(){
		$(".menu").delegate(".menu-title", "mouseenter", function(){
			var menu = $(this);
			if(currentMenu != null && currentMenu.data("submenu") != menu.data("submenu")){
				menu.toggleClass("selected");
				currentMenu.toggleClass("selected");
				currentMenu = menu;
				var targetID = menu.data("submenu");
				var target = $("." + targetID);
				var index = target.data("index");
				$(".gallery-container").stop(true, true).animate({"top":0, "left": 0 - index * 1000}, 500, function(){});
				var targetSubmenu = $("." + targetID + " .submenu.selected");
				currentSubmenu = targetSubmenu;
			}
		});
		$(".menu-gallery").delegate(".submenu", "mouseenter", function(){
			var submenu = $(this);
			var innerContainer = $("." + submenu.data("menu") + "-container");
			var imageID = submenu.data("submenu-image");
			var image = $("#" + imageID);
			var index = image.data("index");
			currentSubmenu.toggleClass("selected");
			if(!submenu.hasClass("selected")){
				submenu.toggleClass("selected");
			}
			currentSubmenu = submenu;
			innerContainer.stop(true, true).animate({"top":0, "left": 0 - index * 746}, 500, function(){});
		});
	}
	function _openGallery(gallery){
		gallery.slideDown();
	}
	function _closeGallery(gallery){
		gallery.stop(true, true).slideUp();
	}
	
	return gallery;
	
})();



$(document).ready(function() {
	$(".header").delegate(".header-logo", "click", function(){
		var protocol = $(location).attr("protocol");
		var hostname = $(location).attr("hostname");
		var port = $(location).attr("port");
		var url = null;
		if(port){
			url = protocol + "//" + hostname + ":" + port +"/fx";
		}else{
			url = protocol + "//" + hostname +"/fx";
		}
		window.location = url;
	});
	var gallery = new Gallery();
	var menu = new HeaderMenu();
});

function addMapLink(){
	var menuItem = $("<div></div>");
	menuItem.attr("class", "navi-menu-item");
	var link = $("<a></a>");
	link.attr("href", "javascript:openMap()");
	var text = $("<span></span>")
	text.html($(".map-text").html());
	text.appendTo(link);
	link.appendTo(menuItem);
	menuItem.appendTo($("ul.navi-menu-items"));
}

function openMap(){
	$("#map-dialog").dialog({width: 480, height: 480});
}
