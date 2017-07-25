<?xml version="1.0" encoding="UTF-8" standalone="no"?>

<xsd:schema xmlns="http://www.springframework.org/schema/util"
		xmlns:xsd="http://www.w3.org/2001/XMLSchema"
		xmlns:beans="http://www.springframework.org/schema/beans"
		xmlns:tool="http://www.springframework.org/schema/tool"
		targetNamespace="http://www.springframework.org/schema/util"
		elementFormDefault="qualified"
		attributeFormDefault="unqualified">

	<xsd:import namespace="http://www.springframework.org/schema/beans" schemaLocation="http://www.springframework.org/schema/beans/spring-beans-2.0.xsd"/>
	<xsd:import namespace="http://www.springframework.org/schema/tool" schemaLocation="http://www.springframework.org/schema/tool/spring-tool-2.0.xsd"/>

	<xsd:element name="constant">
		<xsd:annotation>
			<xsd:documentation>
	Reference a public, static field on a type and expose its value as
	a bean. For example <code>&lt;util:constant static-field=&quot;java.lang.Integer.MAX_VALUE&quot;/&gt;</code>.
			</xsd:documentation>
		</xsd:annotation>
		<xsd:complexType>
			<xsd:attribute name="id" type="xsd:ID"/>
			<xsd:attribute name="static-field" type="xsd:string" use="required"/>
		</xsd:complexType>
	</xsd:element>

	<xsd:element name="property-path">
		<xsd:annotation>
			<xsd:documentation>
	Reference a property on a bean (or as a nested value) and expose its values as
	a bean. For example &lt;util:property-path path=&quot;order.customer.name&quot;/&gt;.
			</xsd:documentation>
		</xsd:annotation>
		<xsd:complexType>
			<xsd:attribute name="id" type="xsd:ID"/>
			<xsd:attribute name="path" type="xsd:string" use="required"/>
		</xsd:complexType>
	</xsd:element>

	<xsd:element name="list">
		<xsd:annotation>
			<xsd:documentation source="java:org.springframework.beans.factory.config.ListFactoryBean">
	Builds a List instance of the specified type, populated with the specified content.
			</xsd:documentation>
			<xsd:appinfo>
				<tool:annotation>
					<tool:exports type="java.util.List"/>
				</tool:annotation>
			</xsd:appinfo>
		</xsd:annotation>
		<xsd:complexType>
			<xsd:complexContent>
				<xsd:extension base="beans:listOrSetType">
					<xsd:attribute name="id" type="xsd:ID"/>
					<xsd:attribute name="list-class" type="xsd:string">
						<xsd:annotation>
							<xsd:appinfo>
								<tool:annotation>
									<tool:expected-type type="java.lang.Class"/>
									<tool:assignable-to type="java.util.List"/>
								</tool:annotation>
							</xsd:appinfo>
						</xsd:annotation>
					</xsd:attribute>
					<xsd:attribute name="scope" type="xsd:string">
						<xsd:annotation>
							<xsd:documentation><![CDATA[
	The scope of this collection bean: typically "singleton" (one shared instance,
	which will be returned by all calls to getBean with the given id), or
	"prototype" (independent instance resulting from each call to getBean).
	Default is "singleton". Further scopes, such as "request" or "session",
	might be supported by extended bean factories (e.g. in a web environment).
							]]></xsd:documentation>
						</xsd:annotation>
					</xsd:attribute>
				</xsd:extension>
			</xsd:complexContent>
		</xsd:complexType>
	</xsd:element>

	<xsd:element name="set">
		<xsd:annotation>
			<xsd:documentation source="java:org.springframework.beans.factory.config.SetFactoryBean">
	Builds a Set instance of the specified type, populated with the specified content.
			</xsd:documentation>
			<xsd:appinfo>
				<tool:annotation>
					<tool:exports type="java.util.Set"/>
				</tool:annotation>
			</xsd:appinfo>
		</xsd:annotation>
		<xsd:complexType>
			<xsd:complexContent>
				<xsd:extension base="beans:listOrSetType">
					<xsd:attribute name="id" type="xsd:ID"/>
					<xsd:attribute name="set-class" type="xsd:string">
						<xsd:annotation>
							<xsd:appinfo>
								<tool:annotation>
									<tool:expected-type type="java.lang.Class"/>
									<tool:assignable-to type="java.util.Set"/>
								</tool:annotation>
							</xsd:appinfo>
						</xsd:annotation>
					</xsd:attribute>
					<xsd:attribute name="scope" type="xsd:string">
						<xsd:annotation>
							<xsd:documentation><![CDATA[
	The scope of this collection bean: typically "singleton" (one shared instance,
	which will be returned by all calls to getBean with the given id), or
	"prototype" (independent instance resulting from each call to getBean).
	Default is "singleton". Further scopes, such as "request" or "session",
	might be supported by extended bean factories (e.g. in a web environment).
							]]></xsd:documentation>
						</xsd:annotation>
					</xsd:attribute>
				</xsd:extension>
			</xsd:complexContent>
		</xsd:com