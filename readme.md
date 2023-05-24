# Basic Concepts

entity로 객체 만들고, 하위 구성요소 포함한다.  
repository로 엔티티에 의해 생성된 데이터베이스 테이블에 접근한다.  
service는 스프링에서 데이터 처리를 위해 작성하는 클래스이다.

# DB

1. user

```
create table user(
    user_id VARCHAR(20) PRIMARY KEY,
    user_pw VARCHAR(20) NOT NULL,
    user_name VARCHAR(20) NOT NULL,
    email VARCHAR(30) NOT NULL UNIQUE,
    birthdate DATE NOT NULL,
    user_num INT AUTO_INCREMENT UNIQUE
);
```

기본적으로 다음 값을 넣어둔다.

```
    user_id     user_pw     user_name    email               birthdate   user_num
	root	    1111	    admin	    admin@gmail.com	    2023-05-07	1
```

2. book

```
create table book(
	book_code INT PRIMARY KEY,
	book_title VARCHAR(255) not null,
	book_author varchar(255) not null,
	book_publisher varchar(255) not null,
	book_borrow boolean not null,
	book_lang varchar(255) not null,
	book_type varchar(255) not null,
	book_year INT not null,
	book_summary varchar(255) not null,
	book_ISBN INT not null
);

```

3. borrow

```
create table borrow(
	book_code INT primary key,
    user_num INT,
    start_date date,
    end_date date,
    FOREIGN KEY (book_code) references book (book_code),
    FOREIGN KEY (user_num) references user (user_num)
);
```

4. reservation

```
create table reservation(
	book_code INT,
    user_num INT,
    date datetime,
    FOREIGN KEY (book_code) references book (book_code),
    FOREIGN KEY (user_num) references user (user_num),
    primary key(book_code, date)
);
```

book_DB.csv 파일을 import 했다.

# SpringBoot

## Controller

1. LoginController

```

```
