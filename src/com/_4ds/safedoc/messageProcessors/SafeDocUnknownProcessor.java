package com._4ds.safedoc.messageProcessors;

import com._4ds.safedoc.processing.SafeDocError;
import com._4ds.safedoc.processing.SafeDocMessageProcessor;
import com._4ds.safedoc.processing.impl.SafeDocErrorImpl;
import com._4ds.safedoc.processing.impl.SafeDocMessageProcessorImpl;

public class SafeDocUnknownProcessor extends SafeDocMessageProcessorImpl implements SafeDocMessageProcessor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SafeDocUnknownProcessor() throws Exception {
		super();
	}

	public void execute() {
		m_Error= new SafeDocErrorImpl("005",SafeDocError.K_ERROR);
	}

	public Object getReply() {
		return "Unknown request";
	}
}
