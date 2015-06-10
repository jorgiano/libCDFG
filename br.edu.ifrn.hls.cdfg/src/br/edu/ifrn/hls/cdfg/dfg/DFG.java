package br.edu.ifrn.hls.cdfg.dfg;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DFG {

	private final static Logger LOGGER = Logger.getLogger(DFG.class.getName());

	private String name;
	private Map<String, DFGOperationNode> nodes;
	private Map<String, DFGVertex> vertices;
	private Map<String, DFGInputNode> inputs;
	private Map<String, DFGOutputNode> outputs;

	private Map<String, Object> tags;

	private void init() {
		nodes = new HashMap<String, DFGOperationNode>();
		vertices = new HashMap<String, DFGVertex>();
		inputs = new HashMap<String, DFGInputNode>();
		outputs = new HashMap<String, DFGOutputNode>();
		tags = new HashMap<String, Object>();
	}

	public DFG() {
		this.init();
		this.setName("noname");
		LOGGER.log(Level.INFO, "Creating DFG noname");
	}

	public DFG(String name) {
		LOGGER.log(Level.INFO, "Creating DFG " + name);
		this.init();
		this.setName(name);
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addInputNode(DFGInputNode input) {
		/* TODO: check integrity (input name, etc) */
		LOGGER.log(Level.FINE, "Adding input " + input.getName() + " to DFG "
				+ this.name);
		inputs.put(input.getName(), input);

	}

	public DFGInputNode getInputByName(String name) {
		return this.inputs.get(name);
	}

	public void addOutputNode(DFGOutputNode output) {
		/* TODO check integrity (input name, etc) */
		LOGGER.log(Level.FINE, "Adding output " + output.getName() + " to DFG "
				+ this.name);
		outputs.put(output.getName(), output);

	}

	public DFGOutputNode getOutputByName(String name) {
		return this.outputs.get(name);
	}

	public void addOperationNode(DFGOperationNode operation) {
		/* TODO: check integrity */
		LOGGER.log(Level.FINE, "Adding operation "
				+ operation.getFunction().getName() + " to DFG " + this.name);
		nodes.put(operation.getName(), operation);
	}

	public void addVertex(DFGVertex vertex) {
		/* TODO: check integrity */
		LOGGER.log(Level.FINE, "Adding Vertex");
		vertices.put(vertex.getName(), vertex);
	}

	public boolean check() {
		return false;
	}

	public int numberOfInputs() {
		return inputs.size();
	}

	public int numberOfOutputs() {
		return outputs.size();
	}

	public int numberOfNodes() {
		return nodes.size();
	}

	public int numberOfVertices() {
		return vertices.size();
	}

	public DFGOperationNode getOperationByName(String name) {
		return this.nodes.get(name);
	}
}
