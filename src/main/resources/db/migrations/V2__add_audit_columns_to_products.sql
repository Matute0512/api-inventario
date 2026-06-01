-- Add audit columns to products table to support JPA Auditing (@CreatedDate, @LastModifiedDate)
-- DATETIME(6) is required for compatibility with Java's LocalDateTime precision
ALTER TABLE products
    ADD COLUMN created_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    ADD COLUMN updated_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6);