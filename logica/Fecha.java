package logica;

import java.time.LocalDate;

public class Fecha {
    //atributos
    private int dia;
    private int mes;
    private int anio;
    //Constructor vacio
    public Fecha(){

    }
    //Constructor lleno
    public Fecha(int dia, int mes, int anio){
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
    }
    
    //constructor sobrecargado de la clase Fecga
    public Fecha(String s){
        //Buscamos la primera ocurrencia de '/'
        int pos1 = s.indexOf('/');
        //Buscamos las última ocurrencia de '/'
        int pos2 = s.lastIndexOf('/');
        //procesamos el día
        String sDia = s.substring(0,pos1);
        this.dia = Integer.parseInt(sDia);
        //procesamos el mes
        String sMes = s.substring(pos1+1,pos2);
        this.mes = Integer.parseInt(sMes);
        //procesamos el anio
        String sAnio = s.substring(pos2+1);
        this.anio = Integer.parseInt(sAnio);

    }

    public static Fecha hoy() {
        LocalDate actual = LocalDate.now();
        return new Fecha(actual.getDayOfMonth(), actual.getMonthValue(), actual.getYear());
    }
    public int getDia() {
        return dia;
    }
    public void setDia(int dia) {
        this.dia = dia;
    }
    public int getMes() {
        return mes;
    }
    public void setMes(int mes) {
        this.mes = mes;
    }
    public int getAnio() {
        return anio;
    }
    public void setAnio(int anio) {
        this.anio = anio;
    }

    //Retorna la fecha en dias
    private int fechaToDias(){
        return this.anio*360+this.mes*30+this.dia;
    }
    //asigna la fecha expresada en dias a los atributos
    private void diasToFecha(int i){
        //dividimos por 360 y obtenemos el añp
        this.anio = (int)i/360;
        //del resto o residuo de la división anterior
        //podremos obtener el mes y el dia

        int resto = i % 360;

        //el mes es el resto dividido entre 30
        this.mes = (int) resto/30;

        //el resto de la division anterior son los dias
        this.dia = resto % 30;

        //ajuste por di el dia es cero
        if (dia ==0){
            dia = 30;
            mes--;
        }
        //ajuste por si el mes quedo en cero
        if (mes == 0){
            mes = 12;
            anio--;
        }
        
    }

    public void addDias(int d){
        //convertimos la fecha a dias y le sumamos d
        int sum = fechaToDias()+d;
        //la fecha resultante (sum) se separa en dia, mes y anio
        diasToFecha(sum);
    }

    
    @Override
    public String toString() {
        return  dia + "/" + mes + "/" + anio ;
    }

    @Override
    public boolean equals (Object o){
        Fecha otra = (Fecha) o;
        boolean valor;
        valor = (this.dia == otra.getDia() && this.mes == otra.getMes() && 
                this.anio == otra.getAnio());
        return valor;
    }
    
   
    

    
}