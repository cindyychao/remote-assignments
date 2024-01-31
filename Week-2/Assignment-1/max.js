function max(numbers) {
    if (arr.length === 0) {
        return undefined; // Return undefined for an empty array
    }
    let max = arr[0]; // Assume the first element is the maximum
    for (let i = 1; i < arr.length; i++) {
        if (arr[i] > max) {
            max = arr[i]; // Update max if a larger element is found
        }
    }
    return max;
}
