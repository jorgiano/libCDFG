package br.edu.ifrn.hls.cdfg.visualization;

import java.util.Map;

import br.edu.ifrn.hls.cdfg.dfg.DFG;
import br.edu.ifrn.hls.cdfg.dfg.DFGInputNode;
import br.edu.ifrn.hls.cdfg.dfg.DFGNode;
import br.edu.ifrn.hls.cdfg.dfg.DFGNodePort;
import br.edu.ifrn.hls.cdfg.dfg.DFGOperationNode;
import br.edu.ifrn.hls.cdfg.dfg.DFGOutputNode;
import br.edu.ifrn.hls.cdfg.dfg.DFGVertex;

public class DFGDOTFormatGenerator {

	public DFGDOTFormatGenerator() {

	}

	private DFG dfg;

	public String generate(DFG dfg) {
		StringBuilder sb = new StringBuilder();
		this.dfg = dfg;
		sb.append("digraph ");
		sb.append(dfg.getName()).append("\n");
		sb.append("{\n");
		buildAll(dfg, sb);
		sb.append("}");
		return sb.toString();
	}

	private void buildAll(DFG dfg, StringBuilder sb) {
		buildInputs(dfg.getInputs(), sb);
		buildOutputs(dfg.getOutputs(), sb);
		buildNodes(dfg.getOperations(), sb);
		buildRanks(dfg, sb);
		buildVertices(dfg, sb);

	}

	private void buildRanks(DFG dfg, StringBuilder sb) {
		sb.append("\n  {rank=source");
		for (DFGInputNode input : dfg.getInputs().values()) {
			sb.append(";\"");
			sb.append(input.getName());
			sb.append("\"");
		}
		sb.append("}\n");
		sb.append("  {rank=sink");
		for (DFGOutputNode output : dfg.getOutputs().values()) {
			sb.append(";\"");
			sb.append(output.getName());
			sb.append("\"");
		}
		sb.append("}\n");

	}

	private void buildVertices(DFG dfg, StringBuilder sb) {
		Map<String, DFGVertex> vertices = dfg.getVertices();
		for (DFGVertex vertex : vertices.values()) {
			sb.append("  ");
			for (DFGNode target : vertex.getTargetsNodes()) {
				if (vertex.getSource().getNode() instanceof DFGInputNode) {
          sb.append("\"");
					sb.append(vertex.getSource().getName());
          sb.append("\"");
				} else if (vertex.getSource().getNode() instanceof DFGOperationNode) {
					DFGOperationNode op = (DFGOperationNode) vertex.getSource()
							.getNode();
					sb.append(op.getName());
				}
				sb.append("->");
				if (target instanceof DFGOutputNode) {
					sb.append("\"");
					sb.append(((DFGOutputNode) target).getName());
					sb.append("\"");
				} else if (target instanceof DFGOperationNode) {
					DFGOperationNode op = (DFGOperationNode) target;
					sb.append(op.getName());
				}
				sb.append(";\n");
			}
			sb.append("\n");
		}
	}

	private void buildOutputs(Map<String, DFGOutputNode> outputs,
			StringBuilder sb) {
		for (DFGOutputNode output : outputs.values()) {
			sb.append("  ");
			sb.append(output.getName());
			sb.append("[shape=box];\n");
		}
	}

	private void buildNodes(Map<String, DFGOperationNode> operations,
			StringBuilder sb) {
		for (DFGOperationNode op : operations.values()) {
			sb.append("  ");
			sb.append(op.getName());
			sb.append("[label=\"");
			sb.append(op.getFunction().getMnemonic());
			sb.append("\"];\n");
		}
	}

	private void buildInputs(Map<String, DFGInputNode> inputs, StringBuilder sb) {
		for (DFGInputNode input : inputs.values()) {
			sb.append("  ");
			sb.append(input.getName());
			sb.append("[shape=box];\n");
		}
	}

}
