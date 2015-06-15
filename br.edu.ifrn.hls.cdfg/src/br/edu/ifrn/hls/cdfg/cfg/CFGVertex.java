package br.edu.ifrn.hls.cdfg.cfg;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class CFGVertex {

	private final static Logger LOGGER = Logger.getLogger(CFGVertex.class
			.getName());

	private CFGNode source;
	private CFGNode target;

	private Map<String, String> tags;

	public CFGVertex() {
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
