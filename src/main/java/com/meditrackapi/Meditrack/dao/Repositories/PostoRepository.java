package com.meditrackapi.Meditrack.dao.Repositories;

import com.meditrackapi.Meditrack.domain.DTOs.PostoTOs.Response.ListaPostosResponse;
import com.meditrackapi.Meditrack.domain.Entities.Posto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostoRepository extends JpaRepository<Posto, String> {
    @Query(
            value = "SELECT p.nome AS nomePosto, " +
                    "p.bairro AS bairroPosto, " +
                    "p.rua AS ruaPosto, " +
                    "p.numero AS numeroPosto, " +
                    "p.linhas_onibus AS linhasOnibus, " +
                    "p.telefone AS telefone " +
                    "FROM posto p " +
                    "JOIN medicamento_posto mp ON p.id = mp.posto_id " +
                    "WHERE mp.medicamento_id = :id",
            nativeQuery = true
    )
    List<ListaPostosResponse> findPostosByMedicamentoId(@Param("id") String id);
}
