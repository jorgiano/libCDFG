package br.edu.ifrn.hls.cdfg.dfg;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DFG {

	private final static Logger LOGGER = Logger.getLogger(DFG.class.getName());

	private String name;
	private Map<String, DFGOperationNode> operations;
	private Map<String, DFGVertex> vertices;
	private Map<String, DFGInputNode> inputs;
	private Map<String, DFGOutputNode> outputs;

	private Map<String, String> tags;

	public Map<String, String> getTags() {
		return this.tags;
	}

	public Map<String, DFGOperationNode> getOperations() {
		return operations;
	}

	public Map<String, DFGVertex> getVertices() {
		return vertices;
	}

	public Map<String, DFGInputNode> getInputs() {
		return inputs;
	}

	public Map<String, DFGOutputNode> getOutputs() {
		return outputs;
	}

	private void init() {
		operations = new HashMap<String, DFGOperationNode>();
		vertices = new HashMap<String, DFGVertex>();
		inputs = new HashMap<String, DFGInputNode>();
		outputs = new HashMap<String, DFGOutputNode>();
		tags = new HashMap<String, String>();
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
		operations.put(operation.getName(), operation);
	}

	public void addVertex(DFGVertex vertex) {
		/* TODO: check integrity */
		LOGGER.log(Level.FINE, "Adding Vertex");
		vertices.put(vertex.getName(), vertex);
	}

	public int numberOfInputs() {
		return inputs.size();
	}

	public int numberOfOutputs() {
		return outputs.size();
	}

	public int numberOfOperations() {
		return operations.size();
	}

	public int numberOfVertices() {
		return vertices.size();
	}

	public DFGOperationNode getOperationByName(String name) {
		return this.operations.get(name);
	}

	public boolean check() {
		boolean ok = this.name != null;
		ok = ok && checkInputs();
		ok = ok && checkOutputs();
		ok = ok && checkOperations();
		ok = ok && checkVertices();
		ok = ok && checkCycles();
		return ok;
	}

	private boolean checkCycles() {
		boolean ok = true;
		System.out
				.println("IMPORTANT: The check method is not checking for DFG cycles!!!");
		return ok;
	}

	private boolean checkOperations() {
		boolean ok = true;
		for (DFGOperationNode node : this.operations.values()) {
			ok = ok && (node.check());
			ok = ok && (node.getDFG() == this);
		}
		return ok;
	}

	private boolean checkOutputs() {
		boolean ok = true;
		for (DFGOutputNode output : outputs.values()) {
			ok = ok
					&& (output.check() && output.getInputPort()
							.getConnectedTo() != null);
			ok = ok && (output.getDFG() == this);

		}
		return ok;
	}

	private boolean checkInputs() {
		boolean ok = true;
		for (DFGInputNode input : inputs.values()) {
			ok = ok
					&& (input.check() && input.getOutputPort().getConnectedTo() != null);
			ok = ok && (input.getDFG() == this);

		}

		return ok;
	}

	private boolean checkVertices() {
		boolean ok = true;
		for (DFGVertex vertex : this.vertices.values()) {
			ok = ok && vertex.check();
			ok = ok && (vertex.getDFG() == this);
		}

		return ok;
	}

	public String toYAML() {
		StringBuilder sb = new StringBuilder("  ");
		sb.append(this.getName()).append(":\n");
		sb.append("    nodes:\n");
		sb.append("      inputs:\n");
		for (DFGInputNode input : this.inputs.values()) {
			sb.append(input.toYAML());
		}
		sb.append("      outputs:\n");
		for (DFGOutputNode output : this.outputs.values()) {
			sb.append(output.toYAML());
		}
		sb.append("      operations:\n");
		for (DFGOperationNode node : this.operations.values()) {
			sb.append(node.toYAML());
		}
		sb.append("    vertices:\n");
		for (DFGVertex vertex : this.vertices.values()) {
			sb.append(vertex.toYAML());
		}
		sb.append("    tags: {");
		String sep = " ";
		for (String tagName : this.getTags().keySet()) {
			String value = this.getTags().get(tagName);
			sb.append(sep);
			sb.append(tagName);
			sb.append(": ");
			sb.append(value);
			sep = ",";
		}
		sb.append("}\n");
		return sb.toString();
	}
}
