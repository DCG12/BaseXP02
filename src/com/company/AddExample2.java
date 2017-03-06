package com.company;

import java.io.*;
import java.nio.file.Files;

/**
 * This example shows how documents can be added to databases, and how
 * existing documents can be replaced.
 *
 * This example requires a running database server instance.
 * Documentation: http://docs.basex.org/wiki/Clients
 *
 * @author BaseX Team 2005-17, BSD License
 */
public final class AddExample2 {
    /**
     * Main method.
     *
     * @param args command-line arguments
     * @throws IOException I/O exception
     */
    public static void main(final String... args) throws IOException {
        // create session
        try (BaseXClient session = new BaseXClient("localhost", 1984, "admin", "admin")) {
            // create empty database
            session.execute("create db Prova2");
            System.out.println(session.info());

            byte[] array = Files.readAllBytes(new File("/home/46406163y/Baixades/Factbook.xml").toPath());

            // define input stream
            InputStream bais = new ByteArrayInputStream(array);

            // add document
            session.add("Factbook.xml", bais);
            System.out.println(session.info());

            // define input stream
            bais = new ByteArrayInputStream("<x>Hello Universe!</x>".getBytes());

            // add document
            session.add("universe.xml", bais);
            System.out.println(session.info());

            // run query on database
            System.out.println(session.execute("xquery collection('Prova2')//firstname"));

            // drop database
    //        session.execute("drop db database");
        }
    }
}