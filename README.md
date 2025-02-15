# Social Network Web Application (Spring Boot)

## Giới thiệu

Dự án này là một ứng dụng mạng xã hội được phát triển bằng Spring Boot, cho phép người dùng kết nối, chia sẻ và tương tác với nhau thông qua các bài viết, bình luận và tin nhắn. Đây là dự án do chính tôi "Phạm Văn Mẫn" lên ý tưởng và thiết kế, và làm cùng những người bạn của mình để nâng cao kỹ năng lập trình web và làm việc với các công nghệ liên quan.

## Tính năng chính

- Đăng ký, đăng nhập (bao gồm đăng nhập bằng Facebook)
- Đăng bài viết (bao gồm văn bản, hình ảnh)
- Kết bạn, theo dõi người dùng khác
- Bình luận, tương tác với bài viết
- Nhắn tin trực tiếp giữa người dùng
- Thay đổi thông tin cá nhân và ảnh đại diện
- Hệ thống thông báo khi có tương tác mới

## Công nghệ sử dụng

- Backend: Spring Boot, Spring Security, Websocket, Hibernate
- Frontend: Thymeleaf, HTML, CSS, JavaScript, Bootstrap, jQuery, Ajax
- Database: MySQL, Firebase
- Authentication: OAuth (Facebook)
- Realtime Messaging: WebSocket
- Email Service: JavaMail API

## Cài đặt và chạy dự án

1. Clone repository:
   ```bash
   git clone https://github.com/cacestudymodule4/case-study-module-4.git
   ```
2. Import project vào IDE (IntelliJ/Eclipse)
3. Cấu hình database MySQL và cập nhật thông tin kết nối trong file `application.properties`
4. Chạy project bằng lệnh:
   ```bash
   SpringApplication.run();
   ```
5. Truy cập website tại: `http://localhost:8080/`

## Cấu trúc thư mục

```
Social-Network-Project/
│── src/
│   ├── main/
│   │   ├── java/com/example/case_study_module_4/
│   │   │   ├── aspect/                 # Xử lý request lỗi
│   │   │   ├── config/                 # Config firebase, security, websocket
│   │   │   ├── controller/             # Controllers
│   │   │   ├── DTO/                    # Các model mang dữ liệu
│   │   │   ├── model/                  # Các class mô hình dữ liệu
│   │   │   ├── repository/             # Tầng truy vấn database
│   │   │   ├── restful/                # Các endpoit cho ajax call dữ liệu
│   │   │   ├── service/                # Business logic
│   │   ├── resources/
│   │   │   ├── static/                 # Hình ảnh, CSS, JS
│   │   │   ├── templates/              # Giao diện Thymeleaf
│   │   │   ├── application.properties  # Cấu hình ứng dụng
│── README.md
```

## Đóng góp

Nếu bạn muốn đóng góp vào dự án, vui lòng fork repository, tạo nhánh mới và gửi pull request.

## License

Dự án này được phát hành theo giấy phép MIT.

## Thông báo

Rất tiết là hiện tại firebase không còn free nữa nên là dự án này không có dữ liệu mẫu, và cũng không hoạt động được nữa.