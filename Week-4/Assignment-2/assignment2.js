function ajax(src, callback) {
    fetch(src)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            callback(data);
        })
        .catch(error => {
            console.error('Fetch error:', error.message);
        });
}

function render(data) {
    const container = document.createElement('div');

    data.forEach(product => {
        const productDiv = document.createElement('div');
        productDiv.textContent = product.name;
        container.appendChild(productDiv);
    });

    // Append the container to a specific element on the page
    const targetElement = document.getElementById('product-container');
    targetElement.appendChild(container);
}

ajax(
    'https://remote-assignment.s3.ap-northeast-1.amazonaws.com/products',
    function(response) {
        render(response);
    }
);
