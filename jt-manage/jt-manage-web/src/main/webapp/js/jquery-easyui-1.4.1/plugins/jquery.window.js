y()" title="class or interface in java.lang">notify</A>, <A HREF="http://java.sun.com/j2se/1.4.2/docs/api/java/lang/Object.html#notifyAll()" title="class or interface in java.lang">notifyAll</A>, <A HREF="http://java.sun.com/j2se/1.4.2/docs/api/java/lang/Object.html#wait()" title="class or interface in java.lang">wait</A>, <A HREF="http://java.sun.com/j2se/1.4.2/docs/api/java/lang/Object.html#wait(long)" title="class or interface in java.lang">wait</A>, <A HREF="http://java.sun.com/j2se/1.4.2/docs/api/java/lang/Object.html#wait(long, int)" title="class or interface in java.lang">wait</A></CODE></TD>
</TR>
</TABLE>
&nbsp;
<P>

<!-- ========= CONSTRUCTOR DETAIL ======== -->

<A NAME="constructor_detail"><!-- --></A>
<TABLE BORDER="1" WIDTH="100%" CELLPADDING="3" CELLSPACING="0" SUMMARY="">
<TR BGCOLOR="#CCCCFF" CLASS="TableHeadingColor">
<TH ALIGN="left" COLSPAN="1"><FONT SIZE="+2">
<B>Constructor Detail</B></FONT></TH>
</TR>
</TABLE>

<A NAME="DirectoryWalker.CancelException(java.io.File, int)"><!-- --></A><H3>
DirectoryWalker.CancelException</H3>
<PRE>
public <B>DirectoryWalker.CancelException</B>(<A HREF="http://java.sun.com/j2se/1.4.2/docs/api/java/io/File.html" title="class or interface in java.io">File</A>&nbsp;file,
                                       int&nbsp;depth)</PRE>
<DL>
<DD>Constructs a <code>CancelException</code> with
 the file and depth when cancellation occurred.
<P>
<DL>
<DT><B>Parameters:</B><DD><CODE>file</CODE> - the file when the operation was cancelled, may be null<DD><CODE>depth</CODE> - the depth when the operation was cancelled, may be null</DL>
</DL>
<HR>

<A NAME="DirectoryWalker.CancelException(java.lang.String, java.io.File, int)"><!-- --></A><H3>
DirectoryWalker.CancelException</H3>
<PRE>
public <B>DirectoryWalker.CancelException</B>(<A HREF="http://java.sun.com/j2se/1.4.2/docs/api/java/lang/String.html" title="class or interface in java.lang">String</A>&nbsp;message,
                                       <A HREF="http://java.sun.com/j2se/1.4.2/docs/api/java/io/File.html" title="class or interface in java.io">File</A>&nbsp;file,
                                       int&nbsp;depth)</PRE>
<DL>
<DD>Constructs a <code>CancelException</code> with
 an appropriate message and the file and depth when
 cancellation occurred.
<P>
<DL>
<DT><B>Parameters:</B><DD><CODE>message</CODE> - the detail message<DD><CODE>file</CODE> - the file when the operation was cancelled<DD><CODE>depth</CODE> - the depth when the operation was cancelled</DL>
</DL>

<!-- ============ METHOD DETAIL ========== -->

<A NAME="method_detail"><!-- --></A>
<TABLE BORDER="1" WIDTH="100%" CELLPADDING="3" CELLSPACING="0" SUMMARY="">
<TR BGCOLOR="#CCCCFF" CLASS="TableHeadingColor">
<TH ALIGN="left" COLSPAN="1"><FONT SIZE="+2">
<B>Method Detail</B></FONT></TH>
</TR>
</TABLE>

<A NAME="getFile()"><!-- --></A><H3>
getFile</H3>
<PRE>
public <A HREF="http://java.sun.com/j2se/1.4.2/docs/api/java/io/File.html" title="class or interface in java.io">File</A> <B>getFile</B>()</PRE>
<DL>
<DD>Return the file when the operation was cancelled.
<P>
<DD><DL>

<DT><B>Returns:</B><DD>the file when the operation was cancelled</DL>
</DD>
</DL>
<HR>

<A NAME="getDepth()"><!-- --></A><H3>
getDepth</H3>
<PRE>
public int <B>getDepth</B>()</PRE>
<DL>
<DD>Return the depth when the operation was cancelled.
<P>
<DD><DL>

<DT><B>Returns:</B><DD>the depth when the operation was cancelled</DL>
</DD>
</DL>
<!-- ========= END OF CLASS DATA ========= -->
<HR>


<!-- ======= START OF BOTTOM NAVBAR ====== -->
<A NAME="navbar_bottom"><!-- --></A>
<A HREF="#skip-navbar_bottom" title="Skip navigation links"></A>
<TABLE BORDER="0" WIDTH="100%" CELLPADDING="1" CELLSPACING="0" SUMMARY="">
<TR>
<TD COLSPAN=2 BGCOLOR="#EEEEFF" CLASS="NavBarCell1">
<A NAME="navbar_bottom_firstrow"><!-- --></A>
<TABLE BORDER="0" CELLPADDING="0" CELLSPACING="3" SUMMARY="">
  <TR ALIGN="center" VALIGN="top">
  <TD BGCOLOR="#EEEEFF" CLASS="NavBarCell1">    <A HREF="../../../../overview-summary.html"><FONT CLASS="NavBarFont1"><B>Overview</B></FONT></A>&nbsp;</TD>
  <TD BGCOLOR="#EEEEFF" CLASS="NavBarCell1">    <A HREF="package-summary.html"><FONT CLASS="NavBarFont1"><B>Package</B></FONT></A>&nbsp;</TD>
  <TD BGCOLOR="#FFFFFF" CLASS="NavBarCell1Rev"> &nbsp;<FONT CLASS="NavBarFont1Rev"><B>Class</B></FONT>&nbsp;</TD>
  <TD BGCOLOR="#EEEEFF" CLASS="NavBarCell1">    <A HREF="class-use/DirectoryWalker.CancelException.html"><FONT CLASS="NavBarFont1"><B>Use</B></FONT></A>&nbsp;</TD>
  <TD BGCOLOR="#EEEEFF" CLASS="NavBarCell1">    <A HREF="package-tree.html"><FONT CLASS="NavBarFont1"><B>Tree</B></FONT></A>&nbsp;</TD>
  <TD BGCOLOR="#EEEEFF" CLASS="NavBarCell1">    <A HREF="../../../../deprecated-list.html"><FONT CLASS="NavBarFont1"><B>Deprecated</B></FONT></A>&nbsp;</TD>
  <TD BGCOLOR="#EEEEFF" CLASS="NavBarCell1">    <A HREF="../../../../index-all.html"><FONT CLASS="NavBarFont1"><B>Index</B></FONT></A>&nbsp;</TD>
  <TD BGCOLOR="#EEEEFF" CLASS="NavBarCell1">    <A HREF="../../../../help-doc.html"><FONT CLASS="NavBarFont1"><B>Help</B></FONT></A>&nbsp;</TD>
  </TR>
</TABLE>
</TD>
<TD ALIGN="right" VALIGN="top" ROWSPAN=3><EM>
</EM>
</TD>
</TR>

<TR>
<TD BGCOLOR="white" CLASS="NavBarCell2"><FONT SIZE="-2">
&nbsp;<A HREF="../../../../org/apache/commons/io/DirectoryWalker.html" title="class in org.apache.commons.io"><B>PREV CLASS</B></A>&nbsp;
&nbsp;<A HREF="../../../../org/apache/commons/io/EndianUtils.html" title="class in org.apache.commons.io"><B>NEXT CLASS</B></A></FONT></TD>
<TD BGCOLOR="white" CLASS="NavBarCell2"><FONT SIZE="-2">
  <A HREF="../../../../index.html?org/apache/commons/io/DirectoryWalker.CancelException.html" target="_top"><B>FRAMES</B></A>  &nbsp;
&nbsp;<A HREF="DirectoryWalker.CancelException.html" target="_top"><B>NO FRAMES</B></A>  &nbsp;
&nbsp;<SCRIPT type="text/javascript">
  <!--
  if(window==top) {
    document.writeln('<A HREF="../../../../allclasses-noframe.html"><B>All Classes</B></A>');
  }
  //-->
</SCRIPT>
<NOSCRIPT>
  <A HREF="../../../../allclasses-noframe.html"><B>All Classes</B></A>
</NOSCRIPT>


</FONT></TD>
</TR>
<TR>
<TD VALIGN="top" CLASS="NavBarCell3"><FONT SIZE="-2">
  SUMMARY:&nbsp;NESTED&nbsp;|&nbsp;FIELD&nbsp;|&nbsp;<A HREF="#constructor_summary">CONSTR</A>&nbsp;|&nbsp;<A HREF="#method_summary">METHOD</A></FONT></TD>
<TD VALIGN="top" CLASS="NavBarCell3"><FONT SIZE="-2">
DETAIL:&nbsp;FIELD&nbsp;|&nbsp;<A HREF="#constructor_detail">CONSTR</A>&nbsp;|&nbsp;<A HREF="#method_detail">METHOD</A></FONT></TD>
</TR>
</TABLE>
<A NAME="skip-navbar_bottom"></A>
<!-- ======== END OF BOTTOM NAVBAR ======= -->

<HR>
Copyright � 2002-2008 <a href="http://www.apache.org/">The Apache Software Foundation</a>. All Rights Reserved.
</BODY>
</HTML>
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  