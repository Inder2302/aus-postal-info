DROP TABLE IF EXISTS suburb;
create table suburb (
    suburb_uuid VARCHAR(50) PRIMARY KEY,
    post_code Integer,
    suburb_name VARCHAR(100),
    suburb_state_abbr VARCHAR(4)
);