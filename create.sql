create table p (
    id bigint not null,

)create sequence hibernate_sequence start with 1 increment by 1
create table author (id varchar(255) not null, born_date date, dye_date date, name varchar(255) not null, primary key (id))
create table author_books (author_id varchar(255) not null, books_id integer not null)
create table book_table (id integer not null, finish_date date, genre integer, is_available boolean, name varchar(255) not null, publication_date integer, start_date date, author_id varchar(255), publisher_id varchar(255), primary key (id))
create table p (id integer not null, primary key (id))
create table publisher (id varchar(255) not null, established_date date, name varchar(255), primary key (id))
create table publisher_book_list (publisher_id varchar(255) not null, book_list_id integer not null)
create table user (id varchar(255) not null, name varchar(255), pass varchar(255), primary key (id))
create table user_book_list (user_id varchar(255) not null, book_list_id integer not null)
alter table author add constraint UK_or6k6jmywerxbme223c988bmg unique (name)
alter table author_books add constraint UK_fxksjqa1a5dnqf0egcdxlrcna unique (books_id)
alter table book_table add constraint UK_refg885os3jko5akcgaiuttu5 unique (name)
alter table publisher add constraint UK_h9trv4xhmh6s68vbw9ba6to70 unique (name)
alter table publisher_book_list add constraint UK_o52eqygdolu88tx5xe54snl8b unique (book_list_id)
alter table user add constraint UK_gj2fy3dcix7ph7k8684gka40c unique (name)
alter table user_book_list add constraint UK_srv5g5m5cf24t3tjwa3ffpagt unique (book_list_id)
alter table author_books add constraint FK4ufn972e4brmxu4xkc922py1a foreign key (books_id) references book_table
alter table author_books add constraint FKfvabqdr9njwv4khjqkf1pbmma foreign key (author_id) references author
alter table book_table add constraint FKldb8futb8s5xaf109r782t1b foreign key (author_id) references author
alter table book_table add constraint FK7q606h0c2lyvsswahgaiyx9ta foreign key (publisher_id) references publisher
alter table publisher_book_list add constraint FKl49iypm53xix4lsi5uj1r14g3 foreign key (book_list_id) references book_table
alter table publisher_book_list add constraint FKabpi1wnm7x4mr3eaugppcqr98 foreign key (publisher_id) references publisher
alter table user_book_list add constraint FKphp1mcr89fyvtipw9o6fqsday foreign key (book_list_id) references book_table
alter table user_book_list add constraint FK26j6vct48cm0sw989sjttwm6b foreign key (user_id) references user
