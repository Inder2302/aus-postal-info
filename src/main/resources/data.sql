DROP TABLE IF EXISTS suburb;
DROP TABLE IF EXISTS USER;
create table suburb (
    suburb_uuid VARCHAR(50) PRIMARY KEY,
    post_code Integer,
    suburb_name VARCHAR(100),
    suburb_state_abbr VARCHAR(4)
);

create table user (
    id Integer,
    username VARCHAR(40),
    password VARCHAR(100),
    active VARCHAR(5),
    roles VARCHAR(50)
);
