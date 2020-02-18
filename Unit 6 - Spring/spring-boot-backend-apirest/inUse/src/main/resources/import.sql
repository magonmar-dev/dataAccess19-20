/* Populate tabla clientes */
INSERT INTO clientes (nombre, apellido, email, create_at) VALUES('María','González','maria@iessanvicente.com','2020-01-30');
INSERT INTO clientes (nombre, apellido, email, create_at) VALUES('Mr.John','Doe','john.doe@gmail.com','2018-01-02');
INSERT INTO clientes (nombre, apellido, email, create_at) VALUES('Linus','Torvalds','linus.torvalds@gmail.com','2018-01-03');
INSERT INTO clientes (nombre, apellido, email, create_at) VALUES('Rasmus','Lerdorf','rasmus.lerdorf@gmail.com','2018-01-04');
INSERT INTO clientes (nombre, apellido, email, create_at) VALUES('Erich','Gamma','erich.gamma@gmail.com','2018-02-01');
INSERT INTO clientes (nombre, apellido, email, create_at) VALUES('Richard','Helm','richard.helm@gmail.com','2018-02-10');
INSERT INTO clientes (nombre, apellido, email, create_at) VALUES('Ralph','Johnson','ralph.johnson@gmail.com','2018-02-18');
INSERT INTO clientes (nombre, apellido, email, create_at) VALUES('John','Vlissides','john.vlissides@gmail.com','2018-02-28');
INSERT INTO clientes (nombre, apellido, email, create_at) VALUES('Dr.James','Gosling','james.gosling@gmail.com','2018-03-03');
INSERT INTO clientes (nombre, apellido, email, create_at) VALUES('Magma','Lee','magma.lee@gmail.com','2018-03-04');
INSERT INTO clientes (nombre, apellido, email, create_at) VALUES('Tornado','Roe','tornado.roe@gmail.com','2018-03-05');
INSERT INTO clientes (nombre, apellido, email, create_at) VALUES('Jade','Doe','jane.doe@gmail.com','2018-03-06');

/* Populate tabla productos */

INSERT INTO productos (descripcion, precio, fecha_alta, disponibilidad) VALUES('Placa base',50.55,'2020-02-01',true);
INSERT INTO productos (descripcion, precio, fecha_alta, disponibilidad) VALUES('Procesador',599.99,'2020-02-02',false);
INSERT INTO productos (descripcion, precio, fecha_alta, disponibilidad) VALUES('Memoria SSD',125.60,'2020-02-03',true);
INSERT INTO productos (descripcion, precio, fecha_alta, disponibilidad) VALUES('Memoria RAM',45.99,'2020-02-04',true);
INSERT INTO productos (descripcion, precio, fecha_alta, disponibilidad) VALUES('Grabadora',20.50,'2020-02-04',false);
INSERT INTO productos (descripcion, precio, fecha_alta, disponibilidad) VALUES('Tarjeta Gráfica',1068.88,'2020-02-04',true);

/* Populate tabla compras */

INSERT INTO productos_clientes (fecha_compra, cod_producto, id_cliente) VALUES('2020-02-06', 1, 1);
INSERT INTO productos_clientes (fecha_compra, cod_producto, id_cliente) VALUES('2020-02-02', 2, 3);
INSERT INTO productos_clientes (fecha_compra, cod_producto, id_cliente) VALUES('2020-02-12', 3, 5);
INSERT INTO productos_clientes (fecha_compra, cod_producto, id_cliente) VALUES('2020-02-28', 4, 2);
INSERT INTO productos_clientes (fecha_compra, cod_producto, id_cliente) VALUES('2020-02-14', 5, 1);
INSERT INTO productos_clientes (fecha_compra, cod_producto, id_cliente) VALUES('2020-02-09', 6, 3);

/* Populate tabla usuarios */

INSERT INTO usuarios (name, password, idcliente) VALUES('mariagm','1234',1);
INSERT INTO usuarios (name, password, idcliente) VALUES('john','1234',2);
INSERT INTO usuarios (name, password, idcliente) VALUES('linux','1234',3);

/* Populate tabla mails */

INSERT INTO mails (mail, idusuario) VALUES('mariagm173@hotmail.com',1);
INSERT INTO mails (mail, idusuario) VALUES('mg@gmail.es',1);
INSERT INTO mails (mail, idusuario) VALUES('john@gmail.es',2);
INSERT INTO mails (mail, idusuario) VALUES('john@hotmail.com',2);
INSERT INTO mails (mail, idusuario) VALUES('linux@gmail.com',3);