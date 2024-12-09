package com.example.main;


import com.example.main.UsuarioManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

class main {

    public static Connection conectarBaseDeDatos() {
        String URL = "jdbc:mysql://localhost:3306/oriental";
        String USER = "root";
        String PASSWORD = "";

        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos.");
            return connection;
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos.");
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        Connection connection = conectarBaseDeDatos();
        if (connection != null) {
            mostrarMenu(connection);
        } else {
            System.err.println("No se pudo establecer conexión con la base de datos.");
        }
    }

    private static void mostrarMenu(Connection connection) {
        Scanner scanner = new Scanner(System.in);
        UsuarioManager usuarioManager = new UsuarioManager();

        while (true) {
            try {
                // Mostrar el menú
                System.out.println("\n|-----------Menú:----------|");
                System.out.println("1. Crear usuario");
                System.out.println("2. Consultar usuario");
                System.out.println("3. Actualizar usuario");
                System.out.println("4. Eliminar usuario");
                System.out.println("5. Salir");
                System.out.print("| Elige una opción: ");

                // Validación para evitar NoSuchElementException
                int opcion = leerEntero(scanner);

                switch (opcion) {
                    case 1:
                        // Crear usuario
                        System.out.print("Ingresa el nombre del nuevo usuario: ");
                        String nombreUsuario = scanner.nextLine();
                        usuarioManager.crearUsuario(connection, nombreUsuario);
                        break;
                    case 2:
                        // Consultar usuario
                        System.out.print("Ingresa el nombre del usuario que deseas consultar: ");
                        String usuarioABuscar = scanner.nextLine();
                        usuarioManager.consultarUsuario(connection, usuarioABuscar);
                        break;
                    case 3:
                        // Actualizar usuario
                        usuarioManager.actualizarUsuario(connection);
                        break;
                    case 4:
                        // Eliminar usuario
                        usuarioManager.eliminarUsuario(connection);
                        break;
                    case 5:
                        // Salir
                        System.out.println("Saliendo del programa...");
                        scanner.close();
                        connection.close();
                        System.exit(0);
                        break;
                    default:
                        System.err.println("Opción inválida. Por favor, intenta nuevamente.");
                }
            } catch (Exception e) {
                System.err.println("Ocurrió un error inesperado.");
                e.printStackTrace();
                scanner.nextLine(); // Limpiar el buffer para evitar que el error se repita
            }
        }
    }

    // Función para leer la opción de forma segura
    private static int leerEntero(Scanner scanner) {
        while (true) {
            try {
                if (scanner.hasNextInt()) {
                    int valor = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer
                    return valor;
                } else {
                    System.err.println("Por favor, ingresa un número válido.");
                    scanner.nextLine(); // Limpiar el buffer
                }
            } catch (Exception e) {
                System.err.println("Error al leer la opción.");
                scanner.nextLine();
            }
        }
    }
}
