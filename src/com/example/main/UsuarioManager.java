package com.example.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UsuarioManager {

    public static void crearUsuario(Connection connection, String nombreUsuario) {
        Scanner scanner = new Scanner(System.in); // Capturar entradas

        try {
            // Solicitar email y contraseña al usuario
            System.out.print("Ingresa el correo electrónico del usuario: ");
            String email = scanner.nextLine();
            System.out.print("Ingresa la contraseña del usuario: ");
            String contrasenaUsuario = scanner.nextLine();

            // Consulta SQL para insertar el usuario en la base de datos
            String query = "INSERT INTO usuarios (nombre, email, contrasena) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            // Configurar parámetros
            preparedStatement.setString(1, nombreUsuario);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, contrasenaUsuario);

            // Ejecutar la consulta
            int filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Usuario creado con éxito.");
            } else {
                System.err.println("No se pudo crear el usuario.");
            }

            // Cerrar recursos
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al crear usuario.");
        }
    }


    public void consultarUsuario(Connection connection, String nombreUsuario) {
        // Nota: Solo hay un marcador de parámetro en la consulta.
        String sql = "SELECT * FROM usuarios WHERE nombre = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, nombreUsuario); // Solo configuramos el primer parámetro

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                String email = resultSet.getString("email");

                System.out.println("Usuario encontrado:");
                System.out.println("Nombre: " + nombre);
                System.out.println("Email: " + email);
            } else {
                System.out.println("No se encontró el usuario.");
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar el usuario.");
            e.printStackTrace();
        }
    }


    public void actualizarUsuario(Connection connection) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Se solicita el id del usuario que se desea actualizar
            System.out.print("Ingresa el id del usuario que deseas actualizar: ");
            int id_usuario = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer para la siguiente entrada

            // Solicitar el nuevo nombre y correo electrónico
            System.out.print("Ingresa el nuevo nombre: ");
            String nuevoNombre = scanner.nextLine();

            System.out.print("Ingresa el nuevo correo electrónico: ");
            String nuevoEmail = scanner.nextLine();

            // Comprobar si el correo electrónico ya existe en la base de datos
            String emailCheckQuery = "SELECT COUNT(*) FROM usuarios WHERE email = ? AND id_usuario != ?";
            try (PreparedStatement checkStmt = connection.prepareStatement(emailCheckQuery)) {
                checkStmt.setString(1, nuevoEmail);
                checkStmt.setInt(2, id_usuario);

                ResultSet rs = checkStmt.executeQuery();
                rs.next();
                int count = rs.getInt(1);

                if (count > 0) {
                    System.err.println("Error: El correo electrónico ya existe para otro usuario.");
                    return;
                }
            }

            String sql = "UPDATE usuarios SET nombre = ?, email = ? WHERE id_usuario = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, nuevoNombre);
                preparedStatement.setString(2, nuevoEmail);
                preparedStatement.setInt(3, id_usuario);

                int filasActualizadas = preparedStatement.executeUpdate();

                if (filasActualizadas > 0) {
                    System.out.println("Usuario actualizado correctamente.");
                } else {
                    System.out.println("No se encontró un usuario con ese id_usuario.");
                }
            }
        } catch (Exception e) {
            System.err.println("Error al actualizar el usuario.");
            e.printStackTrace();
        }
    }


    public void eliminarUsuario(Connection connection) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Ingresa el ID del usuario que deseas eliminar: ");

            // Validar correctamente la lectura de datos
            int idUsuario = 0;
            while (true) {
                try {
                    String entrada = scanner.nextLine().trim();
                    if (!entrada.isEmpty()) {
                        idUsuario = Integer.parseInt(entrada);
                        break;
                    }
                    System.err.println("Por favor, ingresa un número válido.");
                } catch (NumberFormatException e) {
                    System.err.println("Por favor, ingresa un número válido.");
                }
            }

            String sql = "DELETE FROM usuarios WHERE id_usuario = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, idUsuario);
                int filasEliminadas = preparedStatement.executeUpdate();

                if (filasEliminadas > 0) {
                    System.out.println("Usuario eliminado correctamente.");
                } else {
                    System.out.println("No se encontró un usuario con ese ID.");
                }
            }

        } catch (Exception e) {
            System.err.println("Error al eliminar el usuario.");
            e.printStackTrace();
        }
    }
}


