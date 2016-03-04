/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Prog;

/**
 *
 * @author foreyn
 */
public class ExceptionQuitter extends Exception {

    /**
     * Creates a new instance of <code>ExceptionQuitter</code> without detail
     * message.
     */
    public ExceptionQuitter() {
    }

    /**
     * Constructs an instance of <code>ExceptionQuitter</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ExceptionQuitter(String msg) {
        super(msg);
    }
}
