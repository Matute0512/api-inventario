-- Add index on products.name to optimize search queries by product name
CREATE INDEX idx_product_name ON products(name);