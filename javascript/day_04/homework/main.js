// Bài tập 1:
const passwordInput = document.getElementById("passwordInput");
const btnShowHide = document.getElementById("showHideBtn");

showHideBtn.addEventListener('click', function() {
    if (passwordInput.type == 'password') {
        passwordInput.type = 'text';
        btnShowHide.textContent = 'Hide';
    } else {
        passwordInput.type = 'password';
        btnShowHide.textContent = 'Show';
    }
});


// Bài tập 2:
let counterVal = 0;
const counterEl = document.getElementById("counter");
const nextBtn = document.querySelector(".nextBtn");
const prevBtn = document.querySelector(".prevBtn");

function updateCounterDisplay() {
    counterEl.textContent = counterVal;
    if (counterVal > 0) {
        counterEl.style.color = "red";
    } else if (counterVal < 0) {
        counterEl.style.color = "green";
    } else {
        counterEl.style.color = "#333333";
    }
};

nextBtn.addEventListener("click", function() {
    counterVal++;
    updateCounterDisplay();
});

prevBtn.addEventListener("click", function() {
    counterVal--;
    updateCounterDisplay();
});
