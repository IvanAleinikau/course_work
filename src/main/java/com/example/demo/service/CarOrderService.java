package com.example.demo.service;

import com.example.demo.dao.CarDao;
import com.example.demo.dao.CarOrderDao;
import com.example.demo.model.Car;
import com.example.demo.model.CarOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarOrderService {

    @Autowired
    private final CarOrderDao carOrderDao;

    public CarOrderService(CarOrderDao carOrderDao) {
        this.carOrderDao = carOrderDao;
    }

    public List<CarOrder> findAll(){
        List<CarOrder> carOrders = new ArrayList<CarOrder>();
        carOrderDao.findAll().forEach(order -> carOrders.add(order));
        return carOrders;
    }

    public CarOrder findById(Integer carOrderId){
        return carOrderDao.findById(carOrderId).get();
    }

    public void create(CarOrder carOrder){
        carOrderDao.save(carOrder);
    }

    public void delete(Integer carOrderId){
        carOrderDao.deleteById(carOrderId);
    }

    public void update(CarOrder carOrder,Integer carOrderId){
        carOrderDao.save(carOrder);
    }
}
