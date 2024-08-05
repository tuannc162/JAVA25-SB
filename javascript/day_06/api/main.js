// console.log("Trước Promise");

// // promise: Đại diện cho 1 cv mất 1 khoảng thời gian là n hoàn thành
// const promise = new Promise((resolve, reject) => {
//     console.log("Trong Promise");
//     // Lấy dữ liệu từ CSDL
//     let data ;
//     setTimeout(() => {
//         console.log("Hoàn thành Promise");
//         data = "dữ liệu";

//         // resolve(data); // Công việc hoàn thành
//         reject(new Error("Không thể lấy được dữ liệu!"));
//     }, 3000); // Giả sử là mất 3s để lấy query dữ liệu trong CSDL
// });

// console.log("Sau Promise");
// console.log(promise);


// promise.then((data) => {
//     console.log("Trong then");
//     console.log("Tôi nhận được data", data);
// }).catch(reason => {

// });

// console.log("Sau then")


// // Call API
// // Application Programing Interface
// // Giao diện lập trình ứng dụng  => bộ các quy tắc cho phép các ứng dụng/hệ thống giao tiếp/trao đổi và làm việc với nhau

// // Các điểm truy cập cho phép các ứng dụng Client giao tiếp và trao đổi với Server
// // HTTP => giao thức phôt biến để truyển tải dữ liệu giữ Client và Server



// https://dummyjson.com/products
// fetch("https://dummyjson.com/products")
//     .then(function(response) {});
//     .catch(function(error) {});

function getProducts() {
    return fetch("https://dummyjson.com/products").then(function(response) {
        return response.json(); //convert dữ liệu thành JSON
    });
};

function renderProducts (products = []) {
    const productListEl = document.querySelector(".products");

    const html = products.map(function (product) {
        const productHtml = [
            `<div>`,
            `<div class="product-image">`,
            `<img src="${product.thumbnail} alt="${product.title}" />`,
            `</div>`,
            `<div class="product-info">`,
            `<h3 class="product-title">${product.title}</h3>`,
            `<p class="product-price">${product.price}</p>`,
            `</div>`,
            `</div>`,

          ].join("");

          productListEl.innerHTML = html;
    });

};

function main() {
    getProducts().then(function (data) {
        renderProducts(data.products);
    });
};
main();
















