package edu.pucmm.josecl200.encuestacompose.controladoras;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.canvasjs.chart.services.CanvasjsChartService;
import org.thymeleaf.model.IModel;


@Controller
@RequestMapping("/canvasjschart")
public class ChartController {


    @GetMapping("/DispBar1")
    public String Bar1(Model model){
        Map<String, Integer> ResultMap = new LinkedHashMap<>();
        ResultMap.put("Muy en desacuerdo",40);
        ResultMap.put("Desacuerdo",40);
        ResultMap.put("Neutral",40);
        ResultMap.put("De acuerdo",40);
        ResultMap.put("Muy de acuerdo",40);
        model.addAttribute("ResultMap",ResultMap);
        return "Bar1";
    }



    @Autowired
    private ChartController canvasjsChartService;

    @RequestMapping(method = RequestMethod.GET)
    public String springMVC(ModelMap modelMap) {
        List<List<Map<Object, Object>>> canvasjsDataList = canvasjsChartService.getCanvasjsChartData();
        modelMap.addAttribute("dataPointsList", canvasjsDataList);
        return "chart";
    }

}

