// Tạo dữ liệu cho ứng dụng
const todos = [
    { id: 1, title: "Đi chơi", status: false },
    { id: 2, title: "Làm bài tập", status: true },
    { id: 3, title: "Đá bóng", status: true },
];

// Hiện thị danh sách todos ra ngoài giao diện
const todoContainer = document.querySelector("ul");
const renderTodos = (todos) => {
    if(todos.length == 0) {
        todoContainer.innerHTML = "<li>Không có công việc</li>";
        return;
    }

    let html = "";
    todos.forEach(todo => {
        html += `
            <li>
            <input type="checkbox" ${todo.status ? "checked" : ""}/>
            <span class = ${todo.status ? "active" : ""}>${todo.title}</span>
            <button onclick ="editTodo(${todo.id})">Edit</button>
            <button onclick ="deleteTodo(${todo.id})">Delete</button>
            </li>
        `;



        // if(todo.status) {
        //     html += `
        //     <li>
        //     <input type="checkbox" checked>
        //     <span class = "active">${todo.title}</span>
        //     <button>Edit</button>
        //     <button>Delete</button>
        //     </li>
        // `;
        // } else {
        //     html += `
        //     <li>
        //     <input type="checkbox">
        //     <span>${todo.title}</span>
        //     <button>Edit</button>
        //     <button>Delete</button>
        //     </li>
        // `;
        // }
    });
    todoContainer.innerHTML = html;
};


// 2. Thêm cv mới
const inputTodo = document.getElementById("input-todo");
const btnAdd = document.getElementById("btn-add");

const createId = () => {
    // return Math.floor(Math.random() * 1000000);
    if(todos.length === 0) {
        return 1;
    }
    return Math.max(...todos.map(todo => todo.id)) + 1;
};

btnAdd.addEventListener("click", () => {
    const title = inputTodo.value.trim(); //trim dùng để loại bỏ khoảng trống ở đầu và cuối
    if(title.length === 0) {
        alert("Tên công việc không thể trống");
        return;
    }

    const newTodo = {
        id: createId,
        title: title,
        status: false
    };
    todos.push(newTodo); //Thêm cv mới vào ds cv
    renderTodos(todos); //Tạo cv mới đó
    inputTodo.value = ""; //Xóa tên cv đó trong ô input
});


// 3. Xóa cv
// Xóa cv
const deleteTodo = id => {
    const isDelete = confirm("Bạn có chắc chắn muốn xóa công việc này không?");
    if(!isDelete) return;

    const indexDelete = todos.findIndex(todo => todo.id === id);
    todos.splice(indexDelete, 1);
    renderTodos(todos);
};

// 4. Thay đổi tiêu đề công việc
// Cập nhật tiêu đề cv
const editTodo = id => {
    const todo = todos.find(todo => todo.id === id);
    let newTitile = prompt("Cập nhật tiêu đề mới", todo.title);
    console.log(newTitile);

    if(newTitile === null) return;
    if(newTitile.trim().length == 0) {
        alert("Tên công việc không được để trống");
        return;
    }

    todo.title = newTitile;
    renderTodos(todos);
};

// 5. Thay đổi trạng thái cv
const toggleStatus = id => {
    const todo = todos.find(todo => todo.id == id);
    todo.status = !todo.status;
    renderTodos(todos);
};

// 6. Search công việc
const btnSearch = document.getElementById("btn-search");
btnSearch.addEventListener("click", () => {
    const keyword = inputTodo.value.trim().toLowerCase();
    if (keyword.length === 0) {
        alert("Nhập từ khóa tìm kiếm");
        return;
    }
    // Tìm kiếm trong danh sách todos
    const filteredTodos = todos.filter(todo => todo.title.toLowerCase().includes(keyword));
    renderTodos(filteredTodos);
});

inputTodo.addEventListener("input", () => {
    if (inputTodo.value.trim() === "") {
        getAllTodos();
    }
});






// Hiện thị danh sách todo ra ngoài giao diện khi vào trang
renderTodos(todos);
























