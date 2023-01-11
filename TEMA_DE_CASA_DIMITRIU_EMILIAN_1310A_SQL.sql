drop table comenzi;
drop table client;
drop table magazin;
drop table localitate;
drop table produse;

CREATE TABLE client (
    client_id       NUMBER GENERATED ALWAYS as IDENTITY(START with 1 INCREMENT by 1),
    client_nume VARCHAR2(30 CHAR) NOT NULL,
    
    CONSTRAINT client_id_pk PRIMARY KEY(client_id)
);

CREATE TABLE localitate (
    localitate_id  NUMBER GENERATED ALWAYS as IDENTITY(START with 200 INCREMENT by 1),
    localitate_nume VARCHAR2(25 CHAR) NOT NULL,
    CONSTRAINT localitate_id_pk PRIMARY KEY(localitate_id)
);

CREATE TABLE magazin (
   
    magazin_id               NUMBER GENERATED ALWAYS as IDENTITY(START with 200 INCREMENT by 1),
    localitate_id            NUMBER NOT NULL,
    CONSTRAINT localitate_id_FK foreign key(localitate_id) REFERENCES localitate(localitate_id),
    CONSTRAINT magazin_id_PK primary key(magazin_id)
);

CREATE TABLE produse (
    produs_id             NUMBER GENERATED ALWAYS as IDENTITY(START with 100 INCREMENT by 1),
    produs_denumire        VARCHAR2(25 CHAR) NOT NULL,
    STOC                   NUMBER,
    PRET                  NUMBER,
    CONSTRAINT produs_id_PK PRIMARY KEY(produs_id)
);

CREATE TABLE comenzi (
    comanda_id            NUMBER GENERATED ALWAYS as IDENTITY(START with 100 INCREMENT by 1),
    magazin_id            NUMBER NOT NULL,
    client_id             NUMBER NOT NULL,
    produs_id             NUMBER NOT NULL,
    comanda_date          DATE NOT NULL,
    pret                  NUMBER NOT NULL,
    CONSTRAINT magazin_id_FK foreign key(magazin_id) REFERENCES magazin(magazin_id),
    CONSTRAINT client_id_FK foreign key(client_id) REFERENCES client(client_id),
    CONSTRAINT comanda_id_PK PRIMARY KEY (comanda_id),
    CONSTRAINT pret_ck CHECK ( pret > 0),
    CONSTRAINT produs_id_FK foreign key(produs_id) REFERENCES produse(produs_id)
);

INSERT into localitate(localitate_nume) VALUES('Brasov');
INSERT into localitate(localitate_nume) VALUES('Pascani');
INSERT into localitate(localitate_nume) VALUES('Targu Frumos');
INSERT into localitate(localitate_nume) VALUES('Harlau');
INSERT into localitate(localitate_nume) VALUES('Iasi');

INSERT into client(client_nume) VALUES('Dimitriu');
INSERT into client(client_nume) VALUES('Popescu');
INSERT into client(client_nume) VALUES('Matache');
INSERT into client(client_nume) VALUES('Nicolae');
INSERT into client(client_nume) VALUES('Bucovina');

INSERT into magazin(localitate_id) SELECT localitate_id FROM localitate;

INSERT into produse(produs_denumire,stoc,pret) VALUES('geam simplu',5,500);
INSERT into produse(produs_denumire,stoc,pret) VALUES('usa simpla',10,800);
INSERT into produse(produs_denumire,stoc,pret) VALUES('geam dublu',55,900);
INSERT into produse(produs_denumire,stoc,pret) VALUES('geam triplu',35,1200);
INSERT into produse(produs_denumire,stoc,pret) VALUES('usa dubla',14,1500);

INSERT into comenzi(magazin_id,client_id,produs_id,comanda_date,pret) VALUES(203,1,100,sysdate,900);
INSERT into comenzi(magazin_id,client_id,produs_id,comanda_date,pret) VALUES(202,2,101,sysdate,1500);
INSERT into comenzi(magazin_id,client_id,produs_id,comanda_date,pret) VALUES(201,3,102,sysdate,1200);
INSERT into comenzi(magazin_id,client_id,produs_id,comanda_date,pret) VALUES(204,4,103,sysdate,800);
INSERT into comenzi(magazin_id,client_id,produs_id,comanda_date,pret) VALUES(200,5,104,sysdate,500);

commit;

UPDATE COMENZI set pret = 900 WHERE comanda_id = 102;




select * from comenzi;
select * from client;
select * from localitate;
select * from magazin;
select * from produse;