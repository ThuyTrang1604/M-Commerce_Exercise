# Hướng dẫn sử dụng ứng dụng Telephony cải tiến

## Tính năng chính

### 1. Đọc danh bạ từ điện thoại

- Ứng dụng sẽ yêu cầu quyền đọc danh bạ khi khởi động
- Tự động load tất cả liên hệ từ danh bạ điện thoại
- Hiển thị thống kê số lượng liên hệ theo từng nhà mạng

### 2. Hiển thị thông tin nhà mạng

- Mỗi liên hệ sẽ hiển thị: Tên + Số điện thoại (Tên nhà mạng)
- Ví dụ: "Nguyễn Văn A" + "0123456789 (Viettel)"

### 3. Thực hiện cuộc gọi

- **Cuộc gọi trực tiếp**: Nhấn vào icon cuộc gọi trực tiếp
- **Cuộc gọi quay số**: Nhấn vào icon quay số (hiển thị bàn phím số)
- **Gửi SMS**: Nhấn vào icon SMS để gửi tin nhắn

### 4. Lọc theo nhà mạng (Option Menu)

Từ menu chính, bạn có thể lọc liên hệ theo các nhà mạng:

#### Các nhà mạng được hỗ trợ:

**Viettel**

- Đầu số: 086, 096, 097, 098, 032, 033, 034, 035, 036, 037, 038, 039

**MobiFone**

- Đầu số: 089, 090, 093, 070, 071, 072, 073, 074, 075, 076, 077, 078, 079

**Vinaphone**

- Đầu số: 088, 091, 094, 081, 082, 083, 084, 085, 087

**Vietnamobile**

- Đầu số: 092, 056, 058

**Gmobile**

- Đầu số: 099, 059

**Itelecom**

- Đầu số: 087

**Nhà mạng khác**

- Tất cả số không thuộc các nhà mạng trên

### 5. Thống kê nhà mạng

- Chọn "Thống kê nhà mạng" từ menu để xem số lượng liên hệ theo từng nhà mạng
- Thống kê tự động hiển thị khi load danh bạ lần đầu

## Cách sử dụng

1. **Khởi động ứng dụng**: Mở ứng dụng và cấp quyền đọc danh bạ
2. **Xem danh bạ**: Tất cả liên hệ sẽ được hiển thị với thông tin nhà mạng
3. **Lọc liên hệ**:
   - Nhấn vào menu (3 chấm) ở góc trên bên phải
   - Chọn nhà mạng muốn lọc
   - Ứng dụng sẽ chỉ hiển thị liên hệ thuộc nhà mạng đó
4. **Thực hiện cuộc gọi**:
   - Nhấn vào icon cuộc gọi trực tiếp để gọi ngay
   - Nhấn vào icon quay số để hiển thị bàn phím số
5. **Gửi SMS**: Nhấn vào icon SMS để mở màn hình gửi tin nhắn

## Quyền cần thiết

- `READ_CONTACTS`: Đọc danh bạ
- `CALL_PHONE`: Thực hiện cuộc gọi
- `SEND_SMS`: Gửi tin nhắn

## Lưu ý

- Ứng dụng chỉ hoạt động với số điện thoại Việt Nam
- Cần có quyền đọc danh bạ để sử dụng tính năng
- Thống kê được tính dựa trên 3 chữ số đầu của số điện thoại
