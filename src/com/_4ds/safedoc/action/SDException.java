package com._4ds.safedoc.action;

public class SDException extends java.lang.Exception{
	private static final long serialVersionUID = 1L;
	public int m_ErrorCode;
	public SDException(int x_ErrorCode, String message) {
		super(message);
		m_ErrorCode=x_ErrorCode;
	}
	//String ErrorText;
}
