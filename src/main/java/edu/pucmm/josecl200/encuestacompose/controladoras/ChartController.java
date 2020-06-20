package edu.pucmm.josecl200.encuestacompose.controladoras;

import java.util.Map;
import java.util.TreeMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpSession;
import java.util.Optional;

@RestController
public class ChartController {

    @GetMapping("/get-data")
    public ResponseEntity<Map<String, Integer>> getPieChart() {
        Map<String, Integer> graphData = new TreeMap<>();
        graphData.put("2016", 147);
        graphData.put("2017", 1256);
        graphData.put("2018", 3856);
        graphData.put("2019", 19807);
        return new ResponseEntity<>(graphData, HttpStatus.OK);
    }
}