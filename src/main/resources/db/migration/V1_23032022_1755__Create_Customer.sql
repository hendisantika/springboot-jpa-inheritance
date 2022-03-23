create table customer
(
    id                bigint              not null auto_increment,
    is_active         bit(1) default b'1' not null,
    source_identifier varchar(255),
    valid_from        datetime(6) not null,
    valid_to          datetime(6),
    address           varchar(255)        not null,
    type              varchar(255)        not null,
    full_name         varchar(255)        not null,
    primary key (id)
) engine=InnoDB;