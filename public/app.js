const express = require('express');
const app = express();
const path = require('path');

// Specify the directory where static files are located (public directory)
app.use(express.static(path.join(__dirname, 'public')));

// Start the server
const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
    console.log(`Server is running on port ${PORT}`);
});