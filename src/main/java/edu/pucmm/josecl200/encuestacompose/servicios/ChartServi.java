package edu.pucmm.josecl200.encuestacompose.servicios;
//CanvasjsChartService.java

import java.util.List;
import java.util.Map;

public interface ChartServi {

    List<List<Map<Object, Object>>> getCanvasjsChartData();

}


//CanvasjsChartServiceImpl.java
    package edu.pucmm.josecl200.encuestacompose.servicios;

            import java.util.List;
            import java.util.Map;

            import org.springframework.beans.factory.annotation.Autowired;

            import com.canvasjs.chart.daos.CanvasjsChartDao;

public class CanvasChartServ implements ChartServi {

    @Autowired
    private CanvasjsChartDao canvasjsChartDao;

    public void setCanvasjsChartDao(CanvasjsChartDao canvasjsChartDao) {
        this.canvasjsChartDao = canvasjsChartDao;
    }

    @Override
    public List<List<Map<Object, Object>>> getCanvasjsChartData() {
        return canvasjsChartDao.getCanvasjsChartData();
    }

}