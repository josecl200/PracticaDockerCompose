package edu.pucmm.josecl200.encuestacompose.servicios;

import edu.pucmm.josecl200.encuestacompose.entidades.RespuestasEncuesta;
import edu.pucmm.josecl200.encuestacompose.entidades.Usuario;
import edu.pucmm.josecl200.encuestacompose.repositorios.EncuestaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EncuestaServicio {
    @Autowired
    private EncuestaRepo encuestaRepo;
    public boolean alreadyFilled(Usuario usuario){
        if(encuestaRepo.findAllByUsuario(usuario).size()>0)
            return true;
        return false;
    }

    public void saveEncuesta(RespuestasEncuesta encuesta){
       encuestaRepo.save(encuesta);
    }

    public Double[] promedios(){
        Double[] medias=new Double[3];
        List<RespuestasEncuesta> respuestas=encuestaRepo.findAll();
        double cant1=0,cant2=0,cant3=0;
        int val1=0,val2=0,val3=0;
        int curVal1,curVal2,curVal3;
        for (int n=0;n<respuestas.size();n++){
            curVal1=respuestas.get(n).getCharlas();
            curVal2=respuestas.get(n).getExpositores();
            curVal3=respuestas.get(n).getInstalaciones();
            if(curVal1>0){
                cant1++;
                val1+=curVal1;
            }
            if(curVal2>0){
                cant2++;
                val2+=curVal2;
            }
            if(curVal3>0){
                cant3++;
                val3+=curVal3;
            }
        }

        medias[0]=val1/cant1;
        medias[1]=val2/cant2;
        medias[2]=val3/cant3;
        return medias;
    }
}
