CREATE TABLE CLIENTE
(
  id_cliente INT NOT NULL,
  direccion VARCHAR(255) NOT NULL,
  correo VARCHAR(100) NOT NULL,
  PRIMARY KEY (id_cliente)
);

CREATE TABLE EMPRESA_ASOCIADA
(
  id_empresa_asociada INT NOT NULL,
  nombre VARCHAR(100) NOT NULL,
  PRIMARY KEY (id_empresa_asociada)
);

CREATE TABLE DETALLE_PEDIDO
(
  id_detalle_pedido INT NOT NULL,
  entregado BOOLEAN NOT NULL,
  hora_entrega TIME NOT NULL,
  PRIMARY KEY (id_detalle_pedido)
);

CREATE TABLE MEDIO_PAGO
(
  id_medio_pago INT NOT NULL,
  tipo VARCHAR(50) NOT NULL,
  PRIMARY KEY (id_medio_pago)
);

CREATE TABLE CATEGORIA
(
  id_categoria INT NOT NULL,
  nombre VARCHAR(100) NOT NULL,
  PRIMARY KEY (id_categoria)
);

CREATE TABLE URGENCIA
(
  id_urgencia INT NOT NULL,
  tipo VARCHAR(50) NOT NULL,
  PRIMARY KEY (id_urgencia)
);

CREATE TABLE REPARTIDOR
(
  id_repartidor INT NOT NULL,
  nombre VARCHAR(100) NOT NULL,
  id_empresa_asociada INT NOT NULL,
  PRIMARY KEY (id_repartidor),
  FOREIGN KEY (id_empresa_asociada) REFERENCES EMPRESA_ASOCIADA(id_empresa_asociada)
);

CREATE TABLE PRODUCTO_SERVICIO
(
  id_producto_servicio INT NOT NULL,
  stock INT NOT NULL,
  precio DECIMAL(10,2) NOT NULL,
  id_categoria INT NOT NULL,
  id_empresa_asociada INT NOT NULL,
  PRIMARY KEY (id_producto_servicio),
  FOREIGN KEY (id_categoria) REFERENCES CATEGORIA(id_categoria),
  FOREIGN KEY (id_empresa_asociada) REFERENCES EMPRESA_ASOCIADA(id_empresa_asociada)
);

CREATE TABLE PEDIDO
(
  id_pedido INT NOT NULL,
  hora_pedido TIME NOT NULL,
  id_urgencia INT NOT NULL,
  id_detalle_pedido INT NOT NULL,
  id_repartidor INT NOT NULL,
  id_cliente INT NOT NULL,
  id_medio_pago INT NOT NULL,
  PRIMARY KEY (id_pedido),
  FOREIGN KEY (id_urgencia) REFERENCES URGENCIA(id_urgencia),
  FOREIGN KEY (id_detalle_pedido) REFERENCES DETALLE_PEDIDO(id_detalle_pedido),
  FOREIGN KEY (id_repartidor) REFERENCES REPARTIDOR(id_repartidor),
  FOREIGN KEY (id_cliente) REFERENCES CLIENTE(id_cliente),
  FOREIGN KEY (id_medio_pago) REFERENCES MEDIO_PAGO(id_medio_pago)
);

CREATE TABLE CALIFICACION
(
  id_calificacion INT NOT NULL,
  puntuacion INT NOT NULL,
  id_repartidor INT NOT NULL,
  PRIMARY KEY (id_calificacion),
  FOREIGN KEY (id_repartidor) REFERENCES REPARTIDOR(id_repartidor)
);

-- Tabla de relación entre pedidos y productos/servicios
CREATE TABLE PEDIDO_PRODUCTO
(
  id_pedido INT NOT NULL,
  id_producto_servicio INT NOT NULL,
  cantidad INT NOT NULL,
  PRIMARY KEY (id_pedido, id_producto_servicio),
  FOREIGN KEY (id_pedido) REFERENCES PEDIDO(id_pedido),
  FOREIGN KEY (id_producto_servicio) REFERENCES PRODUCTO_SERVICIO(id_producto_servicio)
);
