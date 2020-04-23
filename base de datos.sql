create database AbpDataBase;
use AbpDataBase;
create table usuario(
    nombreUser varchar(40) not null primary key,
    contrase varchar(64) not null,
    tipo varchar(15) not null
);

DROP TABLE usuario

create table grupos(
    codigo INT AUTO_INCREMENT,
    AccessCode varchar(6) NOT NULL,
    nombre varchar (30) NOT NULL,
    profesor varchar(40) NOT NULL,
    primary KEY(codigo) NOT NULL,
    foreign key (profesor) REFERENCES usuario(nombreUser)
);
    
DROP TABLE grupos
ALTER TABLE grupos AUTO_INCREMENT =1

create table estudiantes_grupos(
    codi int  auto_increment,
    codigoGrupo int(3) not null,
    estudiante varchar(40) not null,
    nota double not null, 
    primary KEY(codi),
    foreign key(codigoGrupo) REFERENCES grupos(codigo),
    foreign key(estudiante) REFERENCES usuario(nombreUser)
); 

DROP table estudiantes_grupos

SELECT * FROM usuario
INSERT INTO usuario VALUES("marian","12345","Profesor")

INSERT INTO grupos VALUES(0,"12","nor","marian")


SELECT * FROM grupos;