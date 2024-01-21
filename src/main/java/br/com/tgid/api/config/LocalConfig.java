package br.com.tgid.api.config;

import br.com.tgid.api.domain.Cliente;
import br.com.tgid.api.domain.Empresa;
import br.com.tgid.api.repositories.ClienteRepository;
import br.com.tgid.api.repositories.EmpresaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;
import java.util.List;

/**
 * Classe para salvar no banco ao startar a aplicacao, afins de testes.
 * @Author pablojg9
 * */
@Configuration
@Profile("local")
public class LocalConfig {

    private final EmpresaRepository empresaRepository;
    private final ClienteRepository clienteRepository;

    @Autowired
    public LocalConfig(EmpresaRepository empresaRepository, ClienteRepository clienteRepository) {
        this.empresaRepository = empresaRepository;
        this.clienteRepository = clienteRepository;
    }

    /*
    * cpfs gerado pelo 4devs.
    * */
    @Bean
    public List<Cliente> startaClienteDB() {
        Cliente cliente1 = new Cliente(null, "cliente 1", "36765463003");
        Cliente cliente2 = new Cliente(null, "cliente 2", "52929049022");
        return clienteRepository.saveAll(List.of(cliente1, cliente2));
    }

    /*
     cpnjs gerado pelo 4devs.
    * */
    @Bean
    public List<Empresa> startaEmpresaDB() {
        Empresa empresa1 = new Empresa(null, "empresa 1", "05413221000105", 1000d, 20d);
        Empresa empresa2 = new Empresa(null, "empresa 2", "89332821000139", 1500d, 20d);
        return empresaRepository.saveAll(List.of(empresa1, empresa2));
    }
}
