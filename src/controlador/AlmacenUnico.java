package controlador;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class AlmacenUnico {

	private String path;
	private File file;
	private FileOutputStream flujoW;
	private FileInputStream flujoR;
	private ObjectInputStream adaptadorR;
	private ObjectOutputStream adaptadorW;
	private boolean estado = true;
	
	public AlmacenUnico(String path) {
		super();
		this.path = path;
		file=new File(path);
		
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean almacena(Object obj){
			try {
				flujoW=new FileOutputStream(file);
				adaptadorW=new ObjectOutputStream(flujoW);
				adaptadorW.writeObject(obj);
			} catch (IOException e) {
				estado=false;
				e.printStackTrace();
			}
			try {
				adaptadorW.close();
				flujoW.close();
			} catch (IOException e) {
				estado=false;
				e.printStackTrace();
			}
		return estado;
	}
	
	public Object recuperar(){
		Object obj=null;
			try {
				flujoR=new FileInputStream(file);
				adaptadorR=new ObjectInputStream(flujoR);
				obj=adaptadorR.readObject();
			} catch (IOException | ClassNotFoundException e) {	
				estado=false;
				e.printStackTrace();
			}
			try {
				adaptadorR.close();
				flujoR.close();
			} catch (IOException e) {
				estado=false;
				e.printStackTrace();
			}
		
		return obj;
	}

	public boolean isEstado() {
		return estado;
	}
	
}
