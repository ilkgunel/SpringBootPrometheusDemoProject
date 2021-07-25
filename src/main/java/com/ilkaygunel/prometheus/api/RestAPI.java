package com.ilkaygunel.prometheus.api;

import com.ilkaygunel.prometheus.service.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ilkay.gunel
 */
@RestController
@RequestMapping("/api")
public class RestAPI {

    @Autowired
    private SeriesService seriesService;

    @RequestMapping(method = RequestMethod.GET, value = "/seriesName")
    public ResponseEntity getSeriesNameOfCharacter(@RequestParam(value = "character", defaultValue = "") String character) {
        String seriesName = seriesService.getSeriesNameOfCharacter(character);
        if ("NOT_FOUND".equalsIgnoreCase(seriesName)) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(seriesName, HttpStatus.OK);
        }
    }
}
