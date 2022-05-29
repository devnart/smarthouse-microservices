package com.smart.house.service;

import com.smart.house.entity.Device;
import com.smart.house.enums.Status;
import com.smart.house.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class DeviceService {

    public final DeviceRepository deviceRepository;
    private final WebClient.Builder webClientBuilder;

    public Device addDevice(Device device) {
        return deviceRepository.save(device);
    }

    public void deleteDevice(String id) {
        deviceRepository.deleteById(id);
    }

    public List<Device> getAllDevices() {
        System.out.println("getAllDevices");
        return deviceRepository.findAll();
    }

    public Device updateDevice(Device device) {
        return deviceRepository.save(device);
    }

    public ResponseEntity<String> updateStatus(String id, Device device) {

        Optional<Device> storeDevice = deviceRepository.findById(id);

        if(storeDevice.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Device not found please enter a valid device id");
        }

        LocalDateTime date = LocalDateTime.now();
        Map<String, String> bodyMap = new HashMap<>();
        bodyMap.put("status", device.getStatus().toString());
        bodyMap.put("deviceId", id);
        bodyMap.put("date", date.toString());

        storeHistory(bodyMap);

        storeDevice.get().setStatus(device.getStatus());
        if(!deviceRepository.save(storeDevice.get()).getId().isEmpty()) {
            return ResponseEntity.ok("Device turned "+device.getStatus()+" successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }

    public Object getDevice(String id) {
        return deviceRepository.findById(id);
    }

    public void storeHistory(Map<String, String> bodyMap) {
        System.out.println("storeHistory");
        String url = "http://history-service";
        webClientBuilder.build().post()
                .uri(url + "/history").body(Mono.just(bodyMap), Map.class)
                .retrieve()
                .bodyToMono(String.class)
                .subscribe();
    }

}
