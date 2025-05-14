package com.meditrackapi.Meditrack.dao.Repositories;

import com.meditrackapi.Meditrack.domain.DTOs.PostoTOs.Response.*;
import com.meditrackapi.Meditrack.domain.Entities.Posto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface PostoRepository extends JpaRepository<Posto, String> {

    @Query(
            value = "SELECT p.id as idPosto, " +
                    "p.nome as nomePosto, " +
                    "p.bairro as bairroPosto, " +
                    "p.rua as ruaPosto, " +
                    "p.numero as numeroPosto, " +
                    "p.linhas_onibus as linhasOnibusPosto, " +
                    "p.telefone as telefonePosto " +
                    "FROM posto p",
            nativeQuery = true
    )
    List<PostoDetalhadoResponse> findAllPostos();

    @Query(
            value = "SELECT p.id AS postoId, " +
                    "p.nome AS nomePosto, " +
                    "p.bairro AS bairroPosto, " +
                    "p.rua AS ruaPosto, " +
                    "p.numero AS numeroPosto, " +
                    "p.linhas_onibus AS linhasOnibus, " +
                    "p.telefone AS telefone, " +
                    "p.latitude AS latitude, " +
                    "p.longitude AS longitude, " +
                    "mp.quantidade_estoque AS quantidadeEstoque " +
                    "FROM posto p " +
                    "JOIN medicamento_posto mp ON p.id = mp.posto_id " +
                    "WHERE mp.medicamento_id = :id " +
                    "AND p.latitude IS NOT NULL " +
                    "AND p.longitude IS NOT NULL",
            nativeQuery = true
    )
    List<ListaPostosResponse> findPostosByMedicamentoId(@Param("id") String id);

    @Query(
        value = "SELECT new com.meditrackapi.Meditrack.domain.DTOs.PostoTOs.Response.PostoComMedicamentosResponse(" +
                "p.id, p.nome) " +
                "FROM Posto p " +
                "WHERE p.id = :id"
    )
    Optional<PostoComMedicamentosResponse> findComMedicamentosById(@Param("id") String id);

    @Query(
        value = "SELECT new com.meditrackapi.Meditrack.domain.DTOs.PostoTOs.Response.PostoResumoResponse(" +
                "p.id, p.nome, p.bairro, p.rua, p.numero, p.linhasOnibus, p.telefone) " +
                "FROM Posto p " +
                "WHERE UPPER(p.nome) LIKE UPPER(CONCAT('%', :nome, '%'))"
    )
    List<PostoResumoResponse> findByNomeContainingIgnoreCase(@Param("nome") String nome);

    @Query(value = "SELECT * FROM posto WHERE latitude IS NOT NULL AND longitude IS NOT NULL", nativeQuery = true)
    List<Posto> findPostosWithCoordinates();
}