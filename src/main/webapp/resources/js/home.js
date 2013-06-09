$('document').ready(function() {
	var createEventLink = $("#create-event-link");
	var profileLink = $("#profile-link");
	var $eventListings = $(".event-listing");
	
	$.ajaxSetup({
		headers : { "Accept": "application/json;charset=UTF-8" }
	});
	
	var viewProfile = function(e) {
		$.ajax({
			url: ctx + "/users/me"
		}).done(function(user) {
			var source = $("#user-template").html();
			var template = Handlebars.compile(source);
			var userHtml = template(user);
			
			$("#action-panel").html(userHtml);
		});
		
		e.preventDefault();
	};
	
	var createEvent = function(e) {
		
		var source   = $("#create-event-template").html();
		var template = Handlebars.compile(source);
		var eventFormHtml = template();
		
		$("#action-panel").html(eventFormHtml);
		
		e.preventDefault();
	};
	
	var showEvent = function(e) {
		var $button = $(e.currentTarget);
		$.ajax({
			url: ctx + "/events/" + $button.attr("data-event-id")
		}).done(function(event) {
			var source = $("#view-event-template").html();
			var template = Handlebars.compile(source);
			var eventHtml = template(event);
			
			$("#action-panel").html(eventHtml);
		});
		
		e.preventDefault();
	};
	
	if (createEventLink.length != 0) {
		createEventLink.click(createEvent);
	}
	
	if (profileLink.length != 0) {
		profileLink.click(viewProfile);
	}
	
	if($eventListings.length != 0) {
		$eventListings.on("click", "a", showEvent);
	}
	
//	viewProfile();
	
});