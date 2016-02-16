/*
 * (C) Copyright 2005 Davide Brugali, Marco Torchiano
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
 * 02111-1307  USA
 */
package multiformat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * The multiformat calculator
 */
public class Calculator {
	private LinkedList<Rational> operands = new LinkedList<>();
	private ArrayList<ActionListener> actionListenerList = new ArrayList<ActionListener>();
	private String input="";

	// The current format of the calculator
	private Format format = new FixedPointFormat();
	// The current numberbase of the calculator
	private Base base = new DecimalBase();

	
	/**
	 * Add an operand to the stack.
	 *
	 * @param newOperand Operand value.
	 */
	public void addOperand(String newOperand) throws FormatException {
		Rational operand = this.format.parse(newOperand, this.base);
		this.operands.push(operand);
		notifyListners();
	}

	/**
	 * Remove the operand at the top of the stack and return it. Default to Zero
	 * if the stack is empty.
	 *
	 * @return Operand.
	 */
	private Rational popOperand() {
		if (this.operands.size() > 0) {
			return this.operands.pop();
		}
		return new Rational();
	}

	/**
	 * Add the two Rationals at the top of the stack.
	 */
	public void add() {
		Rational b = this.popOperand();
		Rational a = this.popOperand();
		this.operands.push(a.plus(b));
		notifyListners();
	}
	/**
	 * Subtract the two Rationals at the top of the stack.
	 */
	public void subtract() {
		Rational b = this.popOperand();
		Rational a = this.popOperand();
		this.operands.push(a.minus(b));
		notifyListners();
	}
	/**
	 * Multiply the two Rationals at the top of the stack.
	 */
	public void multiply() {
		Rational b = this.popOperand();
		Rational a = this.popOperand();
		this.operands.push(a.mul(b));
		notifyListners();
	}
	/**
	 * Divide the two Rationals at the top of the stack.
	 */
	public void divide() {
		Rational b = this.popOperand();
		Rational a = this.popOperand();
		this.operands.push(a.div(b));
		notifyListners();
	}
	/**
	 * Remove the operand at the top of the stack, if it exists.
	 */
	public void delete() {
		if (this.operands.size() > 0) {
			this.operands.removeFirst();
		}
		notifyListners();
	}

	/**
	 * Return the current first operand, or Zero.
	 *
	 * @return First operand value.
	 */
	public String firstOperand() {
		Rational operand = new Rational();
		if (this.operands.size() >= 2) {
			// The stack is in reverse order: the second operand is at the top, the
			// first is just below.
			operand = this.operands.get(1);
		}
		return this.format.toString(operand, this.base);
	}
	/**
	 * Return the current second operand, or Zero.
	 *
	 * @return Second operand value.
	 */
	public String secondOperand() {
		Rational operand = new Rational();
		if (this.operands.size() >= 1) {
			operand = this.operands.peek();
		}
		return this.format.toString(operand, this.base);
	}

	public void setBase(Base base) {
		this.base = base;
		notifyListners();
	}
	public Base getBase() {
		return this.base;
	}
	public void setFormat(Format format) {
		this.format = format;
		notifyListners();
	}
	public Format getFormat() {
		return this.format;
	}
	public void addActionListener(ActionListener l){
		actionListenerList.add(l);
		notifyListners();
	}
	private void notifyListners(ActionEvent e){
		for(ActionListener l:actionListenerList){
			l.actionPerformed(e);
		}
	}
	private void notifyListners(){
		notifyListners(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
	}
	
	public void setInput(String input){
		this.input = input;
		notifyListners();
		
	}
	public String getInput() {
		return input;
	}
}
