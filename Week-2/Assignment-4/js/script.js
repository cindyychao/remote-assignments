document.addEventListener("DOMContentLoaded", function() {
    var welcomeMessage = document.querySelector(".banner");
    var tagline = document.querySelector(".tagline");
    var paragraph = document.querySelector(".banner p");
    var learnMoreButton = document.querySelector(".button");
    var contentBoxes = document.getElementById("content-boxes");

    tagline.style.display = "block";
    paragraph.style.display = "block";

    welcomeMessage.addEventListener("click", function() {
        this.querySelector(".headline").textContent = "Have a Good Time!";
        tagline.style.display = "none";
        paragraph.style.display = "none";
        this.style.backgroundColor = "#6495ED"; 
    });

    learnMoreButton.addEventListener("click", function() {
        contentBoxes.style.display = "flex";
    });
});