function onEdit(searchValue) {
	var count = 0;
	$('#mainTable > tbody  > tr').each(function() {
		var itemName = $(this).children()[1].innerHTML;
		if (itemName.toLowerCase().indexOf(searchValue.toLowerCase()) === -1) {
			$(this).hide();
		}
		else {
			$(this).show();
			count += 1;
		}
		
	});

	if (count === 0) {
		$('#searchbox').attr('pattern', '');
	}
	else {
		$('#searchbox').attr('pattern', '.*');
	}
	//console.log(count);

	//alert(count);
}


var continueRecursiveCall = false;
var lastSearchValue = "";

$('#searchbox').focus(function() {
	//console.log("Beginning Recursive Call");
	continueRecursiveCall = true;
	recursiveCall();
	//console.log($('#searchbox').val());
});

$('#searchbox').blur(function() {
	//console.log("Ending Recursive Call");
	continueRecursiveCall = false;
});

function recursiveCall () {
	//console.log("recursiveCall");
	if (lastSearchValue != $('#searchbox').val()) {
		//console.log("new search value:"+$('#searchbox').val());
		lastSearchValue = $('#searchbox').val();

		onEdit(lastSearchValue);

		/*if (lastSearchValue.length > 10) {
			$('#searchbox').attr('pattern', '');
		}
		else {
			$('#searchbox').attr('pattern', '.*');
		}*/
	}
	if (continueRecursiveCall) {
		setTimeout(recursiveCall,10);
	}
}

//console.log("FINISHED");