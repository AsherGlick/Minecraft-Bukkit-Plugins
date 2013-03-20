function onEdit() {
	alert("pre");
	var count = 0;
	$('#mainTable > tbody  > tr').each(function() {
		count += 1;
	});
	alert(count);
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
		console.log("new search value:"+$('#searchbox').val());
		lastSearchValue = $('#searchbox').val();
		if (lastSearchValue.length > 10) {
			$('#searchbox').attr('pattern', '');
		}
		else {
			$('#searchbox').attr('pattern', '.*');
		}
	}
	if (continueRecursiveCall) {
		setTimeout(recursiveCall,10);
	}
}

console.log("FINISHED");