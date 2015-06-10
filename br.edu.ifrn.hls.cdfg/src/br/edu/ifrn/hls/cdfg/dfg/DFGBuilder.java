package br.edu.ifrn.hls.cdfg.dfg;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.edu.ifrn.hls.cdfg.libFunction.Function;
import br.edu.ifrn.hls.cdfg.libFunction.FunctionsLib;

public class DFGBuilder {

	private final static Logger LOGGER = Logger.getLogger(DFGBuilder.class
			.getName());

	private DFGBuilder() {
	}

	public static DFG buildDFGfromYAMLMap(Map<String, Object> dfgData) {
		DFG dfg = new DFG();
		LOGGER.log(Level.INFO, "Building DFG from " + dfgData.toString());

		@SuppressWarnings("unchecked")
		Map<String, Object> nodes = (Map<String, Object>) dfgData.get("nodes");
		buildDFGNodes(dfg, nodes);
		@SuppressWarnings("unchecked")
		Map<String, Object> vertices = (Map<String, Object>) dfgData
				.get("vertices");
		buildDFGVertices(dfg, vertices);
		@SuppressWarnings("unchecked")
		Map<String, Object> tags = (Map<String, Object>) dfgData.get("tags");
		buildDFGTags(dfg, tags);
		return dfg;
	}

	private static void buildDFGNodes(DFG dfg, Map<String, Object> nodes) {
		@SuppressWarnings("unchecked")
		Map<String, Object> inputs = (Map<String, Object>) nodes.get("inputs");
		buildDFGInputs(dfg, inputs);
		@SuppressWarnings("unchecked")
		Map<String, Object> outputs = (Map<String, Object>) nodes
				.get("outputs");
		buildDFGOutputs(dfg, outputs);
		@SuppressWarnings("unchecked")
		Map<String, Object> operations = (Map<String, Object>) nodes
				.get("operations");
		buildDFGOperations(dfg, operations);

	}

	private static void buildDFGInputs(DFG dfg, Map<String, Object> inputs) {
		for (String inputName : inputs.keySet()) {
			@SuppressWarnings("unchecked")
			Map<String, Object> inputData = (Map<String, Object>) inputs
					.get(inputName);
			String type = (String) inputData.get("type");
			DFGInputNode input = new DFGInputNode(inputName, type);
			@SuppressWarnings("unchecked")
			Map<String, Object> tags = (Map<String, Object>) inputData
					.get("tags");
			if (tags != null)
				for (String tagName : tags.keySet()) {
					input.addTag(tagName, tags.get(tagName));
				}
			dfg.addInputNode(input);
		}

	}

	private static void buildDFGOutputs(DFG dfg, Map<String, Object> outputs) {
		for (String outputName : outputs.keySet()) {
			@SuppressWarnings("unchecked")
			Map<String, Object> outputData = (Map<String, Object>) outputs
					.get(outputName);
			String type = (String) outputData.get("type");
			DFGOutputNode output = new DFGOutputNode(outputName, type);
			@SuppressWarnings("unchecked")
			Map<String, Object> tags = (Map<String, Object>) outputData
					.get("tags");
			if (tags != null)
				for (String tagName : tags.keySet()) {
					output.addTag(tagName, tags.get(tagName));
				}
			dfg.addOutputNode(output);
		}
	}

	private static void buildDFGOperations(DFG dfg,
			Map<String, Object> operations) {
		for (String operationName : operations.keySet()) {
			@SuppressWarnings("unchecked")
			Map<String, Object> nodeData = (Map<String, Object>) operations
					.get(operationName);
			String functionName = (String) nodeData.get("function");
			Function function = FunctionsLib.getFunctionsLib().getFunction(
					functionName);
			DFGOperationNode node = new DFGOperationNode(function);
			node.setName(operationName);
			@SuppressWarnings("unchecked")
			Map<String, Object> inputs = (Map<String, Object>) nodeData
					.get("inputs");
			buildDFGNodeInputPorts(node, inputs);
			@SuppressWarnings("unchecked")
			Map<String, Object> outputs = (Map<String, Object>) nodeData
					.get("outputs");
			buildDFGNodeOutputPorts(node, outputs);
			dfg.addOperationNode(node);

		}
	}

	private static void buildDFGNodeOutputPorts(DFGOperationNode node,
			Map<String, Object> outputs) {
		for (Object o : outputs.keySet()) {
			String outputName = (String) o;
			String type = (String) outputs.get(outputName);
			DFGNodePort port = new DFGNodePort();
			port.setName(outputName);
			port.setType(type);
			port.setNode(node);
			node.addOutput(port);
		}
	}

	private static void buildDFGNodeInputPorts(DFGOperationNode node,
			Map<String, Object> inputs) {
		for (Object o : inputs.keySet()) {
			String inputName = (String) o;
			LOGGER.log(Level.FINE, "inputname");
			String type = (String) inputs.get(inputName);
			DFGNodePort port = new DFGNodePort();
			port.setName(inputName);
			port.setType(type);
			port.setNode(node);
			node.addInput(port);
		}
	}

	private static void buildDFGVertices(DFG dfg, Map<String, Object> vertices) {
		for (String vertexName : vertices.keySet()) {
			@SuppressWarnings("unchecked")
			Map<String, Object> vertexData = (Map<String, Object>) vertices
					.get(vertexName);

			@SuppressWarnings("unchecked")
			Map<String, String> source = (Map<String, String>) vertexData
					.get("source");
			String type = source.get("type");
			String name = source.get("name");
			DFGVertex vertex = new DFGVertex(vertexName);
			buildVertexSource(dfg, source, type, name, vertex);
			@SuppressWarnings("unchecked")
			List<Map<String, String>> targets = (List<Map<String, String>>) vertexData
					.get("targets");
			buildVertexTargets(dfg, vertex, targets);
			@SuppressWarnings("unchecked")
			Map<String, Object> tags = (Map<String, Object>) vertexData
					.get("tags");
			buildVertexTags(vertex, tags);
			dfg.addVertex(vertex);
		}
	}

	private static void buildVertexTags(DFGVertex vertex,
			Map<String, Object> tags) {
	}

	private static void buildVertexTargets(DFG dfg, DFGVertex vertex,
			List<Map<String, String>> targets) {
		for (Map<String, String> target : targets) {
			String targetType = target.get("type");
			String targetName = target.get("name");
			if (targetType.equals("input")) {
				try {
					throw new Exception("Vertex source cannot be an input node");
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (targetType.equalsIgnoreCase("output")) {
				DFGNodePort outputPort = dfg.getOutputByName(targetName)
						.getInputByName(targetName);
				vertex.addTarget(outputPort);
				outputPort.setConnectedTo(vertex);

			} else if (targetType.equals("operation")) {
				DFGNodePort operationPort;
				String portName = target.get("port");
				DFGOperationNode op = dfg.getOperationByName(targetName);
				operationPort = op.getInputByName(portName);
				vertex.addTarget(operationPort);
				operationPort.setConnectedTo(vertex);
			}
		}
	}

	private static void buildVertexSource(DFG dfg, Map<String, String> source,
			String type, String name, DFGVertex vertex) {
		if (type.equals("input")) {
			DFGNodePort inputPort = dfg.getInputByName(name).getOutputByName(
					name);
			vertex.setFrom(inputPort);
			inputPort.setConnectedTo(vertex);
		} else if (type.equalsIgnoreCase("output")) {
			try {
				throw new Exception("Vertex source cannot be an output node");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (type.equals("operation")) {
			DFGNodePort operationPort;
			String portName = source.get("port");
			operationPort = dfg.getOperationByName(name).getOutputByName(
					portName);
			vertex.setFrom(operationPort);
			operationPort.setConnectedTo(vertex);
		}
	}

	private static void buildDFGTags(DFG dfg, Map<String, Object> tags) {
		// TODO Auto-generated method stub

	}

}
