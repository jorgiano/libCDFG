package br.edu.ifrn.hls.cdfg.builder;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.edu.ifrn.hls.cdfg.cfg.CFG;
import br.edu.ifrn.hls.cdfg.dfg.DFG;

public class CFGBuilder {

	private final static Logger LOGGER = Logger.getLogger(CFGBuilder.class
			.getName());

	private CFGBuilder() {
	}

	public static CFG buildDFGfromYAMLMap(Map<String, Object> cfgData) {
		CFG cfg = new CFG();
		if (cfgData instanceof Map) {
			@SuppressWarnings("unchecked")
			Map<String, Object> cdfgData = (Map<String, Object>) cfgData;
			String name = (String) cdfgData.get("name");
			cfg.setName(name);
			@SuppressWarnings("unchecked")
			Map<String, Object> initDFG = (Map<String, Object>) cdfgData
					.get("init");
			String initDFGName = (String) initDFG.get("name");
			@SuppressWarnings("unchecked")
			Map<String, Object> cdfgNodes = (Map<String, Object>) cdfgData
					.get("nodes");
			LOGGER.log(Level.INFO, "Building init node " + initDFGName);
			@SuppressWarnings("unchecked")
			Map<String, Object> dfgNodes = (Map<String, Object>) cdfgNodes
					.get(initDFGName);
			DFG init = DFGBuilder.buildDFGfromYAMLMap(dfgNodes);
			init.setName(initDFGName);
			cfg.addNode(init);
			cfg.setInitDFG(init);
		}
		return cfg;
	}
}
