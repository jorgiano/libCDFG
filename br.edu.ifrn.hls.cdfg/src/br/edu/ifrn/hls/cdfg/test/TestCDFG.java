package br.edu.ifrn.hls.cdfg.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.yaml.snakeyaml.Yaml;

import br.edu.ifrn.hls.cdfg.cdfg.CDFG;
import br.edu.ifrn.hls.cdfg.dfg.DFG;
import br.edu.ifrn.hls.cdfg.dfg.DFGBuilder;
import br.edu.ifrn.hls.cdfg.function.FunctionsLib;

public class TestCDFG {

	private final static Logger LOGGER = Logger
			.getLogger("br.edu.ifrn.hls.cdfg.*");

	public static void main(String[] args) {

		LOGGER.setLevel(Level.FINE);
		DFG initDFG = null;
		CDFG cdfg = null;
		LOGGER.log(Level.INFO, "Test load init DFG from YAML file");
		try {
			FunctionsLib.getFunctionsLib().loadFromFile("functions.yml");
			cdfg = loadInitFromYAMLFile("mult_add.yml");
			// cdfg = loadInitFromYAMLFile("mult_add.yml");
			initDFG = cdfg.getInitDFG();
			System.out.println("\n\ninit DFG: " + initDFG);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		FileWriter fos = null;
		BufferedWriter bof = null;
		try {
			fos = new FileWriter(new File("saida.yml"));
			bof = new BufferedWriter(fos);
			bof.write(cdfg.toYAML());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bof.close();
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private static CDFG loadInitFromYAMLFile(String filename)
			throws IOException {
		DFG init = null;
		File f = new File(filename);
		FileInputStream fis = new FileInputStream(f);
		Yaml yaml = new Yaml();
		Object data = yaml.load(fis);
		CDFG cdfg = new CDFG();
		if (data instanceof Map) {
			@SuppressWarnings("unchecked")
			Map<String, Object> cdfgData = (Map<String, Object>) data;
			String name = (String) cdfgData.get("name");
			cdfg.setName(name);
			@SuppressWarnings("unchecked")
			Map<String, Object> initDFG = (Map<String, Object>) cdfgData
					.get("init");
			String initDFGName = (String) initDFG.get("name");
			@SuppressWarnings("unchecked")
			Map<String, Object> cdfgNodes = (Map<String, Object>) cdfgData
					.get("nodes");
			LOGGER.log(Level.INFO, "Building init node " + initDFGName);
			@SuppressWarnings("unchecked")
			Map<String, Object> dfgNodes = (Map<String, Object>) cdfgNodes
					.get(initDFGName);
			init = DFGBuilder.buildDFGfromYAMLMap(dfgNodes);
			init.setName(initDFGName);
			cdfg.addNode(init);
			cdfg.setInitDFG(init);
			System.out.println("DFG " + init.getName());
			System.out.println("  Number of Inputs     = "
					+ init.numberOfInputs());
			System.out.println("  Number of Outputs    = "
					+ init.numberOfOutputs());
			System.out.println("  Number of Nodes      = "
					+ init.numberOfOperations());
			System.out.println("  Number of Vertices   = "
					+ init.numberOfVertices());
			System.out.println("  Test                 = " + init.check());
		}
		fis.close();
		return cdfg;

	}
}
