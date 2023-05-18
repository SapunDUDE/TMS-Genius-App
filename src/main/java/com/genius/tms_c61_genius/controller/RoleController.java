package com.genius.tms_c61_genius.controller;

import com.genius.tms_c61_genius.aspect.TrackExecutionTime;
import com.genius.tms_c61_genius.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RoleController {
    private final RoleService roleService;
    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/admin/role")
    ResponseEntity<String>getRole(@RequestParam Integer roleid){
        return new ResponseEntity<>(roleService.getRole(roleid), HttpStatus.OK);
    }

    @PostMapping("/admin/role")
    @TrackExecutionTime
    ResponseEntity<String>createRole(@RequestParam String rolename){
        return new ResponseEntity<>(roleService.createRole(rolename),HttpStatus.CREATED);
    }

    @PutMapping("/admin/role")
    @TrackExecutionTime
    ResponseEntity<String>updateRole(@RequestParam Integer roleid, @RequestParam String rolename){
        return new ResponseEntity<>(roleService.updateRole(roleid,rolename),HttpStatus.OK);
    }

    @DeleteMapping("/admin/role")
    @TrackExecutionTime
    ResponseEntity<HttpStatus>deleteRole(@RequestParam Integer roleid){
        roleService.deleteRole(roleid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
