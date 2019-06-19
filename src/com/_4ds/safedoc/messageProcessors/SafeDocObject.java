package com._4ds.safedoc.messageProcessors;

public class SafeDocObject {
	private String m_msgType;
	public String getM_msgType() {
		return m_msgType;
	}
	public void setM_msgType(String type) {
		m_msgType = type;
	}
	private String m_SessionId;
	private byte[] m_Xml;
	public byte[] getM_Xml() {
		return m_Xml;
	}
	public void setM_Xml(byte[] xml) {
		m_Xml = xml;
	}
}
