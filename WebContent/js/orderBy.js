// Function toggleOrderComputer
(function($) {

	$.fn.toggleOrderComputer = function() {
		if ($("#orderComputer").text() == "Order by Id") {
			$("#orderComputer").text("Order by Name");
		} else {
			$("#orderComputer").text("Order by Id");
		}
		return this;
	};

}(jQuery));