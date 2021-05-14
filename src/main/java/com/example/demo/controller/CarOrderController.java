package com.example.demo.controller;

import com.example.demo.model.Car;
import com.example.demo.model.CarOrder;
import com.example.demo.service.CarOrderService;
import com.example.demo.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class CarOrderController {

    @Autowired
    private CarService carService;

    @Autowired
    private CarOrderService carOrderService;

    @GetMapping(value = "/order")
    public String order(Model model){
        return "order";
    }

    @GetMapping(value = "/active_rentals")
    public String activeOrder(Model model){
        List<CarOrder> carOrders = carOrderService.findAll();
        List<CarOrder> carOrderList = new ArrayList<>() ;
        List<CarOrder> carOrderList2 = new ArrayList<>() ;
        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < carOrders.size(); i++) {
            if(carOrders.get(i).isLeased()){
                carOrderList.add(carOrders.get(i));
                cars.add(carService.findById(carOrderList.get(i).getCarId()));
            }
        }

        model.addAttribute("listOrder",carOrderList);
        return "active_rentals";
    }

    @GetMapping(value = "/car_rent/{id}/order")
    public String carOrderGet(@PathVariable(value = "id") Integer carId , Model model) {
        Optional<Car> car = Optional.ofNullable(carService.findById(carId));
        ArrayList<Car> res = new ArrayList<>();
        car.ifPresent(res::add);
        model.addAttribute("car",res);
        return "order";
    }

    @PostMapping(value = "/car_rent/{id}/order")
    public String carOrder(@PathVariable(value = "id") Integer carId ,
                                @RequestParam(value = "dateLeased", required = false)
                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                @RequestParam(value = "username") String username) {
        Car car = carService.findById(carId);
        car.setLeased(true);
        CarOrder carOrder = new CarOrder();
        carOrder.setCarOrderId(carId);
        carOrder.setLeased(true);
        carOrder.setDate(date);
        carOrder.setUsername(username);
        carOrder.setCarId(carId);
        carOrderService.create(carOrder);
        return "redirect:/car_rent";
    }

    @PostMapping(value = "/order/{id}")
    public String updateOrder(@PathVariable(value = "id") Integer carOrderId ) {
        CarOrder carOrder = carOrderService.findById(carOrderId);
        carOrder.setLeased(false);
        Car car = carService.findById(carOrderId);
        car.setLeased(false);
        carService.update(car,carOrderId);
        carOrderService.delete(carOrderId);
        return "redirect:/active_rentals";
    }
}
