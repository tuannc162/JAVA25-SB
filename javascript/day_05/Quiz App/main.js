// Tạo dữ liệu
const questions = [
    {
        id: 1,
        title: "Thủ đô của Việt Nam là gì?",
        choices: ["Hà Nội", "TP Hồ Chí Minh", "Hải Phòng", "Đà Nẵng"],
        answer: "Hà Nội"
    },
    {
        id: 2,
        title: "1 + 1 bằng mấy?",
        choices: [1, 2, 3, 4],
        answer: "2"
    },
    {
        id: 3,
        title: "Năm 2024 có phải năm nhuận không?",
        choices: ["Có", "Không"],
        answer: "Có"
    },
    {
        id: 4,
        title: "1 năm có mấy mùa",
        choices: [1, 2, 3, 4],
        answer: "4"
    },
    {
        id: 5,
        title: "1 tháng có nhiều nhất bao nhiêu ngày",
        choices: [31, 28, 29, 30],
        answer: "31"
    },
    {
        id: 5,
        title: "1 + 1 = 3",
        choices: [true, false],
        answer: "false"
    },
];

// biến tăng khi next câu hỏi
let currentQuestionIndex = 0;

// hàm vẽ lại giao diện
const renderQuestion = () => {
    const question = questions[currentQuestionIndex];

    // Hiện thị câu hỏi
    const titleEl = document.querySelector("#question p");
    titleEl.innerHTML = `Câu ${currentQuestionIndex + 1}: ${question.title}`;

    // Hiện thị các lựa chọn
    const choicesEl = document.querySelector(".choices");
    let html = "";
    question.choices.forEach((choice, index) => {
        html += `
            <div class="choice-item">
            <input type="radio" name="choice" id="${index}" value="${choice}">
            <label for="${index}">${choice}</label>
        </div>
        `;
    });
    choicesEl.innerHTML = html;

    // Hiển thị nút Kết thúc
    const btnFinish = document.querySelector("#btn-finish");
    if (currentQuestionIndex == questions.length - 1) {
        btnFinish.classList.remove("hide");
        document.querySelector("#btn-next").classList.add("hide");
    }
};




// Xử lý sự kiện khi bấm vào nút Câu tiếp theo
let score = 0;
document.querySelector("#btn-next").addEventListener("click", () => {
    const select = document.querySelector('input[name="choice"]:checked');
    
    if (select) {
        const question = questions[currentQuestionIndex];
        if (select.value == question.answer) {
            score++;
        }

        currentQuestionIndex++;
        renderQuestion();

    } else {
        alert("Bạn hãy chọn đáp án trước khi bấm tiếp tục.");
    }
});



// Xử lý sự kiện khi bấm vào nút Kết thúc
document.querySelector("#btn-finish").addEventListener("click", () => {
    const select = document.querySelector('input[name="choice"]:checked');
    if (select) {
        const question = questions[currentQuestionIndex];
        if (select.value == question.answer) {
            score++;
        }
    }

    alert(`Bạn đã trả lời đúng ${score} trên tổng số ${questions.length} câu hỏi.`);
});

renderQuestion();

// 2. next câu tiếp, nếu không chọn đáp án sẽ hiện thông báo

// 3. Lưu lại câu trả lời để tính điểm

// 4. Đến câu cuối cùng sẽ thông báo cho họ điểm

// nộp bài qua giao diện codepain hoặc github.io,...
