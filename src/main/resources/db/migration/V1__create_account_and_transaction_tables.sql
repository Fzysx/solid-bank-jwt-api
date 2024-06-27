CREATE TABLE Users
(
    id BIGINT AUTO_INCREMENT,
    username nvarchar(30) NOT NULL UNIQUE,
    password nvarchar(80) NOT NULL,
    CONSTRAINT PK_Users PRIMARY KEY (id)
);

CREATE TABLE Roles
(
    id INT AUTO_INCREMENT,
    name nvarchar(50),
    CONSTRAINT PK_Roles PRIMARY KEY (id)
);

CREATE TABLE Users_roles
(
    user_id BIGINT NOT NULL,
    role_id INT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES Users (id),
    FOREIGN KEY (role_id) REFERENCES Roles (id)
);

CREATE TABLE Accounts
(
    id NVARCHAR(20) NOT NULL UNIQUE,
    account_type NVARCHAR(20)  NOT NULL,
    client_id NVARCHAR(20) NOT NULL,
    balance DOUBLE,
    is_withdraw_allowed BOOLEAN,
    CONSTRAINT PK_Accounts PRIMARY KEY (id),
    FOREIGN KEY (client_id) REFERENCES Users (id) ON DELETE CASCADE
);

CREATE TABLE Transactions
(
    id NVARCHAR(10) NOT NULL UNIQUE,
    account_id NVARCHAR(20) NOT NULL,
    amount DOUBLE,
    transaction_type NVARCHAR(20)  NOT NULL,
    account_from_id NVARCHAR(20)  NOT NULL,
    account_to_id NVARCHAR(20)  NOT NULL,
    balance_before DOUBLE,
    balance_after DOUBLE,
    CONSTRAINT PK_Transactions PRIMARY KEY (id),
    FOREIGN KEY (account_id) REFERENCES Accounts (id) ON DELETE CASCADE
);

insert into Roles (name)
values
    ('ROLE_USER');

insert into users (username, password)
values
    ('user', '$2a$10$BHYGjTxsiLQCtAExROi.F.8PxvwbN421oILeXoS718hxbqroC3n16');

insert into users_roles(user_id, role_id)
values
    (1, 1);
