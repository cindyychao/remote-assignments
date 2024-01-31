function groupByKey(input) {
    const result = {};
    for (const entry of input) {
        // If the key is not in the result object, initialize its sum to the current value
        // Otherwise, add the current value to the existing sum
        result[entry.key] = (result[entry.key] || 0) + entry.value;
    }
    return result;
}
