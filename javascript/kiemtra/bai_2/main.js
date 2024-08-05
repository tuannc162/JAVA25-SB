const quizes = [
    {
        id: 1,
        question: "1 + 1 = ?",
        answers: [1, 2, 3, 4],
    },
    {
        id: 2,
        question: "2 + 2 = ?",
        answers: [2, 3, 4, 5],
    },
    {
        id: 3,
        question: "3 + 3 = ?",
        answers: [3, 4, 5, 6],
    },
];


// Hàm để render
const renderQuestion = () => {
    const container = document.querySelector('.quiz-container');
    container.innerHTML = quizes.map(quiz => `
        <div class="quiz-item">
            <h3>Câu ${quiz.id} : ${quiz.question}</h3>
            <div class="quiz-answer">
                ${quiz.answers.map(answer => `
                    <div class="quiz-answer-item">
                        <input type="radio" name="${quiz.id}" value="${answer}">
                        <label>${answer}</label>
                    </div>
                `).join('')}
            </div>
        </div>
    `).join('');
}

// Hàm random
const randomAnswers = () => {
    quizes.forEach(quiz => {
        const answerInput = document.querySelectorAll(`input[name="${quiz.id}"]`);
        const randomAns = Math.floor(Math.random() * answerInput.length);
        answerInput.forEach((input, index) => {
            input.checked = (index === randomAns);
        });
    });
}

renderQuestion();

// Sự kiện click cho nút Random Answer
const button = document.getElementById('btn');
button.addEventListener('click', randomAnswers);

