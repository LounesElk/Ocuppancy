package com.itso.occupancy.occupancy.service.version;

import com.itso.occupancy.occupancy.config.errorhandler.customexception.VersionException;
import com.itso.occupancy.occupancy.helper.tool.VersionUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VersionService implements IVersionService {

    private final VersionUtil versionUtil;


    /**
     * method to check version from query app
     * @param version
     * @return
     */
    @Override
    public ResponseEntity<?> checkLastVersion(String version) {

        String pomVersion = versionUtil.getPomVersion();

        if(VersionUtil.checkMajorVersion(pomVersion) == VersionUtil.checkMajorVersion(version))
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        throw new VersionException(String.format("Major break changes present in latest build version [%s]", pomVersion));
    }
}
