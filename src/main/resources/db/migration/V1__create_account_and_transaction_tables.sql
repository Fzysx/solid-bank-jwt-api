CREATE TABLE Account
(
    id NVARCHAR(10) NOT NULL,
    account_type NVARCHAR(20)  NOT NULL,
    client_id NVARCHAR(10) NOT NULL,
    balance DOUBLE,
    is_withdraw_allowed BOOLEAN,
    CONSTRAINT PK_Account PRIMARY KEY  (id)
);

CREATE TABLE Transaction
(
    id NVARCHAR(10) NOT NULL,
    account_id NVARCHAR(10) NOT NULL,
    client_id NVARCHAR(10) NOT NULL,
    amount DOUBLE,
    transaction_type NVARCHAR(20)  NOT NULL,
    balance_before DOUBLE,
    balance_after DOUBLE,
    CONSTRAINT PK_Invoice PRIMARY KEY  (id),
    FOREIGN KEY (account_id) REFERENCES Account (id) ON DELETE SET NULL
);
