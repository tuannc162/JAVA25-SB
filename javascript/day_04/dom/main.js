// C1: Sử dụng HTML-attribute
const sayHello = () => {
    alert('Xin chào các bạn 1');
};

// C2: Sử dụng DOM property
const btn2 = document.getElementById("btn2");
btn2.onclick = () => {
    alert('Xin chào các bạn 2');
};

// C3: Sử dụng addEventListener
const btn3 = document.getElementById("btn3");
btn3.addEventListener("click", () => {
    alert('Xin chào các bạn 3');
    });



// Ví dụ 2:
const btnPlay = document.getElementById("play");
const btnPause = document.getElementById("pause");
const timeEl = document.getElementById("time");
const messageEl = document.getElementById("message");

let time = 10;
let interval;

btnPlay.addEventListener("click", () => {
    interval = setInterval (() => {
        time--;
        timeEl.innerText = `${time}s`;
        if(time == 0) {
            messageEl.innerText = "Hết giờ";
            clearInterval(interval);
        }
    }, 1000); //1000ms = 1s
});

btnPause.addEventListener("click", () => {
    clearInterval(interval);
});



// Ví dụ 3: Mouse event
document.addEventListener("click", (e) => {
    const currentEl = document.querySelector(".circle");
    if(currentEl) {
        currentEl.style.left = `${e.offsetX - 50}px`;
        currentEl.style.top = `${e.offsetY - 50}px`;
        return;
    }

    const circleEl = document.createElement("div");
    circleEl.classList.add("circle");

    // Set position for circle
    circleEl.style.left = `${e.offsetX - 50}px`;
    circleEl.style.top = `${e.offsetY - 50}px`;

    document.body.appendChild(circleEl)
});



// Ví dụ 4: Tìm kiếm user
const users = [
    { id: 1, name: "Nguyễn Văn Thịnh" },
    { id: 2, name: "Trần Anh Bảo" },
    { id: 3, name: "Phạm Thái Linh" },
    { id: 4, name: "Nguyễn Quốc Hùng" },
    { id: 5, name: "Trần Thảo Nhi" },
    { id: 6, name: "Ngô Tuấn Minh" },
    { id: 7, name: "Uông Đức Thành" },
    { id: 8, name: "Bùi Minh Huy" },
];

const inputEl = document.getElementById("input-name");
const btnShowAll = document.getElementById("btn-show-all");
const listUserEl = document.getElementById("list");

const renderUsers = (users) => {
    let html = "";
    users.forEach(user => {
        html += `<li>${user.id} - ${user.name}</li>`;
    });
    listUserEl.innerHTML = html;
};

inputEl.addEventListener("keydown", (e) => {
    if(e.key === "Enter") {
        // Lấy keyword từ input
        const keyword = e.target.value;

        // Lọc ra những user có tên chứa keyword
        const result = users.filter(user => user.name.toLowerCase().includes(keyword.toLowerCase()));

        // Hiển thị kết quả
        renderUsers(result);
    }
})

btnShowAll.addEventListener("click", () => {
    renderUsers(users);
});

renderUsers(users);




// Ví dụ 5: Scroll back-to-top
const btnTop = document.getElementById("back-to-top");
window.addEventListener("scroll", () => {
    if(document.documentElement.scrollTop > 300) {
        // Hiện thị nút back to top
        btnTop.classList.remove("hide");
    } else {
        // Ẩn nút back to top
        btnTop.classList.add("hide");
    }
});

btnTop.addEventListener("click", () => {
    window.scrollTo({
        top: 0,
        behavior: "smooth"
    });
});
