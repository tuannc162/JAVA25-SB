// Danh sách các sản phẩm có trong giỏ hàng
let products = [
    {
        name: "Iphone 13 Pro Max", // Tên sản phẩm
        price: 3000000, // Giá mỗi sản phẩm
        brand: "Apple", // Thương hiệu
        count: 2, // Số lượng sản phẩm trong giỏ hàng
    },
    {
        name: "Samsung Galaxy Z Fold3",
        price: 41000000,
        brand: "Samsung",
        count: 1,
    },
    {
        name: "IPhone 11",
        price: 15500000,
        brand: "Apple",
        count: 1,
    },
    {
        name: "OPPO Find X3 Pro",
        price: 19500000,
        brand: "OPPO",
        count: 3,
    },
]

// Câu 1: In ra thông tin các sản phẩm trong giỏ hàng theo cấu trúc sau
// Tên - Giá - Thương Hiệu - Số lượng
// Ví dụ : OPPO Find X3 Pro - 19500000 - OPPO - 3
console.log("Câu 1:");
products.forEach (product => {
    console.log(`${product.name} - ${product.price} - ${product.brand} - ${product.count}`);
});



// Câu 2: Tính tổng tiền tất cả sản phẩm trong giỏ hàng
// Tổng tiền mỗi sản phẩm = price * count
let totalCost = 0;
products.forEach(product => {
    totalCost += product.price * product.count;
});
console.log("Câu 2:")
console.log(totalCost);



// Câu 3: Tìm các sản phẩm của thuơng hiệu "Apple"
let apple = products.filter(product => product.brand == "Apple");
console.log("Câu 3:");
console.log(apple);



// Câu 4: Tìm các sản phẩm có giá > 20000000
let maxProducts = products.filter(product => product.price > 20000000);
console.log("Câu 4:");
console.log(maxProducts);




// Câu 5: Tìm các sản phẩm có chữ "pro" trong tên (Không phân biệt hoa thường)
let proProducts = products.filter(product => product.name.toLowerCase().includes("pro"));
console.log("Câu 5:");
console.log(proProducts);



// Câu 6. Thêm 1 sản phẩm bất kỳ vào trong mảng product
let newProduct = {
    name: "Xiaomi Note10",
    price: 10000000,
    brand: "Xiaomi",
    count: 0,
};
products.push(newProduct);
console.log("Câu 6:");
console.log(products);



// Câu 7. Xóa tất cả sản phẩm của thương hiệu "Samsung" trong giỏ hàng
products = products.filter(product => product.brand != "Samsung");
console.log("Câu 7:");
console.log(products);



// Câu 8. Sắp xếp giỏ hàng theo price tăng dần
products.sort((a, b) => a.price - b.price);
console.log("Câu 8:");
console.log(products);


// Câu 9. Sắp xếp giỏ hàng theo count giảm dần
products.sort((a, b) => b.count - a.count);
console.log("Câu 9:");
console.log(products);



// Câu 10. Lấy ra 2 sản phẩm bất kỳ trong giỏ hàng
let twoProducts = products.slice(0, 2);
console.log("Câu 10:");
console.log(twoProducts);


// BT2
const grades = [
    {name: 'John', grade: 8, sex: 'M'},
    {name: 'Sarah', grade: 12, sex: 'F'},
    {name: 'Bob', grade: 16, sex: 'M'},
    {name: 'Johnny', grade: 2, sex: 'M'},
    {name: 'Ethan', grade: 4, sex: 'M'},
    {name: 'Paula', grade: 18, sex: 'F'},
    {name: 'Donald', grade: 5, sex: 'M'},
    {name: 'Jennifer', grade: 13, sex: 'F'},
    {name: 'Courtney', grade: 15, sex: 'F'},
    {name: 'Jane', grade: 9, sex: 'F'}
   ];
   
   // 1. tính thứ hạng trung bình của cả lớp
   function averageGradeOfClass() {
     const totalGrades = grades.reduce((sum, student) => sum + student.grade, 0);
     return totalGrades / grades.length;
   }
   
   // 2. tính thứ hạng trung bình của nam trong lớp
   function averageGradeOfBoys() {
     const boys = grades.filter(student => student.sex === 'M');
     const totalGrades = boys.reduce((sum, student) => sum + student.grade, 0);
     return totalGrades / boys.length;
   }
   
   // 3. tính thứ hạng trung bình của nữ trong lớp
   function averageGradeOfGirls() {
     const girls = grades.filter(student => student.sex === 'F');
     const totalGrades = girls.reduce((sum, student) => sum + student.grade, 0);
     return totalGrades / girls.length;
   }
   
   // 4. tìm học viên nam có thứ hạng cao nhất trong lớp
   function highGradeBoy() {
     const boys = grades.filter(student => student.sex === 'M');
     return boys.reduce((maxGradeStudent, student) => (student.grade > maxGradeStudent.grade ? student : maxGradeStudent));
   }
   
   // 5. tìm học viên nữ có thứ hạng cao nhất trong lớp
   function highGradeGirl() {
     const girls = grades.filter(student => student.sex === 'F');
     return girls.reduce((maxGradeStudent, student) => (student.grade > maxGradeStudent.grade ? student : maxGradeStudent));
   }
   
   // 6. tìm học viên nam có thứ hạng thấp nhất trong lớp
   function lowGradeBoy() {
     const boys = grades.filter(student => student.sex === 'M');
     return boys.reduce((minGradeStudent, student) => (student.grade < minGradeStudent.grade ? student : minGradeStudent));
   }
   
   // 7. tìm học viên nữ có thứ hạng thấp nhất trong lớp
   function lowGradeGirl() {
     const girls = grades.filter(student => student.sex === 'F');
     return girls.reduce((minGradeStudent, student) => (student.grade < minGradeStudent.grade ? student : minGradeStudent));
   }
   
   // 8. thứ hạng cao nhất của cả lớp
   function highGradeOfClass() {
     return grades.reduce((maxGrade, student) => (student.grade > maxGrade ? student.grade : maxGrade), -Infinity);
   }
   
   // 9. thứ hạng thấp nhất của cả lớp
   function lowGradeOfClass() {
     return grades.reduce((minGrade, student) => (student.grade < minGrade ? student.grade : minGrade), Infinity);
   }
   
   // 10. lấy ra danh sách tất cả học viên nữ trong lớp
   function getAllGirls() {
     return grades.filter(student => student.sex === 'F');
   }
   
   // 11. sắp xếp tên học viên theo chiều tăng dần của bảng chữ cái
   function sortByNameAscending() {
     return grades.slice().sort((a, b) => a.name.localeCompare(b.name));
   }
   
   // 12. sắp xếp thứ hạng học viên theo chiều giảm dần
   function sortByGradeDescending() {
     return grades.slice().sort((a, b) => b.grade - a.grade);
   }
   
   // 13. lấy ra học viên nữ có tên bắt đầu bằng "J"
   function getGirlsWithNameStartingWithJ() {
     return grades.filter(student => student.sex === 'F' && student.name.startsWith('J'));
   }
   
   // 14. lấy ra top 5 người có thứ hạng cao nhất trong lớp
   function getTop5Students() {
     return grades.slice().sort((a, b) => b.grade - a.grade).slice(0, 5);
   }

console.log('Thứ hạng trung bình của cả lớp:', averageGradeOfClass());
console.log('Thứ hạng trung bình của nam trong lớp:', averageGradeOfBoys());
console.log('Thứ hạng trung bình của nữ trong lớp:', averageGradeOfGirls());
console.log('Học viên nam có thứ hạng cao nhất trong lớp:', highGradeBoy());
console.log('Học viên nữ có thứ hạng cao nhất trong lớp:', highGradeGirl());
console.log('Học viên nam có thứ hạng thấp nhất trong lớp:', lowGradeBoy());
console.log('Học viên nữ có thứ hạng thấp nhất trong lớp:', lowGradeGirl());
console.log('Thứ hạng cao nhất của cả lớp:', highGradeOfClass());
console.log('Thứ hạng thấp nhất của cả lớp:', lowGradeOfClass());
console.log('Danh sách học viên nữ trong lớp:', getAllGirls());
console.log('Danh sách học viên được sắp xếp theo tên:', sortByNameAscending());
console.log('Danh sách học viên được sắp xếp theo thứ hạng giảm dần:', sortByGradeDescending());
console.log('Học viên nữ có tên bắt đầu bằng "J":', getGirlsWithNameStartingWithJ());
console.log('Top 5 học viên có thứ hạng cao nhất trong lớp:', getTop5Students());
