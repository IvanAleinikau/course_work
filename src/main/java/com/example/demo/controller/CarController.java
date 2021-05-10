package com.example.demo.controller;

import com.example.demo.model.Car;
import com.example.demo.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping(value = "/cars")
    public String carsPage(Model model){
        model.addAttribute("listCar",carService.findAll());
        return "cars";
    }

    @GetMapping(value = "/cars/add")
    public String carPage(){
        return "car";
    }

    @PostMapping(value = "/car")
    public String createCar(Car car){
        car.setLeased(false);
        carService.create(car);
        return "redirect:/cars";
    }

    @DeleteMapping(value = "/delete")
    public String deleteCar(@PathVariable  Integer carId){
        carService.delete(carId);
        return "redirect:/cars";
    }

    @GetMapping(value = "/cars/{id}/delete")
    public String deleteCarGet(@PathVariable(value = "id") Integer carId , Model model) {
        Optional<Car> car = Optional.ofNullable(carService.findById(carId));
        ArrayList<Car> res = new ArrayList<>();
        car.ifPresent(res::add);
        model.addAttribute("car",res);
        return "car_delete";
    }

    @GetMapping(value = "/cars/{id}/update")
    public String updateCarGet(@PathVariable(value = "id") Integer carId , Model model) {
        Optional<Car> car = Optional.ofNullable(carService.findById(carId));
        ArrayList<Car> res = new ArrayList<>();
        car.ifPresent(res::add);
        model.addAttribute("car",res);
        return "car_update";
    }

    @PostMapping(value = "/cars/{id}/delete")
    public String deleteCarPost(@PathVariable(value = "id") Integer carId , Car car, Model model) {
        carService.delete(carId);
        return "redirect:/cars";
    }

    @PostMapping(value = "/cars/{id}/update")
    public String updateCarPost(@PathVariable(value = "id") Integer carId ,
                                @RequestParam  String carModel,
                                @RequestParam  Integer yearOfIssue,
                                @RequestParam  String carColor,
                                @RequestParam  Double pricePerDay) {
        Car car = carService.findById(carId);
        car.setCarModel(carModel);
        car.setYearOfIssue(yearOfIssue);
        car.setCarColor(carColor);
        car.setPricePerDay(pricePerDay);
        carService.update(car,carId);
        return "redirect:/cars";
    }

    @GetMapping(value = "/car_rent")
    public String carRentPage(Model model){
        List<Car> allCars = carService.findAll();
        ArrayList<Car> notLeasedCars = new ArrayList<>();
        for (int i = 0; i < allCars.size() ; i++) {
            if(!allCars.get(i).isLeased()){
                notLeasedCars.add(allCars.get(i));
            }
        }
        model.addAttribute("cars",notLeasedCars);
        return "car_rent";
    }


}
