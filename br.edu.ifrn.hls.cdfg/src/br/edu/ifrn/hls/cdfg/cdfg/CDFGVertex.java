package br.edu.ifrn.hls.cdfg.cdfg;

import java.util.HashMap;
import java.util.Map;

public class CDFGVertex {

	private Map<String, String> tags;

	public CDFGVertex() {
		this.tags = new HashMap<String, String>();
	}

	public String toYAML() {
		// TODO Auto-generated method stub
		return "";
	}

	public Map<String, String> getTags() {
		return tags;
	}

}
