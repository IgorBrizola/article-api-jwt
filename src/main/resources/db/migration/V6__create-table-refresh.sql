CREATE TABLE refresh_token (
    id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
    token NVARCHAR(255) NOT NULL UNIQUE,
    username NVARCHAR(255) NOT NULL,
    date_created DATETIME2 DEFAULT GETDATE()
);
