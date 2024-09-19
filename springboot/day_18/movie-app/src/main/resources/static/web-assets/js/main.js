
    document.addEventListener('DOMContentLoaded', function() {
    const myModal = document.getElementById('modal-trailer');
    const iframe = myModal.querySelector('iframe');

    myModal.addEventListener('hide.bs.modal', function () {
    const src = iframe.src;
    iframe.src = '';
    iframe.src = src;
});
});


    document.addEventListener('DOMContentLoaded', function() {
    const stars = document.querySelectorAll('#rating i');
    const ratingValue = document.getElementById('ratingValue');
    const ratingDisplay = document.getElementById('ratingDisplay');

    // Xử lý khi người dùng nhấp vào sao
    stars.forEach(star => {
    star.addEventListener('click', function() {
    const rating = this.getAttribute('data-rating');
    ratingValue.value = rating;
    updateStarColors(rating);
    updateRatingDisplay(rating);
});

    // Xử lý khi di chuột qua sao
    star.addEventListener('mouseover', function() {
    const rating = this.getAttribute('data-rating');
    updateStarColors(rating, true);
});

    // Xử lý khi di chuột ra ngoài sao
    star.addEventListener('mouseout', function() {
    const rating = ratingValue.value;
    updateStarColors(rating);
});
});

    // Xử lý khi người dùng nhấp vào nút "Hoàn tất"
    document.getElementById('completeReview').addEventListener('click', function() {
    const rating = ratingValue.value;
    const comment = document.getElementById('comment').value;

    if (rating > 0 && comment.trim() !== '') {
    console.log('Đánh giá:', rating);
    console.log('Bình luận:', comment);

    // Đóng modal sau khi gửi
    var modal = bootstrap.Modal.getInstance(document.getElementById('reviewModal'));
    modal.hide();
} else {
    alert('Vui lòng chọn đánh giá và nhập bình luận.');
}
});

    // Cập nhật màu sắc của các sao
    function updateStarColors(rating, hover = false) {
    stars.forEach(star => {
    const starRating = parseInt(star.getAttribute('data-rating'));
    if (hover) {
    // Nếu đang di chuột qua sao
    if (starRating <= rating) {
    star.style.color = '#FFD43B';
} else {
    star.style.color = '#ccc';
}
} else {
    // Nếu không di chuột qua sao
    if (starRating <= rating) {
    star.style.color = '#FFD43B';
} else {
    star.style.color = '#ccc';
}
}
});
}

    // Cập nhật hiển thị đánh giá
    function updateRatingDisplay(rating) {
    ratingDisplay.textContent = `Bạn đã đánh giá ${rating}/10`;
}
});
