package br.edu.ifrn.hls.cdfg.dfg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import br.edu.ifrn.hls.cdfg.cdfg.CDFG;
import br.edu.ifrn.hls.cdfg.libFunction.FunctionsLib;

public class TestDFG {

	public static void main(String[] args) {
		DFG initDFG;

		System.out.println("\n\n Teste de load de DFG");
		try {
			FunctionsLib.getFunctionsLib().loadFromFile("functions.yml");
			initDFG = loadInitFromYAMLFile("add.yml");
			System.out.println("\n\ninit DFG: ");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static DFG loadInitFromYAMLFile(String filename)
			throws FileNotFoundException {
		DFG init = null;
		File f = new File(filename);
		FileInputStream fis = new FileInputStream(f);
		Yaml yaml = new Yaml();
		Object data = yaml.load(fis);
		if (data instanceof Map) {
			Map cdfg = (Map) data;
			Map initDFG = (Map) cdfg.get("init");
			String initDFGName = (String) initDFG.get("name");
			Map initDFGTags = (Map) initDFG.get("tags");
			Map dfgNodes = (Map) cdfg.get("nodes");
			init = buildDFG((Map) dfgNodes.get(initDFGName));
			init.setName(initDFGName);
		}
		return init;

	}

	private static DFG buildDFG(Map dfgData) {
		DFG dfg = new DFG();
		Map<String, Object> nodes = (Map<String, Object>) dfgData.get("nodes");
		buildDFGNodes(dfg, nodes);
		Map<String, Object> vertices = (Map<String, Object>) dfgData
				.get("vertices");
		buildDFGVertices(dfg, vertices);
		Map<String, Object> tags = (Map<String, Object>) dfgData.get("tags");
		buildDFGTags(dfg, tags);
		return dfg;
	}

	private static void buildDFGNodes(DFG dfg, Map<String, Object> nodes) {
		Map<String, Object> inputs = (Map<String, Object>) nodes.get("inputs");
		buildDFGInputs(dfg, inputs);
		Map<String, Object> outputs = (Map<String, Object>) nodes
				.get("outputs");
		buildDFGOutputs(dfg, outputs);
		Map<String, Object> operations = (Map<String, Object>) nodes
				.get("operations");
		buildDFGOperations(dfg, operations);

	}

	private static void buildDFGInputs(DFG dfg, Map<String, Object> inputs) {
		// TODO Auto-generated method stub

	}

	private static void buildDFGOutputs(DFG dfg, Map<String, Object> outputs) {
		// TODO Auto-generated method stub

	}

	private static void buildDFGOperations(DFG dfg,
			Map<String, Object> operations) {
		// TODO Auto-generated method stub

	}

	private static void buildDFGVertices(DFG dfg, Map<String, Object> vertices) {
		// TODO Auto-generated method stub

	}

	private static void buildDFGTags(DFG dfg, Map<String, Object> tags) {
		// TODO Auto-generated method stub

	}

}
