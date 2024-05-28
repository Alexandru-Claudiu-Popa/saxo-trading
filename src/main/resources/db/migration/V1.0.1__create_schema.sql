create table ACCOUNTS
(
    NAME            VARCHAR(100) not null,
    SAXO_API_KEY    VARCHAR(50)  not null,
    SAXO_SECRET_KEY VARCHAR(25)  not null,
    DAILY_RISK      NUMERIC(15, 2)          not null,
    MAX_RISK        NUMERIC(15, 2)          not null,
    CREATED_TIME    TIMESTAMP              not null,
    MODIFIED_TIME   TIMESTAMP
);

create unique index ACCOUNTS_SAXO_SECRET_KEY_UINDEX
    on ACCOUNTS (SAXO_SECRET_KEY);

alter table ACCOUNTS
    add constraint ACCOUNTS_PK
        primary key (SAXO_SECRET_KEY);

create table ORDERS
(
    ID                   VARCHAR(36) not null,
    SAXO_SECRET_KEY      VARCHAR(25) not null,
    SYMBOL               VARCHAR(15) not null,
    BUY_SELL             VARCHAR(4)  not null,
    ORDER_TYPE           VARCHAR(10) not null,
    POSITION_SIZE        INTEGER               not null,
    STRATEGY             VARCHAR(20) not null,
    SL_PERCENT_PER_TRADE NUMERIC(4, 2)         not null,
    STATUS               VARCHAR(8)            not null,
    CREATED_TIME         TIMESTAMP             not null,
    constraint ORDERS_PK
        primary key (ID),
    constraint ORDERS_ACCOUNTS_SAXO_SECRET_KEY_FK
        foreign key (SAXO_SECRET_KEY) references ACCOUNTS
);

create table TRADES
(
    ID              VARCHAR(36) not null,
    SAXO_SECRET_KEY VARCHAR(25) not null,
    STATUS          VARCHAR(6)  not null,
    BUY_SELL_PRICE  NUMERIC(12, 2)        not null,
    CLOSE_PRICE     NUMERIC(12, 2),
    OPEN_DATE_TIME  TIMESTAMP             not null,
    CLOSE_DATE_TIME TIMESTAMP,
    ORDER_ID        VARCHAR(36) not null,
    constraint TRADES_PK
        primary key (ID),
    constraint TRADES_ACCOUNTS_SAXO_SECRET_KEY_FK
        foreign key (SAXO_SECRET_KEY) references ACCOUNTS,
    constraint TRADES_ORDERS_ID_FK
        foreign key (ORDER_ID) references ORDERS
);