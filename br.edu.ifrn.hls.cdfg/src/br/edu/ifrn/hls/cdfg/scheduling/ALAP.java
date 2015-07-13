package br.edu.ifrn.hls.cdfg.scheduling;

import java.rmi.server.Operation;
import java.util.ArrayList;
import java.util.List;

import javax.xml.soap.Node;

import br.edu.ifrn.hls.cdfg.dfg.DFG;
import br.edu.ifrn.hls.cdfg.dfg.DFGInputNode;
import br.edu.ifrn.hls.cdfg.dfg.DFGNode;
import br.edu.ifrn.hls.cdfg.dfg.DFGNodePort;
import br.edu.ifrn.hls.cdfg.dfg.DFGOperationNode;
import br.edu.ifrn.hls.cdfg.dfg.DFGOutputNode;
import br.edu.ifrn.hls.cdfg.dfg.DFGVertex;
import br.edu.ifrn.hls.cdfg.visualization.DFGDOTFormatGenerator;

public class ALAP {

	public void schedule(DFG dfg) {
		ASAP asap = new ASAP();
		asap.schedule(dfg);
		removeALAP(dfg);
		equalizeIO(dfg);
		scheduleOutputs(dfg);
		scheduleOperations(dfg);
		scheduleFinal(dfg);
	}

	private void removeALAP(DFG dfg) {
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
	
	private void scheduleOperations(DFG dfg) {
		boolean temp = true;
		while(temp){
			temp = false;
			for(DFGOperationNode op : dfg.getOperations().values()){
				for(DFGNodePort node : op.getOutputs().values()){
					int maior = 0;
					for(DFGNode target : node.getConnectedTo().getTargetsNodes())
						if(target instanceof DFGOperationNode){
							DFGOperationNode oper = (DFGOperationNode) target;
							if(oper.getTags().get("alap") != null){
								int valor = Integer.parseInt(oper.getTags().get("alap"));
								if(valor > maior){
									op.getTags().put("alap", String.valueOf(--valor));
									maior = valor;
								}
							} else {
								temp = false;
							}
					}
				}
			}
		}
	}

	private void scheduleOutputs(DFG dfg){
		for(DFGOutputNode out : dfg.getOutputs().values()){
			for(DFGNodePort input : out.getInputs().values()){
				int value =  Integer.parseInt(out.getTags().get("alap"));
				input.getConnectedTo().getSource().getNode().getTags().put("alap", String.valueOf(value-1));
			}
		}
	}
	
	private void equalizeIO(DFG dfg){
		int bigger = 0;
		for(DFGOutputNode out : dfg.getOutputs().values()){
			if(Integer.parseInt(out.getTags().get("asap")) > bigger){
				bigger = Integer.parseInt(out.getTags().get("asap"));
			}
		}
		for(DFGOutputNode out : dfg.getOutputs().values()){
			out.getTags().put("alap", String.valueOf(bigger));
		}
	}

	private void scheduleFinal(DFG dfg) {
		for(DFGOperationNode op : dfg.getOperations().values()){
			if(op.getTags().get("alap") == null){
				op.getTags().put("alap", "1");
			}
		}
		for(DFGInputNode in : dfg.getInputs().values()){
				in.getTags().put("alap", "0");
		}
	}
}
