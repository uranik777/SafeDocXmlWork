package com._4ds.eclient.safedoc.businesslogic.function;

import com._4ds.eclient.NskCall.EClientFunction;

public class EClientSafeDocFunction extends EClientFunction {
	private static final long serialVersionUID = -6048348135951275739L;

	public EClientSafeDocFunction() {
		super();
	}

	public void docHeaderUpdateStatus(){
		m_serverClass="DOCHDMK-SERVER";
		m_TMFStatus=false;
		m_transactionCode="UWS";
		super.execute();
	}
}
