function findPosition(numbers, target) {
    for (let i = 0; i < numbers.length; i++) {
        if (numbers[i] === target) {
            return i; 
        }
    }
    return -1; // Return -1 if the target is not found in the array
}
