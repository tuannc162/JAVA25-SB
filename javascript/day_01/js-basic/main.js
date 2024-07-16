console.log("Hello world");

// Khai báo biến và không gán giá trị cho biến
let age;
console.log(age); //undefined (Kiểu dữ liệu nguyên thủy, vì chưa gán giá trị cho biến age)
age = 35;
console.log(age);

// Khai báo biến và gán giá trị cho biến
let email = "hien@techmaster.vn"
console.log(email);


let NUMBER = 10;
const lenght = 20

// Template strings - ES6
let name = "Bùi Hiên"
let year = 1997

console.log(`Xin chào các bạn, mình tên là ${name}. Năm nay ${2022 - year} tuổi`);

// Function
// Tính tổng 2 số
// c1: Regular function
function sum(a, b) {
    return a + b;
}

// c2: function expression
let sum1 = function(a, b) {
    return a + b;
}

// c3: Arrow function (ES6) -  Gần giống Lambda function (Java8)
let sum2 = (a, b) => {
    return a + b;
}




let reverseString = (str) => {
    let reversed = "";

    for(let i=str.length; i>=0; i--){
        reversed += str[i];
    }
    return reversed;
}
console.log(reverseString("hello"));

function result(n) {
    let reversed = "";

    for(let i=n.length; i>=0; i--){
        reversed += n[i];
    }
    return reversed;
}
console.log(result("hello"));