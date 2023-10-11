package com.sdu.onlineofflineindicator.repository;

import com.sdu.onlineofflineindicator.model.Pulse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PulseRepository extends JpaRepository<Pulse, Integer> {
}
