// add hovered class to selected list item

let list = document.querySelectorAll(".navigation li");

function activeLink() {
    list.forEach(item => {
        item.classList.remove("hovered");
    });
    this.classList.add("hovered")
}

list.forEach(item => item.addEventListener("mouseover", activeLink));

//menu toggle

let toggle = document.querySelector(".toggle");
let navigation = document.querySelector(".navigation");
let main = document.querySelector(".main");

toggle.onclick = function () {
    navigation.classList.toggle("active");
    main.classList.toggle("active");
};

// document.querySelectorAll(".btn-delete").forEach(function (el) {
//     el.addEventListener("click", function (e) {
//         e.preventDefault();  // Ngăn form gửi đi ngay lập tức
//         let form = this.closest("form");  // Lấy form tương ứng với nút xóa
//         deleteConfirm(form);
//     });
// });
//
// function deleteConfirm(form) {
//     Swal.fire({
//         title: "Bạn chắc chắn muốn xóa?",
//         text: "Hành động này không thể hoàn tác!",
//         icon: "warning",
//         showCancelButton: true,
//         confirmButtonColor: "#d33",
//         cancelButtonColor: "#3085d6",
//         confirmButtonText: "Xác nhận",
//         cancelButtonText: "Hủy"
//     }).then((result) => {
//         if (result.isConfirmed) {
//             form.submit();  // Nếu xác nhận thì submit form
//         }
//     });
// }

