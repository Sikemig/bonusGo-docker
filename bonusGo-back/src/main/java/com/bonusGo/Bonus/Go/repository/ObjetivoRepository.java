package com.bonusGo.Bonus.Go.repository;

import com.bonusGo.Bonus.Go.model.Objetivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObjetivoRepository extends JpaRepository<Objetivo, Integer> {
}
