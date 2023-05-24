package com.genius.tms_c61_genius.controller;

import com.genius.tms_c61_genius.aspect.TrackExecutionTime;
import com.genius.tms_c61_genius.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController {
    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping()
    ResponseEntity<String> getRole(@RequestParam Integer roleid) {
        return new ResponseEntity<>(roleService.getRole(roleid), HttpStatus.OK);
    }

    @PostMapping()
    @TrackExecutionTime
    ResponseEntity<String> createRole(@RequestParam String rolename) {
        return new ResponseEntity<>(roleService.createRole(rolename), HttpStatus.CREATED);
    }

    @PutMapping()
    @TrackExecutionTime
    ResponseEntity<String> updateRole(@RequestParam Integer roleid, @RequestParam String rolename) {
        return new ResponseEntity<>(roleService.updateRole(roleid, rolename), HttpStatus.OK);
    }

    @DeleteMapping()
    @TrackExecutionTime
    ResponseEntity<HttpStatus> deleteRole(@RequestParam Integer roleid) {
        roleService.deleteRole(roleid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
