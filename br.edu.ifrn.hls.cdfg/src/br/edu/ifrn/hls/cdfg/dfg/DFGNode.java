package br.edu.ifrn.hls.cdfg.dfg;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class DFGNode {

	protected Map<String, DFGNodePort> inputs;
	protected Map<String, DFGNodePort> outputs;

	private Map<String, Object> tags;

	public DFGNode() {
		inputs = new HashMap<String, DFGNodePort>();
		outputs = new HashMap<String, DFGNodePort>();
	}

	public void addInput(DFGNodePort port) {
		this.inputs.put(port.getName(), port);
	}

	public void addOutput(DFGNodePort port) {
		this.outputs.put(port.getName(), port);
	}

	public DFGNodePort getInputByName(String portName) {
		return inputs.get(portName);
	}

	public DFGNodePort getOutputByName(String portName) {
		return outputs.get(portName);
	}

	public int getNumberOfInputs() {
		return inputs.size();
	}

	public int getNumberOfOutputs() {
		return outputs.size();
	}

	public Set<String> getInputsName() {
		return inputs.keySet();
	}

	public Set<String> getOutputsName() {
		return outputs.keySet();
	}

	public void removeInputByName(String name) {
		inputs.remove(name);
	}

	public void removeOutputByName(String name) {
		outputs.remove(name);
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

	public boolean check() {
		return false;
	}

}
