// Khai báo array rỗng
let arr = [];

// Khai báo array
let number = [];

// Gán giá trị cho các phần tử của array thông qua chỉ số
number[0] = 1;
number[1] = "Bùi Hiên";
number[2] = true;

// Khai báo và khởi tạo giá trị cho array
let myArr = [1, 2, 3, 4, true, false, "Nguyễn Văn A"];


// Bài 1: Viết function tìm số lớn nhất trong mảng
// C1: Sắp xếp
const findMax = (arr) => {
    arr.sort((a,b) => b-a);
    return arr[0];
}
console.log(findMax([1,5,7,3,10,9,2]));

// C2: So sánh -> gán
const findMax2 = (arr) => {
    let max = arr[0];
    arr.forEach((value) => {
        if(value > max){
            max = value;
        }
    })
    return max;
}
console.log(findMax2([1,5,7,3,10,9,2,12]));

// C3: Cách thuần JS - Math.max(1,3,4,43,5,6,10)
const findMax3 = (arr) => {
    return Math.max(...arr); // ... là cú pháp của ES6: Spread operator có tác dụng duỗi 1 mảng thành danh sách số
}
console.log(findMax3([1,5,7,3,10,9,2,12,20]));


// Bài 3: Viết function cho phép truyền vào 1 mảng các số, kết quả trả về là 1 mảng mới với các số là số dư tương ứng khi chia mảng cũ cho 2
// Ví dụ : [4,2,5,6,2,7] => [0,0,1,0,0,1]
// C1:
const modulo2 = (arr) => {
    let rs = [];
    arr.forEach ((value) => {
        rs.push(value % 2);
        // arr[index] = value % 2;
    })
    return rs;
}
console.log(modulo2([4,2,5,6,2,7]));

// C2: Dùng map
const modulo3 = (arr) => {
    return arr.map((e) => e % 2);
}

// C3: Dùng Filter
function oddNumbers(arr) {
    // const result = arr.filter(e => e % 2 == 1)
    // return result

    return arr.filter((e) => e % 2 == 1);
}
console.log(oddNumbers([4, 2, 5, 6, 2, 7]));


// Bài 5: Viết function truyền vào 1 chuỗi, hãy sao chép đó chuỗi lên 10 lần, ngăn cách nhau bởi dấu gạch ngang (Sử dụng array để làm)

function repeatStringWithSeparator(str, times, separator) {
    // Tạo một mảng gồm `times` phần tử, mỗi phần tử là chuỗi `str`
    let repeatedArray = new Array(times).fill(str);
    
    // Sử dụng `join` để nối các phần tử trong mảng với `separator` giữa chúng
    let repeatedString = repeatedArray.join(separator);
    
    return repeatedString;
}

// Chuỗi cần sao chép
let inputString = "a";

// Số lần sao chép
let numberOfTimes = 10;

// Dấu phân cách
let separator = "-";

// Gọi hàm và in kết quả
let result = repeatStringWithSeparator(inputString, numberOfTimes, separator);
console.log(result);



