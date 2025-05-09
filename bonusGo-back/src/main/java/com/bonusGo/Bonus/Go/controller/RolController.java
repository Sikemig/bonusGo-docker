package com.bonusGo.Bonus.Go.controller;

import com.bonusGo.Bonus.Go.model.Rol;
import com.bonusGo.Bonus.Go.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("roles")
public class RolController {

    @Autowired
    private RolService rolService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Rol>> getAllRoles() {
        List<Rol> roles = rolService.getAllRoles();
        return ResponseEntity.ok(roles);
    }
}
