CREATE TABLE IF NOT EXISTS PaymentMethod (
    Id INTEGER PRIMARY KEY AUTOINCREMENT,
    MethodName TEXT NOT NULL,
    Description TEXT
);

INSERT INTO PaymentMethod (MethodName, Description) VALUES 
('Cash', 'Thanh toán bằng tiền mặt'),
('Credit Card', 'Thanh toán bằng thẻ tín dụng'),
('Debit Card', 'Thanh toán bằng thẻ ghi nợ'),
('Bank Transfer', 'Chuyển khoản ngân hàng'),
('E-Wallet', 'Ví điện tử'); 