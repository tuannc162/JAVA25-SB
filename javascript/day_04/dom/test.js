// 1. Khi nhấn vào button “Change content” hiển thị một nội dung quote bất kỳ
const text = document.getElementById('text');
const btnChangeContent = document.getElementById('btn-1');

btnChangeContent.onclick = function() {
    const quotes = [
        "Tôi tên là Nguyễn Văn A",
        "Xin chào!"
    ];
    const randomQuote = quotes[Math.floor(Math.random() * quotes.length)];
    text.textContent = randomQuote;
};

// 2. Khi nhấn vào button “Change color” thì thay đổi màu của thẻ p (sử dụng màu HEX - cần viết 1 function để random màu HEX)
const btnChangeColor = document.getElementById('btn-2');

btnChangeColor.onclick = function() {
    const randomColor = getRandomHexColor();
    text.style.color = randomColor;
};

// Hàm để tạo màu HEX ngẫu nhiên
function getRandomHexColor() {
    const letters = '0123456789ABCDEF';
    let color = '#';
    for (let i = 0; i < 6; i++) {
        color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
}

// 3. Khi nhấn vào button “Change background” thì thay đổi màu background-color của thẻ p (sử dụng màu RGB - cần viết 1 function để random màu RGB)
const btnChangeBackground = document.getElementById('btn-3');

btnChangeBackground.addEventListener('click', function() {
    const randomColor = getRandomRgbColor();
    text.style.backgroundColor = randomColor;
});

// Hàm để tạo màu RGB ngẫu nhiên
function getRandomRgbColor() {
    const r = Math.floor(Math.random() * 256);
    const g = Math.floor(Math.random() * 256);
    const b = Math.floor(Math.random() * 256);
    return `rgb(${r}, ${g}, ${b})`;
}




