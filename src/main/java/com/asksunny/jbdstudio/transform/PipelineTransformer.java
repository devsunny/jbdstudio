package com.asksunny.jbdstudio.transform;

import com.asksunny.jbdstudio.sql.ColumnMetaData;

public class PipelineTransformer 
{
	ColumnMetaData[] columnMetadata = null;
	
	public PipelineTransformer(ColumnMetaData[] metadata)
	{
		this.columnMetadata = metadata;
	}
	
	public boolean processDataRow(Object[] data){
		
		return false;
	}
}
