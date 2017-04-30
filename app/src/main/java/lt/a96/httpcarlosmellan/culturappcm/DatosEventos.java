package lt.a96.httpcarlosmellan.culturappcm;

import android.graphics.drawable.Drawable;

/**
 * Created by Casa on 10-04-2017.
 */

public class DatosEventos {
    public Drawable foto;
    public String idevento;
    public String nombre;
    public String descripcion;
    public String localizacion;
    public String fechainicio;
    public String horainicio;
    public long id;

    //Metodo constructor de la clase
    public DatosEventos(Drawable foto, String idevento, String nombre, String descripcion, String localizacion, String fechainicio, String horainicio){
        //A los atributos les asignamos los parametros correspondientes
        this.foto = foto;
        this.idevento = idevento;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.localizacion = localizacion;
        this.fechainicio = fechainicio;
        this.horainicio = horainicio;
        //this.id= id;
    }


    //metodo get obtiene los datos
    public Drawable getFoto(){
        return foto;
    }
    //metodo set asigna o inicializa los datos
    public void setFoto(Drawable foto){
        this.foto=foto;
    }

    //IDEVENTO
    public String getIdevento(){
        return idevento;
    }
    public void setIdevento(String idvento){
        this.idevento=idevento;
    }

    //NOMBREEVENTO
    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre=nombre;
    }

    //DESCRIPCION
    public String getDescripcion(){
        return descripcion;
    }
    public void setDescripcion(String descripcion){
        this.descripcion=descripcion;
    }

    //LOCALIZACION
    public String getLocalizacion(){
        return localizacion;
    }
    public void setLocalizacion(String localizacion){
        this.localizacion=localizacion;
    }

    //FECHAINICIO
    public String getFechainicio(){
        return fechainicio;
    }
    public void setFechainicio(String fechainicio){
        this.fechainicio=fechainicio;
    }

    //HORAINICIO
    public String getHorainicio(){
        return horainicio;
    }
    public void setHorainicio(String horainicio){
        this.horainicio=horainicio;
    }

    //metodo get obtiene los datos
    public long getId(){
        return id;
    }
    //metodo set asigna o inicializa los datos
    public void setId(long id){
        this.id=id;
    }
}
