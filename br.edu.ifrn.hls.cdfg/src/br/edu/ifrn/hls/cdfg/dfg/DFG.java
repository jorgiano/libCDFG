package br.edu.ifrn.hls.cdfg.dfg;

import java.util.List;
import java.util.Map;

public class DFG {

	private String name;
	private List<DFGNode> nodes;
	private List<DFGVertex> vertex;
	private List<DFGNode> sinks;
	private List<DFGNode> sources;

	private Map<String, Object> tags;

	boolean check() {
		return false;
	}

}
