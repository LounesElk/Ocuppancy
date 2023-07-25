package com.itso.occupancy.occupancy.controller.version;

import com.itso.occupancy.occupancy.service.version.VersionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/version")
@AllArgsConstructor
public class VersionController {


    private final VersionService versionService;

    /**
     * valid version for desktop api, or return last version to get
     * @param version
     * @return
     */
    @GetMapping
    public ResponseEntity<?> validVersionOrUpdate(@RequestParam String version) {
        return versionService.checkLastVersion(version);
    }


}
