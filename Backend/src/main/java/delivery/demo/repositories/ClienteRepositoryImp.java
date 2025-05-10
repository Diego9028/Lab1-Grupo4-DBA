package delivery.demo.repositories;

import delivery.demo.entities.ClienteEntity;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class ClienteRepositoryImp {

    private final Sql2o sql2o;

    public ClienteRepositoryImp(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    public Optional<Long> obtenerClienteConMasGasto() {
        String sql = """
        SELECT 
          c.id
        FROM CLIENTE c
        JOIN PEDIDO p ON c.id = p.id_cliente
        JOIN DETALLE_PEDIDO dp ON p.id_detalle_pedido = dp.id_detalle_pedido
        JOIN PEDIDO_PRODUCTO pp ON p.id_pedido = pp.id_pedido
        JOIN PRODUCTO_SERVICIO ps ON pp.id_producto_servicio = ps.id_producto_servicio
        WHERE dp.entregado = TRUE
        GROUP BY c.id
        ORDER BY SUM(ps.precio * pp.cantidad) DESC
        LIMIT 1
    """;
        try (Connection conn = sql2o.open()) {
            return Optional.ofNullable(conn.createQuery(sql)
                    .executeScalar(Long.class));
        }
    }

    public Map<String, Object> findClienteQueMasGasto() {
        String sql = """
        SELECT 
            c.id_cliente AS "idCliente",
            c.direccion AS "direccion",
            c.correo AS "correo",
            SUM(ps.precio * pp.cantidad) AS "totalGastado"
        FROM CLIENTE c
        JOIN PEDIDO p ON c.id_cliente = p.id_cliente
        JOIN DETALLE_PEDIDO dp ON p.id_detalle_pedido = dp.id_detalle_pedido
        JOIN PEDIDO_PRODUCTO pp ON p.id_pedido = pp.id_pedido
        JOIN PRODUCTO_SERVICIO ps ON pp.id_producto_servicio = ps.id_producto_servicio
        WHERE dp.entregado = TRUE
        GROUP BY c.id_cliente, c.direccion, c.correo
        ORDER BY totalGastado DESC
        LIMIT 1
    """;

        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetchTable()
                    .asList()
                    .stream()
                    .findFirst()
                    .orElse(null);
        }
    }

    public Optional<ClienteEntity> findByCorreo(String correo) {
        String sql = "SELECT * FROM CLIENTE WHERE correo = :correo";

        try (Connection con = sql2o.open()) {
            ClienteEntity cliente = con.createQuery(sql)
                    .addParameter("correo", correo)
                    .executeAndFetchFirst(ClienteEntity.class);
            return Optional.ofNullable(cliente);
        }
    }

    public List<ClienteEntity> findAllClientes() {
        String sql = "SELECT * FROM CLIENTE";

        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetchTable()
                    .asList()
                    .stream()
                    .map(row -> {
                        ClienteEntity c = new ClienteEntity();
                        c.setId(((Number) row.get("id_cliente")).longValue());
                        c.setNombre((String) row.get("nombre"));
                        c.setDireccion((String) row.get("direccion"));
                        c.setCorreo((String) row.get("correo"));
                        c.setPassword((String) row.get("password"));
                        return c;
                    })
                    .toList();
        }
    }
}

