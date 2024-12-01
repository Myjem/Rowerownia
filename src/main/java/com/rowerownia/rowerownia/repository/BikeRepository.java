package com.rowerownia.rowerownia.repository;

import com.rowerownia.rowerownia.entity.Bike;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BikeRepository extends CrudRepository<Bike,Integer> {
}
