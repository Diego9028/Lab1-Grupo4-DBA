-- Población grande para las tablas

-- CLIENTE
INSERT INTO CLIENTE (nombre, direccion, correo, password) VALUES
('Antonio Torres', 'Calle A, Murcia', 'cliente1@example.com', '$2a$10$q0ZB/DYih0X6a0RptSkEW.DdwsOdKl/YGfftHJNh7WtbR/jcgg7sW'),
('Laura Sánchez', 'Calle B, Cartagena', 'cliente2@example.com', '$2a$10$w7eN/zIxTCey26VVaOuFd.DfXDihr4rjXAN4XknP08BkxsAU5P8MK'),
('José Martínez', 'Avenida C, Lorca', 'cliente3@example.com', '$2a$10$RBOnE3uwM.4cSbgBw0k3Ze0.hdbvVhceZ7RMq14gxgsUo6yJhxTrq'),
('Elena Ruiz', 'Calle D, Molina de Segura', 'cliente4@example.com', '$2a$10$dM5nt.KHUupeGeyhsB57juucQCo6wZdS/iZdSnTt2cJk.D7hFDnWC'),
('Carlos López', 'Plaza E, Alcantarilla', 'cliente5@example.com', '$2a$10$SI3H/vriOgIgOGhZxV0pCOFwv0uYhQW0Zv5hOLsOIpdKVQ32sA/jq'),
('María Gómez', 'Calle F, San Javier', 'cliente6@example.com', '$2a$10$OTxUDN7XyDorQGkzt1NfneprKm3I437GfWmPOTE5NgrZ.bF9UCI/i'),
('Miguel Díaz', 'Avenida G, Totana', 'cliente7@example.com', '$2a$10$YNfWObQ3tYQqx6jFo0H/ReXYtZNfdB2o/Odo3NfW3jS.uneoL52X.'),
('Sara Romero', 'Calle H, Águilas', 'cliente8@example.com', '$2a$10$HFtu6s5gbqf/yJpbNXRtvuv3CweF7pFNrKt.wEjet3ooQ0aM6DzHO'),
('David Navarro', 'Calle I, Cehegín', 'cliente9@example.com', '$2a$10$mOSKaq9TQOnjFMKr60Ex8OzjEsY.hJi8LCl3rm5UuuXcePKaxxS7.'),
('Lucía Ortega', 'Avenida J, Cieza', 'cliente10@example.com', '$2a$10$ScKQMbeA9l5bfRaq6Mq1b.068J5S8IFrLm3L0E221LQdZ3F7/UXFW');

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
INSERT INTO PRODUCTO_SERVICIO (id_producto_servicio, stock, precio, id_categoria, id_empresa_asociada, es_producto) VALUES
(1,  50,  25.99, 1, 1, TRUE),
(2,  40, 199.99, 2, 2, TRUE),
(3, 100,  15.00, 3, 3, TRUE),
(4,  20,  89.50, 4, 4, TRUE),
(5,  70,  12.75, 5, 5, TRUE),
(6,  60,   9.99, 1, 1, TRUE),
(7,  80, 150.00, 2, 2, TRUE),
(8,  30,  45.25, 3, 3, TRUE),
(9,  90,  30.00, 4, 4, TRUE),
(10,100,   8.00, 5, 5, TRUE),
(11, 0, 120.00, 2, 2, FALSE);  


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
INSERT INTO DETALLE_PEDIDO (entregado, hora_entrega, calificacion) VALUES
(TRUE,  '2024-03-01 10:00:00', NULL),
(FALSE, '2024-03-02 15:30:00', NULL),
(TRUE,  '2024-03-03 11:15:00', NULL),
(TRUE,  '2024-03-04 09:45:00', NULL),
(FALSE, '2024-03-05 13:20:00', NULL);

-- PEDIDO
INSERT INTO PEDIDO (hora_pedido, id_urgencia, id_detalle_pedido, id_repartidor, id_cliente, id_medio_pago) VALUES
('2024-03-01 09:00:00', 1, 1, 1, 1, 1),
('2024-03-02 14:00:00', 2, 2, 2, 2, 2),
('2024-03-03 10:30:00', 3, 3, 3, 3, 3),
('2024-03-04 08:45:00', 1, 4, 4, 4, 4),
('2024-03-05 12:15:00', 2, 5, 5, 5, 1),
('2024-03-06 11:20:00', 3, 1, 6, 6, 2),
('2024-03-07 16:10:00', 1, 2, 7, 7, 3),
('2024-03-08 17:05:00', 2, 3, 8, 8, 4),
('2024-03-09 10:50:00', 3, 4, 9, 9, 1),
('2024-03-10 13:40:00', 1, 5, 10, 10, 2);

-- CALIFICACION
INSERT INTO CALIFICACION (
  id_calificacion,
  total_puntos,
  total_pedidos,
  promedio,
  id_repartidor
) VALUES
(1,  5, 1, 5.00, 1),
(2,  4, 2, 2.00, 2),
(3,  3, 3, 1.00, 3),
(4,  5, 4, 1.25, 4),
(5,  2, 5, 0.40, 5),
(6,  4, 6, 0.67, 6),
(7,  1, 7, 0.14, 7),
(8,  5, 8, 0.63, 8),
(9,  3, 9, 0.33, 9),
(10, 4,10, 0.40, 10);

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



UPDATE DETALLE_PEDIDO
SET    calificacion = CASE id_detalle_pedido
                        WHEN 1 THEN 1
                        WHEN 3 THEN 3
                        WHEN 4 THEN 4
                      END
WHERE  id_detalle_pedido IN (1,3,4);
