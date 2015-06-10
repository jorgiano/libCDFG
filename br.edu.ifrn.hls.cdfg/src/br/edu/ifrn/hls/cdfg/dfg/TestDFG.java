package br.edu.ifrn.hls.cdfg.dfg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.yaml.snakeyaml.Yaml;

import br.edu.ifrn.hls.cdfg.libFunction.FunctionsLib;

public class TestDFG {

	private final static Logger LOGGER = Logger
			.getLogger("br.edu.ifrn.hls.cdfg.*");

	public static void main(String[] args) {

		LOGGER.setLevel(Level.FINE);
		DFG initDFG = null;
		LOGGER.log(Level.INFO, "Test load init DFG from YAML file");
		try {
			FunctionsLib.getFunctionsLib().loadFromFile("functions.yml");
			initDFG = loadInitFromYAMLFile("mult_add.yml");
			System.out.println("\n\ninit DFG: " + initDFG);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static DFG loadInitFromYAMLFile(String filename) throws IOException {
		DFG init = null;
		File f = new File(filename);
		FileInputStream fis = new FileInputStream(f);
		Yaml yaml = new Yaml();
		Object data = yaml.load(fis);
		if (data instanceof Map) {
			@SuppressWarnings("unchecked")
			Map<String, Object> cdfg = (Map<String, Object>) data;
			@SuppressWarnings("unchecked")
			Map<String, Object> initDFG = (Map<String, Object>) cdfg
					.get("init");
			String initDFGName = (String) initDFG.get("name");
			@SuppressWarnings("unchecked")
			Map<String, Object> cdfgNodes = (Map<String, Object>) cdfg
					.get("nodes");
			LOGGER.log(Level.INFO, "Building init node " + initDFGName);
			@SuppressWarnings("unchecked")
			Map<String, Object> dfgNodes = (Map<String, Object>) cdfgNodes
					.get(initDFGName);
			init = DFGBuilder.buildDFGfromYAMLMap(dfgNodes);
			init.setName(initDFGName);
			System.out.println("DFG " + init.getName());
			System.out.println("  Number of Inputs     = "
					+ init.numberOfInputs());
			System.out.println("  Number of Outputs    = "
					+ init.numberOfOutputs());
			System.out.println("  Number of Nodes      = "
					+ init.numberOfNodes());
			System.out.println("  Number of Vertices   = "
					+ init.numberOfVertices());

		}
		fis.close();
		return init;

	}
}
