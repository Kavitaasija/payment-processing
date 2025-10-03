CREATE TABLE payment_event_history (
    id VARCHAR(36) PRIMARY KEY,
    payment_id VARCHAR(36) NOT NULL,
    event_type VARCHAR(50) NOT NULL,
    event_status VARCHAR(50) NOT NULL,
    event_timestamp TIMESTAMP NOT NULL,
    event_data JSONB,
    created_by VARCHAR(255),
    previous_status VARCHAR(50),
    new_status VARCHAR(50),
    gateway_response TEXT,
    error_code VARCHAR(100),
    error_message TEXT,
    
    CONSTRAINT fk_payment_event_payment_details
        FOREIGN KEY (payment_id)
        REFERENCES payment_details (id)
        ON DELETE CASCADE
);

-- Create index for searching by payment_id
CREATE INDEX idx_payment_event_payment_id ON payment_event_history(payment_id);

-- Create index for searching by event_type
CREATE INDEX idx_payment_event_type ON payment_event_history(event_type);

-- Create index for searching by event_timestamp (for reporting)
CREATE INDEX idx_payment_event_timestamp ON payment_event_history(event_timestamp);

-- Create index for searching by event_status
CREATE INDEX idx_payment_event_status ON payment_event_history(event_status);

-- Comments explaining event types
COMMENT ON TABLE payment_event_history IS 'Tracks all events related to payment processing';
COMMENT ON COLUMN payment_event_history.event_type IS 'Types include: AUTHORIZATION_REQUESTED, AUTHORIZATION_COMPLETED, CAPTURE_REQUESTED, CAPTURE_COMPLETED, REFUND_REQUESTED, REFUND_COMPLETED, VOID_REQUESTED, VOID_COMPLETED';
COMMENT ON COLUMN payment_event_history.event_status IS 'Status values: SUCCESS, FAILED, PENDING, PROCESSING';
COMMENT ON COLUMN payment_event_history.event_data IS 'JSON data containing event-specific details';