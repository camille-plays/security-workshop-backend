-- Create table transfers
CREATE TABLE transfers
(
    transfer_id            uuid PRIMARY KEY DEFAULT gen_random_uuid(),
    amount                 NUMERIC(20, 2) NOT NULL, -- BigDecimal amount with precision 20 and scale 2
    source_balance_id      uuid,
    destination_balance_id uuid
);