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

  private List<String> ready = new ArrayList<String>();
  private int largerAsap = 0;
  private int total;

	public void schedule(DFG dfg) {
		ASAP asap = new ASAP();
		asap.schedule(dfg);
		total = dfg.getInputs().size() + dfg.getOutputs().size() 
		  + dfg.getOperations().size();
		System.out.println(total);
		removeALAP(dfg);
    equalizeOutputs(dfg);
	  scheduleInputs(dfg);
	  scheduleOperations(dfg);
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

	private void equalizeOutputs(DFG dfg) {
	  for (DFGOutputNode out : dfg.getOutputs().values()) {
      ready.add(out.getName());
      if (Integer.valueOf(out.getTags().get("asap")) > largerAsap) {
        largerAsap = Integer.valueOf(out.getTags().get("asap"));
      }
	  }
	  for (DFGOutputNode out : dfg.getOutputs().values()) {
	    out.getTags().put("alap", String.valueOf(largerAsap));
	  }
	}
  private void scheduleInputs(DFG dfg) {
    for(DFGInputNode in : dfg.getInputs().values()) {
      in.getTags().put("alap", "0");
      ready.add(in.getName());
    }
  }

  private void scheduleOperations(DFG dfg) {
    while(ready.size() < total) {
      for (DFGOperationNode op : dfg.getOperations().values()) {
        if(!ready.contains(op.getName())){
          for(DFGNodePort port : op.getOutputs().values()) {
            int smallAlap = 10;
            for(DFGNode node : port.getConnectedTo().getTargetsNodes()){
              if (node.getTags().get("alap") != null) {
                if (Integer.valueOf(node.getTags().get("alap")) < smallAlap) {
                  smallAlap = Integer.valueOf(node.getTags().get("alap"));
                  op.getTags().put("alap", String.valueOf(smallAlap-1));
                  if (!ready.contains(op.getName())) {
                    ready.add(op.getName());
                  }
                }
              }
            }
          }
        }
      }
    }
  }
}

