# Test Plan cho ứng dụng Telephony cải tiến

## 1. Test Quyền (Permissions)

### ✅ Test quyền đọc danh bạ

- [ ] Mở ứng dụng lần đầu
- [ ] Kiểm tra dialog yêu cầu quyền READ_CONTACTS
- [ ] Chấp nhận quyền
- [ ] Kiểm tra danh bạ được load

### ✅ Test quyền cuộc gọi

- [ ] Nhấn vào icon cuộc gọi trực tiếp
- [ ] Kiểm tra dialog yêu cầu quyền CALL_PHONE
- [ ] Chấp nhận quyền
- [ ] Kiểm tra cuộc gọi được thực hiện

### ✅ Test quyền SMS

- [ ] Kiểm tra quyền RECEIVE_SMS được yêu cầu
- [ ] Test gửi SMS từ ứng dụng khác
- [ ] Kiểm tra SMS Receiver hoạt động

## 2. Test Đọc danh bạ

### ✅ Test load danh bạ

- [ ] Kiểm tra tất cả liên hệ được hiển thị
- [ ] Kiểm tra thông tin nhà mạng được hiển thị đúng
- [ ] Kiểm tra thống kê tự động hiển thị

### ✅ Test hiển thị thông tin nhà mạng

- [ ] Kiểm tra format: "Số điện thoại (Tên nhà mạng)"
- [ ] Test với các số Viettel: 086, 096, 097, 098, 032-039
- [ ] Test với các số MobiFone: 089, 090, 093, 070-079
- [ ] Test với các số Vinaphone: 088, 091, 094, 081-085, 087
- [ ] Test với các số Vietnamobile: 092, 056, 058
- [ ] Test với các số Gmobile: 099, 059
- [ ] Test với số không xác định

## 3. Test Menu Lọc

### ✅ Test menu "Tất cả"

- [ ] Chọn menu "Tất cả"
- [ ] Kiểm tra tất cả liên hệ được hiển thị
- [ ] Kiểm tra thông báo "Hiển thị tất cả danh bạ"

### ✅ Test menu "Thống kê nhà mạng"

- [ ] Chọn menu "Thống kê nhà mạng"
- [ ] Kiểm tra thống kê hiển thị đúng
- [ ] Kiểm tra format thống kê

### ✅ Test lọc Viettel

- [ ] Chọn menu "Lọc Viettel"
- [ ] Kiểm tra chỉ hiển thị số Viettel
- [ ] Kiểm tra thông báo số lượng

### ✅ Test lọc MobiFone

- [ ] Chọn menu "Lọc MobiFone"
- [ ] Kiểm tra chỉ hiển thị số MobiFone
- [ ] Kiểm tra thông báo số lượng

### ✅ Test lọc Vinaphone

- [ ] Chọn menu "Lọc Vinaphone"
- [ ] Kiểm tra chỉ hiển thị số Vinaphone
- [ ] Kiểm tra thông báo số lượng

### ✅ Test lọc Vietnamobile

- [ ] Chọn menu "Lọc Vietnamobile"
- [ ] Kiểm tra chỉ hiển thị số Vietnamobile
- [ ] Kiểm tra thông báo số lượng

### ✅ Test lọc Gmobile

- [ ] Chọn menu "Lọc Gmobile"
- [ ] Kiểm tra chỉ hiển thị số Gmobile
- [ ] Kiểm tra thông báo số lượng

### ✅ Test lọc nhà mạng khác

- [ ] Chọn menu "Lọc nhà mạng khác"
- [ ] Kiểm tra chỉ hiển thị số không thuộc các nhà mạng chính
- [ ] Kiểm tra thông báo số lượng

## 4. Test Chức năng cuộc gọi

### ✅ Test cuộc gọi trực tiếp

- [ ] Nhấn vào icon cuộc gọi trực tiếp
- [ ] Kiểm tra cuộc gọi được thực hiện ngay
- [ ] Test với các loại số khác nhau

### ✅ Test cuộc gọi quay số

- [ ] Nhấn vào icon quay số
- [ ] Kiểm tra bàn phím số hiển thị
- [ ] Kiểm tra số được điền sẵn

### ✅ Test cuộc gọi từ click item

- [ ] Nhấn vào item trong list
- [ ] Kiểm tra cuộc gọi được thực hiện

## 5. Test Chức năng SMS

### ✅ Test gửi SMS

- [ ] Nhấn vào icon SMS
- [ ] Kiểm tra màn hình SendSMSActivity mở
- [ ] Kiểm tra thông tin liên hệ được truyền đúng

### ✅ Test nhận SMS

- [ ] Gửi SMS từ thiết bị khác
- [ ] Kiểm tra SMS Receiver hoạt động
- [ ] Kiểm tra thông báo hiển thị đúng

## 6. Test Giao diện

### ✅ Test layout

- [ ] Kiểm tra layout hiển thị đúng trên các kích thước màn hình
- [ ] Kiểm tra các icon hiển thị đúng
- [ ] Kiểm tra text hiển thị đúng

### ✅ Test menu

- [ ] Kiểm tra menu hiển thị đầy đủ các option
- [ ] Kiểm tra tên menu đúng
- [ ] Kiểm tra thứ tự menu hợp lý

## 7. Test Performance

### ✅ Test hiệu suất

- [ ] Kiểm tra thời gian load danh bạ
- [ ] Kiểm tra thời gian lọc
- [ ] Kiểm tra memory usage

## 8. Test Error Handling

### ✅ Test xử lý lỗi

- [ ] Test khi không có quyền
- [ ] Test khi danh bạ trống
- [ ] Test khi không có kết nối mạng
- [ ] Test với số điện thoại không hợp lệ

## Kết quả mong đợi

Sau khi test, ứng dụng phải:

- ✅ Load được danh bạ từ điện thoại
- ✅ Hiển thị đúng thông tin nhà mạng
- ✅ Lọc được theo từng nhà mạng
- ✅ Thực hiện được cuộc gọi
- ✅ Gửi được SMS
- ✅ Nhận được SMS
- ✅ Hiển thị thống kê chính xác
- ✅ Giao diện thân thiện và dễ sử dụng
