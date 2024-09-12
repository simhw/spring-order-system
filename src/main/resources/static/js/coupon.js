$(document).ready(function () {

    $('.download-btn').click(function (event) {
        event.preventDefault();
        const couponId = $(this).data('coupon-id');

        $.ajax({
            type: "POST",
            url: "/api/coupons/download",
            data: {"couponId": couponId},
            success: function (data) {
                if (data.code === 201) {
                    alert("다운로드가 완료되었습니다.")
                }
            },
            error: function (xhr) {
                if (xhr.status === 401) {
                    window.location.href = '/login';
                }
                alert("다운로드에 실패했습니다.")
            }
        });
    });
});


