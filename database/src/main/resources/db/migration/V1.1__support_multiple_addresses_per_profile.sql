ALTER TABLE address
    ADD COLUMN profile_id VARCHAR(36);
ALTER TABLE address
    ADD COLUMN main BOOLEAN NOT NULL DEFAULT TRUE;

ALTER TABLE address
    ADD FOREIGN KEY (profile_id) REFERENCES customer_profile (id);
CREATE INDEX address_customer_profile_id ON address (profile_id);

UPDATE address SET main = TRUE, profile_id = (SELECT id FROM customer_profile WHERE customer_profile.address_id = address.id);
