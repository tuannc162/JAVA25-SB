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


// const paragraph1 = `
//     Với các du khách mới chập chững trong môn đi bộ đường dài , vùng
//     miền nam dãy Ural ( từ thung lũng sông Ural đến thung lũng sông Ufa
//     ) sẽ là lựa chọn phù hợp . Địa hình núi nơi đây không quá hiểm trở ,
//     hơn 8 tháng trong năm thường có người đi bộ đường dài tại đây . Rất
//     nhiều khách bộ hành lựa chọn tuyến đường đi qua Công viên quốc gia
//     Taganai . Điểm cao nhất trên tuyến đường dài 52km này là đỉnh núi
//     Kruglitsa cũng chỉ cao 1178m . Ở hai bên đường có một số trạm nghỉ
//     đầy đủ tiện nghi nên du khách không phải lo nghĩ đến việc phải ngủ
//     lều . Công viên quốc gia Zyuratkul là điểm đến ưa thích khác của
//     người đi bộ đường dài . Điểm nhấn của hành trình này là hồ Zyuratkul
//     cao 724m so với mặt nước biển . Nhiều du khách đã phải lòng hồ
//     Zyuratkul vì thắng cảnh ở hai bên và điều kiện hoàn hảo để tham gia
//     các hoạt động thể thao dưới nước . Khách bộ hành chỉ cần chú ý đem
//     theo đầy đủ đồ ăn , nước uống , lều trại và các trang thiết bị khác
//     vì không có nhiều cơ sở lưu trú ở Công viên quốc gia Zyuratkul .
// `;

// // Tách đoạn văn bản thành các từ
// const words1 = paragraph.split(/\s+/);

// // Tạo mảng mới sau khi xử lý từng từ
// const wordCount = words.map(word => {
//     // Kiểm tra độ dài của từ
//     if (word.length >= 5) {
//         return `${word}<div style="display: inline-block;">[${word.length}]</div>`;
//     } else {
//         return `${word}<div style="display: inline-block;">[${word.length}]</div>`;
//     }
// });

// // Gán nội dung đã xử lý vào thẻ <p> trong HTML
// document.querySelector('p').innerHTML = wordCount.join(" ");

// // Log kết quả để kiểm tra
// console.log(wordCount);


// 4.  Thay thế ký hiệu (, - dấu phẩy) => 🤔 và (. - dấu chấm) => 😲

// document.querySelector('p').innerHTML = wordCount.join(" ").replace(/,/g, '🤔').replace(/\./g, '😲');







