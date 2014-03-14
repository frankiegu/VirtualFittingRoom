test("capitaliseFirstLetter single char Test", function(){
	var a= "a";
	ok("A"==capitaliseFirstLetter(a), "Passed!");
});
test("capitaliseFirstLetter single word Test", function(){
	var a= "apple";
	ok("Apple"==capitaliseFirstLetter(a), "Passed!");
});
test("capitaliseFirstLetter empty string Test", function(){
	var a= "";
	ok(""==capitaliseFirstLetter(a), "Passed!");
});
test("rendergrids Success Test", function(){
	ok(rendergrids(123,[],[],[]), "Passed!");
});
test("rendergrids Failed Test", function(){
	ok(!rendergrids("boy",[],123,[]), "Passed!");
});
test("renderpage Success Test", function(){
	ok(renderpage("boy","what","girl",123), "Passed!");
});
test("renderpage Failed Test", function(){
	ok(!renderpage("boy",[],123,[]), "Passed!");
});


