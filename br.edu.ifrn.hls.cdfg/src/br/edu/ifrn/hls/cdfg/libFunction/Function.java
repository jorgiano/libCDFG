package br.edu.ifrn.hls.cdfg.libFunction;

import java.util.HashMap;
import java.util.Map;

public class Function {

	private String mnemonic;
	private String name;
	private String[] inputs;
	private String[] outputs;
	private Map<String, Object> tags;

	/*
	 * TODO: Name should not be null
	 */
	public Function(String name, int numberOfInputs, int numberOfOutputs) {
		init(name, numberOfInputs, numberOfOutputs, name);
	}

	public Function(String name, int numberOfInputs, int numberOfOutputs,
			String mnemonic) {
		init(name, numberOfInputs, numberOfOutputs, mnemonic);
	}

	private void init(String name, int numberOfInputs, int numberOfOutputs,
			String mnemonic) {
		this.mnemonic = mnemonic;
		this.name = name;
		inputs = new String[numberOfInputs];
		for (int i = 0; i < numberOfInputs; i++) {
			inputs[i] = "i" + i;
		}
		outputs = new String[numberOfOutputs];
		for (int i = 0; i < numberOfOutputs; i++) {
			outputs[i] = "o" + i;
		}
		tags = new HashMap<String, Object>();
	}

	public void setmnemonic(String mnemonic) {
		this.mnemonic = mnemonic;
	}

	public String getMnemonic() {
		return this.mnemonic;
	}

	public int getNUmberOfInputs() {
		return inputs.length;
	}

	public String getInputName(int index) {

		String name = null;
		if ((index >= 0) && (index < inputs.length))
			name = inputs[index];
		return name;
	}

	public void setInputName(int index, String name) {
		if ((index >= 0) && (index < inputs.length))
			inputs[index] = name;
	}

	public String getOutputName(int index) {
		String name = null;
		if ((index >= 0) && (index < outputs.length))
			name = outputs[index];
		return name;
	}

	public void setOutputName(int index, String name) {
		if ((index >= 0) && (index < outputs.length))
			outputs[index] = name;
	}

	public int getNumberOfOutputs() {
		return outputs.length;
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

	public String toString() {
		String sep = " ";
		StringBuilder sb = new StringBuilder();
		sb.append(this.getName()).append(" (").append(this.getMnemonic())
				.append(") {\n  inputs: ");
		for (int i = 0; i < inputs.length; i++) {
			sb.append(sep).append(inputs[i]);
			sep = ", ";
		}
		sb.append("\n  outputs: ");
		sep = " ";
		for (int i = 0; i < outputs.length; i++) {
			sb.append(sep).append(outputs[i]);
			sep = ", ";
		}
		sb.append("\n  tags: ");
		sep = "";
		sb.append("{");
		if (tags.size() > 0) {
			for (String tag : tags.keySet()) {
				sb.append(sep).append(tag).append(": ").append(tags.get(tag));
				sep = ", ";
			}
		}
		sb.append("}");
		sb.append("\n}");
		return sb.toString();
	}
}
