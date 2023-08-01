package com.mohsenfn.equipment.Service;

import com.mohsenfn.equipment.Entity.Equipment;
import com.mohsenfn.equipment.Repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipmentService implements EquipmentIService{
    @Autowired
    private EquipmentRepository er;
    @Override
    public Equipment AddEquipment(Equipment equipment) {
        return er.save(equipment);
    }

    @Override
    public Equipment UpdateEquipment(Equipment equipment) {
        return er.save(equipment);
    }

    @Override
    public void DeleteEquipment(long idEquipment) {
    er.deleteById(idEquipment);
    }

    @Override
    public void ReduceQuantity(long quantity, long idEquipment) {
        Equipment equipment=er.findById(idEquipment).get();
        equipment.setQuantity(quantity);
    }
}
