// 1. Highlight tất cả các từ có độ dài lớn hơn hoặc bằng 5 ký tự trong đoạn văn (background = “yellow”)

const paragraph = document.querySelector('p').innerText;
console.log(paragraph);
const words = paragraph.split(" ");
console.log(words);
const highlightWord = words.map(word => {
    if(word.length >= 5) {
        return `<span style=background-color:yellow>${word}</span>`;
    }
    return word;
});
document.querySelector('p').innerHTML = highlightWord.join(" ");

// 2. Thêm link hiển thị text “facebook” link đến trang facebook.com ở sau thẻ p

// const link = document.createElement('a');
// link.href = 'https://www.facebook.com';
// link.innerText = 'Facebook.com';
// console.log(link);
// document.body.appendChild(link);

const link = document.createElement('a');
link.href = 'https://www.facebook.com';
link.textContent = 'Facebook';
document.body.insertBefore(link, paragraph.nextElementSibling);

// 3. Đếm số từ có trong đoạn văn. Tạo 1 thẻ div để hiển thị số từ

const wordCount = highlightWord.map(word => {
    if(word.length >=5) {
        return word +`<div style=display:inline-block>[${word.length - 43}]</div>`;
    } else {
        return word +`<div style=display:inline-block>[${word.length}]</div>`;
    }
});
document.querySelector('p').innerHTML = wordCount.join(" ");
console.log(wordCount);

// 4.  Thay thế ký hiệu (, - dấu phẩy) => 🤔 và (. - dấu chấm) => 😲

document.querySelector('p').innerHTML = wordCount.join(" ").replace(/,/g, '🤔').replace(/\./g, '😲');







