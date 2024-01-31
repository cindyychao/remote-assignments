function avg(data) {
    if (data.size === 0) {
        return 0;
    }
    const total = data.products.reduce((sum, product) => sum + product.price, 0);
    return total / data.size;
}
