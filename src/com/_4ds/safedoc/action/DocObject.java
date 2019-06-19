package com._4ds.safedoc.action;

public class DocObject {
	byte[] m_byte;
	String m_name;
	String m_filename;
	public String get_filename() {
		return m_filename;
	}
	public void set_filename(String m_filename) {
		this.m_filename = m_filename;
	}
	public byte[] get_byte() {
		return m_byte;
	}
	public void set_byte(byte[] m_byte) {
		this.m_byte = m_byte;
	}
	public String get_name() {
		return m_name;
	}
	public void set_name(String m_name) {
		this.m_name = m_name;
	}
}
