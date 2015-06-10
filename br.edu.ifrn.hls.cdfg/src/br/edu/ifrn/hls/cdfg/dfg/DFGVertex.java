package br.edu.ifrn.hls.cdfg.dfg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DFGVertex {

	private String name;
	private DFGNodePort source;
	private List<DFGNodePort> targets;
	private Map<String, Object> tags;

	public DFGVertex(String name) {
		setName(name);
		init();
	}

	private void init() {
		targets = new ArrayList<DFGNodePort>();
		tags = new HashMap<String, Object>();

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

	public DFGNodePort getFrom() {
		return source;
	}

	public void setFrom(DFGNodePort from) {
		this.source = from;
	}

	public int numberOfTargets() {
		return targets.size();
	}

	public DFGNodePort getTarget(int index) {
		return targets.get(index);
	}

	public void addTarget(DFGNodePort target) {
		this.targets.add(target);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
