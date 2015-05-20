package br.edu.ifrn.hls.cdfg.dfg;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import br.edu.ifrn.hls.cdfg.libFunction.Function;

public class DFGOperationNode extends DFGNode {

	private Function function;
	private Map<String, DFGVertex> inputMap;
	private Map<String, DFGVertex> outputMap;
	private Map<String, String> inputTypes;
	private Map<String, String> outputTypes;
	private Map<String, Object> tags;

	public DFGOperationNode(Function function) {
		this.function = function;
		this.tags = new HashMap<String, Object>();
	}

	public void mapInputs(DFGVertex vertex[]) throws Exception {
		if (vertex.length != this.function.getNUmberOfInputs())
			throw new Exception("Wrong number of Inputs");
		inputMap = new HashMap<String, DFGVertex>();
		for (int i = 0; i < this.function.getNUmberOfInputs(); i++) {
			inputMap.put(this.function.getInputName(i), vertex[i]);
		}
	}

	public void mapOutputs(DFGVertex vertex[]) throws Exception {
		if (vertex.length != this.function.getNumberOfOutputs())
			throw new Exception("Invalid number of Outputs");
		outputMap = new HashMap<String, DFGVertex>();
		for (int i = 0; i < this.function.getNumberOfOutputs(); i++) {
			outputMap.put(this.function.getOutputName(i), vertex[i]);
		}
	}

	public void setInputTypes(String inputTypes[]) throws Exception {
		if (inputTypes.length != this.function.getNUmberOfInputs())
			throw new Exception("Wrong number of Inputs");
		this.inputTypes = new HashMap<String, String>();
		for (int i = 0; i < this.function.getNUmberOfInputs(); i++) {
			this.inputTypes.put(this.function.getInputName(i), inputTypes[i]);
		}
	}

	public void setOutputTypes(String outputTypes[]) throws Exception {
		if (outputTypes.length != this.function.getNumberOfOutputs())
			throw new Exception("Wrong number of Outputs");
		this.outputTypes = new HashMap<String, String>();
		for (int i = 0; i < this.function.getNumberOfOutputs(); i++) {
			this.outputTypes
					.put(this.function.getOutputName(i), outputTypes[i]);
		}
	}

	/* IMPORTANT: adding an exiting tag replaces older data by new one */
	public void addTag(String key, Object data) {
		this.tags.put(key, data);
	}

	public Object getTag(String key) {
		return tags.get(key);
	}

	public Set<String> getTagNames() {
		return tags.keySet();
	}

	public int getNUmberOfTags() {
		return tags.size();
	}

	public void removeTag(String key) {
		tags.remove(key);
	}

	boolean check() {
		return false;
	}

}
