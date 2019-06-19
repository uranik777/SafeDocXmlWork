package com._4ds.safedoc.action;

public class FileOperations {
	public boolean isDir(String x_string) {
		if(containsDisk(x_string)==false)
		{
			if(containsDir(x_string))
			{
				if(containsFilename(x_string)==false)
				{
					return true;
				}
			}
		}
		return false;	
	}






	public boolean isDiskDir(String x_string) {
		if(containsDisk(x_string))
		{
			if(containsDir(x_string))
			{
				if(containsFilename(x_string)==false)
				{
					return true;
				}
			}
		}
		return false;	
	}






	public boolean isFilename(String x_string) {
		if(containsDisk(x_string)==false)
		{
			if(containsDir(x_string)==false)
			{
				if(containsFilename(x_string))
				{
					return true;
				}
			}
		}
		return false;	
	}






	public boolean isDirFilename(String x_string) {
		if(containsDisk(x_string)==false)
		{
			if(containsDir(x_string))
			{
				if(containsFilename(x_string))
				{
					return true;
				}
			}
		}
		return false;	
	}






	public boolean isDiskDirFilename(String x_string) {
		if(containsDisk(x_string))
		{
			if(containsDir(x_string))
			{
				if(containsFilename(x_string))
				{
					return true;
				}
			}
		}
		return false;
	}






	public boolean containsFilename(String x_string) {
		if(containsDir(x_string)==false && containsDisk(x_string)==false && x_string.length()>0) {
			return true;
		}
		
		if(containsDir(x_string)==true){
			if(x_string.endsWith("\\") ||  x_string.endsWith("/")) {
				return false;
			}
			else{
				return true;
			}
				
		}
		return false;
	}






	public boolean containsDir(String x_string) {
		if(x_string.contains("/") || x_string.contains("\\")){
			return true;
		}
		return false;
	}






	public boolean containsDisk(String x_string) {
		if(x_string.contains(":")){
			return true;
		}
		return false;
	}
	
	public String checkFolder(String path) {
		java.io.File file=new java.io.File(path);
		java.io.File fld=new java.io.File(file.getPath() );
		if(fld.exists()==false){
			fld.mkdirs();
		}
		return fld.getPath()+"\\";
	}    

	
	

}
