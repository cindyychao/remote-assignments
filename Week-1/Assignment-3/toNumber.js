function toNumber(input){
	const resultArray =[];

	for(let i=0; i<input.length; i++){
		const charCode = input[i].charCodeAt(0);

		//check if the character is lowercase
		if (charCode >=97 && charCode <=122){
			resultArray.push(charCode -96);
		}
		//check if the character is uppercase
		else if (charCode >=65 && charCode <=90){
			resultArray.push(charCode -64);
		}
	}
	return resultArray;
}


let input2 = ['e','d','c','d','e'];
console.log(countAandB(input2));
console.log(toNumber(input2);
