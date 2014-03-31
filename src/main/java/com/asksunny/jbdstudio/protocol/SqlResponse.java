package com.asksunny.jbdstudio.protocol;

import java.util.List;

/*
 * <jdbi>
 * 	<request>
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
 * 	</request>
 * </jdbi>
 * 
 * <jdbi>
 * 	<response>
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
 * 	</response>
 * </jdbi>
 * 
 * 
 */

public class SqlResponse {
	int requestId;
	List<String> responseBody;	
}
