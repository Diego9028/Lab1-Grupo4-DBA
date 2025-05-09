package delivery.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import delivery.demo.repositories.EmpresaAsociadaRepositoryImp;
import java.util.List;
import java.util.Map;

@Service
public class EmpresaAsociadaService {

    @Autowired
    private EmpresaAsociadaRepositoryImp empresaAsociadaRepositoryImp;

    public List<Map<String, Object>> obtenerEntregasFallidasPorEmpresa() {
        return empresaAsociadaRepositoryImp.obtenerEntregasFallidasPorEmpresa();
    }
}
