/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operaciones;

import java.io.File;

/**
 *
 * @author Angel
 */
public class Archivos {
    private String ruta;
    
    public Archivos(String ruta){
        this.ruta = ruta;
    }

    protected String extension(String filename) {
        int extpos = filename.lastIndexOf('.');
        return extpos < 0 || filename.substring(extpos + 1).equals("") ? "noext" : filename.substring(extpos + 1);
    }
    
    protected String prefijoArch(String filename) {
        int prepos = filename.indexOf('_');
        return prepos < 0 || filename.substring(0, prepos).equals("") ? "nopre" : filename.substring(0, prepos);
    }
    
    protected boolean crearCarpeta(String nombre){
        String direccion = unirRutaNombre(ruta, nombre);
        File directorio = new File(direccion);
        if (!directorio.exists())
            return directorio.mkdirs();
        else
            return true;
    }
    
    protected String unirRutaNombre(String anterior, String posterior){
        String separador = anterior.endsWith("\\") ? "" : "\\";
        return anterior + separador + posterior;
    }
    
    public void organiza(boolean porExtension) {
        String carpeta, mover;
        File directory = new File(ruta);

        for (File archivos : directory.listFiles()) 
            if (archivos.isFile()){
                if(porExtension)
                    carpeta = extension(archivos.getName());
                else
                    carpeta = prefijoArch(archivos.getName());
                if(crearCarpeta(carpeta)){
                    mover = unirRutaNombre(ruta, carpeta);
                    mover = unirRutaNombre(mover, archivos.getName());
                    archivos.renameTo(new File(mover));
                }
            }
    }
}
