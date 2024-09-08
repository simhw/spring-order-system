$(document).ready(function () {
    $('#like').click(function () {
        const url = "/api/like/product/" + productId;
        const method = liked ? "DELETE" : "POST";
        liked = !liked;

        $.ajax({
            type: method, url: url,
            success: function (data) {
                if (data.code === 200) {
                    $('#like').toggleClass('btn-danger btn-outline-danger');
                }
            }, error: function (xhr) {
                if (xhr.status === 401) {
                    window.location.href = '/login';
                }
            }
        });
    });
});
