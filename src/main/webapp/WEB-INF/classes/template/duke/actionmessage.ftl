<#--
/*
 * $Id: actionmessage.ftl 805635 2009-08-19 00:18:54Z musachy $
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
-->
<#if (actionMessages?? && actionMessages?size > 0 && !parameters.isEmptyList)>
	<div class="alert alert-info fade in">
		<button type="button" class="close close-sm" data-dismiss="alert">
			<i class="icon-remove"></i>
		</button>
		
		<#if (actionMessages?size > 1)>
		<ul<#rt/>
			<#if parameters.id?if_exists != "">
			 id="${parameters.id?html}"<#rt/>
			</#if>
			
			<#if parameters.cssClass??>
			 class="${parameters.cssClass?html}"<#rt/>
			<#else>
			 class="actionMessage"<#rt/>
			</#if>
			
			<#if parameters.cssStyle??>
			 style="${parameters.cssStyle?html}"<#rt/>
			</#if>
			>
		</#if>
			
				<#list actionMessages as message>
		            <#if message?if_exists != "">
		            	<#if (actionMessages?size > 1)><li></#if>
		                	<span><#if parameters.escape>${message!?html}<#else>${message!}</#if></span>
		                <#if (actionMessages?size > 1)></li></#if>
		            </#if>
				</#list>
		<#if (actionMessages?size > 1)></ul></#if>
	</div>
</#if>