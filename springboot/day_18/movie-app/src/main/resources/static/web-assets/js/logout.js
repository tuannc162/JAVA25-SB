document.getElementById('btn-logout').addEventListener('click', async function () {
    try {
        const response = await axios.post('/api/auth/logout');
        toastr.success(response.data);
        setTimeout(() => {
            window.location.href = '/dang-nhap';
        }, 2000);
    } catch (error) {
        toastr.error('Đăng xuất thất bại');
    }
});