-- Población grande para las tablas

-- CLIENTE
INSERT INTO CLIENTE (id_cliente, direccion, correo) VALUES
(1, 'Calle A, Murcia', 'cliente1@example.com'),
(2, 'Calle B, Cartagena', 'cliente2@example.com'),
(3, 'Avenida C, Lorca', 'cliente3@example.com'),
(4, 'Calle D, Molina de Segura', 'cliente4@example.com'),
(5, 'Plaza E, Alcantarilla', 'cliente5@example.com'),
(6, 'Calle F, San Javier', 'cliente6@example.com'),
(7, 'Avenida G, Totana', 'cliente7@example.com'),
(8, 'Calle H, Águilas', 'cliente8@example.com'),
(9, 'Calle I, Cehegín', 'cliente9@example.com'),
(10, 'Avenida J, Cieza', 'cliente10@example.com');

-- EMPRESA_ASOCIADA
INSERT INTO EMPRESA_ASOCIADA (id_empresa_asociada, nombre) VALUES
(1, 'RapidExpress'),
(2, 'SuperDelivery'),
(3, 'Logística RM'),
(4, 'Entregas Ya'),
(5, 'KargoGo');

-- CATEGORIA
INSERT INTO CATEGORIA (id_categoria, nombre) VALUES
(1, 'Comida'),
(2, 'Tecnología'),
(3, 'Ropa'),
(4, 'Hogar'),
(5, 'Juguetes');

-- REPARTIDOR
INSERT INTO REPARTIDOR (id_repartidor, nombre, id_empresa_asociada) VALUES
(1, 'Juan López', 1),
(2, 'María Pérez', 2),
(3, 'Carlos Ruiz', 3),
(4, 'Ana Gómez', 4),
(5, 'Luis Martínez', 5),
(6, 'Carmen Sánchez', 1),
(7, 'Pedro Díaz', 2),
(8, 'Lucía Romero', 3),
(9, 'Jorge Ortega', 4),
(10, 'Elena Navarro', 5);

-- PRODUCTO_SERVICIO
INSERT INTO PRODUCTO_SERVICIO (id_producto_servicio, stock, precio, id_categoria, id_empresa_asociada) VALUES
(1, 50, 25.99, 1, 1),
(2, 40, 199.99, 2, 2),
(3, 100, 15.00, 3, 3),
(4, 20, 89.50, 4, 4),
(5, 70, 12.75, 5, 5),
(6, 60, 9.99, 1, 1),
(7, 80, 150.00, 2, 2),
(8, 30, 45.25, 3, 3),
(9, 90, 30.00, 4, 4),
(10, 100, 8.00, 5, 5);

-- MEDIO_PAGO
INSERT INTO MEDIO_PAGO (id_medio_pago, tipo) VALUES
(1, 'Tarjeta'),
(2, 'Efectivo'),
(3, 'Transferencia'),
(4, 'PayPal');

-- URGENCIA
INSERT INTO URGENCIA (id_urgencia, tipo) VALUES
(1, 'Alta'),
(2, 'Media'),
(3, 'Baja');

-- DETALLE_PEDIDO
INSERT INTO DETALLE_PEDIDO (id_detalle_pedido, entregado, fecha_entrega) VALUES
(1, TRUE, '2024-03-01 10:00:00'),
(2, FALSE, '2024-03-02 15:30:00'),
(3, TRUE, '2024-03-03 11:15:00'),
(4, TRUE, '2024-03-04 09:45:00'),
(5, FALSE, '2024-03-05 13:20:00');

-- PEDIDO
INSERT INTO PEDIDO (id_pedido, fecha_pedido, id_urgencia, id_detalle_pedido, id_repartidor, id_cliente, id_medio_pago) VALUES
(1, '2024-03-01 09:00:00', 1, 1, 1, 1, 1),
(2, '2024-03-02 14:00:00', 2, 2, 2, 2, 2),
(3, '2024-03-03 10:30:00', 3, 3, 3, 3, 3),
(4, '2024-03-04 08:45:00', 1, 4, 4, 4, 4),
(5, '2024-03-05 12:15:00', 2, 5, 5, 5, 1),
(6, '2024-03-06 11:20:00', 3, 1, 6, 6, 2),
(7, '2024-03-07 16:10:00', 1, 2, 7, 7, 3),
(8, '2024-03-08 17:05:00', 2, 3, 8, 8, 4),
(9, '2024-03-09 10:50:00', 3, 4, 9, 9, 1),
(10, '2024-03-10 13:40:00', 1, 5, 10, 10, 2);

-- CALIFICACION
INSERT INTO CALIFICACION (id_calificacion, puntuacion, id_repartidor) VALUES
(1, 5, 1),
(2, 4, 2),
(3, 3, 3),
(4, 5, 4),
(5, 2, 5),
(6, 4, 6),
(7, 1, 7),
(8, 5, 8),
(9, 3, 9),
(10, 4, 10);

-- PEDIDO_PRODUCTO
INSERT INTO PEDIDO_PRODUCTO (id_pedido, id_producto_servicio, cantidad) VALUES
(1, 1, 2),
(1, 2, 1),
(2, 3, 4),
(3, 4, 1),
(3, 5, 2),
(4, 6, 1),
(5, 7, 2),
(6, 8, 3),
(7, 9, 1),
(8, 10, 2),
(9, 1, 1),
(10, 2, 3);