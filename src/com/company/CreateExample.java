package com.company;

import java.io.*;

/**
 * This example shows how new databases can be created.
 *
 * This example requires a running database server instance.
 * Documentation: http://docs.basex.org/wiki/Clients
 *
 * @author BaseX Team 2005-17, BSD License
 */
public final class CreateExample {
  /**
   * Main method.
   * @param args command-line arguments
   * @throws IOException I/O exception
   */
  public static void main(final String... args) throws IOException {
    // create session
    try(BaseXClient session = new BaseXClient("localhost", 1984, "admin", "admin")) {

      System.out.println(session.execute("info"));

      System.out.println(session.execute("xquery collection('Prova2')/factbook//country"));

    }
  }
}
