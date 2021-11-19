/*
 * The MIT License
 * Copyright © 2014-2021 Ilkka Seppälä
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.iluwatar.pessimistic;

import lombok.extern.slf4j.Slf4j;

/**
 * Summary
 * Prevents conflicts between concurrent transactions by allowing
 * only one transaction at a time to access data.
 * Use multiple system transactions, at which point manage concurrent access to your data.
 * Pessimistic Offline Lock prevents conflicts by avoiding them altogether.
 * It forces a transaction to acquire a lock on a piece of data before it starts to use it
 * once a transaction begins, complete it without being bounced by concurrency control.
 */
@Slf4j
public class App {
  /**
  * Program entry point.
  *
  * @param args command line args
  */
  public static void main(String[] args) {
    LockManager lockManager = new LockManager();
    lockManager.insert(new Customer("Timothy Cole"));
    lockManager.insert(new Customer("Jake Hill"));
    lockManager.insert(new Customer("Ben Webster"));

    // Admins
    long martinId = 1200;
    long davidId = 1004;

    System.out.println("Martin attempts to acquire permission to access customer " + 1L);
    Customer martinCustomer = lockManager.getCustomer(1L, martinId);

    System.out.println("David attempts to acquire permission to access customer " + 1L);
    Customer davidCustomer = lockManager.getCustomer(1L, davidId);

    if (martinCustomer != null) {
      System.out.println("Martin's Customer is " + martinCustomer.getName());
    } else {
      System.out.println("Martin has no customer");
    }

    if (davidCustomer != null) {
      System.out.println(davidCustomer.getName());
    } else {
      System.out.println("David has no customer");
    }

    System.out.println("Martin releases customer " + martinCustomer.getID());
    lockManager.release(martinCustomer, martinId);

    System.out.println("David attempts to acquire permission to access customer " + 1L);
    davidCustomer = lockManager.getCustomer(1L, davidId);
    if (davidCustomer != null) {
      System.out.println("David's Customer is "  + davidCustomer.getName());
    } else {
      System.out.println("David has no customer");
    }
  }
}