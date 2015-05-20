package br.edu.ifrn.hls.cdfg.cdfg;

import java.util.List;

import br.edu.ifrn.hls.cdfg.dfg.DFG;

public class CDFG {

	private List<DFG> nodes;

	static boolean checkType(String typeName) {
		if (typeName.equals("i16") || typeName.equals("i32"))
			return true;
		return false;
	}

}
