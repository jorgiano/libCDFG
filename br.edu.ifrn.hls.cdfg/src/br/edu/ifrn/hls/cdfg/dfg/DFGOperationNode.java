package br.edu.ifrn.hls.cdfg.dfg;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import br.edu.ifrn.hls.cdfg.libFunction.Function;

public class DFGOperationNode extends DFGNode {

	private Function function;

	private String name;

	private Map<String, Object> tags;

	public DFGOperationNode(Function function) {
		this.function = function;
		this.tags = new HashMap<String, Object>();

	}

	public Function getFunction() {
		return this.function;
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

	public int getNumberOfTags() {
		return tags.size();
	}

	public void removeTag(String key) {
		tags.remove(key);
	}

	public boolean check() {
		return false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
