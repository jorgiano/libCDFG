package br.edu.ifrn.hls.cdfg.dfg;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public abstract class DFGNode {

	private final static Logger LOGGER = Logger.getLogger(DFGNode.class
			.getName());

	private Map<String, DFGNodePort> inputs;
	private Map<String, DFGNodePort> outputs;
	private Map<String, DFGNode> dependsOn;
	private DFG dfg;
	private Map<String, String> tags;

	public DFGNode() {
		inputs = new HashMap<String, DFGNodePort>();
		outputs = new HashMap<String, DFGNodePort>();
		dependsOn = new HashMap<String, DFGNode>();
		tags = new HashMap<String, String>();
	}

	public Map<String, DFGNodePort> getInputs() {
		return inputs;
	}

	public Map<String, DFGNodePort> getOutputs() {
		return outputs;
	}

	public DFG getDFG() {
		return dfg;
	}

	public void setDFG(DFG dfg) {
		this.dfg = dfg;
	}

	/*
	 * public void addInput(DFGNodePort port) { this.inputs.put(port.getName(),
	 * port); }
	 * 
	 * public void addOutput(DFGNodePort port) {
	 * this.outputs.put(port.getName(), port); }
	 * 
	 * public DFGNodePort getInputByName(String portName) { return
	 * inputs.get(portName); }
	 * 
	 * public DFGNodePort getOutputByName(String portName) { return
	 * outputs.get(portName); }
	 * 
	 * public int getNumberOfInputs() { return inputs.size(); }
	 * 
	 * public int getNumberOfOutputs() { return outputs.size(); }
	 * 
	 * public Set<String> getInputsName() { return inputs.keySet(); }
	 * 
	 * public Set<String> getOutputsName() { return outputs.keySet(); }
	 * 
	 * public void removeInputByName(String name) { inputs.remove(name); }
	 * 
	 * public void removeOutputByName(String name) { outputs.remove(name); }
	 */
	public Map<String, String> getTags() {
		return this.tags;
	}

	public boolean check() {
		boolean ok = true;
		for (DFGNodePort port : inputs.values()) {
			ok = ok && port.check();
			ok = ok && (port.getNode() == this);
		}
		for (DFGNodePort port : outputs.values()) {
			ok = ok && port.check();
			ok = ok && (port.getNode() == this);
		}
		return ok;
	}

}
