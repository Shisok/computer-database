

  drop table if exists COMPUTER;
  drop table if exists COMPANY;

  create table COMPANY (
    `id`                        bigint not null AUTO_INCREMENT,
    `name`                      varchar(255),
     PRIMARY KEY (`id`))
  ;

  create table COMPUTER (
    `id`                        bigint not null AUTO_INCREMENT,
    `name`                      varchar(255),
    `introduced`                date NULL,
    `discontinued`              date NULL,
    `company_id`                bigint default NULL,
     PRIMARY KEY (`id`) ,
    constraint `fk_computer_company_1` foreign key (`company_id`) references COMPANY (`id`) on delete restrict on update restrict)
  ;

 
