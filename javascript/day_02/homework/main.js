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



