package registros;

import java.util.Date;

public class Entrevista {

    protected Date fecha;
    protected CV curriculum;
    protected String informe;

    public Entrevista(Date fecha, CV curriculum, String informe){
        this.fecha = fecha;
        this.curriculum = curriculum;
        this.informe = informe;
    }

    public Date getFecha() {
        return fecha;
    }

    public CV getCurriculum() {
        return curriculum;
    }

    public String getInforme() {
        return informe;
    }

    public void setCurriculum(CV curriculum) {
        this.curriculum = curriculum;
    }

    public void setInforme(String informe) {
        this.informe = informe;
    }

    @Override
    public String toString() {
        return "Entrevista dada el " + getFecha() +
                "\n" + curriculum.toString() +
                "\nInforme: " + getInforme() +
                "\n";
    }
}
