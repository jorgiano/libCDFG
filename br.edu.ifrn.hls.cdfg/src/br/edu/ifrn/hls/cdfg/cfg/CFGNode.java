package br.edu.ifrn.hls.cdfg.cfg;

import java.util.logging.Logger;

import br.edu.ifrn.hls.cdfg.dfg.DFG;

public class CFGNode {

	private final static Logger LOGGER = Logger.getLogger(CFGNode.class
			.getName());

	private DFG dfg;

	public DFG getDfg() {
		return dfg;
	}

	public void setDfg(DFG dfg) {
		this.dfg = dfg;
	}

	public CFGNode() {

	}

}
