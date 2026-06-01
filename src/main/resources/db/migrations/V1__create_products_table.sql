-- Initial creation of the products table
CREATE TABLE products (
                          id          BIGINT          NOT NULL AUTO_INCREMENT,
                          name        VARCHAR(100)    NOT NULL,
                          description VARCHAR(255),
                          price       DECIMAL(10, 2)  NOT NULL,
                          stock       INT             NOT NULL,

                          CONSTRAINT pk_products PRIMARY KEY (id)
);