package ma.barkouch.laptopapp.services;

import ma.barkouch.laptopapp.model.Dao;
import ma.barkouch.laptopapp.model.impl.LaptopDaoImpl;
import ma.barkouch.laptopapp.entities.Laptop;

import java.util.List;

public class LaptopService {
    final private Dao<Laptop,Integer> laptopDao = new LaptopDaoImpl();

    public List<Laptop> findAll  (){
        return laptopDao.findAll();
    }

    public Laptop findBySerial(int serial){
        return laptopDao.findById(serial);
    }

    public void insert(Laptop laptop){
        laptopDao.insert(laptop);
    }

    public void update(Laptop laptop){
        laptopDao.update(laptop);
    }

    public void deleteBySerial(int serial){
        laptopDao.deleteById(serial);
    }

    public boolean exists(Laptop laptop){
        return laptopDao.exist(laptop);
    }

}
