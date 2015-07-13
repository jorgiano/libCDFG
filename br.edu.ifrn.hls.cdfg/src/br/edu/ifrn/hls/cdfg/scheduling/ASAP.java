package br.edu.ifrn.hls.cdfg.scheduling;

import br.edu.ifrn.hls.cdfg.dfg.DFG;
import br.edu.ifrn.hls.cdfg.dfg.DFGInputNode;
import br.edu.ifrn.hls.cdfg.dfg.DFGNode;
import br.edu.ifrn.hls.cdfg.dfg.DFGNodePort;
import br.edu.ifrn.hls.cdfg.dfg.DFGOperationNode;
import br.edu.ifrn.hls.cdfg.dfg.DFGOutputNode;

public class ASAP {

	public void schedule(DFG dfg) {
		removeASAP(dfg);
		for (DFGInputNode input : dfg.getInputs().values()) {
			input.getTags().put("asap", "0");
		}

		for (DFGOutputNode output : dfg.getOutputs().values()) {
			scheduleNode(dfg, output);
		}
	}

	private void removeASAP(DFG dfg) {
		for(DFGInputNode in : dfg.getInputs().values()){
			in.getTags().remove("alap");
		}
		for(DFGOperationNode op : dfg.getOperations().values()){
			op.getTags().remove("alap");
		}
		for(DFGOutputNode out : dfg.getOutputs().values()){
			out.getTags().remove("alap");
		}
	}

	private int scheduleNode(DFG dfg, DFGNode node) {
		String asap_s = node.getTags().get("asap");
		if (asap_s != null) {
			return Integer.parseInt(asap_s);
		}
		int asap = 0;
		for (DFGNodePort in : node.getInputs().values()) {
			int asap_entrada = scheduleNode(dfg, in.getConnectedTo()
					.getSource().getNode());
			if (asap_entrada + 1 > asap) {
				asap = asap_entrada + 1;
			}
		}
		node.getTags().put("asap", String.valueOf(asap));
		return asap;

	}

}
