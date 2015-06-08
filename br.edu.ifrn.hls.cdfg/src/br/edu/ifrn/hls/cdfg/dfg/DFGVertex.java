package br.edu.ifrn.hls.cdfg.dfg;

import java.util.List;
import java.util.Map;

public class DFGVertex {

	private DFGOperationNode from;
	private List<DFGOperationNode> to;
	private Map<String, Object> tags;

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

}
