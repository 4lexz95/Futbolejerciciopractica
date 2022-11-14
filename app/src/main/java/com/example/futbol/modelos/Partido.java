package com.example.futbol.modelos;

import java.io.Serializable;

public class Partido implements Serializable {

    String equipo1, equipo2;
    int res1;
    int res2;
    String resumen;

    public Partido(String equipo1, String equipo2, int res1, int res2, String resumen) {
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.res1 = res1;
        this.res2 = res2;
        this.resumen = resumen;
    }

    public String getEquipo1() {
        return equipo1;
    }

    public void setEquipo1(String equipo1) {
        this.equipo1 = equipo1;
    }

    public String getEquipo2() {
        return equipo2;
    }

    public void setEquipo2(String equipo2) {
        this.equipo2 = equipo2;
    }

    public int getRes1() {
        return res1;
    }

    public void setRes1(int res1) {
        this.res1 = res1;
    }

    public int getRes2() {
        return res2;
    }

    public void setRes2(int res2) {
        this.res2 = res2;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    @Override
    public String toString() {
        return "Partido{" +
                "equipo1='" + equipo1 + '\'' +
                ", equipo2='" + equipo2 + '\'' +
                ", res1=" + res1 +
                ", res2=" + res2 +
                ", resumen='" + resumen + '\'' +
                '}';
    }
}
