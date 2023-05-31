package persistence;

import java.io.*;

import domain.POOBStairs;
import domain.POOBStairsException;

/**
 * The type Poo bstairs io.
 */
public class POOBstairsIO {

    /**
     * Save class.
     *
     * @param filename the filename
     * @param object   the object
     */
    public static void save(File file ,POOBStairs a) throws POOBStairsException {
        try{
            ObjectOutputStream out = new ObjectOutputStream( new FileOutputStream( file ) );
            out.writeObject(a);
            out.close();

        }
        catch( Exception e){
            throw new POOBStairsException("Ocurrio un error al guardar "+file.getName());
        }
    }

    public static void saveO1( File file, POOBStairs a) throws POOBStairsException {
        if ( file.getName().endsWith(".dat")){
            save(file,a);
        }
        else{
            throw new POOBStairsException(POOBStairsException.EXTENSION_ARCHIVO_NO_VALIDO);
        }
    }

    /**
     * Load class object.
     *
     * @param filename the filename
     * @return the object
     */
    public static POOBStairs abrir( File file ) throws POOBStairsException {
        try{
            ObjectInputStream in = new ObjectInputStream( new FileInputStream(file));
            POOBStairs a = (POOBStairs)in.readObject();
            in.close();
            System.out.println(a.getAllCasillas().size());
            
            return a;

        }catch( Exception e){
            System.out.println(e);
            throw new POOBStairsException("Ocurrio un error al Abrir "+file.getName());
        }
    }


    public static POOBStairs abrirO1( File file ) throws POOBStairsException {
        if ( file.getName().endsWith(".dat")){
            return abrir( file );
        }
        else{
            throw new POOBStairsException(POOBStairsException.EXTENSION_ARCHIVO_NO_VALIDO);
        }
    }

}
