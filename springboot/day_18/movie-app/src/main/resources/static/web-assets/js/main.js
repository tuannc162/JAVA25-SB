document.addEventListener('DOMContentLoaded', function() {
    const myModal = document.getElementById('modal-trailer');
    const iframe = myModal.querySelector('iframe');

    myModal.addEventListener('hide.bs.modal', function () {
        const src = iframe.src;
        iframe.src = '';
        iframe.src = src;
    });
});


const stars = document.querySelectorAll(".rating .star");
const ratingValue = document.getElementById("rating-value");

let currentRating = 0;
let currentEditingReviewId = null; // To track which review is being edited


stars.forEach((star) => {
    star.addEventListener("mouseover", () => {
        resetStars();
        const rating = parseInt(star.getAttribute("data-rating"));
        highlightStars(rating);
    });

    star.addEventListener("mouseout", () => {
        resetStars();
        highlightStars(currentRating);
    });

    star.addEventListener("click", () => {
        currentRating = parseInt(star.getAttribute("data-rating"));
        ratingValue.textContent = `Bạn đã đánh giá ${currentRating} sao.`;
        highlightStars(currentRating);
    });
});

function resetStars() {
    stars.forEach((star) => {
        star.classList.remove("active");
    });
}

function highlightStars(rating) {
    stars.forEach((star) => {
        const starRating = parseInt(star.getAttribute("data-rating"));
        if (starRating <= rating) {
            star.classList.add("active");
        }
    });
}

// Hien thi danh sach review
const reviewListEl = document.querySelector(".review-list");
const renderReviews = reviews => {
    let html = "";
    reviews.forEach(review => {
        html += `
            <div class="rating-item d-flex align-items-center mb-3 pb-3">
                <div class="rating-avatar">
                    <img src="${review.user.avatar}" alt="${review.user.name}">
                </div>
                <div class="rating-info ms-3">
                    <div class="d-flex align-items-center">
                        <p class="rating-name mb-0"><strong>${review.user.name}</strong></p>
                        <p class="rating-time mb-0 ms-2">${formatDate(review.createdAt)}</p></div>
                    <div class="rating-star">
                        <p class="mb-0 fw-medium">
                            <span class="rating-icon me-1"><i class="fa fa-star"></i></span>
                            <span>${review.rating}/10</span>
                        </p>
                        <button class="btn btn-edit-review" th:data-id="${review.id}" type="button" onclick="createReview(review.id)">Sửa</button>
                        <button class="btn btn-delete-review" th:data-id="${review.id}" type="button" onclick="deleteReview(review.id)">Xóa</button>
                    </div>
                    <p class="rating-content mt-1 mb-0 text-muted">${review.content}</p></div>
            </div>
        `
    });
    reviewListEl.innerHTML = html;
}

const formatDate = dateString => {
    const date = new Date(dateString);
    const year = date.getFullYear();
    const month = ("0" + (date.getMonth() + 1)).slice(-2); // 09 -> 09 , 011 -> 11
    const day = ("0" + date.getDate()).slice(-2);
    return `${day}/${month}/${year}`;
}

const formReviewEl = document.getElementById("form-review");
const reviewContentEl = document.getElementById("review-content");
formReviewEl.addEventListener("submit", (e) => {
    e.preventDefault();
    createReview();
})


const fetchReviews = async () => {
    try {
        const response = await axios.get(`/api/reviews?movieId=${movie.id}`);
        reviews = response.data;
        renderReviews(reviews);
    } catch (error) {
        console.error("Error fetching reviews:", error);
    }
}

document.addEventListener("DOMContentLoaded", fetchReviews);

reviewListEl.addEventListener("click", async (e) => {
    if (e.target.classList.contains("btn-edit-review")) {
        const reviewId = e.target.getAttribute("data-id");
        const reviewToEdit = reviews.find(review => review.id == reviewId);
        if (reviewToEdit) {
            currentEditingReviewId = reviewId; // Set the ID of the review to edit
            currentRating = reviewToEdit.rating; // Set the current rating
            reviewContentEl.value = reviewToEdit.content; // Fill in the content
            ratingValue.textContent = `Bạn đã đánh giá ${currentRating} sao.`;
            highlightStars(currentRating); // Highlight existing rating
            const modal = new bootstrap.Modal(modalReviewEl);
            modal.show(); // Show the modal for editing
        }
    }

    if (e.target.classList.contains("btn-delete-review")) {
        const reviewId = e.target.getAttribute("data-id");
        if (confirm("Bạn có chắc chắn muốn xóa bình luận này?")) {
            await deleteReview(reviewId);
            await fetchReviews();
        }
    }
});

// Tạo và sửa review
const createReview = async () => {
    if (currentRating === 0) {
        alert("Vui lòng chọn số sao");
        return;
    }

    if (reviewContentEl.value.trim() === "") {
        alert("Vui lòng nhập nội dung bình luận");
        return;
    }

    const request = {
        rating: currentRating,
        content: reviewContentEl.value,
        movieId: movie.id
    }
    console.log(request);

    try {
        let res;
        if (currentEditingReviewId) {
            // Cập nhật review
            res = await axios.put(`/api/reviews/${currentEditingReviewId}`, request);
            reviews = reviews.map(review => review.id == currentEditingReviewId ? res.data : review);
        } else {
            // Tạo review mới
            res = await axios.post("/api/reviews", request);
            reviews.unshift(res.data);
        }
        renderReviews(reviews);
        resetModal();
        const modal = bootstrap.Modal.getInstance(modalReviewEl);
        modal.hide();
        location.reload();
    } catch (error) {
        console.log(error);
    }
}



const resetModal = () => {
    currentRating = 0; // Đặt lại giá trị đánh giá
    resetStars(); // Reset giao diện sao
    reviewContentEl.value = ""; // Xóa nội dung trong textarea
    ratingValue.textContent = "Vui lòng chọn đánh giá"; // Đặt lại thông báo
    currentEditingReviewId = null;
}

// Delete Review
const deleteReview = async (reviewId) => {
    try {
        await axios.delete(`/api/reviews/${reviewId}`);
        reviews = reviews.filter(review => review.id != reviewId);
        renderReviews(reviews);
    } catch (error) {
        console.error("Error deleting review:", error);
    }
}

// Lắng nghe sự kiện đóng modal để reset
const modalReviewEl = document.getElementById("modalReview");
modalReviewEl.addEventListener("hidden.bs.modal", resetModal);














