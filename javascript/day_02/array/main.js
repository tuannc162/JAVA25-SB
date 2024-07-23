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
// Ví dụ: repeatString(‘a’) => Kết quả trả về là ‘a-a-a-a-a-a-a-a-a-a’
function repeatString(str) {
    // Tạo một mảng gồm 10 phần tử, mỗi phần tử là chuỗi str
    let repeatedArray = new Array(10).fill(str);
    
    // Dùng phương thức join để nối các chuỗi trong mảng lại với nhau, cách nhau bởi dấu '-'
    let repeatedString = repeatedArray.join('-');
    
    return repeatedString;
  }
  console.log(repeatString('a'));


  // 6. Viết function truyền vào 1 chuỗi, kiểm tra xem chuỗi đó có phải chuỗi đối xứng hay không (chuỗi đối xứng là chuỗi đọc xuôi hay ngược đều như nhau, không tính khoảng trắng, không phân biệt hoa thường), kết quả trả về true hoặc false.
  function isSymmetric(str) {
    // Chuẩn hóa chuỗi: loại bỏ khoảng trắng và chuyển về chữ thường
    const normalizedStr = str.replace(/\s/g, '').toLowerCase();
    
    // Kiểm tra chuỗi đối xứng
    for (let i = 0; i < normalizedStr.length / 2; i++) {
      if (normalizedStr[i] !== normalizedStr[normalizedStr.length - 1 - i]) {
        return false;
      }
    }
    
    return true;
  }




