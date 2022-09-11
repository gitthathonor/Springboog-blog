# MyBatis DB연결 세팅

### 설정방법
- MyBatisConfig 파일 필요
- resources/mapper/*.xml 파일 필요
- Users 엔티티 필요
- UsersDao 인터페이스 생성 필요

### 테이블 생성
```sql
create table users(
    id number primary key,
    username varchar2(20),
    password varchar2(20),
    email varchar2(50),
    createdAt TIMESTAMP
);

CREATE SEQUENCE users_seq 
INCREMENT BY 1 
START WITH 1;


create table boards(
    id number primary key,
    title varchar2(150),
    content clob,
    usersId number,
    createdAt TIMESTAMP,
    CONSTRAINT fk_users_id FOREIGN KEY(usersId) REFERENCES users(id)
);

CREATE SEQUENCE boards_seq 
INCREMENT BY 1 
START WITH 1;

```

### 더미데이터 추가
```sql
insert into users(id, username, password, email, createdAt) values(users_seq.nextval, 'ssar', '1234', 'ssar@nate.com', sysdate);
insert into users(id, username, password, email, createdAt) values(users_seq.nextval, 'cos', '1234', 'cos@nate.com', sysdate);
insert into users(id, username, password, email, createdAt) values(users_seq.nextval, 'hong', '1234', 'hong@nate.com', sysdate);
commit;


INSERT into boards VALUES(boards_seq.nextval, 'spring1', 'spring is so hard', 1, sysdate);
INSERT into boards VALUES(boards_seq.nextval, 'spring2', 'controller can make methods to transfer context...', 2, sysdate);
INSERT into boards VALUES(boards_seq.nextval, 'winter', 'winter is so cold', 2, sysdate);
INSERT into boards VALUES(boards_seq.nextval, 'summer', 'summer is so hot', 3, sysdate);
INSERT into boards VALUES(boards_seq.nextval, 'fall', 'fall is the season of man', 3, sysdate);
INSERT into boards VALUES(boards_seq.nextval, 'Servlet', 'Sevlte, which makes...', 3, sysdate);
commit;


```