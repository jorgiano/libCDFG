package br.edu.ifrn.hls.cdfg.dfg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.ifrn.hls.cdfg.libFunction.Function;
import br.edu.ifrn.hls.cdfg.libFunction.FunctionsLib;

public class DFG {

	private String name;
	private List<DFGOperationNode> nodes;
	private List<DFGVertex> vertices;
	private List<DFGInputNode> inputs;
	private List<DFGOutputNode> outputs;

	private Map<String, Object> tags;

	private void init() {
		nodes = new ArrayList<DFGOperationNode>();
		vertices = new ArrayList<DFGVertex>();
		inputs = new ArrayList<DFGInputNode>();
		outputs = new ArrayList<DFGOutputNode>();
		tags = new HashMap<String, Object>();

	}

	public DFG() {
		this.init();
		this.setName("noname");
	}

	public DFG(String name) {
		this.init();
		this.setName(name);
	}

	public void buildFromYAML(Map data) {

		@SuppressWarnings("unchecked")
		Map<String, Object> nodes = (Map<String, Object>) data.get("nodes");
		setDFGNodes(nodes);
		Map<String, Object> vertices = ((Map<String, Object>) data
				.get("vertices"));
		setDFGVertices(vertices);

	}

	private void setDFGVertices(Map<String, Object> vertices) {
		// TODO Auto-generated method stub

	}

	private void setDFGNodes(Map<String, Object> dfgNodes) {
		@SuppressWarnings("unchecked")
		Map<String, Object> inputs = ((Map<String, Object>) dfgNodes
				.get("inputs"));
		setInputNodes(inputs);
		Map<String, Object> outputs = ((Map<String, Object>) dfgNodes
				.get("outputs"));
		setOutputNodes(outputs);
		Map<String, Object> operations = ((Map<String, Object>) dfgNodes
				.get("operations"));
		setOperationNodes(operations);
	}

	private void setOperationNodes(Map<String, Object> operations) {
		for (String operationName : operations.keySet()) {
			System.out.println("Building OP node " + operationName);
			Map<String, Object> nodeData = (Map<String, Object>) operations
					.get(operationName);
			String functionName = (String) nodeData.get("function");
			Function function = FunctionsLib.getFunctionsLib().getFunction(
					functionName);
			DFGOperationNode node = new DFGOperationNode(function);
			List inputs = (List) nodeData.get("inputs");
			
			for (Object o : inputs) {
				Map input = (Map) o;
				String inputName = (String) input.keySet().iterator().next();
				String type = (String) input.get(inputName);
				
			}

		}

	}

	private void setOutputNodes(Map<String, Object> outputs) {
		for (String outputName : outputs.keySet()) {
			DFGOutputNode output = new DFGOutputNode();
			output.setName(outputName);
			@SuppressWarnings("unchecked")
			Map<String, Object> outputData = (Map<String, Object>) outputs
					.get(outputName);
			String type = (String) outputData.get("type");
			output.setType(type);
			System.out.println("Output created: " + output.toString());
			@SuppressWarnings("unchecked")
			Map<String, Object> tags = (Map<String, Object>) outputData
					.get("tags");
			if (tags != null)
				for (String tagName : tags.keySet()) {
					output.addTag(tagName, tags.get(tagName));
				}
		}
	}

	private void setInputNodes(Map<String, Object> inputs) {
		for (String inputName : inputs.keySet()) {
			DFGInputNode input = new DFGInputNode();
			input.setName(inputName);
			@SuppressWarnings("unchecked")
			Map<String, Object> inputData = (Map<String, Object>) inputs
					.get(inputName);
			String type = (String) inputData.get("type");
			input.setType(type);
			System.out.println("Input created: " + input.toString());
			@SuppressWarnings("unchecked")
			Map<String, Object> tags = (Map<String, Object>) inputData
					.get("tags");
			if (tags != null)
				for (String tagName : tags.keySet()) {
					input.addTag(tagName, tags.get(tagName));
				}
		}
	}

	public void addTag(String key, Object tag) {
		tags.put(key, tag);
	}

	public void removeTag(String key) {
		if (tags.get(key) != null)
			tags.remove(key);
	}

	public int getNumberOfTags() {
		return tags.size();
	}

	boolean check() {
		return false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
