package com.rowerownia.rowerownia.repository;

import com.rowerownia.rowerownia.entity.Repair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepairRepository
        extends JpaRepository<Repair,Integer> {
}
