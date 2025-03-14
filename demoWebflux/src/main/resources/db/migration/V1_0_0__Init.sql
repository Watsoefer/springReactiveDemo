CREATE TABLE TBL_PORTFOLIO_POSTION
(
    ID           INT,
    PORTFOLIO_ID VARCHAR(16)    NOT NULL,
    ISIN         VARCHAR(12)    NOT NULL,
    AMOUNT       DECIMAL(10, 4) NOT NULL
);

insert into TBL_PORTFOLIO_POSTION
values (1, '12345', 'DE0005785604', 13);
insert into TBL_PORTFOLIO_POSTION
values (1, '12345', 'DE000A1DAHH0', 42);
insert into TBL_PORTFOLIO_POSTION
values (1, '12345', 'NL0015002CX3', 25);

insert into TBL_PORTFOLIO_POSTION
values (1, '12346', 'DE0005785604', 13);
insert into TBL_PORTFOLIO_POSTION
values (1, '12346', 'DE000A1DAHH0', 42);
insert into TBL_PORTFOLIO_POSTION
values (1, '12346', 'NL0015002CX3', 25);
insert into TBL_PORTFOLIO_POSTION
values (1, '12346', 'DE0006048432', 456);
insert into TBL_PORTFOLIO_POSTION
values (1, '12346', 'DE000A1ML7J1', 11);
insert into TBL_PORTFOLIO_POSTION
values (1, '12346', 'DE000A1ML7J1', 7);
insert into TBL_PORTFOLIO_POSTION
values (1, '12346', 'DE0005785802', 88);
insert into TBL_PORTFOLIO_POSTION
values (1, '12346', 'DE0007236101', 1);
insert into TBL_PORTFOLIO_POSTION
values (1, '12346', 'DE0007236101', 125);



create or replace view portfolio_position as
select p.*
from TBL_PORTFOLIO_POSTION p
where sleep(0.1) = 0;