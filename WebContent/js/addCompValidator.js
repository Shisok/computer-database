/**
 * To validate discontinued after introduced
 */
let formAdd = document.getElementById("addComputerFrom");
formAdd.addEventListener("submit", function(event) {
	let name = document.getElementById("computerName").value;

	let introduced = document.getElementById("introduced").value;
	let discontinued = document.getElementById("discontinued").value;


	let isValidate = true;
	if (name.length < 1) {
		isValidate = false;
		document.getElementById("nameError").style.display = "block";
	}
	if (introduced !== null && discontinued !== null) {
		let dateIntroduced = Date.parse(introduced);
		let dateDiscontinued = Date.parse(discontinued);
		if (dateIntroduced > 0) {
			if (dateIntroduced > dateDiscontinued) {
				document.getElementById("discontinuedError").style.display = "block";
				isValidate = false;
			}
		}
	}
	if (!isValidate) {
		event.preventDefault();
	}
});