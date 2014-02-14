/*******************************************************************************
 * This file is part of Pebble.
 * 
 * Original work Copyright (c) 2009-2013 by the Twig Team
 * Modified work Copyright (c) 2013 by Mitchell Bösecke
 * 
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 ******************************************************************************/
package com.mitchellbosecke.pebble.node.expression;

import com.mitchellbosecke.pebble.compiler.Compiler;
import com.mitchellbosecke.pebble.compiler.NodeVisitor;
import com.mitchellbosecke.pebble.node.NodeExpression;

public class NodeExpressionBlockReferenceAndFunction extends NodeExpression {

	private NodeExpressionNamedArguments args;

	private String blockName;

	/*
	 * output is true if the block is referenced in an expression using the {{
	 * block() }} function, otherwise it is false if it is referenced using
	 * block tags, ie. {% block name %}{% endblock %}
	 */
	private final boolean isExpression;

	public NodeExpressionBlockReferenceAndFunction(int lineNumber, NodeExpressionNamedArguments args) {
		super(lineNumber);
		this.args = args;
		this.isExpression = true;
	}

	public NodeExpressionBlockReferenceAndFunction(int lineNumber, String blockName) {
		super(lineNumber);
		this.blockName = blockName;
		this.isExpression = false;
	}

	@Override
	public void compile(Compiler compiler) {
		if (this.isExpression) {
			compiler.raw("block(").subcompile(args.getArgs().get(0).getValue()).raw(", context, false)");
		} else {
			compiler.newline().write("block(").string(blockName).raw(", context, false, writer);").newline();
		}
	}

	@Override
	public void accept(NodeVisitor visitor) {
		visitor.visit(this);
	}

}
