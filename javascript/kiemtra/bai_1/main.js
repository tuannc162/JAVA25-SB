// Bài 1:  Viết function truyền vào mảng các chuỗi có độ dài bất kỳ. Kết quả trả về là 1 mảng các chuỗi có độ dài lớn nhất
console.log("Bài 1:");
function getStringHasMaxLength(arr) {
    let maxLength = 0;
    const result = [];

    arr.forEach(str => {
        if (str.length > maxLength) {
            maxLength = str.length;
            result.length = 0;
            result.push(str);
        } else if (str.length === maxLength) {
            result.push(str);
        }
    });
    return result;
}
const result = getStringHasMaxLength(['aba', 'aa', 'ad', 'c', 'vcd']);
console.log(result);

// Bài 2: Cho mảng Users sau:
const users = [
    {
        name: "Bùi Công Sơn",
        age: 30,
        isStatus: true
    },
    {
        name: "Nguyễn Thu Hằng",
        age: 27,
        isStatus: false
    },
    {
        name: "Phạm Văn Dũng",
        age: 20,
        isStatus: false
    }
];

// 1. Viết function truyền vào 1 mảng các object user. Trả về mảng các user có age > 25 và isStatus = true
console.log("Bài 2.1:");
function getUsersByAgeAndStatus(users) {
    return users.filter(user => user.age > 25 && user.isStatus);
}
const result1 = getUsersByAgeAndStatus(users);
console.log(result1);

// 2. Viết function truyền vào 1 mảng các object user. Trả về mảng các user có age tăng dần
console.log("Bài 2.2:");
function getUsersSortedByAge(users) {
    return users.slice().sort((a, b) => a.age - b.age);
}
const result2 = getUsersSortedByAge(users);
console.log(result2);

// Bài 3: Viết function truyền vào 1 mảng các chuỗi. Trả về Object hiển thị xem mỗi phần tử trong mảng xuất hiện bao nhiêu lần
console.log("Bài 3:")
function getCountElement(arr) {
    const count = {};
    arr.forEach(i => {
        if (count[i]) {
            count[i] += 1;
        } else {
            count[i] = 1;
        }
    });

    return count;
}
const result3 = getCountElement(["one", "two", "three", "one", "one", "three"]);
console.log(result3);