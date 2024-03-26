-- Create table balances
CREATE TABLE balances
(
    balance_id      uuid PRIMARY KEY DEFAULT gen_random_uuid(),
    amount  NUMERIC(20, 2) NOT NULL, -- BigDecimal amount with precision 20 and scale 2
    user_id INT            NOT NULL
);

-- Create index on user_id for faster lookup
CREATE INDEX idx_user_id ON balances (user_id);