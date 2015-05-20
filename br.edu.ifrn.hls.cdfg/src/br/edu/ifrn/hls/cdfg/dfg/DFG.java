package br.edu.ifrn.hls.cdfg.dfg;

import java.util.List;
import java.util.Map;

public class DFG {

	private String name;
	private List<DFGOperationNode> nodes;
	private List<DFGVertex> vertex;
	private List<DFGInputNode> inputs;
	private List<DFGOutputNode> outputs;

	private Map<String, Object> tags;

	boolean check() {
		return false;
	}

}
