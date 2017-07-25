PRE>
<A HREF="http://java.sun.com/j2se/1.4.2/docs/api/java/lang/Object.html" title="class or interface in java.lang">java.lang.Object</A>
  <IMG SRC="../../../../resources/inherit.gif" ALT="extended by "><A HREF="http://java.sun.com/j2se/1.4.2/docs/api/java/lang/Throwable.html" title="class or interface in java.lang">java.lang.Throwable</A>
      <IMG SRC="../../../../resources/inherit.gif" ALT="extended by "><A HREF="http://java.sun.com/j2se/1.4.2/docs/api/java/lang/Exception.html" title="class or interface in java.lang">java.lang.Exception</A>
          <IMG SRC="../../../../resources/inherit.gif" ALT="extended by "><A HREF="http://java.sun.com/j2se/1.4.2/docs/api/java/io/IOException.html" title="class or interface in java.io">java.io.IOException</A>
              <IMG SRC="../../../../resources/inherit.gif" ALT="extended by "><B>org.apache.commons.io.DirectoryWalker.CancelException</B>
</PRE>
<DL>
<DT><B>All Implemented Interfaces:</B> <DD><A HREF="http://java.sun.com/j2se/1.4.2/docs/api/java/io/Serializable.html" title="class or interface in java.io">Serializable</A></DD>
</DL>
<DL>
<DT><B>Enclosing class:</B><DD><A HREF="../../../../org/apache/commons/io/DirectoryWalker.html" title="class in org.apache.commons.io">DirectoryWalker</A></DD>
</DL>
<HR>
<DL>
<DT><PRE>public static class <B>DirectoryWalker.CancelException</B><DT>extends <A HREF="http://java.sun.com/j2se/1.4.2/docs/api/java/io/IOException.html" title="class or interface in java.io">IOException</A></DL>
</PRE>

<P>
CancelException is thrown in DirectoryWalker to cancel the current
 processing.
<P>

<P>
<DL>
<DT><B>See Also:</B><DD><A HREF="../../../../serialized-form.html#org.apache.commons.io.DirectoryWalker.CancelException">Serialized Form</A></DL>
<HR>

<P>

<!-- ======== CONSTRUCTOR SUMMARY ======== -->

<A NAME="constructor_summary"><!-- --></A>
<TABLE BORDER="1" WIDTH="100%" CELLPADDING="3" CELLSPACING="0" SUMMARY="">
<TR BGCOLOR="#CCCCFF" CLASS="TableHeadingColor">
<TH ALIGN="left" COLSPAN="2"><FONT SIZE="+2">
<B>Constructor Summary</B></FONT></TH>
</TR>
<TR BGCOLOR="white" CLASS="TableRowColor">
<TD><CODE><B><A HREF="../../../../org/apache/commons/io/DirectoryWalker.CancelException.html#DirectoryWalker.CancelException(java.io.File, int)">DirectoryWalker.CancelException</A></B>(<A HREF="http://java.sun.com/j2se/1.4.2/docs/api/java/io/File.html" title="class or interface in java.io">File</A>&nbsp;file,
                                int&nbsp;depth)</CODE>

<BR>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Constructs a <code>CancelException</code> with
 the file and depth when cancellation occurred.</TD>
</TR>
<TR BGCOLOR="white" CLASS="TableRowColor">
<TD><CODE><B><A HREF="../../../../org/apache/commons/io/DirectoryWalker.CancelException.html#DirectoryWalker.CancelException(java.lang.String, java.io.File, int)">DirectoryWalker.CancelException</A></B>(<A HREF="http://java.sun.com/j2se/1.4.2/docs/api/java/lang/String.html" title="class or interface in java.lang">String</A>&nbsp;message,
                                <A HREF="http://java.sun.com/j2se/1.4.2/docs/api/java/io/File.html" title="class or interface in java.io">File</A>&nbsp;file,
                                int&nbsp;depth)</CODE>

<BR>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Constructs a <code>CancelException</code> with
 an appropriate message and the file and depth when
 cancellation occurred.</TD>
</TR>
</TABLE>
&nbsp;
<!-- ========== METHOD SUMMARY =========== -->

<A NAME="method_summary"><!-- --></A>
<TABLE BORDER="1" WIDTH="100%" CELLPADDING="3" CELLSPACING="0" SUMMARY="">
<TR BGCOLOR="#CCCCFF" CLASS="TableHeadingColor">
<TH ALIGN="left" COLSPAN="2"><FONT SIZE="+2">
<B>Method Summary</B></FONT></TH>
</TR>
<TR BGCOLOR="white" CLASS="TableRowColor">
<TD ALIGN="right" VALIGN="top" WIDTH="1%"><FONT SIZE="-1">
<CODE>&nbsp;int</CODE></FONT></TD>
<TD><CODE><B><A HREF="../../../../org/apache/commons/io/DirectoryWalker.CancelException.html#getDepth()">getDepth</A></B>()</CODE>

<BR>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Return the depth when the operation was cancelled.</TD>
</TR>
<TR BGCOLOR="white" CLASS="TableRowColor">
<TD ALIGN="right" VALIGN="top" WIDTH="1%"><FONT SIZE="-1">
<CODE>&nbsp;<A HREF="http://java.sun.com/j2se/1.4.2/docs/api/java/io/File.html" title="class or interface in java.io">File</A></CODE></FONT></TD>
<TD><CODE><B><A HREF="../../../../org/apache/commons/io/DirectoryWalker.CancelException.html#getFile()">getFile</A></B>()</CODE>

<BR>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Return the file when the operation was cancelled.</TD>
</TR>
</TABLE>
&nbsp;<A NAME="methods_inherited_from_class_java.lang.Throwable"><!-- --></A>
<TABLE BORDER="1" WIDTH="100%" CELLPADDING="3" CELLSPACING="0" SUMMARY="">
<TR BGCOLOR="#EEEEFF" CLASS="TableSubHeadingColor">
<TH ALIGN="left"><B>Methods inherited from class java.lang.<A HREF="http://java.sun.com/j2se/1.4.2/docs/api/java/lang/Throwable.html" title="class or interface in java.lang">Throwable</A></B></TH>
</TR>
<TR BGCOLOR="white" CLASS="TableRowColor">
<TD><CODE><A HREF="http://java.sun.com/j2se/1.4.2/docs/api/java/lang/Throwable.html#fillInStackTrace()" title="class or interface in java.lang">fillInStackTrace</A>, <A HREF="http://java.sun.com/j2se/1.4.2/docs/api/java/lang/Throwable.html#getCause()" title="class or interface in java.lang">getCause</A>, <A HREF="http://java.sun.com/j2se/1.4.2/docs/api/java/lang/Throwable.html#getLocalizedMessage()" title="class or interface in java.lang">getLocalizedMessage</A>, <A HREF="http://java.sun.com/j2se/1.4.2/docs/api/java/lang/Throwable.html#getMessage()" title="class or interface in java.lang">getMessage</A>, <A HREF="http://java.sun.com/j2se/1.4.2/docs/api/java/lang/Throwable.html#getStackTrace()" title="class or interface in java.lang">getStackTrace</A>, <A HREF="http://java.sun.com/j2se/1.4.2/docs/api/java/lang/Throwable.html#initCause(java.lang.Throwable)" title="class or interface in java.lang">initCause</A>, <A HREF="http://java.sun.com/j2se/1.4.2/docs/api/java/lang/Throwable.html#printStackTrace()" title="class or interface in java.lang">printStackTrace</A>, <A HREF="http://java.sun.com/j2se/1.4.2/docs/api/java/lang/Throwable.html#printStackTrace(java.io.PrintStream)" title="class or interface in java.lang">printStackTrace</A>, <A HREF="http://java.sun.com/j2se/1.4.2/docs/api/java/lang/Throwable.html#printStackTrace(java.io.PrintWriter)" title="class or interface in java.lang">printStackTrace</A>, <A HREF="http://java.sun.com/j2se/1.4.2/docs/api/java/lang/Throwable.html#setStackTrace(java.lang.StackTraceElement[])" title="class or interface in java.lang">setStackTrace</A>, <A HREF="http://java.sun.com/j2se/1.4.2/docs/api/java/lang/Throwable.html#toString()" title="class or interface in java.lang">toString</A></CODE></TD>
</TR>
</TABLE>
&nbsp;<A NAME="methods_inherited_from_class_java.lang.Object"><!-- --></A>
<TABLE BORDER="1" WIDTH="100%" CELLPADDING="3" CELLSPACING="0" SUMMARY="">
<TR BGCOLOR="#EEEEFF" CLASS="TableSubHeadingColor">
<TH ALIGN="left"><B>Methods inherited from class java.lang.<A HREF="http://java.sun.com/j2se/1.4.2/docs/api/java/lang/Object.html" title="class or interface in java.lang">Object</A></B></TH>
</TR>
<TR BGCOLOR="white" CLASS="TableRowColor">
<TD><CODE><A HREF="http://java.sun.com/j2se/1.4.2/docs/api/java/lang/Object.html#clone()" title="class or interface in java.lang">clone</A>, <A HREF="http://java.sun.com/j2se/1.4.2/docs/api/java/lang/Object.html#equals(java.lang.Object)" title="class or interface in java.lang">equals</A>, <A HREF="http://java.sun.com/j2se/1.4.2/docs/api/java/lang/Object.html#finalize()" title="class or interface in java.lang">finalize</A>, <A HREF="http://java.sun.com/j2se/1.4.2/docs/api/java/lang/Object.html#getClass()