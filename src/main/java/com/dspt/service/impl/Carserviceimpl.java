package com.dspt.service.impl;

import com.dspt.entity.Car;
import com.dspt.entity.Cardet;
import com.dspt.entity.Cardetail;
import com.dspt.entity.Product;
import com.dspt.mapper.Carmapper;
import com.dspt.mapper.Productmapper;
import com.dspt.service.Carservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class Carserviceimpl implements Carservice {
    @Autowired
    Carmapper carmapper;
    @Autowired
    Productmapper productmapper;
    @Override
    public List<Cardetail> findcar(String username) {
        List<Car> cars=carmapper.findcar(username);
        if (cars==null){
            return null;
        }
        List<Cardetail> cardetails =new ArrayList<>();
        for(Car car:cars) {
            Product product = productmapper.findOneProduct(car.getId());
            Cardetail cardetail = new Cardetail(product, car);
            cardetails.add(cardetail);
//            cardet.add(new Cardet(cardetail));
        }
        return cardetails;
    }

    @Override
    public Cardetail findcardetil(String cid) {
        Car car=carmapper.findoen(cid);
        Product product=productmapper.findOneProduct(car.getId());
        return new Cardetail(product,car);
    }

    @Override
    public void delall(String username) {
        carmapper.delall(username);
    }

    @Override
    public Car findoen(String cid) {
        return carmapper.findoen(cid);
    }

    @Override
    public void addcar(Car car) {
         Car car1=carmapper.findpro(car.getId(), car.getUsername());
         if(car1==null){
               carmapper.addcar(car);
         }else {
             Car car2=new Car(car1.getCid(),car1.getId(),car1.getUsername(),car1.getNum()+car.getNum());
             carmapper.update(car2);
         }

    }

    @Override
    public void delcar(String cid) {
          carmapper.delcar(cid);
    }
}
