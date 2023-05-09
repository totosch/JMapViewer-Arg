package model;

import java.io.FileWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public abstract class SerializableJSON {
	public void generarJSON (String archivo) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String data = gson.toJson(this);
		
		try {
			FileWriter writer = new FileWriter(archivo);
			writer.write(data);
			writer.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
