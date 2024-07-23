// 1. Truy cập vào thẻ h1 có id=“heading” thay đổi màu chữ thành ‘red’, và in hoa nội dung của thẻ đó
// C1: Truy cập qua ID
const heading = document.getElementById('heading');
console.log(heading);
heading.style.color = "red";
heading.style.textTransform = "uppercase"; // text-transform: uppercase

// C2: Truy cập không có ID - trẩ về h1 đầu tiên tìm thấy
// const heading1 = document.querySelector("#heading");
// console.log(heading1);


// 2. Thay đổi màu chữ của tất cả thẻ có class “para” thành màu “blue” và có font-size = “20px”
const para = document.querySelectorAll(".para"); // Lấy tất cả sau đó dùng for để thay đổi style
console.log(para);

// C1:
for (let i = 0; i < para.length; i++) {
    para[i].style.color = "blue";
    para[i].style.fontSize = "20px";
}

// C2:
// para.forEach (para => {
//     para.style.color = "black";
//     para.style.fontSize = "50px";
// })



// 3. Thêm thẻ a link đến trang ‘facebook’ ở đằng sau thẻ có class “para-3”
// B1: Tạo thẻ a
const link = document.createElement("a");
link.href = "https://facebook.com";
// link.innerText = "Facebook";
link.innerHTML = "<span>Facebook</span>";
console.log(link);

// B2: Truy cập vào content và body (truy cập vào cha body, phía trước class content)
const contentEl = document.querySelector(".content");
document.body;

// B3: Chỉnh sửa
document.body.insertBefore(link, contentEl);



// 4. Thay đổi nội dung của thẻ có id=“title” thành “Đây là thẻ tiêu đề”
const title = document.getElementById("title");
title.innerText = "Đây là thẻ tiêu đề";


// 5. Thêm class “text-bold” vào thẻ có class=“description” (định nghĩa class “text-bold” có tác dụng in đậm chữ)
const description = document.querySelector(".description"); // truy cập vào class thì sẽ sử dụng dấu chấm
description.classList.add("text-bold");


// 6. Thay thế thẻ có class=“para-3” thành thẻ button có nội dung là “Click me”
const btn = document.createElement("button");
btn.innerText = "Click me";
console.log(btn);

// Truy cập para-3
const p3 = document.querySelector(".para-3");
document.body.replaceChild(btn, p3);


// 7. Copy thẻ có class=“para-2” và hiển thị ngay đằng sau thẻ đó
const p2 = document.querySelector(".para-2");
const p2Copy = p2.cloneNode(true);
document.body.insertBefore(p2Copy, btn);


// 8. Xóa thẻ có class=“para-1”
const p1 = document.querySelector(".para-1");
document.body.removeChild(p1);



// Câu 1. Tạo 1 thẻ p có id=“text”, có nội dung bất kỳ (có thể tạo bằng HTML hay Javascript - tùy chọn). Yêu cầu
const tx = document.getElementById("text");
console.log(tx);

// Đặt màu văn bản thành #777
tx.style.color = "#777";

// Đặt kích thước phông chữ thành 2rem
tx.style.fontSize = "2rem";

// Đặt nội dung HTML thành Tôi có thể làm <em> bất cứ điều gì </em> tôi muốn với JavaScript.
tx.innerHTML = "Tôi có thể làm <em> bất cứ điều gì </em> tôi muốn với JavaScript";

// Câu 2. Sử dụng vòng lặp để đặt màu chữ cho tất cả thẻ li thành màu blue (tạo thẻ ul-li bằng html)
// const ulLi = document.querySelector("ul");
// console.log(ulLi);
// const ulLi1 = ulLi.querySelectorAll("li");
// console.log(ulLi1);
// ulLi1.forEach (ulLi1 => {
//     ulLi1.style.color = "blue";
// })

// C2:
const myList = document.querySelectorAll("#text + ul li");
myList.forEach (myList => {
    myList.style.color = "blue";
})



// Câu 3. Cho mã HTML có nội dung như sau (tạo thẻ ul-li bằng html):
// Sử dụng javascript để thực hiện những công việc sau:

// 1. Thêm 3 thẻ <li> có nội dung Item 8, Item 9, Item 10 vào cuối danh sách
const newItem8 = document.createElement('li');
newItem8.innerText = 'Item 8';
const newItem9 = document.createElement('li');
newItem9.innerText = 'Item 9';
const newItem10 = document.createElement('li');
newItem10.innerText = 'Item 10';
const list = document.getElementById('list');
// Thêm các phần tử <li> mới vào cuối danh sách
list.appendChild(newItem8);
list.appendChild(newItem9);
list.appendChild(newItem10);

// C2: Dùng for
// for (let i = 8; i < 11; i++) {
//     const newIt = document.createElement("li");
//     newIt.textContent = `Item ${i}`;
//     list.appendChild(newIt);
// }


// 2. Sửa nội dung cho thẻ <li> 1 thành màu đỏ (color)
const item1 = list.getElementsByTagName('li')[0];
item1.style.color = 'red';

// 3. Sửa background cho thẻ <li> 3 thành màu xanh (background-color)
const item3 = list.getElementsByTagName('li')[2];
item3.style.backgroundColor = 'blue';

// 4. Remove thẻ <li> 4
const item4 = list.getElementsByTagName('li')[3];
item4.remove();


// 5. Thêm thẻ <li> mới thay thế cho thẻ <li> 4 bị xóa ở bước trước, thẻ <li> mới có nội dung bất kỳ
const newItem4 = document.createElement('li');
newItem4.innerText = 'Đã thay thế';
list.insertBefore(newItem4, list.children[3]);

// C2:
// list.children[2].insertAdjacentElement("afterend", newItem4);
// list.children[3].insertAdjacentElement("beforebegin", newItem4);
// list.children[2].insertAdjacentHTML("afterend", "<li>Nội dung mới</li>");


