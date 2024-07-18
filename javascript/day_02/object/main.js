// Khởi tạo object rỗng
let obj = {}

// Khởi tạo object có dữ liệu (nên sử dụng)
let user1 = {
    name: "Nguyễn Văn A",
    age: 23,
    email: "abc@gmail.com",
    address: {
        province: "Hà Nội",
        district: "Cầu Giấy"
    },
    languages: ["TA", "VN"],
    eat: function (food) {
        console.log(`Eating ${food}`);
    },
    showInfo: function() {
        console.log(`Xin chào, tôi tên là ${this.name}, năm nay tôi ${this.age} tuổi`);
    }
}

console.log(user1);

// // Hoặc (hạn chế sử dụng vì cách khai báo không tường mình)
// let user2 = {}
// user.name = "Nguyễn Văn A"
// user.age = 23
// user.email = "abc@gmail.com"