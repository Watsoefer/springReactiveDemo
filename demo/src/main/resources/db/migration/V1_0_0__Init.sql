CREATE TABLE TBL_PORTFOLIO_POSTION
(
    ID           INT PRIMARY KEY AUTO_INCREMENT,
    PORTFOLIO_ID VARCHAR(16)    NOT NULL,
    ISIN         VARCHAR(12)    NOT NULL,
    AMOUNT       DECIMAL(10, 4) NOT NULL
);

-- view on table is used to simulate slow queries, the more results are returned by a select using e.g. the id, the longer the query takes
-- Mapping to entity uses this view instead of the real table
-- adapt sleep() to fit needs
create or replace view portfolio_position as
select p.*
from TBL_PORTFOLIO_POSTION p
where sleep(0.1) = 0;

-- Insert some test data
insert into TBL_PORTFOLIO_POSTION(portfolio_id, isin, amount)
values ('12345', 'DE0005785604', 13);
insert into TBL_PORTFOLIO_POSTION(portfolio_id, isin, amount)
values ( '12345', 'DE000A1DAHH0', 42);
insert into TBL_PORTFOLIO_POSTION(portfolio_id, isin, amount)
values ( '12345', 'NL0015002CX3', 25);

insert into TBL_PORTFOLIO_POSTION(portfolio_id, isin, amount)
values ('12346', 'DE0005785604', 13);
insert into TBL_PORTFOLIO_POSTION(portfolio_id, isin, amount)
values ('12346', 'DE000A1DAHH0', 42);
insert into TBL_PORTFOLIO_POSTION(portfolio_id, isin, amount)
values ('12346', 'NL0015002CX3', 25);
insert into TBL_PORTFOLIO_POSTION(portfolio_id, isin, amount)
values ('12346', 'DE0006048432', 456);
insert into TBL_PORTFOLIO_POSTION(portfolio_id, isin, amount)
values ('12346', 'DE000A1ML7J1', 11);
insert into TBL_PORTFOLIO_POSTION(portfolio_id, isin, amount)
values ('12346', 'DE000A1ML7J1', 7);
insert into TBL_PORTFOLIO_POSTION(portfolio_id, isin, amount)
values ('12346', 'DE0005785802', 88);
insert into TBL_PORTFOLIO_POSTION(portfolio_id, isin, amount)
values ('12346', 'DE0007236101', 1);
insert into TBL_PORTFOLIO_POSTION(portfolio_id, isin, amount)
values ('12346', 'DE0007236101', 125);