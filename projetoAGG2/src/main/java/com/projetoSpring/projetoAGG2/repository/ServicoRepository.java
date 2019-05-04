package com.projetoSpring.projetoAGG2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetoSpring.projetoAGG2.model.Servico;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {

}
