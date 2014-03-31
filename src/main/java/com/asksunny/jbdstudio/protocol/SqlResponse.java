package com.asksunny.jbdstudio.protocol;

import java.util.List;

/*
 * 	<sqlrequest>
 *   	<id>123456</id>
 *   	<printHeader>true</printHeader>
 *      <printMeta>true</printMeta>
 *      <printMetaOnly>true</printMetaOnly>
 *      <sql><[CDATA[
 *      	SELECT * FROM TEST
 *      ]]></sql>
 *      <sql><[CDATA[
 *      	SELECT * FROM TEST
 *      ]]></sql>
 *      <sql><[CDATA[
 *      	SELECT * FROM TEST
 *      ]]></sql>
 *      <sql><[CDATA[
 *      	SELECT * FROM TEST
 *      ]]></sql>
 *	</sqlrequest>
 * 
 * 	<sqlresponse>
 *   	<id>123456</id> *   	
 *      <resultset><[CDATA[
 *      1|Sunny|Liu
 *      2|John|Doe
 *       ]]></resultset>
 *        <resultset><[CDATA[
 *      1|Sunny|Liu
 *      2|John|Doe
 *       ]]></resultset>
 *        <resultset><[CDATA[
 *      1|Sunny|Liu
 *      2|John|Doe
 *       ]]></resultset>
 * 	</sqlresponse>
 * 
 * 
 */

public class SqlResponse 
{
	int requestId;
	List<String> responseBody;
	public int getRequestId() {
		return requestId;
	}
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	public List<String> getResponseBody() {
		return responseBody;
	}
	public void setResponseBody(List<String> responseBody) {
		this.responseBody = responseBody;
	}	
	
	
}
