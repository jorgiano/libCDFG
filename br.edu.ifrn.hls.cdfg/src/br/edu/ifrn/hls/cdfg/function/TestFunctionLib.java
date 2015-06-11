package br.edu.ifrn.hls.cdfg.function;

import java.io.FileNotFoundException;

public class TestFunctionLib {

	public static void main(String[] args) {
		FunctionsLib functions = FunctionsLib.getFunctionsLib();
		try {
			functions.loadFromFile("functions.yml");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println(functions);
	}

}
