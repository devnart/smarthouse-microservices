package com.smart.house.controller;

import com.smart.house.entity.Device;
import com.smart.house.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.WebServer;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;
import java.util.List;

@RestController
public class DeviceController {

    DeviceService deviceService;

    DeviceController(DeviceService deviceService){
        this.deviceService = deviceService;
    }

    @GetMapping("/devices")
    @ResponseStatus(HttpStatus.OK)
    public List<Device> getAllDevices(){
        return deviceService.getAllDevices();
    }

    @PostMapping("/device/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addDevice(@RequestBody Device device){
        deviceService.addDevice(device);
    }

    @PutMapping("/device/setStatus/{id}")
    public ResponseEntity<String> setStatus(@PathVariable("id") String id, @RequestBody Device device){
        return deviceService.updateStatus(id, device);
    }
    @PostMapping("/device/delete/{id}")
    public void deleteDevice(@PathVariable String id){
        deviceService.deleteDevice(id);
    }

    @PutMapping("/device/update")
    public void updateDevice(@RequestBody Device device){
        deviceService.updateDevice(device);
    }

}
