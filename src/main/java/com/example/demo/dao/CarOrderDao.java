package com.example.demo.dao;

import com.example.demo.model.CarOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarOrderDao extends CrudRepository<CarOrder,Integer> {
}
