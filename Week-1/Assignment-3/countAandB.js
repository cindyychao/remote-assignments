function countAandB(input){
	let countA =0;
	let countB =0;

	for(let i=0; i<input.length; i++){
		if (input[i] === 'a'){
			countA ++;
		} else if (input[i] === 'b'){
			countB+ ++;
		}
	}
	return countA + countB;
}

let input1= ['a','b','c','a','c','a','c'];
console.log(countAandB(input1));
