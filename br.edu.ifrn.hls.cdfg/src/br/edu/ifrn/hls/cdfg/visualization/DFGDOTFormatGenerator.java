package br.edu.ifrn.hls.cdfg.visualization;

import java.util.Map;

import br.edu.ifrn.hls.cdfg.dfg.DFG;
import br.edu.ifrn.hls.cdfg.dfg.DFGInputNode;

public class DFGDOTFormatGenerator {

	public DFGDOTFormatGenerator() {

	}

	public String generate(DFG dfg) {
		StringBuilder sb = new StringBuilder();
		sb.append("graph ");
		sb.append(dfg.getName()).append("\n");
		sb.append("{\n");
		buildInputs(dfg.getInputs(), sb);

		return sb.toString();
	}

	private void buildInputs(Map<String, DFGInputNode> inputs, StringBuilder sb) {
		for (DFGInputNode input : inputs.values()) {
			sb.append(" ");
			sb.append(input.getName());
			sb.append("\n");
		}
	}
}
