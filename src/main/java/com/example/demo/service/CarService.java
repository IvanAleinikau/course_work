package com.example.demo.service;

import com.example.demo.dao.CarDao;
import com.example.demo.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private final CarDao carDao;

    public CarService(CarDao carDao) {
        this.carDao = carDao;
    }

    public List<Car> findAll(){
        List<Car> cars = new ArrayList<Car>();
        carDao.findAll().forEach(car -> cars.add(car));
        return cars;
    }

    public Car findById(Integer carId){
        return carDao.findById(carId).get();
    }

    public void create(Car car){
        carDao.save(car);
    }

    public void delete(Integer carId){
        carDao.deleteById(carId);
    }

    public void update(Car car,Integer carId){
        carDao.save(car);
    }
 }
