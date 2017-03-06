package com.company;

import org.basex.*;
import org.basex.api.client.*;
import org.basex.core.*;
import org.basex.core.cmd.*;

import java.util.Scanner;

/**
 * This class demonstrates database access via the client/server architecture.
 *
 * @author BaseX Team 2005-17, BSD License
 */
public final class ServerCommands {
    /**
     * Runs the example code.
     * @param args (ignored) command-line arguments
     * @throws Exception exception
     */
    public static void main(final String... args) throws Exception {

        Scanner sc = new Scanner(System.in);


        System.out.println("=== ServerCommands ===");

        // Start server on default port 1984
        //BaseXServer server = new BaseXServer();

        // Create a client session with host name, port, user name and password
        System.out.println("\n* Create a client session.");

        try(ClientSession session = new ClientSession("localhost", 1984, "admin", "admin")) {

            // Create a database
            System.out.println("\n* Create a database.");

            session.execute(new CreateDB("input", "/home/46406163y/Baixades/Factbook.xml"));

            int aux=0;

            System.out.println(" ");
            System.out.println("----------------------- MENU ----------------------");
            System.out.println(" ");
            System.out.println("Para saber los paises de la base de datos pulse --------------- 1");
            System.out.println("Para saber cuantos paises hay pulse --------------------------- 2");
            System.out.println("Para saber la información sobre Alemania pulse  --------------- 3");
            System.out.println("Para saber cuanta gente vive en Uganda pulse ------------------ 4");
            System.out.println("Para saber las ciudades de Peru pulse ------------------------- 5");
            System.out.println("Para saber cuanta gente vive en Shangai pulse ----------------- 6");
            System.out.println("Para saber el codigo de la matricula de coche de xipre pulse -- 7");
            System.out.println(" ");
            // Run a query
            System.out.println("\n* Run a query:");

            aux = sc.nextInt();

            switch (aux) {

                case 1:
                try (ClientQuery query = session.query("//country")) {
                    System.out.println(query.execute());
                }
                    break;

                case 2:
                    try (ClientQuery query = session.query("count(//country)")) {
                        System.out.println(query.execute());
                    }
                    break;

                case 3:
                    try (ClientQuery query = session.query("/factbook/record[country=\"Germany\"]/introduction/background")) {
                        System.out.println(query.execute());
                    }
                    break;

                case 4:
                    try (ClientQuery query = session.query("/factbook/record[country=\"Uganda\"]/people/population")) {
                        System.out.println(query.execute());
                    }
                    break;

                case 5:
                    try (ClientQuery query = session.query("/factbook/record[country=\"Uganda\"]/people/population")) {
                        System.out.println(query.execute());
                    }
                    break;
            }

            // Faster version: specify an output stream and run a query
            System.out.println("\n* Run a query (faster):");

            session.setOutputStream(System.out);
            session.setOutputStream(System.out);
            try(ClientQuery query = session.query("//li")) {
                query.execute();
            }
            System.out.println();

            // Reset output stream
            session.setOutputStream(null);

            // Run a query
            System.out.println("\n\n* Run a buggy query: ");

            try {
                session.execute(new XQuery("///"));
            } catch(final BaseXException ex) {
                System.out.println(ex.getMessage());
            }

            // Drop the database
            System.out.println("\n* Close and drop the database.");

        //    session.execute(new DropDB("input"));
        }

        // Stop the server
        System.out.println("\n* Stop the server.");

        //server.stop();
    }
}