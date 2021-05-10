package com.example.demo.dao;

import com.example.demo.model.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarDao extends CrudRepository<Car,Integer> {
}
