package com.itso.occupancy.occupancy.service.version;

import org.springframework.http.ResponseEntity;


public interface IVersionService {

    ResponseEntity<?> checkLastVersion(String version);

}
