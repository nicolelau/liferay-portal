/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.source.formatter.checks;

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.tools.ToolsUtil;
import com.liferay.source.formatter.checks.util.JSPSourceUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Hugo Huijser
 */
public class JSPStylingCheck extends StylingCheck {

	@Override
	protected String doProcess(
		String fileName, String absolutePath, String content) {

		_checkChaining(fileName, content);

		content = _formatLineBreak(fileName, content);

		content = _fixEmptyJavaSourceTag(content);

		content = _fixIncorrectClosingTag(content);

		content = _fixIncorrectSingleLineJavaSource(content);

		content = StringUtil.replace(
			content,
			new String[] {
				"alert('<%= LanguageUtil.", "alert(\"<%= LanguageUtil.",
				"confirm('<%= LanguageUtil.", "confirm(\"<%= LanguageUtil.",
				";;\n"
			},
			new String[] {
				"alert('<%= UnicodeLanguageUtil.",
				"alert(\"<%= UnicodeLanguageUtil.",
				"confirm('<%= UnicodeLanguageUtil.",
				"confirm(\"<%= UnicodeLanguageUtil.", ";\n"
			});

		content = content.replaceAll("'<%= (\"[^.(\\[\"]+\") %>'", "$1");

		_checkIllegalSyntax(
			fileName, content, "=>", "Do not use arrow function",
			"arrow_functions.markdown");
		_checkIllegalSyntax(
			fileName, content, "console.log(", "Do not use console.log");
		_checkIllegalSyntax(
			fileName, content, "debugger.", "Do not use debugger");

		if (!fileName.endsWith("test.jsp")) {
			_checkIllegalSyntax(
				fileName, content, "System.out.print",
				"Do not call 'System.out.print'");
		}

		return formatStyling(content);
	}

	@Override
	protected boolean isJavaSource(String content, int pos) {
		return JSPSourceUtil.isJavaSource(content, pos, true);
	}

	private void _checkChaining(String fileName, String content) {
		Matcher matcher = _chainingPattern.matcher(content);

		if (matcher.find()) {
			addMessage(
				fileName, "Avoid chaining on 'getClass'", "chaining.markdown",
				getLineNumber(content, matcher.start()));
		}
	}

	private void _checkIllegalSyntax(
		String fileName, String content, String syntax, String message) {

		_checkIllegalSyntax(fileName, content, syntax, message, null);
	}

	private void _checkIllegalSyntax(
		String fileName, String content, String syntax, String message,
		String markdownFileName) {

		int pos = -1;

		while (true) {
			pos = content.indexOf(syntax, pos + 1);

			if (pos == -1) {
				return;
			}

			if (!ToolsUtil.isInsideQuotes(content, pos)) {
				addMessage(
					fileName, message, markdownFileName,
					getLineNumber(content, pos));
			}
		}
	}

	private String _fixEmptyJavaSourceTag(String content) {
		Matcher matcher = _emptyJavaSourceTagPattern.matcher(content);

		if (matcher.find()) {
			return StringUtil.replace(
				content, matcher.group(), StringPool.BLANK);
		}

		return content;
	}

	private String _fixIncorrectClosingTag(String content) {
		Matcher matcher = _incorrectClosingTagPattern.matcher(content);

		if (matcher.find()) {
			return StringUtil.replaceFirst(
				content, " />\n", "\n" + matcher.group(1) + "/>\n",
				matcher.end(1));
		}

		return content;
	}

	private String _fixIncorrectSingleLineJavaSource(String content) {
		Matcher matcher = _incorrectSingleLineJavaSourcePattern.matcher(
			content);

		while (matcher.find()) {
			String javaSource = matcher.group(3);

			if (javaSource.contains("<%")) {
				continue;
			}

			String indent = matcher.group(1);

			StringBundler sb = new StringBundler(6);

			sb.append("<%\n");
			sb.append(indent);
			sb.append(StringUtil.trim(javaSource));
			sb.append("\n");
			sb.append(indent);
			sb.append("%>");

			return StringUtil.replaceFirst(
				content, matcher.group(2), sb.toString(), matcher.start());
		}

		return content;
	}

	private String _formatLineBreak(String fileName, String content) {
		Matcher matcher = _incorrectLineBreakPattern1.matcher(content);

		while (matcher.find()) {
			if (!JSPSourceUtil.isJSSource(content, matcher.start(1))) {
				addMessage(
					fileName, "There should be a line break after '}'",
					getLineNumber(content, matcher.start(1)));
			}
		}

		matcher = _incorrectLineBreakPattern2.matcher(content);

		while (matcher.find()) {
			if (JSPSourceUtil.isJavaSource(content, matcher.start())) {
				return StringUtil.replaceFirst(
					content, matcher.group(1), StringPool.SPACE,
					matcher.start());
			}
		}

		return content;
	}

	private static final Pattern _chainingPattern = Pattern.compile(
		"\\WgetClass\\(\\)\\.");
	private static final Pattern _emptyJavaSourceTagPattern = Pattern.compile(
		"\n\t*<%\\!?\n+\t*%>(\n|\\Z)");
	private static final Pattern _incorrectClosingTagPattern = Pattern.compile(
		"\n(\t*)\t((?!<\\w).)* />\n");
	private static final Pattern _incorrectLineBreakPattern1 = Pattern.compile(
		"[\n\t]\\} ?(catch|else|finally) ");
	private static final Pattern _incorrectLineBreakPattern2 = Pattern.compile(
		"=(\n\\s*).*;\n");
	private static final Pattern _incorrectSingleLineJavaSourcePattern =
		Pattern.compile("(\t*)(<% (.*) %>)\n");

}