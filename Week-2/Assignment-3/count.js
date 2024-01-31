function count(input) {
    const result = {};
    for (const char of input) {
        // If the element is not in the result object, initialize its count to 1
        // Otherwise, increment the count
        result[char] = (result[char] || 0) + 1;
    }
    return result;
}
