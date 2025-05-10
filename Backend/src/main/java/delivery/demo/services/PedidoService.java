package delivery.demo.services;

import delivery.demo.repositories.PedidoRepositoryImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class PedidoService {
    private final NamedParameterJdbcTemplate jdbc;

    @Autowired
    public PedidoService(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }
    @Autowired
    private PedidoRepositoryImp pedidoRepositoryImp;

    public List<Map<String, Object>> obtenerMasPedidosPorCategoriaUltimoMes() {
        return pedidoRepositoryImp.obtenerMasPedidosPorCategoriaUltimoMes();
    }

    public List<Map<String, Object>> obtenerTiemposPromedioEntrega() {
        return pedidoRepositoryImp.obtenerTiemposPromedioEntrega();
    }

    public Long registrarPedidoPorFuncion(Long idUrgencia, Long idRepartidor, Long idCliente, Long idMedioPago) {
        String sql = "SELECT registrar_pedido(:u, :r, :c, :m)";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("u", idUrgencia)
                .addValue("r", idRepartidor)
                .addValue("c", idCliente)
                .addValue("m", idMedioPago);

        return jdbc.queryForObject(sql, params, Long.class);
    }
}
