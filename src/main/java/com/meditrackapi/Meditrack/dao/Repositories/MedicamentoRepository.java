package com.meditrackapi.Meditrack.dao.Repositories;

import com.meditrackapi.Meditrack.domain.Entities.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicamentoRepository extends JpaRepository<Medicamento, String> {

}
