-- Create payment_details table
CREATE TABLE payment_details (
    id VARCHAR(36) PRIMARY KEY,
    merchant_id VARCHAR(36) NOT NULL,
    merchant_payment_charge_reference VARCHAR(255) NOT NULL,
    payment_method_type VARCHAR(50) NOT NULL,
    amount DECIMAL(19, 4) NOT NULL,
    currency VARCHAR(3) NOT NULL,
    description TEXT,
    return_url VARCHAR(2048),
    status VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    
    gateway_transaction_id VARCHAR(255),
    gateway_response TEXT,
    error_code VARCHAR(100),
    error_message TEXT,
    
    CONSTRAINT uk_merchant_payment_reference UNIQUE (merchant_id, merchant_payment_charge_reference)
);

-- Create index for searching by merchant_id
CREATE INDEX idx_payment_details_merchant_id ON payment_details(merchant_id);

-- Create index for searching by status
CREATE INDEX idx_payment_details_status ON payment_details(status);

-- Create index for searching by created_at (for reporting)
CREATE INDEX idx_payment_details_created_at ON payment_details(created_at);