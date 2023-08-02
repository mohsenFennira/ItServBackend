package com.mohsenfn.equipment.Controller;

import com.mohsenfn.equipment.Entity.Equipment;
import com.mohsenfn.equipment.Service.EquipmentIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/Equipment")
@RestController
public class EquipmentController {
    @Autowired
    EquipmentIService eis;
    @PostMapping("add")
    public Equipment AddEquipment(@RequestBody Equipment equipment) {
        return eis.AddEquipment(equipment);
    }

   @PutMapping("Update")
    public Equipment UpdateEquipment(@RequestBody Equipment equipment) {
        return eis.UpdateEquipment(equipment);
    }

    @DeleteMapping("Delete")
    public void DeleteEquipment(@RequestParam long idEquipment) {
        eis.DeleteEquipment(idEquipment);
    }

    @GetMapping("GetEquipments")
    public List<Equipment> getEquipments() {
        return eis.getEquipments();
    }
}
