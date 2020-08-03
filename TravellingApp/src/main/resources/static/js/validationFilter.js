function hasClass(element, className) {
	return (' ' + element.className + ' ').indexOf(' ' + className + ' ') > -1;
}

function checkNCount() {

	var selectedCount = document.getElementById('vacationLength').value;
	if (selectedCount < 1) {
		document.getElementById('vacationLength').value = 1;
		//alert the user that the person count is invalid
		Swal.fire({
			icon : 'error',
			title : 'Invalid vacation length',
			text : 'Something went wrong!',
			footer : "<a href>Please select a minimum of 1 night(s)</a>"
		})
	}
}

function checkPCount() {

	var selectedCount = document.getElementById('personCount').value;
	if (selectedCount < 1) {
		document.getElementById('personCount').value = 1;
		//alert the user that the person count is invalid
		Swal.fire({
			icon : 'error',
			title : 'Invalid Person count',
			text : 'Something went wrong!',
			footer : "<a href>Please select a minimum of 1 person(s)</a>"
		})
	}
}

//Set default date to current date
var current = new Date();

var day = ("0" + current.getDate()).slice(-2);
var month = ("0" + (current.getMonth() + 1)).slice(-2);

var today = current.getFullYear() + "-" + (month) + "-" + (day);

var target = document.getElementById('dateFrom');

if (!hasClass(target, "dont")) {
	document.getElementById('dateFrom').value = today;
}

//on changed (selected) date, check if date is valid, if not set it to current date

function checkDate() {
	var selectedText = document.getElementById('dateFrom').value;

	var selectedDate = new Date(selectedText);
	var now = new Date();
	if (selectedDate < now) {
		var current = new Date();
		//slicing required to match sql date format
		var day = ("0" + current.getDate()).slice(-2);
		var month = ("0" + (current.getMonth() + 1)).slice(-2);

		var today = current.getFullYear() + "-" + (month) + "-" + (day);
		//alert the user that the date is invalid
		Swal
				.fire({
					icon : 'error',
					title : 'Invalid Date',
					text : 'Something went wrong!',
					footer : "<a href>Please choose a date, which isn't in the past</a>"
				})
		document.getElementById('dateFrom').value = today;
	}
}