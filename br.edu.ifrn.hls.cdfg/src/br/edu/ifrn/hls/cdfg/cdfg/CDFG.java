package br.edu.ifrn.hls.cdfg.cdfg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import br.edu.ifrn.hls.cdfg.dfg.DFG;
import br.edu.ifrn.hls.cdfg.libFunction.FunctionsLib;

public class CDFG {

	private String name;
	private Map<String, DFG> nodes;
	private DFG init;
	private Map<String, Object> tags;

	public CDFG() {
		nodes = new HashMap<String, DFG>();
		tags = new HashMap<String, Object>();
	}


	static boolean checkType(String typeName) {
		if (typeName.equals("i16") || typeName.equals("i32"))
			return true;
		return false;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("name: ").append(this.name).append("\n");
		sb.append(nodes);
		return sb.toString();
	}

	public static void main(String args[]) {
		System.out.println("\n\n Teste de load de DFG");
		try {
			FunctionsLib.getFunctionsLib().loadFromFile("functions.yml");
			CDFG cdfg = new CDFG();
			//cdfg.loadFromYAMLFile("add.yml");
			System.out.println("\n\nCDFG:\n" + cdfg);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
