package br.com.tgid.api.repositories;

import br.com.tgid.api.domain.Empresa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

public interface EmpresaRepository extends JpaRepository<Empresa, UUID> {

    Optional<Empresa> findByCnpj(String cnpj);

    @Modifying
    @Transactional
    @Query("UPDATE Empresa e SET e.saldo = e.saldo + :valorAdicionado + e.taxa WHERE e.cnpj = :cnpj")
    Integer adicionarValorAoSaldoPorCnpj(@Param("cnpj") String cnpj, @Param("valorAdicionado") Double valorAdicionado);


}
