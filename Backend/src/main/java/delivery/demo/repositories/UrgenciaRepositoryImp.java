package delivery.demo.repositories;

import org.sql2o.Sql2o;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class UrgenciaRepositoryImp {

    private final Sql2o sql2o;

    public UrgenciaRepositoryImp(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    //6 ¿Qué medio de pago se utiliza más en pedidos urgentes?
    public List<Map<String, Object>> obtenerMedioPagoMasUsadoEnUrgenciasAltas() {
        String sql = """
            SELECT\s
              mp.id_medio_pago,
              mp.tipo,
              COUNT(*) AS cantidad_usos
            FROM\s
              PEDIDO p
            JOIN\s
              URGENCIA u ON p.id_urgencia = u.id_urgencia
            JOIN\s
              MEDIO_PAGO mp ON p.id_medio_pago = mp.id_medio_pago
            WHERE\s
              u.tipo = 'Alta'
            GROUP BY\s
              mp.id_medio_pago, mp.tipo
            ORDER BY\s
              cantidad_usos DESC
            LIMIT 1;
        """;

        try (org.sql2o.Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetchTable()
                    .asList();
        }
    }
}
