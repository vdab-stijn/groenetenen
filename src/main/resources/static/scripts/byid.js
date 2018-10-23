/**
 * 
 */
document.getElementById('search').onclick = findBranch;

function findBranch() {
	const eBranchId = document.getElementById('branchId');
	
	if (!eBranchId.validity.valid) {
		alert('Invalid id');
		
		eBranchId.focus();
		
		return;
	}
	
	const request = new XMLHttpRequest();
	
	request.open('GET', eBranchId.value, true);
	request.setRequestHeader('accept', 'application/json');
	request.onload = processResponse;
	request.send();
}

function processResponse() {
	switch (this.status) {
		case 200:
			const branchResource = JSON.parse(this.responseText);
			const branch = branchResource.branch;
			
			document.getElementById('name').innerHTML = branch.name;
			
			const address = branch.address;
			
			document.getElementById('address').innerHTML = address.street +
				' ' + address.number + ' ' + address.postalCode + ' ' +
				address.municipality;
			
			break;
		case 404:
			alert('Branch does not exist');
			break;
		default:
			alert('Technical problem');
	}
}
