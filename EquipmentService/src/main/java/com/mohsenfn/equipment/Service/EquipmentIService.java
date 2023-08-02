package com.mohsenfn.equipment.Service;

import com.mohsenfn.equipment.Entity.Equipment;

import java.util.List;

public interface EquipmentIService {
    public Equipment AddEquipment(Equipment equipment);
    public Equipment UpdateEquipment(Equipment equipment);
    public void DeleteEquipment(long idEquipment);
    public void ReduceQuantity(long quantity,long idEquipment);
    public List<Equipment> getEquipments();
}
