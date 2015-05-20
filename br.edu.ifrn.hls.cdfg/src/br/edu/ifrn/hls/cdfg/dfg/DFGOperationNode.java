package br.edu.ifrn.hls.cdfg.dfg;

import java.util.Map;

import br.edu.ifrn.hls.cdfg.libFunction.Function;

public class DFGOperationNode extends DFGNode {

	private Function function;
	private Map<String, DFGVertex> inputMap;
	private Map<String, DFGVertex> outputMap;
	private Map<String, String> inputTypes;
	private Map<String, String> outputTypes;

	boolean check() {
		return false;
	}

}
