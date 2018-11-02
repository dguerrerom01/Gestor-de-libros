package controlador;

import java.io.*;
import java.util.*;

import modelo.Libro;

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
		file = new File(path);
		
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public boolean almacena(Object obj) {
		ArrayList<Libro> libros = (ArrayList<Libro>) obj;
		try {
			file.delete();
			file.createNewFile();
		}catch (IOException e) {
			e.printStackTrace();
		}
		try {
			for (Libro libro : libros) {
				flujoW = new FileOutputStream(file, true);
				if (file.length() > 0) {
					adaptadorW = new MyObjectOutputStream(new FileOutputStream(file, true));
				} else adaptadorW = new ObjectOutputStream(new FileOutputStream(file));
				adaptadorW.writeObject(libro);
				adaptadorW.close();
			}
		} catch (IOException e) {
			estado = false;
//			e.printStackTrace();
		}
		return estado;
	}
	
	
	public ArrayList<Libro> recuperar(){
		ArrayList<Libro> lista = new ArrayList<>();
		boolean finArchivo = false;
		try {
			adaptadorR = new ObjectInputStream(new FileInputStream(file));
			while (!finArchivo) {
				try {
					lista.add((Libro) adaptadorR.readObject());
				} catch (EOFException e) {
					finArchivo = true;
				}
			}
			adaptadorR.close();
		} catch (IOException | ClassNotFoundException e) {
			estado=false;
//			e.printStackTrace();
		}
		return lista;
	}

	public boolean isEstado() {
		return estado;
	}
	
}
